package org.shirolang.interpreter.ast;

import org.shirolang.interpreter.Defaults;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Defines a state definition in Shiro
 */
public class StateDefinition implements Codeable{
    private String name;
    private String graph;
    private List<OptionSelection> options;

    public StateDefinition(){
        this.name = Defaults.STATE_NAME;
        this.graph = Defaults.GRAPH_NAME;
        this.options = new ArrayList<>();
    }

    public StateDefinition(String name) {
        this.name = name;
        this.graph = Defaults.GRAPH_NAME;
        this.options = new ArrayList<>();
    }

    public StateDefinition(String name, String graph) {
        this.name = name;
        this.graph = graph;
        this.options = new ArrayList<>();
    }

    public StateDefinition(String name, String graph, OptionSelection... selections){
        this(name, graph);
        this.options = Arrays.asList(selections);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGraph() {
        return graph;
    }

    public List<OptionSelection> getOptions() {
        return options;
    }

    public boolean hasOptions() {
        return !options.isEmpty();
    }

    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        ST code = templates.getInstanceOf("stateDef");
        code.add("s", this);
        return code.render();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateDefinition that = (StateDefinition) o;

        if (!name.equals(that.name)) return false;
        if (!graph.equals(that.graph)) return false;
        return options.equals(that.options);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + graph.hashCode();
        result = 31 * result + options.hashCode();
        return result;
    }
}
