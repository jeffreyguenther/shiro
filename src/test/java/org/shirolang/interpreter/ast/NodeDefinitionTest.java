package org.shirolang.interpreter.ast;

import org.junit.Before;
import org.junit.Test;
import org.shirolang.fixtures.NodeDefinitionFixture;
import org.shirolang.fixtures.PortAssignmentFixture;
import org.shirolang.fixtures.PortStatementFixture;

import static org.junit.Assert.*;

public class NodeDefinitionTest {
    private NodeDefinition nodeDef;

    @Before
    public void createInstance(){
        nodeDef = new NodeDefinition("A");
    }

    @Test
    public void getName(){
        assertEquals("A", nodeDef.getName());
    }

    @Test
    public void hasDefaultOption(){
        assertFalse(nodeDef.hasDefaultOption());

        NodeDefinition withOption = new NodeDefinition("A", "b");
        assertTrue(withOption.hasDefaultOption());
        assertEquals("b", withOption.getDefaultOption());
    }

    @Test
    public void emptyNode(){
        assertEquals("node A begin\nend", nodeDef.toCode());
    }

    @Test
    public void withDefaultOption(){
        nodeDef = new NodeDefinition("A", "boom");
        assertEquals("node A [boom] begin\nend", nodeDef.toCode());
    }

    @Test
    public void withInputs(){
        nodeDef.add(PortStatementFixture.input());
        nodeDef.add(PortStatementFixture.input());
        assertEquals(2, nodeDef.getInputs().size());
        assertEquals(
            "node A begin\n" +
            "    input b Box\n" +
            "    input b Box\n" +
            "end",
            nodeDef.toCode()
        );
    }

    @Test
    public void withOutputs(){
        nodeDef.add(PortStatementFixture.output());
        nodeDef.add(PortStatementFixture.output());
        assertEquals(2, nodeDef.getOutputs().size());
        assertEquals(
            "node A begin\n" +
            "    output b Box\n" +
            "    output b Box\n" +
            "end",
            nodeDef.toCode()
        );
    }

    @Test
    public void withInternal(){
        nodeDef.add(PortStatementFixture.internal());
        nodeDef.add(PortStatementFixture.internal());
        assertEquals(2, nodeDef.getInternal().size());
        assertEquals(
            "node A begin\n" +
            "    b Box\n" +
            "    b Box\n" +
            "end",
            nodeDef.toCode()
        );
    }

    @Test
    public void withInputsAndOutputs(){
        nodeDef.add(PortStatementFixture.input());
        nodeDef.add(PortStatementFixture.output());
        assertEquals(
            "node A begin\n" +
            "    input b Box\n" +
            "    output b Box\n" +
            "end",
            nodeDef.toCode()
        );
    }

    @Test
    public void withInputsAndInternals(){
        nodeDef.add(PortStatementFixture.input());
        nodeDef.add(PortStatementFixture.internal());
        assertEquals(
            "node A begin\n" +
            "    input b Box\n" +
            "    b Box\n" +
            "end",
            nodeDef.toCode()
        );
    }

    @Test
    public void withInputsInternalsAndOutputs(){
        nodeDef.add(PortStatementFixture.output());
        nodeDef.add(PortStatementFixture.input());
        nodeDef.add(PortStatementFixture.internal());
        assertEquals(
            "node A begin\n" +
            "    input b Box\n" +
            "    b Box\n" +
            "    output b Box\n" +
            "end",
            nodeDef.toCode()
        );
    }

    @Test
    public void withInputsInternalsOutputsAndAssignments(){
        nodeDef.add(PortAssignmentFixture.withPathAndListOfArgs());
        nodeDef.add(PortStatementFixture.output());
        nodeDef.add(PortStatementFixture.input());
        nodeDef.add(PortStatementFixture.internal());
        assertEquals(
            "node A begin\n" +
            "    input b Box\n" +
            "    b Box\n" +
            "    a.y(1, 2, 3)\n" +
            "    output b Box\n" +
            "end",
            nodeDef.toCode()
        );
    }

    @Test
    public void withInputsInternalsOutputsAssignmentsAndNestedNode(){
        nodeDef.add(NodeDefinitionFixture.withInputs());
        nodeDef.add(PortAssignmentFixture.withPathAndListOfArgs());
        nodeDef.add(PortStatementFixture.output());
        nodeDef.add(PortStatementFixture.input());
        nodeDef.add(PortStatementFixture.internal());
        assertEquals(
            "node A begin\n" +
            "    input b Box\n" +
            "    b Box\n" +
            "    a.y(1, 2, 3)\n" +
            "    output b Box\n\n" +
            "    node A begin\n" +
            "        input b Box\n" +
            "        input b Box\n" +
            "    end\n" +
            "end",
            nodeDef.toCode()
        );
    }

    @Test
    public void withInputsInternalsOutputsAssignmentsAndNestedNodes(){
        nodeDef.add(NodeDefinitionFixture.withInputs());
        nodeDef.add(NodeDefinitionFixture.withInputs());
        nodeDef.add(PortAssignmentFixture.withPathAndListOfArgs());
        nodeDef.add(PortStatementFixture.output());
        nodeDef.add(PortStatementFixture.input());
        nodeDef.add(PortStatementFixture.internal());
        assertEquals(
            "node A begin\n" +
            "    input b Box\n" +
            "    b Box\n" +
            "    a.y(1, 2, 3)\n" +
            "    output b Box\n\n" +
            "    node A begin\n" +
            "        input b Box\n" +
            "        input b Box\n" +
            "    end\n\n" +
            "    node A begin\n" +
            "        input b Box\n" +
            "        input b Box\n" +
            "    end\n" +
            "end",
            nodeDef.toCode()
        );
    }
}