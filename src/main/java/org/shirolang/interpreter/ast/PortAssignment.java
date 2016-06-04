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
    private Path path;

    public PortAssignment(Path path) {
        super();
        this.path = path;
    }

    public PortAssignment(Path path, Map<String, Expression> argMap) {
        super(argMap);
        this.path = path;
    }

    public PortAssignment(Path path, List<Expression> argList) {
        super(argList);
        this.path = path;
    }

    public Path getPath() {
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
