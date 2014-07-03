/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.data;

import edu.uci.ics.jung.graph.UndirectedGraph;
import java.util.ArrayList;
import java.util.List;
import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 * Gets the neighbours of a vertex in a graph
 * 
 * @author jeffreyguenther
 */
public class NeighboursMFunc implements MultiFunction{
    private static final String NAME = "Neighbours";
    private static final int GRAPH = 0;
    private static final int VERTEX = 1;
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        // get the graph
        UndirectedGraph<Object, Object> graph = (UndirectedGraph<Object, Object>) arguments.get(GRAPH).getValue();
        
        // Get the columns used as vertices
        Object vertex = arguments.get(VERTEX).getValue();
        
        ArrayList<Object> neighbours = new ArrayList<>(graph.getNeighbors(vertex));
        
        // create resultuple
        // add resulting graph
        ResultTuple result = new ResultTuple(0, new Value(neighbours, ArrayList.class));
        
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
