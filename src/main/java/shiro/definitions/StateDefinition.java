package shiro.definitions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

/**
 * Definition of a system state.
 * @author jeffreyguenther
 */
public class StateDefinition implements Definition{
    private Instant timeStamp;
    private String comment;
    private Map<String, String> subjunctsMapping;
    private String name;
    private String graphDef;

    public StateDefinition(String gDef, String name, String comment, Map<String, String> subjunctsMapping) {
        this.graphDef = gDef;
        this.name = name;
        this.comment = comment;
        this.subjunctsMapping = subjunctsMapping;
        this.timeStamp = Instant.now();
    }
    
    public StateDefinition(String gDef, String name) {
        this(gDef, name, "", new HashMap<>());
    }

    public String getGraphDef() {
        return graphDef;
    }

    public void setGraphDef(String graphDef) {
        this.graphDef = graphDef;
    }
    
    public void addActiveNode(String node, String activeOption){
        subjunctsMapping.put(node, activeOption);
    }
    
    public void setActiveNodes(Map<String, String> table){
        subjunctsMapping.putAll(table);
    }

    public Map<String, String> getSubjunctsMapping() {
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
        for(String subNodes: subjunctsMapping.keySet()){
            String tableRow = String.format("%16s|%16s%n", subNodes, 
                    subjunctsMapping.get(subNodes));
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
