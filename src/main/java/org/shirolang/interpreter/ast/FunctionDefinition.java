package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.*;

/**
 * Defines a function definition in the Shiro
 */
public class FunctionDefinition extends TypedFunction {
    private String name;

    public FunctionDefinition(String type, String name) {
        this(type, name, "");
    }

    public FunctionDefinition(String type, String name, Map<String, Expression> argMap){
        this(type, name);
        this.argMap = argMap;
        argsType = ArgumentsType.KEYWORDS;
    }

    public FunctionDefinition(String type, String name, List<Expression> argList){
        this(type, name);
        this.argList = argList;
    }

    public FunctionDefinition(String type, String name, String option) {
        super(option, type);
        this.name = name;
    }

    public FunctionDefinition(String type, String name, String option, List<Expression> argList){
        this(type, name, option);
        this.argList = argList;
    }

    public FunctionDefinition(String type, String name, String option, Map<String, Expression> argMap){
        this(type, name, option);
        this.argMap = argMap;
        argsType = ArgumentsType.KEYWORDS;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        ST code = templates.getInstanceOf("funcDef");
        code.add("f", this);
        return code.render();
    }
}
