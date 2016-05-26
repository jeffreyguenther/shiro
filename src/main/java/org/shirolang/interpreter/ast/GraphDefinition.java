package org.shirolang.interpreter.ast;

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

    public GraphDefinition(String name) {
        this.name = name;
        functions = new ArrayList<>();
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

    public void addDefinition(FunctionDefinition def) {
        functions.add(def);
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        ST code = templates.getInstanceOf("graphDef");
        code.add("g", this);
        return code.render();
    }
}
