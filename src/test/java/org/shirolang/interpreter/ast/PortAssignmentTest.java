package org.shirolang.interpreter.ast;


import org.junit.Test;
import org.shirolang.fixtures.ast.PortAssignmentFixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PortAssignmentTest {
    @Test
    public void create(){
        PortAssignment assign = PortAssignmentFixture.withPathAndListOfArgs();
        assertNotNull(assign);
    }

    @Test
    public void toCode(){
        PortAssignment assign = PortAssignmentFixture.withPathAndListOfArgs();
        assertEquals(
            "a.y(1, 2, 3)", assign.toCode()
        );
    }
}