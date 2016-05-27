package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Define a port assignment
 */
public class PortAssignment implements Codeable{
    private FunctionCall assignment;

    public PortAssignment(FunctionCall assignment) {
        this.assignment = assignment;
    }

    public FunctionCall getAssignment() {
        return assignment;
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        ST code = templates.getInstanceOf("portAssignment");
        code.add("p", this);
        return code.render();
    }
}
