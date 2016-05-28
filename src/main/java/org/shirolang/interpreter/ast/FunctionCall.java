package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.List;
import java.util.Map;

/**
 * Defines a function call
 */
public class FunctionCall extends TypedFunction implements Expression{

    public FunctionCall(String type, String option) {
        super(option, type);
    }

    public FunctionCall(String type, String option, Map<String, Expression> argMap) {
        super(option, argMap, type);
    }

    public FunctionCall(String type, String option, List<Expression> argList) {
        super(option, argList, type);
    }

    public FunctionCall(String type) {
        this(type, "");
    }

    public FunctionCall(String type, Map<String, Expression> argMap) {
        this(type, "", argMap);
    }

    public FunctionCall(String type, List<Expression> argList) {
        this(type, "", argList);
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        templates.registerModelAdaptor(Codeable.class, new CodeableAdaptor());
        ST code = templates.getInstanceOf("funcCall");
        code.add("f", this);
        return code.render();
    }
}
