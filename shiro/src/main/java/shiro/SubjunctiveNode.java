package shiro;

import java.util.*;
import shiro.expressions.Path;

/**
 * Definition of a subjunctive node
 * @author jeffreyguenther
 */
public class SubjunctiveNode implements Symbol, Container {
    private String fullName;
    private Node selectedNode;
    private Set<Node> subjuncts;
    private Map<Node, Set<Port>> boundaryPorts;

    public SubjunctiveNode(String name) {
        this.fullName = name;
        selectedNode = null;
        subjuncts = new HashSet<Node>();
        boundaryPorts = new HashMap<Node, Set<Port>>();
    }
    
    /**
     * Add a boundary port for a subjunct
     * @param subjunct subjunct
     * @param p boundary port
     */
    public void addBoundaryPort(Node subjunct, Port p){
        Set<Port> ports = null;
        if(boundaryPorts.containsKey(subjunct)){
             ports = boundaryPorts.get(subjunct);
             ports.add(p);
        }else{
            ports = new HashSet<Port>();
            ports.add(p);
            boundaryPorts.put(subjunct, ports);
        }
        
    }
    
    /**
     * Remove a boundary port for a subjunct node
     * @param subjunct the subjunct whose boundaries to remove
     */
    private void removeBoundaryPortsForNode(Node subjunct){
        // removes all of the boundary ports for a given node
        boundaryPorts.remove(subjunct);
    }
    
    /**
     * Remove a boundary port for a node
     * @param n node whose boundary port should be removed
     * @param p boundary port to be removed
     */
    public void removeBoundaryPort(Node n, Port p){
        Set<Port> ports = boundaryPorts.get(n);
        ports.remove(p);
    }
            
    /**
     * Determine if the subjunct node hasSubjunct the node as a subjunct
     * @param node node to be checked for
     * @return true/false if <code>node</code> is a subjunct
     */
    public boolean hasSubjunct(Node node){
        return subjuncts.contains(node);
    }

    /**
     * Add a subjunct to the dataset
     * @param node to be added as a subjunct
     */
    public void addSubjunct(Node node){
        subjuncts.add(node);
    }
    
    /**
     * Add a subjunct and set the active node
     * @param node subjunct to be added
     * @param activeNode node to be set as active
     * @throws SubjunctNotFoundException 
     */
    public void addSubjunct(Node node, Node activeNode) throws SubjunctNotFoundException{
        subjuncts.add(node);
        setActiveNode(activeNode);
    }
    
    /**
     * Remove a subjunct from the node
     * @param node subjunct to be removed
     * @throws SubjunctNotFoundException 
     */
    public void removeSubjunct(Node node) throws SubjunctNotFoundException{
        subjuncts.remove(node);
        removeBoundaryPortsForNode(node);
        
        // if the node is the selected node
        if(node.equals(selectedNode)){
            // set a new active subjunct to be the first element in the set
            // also know as the last added.
            setActiveNode(new ArrayList<Node>(subjuncts).get(0));
        }
    }
    
    /**
     * Get the name of the node
     * @return name of the node
     */
    @Override
    public String getName() {
        return fullName;
    }

    /**
     * Set the name of the node
     * @param name name of the node
     */
    public void setName(String name) {
        this.fullName = name;
    }
    
    /**
     * Get the ports for the subjunctive node based on the selected node
     * @return set of boundary ports for the selected node
     */
    public Set<Port> getPorts(){
        return boundaryPorts.get(selectedNode);
    }
    
    /**
     * Set the active node
     * @param node subjunct to be set active
     * @throws SubjunctNotFoundException 
     */
    public void setActiveNode(Node node) throws SubjunctNotFoundException {
        // check if the node is a subjunct
        if (subjuncts.contains(node)) {
            // set the selected node to node passed in
            selectedNode = node;
            // activate the selected node
            selectedNode.activate();

            // deactivate all other nodes
            for (Node n : subjuncts) {
                if (!selectedNode.equals(n)) {
                    n.deactivate();

                    // deactive the boundary ports for the deselected node
                    for (Port p : boundaryPorts.get(n)) {
                        p.deactivate();
                    }
                }

                // activate the boundary ports for the subjunct
                for (Port p : boundaryPorts.get(n)) {
                    p.activate();
                }

            }
        } else {
            throw new SubjunctNotFoundException(node.getName() + "is not a subjunct of " + fullName);
        }
    }

    @Override
    public String toString() {
        return subjuncts.toString();
    }

    @Override
    public SymbolType getType() {
        return SymbolType.SubjNode;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deactivate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Symbol resolvePath(Path p) throws PathNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
