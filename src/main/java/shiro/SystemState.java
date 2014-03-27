package shiro;

import shiro.definitions.GraphDefinition;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Definition of a system state.
 * @author jeffreyguenther
 */
public class SystemState {
    private Instant timeStamp;
    private String comment;
    private Map<SubjunctiveNode, Node> subjunctsMapping;
    private String name;
    private GraphDefinition graphDef;

    public SystemState(GraphDefinition gDef, String name, String comment, Map<SubjunctiveNode, Node> subjunctsMapping) {
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
    
    public void setActiveNode(SubjunctiveNode sNode, Node activeNode){
        subjunctsMapping.put(sNode, activeNode);
    }
    
    public void setActiveNode(Map<SubjunctiveNode, Node> table){
        subjunctsMapping.putAll(table);
    }

    public Map<SubjunctiveNode, Node> getSubjunctsMapping() {
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
        for(SubjunctiveNode subNodes: subjunctsMapping.keySet()){
            String tableRow = String.format("%16s|%16s%n", subNodes.getName(), 
                    subjunctsMapping.get(subNodes).getName());
            sb.append(tableRow);
        }
        return sb.toString();
    }
    
    public String toCode(){
        StringBuilder sb = new StringBuilder();
        sb.append("state ").append(getName()).append(" begin\n");
        sb.append("\tGraph").append(" ").append(graphDef.getName()).append("\n");
        subjunctsMapping.forEach((SubjunctiveNode sn, Node n) -> {
            sb.append("\t").append(sn.getName()).append("[").append(n.getName()).append("]\n");
        });
        sb.append("end\n");
        return sb.toString();
    }
}
