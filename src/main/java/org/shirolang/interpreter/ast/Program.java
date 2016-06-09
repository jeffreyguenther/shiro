package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Describes an abstract representation of Shiro program
 */
public class Program implements Codeable{
    private List<IncludeStatement> includes;
    private Map<String, GraphDefinition> graphDefs;
    private GraphDefinition defaultGraph;
    private Map<String, NodeDefinition> nodeDefs;
    private Map<String, StateDefinition> stateDefs;


    public Program() {
        includes = new ArrayList<>();
        graphDefs = new HashMap<>();
        defaultGraph = null;
        nodeDefs = new HashMap<>();
        stateDefs = new HashMap<>();
    }

    public void add(GraphDefinition g) {
        if(g.isDefault()){
            defaultGraph = g;
        }else{
            graphDefs.put(g.getName(), g);
        }
    }

    public Map<String, GraphDefinition> getGraphDefsByName(){
        return graphDefs;
    }

    public List<GraphDefinition> getGraphDefs(){
        return new ArrayList<>(graphDefs.values());
    }

    public boolean hasDefaultGraph(){
        return defaultGraph != null;
    }

    public GraphDefinition getDefaultGraph() {
        return defaultGraph;
    }

    public void add(NodeDefinition n) {
        nodeDefs.put(n.getName(), n);
    }

    public Map<String, NodeDefinition> getNodeDefsByName() {
        return nodeDefs;
    }

    public List<NodeDefinition> getNodeDefs(){
        return new ArrayList<>(nodeDefs.values());
    }

    public void add(StateDefinition stateDefinition) {
        stateDefs.put(stateDefinition.getName(), stateDefinition);
    }

    public Map<String, StateDefinition> getStateDefsByName() {
        return stateDefs;
    }

    public List<StateDefinition> getStateDefs(){
        return new ArrayList<>(stateDefs.values());
    }

    public boolean add(IncludeStatement include) {
        return includes.add(include);
    }

    public List<IncludeStatement> getIncludes() {
        return includes;
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        templates.registerModelAdaptor(Codeable.class, new CodeableAdaptor());
        ST code = templates.getInstanceOf("program");
        code.add("p", this);
        return code.render();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Program program = (Program) o;

        if (!includes.equals(program.includes)) return false;
        if (!graphDefs.equals(program.graphDefs)) return false;
        if (defaultGraph != null ? !defaultGraph.equals(program.defaultGraph) : program.defaultGraph != null)
            return false;
        if (!nodeDefs.equals(program.nodeDefs)) return false;
        return stateDefs.equals(program.stateDefs);

    }

    @Override
    public int hashCode() {
        int result = includes.hashCode();
        result = 31 * result + graphDefs.hashCode();
        result = 31 * result + (defaultGraph != null ? defaultGraph.hashCode() : 0);
        result = 31 * result + nodeDefs.hashCode();
        result = 31 * result + stateDefs.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return toCode();
    }
}
