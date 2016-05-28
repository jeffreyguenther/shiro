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
    private List<Expression> anonExpressions;

    public GraphDefinition(String name) {
        this.name = name;
        functions = new ArrayList<>();
        assignments = new ArrayList<>();
        anonExpressions = new ArrayList<>();
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

    public List<Expression> getAnonymousExpressions() {
        return anonExpressions;
    }

    public void add(FunctionDefinition def) {
        functions.add(def);
    }

    public void add(PortAssignment portAssignment) {
        assignments.add(portAssignment);
    }

    public void add(Expression expression) {
        anonExpressions.add(expression);
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        templates.registerModelAdaptor(Codeable.class, new CodeableAdaptor());

        ST code;
        if(isDefault()) {
            code = templates.getInstanceOf("defaultGraph");
        }else{
            code = templates.getInstanceOf("graphDef");
        }

        code.add("g", this);
        return code.render();
    }
}
