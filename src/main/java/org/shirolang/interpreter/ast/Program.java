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
    private List<GraphDefinition> graphDefs;
    private List<NodeDefinition> nodeDefs;
    private List<StateDefinition> stateDefs;


    public Program() {
        graphDefs = new ArrayList<>();
        nodeDefs = new ArrayList<>();
        stateDefs = new ArrayList<>();
    }

    public boolean add(GraphDefinition g) {
        return graphDefs.add(g);
    }

    public List<GraphDefinition> getGraphDefs(){
        return graphDefs;
    }

    public boolean add(NodeDefinition n) {
        return nodeDefs.add(n);
    }

    public List<NodeDefinition> getNodeDefs() {
        return nodeDefs;
    }

    public List<StateDefinition> getStateDefs() {
        return stateDefs;
    }

    public boolean add(StateDefinition stateDefinition) {
        return stateDefs.add(stateDefinition);
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        ST code = templates.getInstanceOf("program");
        code.add("p", this);
        return code.render();
    }
}
