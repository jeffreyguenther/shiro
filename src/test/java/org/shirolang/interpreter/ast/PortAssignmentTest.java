package org.shirolang.interpreter.ast;


import org.junit.Test;
import org.shirolang.fixtures.FunctionCallFixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PortAssignmentTest {
    @Test
    public void create(){
        PortAssignment assign = new PortAssignment(FunctionCallFixture.withPathAndListOfArgs());
        assertNotNull(assign);
    }

    @Test
    public void toCode(){
        PortAssignment assign = new PortAssignment(FunctionCallFixture.withPathAndListOfArgs());
        assertEquals(
            "a.y(1, 2, 3)", assign.toCode()
        );
    }
}