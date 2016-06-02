package org.shirolang.interpreter.ast;

import org.junit.Test;
import org.shirolang.fixtures.ast.GraphDefinitionFixture;
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
    public void withAnonymousExpressions(){
        GraphDefinition graph = GraphDefinitionFixture.withAnonymousExpressions();
        assertEquals(
            "graph g begin\n" +
            "    1.0 == (3 + 2)\n" +
            "end", graph.toCode()
        );
    }

    @Test
    public void withPortAssignments(){
        GraphDefinition graph = GraphDefinitionFixture.withPortAssignments();
        assertEquals(
            "graph g begin\n" +
            "    a.y(1, 2, 3)\n" +
            "end", graph.toCode()
        );
    }

    @Test
    public void withFunctionDefinitionAndPortAssignment(){
        GraphDefinition graph = GraphDefinitionFixture.withFunctionDeclarationsAndPortAssignments();
        assertEquals(
            "graph g begin\n" +
            "    b Box[a](1, 2, 3)\n" +
            "    a.y(1, 2, 3)\n" +
            "end", graph.toCode()
        );
    }

    @Test
    public void withFunctionDeclarationsAndAnonExpressions(){
        GraphDefinition graph = GraphDefinitionFixture.withFunctionDeclarationsAndAnonExpressions();
        assertEquals(
            "graph g begin\n" +
            "    b Box[a](1, 2, 3)\n" +
            "    1.0 == (3 + 2)\n" +
            "end", graph.toCode()
        );
    }

    @Test
    public void withPortAssignmentsAndAnonExpressions(){
        GraphDefinition graph = GraphDefinitionFixture.withPortAssignmentsAndAnonExpressions();
        assertEquals(
            "graph g begin\n" +
            "    a.y(1, 2, 3)\n" +
            "    1.0 == (3 + 2)\n" +
            "end", graph.toCode()
        );
    }

    @Test
    public void withFunctionDeclarationsPortAssignmentsAndAnonExpressions(){
        GraphDefinition graph = GraphDefinitionFixture.withFunctionDeclarationsPortAssignmentsAndAnonExpressions();
        assertEquals(
            "graph g begin\n" +
            "    b Box[a](1, 2, 3)\n" +
            "    a.y(1, 2, 3)\n" +
            "    1.0 == (3 + 2)\n" +
            "end", graph.toCode()
        );
    }

    @Test
    public void defaultGraphWithPortAssignment(){
        GraphDefinition graph = GraphDefinitionFixture.defaultGraphWithPortAssignment();
        assertEquals("a.y(1, 2, 3)", graph.toCode());
    }

    @Test
    public void defaultGraphWithFunctionDeclarationsAndPortAssignments(){
        GraphDefinition graph = GraphDefinitionFixture.defaultGraphWithFunctionDeclarationsAndPortAssignments();
        assertEquals(
            "b Box[a](1, 2, 3)\n" +
            "a.y(1, 2, 3)", graph.toCode()
        );
    }

    @Test
    public void defaultGraphWithFunctionDeclarationsAndAnonExpressions(){
        GraphDefinition graph = GraphDefinitionFixture.defaultGraphWithFunctionDeclarationsAndAnonExpressions();
        assertEquals(
            "b Box[a](1, 2, 3)\n" +
            "1.0 == (3 + 2)", graph.toCode()
        );
    }

    @Test
    public void defaultGraphWithPortAssignmentsAndAnonExpressions(){
        GraphDefinition graph = GraphDefinitionFixture.defaultGraphWithPortAssignmentsAndAnonExpressions();
        assertEquals(
            "a.y(1, 2, 3)\n" +
            "1.0 == (3 + 2)", graph.toCode()
        );
    }

    @Test
    public void defaultGraphWithFunctionDeclarationsPortAssignmentsAnonExpressions(){
        GraphDefinition graph = GraphDefinitionFixture.defaultGraphWithFunctionDeclarationsPortAssignmentsAnonExpressions();
        assertEquals(
            "b Box[a](1, 2, 3)\n" +
            "a.y(1, 2, 3)\n" +
            "1.0 == (3 + 2)", graph.toCode()
        );
    }

    @Test
    public void defaultGraphWithAnonymousExpressions(){
        GraphDefinition graph = GraphDefinitionFixture.defaultGraphWithAnonExpressions();
        assertEquals("1.0 == (3 + 2)", graph.toCode());
    }

    @Test
    public void equals(){
        GraphDefinition g1 = GraphDefinitionFixture.defaultGraphWithFunctionDeclarationsPortAssignmentsAnonExpressions();
        GraphDefinition g2 = GraphDefinitionFixture.defaultGraphWithFunctionDeclarationsPortAssignmentsAnonExpressions();
        assertTrue(g1.equals(g2));

        GraphDefinition g3 = GraphDefinitionFixture.withFunctionDeclarationsPortAssignmentsAndAnonExpressions();
        GraphDefinition g4 = GraphDefinitionFixture.withFunctionDeclarationsPortAssignmentsAndAnonExpressions();
        assertTrue(g3.equals(g4));
    }
}