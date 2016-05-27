package org.shirolang.interpreter.ast;

import org.junit.Before;
import org.junit.Test;
import org.shirolang.fixtures.GraphDefinitionFixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProgramTest {
    private Program p;

    @Before
    public void createInstance(){
        p = new Program();
    }

    @Test
    public void getGraphDefs(){
        p.add(new GraphDefinition("g"));
        p.add(new GraphDefinition("g1"));
        assertEquals(2, p.getGraphDefs().size());
    }

    @Test
    public void getNodeDefs(){
        p.add(new NodeDefinition("A"));
        p.add(new NodeDefinition("B"));
        assertEquals(2, p.getNodeDefs().size());
    }

    @Test
    public void getStateDefs(){
        p.add(new StateDefinition("s1"));
        p.add(new StateDefinition("s2"));
        assertEquals(2, p.getStateDefs().size());
    }

    @Test
    public void includes(){
        p.add(new IncludeStatement("geom"));
        assertEquals("include \"geom\"", p.toCode());
    }

    @Test
    public void includesAndNode(){
        p.add(new NodeDefinition("A"));
        p.add(new IncludeStatement("geom"));
        assertEquals(
            "include \"geom\"\n\n" +
            "node A begin\nend", p.toCode()
        );
    }

    @Test
    public void includesNodeAndGraph(){
        p.add(new NodeDefinition("A"));
        p.add(new GraphDefinition("g"));
        p.add(new IncludeStatement("geom"));
        assertEquals(
            "include \"geom\"\n\n" +
            "node A begin\nend\n\n" +
            "graph g begin\nend",
            p.toCode()
        );
    }

    @Test
    public void includesNodeGraphAndState(){
        p.add(new NodeDefinition("A"));
        p.add(new GraphDefinition("g"));
        p.add(new IncludeStatement("geom"));
        p.add(new StateDefinition("s1"));
        assertEquals(
            "include \"geom\"\n\n" +
            "node A begin\nend\n\n" +
            "graph g begin\nend\n\n" +
            "state s1 begin\n" +
            "    graph ^\n" +
            "end",
            p.toCode()
        );
    }

    @Test
    public void includeNodeGraphDefaultGraphAndState(){
        p.add(new NodeDefinition("A"));
        p.add(new GraphDefinition("g"));
        p.add(new IncludeStatement("geom"));
        p.add(new StateDefinition("s1"));
        p.add(GraphDefinitionFixture.defaultGraphWithFunctionDeclarations());
        assertEquals(
            "include \"geom\"\n\n" +
            "node A begin\nend\n\n" +
            "b Box[a](1, 2, 3)\n\n" +
            "graph g begin\nend\n\n" +
            "state s1 begin\n" +
            "    graph ^\n" +
            "end",
                p.toCode()
        );
    }

    @Test
    public void singleGraph(){
        p.add(new GraphDefinition("g"));
        assertEquals("graph g begin\nend", p.toCode());
    }

    @Test
    public void emptyDefaultGraph(){
        p.add(new GraphDefinition());
        assertTrue(p.hasDefaultGraph());
        assertEquals(
            "", p.toCode()
        );
    }

    @Test
    public void defaultGraph(){
        p.add(GraphDefinitionFixture.defaultGraphWithFunctionDeclarations());
        assertEquals(
            "b Box[a](1, 2, 3)", p.toCode()
        );
    }

    @Test
    public void defaultGraphWithNode(){
        p.add(new NodeDefinition("A"));
        p.add(GraphDefinitionFixture.defaultGraphWithFunctionDeclarations());
        assertEquals(
          "node A begin\nend\n\n" +
          "b Box[a](1, 2, 3)", p.toCode()
        );
    }

    @Test
    public void defaultGraphWithNodeAndNamedGraph(){
        p.add(new NodeDefinition("A"));
        p.add(GraphDefinitionFixture.defaultGraphWithFunctionDeclarations());
        p.add(new GraphDefinition("g"));
        assertEquals(
            "node A begin\nend\n\n" +
            "b Box[a](1, 2, 3)\n\n" +
            "graph g begin\nend",
            p.toCode()
        );
    }

    @Test
    public void defaultGraphWithNodeNamedGraphAndState(){
        p.add(new NodeDefinition("A"));
        p.add(GraphDefinitionFixture.defaultGraphWithFunctionDeclarations());
        p.add(new GraphDefinition("g"));
        p.add(new StateDefinition("s1"));
        assertEquals(
            "node A begin\nend\n\n" +
            "b Box[a](1, 2, 3)\n\n" +
            "graph g begin\nend\n\n" +
            "state s1 begin\n" +
            "    graph ^\n" +
            "end",
            p.toCode()
        );
    }

    @Test
    public void multipleGraphs(){
        p.add(new GraphDefinition("g"));
        p.add(new GraphDefinition("g1"));
        assertEquals(
            "graph g begin\nend\n\n" +
            "graph g1 begin\nend", p.toCode()
        );
    }

    @Test
    public void singleNode(){
        p.add(new NodeDefinition("A"));
        assertEquals("node A begin\nend", p.toCode());
    }

    @Test
    public void multipleNodes(){
        p.add(new NodeDefinition("A"));
        p.add(new NodeDefinition("B"));
        assertEquals(
            "node A begin\nend\n\n" +
            "node B begin\nend", p.toCode()
        );
    }

    @Test
    public void singleState(){
        p.add(new StateDefinition("s1"));
        assertEquals(
            "state s1 begin\n" +
            "    graph ^\n" +
            "end", p.toCode()
        );
    }

    @Test
    public void graphNodeStateDefs(){
        p.add(new GraphDefinition("g"));
        p.add(new NodeDefinition("A"));
        p.add(new NodeDefinition("B"));
        p.add(new StateDefinition("s1"));

        assertEquals(
            "node A begin\nend\n\n" +
            "node B begin\nend\n\n" +
            "graph g begin\nend\n\n" +
            "state s1 begin\n" +
            "    graph ^\n" +
            "end", p.toCode()
        );
    }

    @Test
    public void graphNodeDefs(){
        p.add(new GraphDefinition("g"));
        p.add(new NodeDefinition("A"));
        p.add(new NodeDefinition("B"));

        assertEquals(
            "node A begin\nend\n\n" +
            "node B begin\nend\n\n" +
            "graph g begin\nend", p.toCode()
        );
    }

    @Test
    public void nodeStateDefs(){
        p.add(new NodeDefinition("A"));
        p.add(new NodeDefinition("B"));
        p.add(new StateDefinition("s1"));

        assertEquals(
            "node A begin\nend\n\n" +
            "node B begin\nend\n\n" +
            "state s1 begin\n" +
            "    graph ^\n" +
            "end", p.toCode()
        );
    }

    @Test
    public void graphStateDefs(){
        p.add(new GraphDefinition("g"));
        p.add(new StateDefinition("s1", "g"));

        assertEquals(
            "graph g begin\nend\n\n" +
            "state s1 begin\n" +
            "    graph g\n" +
            "end", p.toCode()
        );
    }
}