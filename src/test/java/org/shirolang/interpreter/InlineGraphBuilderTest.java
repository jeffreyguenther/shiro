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
import org.shirolang.base.SNode;

import java.io.IOException;

/**
 * Tests parse tree listener to instantiate inline graphs
 */
public class InlineGraphBuilderTest extends ShiroBaseTest{
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
    public void firstPass(){
        String code = "node Box begin\n" +
                "     input length Double\n" +
                "     input width Double\n" +
                "     input height Double\n" +
                "     update Multiply(length, width)\n" +
                "     output area Double(update)\n" +
                "     output name String(\"Box\")\n" +
                "end\n" +
                "\n" +
                "length Double(100.0)\n" +
                "\n" +
                "b Box\n" +
                "b.length(length)\n" +
                "b.width(20.0)\n" +
                "b.height(7.0)\n" +
                "\n";

        ParseTree tree = buildParseTree(code);
        ParseTreeWalker walker = new ParseTreeWalker();

        DefinitionCollector definitionCollector = new DefinitionCollector();
        walker.walk(definitionCollector, tree);

        lib.addGraphDefs(definitionCollector.getGraphs());
        lib.addNodeDefs(definitionCollector.getNodeDefinitions());

        InlineGraphBuilder inline = new InlineGraphBuilder(lib);

        walker.walk(inline, tree);

        SGraph graph = lib.getDefaultGraph();
        SNode node = graph.getNode("b");
        Assert.assertNotNull(node);
        Assert.assertEquals(6, node.getPorts().size());
        Assert.assertNotNull(lib.getNodeDefs().get("Box"));
        Assert.assertEquals(1, lib.getNodeDefs().size());
    }

    @Test
    public void secondPass(){
        String code = "node Box begin\n" +
                "     input length Double\n" +
                "     input width Double\n" +
                "     input height Double\n" +
                "     update Multiply(length, width)\n" +
                "     output area Double(update)\n" +
                "     output name String(\"Box\")\n" +
                "end\n" +
                "\n" +
                "length Double(100.0)\n" +
                "\n" +
                "b Box\n" +
                "b.length(length)\n" +
                "b.width(20.0)\n" +
                "b.height(7.0)\n" +
                "\n";

        ParseTree tree = buildParseTree(code);
        ParseTreeWalker walker = new ParseTreeWalker();

        DefinitionCollector definitionCollector = new DefinitionCollector();
        walker.walk(definitionCollector, tree);

        lib.addGraphDefs(definitionCollector.getGraphs());
        lib.addNodeDefs(definitionCollector.getNodeDefinitions());

        InlineGraphBuilder inline = new InlineGraphBuilder(lib);

        walker.walk(inline, tree);

        inline.setPass(GraphBuilder.SECOND_PASS);
        walker.walk(inline, tree);

        SGraph graph = lib.getDefaultGraph();
        SNode node = graph.getNode("b");
        Assert.assertNotNull(node);
        Assert.assertNotNull(lib.getNodeDefs().get("Box"));
        Assert.assertEquals(1, lib.getNodeDefs().size());

        Assert.assertEquals(1, graph.getNodes().size());
        Assert.assertEquals(13, graph.getPorts().size());
    }

    @Test
    public void inLineExpression() throws IOException {
        ParseTree tree = parse("graph_inline_expression.sro").shiro();

        ParseTreeWalker walker = new ParseTreeWalker();

        DefinitionCollector definitionCollector = new DefinitionCollector();
        walker.walk(definitionCollector, tree);

        lib.addGraphDefs(definitionCollector.getGraphs());
        lib.addNodeDefs(definitionCollector.getNodeDefinitions());

        InlineGraphBuilder inline = new InlineGraphBuilder(lib);

        walker.walk(inline, tree);

        inline.setPass(GraphBuilder.SECOND_PASS);
        walker.walk(inline, tree);

        SGraph g = lib.getDefaultGraph();

        Assert.assertEquals(8, g.getPorts().size());

        SFunc a = g.getPort("a");
        Assert.assertNotNull(a);

        SFunc b = g.getPort("b");
        Assert.assertNotNull(b);

        SFunc c = g.getPort("c");
        Assert.assertNotNull(c);

        g.evaluate();
    }
}
