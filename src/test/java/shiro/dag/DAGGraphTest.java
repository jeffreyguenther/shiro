package shiro.dag;

import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for DAG based on the graph from wikipedia's article on topological sorting
 * http://en.wikipedia.org/wiki/Topological_sort
 * @author jeffreyguenther
 */
public class DAGGraphTest {
    
    private DAGraph<Integer> graph;
    
    public DAGGraphTest() {
    }

    
    @Before
    public void runBeforeEveryTest(){
        graph = new DAGraph<Integer>();
    }
    
    /**
     * Test of addNode method, of class DAGraph.
     */
    @Test
    public void testAddNode() {
        Set<GraphNode<Integer>> expected = new HashSet<GraphNode<Integer>>();
        GraphNode<Integer> node = new GraphNode<Integer>(11);
        expected.add(node);
        
        graph.addNode(node);
        Set<GraphNode<Integer>> result = graph.getNodes();
        Assert.assertEquals(expected, result);
    }

    /**
     * Test of removeNode method, of class DAGraph.
     */
    @Test
    public void testRemoveNode() {
        Set<GraphNode<Integer>> nodes = new HashSet<GraphNode<Integer>>();
        
        GraphNode<Integer> n7 = new GraphNode<Integer>(7);
        nodes.add(n7);
        GraphNode<Integer> n5 = new GraphNode<Integer>(5);
        nodes.add(n5);
        GraphNode<Integer> n3 = new GraphNode<Integer>(3);
        nodes.add(n3);
        
        GraphNode<Integer> n11 = new GraphNode<Integer>(11);
        nodes.add(n11);
        GraphNode<Integer> n8 = new GraphNode<Integer>(8);
        nodes.add(n8);
        
        Set<GraphNode<Integer>> leaves = new HashSet<GraphNode<Integer>>();
        GraphNode<Integer> n2 = new GraphNode<Integer>(2);
        leaves.add(n2);
        GraphNode<Integer> n9 = new GraphNode<Integer>(9);
        leaves.add(n9);
        GraphNode<Integer> n10 = new GraphNode<Integer>(10);
        leaves.add(n10);
        
        // add all of the leaves
        nodes.addAll(leaves);
        
        graph.addDependency(n11, n7);
        graph.addDependency(n11, n5);
        
        graph.addDependency(n8, n7);
        graph.addDependency(n8, n3);
        
        graph.addDependency(n2, n11);
        graph.addDependency(n9, n11);
        graph.addDependency(n9, n8);
        graph.addDependency(n10, n11);
        
        // CASE remove leaf node
        // remove 2
        graph.removeNode(n2);
        // there should only be two leaf nodes left
        Assert.assertEquals(2, graph.getLeafNodes().size());
        // and only 7 nodes in total
        Assert.assertEquals(7, graph.getNodes().size());
        // remove 2 from the list of expected leaves
        nodes.remove(n2);
        leaves.remove(n2);
        Assert.assertEquals(leaves, graph.getLeafNodes());
        Assert.assertEquals(nodes, graph.getNodes());
        
        // CASE remove node that depends on nothing  or is an internal node
        graph.removeNode(n5);
        // there should only be six nodes left
        Assert.assertEquals(6, graph.getNodes().size());
        // remove 5 from the expected leaves list
        nodes.remove(n5);
        // only 7, 3, 11, 8, 9, 10 should be left
        Assert.assertEquals(nodes, graph.getNodes());
    }

    /**
     * Test of addDependency method, of class DAGraph.
     */
    @Test
    public void testAddDependency_GraphNode_GraphNode() {
        GraphNode<Integer> n7 = new GraphNode<Integer>(7);
        GraphNode<Integer> n11 = new GraphNode<Integer>(11);
        GraphNode<Integer> n8 = new GraphNode<Integer>(8);
        GraphNode<Integer> n3 = new GraphNode<Integer>(3);
        
        // set of expected leaves
        Set<GraphNode<Integer>> expectedLeaves = new HashSet<GraphNode<Integer>>();
        expectedLeaves.add(n11);
        expectedLeaves.add(n8);
        
        // set of expected nodes
        Set<GraphNode<Integer>> expectedNodes = new HashSet<GraphNode<Integer>>();
        expectedNodes.add(n7);
        expectedNodes.add(n11);
        expectedNodes.add(n8);
        expectedNodes.add(n3);
        
        // these should not cause a null pointer exception
        graph.addDependency(n8, n3);    // CASE two new nodes
        graph.addDependency(n7, null);  // CASE add new node depending on nothing
        graph.addDependency(n11, n7);   // CASE add new relation with nll being new
        graph.addDependency(n8, n7);    // CASE connecting two existing nodes
        
        Set<GraphNode<Integer>> leafNodes = graph.getLeafNodes();
        Set<GraphNode<Integer>> nodes = graph.getNodes();
        Assert.assertEquals(expectedNodes, nodes);
        Assert.assertEquals(expectedLeaves, leafNodes);
        
        // Check if the right dependencies are geing added
        // Start with n8
        Set<GraphNode<Integer>> n8ExpectedDeps = new HashSet<GraphNode<Integer>>();
        n8ExpectedDeps.add(n3);
        n8ExpectedDeps.add(n7);
        Set<GraphNode<Integer>> n8ActualDeps = n8.getNodesDependedOn();
        Assert.assertEquals(n8ExpectedDeps, n8ActualDeps);
        
        // n11
        Set<GraphNode<Integer>> n11ExpectedDeps = new HashSet<GraphNode<Integer>>();
        n11ExpectedDeps.add(n7);
        Set<GraphNode<Integer>> n11ActualDeps = n11.getNodesDependedOn();
        Assert.assertEquals(n11ExpectedDeps, n11ActualDeps);
        
        // n7 Ensure that n7 depends on nothing
        Set<GraphNode<Integer>> n7ActualDeps = n7.getNodesDependedOn();
        Assert.assertTrue(n7ActualDeps.isEmpty());
    }

