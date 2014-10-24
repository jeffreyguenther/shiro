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

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 *
 */
public class DefinitionCollectorTest extends ShiroBaseTest{

    @Test
    public void handlesNonNestedNodes() throws IOException {
        ShiroParser parser = parse("node_with_eval.sro");
        ParseTree tree = parser.shiro();

        ParseTreeWalker walker = new ParseTreeWalker();
        DefinitionCollector l = new DefinitionCollector();
        walker.walk(l, tree);

        Assert.assertEquals(1, l.getNodeDefinitions().size());
        Assert.assertTrue(l.getNodeDefinitions().containsKey("Box"));
    }

    @Test
    public void handlesNoAlts() throws IOException {
        ShiroParser parser = parse("node_with_eval.sro");
        ParseTree tree = parser.shiro();

        ParseTreeWalker walker = new ParseTreeWalker();
        DefinitionCollector l = new DefinitionCollector();
        walker.walk(l, tree);

        Assert.assertTrue(l.getAlternativeDefinitions().isEmpty());
    }


    @Test
    public void handlesNoGraphs() throws IOException {
        ShiroParser parser = parse("node_with_eval.sro");
        ParseTree tree = parser.shiro();

        ParseTreeWalker walker = new ParseTreeWalker();
        DefinitionCollector l = new DefinitionCollector();
        walker.walk(l, tree);

        Assert.assertTrue(l.getGraphs().isEmpty());
    }

    @Test
    public void collectNodeGraphAndState() throws IOException {
        ShiroParser parser = parse("graph_with_states.sro");

        ParseTree tree = parser.shiro();

        ParseTreeWalker walker = new ParseTreeWalker();
        DefinitionCollector l = new DefinitionCollector();
        walker.walk(l, tree);

        Assert.assertTrue(l.getAlternativeDefinitions().containsKey("lowRate"));
        Assert.assertTrue(l.getAlternativeDefinitions().containsKey("highRate"));
        Assert.assertTrue(l.getGraphs().containsKey("money"));
        Assert.assertTrue(l.getNodeDefinitions().containsKey("Invest"));
        Assert.assertTrue(l.getNodeDefinitions().containsKey("InterestRates"));
    }
}
