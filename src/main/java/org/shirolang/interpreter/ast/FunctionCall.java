package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.List;
import java.util.Map;

/**
 * Defines a function call
 */
public class FunctionCall extends Function{
    private String path;

    public FunctionCall(String path, Map<String, Expression> argMap) {
        super("", argMap);
        this.path = path;
    }

    public FunctionCall(String path, List<Expression> argList) {
        super("", argList);
        this.path = path;
    }

    public FunctionCall(String path, String option, List<Expression> argList) {
        super(option, argList);
        this.path = path;
    }

    public FunctionCall(String path, String option, Map<String, Expression> argMap) {
        super(option, argMap);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        ST code = templates.getInstanceOf("funcCall");
        code.add("f", this);
        return code.render();
    }
}
