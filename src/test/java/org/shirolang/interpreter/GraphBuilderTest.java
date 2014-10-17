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
import org.junit.Test;
import org.shirolang.base.SGraph;

import java.io.IOException;

/**
 *
 */
public class GraphBuilderTest extends ShiroBaseTest{
    @Test
    public void buildDefaultGraph() throws IOException {
        ParseTreeWalker walker = new ParseTreeWalker();
        Library l = new Library();

        ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(this.getClass()
                .getResourceAsStream("graph_inline_node_production_assignment.sro")));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        ShiroParser parser = new ShiroParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.shiro();

        DefinitionCollector definitionCollector = new DefinitionCollector();
        walker.walk(definitionCollector, tree);


        l.addNodeDefs(definitionCollector.getNodeDefinitions());

        SGraph g = l.getDefaultGraph();

        ParseTreeWalker w2 = new ParseTreeWalker();
        GraphBuilder graphBuilder = new GraphBuilder(l, g);
        w2.walk(graphBuilder, tree);

        Assert.assertEquals(1, g.getNodes().size());
        Assert.assertNotNull(g.getNode("b"));
        Assert.assertEquals(11, g.getPorts().size());

        g.evaluate();

    }
}
