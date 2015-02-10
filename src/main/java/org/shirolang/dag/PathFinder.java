package org.shirolang.dag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Find the paths in a graph that can be used update the graph in parallel
 * @param <T> type of graph node
 * @author Ankit Gupta, Jeffrey Guenther
 */
public class PathFinder<T> {

    public PathFinder() {
    }
    
    public  List<List<GraphNode<T>>> getIndependentPathsToRoot(Set<GraphNode<T>> startAtPorts) {
        List<List<GraphNode<T>>> allIndependentPaths = new ArrayList<List<GraphNode<T>>>();
        for (GraphNode<T> n : startAtPorts) {
            allIndependentPaths.addAll(getIndependentPathsToRoot(n));
        }
        return allIndependentPaths;

    }

    public List<List<GraphNode<T>>> getIndependentPathsToRoot(GraphNode<T> startPort) {
        List<List<GraphNode<T>>> independentPaths = new ArrayList<List<GraphNode<T>>>();
        ArrayList<GraphNode<T>> temp = new ArrayList<GraphNode<T>>();
        gotoRoot(startPort, temp, independentPaths);
        //TODO: later use a data structure that supports adding an element to the beginning of the list
        for(List<GraphNode<T>> path : independentPaths){
            Collections.reverse(path);
        }
        return independentPaths;
    }

    
    //TODO: later use a data structure that supports adding an element to the beginning of the list
    private void gotoRoot(GraphNode<T> node, List<GraphNode<T>> result, List<List<GraphNode<T>>> finalResult) {
        Set<GraphNode<T>> dependentPorts = node.getNodesDependedOn();
        //add myself to the list before it gets passed to my dependent on nodes
        result.add(node);
        for (GraphNode<T> dp : dependentPorts) {
            //create a new copy of the ports to be passed along.
            ArrayList<GraphNode<T>> temp = new ArrayList<GraphNode<T>>();
            temp.addAll(result);
            //pass it on
            gotoRoot(dp, temp, finalResult);
        }
        if (dependentPorts.isEmpty()) {
            finalResult.add(result);
        }
    }

    public void printPath(List<GraphNode<T>> list) {
        for (GraphNode<T> p : list) {
            System.out.print(p.getValue() + " ");
        }
        System.out.println();
    }

    public void printPaths(List<List<GraphNode<T>>> lists) {
        for (List<GraphNode<T>> l : lists) {
            printPath(l);
        }
    }
}
