/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.data;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import java.util.ArrayList;
import java.util.List;
import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 * Filters a graph to only contain edges with the vertices specifieds
 * 
 * @author jeffreyguenther
 */
@SuppressWarnings("unchecked")
public class VertexFilterMFunc implements MultiFunction{
    private static final String NAME = "VertexFilter";
    private static final int GRAPH = 0;
    private static final int VERTICES = 1;
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        // get the graph
        UndirectedGraph<Object, Object> graph = (UndirectedGraph<Object, Object>) arguments.get(GRAPH).getValue();
        
        // Get the columns used as vertices
        ArrayList<Object> vertices = (ArrayList<Object>) arguments.get(VERTICES).getValue();
        
        VertexFilter<Object, Object> filter = new VertexFilter<>(vertices);
        Graph<Object, Object> filteredGraph = filter.transform(graph);
        
        // create resultuple
        // add resulting graph
        ResultTuple result = new ResultTuple(0, new Value(filteredGraph, UndirectedGraph.class));
        
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
