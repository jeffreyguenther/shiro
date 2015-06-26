/**
 * The MIT License
 *
 * Copyright (c) 2012 - 2014 Jeffrey Guenther.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.shirolang.base;

import org.junit.Assert;
import org.junit.Test;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.values.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Test the a node
 */
public class SNodeTest {
    private Scope getScope(){
        return new Scope() {
            @Override
            public SFunc resolvePath(Path path) {
                return null;
            }

            @Override
            public SFunc resolvePath(String path) {
                return null;
            }

            @Override
            public String getName() {
                return "";
            }

            @Override
            public String getFullName() {
                return "";
            }

            @Override
            public boolean isRoot() {
                return true;
            }
        };
    }

    @Test
    public void defaultConstructor(){
        SNode n = new SNode();
        Assert.assertNull("should be null", n.getScope());
        Assert.assertEquals("should have no name", "", n.getName());
        Assert.assertEquals("should have no full name", "", n.getFullName());
        Assert.assertFalse("should have no options", n.hasOptions());
        Assert.assertFalse("should have no nested nodes", n.hasNestedNodes());
        Assert.assertFalse("should have no ports", n.hasPorts());
        Assert.assertNull("should not have active option", n.getActiveOption());
    }

    @Test
    public void threeArgConstructor(){
        // Stand in scope until graphs are implemented
        Scope g = getScope();

        String type = "EndPoints";
        String name = "endPoints";

        SNode n = new SNode(type, name, g);
        Assert.assertEquals("should be null",g ,n.getScope());
        Assert.assertEquals("should have type",type ,n.getType());
        Assert.assertEquals("should have name", name, n.getName());
        Assert.assertEquals("should have full name", name, n.getFullName());
        Assert.assertFalse("should have no options", n.hasOptions());
        Assert.assertFalse("should have no nested nodes", n.hasNestedNodes());
        Assert.assertFalse("should have no ports", n.hasPorts());
        Assert.assertNull("should not have active option", n.getActiveOption());

        String childNodeType = "Point";
        String childNodeName = "P1";
        String expectedFullName = "endPoints.P1";
        SNode childNode = new SNode(childNodeType, childNodeName, n);
        Assert.assertEquals("should have name", childNodeName, childNode.getName());
        Assert.assertEquals("should have full name", expectedFullName, childNode.getFullName());
        Assert.assertEquals("should have type", childNodeType, childNode.getType());
    }

    @Test
    public void setNameAndFullName(){
        String type = "EndPoints";
        String name = "endPoints";
        SNode n = new SNode(type, name, getScope());

        String childNodeType = "Point";
        String expectedChildNodeName = "P1";
        SNode childNode = new SNode(childNodeType, expectedChildNodeName, n);
        Assert.assertEquals("should update name", expectedChildNodeName, childNode.getName());
        n.addNestedNode(childNode);

        // Add ports
        SDouble p1 = new SDouble();
        p1.setSymbolType(SymbolType.PORT);
        p1.setName("x");

        childNode.addPort(p1);

        childNode.setName("A1");
        Assert.assertEquals("should update full name", "endPoints.A1", childNode.getFullName());
        Assert.assertEquals("should update name", "A1", childNode.getName());

        Assert.assertEquals("port should be renamed", "endPoints.A1.x", p1.getFullName());

        n.setName("greenPoints");
        Assert.assertEquals("should update full name", "greenPoints.A1", childNode.getFullName());
        Assert.assertEquals("should update name", "A1", childNode.getName());
        Assert.assertEquals("port should be renamed", "greenPoints.A1.x", p1.getFullName());

        n.setFullName("dogs.cats.endPoints");
        Assert.assertEquals("should update full name", "dogs.cats.endPoints.A1", childNode.getFullName());
        Assert.assertEquals("should update name", "A1", childNode.getName());
        Assert.assertEquals("port should be renamed", "dogs.cats.endPoints.A1.x", p1.getFullName());
    }

