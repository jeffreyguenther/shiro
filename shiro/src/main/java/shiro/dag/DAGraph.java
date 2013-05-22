package shiro.dag;

import java.util.*;

/**
 * Generic description of a directed acyclic graph. The graph is generic in the
 * sense that the vertices, or nodes in the graph are defined when the graph is
 * declared.
 *
 * @param <T> 
 * @author jeffreyguenther
 */
public class DAGraph<T> {

    private Set<GraphNode<T>> leafNodes;
    private Set<GraphNode<T>> nodes;

    /**
     * Create a directed acyclic graph
     */
    public DAGraph() {
        leafNodes = new LinkedHashSet<GraphNode<T>>();
        nodes = new LinkedHashSet<GraphNode<T>>();
    }

    /**
     * Get the leaf nodes of the graph
     * @return the set of leaf nodes in the graph
     */
    public Set<GraphNode<T>> getLeafNodes() {
        return leafNodes;
    }

    /**
     * Get all the nodes in the graph
     * @return the set of all nodes in the graph
     */
    public Set<GraphNode<T>> getNodes() {
        return nodes;
    }
    
    /**
     * Get the graph node for a given value
     * This is a helper when creating dependencies between
     * nodes that already exist in the graph. For dependencies to
     * be created as you expect, you must use the same graph node
     * references to create the dependences. If you create a new graph node
     * each time, you'll end up with a bunch of extra nodes in the graph.
     * @param value
     * @return a reference to the graph node containing the value, returns null 
     * if no graph node can be found.
     */
    public GraphNode<T> getNodeForValue(T value, NodeAction action) {
        GraphNode<T> matchedNode = null;
        for (GraphNode<T> n : nodes) {
            if (n.getValue().equals(value)) {
                matchedNode = n;
            }
        }

        // if the node has not been found create a new graph node
        if (matchedNode == null) {
            matchedNode = new GraphNode<T>(value, action);
        }
        return matchedNode;
    }
    
    /**
     * Add a node to the dependency graph without any relations.
     *
     * @param node to be added
     */
    public void addNode(GraphNode<T> node) {
        // add the node to the overall collection of nodes
        nodes.add(node);

        // mark the node as a leaf node
        node.setLeaf();
        leafNodes.add(node);
    }

    /**
     * Remove a node from the graph
     * @param nodeToRemove node to be removed
     */
    public void removeNode(GraphNode<T> nodeToRemove) {
        // CASE: Leaf node. No other nodes depend on nodeToRemove
        if(leafNodes.contains(nodeToRemove)){
            leafNodes.remove(nodeToRemove);
            nodes.remove(nodeToRemove);
        }else{ 
            // CASE: the node is internal.
            // Get the nodes that depend on nodeToRemove
            Set<GraphNode<T>> dependents = new HashSet<GraphNode<T>>();
            // create a map to determine if a node has been visited.
            Map<GraphNode<T>, Boolean> visited = new HashMap<GraphNode<T>, Boolean>();
            for(GraphNode<T> gn: nodes){
                visited.put(gn, Boolean.FALSE);
            }
            
            // Start with each leaf ports and look up the nodes that depend on nodeToRemove
            for(GraphNode<T> leaf: leafNodes){
                dependents.addAll(getDependents(leaf, nodeToRemove, visited));
            }
            
            // remove the reference to the node in its dependents
            for(GraphNode<T> dp: dependents){
                dp.removeDependencyOnNode(nodeToRemove);
                
                // handle the case where remove the node causes one of the nodes
                // that depended on it to become completely disconnected
                // Unncessary: the node will already be a leaf or will be depended on by some
                // other node that is a leaf
            } 
            
            // check if remove the node makes one of the nodes it depended on
            // completely disconnected
            for(GraphNode<T> dpOn: nodeToRemove.getNodesDependedOn()){
                if(!dpOn.hasDependencies()){
                    leafNodes.add(dpOn);
                    dpOn.setLeaf();
                }
            } 
            
            nodes.remove(nodeToRemove);
        }
    }
    
