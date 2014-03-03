package shiro;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import shiro.expressions.Path;

/**
 * This class represents a graph definition.
 * @author jeffreyguenther
 */
public class GraphDefinition {
    private Map<String, String> nodeProductions; // name -> type. Names are unique
    private Map<Path, PortAssignment> portAssignments;
    private String name;

    public GraphDefinition(String name) {
        nodeProductions = new HashMap<>();
        portAssignments = new HashMap<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getNodeProductions() {
        return nodeProductions;
    }

    public void setNodeProductions(Map<String, String> nodeProductions) {
        this.nodeProductions = nodeProductions;
    }

    public Set<PortAssignment> getPortAssignments() {
        return new LinkedHashSet<>(portAssignments.values());
    }

    public void setPortAssignments(Set<PortAssignment> portAssignments) {
        for(PortAssignment pa: portAssignments){
            this.portAssignments.put(pa.getPath(), pa);
        }
        
    }
    
    public void addPortAssignment(PortAssignment assign){
        //portAssignments.
        portAssignments.put(assign.getPath(), assign);
    }

    public boolean removePortAssignment(PortAssignment assign) {
        return portAssignments.remove(assign.getPath(), assign);
    }
    
    public void addNodeProduction(String type, String name){
        nodeProductions.put(name, type);
    }
    
    public void removeNodeProduction(String type, String name){
        nodeProductions.remove(name, type);
    }
    
    public String toCode(){
        StringBuilder sb = new StringBuilder();
        sb.append("graph ").append(getName()).append(" begin\n");
        nodeProductions.entrySet().stream().forEach((e) -> {
            sb.append("\t").append(e.getValue()).append(" -> ").append(e.getKey()).append("\n");
        });
        
        sb.append("\n");
        portAssignments.values().stream().forEach((PortAssignment a) -> { sb.append("\t").append(a.toCode()).append("\n"); });
        
        sb.append("end\n\n");
        return sb.toString();
    }
}
