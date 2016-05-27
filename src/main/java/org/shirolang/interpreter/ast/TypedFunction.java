package org.shirolang.interpreter.ast;

import java.util.List;
import java.util.Map;

/**
 * Adds a type field to the function
 */
public abstract class TypedFunction extends FunctionBase {
    protected String type;

    public TypedFunction(String option, String type) {
        super(option);
        this.type = type;
    }

    public TypedFunction(String option, Map<String, Expression> argMap, String type) {
        super(option, argMap);
        this.type = type;
    }

    public TypedFunction(String option, List<Expression> argList, String type) {
        super(option, argList);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
