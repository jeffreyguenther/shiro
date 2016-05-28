package org.shirolang.fixtures;

import org.shirolang.interpreter.ast.Expression;
import org.shirolang.interpreter.ast.Literal;
import org.shirolang.interpreter.ast.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Creates function definitions for testing
 */
public class ReferenceFixture {
    /**
     * ~Box()
     */
    public static Reference withType(){
        return new Reference("Box");
    }

    /**
     * ~Box()[b]
     */
    public static Reference withTypeAndOutputSelector(){
        return new Reference("Box", "", "b");
    }

    /**
     * ~Box[a]()
     */
    public static Reference withNameTypeAndOption(){
        return new Reference("Box", "a");
    }

    /**
     * ~Box[b]()[a]
     */
    public static Reference withNameTypedOptionAndOutputSelector(){
        return new Reference("Box", "b", "a");
    }

    /**
     * ~Box(1, 2, 3)
     */
    public static Reference withTypeAndListOfArgs(){
        return new Reference("Box", Arrays.asList(new Literal<>(1), new Literal<>(2), new Literal<>(3)));
    }

    /**
     * ~Box(a: 1, b: 10)
     */
    public static Reference withTypeAndKeywordArgs(){
        Map<String, Expression> args = new HashMap<>();
        args.put("a", new Literal<>(1));
        args.put("b", new Literal<>(10));

        return new Reference("Box", args);
    }

    /**
     * ~Box[a](1, 2, 3)
     */
    public static Reference withTypeOptionAndListOfArgs(){
        return new Reference("Box", "a", Arrays.asList(new Literal<>(1), new Literal<>(2), new Literal<>(3)));
    }

    /**
     * ~Box[a](1, 2, 3)[b]
     */
    public static Reference withTypeOptionListOfArgsAndOutputSelector(){
        return new Reference("Box", "a", Arrays.asList(new Literal<>(1), new Literal<>(2), new Literal<>(3)), "b");
    }

    /**
     * ~Box[a](a: 1, b: 10)
     */
    public static Reference withTypeOptionAndKeywordArgs(){
        Map<String, Expression> args = new HashMap<>();
        args.put("a", new Literal<>(1));
        args.put("b", new Literal<>(10));

        return new Reference("Box", "a", args);
    }

    /**
     * ~Box[a](a: 1, b: 10)[b]
     */
    public static Reference withTypeOptionKeywordArgsAndOutputSelector(){
        Map<String, Expression> args = new HashMap<>();
        args.put("a", new Literal<>(1));
        args.put("b", new Literal<>(10));

        return new Reference("Box", "a", args, "b");
    }

    public static Reference doubleIt(){
        return new Reference("DoubleIt");
    }
}
