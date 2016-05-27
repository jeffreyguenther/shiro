package org.shirolang.interpreter.ast;

import org.junit.Test;
import org.shirolang.fixtures.GraphDefinitionFixture;
import org.shirolang.interpreter.Defaults;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GraphDefinitionTest {
    @Test
    public void getName(){
        GraphDefinition graph = GraphDefinitionFixture.emptyGraph();
        assertEquals("g", graph.getName());
    }

    @Test
    public void defaultGraph(){
        GraphDefinition graph = new GraphDefinition();
        assertEquals(Defaults.GRAPH_NAME, graph.getName());
        assertTrue(graph.isDefault());
    }

    @Test
    public void canOutputEmptyGraph(){
        GraphDefinition graph = GraphDefinitionFixture.emptyGraph();
        assertEquals("graph g begin\nend", graph.toCode());
    }

    @Test
    public void withFunctionDefinition(){
        GraphDefinition graph = GraphDefinitionFixture.withFunctionDeclarations();
        assertEquals(
            "graph g begin\n" +
            "    b Box[a](1, 2, 3)\n" +
            "end", graph.toCode()
        );
    }

    @Test
    public void withPortAssignmentandFunctionDefinition(){
        GraphDefinition graph = GraphDefinitionFixture.withFunctionDeclarationsAndPortAssignments();
        assertEquals(
            "graph g begin\n" +
            "    b Box[a](1, 2, 3)\n" +
            "    a.y(1, 2, 3)\n" +
            "end", graph.toCode()
        );
    }
}