package org.shirolang.fixtures;

import org.shirolang.interpreter.ast.Expression;
import org.shirolang.interpreter.ast.FunctionCall;
import org.shirolang.interpreter.ast.Literal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Creates function calls for testing
 */
public class FunctionCallFixture {
    /**
     * a.y[b](1, 2, 3)
     * @return
     */
    public static FunctionCall withPathOptionAndListOfArgs(){
        return new FunctionCall("a.y", "b",
                Arrays.asList(new Literal<>(1), new Literal<>(2), new Literal<>(3)));
    }

    /**
     * x.y[b](a: 1, b: 10)
     * @return
     */
    public static FunctionCall withPathOptionAndKeywordargs(){
        Map<String, Expression> args = new HashMap<>();
        args.put("a", new Literal<>(1));
        args.put("b", new Literal<>(10));

        return new FunctionCall("x.y", "b", args);
    }

    /**
     * a.y(1, 2, 3)
     * @return
     */
    public static FunctionCall withPathAndListOfArgs(){
        return new FunctionCall("a.y",
                Arrays.asList(new Literal<>(1), new Literal<>(2), new Literal<>(3)));
    }

    /**
     * x.y(a: 1, b: 10)
     * @return
     */
    public static FunctionCall withPathAndKeywordargs(){
        Map<String, Expression> args = new HashMap<>();
        args.put("a", new Literal<>(1));
        args.put("b", new Literal<>(10));

        return new FunctionCall("x.y", args);
    }

    /**
     * a.x(a.b(10) / 0.1, 10)
     * @return
     */
    public static FunctionCall withFunctionCallArgs(){
        return new FunctionCall("a.x", Arrays.asList(ExpressionDefinitionFixture.withFunctionCall(), Literal.asInteger(10)));
    }
}
