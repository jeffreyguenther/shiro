package shiro;

import java.util.*;
import shiro.expressions.Path;

/**
 * Definition of a subjunctive node // TODO - set default active node
 *
 * @author jeffreyguenther
 */
public class SubjunctiveNode implements Symbol, Container {

    private Scope parentScope;
    private String fullName;
    private Node activeNode;
    private Map<String, Node> subjuncts;

    public SubjunctiveNode(String name, Scope scope) {
        this.parentScope = scope;
        this.fullName = name;
        this.activeNode = null;
        this.subjuncts = new HashMap<String, Node>();
    }

    /**
     * Get the subjuncts
     * @return the subjuncts
     */
    public Set<Node> getSubjuncts(){
        return new HashSet<>(subjuncts.values());
    }
    
    /**
     * Determine if the subjunctive node contains the subjunct
     *
     * @param node node to be checked for
     * @return true/false if <code>node</code> is a subjunct
     */
    public boolean hasSubjunct(Node node) {
        return subjuncts.containsValue(node);
    }

    /**
     * Add a subjunct to the dataset
     *
     * @param node to be added as a subjunct
     */
    public void addSubjunct(Node node) {
        subjuncts.put(node.getName(), node);
    }

    /**
     * Add a subjunct and set the active node
     *
     * @param node subjunct to be added
     * @param activeNode node to be set as active
     * @throws SubjunctNotFoundException
     */
    public void addSubjunct(Node node, boolean active) throws SubjunctNotFoundException {
        addSubjunct(node);
        if (active) {
            setActiveNode(node);
        }
    }
    
    /**
     * Get subjunct node by name
     * @param name name of subjunct to get
     * @return a reference to the subjunctive node with the given name
     */
    public Node getSubjunct(String name){
        return subjuncts.get(name);
    }

    /**
     * Remove a subjunct from the node
     *
     * @param node subjunct to be removed
     * @throws SubjunctNotFoundException
     */
    public void removeSubjunct(Node node) throws SubjunctNotFoundException {
        subjuncts.remove(node.getFullName());

        // if the node is the selected node
        if (node.equals(activeNode)) {
            // set a new active subjunct to be the first element in the set
            // also know as the last added.
            setActiveNode(new ArrayList<Node>(subjuncts.values()).get(0));
        }
    }
    
    public Node getActiveSubjunct(){
        return activeNode;
    }

    /**
     * Get the name of the node
     *
     * @return name of the node
     */
    @Override
    public String getName() {
        return fullName;
    }

    /**
     * Set the name of the node
     *
     * @param name name of the node
     */
    public void setName(String name) {
        this.fullName = name;
    }

    /**
     * Set the active node
     *
     * @param node subjunct to be set active
     * @throws SubjunctNotFoundException
     */
    private void setActiveNode(Node node) throws SubjunctNotFoundException {
        // check if the node is a subjunct
        if (subjuncts.containsValue(node)) {
            // set the selected node to node passed in
            activeNode = node;
            // activate the selected node
            activeNode.activate();

            // deactivate all other nodes
            for (Node n : subjuncts.values()) {
                if (!activeNode.equals(n)) {
                    n.deactivate();
                }

            }
        } else {
            throw new SubjunctNotFoundException(node.getName() + "is not a subjunct of " + fullName);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFullName())
          .append(":")
          .append(getType())
          .append("\n");
        
        for(Node n: subjuncts.values()){
            sb.append("\t")
            .append(n.toString())
            .append("\n");
        }
        return sb.toString();
    }

    @Override
    public SymbolType getType() {
        return SymbolType.SUBJ;
    }

    @Override
    public void activate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void activate(Path key) throws PathNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void activate(String key) throws PathNotFoundException {
        try {
            Node activenNode = subjuncts.get(key);
            setActiveNode(activenNode);
        } catch (SubjunctNotFoundException ex) {
            throw new PathNotFoundException(key + " was not found.");
        }
    }

    @Override
    public void deactivate() {
        for(Node n: subjuncts.values()){
            n.deactivate();
        }
    }

    @Override
    public Symbol resolvePath(Path p) throws PathNotFoundException {

        if (p.getCurrentPathHead().equals("active")) {
            // move the path head to the right one element
            p.popPathHead();

            // resolve the path
            return activeNode.resolvePath(p);
        }else{
            p.popPathHead();
            
            Node containedNode = subjuncts.get(p.getCurrentPathHead());
            
            if(containedNode != null && !p.isAtEnd()){
                return containedNode.resolvePath(p);
            }else{
                return containedNode;
            }
        }
    }

    @Override
    public Symbol resolvePath(String path) throws PathNotFoundException {
        return resolvePath(PathHelpers.createPath(path));
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void addNestedContainer(Container c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Container> getNestedContainers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasNestedContainers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Path getPath() {
        List<String> pathParts = Arrays.asList(fullName.split("\\."));
        return new Path(parentScope, pathParts);
    }
}
