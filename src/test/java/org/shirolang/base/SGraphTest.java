/*
 * Copyright (c) 2012-2014 Jeffrey Guenther
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software  and associated documentation files (the
 * "Software"), to deal in the Software without restriction,  including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute,  sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT  NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.shirolang.base;


import org.junit.Assert;
import org.junit.Test;
import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.values.Path;
import org.shirolang.values.SDouble;
import org.shirolang.values.SIdent;

/**
 *
 */
public class SGraphTest {

    @Test
    public void getNameandFullName(){
        String expected = "MyGraph";
        SGraph g = new SGraph(expected);
        Assert.assertEquals("should have name", expected, g.getName());
        Assert.assertEquals("should have full name", expected, g.getFullName());
        Assert.assertEquals("full and short names should be the same", g.getFullName(), g.getName());
    }

    @Test
    public void addNode(){
        SGraph g = new SGraph();
        SNode n = new SNode();
        g.addNode(n);

        Assert.assertTrue(g.getNodes().contains(n));
        Assert.assertSame(g, n.getScope());
    }

    @Test
    public void getNode(){
        SGraph g = new SGraph();
        SNode n = new SNode();
        g.addNode(n);

        Assert.assertSame(n, g.getNode(""));
    }

    @Test
    public void addPort(){
        SGraph g = new SGraph();

        SDouble d = new SDouble();
        d.setSymbolType(SymbolType.PORT);
        g.addPort(d);

        Assert.assertTrue(g.getPorts().contains(d));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPortException(){
        SGraph g = new SGraph();

        SDouble d = new SDouble();
        g.addPort(d);
    }

    @Test
    public void getPort(){
        SGraph g = new SGraph();

        SDouble d = new SDouble();
        d.setSymbolType(SymbolType.PORT);
        g.addPort(d);

        Assert.assertSame(d, g.getPort(""));
    }

    @Test
    public void isRoot(){
        SGraph g = new SGraph("");
        Assert.assertTrue(g.isRoot());
    }

    @Test
    public void resolvePathToNodePrototype(){
        Assert.fail();
    }

    @Test
    public void resolvePathToNodeInstance() throws PathNotFoundException {
        SGraph g = new SGraph();
        SNode n = new SNode("Type", "maturityScore");
        g.addNode(n);

        SFunc path = g.resolvePath("maturityScore");
        Assert.assertSame(n, path);
    }

    @Test
    public void resolvePathNestedNode() throws PathNotFoundException {
        SGraph g = new SGraph();
        SNode n = new SNode("Type", "N");
        SNode p = new SNode("Type", "P");
        SDouble d = new SDouble(12.2);
        d.setSymbolType(SymbolType.PORT);
        d.setName("d");
        p.addPort(d);
        n.addNestedNode(p);
        g.addNode(n);

        Assert.assertSame(d, g.resolvePath("N.P.d"));
    }

    @Test(expected = PathNotFoundException.class)
    public void resolvePathToNodeInstanceFail() throws PathNotFoundException {
        SGraph g = new SGraph();
        SNode n = new SNode("Type", "maturityScore");
        g.addNode(n);

        SFunc path = g.resolvePath("maturtyScore");
        Assert.assertSame(n, path);
    }

    @Test
    public void resolvePathToLoosePort() throws PathNotFoundException {
        SGraph g = new SGraph();

        SDouble d = new SDouble();
        d.setSymbolType(SymbolType.PORT);
        d.setName("num");
        g.addPort(d);

        SFunc path = g.resolvePath("num");
        Assert.assertSame(d, path);
    }

    @Test(expected = PathNotFoundException.class)
    public void resolvePathToLoosePortFail() throws PathNotFoundException {
        SGraph g = new SGraph();

        SDouble d = new SDouble();
        d.setSymbolType(SymbolType.PORT);
        d.setName("num");
        g.addPort(d);

        SFunc path = g.resolvePath("nums");
        Assert.assertSame(d, path);
    }

    @Test
    public void resolveIdentifier(){
        SGraph g = new SGraph();

        SDouble d = new SDouble();
        d.setSymbolType(SymbolType.PORT);
        d.setName("num");
        g.addPort(d);

        d.evaluate();

        SIdent id = new SIdent(g, "num");

        id.evaluate();
        Assert.assertSame(d, id.getResult());

    }
}
