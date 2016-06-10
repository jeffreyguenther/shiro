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
import org.shirolang.exceptions.NameUsedException;
import org.shirolang.functions.math.SAdd;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

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
        SFunc add = l.createFunction(null, "Add");
        Assert.assertEquals("Add", add.getType());
    }

    @Test
    public void registerMFuncByName() throws NameUsedException {
        String name = "myfunction";
        l.registerFunction(name, SAdd::new);
        SFunc function = l.createFunction(null, name);
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
                .getResourceAsStream("graph_named_with_internal_ports.sro")));
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
