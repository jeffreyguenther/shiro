/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.data;

import com.google.common.collect.Table;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import java.util.List;
import java.util.Map;
import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 * Converts a table into a undirected graph.
 * 
 * @author jeffreyguenther
 */
public class UndirectedGraphMFunc implements MultiFunction{
    private static final String NAME = "UndirectedGraph";
    private static final int TABLE = 0;
    private static final int COLUMN_A = 1;
    private static final int COLUMN_B = 2;
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        // get the table
        Value tableValue = arguments.get(TABLE);
        Table<Integer, String, Comparable> table = (Table<Integer, String, Comparable>) tableValue.getValue();
        
        // Get the columns used as vertices
        String columnA = arguments.get(COLUMN_A).getValueAsString();
        String columnB = arguments.get(COLUMN_B).getValueAsString();
        
        // create instance of undirected graph
        UndirectedGraph<Object, Object> graph = new UndirectedSparseGraph<>();
        
        // for every row in the table,
        Map<Integer, Map<String, Comparable>> rows = table.rowMap();
        for(Integer i: rows.keySet()){
            Map<String, Comparable> row = rows.get(i);
            Comparable a = row.get(columnA);
            Comparable b = row.get(columnB);
            graph.addVertex(a);
            graph.addVertex(b);
            graph.addEdge(i, a, b);
        }
        
        // create resultuple
        // add resulting graph
        ResultTuple result = new ResultTuple(0, new Value(graph, UndirectedGraph.class));
        
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
