package org.shirolang.interpreter.ast;

import org.junit.Test;
import org.shirolang.fixtures.GraphDefinitionFixture;

import static org.junit.Assert.assertEquals;

public class GraphDefinitionTest {
    @Test
    public void getName(){
        GraphDefinition graph = GraphDefinitionFixture.emptyGraph();
        assertEquals("g", graph.getName());
    }

    @Test
    public void canOutputEmptyGraph(){
        GraphDefinition graph = GraphDefinitionFixture.emptyGraph();
        assertEquals("graph g begin\nend", graph.toCode());
    }

    @Test
    public void canOutputGraph(){
        GraphDefinition graph = GraphDefinitionFixture.withFunctionDeclarations();
        assertEquals(
            "graph g begin\n" +
            "    b Box[a](1, 2, 3)\n" +
            "end", graph.toCode()
        );
    }
}