package org.shirolang.interpreter.ast;

import java.util.List;
import java.util.Map;

/**
 * Adds type and option fields to FunctionBase
 */
public abstract class TypedFunction extends FunctionBase {
    protected String type;
    protected String option;

    public TypedFunction(String option, String type) {
        super();
        this.type = type;
        this.option = option;
    }

    public TypedFunction(String option, Map<String, Expression> argMap, String type) {
        super(argMap);
        this.type = type;
        this.option = option;
    }

    public TypedFunction(String option, List<Expression> argList, String type) {
        super(argList);
        this.type = type;
        this.option = option;
    }

    public String getType() {
        return type;
    }

    public boolean hasActiveOption() {
        return !option.isEmpty();
    }

    public String getOption() {
        return option;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TypedFunction that = (TypedFunction) o;

        return type.equals(that.type);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