    /**
     * Test of addDependency method, of class DAGraph.
     */
    @Test
    public void testAddDependency_DependencyRelation() {
        GraphNode<Integer> n7 = new GraphNode<Integer>(7);
        GraphNode<Integer> n11 = new GraphNode<Integer>(11);
        GraphNode<Integer> n8 = new GraphNode<Integer>(8);
        GraphNode<Integer> n3 = new GraphNode<Integer>(3);
        
        // set of expected leaves
        Set<GraphNode<Integer>> expectedLeaves = new HashSet<GraphNode<Integer>>();
        expectedLeaves.add(n11);
        expectedLeaves.add(n8);
        
        // set of expected nodes
        Set<GraphNode<Integer>> expectedNodes = new HashSet<GraphNode<Integer>>();
        expectedNodes.add(n7);
        expectedNodes.add(n11);
        expectedNodes.add(n8);
        expectedNodes.add(n3);
        
        // these should not cause a null pointer exception
        graph.addDependency( new DependencyRelation<GraphNode<Integer>>(n8, n3));    // CASE two new nodes
        graph.addDependency(new DependencyRelation<GraphNode<Integer>>(n7, null));  // CASE add new node depending on nothing
        graph.addDependency(new DependencyRelation<GraphNode<Integer>>(n11, n7));   // CASE add new relation with nll being new
        graph.addDependency(new DependencyRelation<GraphNode<Integer>>(n8, n7));    // CASE connecting two existing nodes
        
        Set<GraphNode<Integer>> leafNodes = graph.getLeafNodes();
        Set<GraphNode<Integer>> nodes = graph.getNodes();
        Assert.assertEquals(expectedNodes, nodes);
        Assert.assertEquals(expectedLeaves, leafNodes);
        
        // Check if the right dependencies are geing added
        // Start with n8
        Set<GraphNode<Integer>> n8ExpectedDeps = new HashSet<GraphNode<Integer>>();
        n8ExpectedDeps.add(n3);
        n8ExpectedDeps.add(n7);
        Set<GraphNode<Integer>> n8ActualDeps = n8.getNodesDependedOn();
        Assert.assertEquals(n8ExpectedDeps, n8ActualDeps);
        
        // n11
        Set<GraphNode<Integer>> n11ExpectedDeps = new HashSet<GraphNode<Integer>>();
        n11ExpectedDeps.add(n7);
        Set<GraphNode<Integer>> n11ActualDeps = n11.getNodesDependedOn();
        Assert.assertEquals(n11ExpectedDeps, n11ActualDeps);
        
        // n7 Ensure that n7 depends on nothing
        Set<GraphNode<Integer>> n7ActualDeps = n7.getNodesDependedOn();
        Assert.assertTrue(n7ActualDeps.isEmpty());
    }

    /**
     * Test of removeDependency method, of class DAGraph.
     */
    @Test
    public void testRemoveDependency_GraphNode_GraphNode() {
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
        
        
        // remove 2's dependency on 11
        // CASE cause a leaf node to become disconnected
        graph.removeDependency(n2, n11);
        // 2 should be made a leaf
        Assert.assertTrue(n2.isLeaf());
        // 2 should not have any dependencies
        Assert.assertTrue(!n2.hasDependencies());
        // graph should have 2 listed as a leaf
        Assert.assertTrue(graph.getLeafNodes().contains(n2));
        
        // remove 11's dependency on 5
        // CASE cause a depended on node to become disconnected
        graph.removeDependency(n11, n5);
        // 11 should not depend on 5
        Assert.assertTrue(!n11.getNodesDependedOn().contains(n5));
        // 5 should be a leaf
        Assert.assertTrue(graph.getLeafNodes().contains(n5));
        
        // remove 8's dependency on 3
        // CASE remove the dependency on an internal node
        graph.removeDependency(n8, n3);
        // 8 should not have 3 as a dependency
        Assert.assertTrue(!n8.getNodesDependedOn().contains(n3));
        
    }

