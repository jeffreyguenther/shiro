package org.shirolang.base;

import org.shirolang.dag.GraphNode;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.interpreter.Consoleable;
import org.shirolang.interpreter.ast.Path;
import org.shirolang.interpreter.ast.PathSegment;

import java.util.*;

import static java.util.stream.Collectors.toSet;

/**
 * Defines a graph instance in Shiro.
 *
 * A graph functions as the root of the scope tree for
 * a Shiro program.
 */
public class SGraph implements Scope, Consoleable{
    private SEvaluator evaluator; //object with methods to evaluate graphs

    private String name; // name of the graph
    private Map<String, SNode> nodes; // nodes stored in the graph
    private Map<String, SFunc> ports;
    private Set<SFunc> anonymousPorts;

    public SGraph(){
        this("");
    }

    public SGraph(String name){
        evaluator = new SEvaluator();

        this.name = name;
        this.nodes = new HashMap<>();
        this.ports = new HashMap<>();
        this.anonymousPorts = new HashSet<>();
    }

    /**
     * Add the function
     * @param func function to add the graph
     */
    public void addFunction(SFunc func){
        if(func.getSymbolType().isNode()){
            addNode((SNode) func);
        }else{
            addPort(func);
        }
    }

    /**
     * Adds the node to the graph.
     * The node is stored by it's name.
     * Updates the node's scope to the graph
     * @param node node to be added
     */
    public void addNode(SNode node){
        node.setScope(this);
        nodes.put(node.getName(), node);
    }

    /**
     * Gets the node stored in the graph by name
     * @param name
     * @return
     */
    public SNode getNode(String name){
        return nodes.get(name);
    }

    /**
     * Gets the node instances in the graph
     * @return the add of node instances in the graph
     */
    public Set<SNode> getNodes(){
        return new HashSet<>(nodes.values());
    }

    public Set<SNode> getNodesWithOptions(){
        return nodes.values().stream().filter(SNode::hasOptions).collect(toSet());
    }

    /**
     * Adds a port to the graph
     * @param port port to be added to the graph
     */
    public void addPort(SFunc port){
        ports.put(port.getName(), port);
    }


    public void addAnonymousPort(SFunc port){
        anonymousPorts.add(port);
    }

    /**
     * Gets all of the ports in the graph
     * @return the add of named ports in the graph
     */
    public Set<SFunc> getPorts(){
        HashSet<SFunc> allPorts = new HashSet<>();

        // Get all of the top level ports
        allPorts.addAll(ports.values());
        allPorts.addAll(anonymousPorts);
        for(SNode n: getNodes()){
            allPorts.addAll(n.getPorts());
        }

        // Get all the dependencies
        HashSet<SFunc> others = new HashSet<>();
        for(SFunc f: allPorts){
            others.addAll(collectDependencies(f));
        }

        allPorts.addAll(others);
        return allPorts;
    }

    /**
     * Walk the graph to collect the ports depended on
     * @param port port to collect dependences
     * @return set containing all of the functions depended on
     */
    private Set<SFunc> collectDependencies(SFunc port){
        Set<SFunc> deps = new HashSet<>();

        for(SFunc arg : port.getDependencies()){
            deps.add(arg);

            // if the arg is not a named port
            if(arg.getName().isEmpty()){
                deps.addAll(collectDependencies(arg));
            }
        }

        return deps;
    }

    /**
     * Gets the port with the given name
     * @param name name of the port to be found
     * @return the port with the given name
     */
    public SFunc getPort(String name){
        return ports.get(name);
    }

    /**
     * Evaluates a graph with the passed configuration
     * @param activations subjunct table to determine the active options
     * @throws OptionNotFoundException
     */
    public void evaluate(Set<StateActivation> activations) throws OptionNotFoundException {

        for(StateActivation activation: activations) {
            SNode node = nodes.get(activation.getName());

            if(node != null) {
                node.activateOptions(activation);
            }else{
                throw new OptionNotFoundException("No node named: " + activation.getName() + " cannot be found.");
            }
        }
        evaluate();
    }

    /**
     * Evaluates the graph
     * TODO add details about which options the method will use
     */
    public void evaluate(){
        evaluator.evaluateGraph(getPorts());
    }

    @Override
    public SFunc resolvePath(Path path) throws PathNotFoundException {
        SFunc funcReferenced = null;

        if(path.isAtEnd()){
            // check the nodes
            funcReferenced = nodes.get(path.getSegmentAtHead().getKey().get());

            // check the ports
            if(funcReferenced == null){
                funcReferenced = ports.get(path.getSegmentAtHead().getKey().get());
            }
        }else{ // recursively examine the node
            PathSegment head = path.getSegmentAtHead();

            if(head.getKey().get().equals(getName())){
                path.advanceHead();
            }

            if(head.isSimple()) {
                if (nodes.containsKey(head.getKey().get())) {
                    SNode nodeReferenced = nodes.get(path.getSegmentAtHead().getKey().get());
                    path.advanceHead();
                    funcReferenced = nodeReferenced.resolvePath(path);
                }
            }
        }

        if(funcReferenced == null){
            throw new PathNotFoundException(path + " was not found.");
        }
        
        return funcReferenced;
    }

    @Override
    public SFunc resolvePath(String path) throws PathNotFoundException {
        return resolvePath(Path.create(path));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullName() {
        return name;
    }

    @Override
    public boolean isRoot() {
        return true;
    }

    /**
     * Outputs the graph in it's console from
     * @return string containing console output for the graph
     */
    public String toConsole(){
        StringBuilder sb = new StringBuilder();

        sb.append("#<Graph::").append(name).append("\n[");

        for(GraphNode<SFunc> n: evaluator.getNodes()){
            sb.append(formatDependency(n));
        }
        sb.append("]>");
        return sb.toString();
    }


    /**
     * Generates a dependency string
     * @param node node to print
     * @return string containing each of the node's dependencies on a new line.
     */
    private String formatDependency(GraphNode<SFunc> node){
        StringBuilder sb = new StringBuilder();

        SFunc dependent = node.getValue();

        if(!dependent.getDependencies().isEmpty()) {

            for (GraphNode<SFunc> dependedOn : node.getNodesDependedOn()) {
                SFunc func = dependedOn.getValue();

                sb.append(dependent.getFullName())
                    .append(dependent.toConsole())
                    .append(" => ")
                    .append(func.getFullName())
                    .append(func.toConsole())
                .append("\n");
            }
        }else{
            sb.append(dependent.getFullName())
              .append(dependent.toConsole())
              .append("\n");
        }

        return sb.toString();
    }
}
