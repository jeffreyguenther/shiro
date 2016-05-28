package org.shirolang.fixtures;

import org.shirolang.interpreter.ast.Expression;
import org.shirolang.interpreter.ast.FunctionCall;
import org.shirolang.interpreter.ast.Literal;
import org.shirolang.interpreter.ast.PortAssignment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Creates function calls for testing
 */
public class PortAssignmentFixture {
    /**
     * a.y[b](1, 2, 3)
     */
    public static PortAssignment withPathOptionAndListOfArgs(){
        return new PortAssignment("a.y", "b",
                Arrays.asList(new Literal<>(1), new Literal<>(2), new Literal<>(3)));
    }

    /**
     * x.y[b](a: 1, b: 10)
     */
    public static PortAssignment withPathOptionAndKeywordargs(){
        Map<String, Expression> args = new HashMap<>();
        args.put("a", new Literal<>(1));
        args.put("b", new Literal<>(10));

        return new PortAssignment("x.y", "b", args);
    }

    /**
     * a.y(1, 2, 3)
     */
    public static PortAssignment withPathAndListOfArgs(){
        return new PortAssignment("a.y", Arrays.asList(new Literal<>(1), new Literal<>(2), new Literal<>(3)));
    }

    /**
     * x.y(a: 1, b: 10)
     */
    public static PortAssignment withPathAndKeywordargs(){
        Map<String, Expression> args = new HashMap<>();
        args.put("a", new Literal<>(1));
        args.put("b", new Literal<>(10));

        return new PortAssignment("x.y", args);
    }
}