    @Test
    public void addNestedNode(){
        String type = "EndPoints";
        String name = "endPoints";
        SNode n = new SNode(type, name, getScope());

        String childNodeType = "Point";
        String childNodeName = "P1";
        String expectedFullName = "endPoints.P1";
        SNode childNode = new SNode(childNodeType, childNodeName, n);

        n.addNestedNode(childNode);
        Assert.assertEquals("should have full name", expectedFullName, childNode.getFullName());
        Assert.assertEquals("should have name", childNodeName, childNode.getName());
        Assert.assertEquals("should have new scope", n, childNode.getScope());
        Assert.assertTrue("should have nested nodes", n.hasNestedNodes());
    }

    @Test
    public void addPort(){
        SNode n =  new SNode();
        SDouble sDouble = new SDouble(23.4);
        sDouble.setSymbolType(SymbolType.PORT);
        sDouble.setName("a");
        n.addPort(sDouble);

        Assert.assertSame(sDouble, n.getInput("a"));
        Assert.assertSame(sDouble, n.getPort("a"));
    }

    @Test
    public void resolvePathToInternalPort() throws PathNotFoundException {
        SNode n =  new SNode();
        SDouble sDouble = new SDouble(23.4);
        sDouble.setSymbolType(SymbolType.PORT);
        sDouble.setName("a");
        n.addPort(sDouble);

        // resolve "a" in the scope of n.
        Assert.assertSame(sDouble, n.resolvePath("a"));
    }

    @Test(expected = PathNotFoundException.class)
    public void resolvePathToThis() throws PathNotFoundException {
        SNode n =  new SNode();
        SDouble sDouble = new SDouble(23.4);
        sDouble.setSymbolType(SymbolType.PORT);
        sDouble.setName("a");
        n.addPort(sDouble);

        // resolve "this.a" in the scope of n.
        Assert.assertSame(sDouble, n.resolvePath("this.a"));
    }

    @Test
    public void resolvePathToReference() throws PathNotFoundException {
        SNode n =  new SNode("A", "a");
        SNode nested =  new SNode("B", "b");
        n.addNestedNode(nested);

        Path reference = new Path();
        reference.makeReference();
        reference.addSegment(new PathSegment("a"));

        Assert.assertSame(n, n.resolvePath(reference));

        Path ref = new Path();
        ref.makeReference();
        ref.addSegment(new PathSegment("b"));

        Assert.assertSame(nested, n.resolvePath(ref));
    }

    @Test
    public void getDefaultOption(){
        SNode n = new SNode();
        Assert.assertNull(n.getDefaultOption());

        // add a default with the option not existing
        SDouble d = new SDouble(12.0);
        d.setSymbolType(SymbolType.PORT);
        d.setName("d");
        n.addOptionAsDefault(d);
        Assert.assertSame(d, n.getDefaultOption());

        SNode n1 = new SNode();
        // add a default with the option already existing
        SString s = new SString("232");
        s.setSymbolType(SymbolType.PORT);
        s.setName("s");
        n1.addOption(s);
        n1.addOption(d);
        Assert.assertTrue(n.getOptions().containsValue(d));
        n1.addOptionAsDefault(d);
        Assert.assertSame(d, n1.getDefaultOption());

    }

    @Test
    public void addOption(){
        SNode n =  new SNode("Type", "NodeName", null);
        SDouble sDouble = new SDouble(23.4);
        sDouble.setSymbolType(SymbolType.PORT);
        sDouble.setName("a");

        n.addOption(sDouble);
        Assert.assertSame(sDouble, n.getOption("a"));

        // create a node that will be nested
        SNode nested = new SNode("Boom", "nested", null);

        Assert.assertNull(n.getNestedNode("nested"));
        n.addOption(nested);
        Assert.assertEquals("name should be nested", "NodeName.nested", nested.getFullName());

        Assert.assertSame(nested, n.getNestedNode("nested"));
    }

