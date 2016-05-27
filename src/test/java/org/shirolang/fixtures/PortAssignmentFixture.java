package org.shirolang.fixtures;

import org.shirolang.interpreter.ast.FunctionCall;
import org.shirolang.interpreter.ast.PortAssignment;

/**
 * Create port assignments
 */
public class PortAssignmentFixture {
    public static PortAssignment create(FunctionCall functionCall) {
        return new PortAssignment(functionCall);
    }
}
