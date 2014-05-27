/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toSet;
import shiro.dag.DAGraph;
import shiro.dag.DependencyRelation;
import shiro.dag.GraphNode;
import shiro.dag.TopologicalSort;
import shiro.exceptions.PathNotAccessibleException;
import shiro.exceptions.PathNotFoundException;
import shiro.expressions.Expression;
import shiro.expressions.Path;

/**
 * Defines a realized Shiro graph object
 *
 * @author jeffreyguenther
 */
public class Graph implements Scope {

    private static final PortAction graphNodeAction = new PortAction();
    private ShiroRuntime runtime;
    
    private String name;
    private DAGraph<Port> graph;
    private Map<String, Node> nodes;

    public Graph(ShiroRuntime rt) {
        graph = new DAGraph<>();
        nodes = new HashMap<>();
        runtime = rt;
    }

    public void clear() {
        graph.removeAllDependencies();
        nodes.clear();
    }

    @Override
    public String getFullName() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Path getPath() {
        return Path.createPath(name);
    }

    @Override
    public boolean isRoot() {
        return true;
    }

    public Set<Node> getNodesOfType(String type) {
        return nodes.values().stream().filter(n -> n.getType().equals(type)).collect(toSet());
    }

    public void addNode(Node n) {
        nodes.put(n.getFullName(), n);
    }

    public Set<Node> getNodes() {
        return new HashSet<>(nodes.values());
    }
    
    public Set<Node> getNodesWithOptions(){
        return nodes.values().stream().filter((Node n) -> n.hasOptions()).collect(toSet());
    }
    

    public void evaluate(Map<String, String> subjunctTable) {
        graph.removeAllDependencies();

        for (String nodeName : subjunctTable.keySet()) {
            Node n = nodes.get(nodeName);
            n.setActiveOption(subjunctTable.get(nodeName));
        }

        List<DependencyRelation<Port>> deps = new ArrayList<>();

        for (Node n : getNodes()) {
            // get all of the dependencies for each node
            deps.addAll(n.getDependencies());
        }

        for (DependencyRelation<Port> d : deps) {
            addDependency(d);
        }

        TopologicalSort<Port> sorter = new TopologicalSort<>(graph);
        List<GraphNode<Port>> topologicalOrdering = sorter.getTopologicalOrdering();

        // loop through all ports to update them.
        for (GraphNode<Port> gn : topologicalOrdering) {
            gn.doAction();
        }
    }

    /**
     * Add a dependency between two ports
     *
     * @param graph
     * @param dependency dependency relation to be added to the graph
     */
    private void addDependency(DependencyRelation<Port> dependency) {
        addDependency(dependency.getDependent(), dependency.getDependedOn());
    }

    /**
     * *
     * Add a dependency between two ports A - depends on -> B
     *
     * @param graph
     * @param a the depended on port reference
     * @param b the dependent port reference
     */
    private void addDependency(Port a, Port b) {

        if (b == null) {
            graph.addDependency(new GraphNode<>(a, graphNodeAction), null);
        } else {
            graph.addDependency(graph.getNodeForValue(a, graphNodeAction),
                    graph.getNodeForValue(b, graphNodeAction));
        }
    }
    
    @Override
    public Symbol find(Path p) throws PathNotFoundException, PathNotAccessibleException {
        Symbol matchedSymbol = null;
        
        // check proto instances
        // if the path matches a node type
        if (p.isAtEnd() && runtime.getNodeDefs().containsKey(p.getCurrentPathHead())) {
            if (nodes.containsKey(p.getCurrentPathHead())) {
                matchedSymbol = nodes.get(p.getCurrentPathHead());
            } else {
                matchedSymbol = runtime.produceNodeWithName(this,p.getCurrentPathHead(), p.getCurrentPathHead());
                addNode((Node)matchedSymbol);
            }
        } else if (nodes.containsKey(p.getCurrentPathHead())) {
            Node n = nodes.get(p.getCurrentPathHead());
            
            if (!p.isAtEnd()) {
                p.popPathHead();
                matchedSymbol = n.find(p);
            } else {
                matchedSymbol = n;
            }
        } else {
            throw new PathNotFoundException(p + " cannot be found.");
        }
        
        return matchedSymbol;
    }
    
    @Override
    public Symbol find(String s) throws PathNotFoundException, PathNotAccessibleException {
        return find(Path.createPath(s));
    }
    
    public List<Port> findAll(Path p) {
        List<Port> found = new ArrayList<>();
        for (Node n : nodes.values()) {
            for (Port port : n.getPorts()) {
                for (Expression e : port.getArguments()) {
                    if (e instanceof Path
                            && ((Path) e).getPath().startsWith(p.getPath())) {
                        found.add(port);
                    }
                }
            }
        }
        
        return found;
    }
    
    /**
     * Resolve a symbol from a given path.
     *
     * @param p path to the resolved
     * @return a node or port that the path corresponds to.
     * @throws PathNotFoundException
     */
    @Override
    public Symbol resolvePath(Path p) throws PathNotFoundException {
        Symbol matchedPort = null;
        
        // check if any realized node names match the start of the path
        Node matchedNode = nodes.get(p.getCurrentPathHead());
        if (matchedNode != null) {
            // pop the path head
            p.popPathHead();
            // attempt to find the path in the node
            matchedPort = matchedNode.resolvePath(p);
        } else if (runtime.getNodeDefs().get(p.getCurrentPathHead()) != null) {
            // determine if desired path is a node not yet realized
            // create the new
            matchedNode = runtime.produceNodeWithName(this, p.getCurrentPathHead(), p.getCurrentPathHead());
            addNode(matchedNode);
            // attempt to find the port in the realized node
            // pop the path head
            p.popPathHead();
            // attempt to find the path in the node
            matchedPort = matchedNode.resolvePath(p);
        }
        
        // IF after checking both the realized and unrealized nodes
        if (matchedPort == null) {
            //  ERROR
            throw new PathNotFoundException(p + " cannnot be resolved.");
        }
        
        return matchedPort;
    }
    
    @Override
    public Symbol resolvePath(String path) throws PathNotFoundException {
        return resolvePath(Path.createPath(path));
    }
}