    @Test(expected = RuntimeException.class)
    public void addOptionWithoutNameFail(){
        SNode n = new SNode();
        SDouble sDouble = new SDouble(23.4);
        sDouble.setSymbolType(SymbolType.PORT);

        n.addOption(sDouble);
    }

    @Test
    public void getOptions(){
        SNode n =  new SNode();
        SDouble sDouble = new SDouble(23.4);
        sDouble.setSymbolType(SymbolType.PORT);
        sDouble.setName("a");
        n.addOption(sDouble);

        SNode nested = new SNode("Boom", "nested", null);
        n.addOption(nested);

        Assert.assertTrue(n.getOptions().containsValue(sDouble));
        Assert.assertTrue(n.getOptions().containsValue(nested));
    }

    @Test
    public void addOptionAsActive(){
        SNode n =  new SNode();
        SDouble sDouble = new SDouble(23.4);
        sDouble.setSymbolType(SymbolType.PORT);
        sDouble.setName("a");
        n.addOptionAsActive(sDouble);

        Assert.assertSame(sDouble, n.getActiveOption());
        Assert.assertTrue(n.getOptions().containsValue(sDouble));
    }

    @Test(expected = OptionNotFoundException.class)
    public void setActiveOptionFail() throws OptionNotFoundException {
        SNode n = new SNode();
        n.addPort(new SDouble("a", 95.3));
        n.setActiveOption("boom");
    }

    @Test
    public void setActiveOption() throws OptionNotFoundException {
        SNode n = new SNode();
        SDouble d = new SDouble("a", 95.3);
        n.addOption(d);
        SDouble d2 = new SDouble("b", 5.3);
        n.addOption(d2);

        n.setActiveOption("b");
        Assert.assertSame(d2, n.getActiveOption());
        Assert.assertFalse(d.isActive());
        Assert.assertTrue(d2.isActive());

        n.setActiveOption("a");
        Assert.assertSame(d, n.getActiveOption());
        Assert.assertFalse(d2.isActive());
        Assert.assertTrue(d.isActive());

        Assert.assertNull(n.getDefaultOption());

        Assert.assertFalse(n.getPorts().contains(d2));
        Assert.assertTrue(n.getPorts().contains(d));
    }

    @Test
    public void addOptionAsDefault(){
        SNode n = new SNode();
        SDouble sDouble = new SDouble(23.4);
        sDouble.setSymbolType(SymbolType.PORT);
        sDouble.setName("a");

        n.addOptionAsDefault(sDouble);
        Assert.assertSame(sDouble, n.getDefaultOption());
        Assert.assertTrue(n.getOptions().containsValue(sDouble));
    }

    @Test
    public void resolvePathToActiveOptionPort() throws PathNotFoundException {
        SNode n =  new SNode();
        SDouble sDouble = new SDouble(23.4);
        sDouble.setSymbolType(SymbolType.PORT);
        sDouble.setName("a");
        n.addOptionAsActive(sDouble);

        // resolve "active" in the scope of n.
        Assert.assertSame(sDouble, n.resolvePath("active"));
    }

    @Test
    public void resolvePathToActiveOptionNode() throws PathNotFoundException {
        SNode n =  new SNode("Type", "A", null);
        SNode nested = new SNode("Type", "Z", null);
        n.addOptionAsActive(nested);

        Assert.assertSame(nested, n.resolvePath("active"));
    }

    @Test(expected = PathNotFoundException.class)
    public void resolvePathToActiveOptionNodeWithPortsFail() throws PathNotFoundException {
        SNode n =  new SNode("Type", "A", null);
        SNode nested = new SNode("Type", "Z", null);

        n.addOptionAsActive(nested);

        Assert.assertSame(nested, n.resolvePath("active.x"));
    }

