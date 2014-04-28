package shiro.definitions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import shiro.Node;

/**
 * Definition of a system state.
 * @author jeffreyguenther
 */
public class SystemState implements Definition{
    private Instant timeStamp;
    private String comment;
    private Map<Node, Node> subjunctsMapping;
    private String name;
    private GraphDefinition graphDef;

    public SystemState(GraphDefinition gDef, String name, String comment, Map<Node, Node> subjunctsMapping) {
        this.graphDef = gDef;
        this.name = name;
        this.comment = comment;
        this.subjunctsMapping = subjunctsMapping;
        this.timeStamp = Instant.now();
    }
    
    public SystemState(GraphDefinition gDef, String name) {
        this(gDef, name, "", new HashMap<>());
    }

    public GraphDefinition getGraphDef() {
        return graphDef;
    }

    public void setGraphDef(GraphDefinition graphDef) {
        this.graphDef = graphDef;
    }
    
    public void setActiveNode(Node sNode, Node activeNode){
        subjunctsMapping.put(sNode, activeNode);
    }
    
    public void setActiveNode(Map<Node, Node> table){
        subjunctsMapping.putAll(table);
    }

    public Map<Node, Node> getSubjunctsMapping() {
        return subjunctsMapping;
    }

    public Instant getTimeCreated() {
        return timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // print the subjunct table
        for(Node subNodes: subjunctsMapping.keySet()){
            String tableRow = String.format("%16s|%16s%n", subNodes.getName(), 
                    subjunctsMapping.get(subNodes).getName());
            sb.append(tableRow);
        }
        return sb.toString();
    }
    
    @Override
    public String toCode(){
        STGroup template = Definition.getTemplate();
        ST state = template.getInstanceOf("state");
        state.add("s", this);
        return state.render();
    }
}
