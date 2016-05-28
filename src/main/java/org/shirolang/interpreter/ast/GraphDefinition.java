package org.shirolang.interpreter.ast;

import org.shirolang.interpreter.Defaults;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.ArrayList;
import java.util.List;

/**
 *  Describes a graph definition
 */
public class GraphDefinition implements Codeable{
    private String name;
    private List<FunctionDefinition> functions;
    private List<PortAssignment> assignments;

    public GraphDefinition(String name) {
        this.name = name;
        functions = new ArrayList<>();
        assignments = new ArrayList<>();
    }

    public GraphDefinition() {
        this(Defaults.GRAPH_NAME);
    }

    public boolean isDefault(){
        return this.name.equals(Defaults.GRAPH_NAME);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FunctionDefinition> getFunctions() {
        return functions;
    }

    public List<PortAssignment> getAssignments() {
        return assignments;
    }

    public void add(FunctionDefinition def) {
        functions.add(def);
    }

    public void add(PortAssignment portAssignment) {
        assignments.add(portAssignment);
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        templates.registerModelAdaptor(Codeable.class, new CodeableAdaptor());
        ST code = templates.getInstanceOf("graphDef");
        code.add("g", this);
        return code.render();
    }
}
