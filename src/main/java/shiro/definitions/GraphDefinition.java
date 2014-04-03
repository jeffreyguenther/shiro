package shiro.definitions;

import java.util.ArrayList;
import java.util.List;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

/**
 * This class represents a graph definition.
 * It provides an abstract description of a graph declaration while at the same
 * time keeping a record of the order of the lines. Lines of the graph will
 * be stored in the order they are added.
 * @author jeffreyguenther
 */
public class GraphDefinition implements Definition{
    private List<Definition> lines;
    private List<Production> productions; 
    private List<PortAssignment> portAssignments;
    private String name;

    public GraphDefinition(String name) {
        lines = new ArrayList<>();
        productions = new ArrayList<>();
        portAssignments = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> nodeProductions) {
        this.productions = nodeProductions;
    }

    public List<PortAssignment> getPortAssignments() {
        return portAssignments;
    }

    public void setPortAssignments(List<PortAssignment> portAssignments) {
        this.portAssignments = portAssignments;
    }
    
    public boolean addPortAssignment(PortAssignment assign){
        lines.add(assign);
        return portAssignments.add(assign);
    }

    public boolean removePortAssignment(PortAssignment assign) {
        lines.remove(assign);
        return portAssignments.remove(assign);
    }
    
    public void addProduction(String type, String name){
        addProduction(type, name, null);
    }
    
    public void addProduction(String type, String name, String inlineComment){
        Production production = new Production(type, name, inlineComment);
        lines.add(production);
        productions.add(production);
    }
    
    public void removeProduction(String type, String name){
        Production p = new Production(type, name);
        lines.remove(p);
        productions.remove(p);
    }
    
    public List<Definition> getLines(){
        return lines;
    }
    
    public void addNewLine(){
        lines.add(new Newline());
    }
    
    public void addComment(Comment c){
        lines.add(c);
    }
    
    @Override
    public String toCode(){
        STGroup group = Definition.getTemplate();
        ST st = group.getInstanceOf("graphDecl");
        st.add("g", this);
        return st.render();
    }
}
