package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.List;
import java.util.Map;

/**
 * Describes a Reference AST node
 */
public class Reference extends TypedFunction implements Expression{
    private String outputSelector;

    public Reference(String type, String option, String outputSelector) {
        super(option, type);
        this.outputSelector = outputSelector;
    }

    public Reference(String type, String option, Map<String, Expression> argMap, String outputSelector) {
        super(option, argMap, type);
        this.outputSelector = outputSelector;
    }

    public Reference(String type, String option, List<Expression> argList, String outputSelector) {
        super(option, argList, type);
        this.outputSelector = outputSelector;
    }

    public Reference(String type, String option) {
        this(type, option, "");
    }

    public Reference(String type, String option, Map<String, Expression> argMap) {
        this(type, option, argMap, "");
    }

    public Reference(String type, String option, List<Expression> argList) {
        this(type, option, argList, "");
    }

    public Reference(String type) {
        this(type, "", "");
    }

    public Reference(String type, Map<String, Expression> argMap) {
        this(type, "", argMap);
    }

    public Reference(String type, List<Expression> argList) {
        this(type, "", argList);
    }

    public Reference(String type, Map<String, Expression> argMap, String outputSelector) {
        this(type, "", argMap, outputSelector);
    }

    public Reference(String type, List<Expression> argList, String outputSelector) {
        this(type, "", argList, outputSelector);
    }

    public String getSelector() {
        return outputSelector;
    }

    public boolean hasOutputSelector(){
        return !outputSelector.isEmpty();
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        templates.registerModelAdaptor(Codeable.class, new CodeableAdaptor());
        ST code = templates.getInstanceOf("anonReference");
        code.add("r", this);
        return code.render();
    }
}