    /**
     * Test of removeDependency method, of class DAGraph.
     */
    @Test
    public void testRemoveDependency_DependencyRelation() {
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
        
        
        // remove 2's dependency on 11
        // CASE cause a leaf node to become disconnected
        graph.removeDependency(new DependencyRelation<GraphNode<Integer>>(n2, n11));
        // 2 should be made a leaf
        Assert.assertTrue(n2.isLeaf());
        // 2 should not have any dependencies
        Assert.assertTrue(!n2.hasDependencies());
        // graph should have 2 listed as a leaf
        Assert.assertTrue(graph.getLeafNodes().contains(n2));
        
        // remove 11's dependency on 5
        // CASE cause a depended on node to become disconnected
        graph.removeDependency(new DependencyRelation<GraphNode<Integer>>(n11, n5));
        // 11 should not depend on 5
        Assert.assertTrue(!n11.getNodesDependedOn().contains(n5));
        // 5 should be a leaf
        Assert.assertTrue(graph.getLeafNodes().contains(n5));
        
        // remove 8's dependency on 3
        // CASE remove the dependency on an internal node
        graph.removeDependency(new DependencyRelation<GraphNode<Integer>>(n8, n3));
        // 8 should not have 3 as a dependency
        Assert.assertTrue(!n8.getNodesDependedOn().contains(n3));
    }

    /**
     * Test of getLeafNodes method, of class DAGraph.
     */
    @Test
    public void testGetLeafNodes() {
        Set<GraphNode<Integer>> leaves = new HashSet<GraphNode<Integer>>();
        GraphNode<Integer> n7 = new GraphNode<Integer>(7);
        GraphNode<Integer> n5 = new GraphNode<Integer>(5);
        GraphNode<Integer> n3 = new GraphNode<Integer>(3);
        
        GraphNode<Integer> n11 = new GraphNode<Integer>(11);
        GraphNode<Integer> n8 = new GraphNode<Integer>(8);
        
        GraphNode<Integer> n2 = new GraphNode<Integer>(2);
        leaves.add(n2);
        GraphNode<Integer> n9 = new GraphNode<Integer>(9);
        leaves.add(n9);
        GraphNode<Integer> n10 = new GraphNode<Integer>(10);
        leaves.add(n10);
        
        
        graph.addDependency(n11, n7);
        graph.addDependency(n11, n5);
        
        graph.addDependency(n8, n7);
        graph.addDependency(n8, n3);
        
        graph.addDependency(n2, n11);
        graph.addDependency(n9, n11);
        graph.addDependency(n9, n8);
        graph.addDependency(n10, n11);
        
        
        Assert.assertEquals(3, graph.getLeafNodes().size());
        Assert.assertEquals(leaves, graph.getLeafNodes());
    }

    /**
     * Test of getNodes method, of class DAGraph.
     */
    @Test
    public void testGetNodes() {
        Set<GraphNode<Integer>> nodes = new HashSet<GraphNode<Integer>>();
        
        GraphNode<Integer> n7 = new GraphNode<Integer>(7);
        nodes.add(n7);
        GraphNode<Integer> n5 = new GraphNode<Integer>(5);
        nodes.add(n5);
        GraphNode<Integer> n3 = new GraphNode<Integer>(3);
        nodes.add(n3);
        
        GraphNode<Integer> n11 = new GraphNode<Integer>(11);
        nodes.add(n11);
        GraphNode<Integer> n8 = new GraphNode<Integer>(8);
        nodes.add(n8);
        
        GraphNode<Integer> n2 = new GraphNode<Integer>(2);
        nodes.add(n2);
        GraphNode<Integer> n9 = new GraphNode<Integer>(9);
        nodes.add(n9);
        GraphNode<Integer> n10 = new GraphNode<Integer>(10);
        nodes.add(n10);
        
        
        graph.addDependency(n11, n7);
        graph.addDependency(n11, n5);
        
        graph.addDependency(n8, n7);
        graph.addDependency(n8, n3);
        
        graph.addDependency(n2, n11);
        graph.addDependency(n9, n11);
        graph.addDependency(n9, n8);
        graph.addDependency(n10, n11);
        
        Assert.assertEquals(8, graph.getNodes().size());
        Assert.assertEquals(nodes, graph.getNodes());
    }
}
