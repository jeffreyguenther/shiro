package org.shirolang.interpreter.ast;

import org.junit.Test;
import org.shirolang.fixtures.ast.StateDefinitionFixture;
import org.shirolang.fixtures.interpreter.InterpreterFixture;
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
        StateDefinition stateDef = StateDefinitionFixture.withSingleOption();

        assertTrue(stateDef.hasOptions());
        assertEquals(
            "state s1 begin\n" +
            "    graph g1\n" +
            "    a[b]\n" +
            "end", stateDef.toCode()
        );
    }

    @Test
    public void canAddNestedOptions(){
        OptionSelection inner2 = new OptionSelection("d", "e");
        OptionSelection inner = new OptionSelection("b", "c", inner2);
        OptionSelection selection = new OptionSelection("a", "b", inner);

        StateDefinition stateDef = new StateDefinition("s1", "g1", selection);

        assertTrue(stateDef.hasOptions());
        assertEquals(
            "state s1 begin\n" +
            "    graph g1\n" +
            "    a[b] begin\n" +
            "        b[c] begin\n" +
            "            d[e]\n" +
            "        end\n" +
            "    end\n" +
            "end", stateDef.toCode()
        );
    }

    @Test
    public void canHaveMultipleOptionsAndNestedOptions(){
        StateDefinition stateDef = StateDefinitionFixture.nested();

        assertTrue(stateDef.hasOptions());
        assertEquals(InterpreterFixture.nestedStates(), stateDef.toCode());
    }

    @Test
    public void equals(){
        StateDefinition d1 = StateDefinitionFixture.nested();
        StateDefinition d2 = StateDefinitionFixture.nested();
        assertTrue(d1.equals(d2));
    }
}
