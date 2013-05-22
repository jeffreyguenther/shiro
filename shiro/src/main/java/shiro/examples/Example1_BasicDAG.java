package shiro.examples;

import java.util.List;
import shiro.dag.DAGraph;
import shiro.dag.GraphNode;
import shiro.dag.TopologicalSort;

/**
 * A first iteration of a dependency graph on our way to building a subjunctive
 * dependency graph. This example graph is from <a
 * href="http://en.wikipedia.org/wiki/Topological_sorting"> Wikipedia's
 * Topological Sort</a> entry.
 *
 * @author jeffreyguenther
 */
public class Example1_BasicDAG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Build a graph
        DAGraph<Integer> graph = new DAGraph<Integer>();
        // Define an action
        IntegerAddAction action = new IntegerAddAction();
        
        // define nodes for the graph
        GraphNode<Integer> n7 = new GraphNode<Integer>(7, action);
        GraphNode<Integer> n5 = new GraphNode<Integer>(5, action);
        GraphNode<Integer> n3 = new GraphNode<Integer>(3, action);
        
        GraphNode<Integer> n11 = new GraphNode<Integer>(11, action);
        GraphNode<Integer> n8 = new GraphNode<Integer>(8, action);
        
        GraphNode<Integer> n2 = new GraphNode<Integer>(2, action);
        GraphNode<Integer> n9 = new GraphNode<Integer>(9, action);
        GraphNode<Integer> n10 = new GraphNode<Integer>(10, action);
        
        // Add nodes to the graph
        graph.addDependency(n11, n7);
        graph.addDependency(n11, n5);
        
        graph.addDependency(n8, n7);
        graph.addDependency(n8, n3);
        
        graph.addDependency(n2, n11);
        graph.addDependency(n9, n11);
        graph.addDependency(n9, n8);
        graph.addDependency(n10, n11);
        graph.addDependency(n10, n3);
        
        // Sort the graph
        TopologicalSort<Integer> sort = new TopologicalSort<Integer>(graph);
        List<GraphNode<Integer>> list = sort.getTopologicalOrdering();
        // Calculate the graph
        for(GraphNode<Integer> gn: list){
            System.out.print(gn.getValue());
            gn.doAction();
            IntegerAddAction result = (IntegerAddAction) gn.getAction();
            System.out.println(": " + result.getResult().get(gn.getValue()));
        }
        
        
    }
}
