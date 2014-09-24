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

package org.shirolang.interpreter;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.SType;
import org.shirolang.functions.math.SAdd;
import org.shirolang.functions.math.SPower;
import org.shirolang.values.SDouble;
import org.shirolang.values.SIdent;

import java.util.List;

/**
 *
 */
public class ShiroInlineExpressionListenerTest {
    private static ParseTree buildParseTree(String expr){
        ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(expr));
        // parse
        ShiroParser parser = new ShiroParser(new CommonTokenStream(lex));
        parser.setBuildParseTree(true);
        return parser.shiro();
    }

    private Library lib;

    @Before
    public void setup(){
        lib = new Library();
    }


    @Test
    public void objectConstruction(){
        String code = "port a Double(11.0)\n" +
                "port b Double(2.0)\n" +
                "port c Power(a, b)\n" +
                "c + b\n";

        ShiroInlineExpressionListener inline = new ShiroInlineExpressionListener(lib);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(inline, buildParseTree(code));

        SGraph graph = lib.getDefaultGraph();
        SFunc a = graph.getPort("a");
        SFunc b = graph.getPort("b");
        SFunc c = graph.getPort("c");

        Assert.assertEquals(SType.DOUBLE, a.getType());
        Assert.assertEquals(SType.DOUBLE, b.getType());
        Assert.assertEquals(SType.POWER, c.getType());

        List<SFunc> dependencies = c.getDependencies();
        SFunc powDepA = dependencies.get(0);
        SFunc powDepB = dependencies.get(1);

        Assert.assertEquals("Ident", powDepA.getType());
        Assert.assertEquals("Ident", powDepB.getType());

        Assert.assertTrue("a ident refers to Double named a", powDepA.getDependencies().contains(a));
        Assert.assertTrue("b ident refers to Double named b", powDepB.getDependencies().contains(b));

//        SDouble a = new SDouble("a", 11.0);
//        g.addPort(a);
//        SDouble b = new SDouble("b", 2.0);
//        g.addPort(b);
//
//        SIdent aIdent = new SIdent(g, "a");
//        Assert.assertTrue(aIdent.getDependencies().contains(a));
//        SIdent bIdent = new SIdent(g, "b");
//        Assert.assertTrue(bIdent.getDependencies().contains(b));
//
//        SPower pow = new SPower(aIdent, bIdent);
//        pow.setName("c");
//        g.addPort(pow);
//        Assert.assertEquals(2, pow.getDependencies().size());
//        Assert.assertTrue(pow.getDependencies().contains(aIdent));
//        Assert.assertTrue(pow.getDependencies().contains(bIdent));
//
//        SIdent cIdent = new SIdent(g, "c");
//        Assert.assertTrue(cIdent.getDependencies().contains(pow));
//
//        SAdd add = new SAdd(cIdent, bIdent);
//        Assert.assertTrue(add.getDependencies().contains(cIdent));
//        Assert.assertTrue(add.getDependencies().contains(bIdent));
    }
}
