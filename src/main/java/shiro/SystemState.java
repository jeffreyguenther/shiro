package shiro;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;

/**
 * Definition of a system state.
 * @author jeffreyguenther
 */
public class SystemState {
    private DateTime timeCreated;
    private String comment;
    private Map<SubjunctiveNode, Node> subjunctsMapping;
    private String name;

    public SystemState(String comment, Map<SubjunctiveNode, Node> subjunctsMapping) {
        this.comment = comment;
        this.subjunctsMapping = subjunctsMapping;
        this.timeCreated = new DateTime();
    }
    
    public SystemState(String name) {
        this.name = name;
        this.comment = "";
        this.subjunctsMapping = new HashMap<SubjunctiveNode, Node>();
        this.timeCreated = new DateTime();
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

    public DateTime getTimeCreated() {
        return timeCreated;
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
    
    
}
