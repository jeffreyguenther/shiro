/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro;

import java.util.Map;
import shiro.dag.DAGraph;

/**
 * Defines a realized Shiro graph object
 * @author jeffreyguenther
 */
public class Graph {
    private DAGraph<Port> graph;
    private Map<String, Node> nodes;
    
}
