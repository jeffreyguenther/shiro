package shiro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jeffreyguenther
 */
public class GraphDefinition {
    private Map<String, String> nodeProductions;
    private Set<PortAssignment> portAssignments;
    private String name;

    public GraphDefinition(String name) {
        nodeProductions = new HashMap<>();
        portAssignments = new HashSet<>();
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
        return portAssignments;
    }

    public void setPortAssignments(Set<PortAssignment> portAssignments) {
        this.portAssignments = portAssignments;
    }
    
    public boolean addPortAssignment(PortAssignment assign){
        return portAssignments.add(assign);
    }

    public boolean removePortAssignment(PortAssignment assign) {
        return portAssignments.remove(assign);
    }
    
    public void addNodeProduction(String type, String name){
        nodeProductions.put(type, name);
    }
    
    public void removeNodeProduction(String type, String name){
        nodeProductions.remove(type, name);
    }
    
    public String toCode(){
        StringBuilder sb = new StringBuilder();
        sb.append("graph ").append(getName()).append(" begin\n");
        nodeProductions.entrySet().stream().forEach((e) -> {
            sb.append("\t").append(e.getKey()).append(" -> ").append(e.getValue()).append("\n");
        });
        
        portAssignments.stream().forEach((PortAssignment a) -> { sb.append("\t").append(a.toCode()).append("\n"); });
        
        sb.append("end\n\n");
        return sb.toString();
    }
}