    @Test
    public void resolvePathToActiveOptionNodeWithPorts() throws PathNotFoundException {
        SNode n =  new SNode("Type", "A", null);
        SNode nested = new SNode("Type", "Z", null);
        SDouble x = new SDouble(12.2);
        x.setSymbolType(SymbolType.PORT);
        x.setName("x");
        nested.addPort(x);

        n.addOptionAsActive(nested);

        Assert.assertSame(x, n.resolvePath("active.x"));
    }


    @Test(expected = PathNotFoundException.class)
    public void resolvePathToNestedNode() throws PathNotFoundException {
        SNode n =  new SNode("Type", "a", null);
        SNode nested = new SNode("Type", "z", null);

        n.addNestedNode(nested);

        Assert.assertSame(nested, n.resolvePath("Z"));
    }

    @Test
    public void resolvePathToNestedNodeWithPorts() throws PathNotFoundException {
        SNode n =  new SNode("Type", "a", null);
        SNode nested = new SNode("Type", "z", null);
        SDouble x = new SDouble(12.2);
        x.setSymbolType(SymbolType.PORT);
        x.setName("x");
        nested.addPort(x);

        n.addNestedNode(nested);

        Assert.assertSame(x, n.resolvePath("z.x"));
    }

    @Test
    public void resolvePathToGlobalScope() throws PathNotFoundException {
        SGraph g = new SGraph();
        SNode n = new SNode("Type", "a", g);
        SDouble d = new SDouble(183.2);
        d.setSymbolType(SymbolType.PORT);
        d.setName("d");
        n.addPort(d);

        SNode n1 = new SNode("Type", "b", g);
        SDouble d1 = new SDouble(13.2);
        d1.setSymbolType(SymbolType.PORT);
        d1.setName("e");
        n1.addPort(d1);

        g.addNode(n);
        g.addNode(n1);

        Assert.assertSame(d, n1.resolvePath("a.d"));
        Assert.assertSame(d1, n.resolvePath("b.e"));
    }

    @Test
    public void isRoot(){
        SNode n = new SNode();
        Assert.assertFalse(n.isRoot());
    }

    @Test
    public void getPorts(){
        SNode n = new SNode();
        SInteger i = new SInteger("b", 11);
        n.addPort(i);
        SIdent id = new SIdent(n, "b");
        SInteger i2 = new SInteger();
        i2.appendInput(id);
        n.addPort(i2);

        Set<SFunc> expected = new HashSet<>();
        expected.add(i);
        expected.add(i2);

        Assert.assertEquals(expected, n.getPorts());
    }

//    @Test
//    public void resolvePathIndices(){
//        SNode n = new SNode();
//        SDouble p = new SDouble("p", 100.0);
//        SDouble r = new SDouble("r", 0.05);
//        SDouble t = new SDouble("t", 1.0);
//
//        SSimpleInterest interest = new SSimpleInterest();
//        interest.setName("eval");
//        interest.setInput(SSimpleInterest.PRINCIPAL, p);
//        interest.setInput(SSimpleInterest.RATE, r);
//        interest.setInput(SSimpleInterest.DURATION, t);
//        n.addPort(interest);
//
//        List<String> parts = new ArrayList<>();
//        parts.add("eval");
//        Path path = new Path(parts, 0);
//        SIdent id = new SIdent(n, path);
//
//        List<String> parts2 = new ArrayList<>();
//        parts2.add("eval");
////        Path path2 = new Path(parts2, "value");
//        SIdent id2 = new SIdent(n, path2);
//
//        p.evaluate();
//        r.evaluate();
//        t.evaluate();
//        interest.evaluate();
//        id.evaluate();
//        id2.evaluate();
//
//        SDouble resInterest = (SDouble) id.getOutput();
//        SDouble resValue = (SDouble) id2.getOutput();
//        Assert.assertEquals(5.0, resInterest.getPath(), 1e-15);
//        Assert.assertEquals(105.0, resValue.getPath(), 1e-15);
//    }
}