    /**
     * Get the nodes that depend on a node
     * @param n graph not to check
     * @param nodetoMatch node to match
     * @param visitedMap map of node references to booleans that indicates whether
     * a node has been visited
     * @return the set of nodes dependent on nodeToMatch
     */
    private Set<GraphNode<T>> getDependents(GraphNode<T> n, GraphNode<T> nodetoMatch, Map<GraphNode<T>, Boolean> visitedMap){
        // create a map of dependent nodes
        Set<GraphNode<T>> nodesDependentOnNodeToMatch = new LinkedHashSet<GraphNode<T>>();
        
        // if the node has not been visited
        if(!visitedMap.get(n)){
            // set the node as visited
            visitedMap.put(n, Boolean.TRUE);
            
            // get the nodes n depends on
            Set<GraphNode<T>> dependedOnNodes = n.getNodesDependedOn();
            
            // check each depended on port if it matches
            for(GraphNode<T> dn: dependedOnNodes){
                if(dn.equals(nodetoMatch)){
                   nodesDependentOnNodeToMatch.add(n);
                }else{
                    nodesDependentOnNodeToMatch.addAll(getDependents(dn, nodetoMatch, visitedMap));
                }
            }
        }
        return nodesDependentOnNodeToMatch;
    }
    
    
    /**
     * Add a dependency relation between two graph nodes. 
     * A depends on B
     * If either of the node are not already in the graph, the nodes are added, and the dependency is
     * added. It is possible to add a relation where a node depends on null. The
     * node will be added as a leaf.
     * @param dependentNode the A node in the relation
     * @param dependedOnNode  the B node in the relation
     */
    public void addDependency(GraphNode<T> dependentNode, GraphNode<T> dependedOnNode){
        // detect if the node depended on is null
        if (dependedOnNode != null && dependentNode != null) {

            // if the nodes are new
            if (!nodes.contains(dependentNode) && !nodes.contains(dependedOnNode)) {
                // add both nodes
                nodes.add(dependentNode);
                nodes.add(dependedOnNode);
                
                // add the dependent node as a leaf node
                dependentNode.setLeaf();
                leafNodes.add(dependentNode);
                
                // add the dependency. REMEMBER relations point TO what it depends on
                dependentNode.addDependencyOnNode(dependedOnNode);
            } else {

                // add the dependency. REMEMBER relations point TO what it depends on
                dependentNode.addDependencyOnNode(dependedOnNode);
                
                // if the dependent node is new
                if(!nodes.contains(dependentNode)){
                    // add the node as a leaf
                    addNode(dependentNode);
                }
                
                // if the depended on node is new
                if(!nodes.contains(dependedOnNode)){
                    nodes.add(dependedOnNode);
                }

                // if the node being depended on is a leaf, remove it from the leaves
                if (leafNodes.contains(dependedOnNode)) {
                    leafNodes.remove(dependedOnNode);
                    dependedOnNode.unSetLeaf();
                }
            }

        } else { // if there is no node to be depended on, add the graph node
            // but only add if the node does not already exist.
            if (dependedOnNode == null && dependentNode != null && !nodes.contains(dependentNode)) {
                addNode(dependentNode);
            } else {
                throw new NullPointerException("Both the dependent and the depended on"
                        + " values are null. Relation cannot be added.");
            }
        }
    }

    /**
     * Add a dependency relation between two graph nodes. 
     * A depends on B
     * If either of the node are not already in the graph, the nodes are added, and the dependency is
     * added. It is possible to add a relation where a node depends on null. The
     * node will be added as a leaf.
     *
     * @param relation the dependency relation
     */
    public void addDependency(DependencyRelation<GraphNode<T>> relation) {
        GraphNode<T> dependentNode = relation.getDependent();
        GraphNode<T> dependedOnNode = relation.getDependedOn();
        addDependency(dependentNode, dependedOnNode);
    }
    
    /**
     * Remove a dependency relation from the graph.
     * Remove A depends on B.
     * @param dependentNode the A node in the relation.
     * @param dependedOnNode the B node in the relation.
     */
    public void removeDependency(GraphNode<T> dependentNode, GraphNode<T> dependedOnNode){
        // add the dependency. REMEMBER relations point TO what it depends on
        dependentNode.removeDependencyOnNode(dependedOnNode);

        // if the dependent node is no longer attached, make it a leaf node
        handleGraphIndependence(dependentNode);
        handleGraphIndependence(dependedOnNode);
    }
    
    /**
     * Remove a dependency relation from the graph.
     * Remove A depends on B.
     * @param relation relation to be removed
     */
    public void removeDependency(DependencyRelation<GraphNode<T>> relation) {
        // get the nodes from the dependency relation
        GraphNode<T> dependentNode = relation.getDependent();
        GraphNode<T> dependedOnNode = relation.getDependedOn();
        
        // remove the nodes from the graph
        removeDependency(dependentNode, dependedOnNode);
    }
    
    /**
     * If a node has become disconnected, make it a leaf.
     * @param node node to check for graph independence
     */
    private void handleGraphIndependence(GraphNode<T> node){
        if (!node.hasDependencies()) {
            node.setLeaf();
            leafNodes.add(node);
        }
    }
}
