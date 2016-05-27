package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Defines a port statement
 */
public class PortDefinition implements Codeable{
    private boolean isOption;
    private Access access;
    private FunctionDefinition function;

    public PortDefinition(boolean isOption, Access access, FunctionDefinition function) {
        this.isOption = isOption;
        this.access = access;
        this.function = function;
    }

    public PortDefinition(Access access, FunctionDefinition function) {
        this(false, access, function);
    }

    public boolean isOption() {
        return isOption;
    }

    public Access getAccess() {
        return access;
    }

    public boolean isInternal() {
        return access.isInternal();
    }

    public boolean isInput() {
        return access.isReadWrite();
    }

    public boolean isOutput() {
        return access.isRead();
    }

    public FunctionDefinition getFunction() {
        return function;
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        ST code = templates.getInstanceOf("portDef");
        code.add("p", this);
        return code.render();
    }
}
