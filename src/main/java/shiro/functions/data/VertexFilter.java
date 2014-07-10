/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.data;

import edu.uci.ics.jung.algorithms.filters.Filter;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import java.util.Collection;

/**
 *
 * @author jeffreyguenther
 * @param <V> type of vertex
 * @param <E> type of edge
 */
@SuppressWarnings("unchecked")
public class VertexFilter<V, E> implements Filter<V, E>{
    
    private Collection<V> vertsToKeep;

    public VertexFilter(Collection<V> vertsToKeep) {
        this.vertsToKeep = vertsToKeep;
    }
    
    @Override
    public Graph<V, E> transform(Graph<V, E> g) {
        Graph<V, E> filtered;
        
        try {
            filtered = g.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException("Unable to create copy of existing graph: ", ex);
        }
        
        for(E e: g.getEdges()){
            Pair<V> endpoints = g.getEndpoints(e);
            V first = endpoints.getFirst();
            V second = endpoints.getSecond();
            
            if(vertsToKeep.contains(first) || vertsToKeep.contains(second)){
                filtered.addEdge(e, first, second);
            }
        }
        
        return filtered;
    }
}
