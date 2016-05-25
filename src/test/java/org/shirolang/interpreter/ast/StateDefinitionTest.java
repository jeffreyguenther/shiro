package org.shirolang.interpreter.ast;

import org.junit.Test;
import org.shirolang.fixtures.StateDefinitionFixture;
import org.shirolang.interpreter.Defaults;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StateDefinitionTest {

    @Test
    public void getGraph(){
        StateDefinition stateDef = StateDefinitionFixture.defaultGraph("s1");
        assertEquals(Defaults.GRAPH_NAME, stateDef.getGraph());
    }

    @Test
    public void defaultConstructor(){
        StateDefinition stateDef = StateDefinitionFixture.defaultGraphAndState();
        assertEquals(Defaults.GRAPH_NAME, stateDef.getGraph());
        assertEquals(Defaults.STATE_NAME, stateDef.getName());
    }

    @Test
    public void canSetGraphAndName(){
        String graph = "g1";
        String state = "s2";
        StateDefinition stateDef = new StateDefinition(state, graph);

        assertEquals(graph, stateDef.getGraph());
        assertEquals(state, stateDef.getName());
        assertEquals(
            "state s2 begin\n" +
            "    graph g1\n" +
            "end", stateDef.toCode()
        );
    }

    @Test
    public void getName(){
        StateDefinition stateDef = StateDefinitionFixture.defaultGraph("s1");
        assertEquals("s1", stateDef.getName());
    }

    @Test
    public void canAddOptions(){
        StateDefinition stateDef = StateDefinitionFixture.singleOption();

        assertTrue(stateDef.hasOptions());
        assertEquals(
            "state s1 begin\n" +
            "    graph g1\n" +
            "    A[b]\n" +
            "end", stateDef.toCode()
        );
    }

    @Test
    public void canAddNestedOptions(){
        OptionSelection inner2 = new OptionSelection("D", "e");
        OptionSelection inner = new OptionSelection("B", "c", inner2);
        OptionSelection selection = new OptionSelection("A", "b", inner);

        StateDefinition stateDef = new StateDefinition("s1", "g1", selection);

        assertTrue(stateDef.hasOptions());
        assertEquals(
            "state s1 begin\n" +
            "    graph g1\n" +
            "    A[b] begin\n" +
            "        B[c] begin\n" +
            "            D[e]\n" +
            "        end\n" +
            "    end\n" +
            "end", stateDef.toCode()
        );
    }

    @Test
    public void canHaveMultipleOptionsAndNestedOptions(){
        StateDefinition stateDef = StateDefinitionFixture.nested();

        assertTrue(stateDef.hasOptions());
        assertEquals(
            "state s1 begin\n" +
            "    graph g1\n" +
            "    A[b] begin\n" +
            "        B[c] begin\n" +
            "            D[e]\n" +
            "        end\n" +
            "        H[i]\n" +
            "    end\n" +
            "    F[g]\n" +
            "end", stateDef.toCode()
        );
    }
}
