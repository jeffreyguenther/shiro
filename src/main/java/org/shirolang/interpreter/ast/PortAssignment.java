package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.List;
import java.util.Map;

/**
 * Define a port assignment
 */
public class PortAssignment extends FunctionBase implements Codeable{
    private String path;

    public PortAssignment(String path, String option) {
        super(option);
        this.path = path;
    }

    public PortAssignment(String path, String option, Map<String, Expression> argMap) {
        super(option, argMap);
        this.path = path;
    }

    public PortAssignment(String path, String option, List<Expression> argList) {
        super(option, argList);
        this.path = path;
    }

    public PortAssignment(String path) {
        this(path, "");
    }

    public PortAssignment(String path, Map<String, Expression> argMap) {
        this(path, "", argMap);

    }

    public PortAssignment(String path, List<Expression> argList) {
        this(path, "", argList);
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        templates.registerModelAdaptor(Codeable.class, new CodeableAdaptor());
        ST code = templates.getInstanceOf("portAssignment");
        code.add("p", this);
        return code.render();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PortAssignment that = (PortAssignment) o;

        return path.equals(that.path);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }
}
