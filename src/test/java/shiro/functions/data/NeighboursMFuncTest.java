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
public class NeighboursMFuncTest {
    @Test
    public void convert(){
        UndirectedGraph<Object, Object> graph = new UndirectedSparseGraph<>();
        graph.addEdge("AB", "A", "B");
        graph.addEdge("AC", "A", "C");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("BD", "B", "D");
        
        List<Value> arguments = new ArrayList<>();
        arguments.add(new Value(graph, UndirectedGraph.class));
        arguments.add(new Value("A", String.class));
        
        NeighboursMFunc mfunc = new NeighboursMFunc();
        ResultTuple result = mfunc.evaluate(arguments);
        
        ArrayList<Object> ns = (ArrayList<Object>) result.getValueForIndex(0).getValue();
        assertTrue(ns.contains("B"));
        assertTrue(ns.contains("C"));
        assertFalse(ns.contains("D"));
    }
}
