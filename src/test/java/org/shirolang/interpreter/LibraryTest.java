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
import org.shirolang.exceptions.NameUsedException;
import org.shirolang.functions.math.SAdd;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LibraryTest{
    private  Library l;
    @Before
    public void setup(){
        l = new Library();
    }

    @Test
    public void library(){
        Library l = new Library();
    }

    @Test
    public void createFunction(){
        SFunc add = l.createFunction("Add");
        Assert.assertEquals("Add", add.getType());
    }

    @Test
    public void registerMFuncByName() throws NameUsedException {
        String name = "myfunction";
        l.registerFunction(name, SAdd::new);
        SFunc function = l.createFunction(name);
        assertEquals("Add", function.getType());
    }

    @Test
    public void isNameUsed(){
        Assert.assertTrue(l.isTypeNameUsed("Add"));
        Assert.assertFalse(l.isTypeNameUsed("Adder"));
    }

    @Test
    public void getDefaultGraph(){
        Assert.assertNotNull(l.getDefaultGraph());
    }

    @Test
    public void instantiateNamedGraph() throws IOException {
        ParseTreeWalker walker = new ParseTreeWalker();
        ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(this.getClass()
                .getResourceAsStream("graph_named.sro")));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        ShiroParser parser = new ShiroParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.shiro();

        DefinitionCollector definitionCollector = new DefinitionCollector();
        walker.walk(definitionCollector, tree);

        l.addNodeDefs(definitionCollector.getNodeDefinitions());
        l.addGraphDefs(definitionCollector.getGraphs());


        for(Map.Entry<String, ParseTree> e: l.getGraphDefs().entrySet()){
            SGraph graph = l.instantiateNamedGraph(e.getValue(), e.getKey());
            l.addGraph(graph);

            graph.evaluate();
        }

        Assert.assertTrue(l.getGraphDefs().containsKey("box_calc"));
    }

    @Test
    public void instantiateNamedGraphWithLoosePort() throws IOException {
        ParseTreeWalker walker = new ParseTreeWalker();
        ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(this.getClass()
                .getResourceAsStream("graph_named_with_loose_ports.sro")));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        ShiroParser parser = new ShiroParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.shiro();

        DefinitionCollector definitionCollector = new DefinitionCollector();
        walker.walk(definitionCollector, tree);

        l.addNodeDefs(definitionCollector.getNodeDefinitions());
        l.addGraphDefs(definitionCollector.getGraphs());


        for(Map.Entry<String, ParseTree> e: l.getGraphDefs().entrySet()){
            SGraph graph = l.instantiateNamedGraph(e.getValue(), e.getKey());
            l.addGraph(graph);

            graph.evaluate();
        }

        Assert.assertTrue(l.getGraphDefs().containsKey("box_calc"));
    }
}
