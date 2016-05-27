package org.shirolang.fixtures;

import org.shirolang.interpreter.ast.Expression;
import org.shirolang.interpreter.ast.FunctionDefinition;
import org.shirolang.interpreter.ast.Literal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Creates function definitions for testing
 */
public class FunctionDefinitionFixture {
    /**
     * b Box
     */
    public static FunctionDefinition withNameAndType(){
        return new FunctionDefinition("Box", "b");
    }

    /**
     * b Box[a]
     */
    public static FunctionDefinition withNameTypeAndOption(){
        return new FunctionDefinition("Box", "b", "a");
    }

    /**
     * b Box(1, 2, 3)
     */
    public static FunctionDefinition withNameTypeAndListOfArgs(){
        return new FunctionDefinition("Box", "b",
                Arrays.asList(new Literal<>(1), new Literal<>(2), new Literal<>(3)));
    }

    /**
     * b Box(a: 1, b: 10)
     */
    public static FunctionDefinition withNameTypeAndKeywordArgs(){
        Map<String, Expression> args = new HashMap<>();
        args.put("a", new Literal<>(1));
        args.put("b", new Literal<>(10));

        return new FunctionDefinition("Box", "b", args);
    }

    /**
     * b Box[a](1, 2, 3)
     */
    public static FunctionDefinition withNameTypeOptionAndListOfArgs(){
        return new FunctionDefinition("Box", "b", "a",
                Arrays.asList(new Literal<>(1), new Literal<>(2), new Literal<>(3)));
    }

    /**
     * b Box[a](a: 1, b: 10)
     */
    public static FunctionDefinition withNameTypeOptionAndKeywordArgs(){
        Map<String, Expression> args = new HashMap<>();
        args.put("a", new Literal<>(1));
        args.put("b", new Literal<>(10));

        return new FunctionDefinition("Box", "b", "a", args);
    }
}
