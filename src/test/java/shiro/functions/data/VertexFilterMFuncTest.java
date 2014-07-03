/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.data;

import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import shiro.ResultTuple;
import shiro.Value;

/**
 *
 * @author jeffreyguenther
 */
public class VertexFilterMFuncTest {
    @Test
    public void convert(){
        UndirectedGraph<Object, Object> graph = new UndirectedSparseGraph<>();
        graph.addEdge("AB", "A", "B");
        graph.addEdge("AC", "A", "C");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CD", "C", "D");
        
        List<Object> verts = new ArrayList<>();
        verts.add("A");
        verts.add("B");
        
        List<Value> arguments = new ArrayList<>();
        arguments.add(new Value(graph, UndirectedGraph.class));
        arguments.add(new Value(verts, ArrayList.class));
        
        VertexFilterMFunc mfunc = new VertexFilterMFunc();
        ResultTuple result = mfunc.evaluate(arguments);
        
        UndirectedGraph<Object, Object> ns = (UndirectedGraph<Object, Object>) result.getValueForIndex(0).getValue();
        assertTrue("should contain A", ns.containsVertex("A"));
        assertTrue("should contain B", ns.containsVertex("B"));
        assertTrue("should contain C", ns.containsVertex("C"));
        assertTrue("should contain AB", ns.containsEdge("AB"));
        assertTrue("should contain AC", ns.containsEdge("AC"));
        assertTrue("should contain BC", ns.containsEdge("BC"));
        
        assertFalse("should not contain D", ns.containsVertex("D"));
        assertFalse("should not contain CD", ns.containsEdge("CD"));
    }
}
