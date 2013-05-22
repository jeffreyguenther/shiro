package shiro.dag;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author jeffreyguenther
 */
public class TopologicalSortTest {

    private DAGraph<Integer> graph;

    public TopologicalSortTest() {
        graph = new DAGraph<Integer>();
    }

    /**
     * Test of getTopologicalOrdering method, of class TopologicalSort.
     */
    @Test
    public void testGetTopologicalOrdering() {
        GraphNode<Integer> n7 = new GraphNode<Integer>(7);
        GraphNode<Integer> n5 = new GraphNode<Integer>(5);
        GraphNode<Integer> n3 = new GraphNode<Integer>(3);

        GraphNode<Integer> n11 = new GraphNode<Integer>(11);
        GraphNode<Integer> n8 = new GraphNode<Integer>(8);

        GraphNode<Integer> n2 = new GraphNode<Integer>(2);
        GraphNode<Integer> n9 = new GraphNode<Integer>(9);
        GraphNode<Integer> n10 = new GraphNode<Integer>(10);

        graph.addDependency(n11, n7);
        graph.addDependency(n11, n5);

        graph.addDependency(n8, n7);
        graph.addDependency(n8, n3);

        graph.addDependency(n2, n11);
        graph.addDependency(n9, n11);
        graph.addDependency(n9, n8);
        graph.addDependency(n10, n11);

        List<GraphNode<Integer>> expectedOrder = new ArrayList<GraphNode<Integer>>();
        expectedOrder.add(n7);
        expectedOrder.add(n5);
        expectedOrder.add(n11);
        expectedOrder.add(n2);
        expectedOrder.add(n3);
        expectedOrder.add(n8);
        expectedOrder.add(n9);
        expectedOrder.add(n10);

        TopologicalSort<Integer> topoSort = new TopologicalSort<Integer>(graph);
        List<GraphNode<Integer>> topologicalOrdering = topoSort.getTopologicalOrdering();
        Assert.assertEquals(expectedOrder, topologicalOrdering);
    }
}
