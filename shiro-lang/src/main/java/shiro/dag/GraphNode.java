package shiro.dag;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Defines a graph node used in the generic graph node system.
 * @param <T> 
 * @author jeffreyguenther
 */
public class GraphNode<T> {
    private T value;            // object stored in the graph nodes
    private boolean isLeaf;     // leaf flag
    protected boolean updated;  // flag to indicate the status of the port
    protected boolean active;   // flag to indicate if port is active or not
    
    protected NodeAction<T> action;  // action to be done when visited
    protected Set<GraphNode<T>> nodesDependedOn;

    /**
     * Create a graph node
     * @param value value of the graph node
     * @param action action to executed at the node
     */
    public GraphNode(T value, NodeAction<T> action) {
        this.value = value;
        this.action = action;
        
        this.isLeaf = false;
        this.updated = false;
        this.active = true;
        this.nodesDependedOn = new LinkedHashSet<GraphNode<T>>();
    }

    /**
     * Create a graph node.
     * This creates a graph node without an action
     * @param value 
     */
    public GraphNode(T value) {
        this(value, null);
    }

    /**
     * Get the value of the graph node
     * @return the value of the graph node
     */
    public T getValue() {
        return value;
    }

    
    public void setValue(T value) {
        this.value = value;
    }
    
    /**
     * Do the action specified.
     * Commonly used to cause something to happen when a graph node is visited
     * during traversal, etc.
     */
    public void doAction(){
        action.doAction(value, nodesDependedOn);
        updated = true;
    }
    
    /**
     * Get the node's action.
     * @return 
     */
    public NodeAction getAction(){
        return action;
    }
    
    /**
     * Add a dependency on the graph node passed
     * @param node graph node to be depended upon
     * @return true if the dependency was successfully added, otherwise false
     */
    public boolean addDependencyOnNode(GraphNode<T> node){
        return nodesDependedOn.add(node);
    }

    /**
     * Remove the node's dependency on the node passed
     * @param node graph node to no longer be depended upon
     * @return true if the dependency was successfully remove, or didn't exist,
     * otherwise false
     */
    public boolean removeDependencyOnNode(GraphNode<T> node) {
        return nodesDependedOn.remove(node);
    }
    
    /**
     * Determine if node has an edge between it and node
     * @param node
     * @return whether there is a dependency between the node and the passed node
     */
    public boolean doesDependOn(GraphNode<T> node){
        return nodesDependedOn.contains(node);
    }

    /**
     * Check if the node has dependencies
     * @return boolean whether node has dependencies
     */
    public boolean hasDependencies(){
        return !nodesDependedOn.isEmpty();
    }

    /***
     * Get the graph nodes depended upon
     * @return set of graph nodes depended upon
     */
    public Set<GraphNode<T>> getNodesDependedOn() {
        return nodesDependedOn;
    }

    /***
     * Set the nodes depended upon
     * @param nodesDependedOn set of nodes to be depended upon
     */
    public void setNodesDependedOn(Set<GraphNode<T>> nodesDependedOn) {
        this.nodesDependedOn = nodesDependedOn;
    }
    
    /**
     * Determine of the port is active
     * @return true/false depending if the port is active or not
     */
    public boolean isActive() {
        return active;
    }
    
    /***
     * Activate the port
     */
    public void activate(){
        active = true;
    }
    
    /**
     * Deactivate the port
     */
    public void deactivate(){
        active = false;
    }
    
    /***
     * Gets whether a port has been updated
     * @return whether a port has been updated
     */
    public boolean isUpdated() {
        return updated;
    }
    
    /**
     * Set the port as leaf
     */
    public void setLeaf(){
       isLeaf = true;
    }
    
    /**
     * Unset the port as a leaf
     */
    public void unSetLeaf(){
        isLeaf = false;
    }

    /***
     * Determine if the port is a leaf
     * @return if the port is a leaf
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * Set the updated flag
     * @param update value of flag
     */
    public void setUpdated(boolean update) {
        this.updated = update;
    }

    @Override
    public String toString() {
        return "GraphNode{" + "value=" + value + ", isLeaf=" + isLeaf + ", updated=" + updated + ", active=" + active + ", action=" + action + ", nodesDependedOn=" + nodesDependedOn + '}';
    }
    
    
}
