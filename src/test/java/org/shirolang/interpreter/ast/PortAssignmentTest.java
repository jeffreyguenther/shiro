package org.shirolang.interpreter.ast;


import org.junit.Test;
import org.shirolang.fixtures.ast.PortAssignmentFixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PortAssignmentTest {
    @Test
    public void withPathAndListOfArgs(){
        PortAssignment assign = PortAssignmentFixture.withPathAndListOfArgs();
        assertEquals(
            "a.y(1, 2, 3)", assign.toCode()
        );
    }

    @Test
    public void withInputsOutputsAndListOfArgs(){
        PortAssignment assign = PortAssignmentFixture.withInputsOutputsAndListOfArgs();
        assertEquals(
                "outputs[a].inputs[y](1, 2, 3)", assign.toCode()
        );
    }

    @Test
    public void equals(){
        PortAssignment a1 = PortAssignmentFixture.withPathAndListOfArgs();
        PortAssignment a2 = PortAssignmentFixture.withPathAndListOfArgs();
        assertTrue(a1.equals(a2));
    }
}