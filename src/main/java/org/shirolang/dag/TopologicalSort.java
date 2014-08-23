package org.shirolang.dag;

import java.util.*;

/**
 * Topological sort of the graph based on a depth-first traversal
 * @param <T> Type of values stored in the graph node
 * @author jeffreyguenther
 */
public class TopologicalSort<T> {
    private final DAGraph<T> graph;
    private final List<GraphNode<T>> topoList;
    private final Map<GraphNode<T>, Boolean> visited;

    public TopologicalSort(DAGraph<T> graph) {
        this.graph = graph;
        this.topoList = new ArrayList<>();
        this.visited = new HashMap<>();
        
        // initialize the visited map to false;
        for(GraphNode<T> n: graph.getNodes()){
            visited.put(n, Boolean.FALSE);
        }
    }
    
    private void dfsVist(GraphNode<T> node, Map<GraphNode<T>, Boolean> visited){
        // check if the node has been visited
        if(!visited.get(node)){
            // mark the node as visited
            visited.put(node, Boolean.TRUE);
            
            Set<GraphNode<T>> dependedOnNodes = node.getNodesDependedOn();
            for(GraphNode<T> dp: dependedOnNodes){
                dfsVist(dp, visited);
            }
            
            // add the current node to the sorted list
            topoList.add(node);
        }
    }
    
    public List<GraphNode<T>> getTopologicalOrdering(){
        for(GraphNode<T> leaf: graph.getLeafNodes()){
            dfsVist(leaf, visited);
        }
        
        return topoList;
    }
}
