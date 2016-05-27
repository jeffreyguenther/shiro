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
     * Creates a function definition of type Box named b
     * without an active option
     * @return FunctionDefinition
     */
    public static FunctionDefinition withNameAndType(){
        return new FunctionDefinition("Box", "b");
    }

    /**
     * Creates a function definition of type Box named b
     * with an active option named a
     * @return FunctionDefinition
     */
    public static FunctionDefinition withNameTypeAndOption(){
        return new FunctionDefinition("Box", "b", "a");
    }

    /**
     * Creates a function definition of type Box named b
     * with the args [1, 2, 3]
     * @return FunctionDefinition
     */
    public static FunctionDefinition withNameTypeAndListOfArgs(){
        return new FunctionDefinition("Box", "b",
                Arrays.asList(new Literal<Integer>(1), new Literal<Integer>(2), new Literal<Integer>(3)));
    }

    /**
     * Creates a function definition of type Box named b with
     * keyword args {a: 1, b: 10}
     * @return FunctionDefinition
     */
    public static FunctionDefinition withNameTypeAndKeywordArgs(){
        Map<String, Expression> args = new HashMap<>();
        args.put("a", new Literal<Integer>(1));
        args.put("b", new Literal<Integer>(10));

        return new FunctionDefinition("Box", "b", args);
    }

    /**
     * Creates a function definition of type Box named b
     * with an active option named a with the args [1, 2, 3]
     *
     * b Box[a](1, 2, 3)
     * @return FunctionDefinition
     */
    public static FunctionDefinition withNameTypeOptionAndListOfArgs(){
        return new FunctionDefinition("Box", "b", "a",
                Arrays.asList(new Literal<Integer>(1), new Literal<Integer>(2), new Literal<Integer>(3)));
    }

    /**
     * Creates a function definition of type Box named b
     * with an active option named and the keyword args {a: 1, b: 10}
     * @return FunctionDefinition
     */
    public static FunctionDefinition withNameTypeOptionAndKeywordArgs(){
        Map<String, Expression> args = new HashMap<>();
        args.put("a", new Literal<Integer>(1));
        args.put("b", new Literal<Integer>(10));

        return new FunctionDefinition("Box", "b", "a", args);
    }
}
