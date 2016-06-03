package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes an abstract representation of Shiro program
 */
public class Program implements Codeable{
    private List<IncludeStatement> includes;
    private List<GraphDefinition> graphDefs;
    private GraphDefinition defaultGraph;
    private List<NodeDefinition> nodeDefs;
    private List<StateDefinition> stateDefs;


    public Program() {
        includes = new ArrayList<>();
        graphDefs = new ArrayList<>();
        defaultGraph = null;
        nodeDefs = new ArrayList<>();
        stateDefs = new ArrayList<>();
    }

    public boolean add(GraphDefinition g) {
        if(g.isDefault()){
            defaultGraph = g;
            return true;
        }else{
            return graphDefs.add(g);
        }
    }

    public List<GraphDefinition> getGraphDefs(){
        return graphDefs;
    }

    public boolean hasDefaultGraph(){
        return defaultGraph != null;
    }

    public GraphDefinition getDefaultGraph() {
        return defaultGraph;
    }

    public boolean add(NodeDefinition n) {
        return nodeDefs.add(n);
    }

    public List<NodeDefinition> getNodeDefs() {
        return nodeDefs;
    }

    public boolean add(StateDefinition stateDefinition) {
        return stateDefs.add(stateDefinition);
    }

    public List<StateDefinition> getStateDefs() {
        return stateDefs;
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
