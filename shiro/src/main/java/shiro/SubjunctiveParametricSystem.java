package shiro;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.collections15.map.HashedMap;
import shiro.dag.DAGraph;
import shiro.dag.DependencyRelation;
import shiro.dag.GraphNode;
import shiro.dag.TopologicalSort;
import shiro.events.NodeEvent;
import shiro.events.NodeEventListener;
import shiro.events.SubjParametricSystemEvent;
import shiro.events.SubjParametricSystemEventListener;
import shiro.expressions.Path;
import shiro.functions.MultiFunction;
import shiro.functions.MultiplyMFunc;
import shiro.functions.SumMFunc;
import shiro.functions.ValueMFunc;
import shiro.functions.graphics.LineMFunc;
import shiro.functions.graphics.PointMFunc;
import shiro.interpreter.NodeProductionListener;
//import shiro.interpreter.ShiroDefinitionPass;

/**
 * A subjunctive parametric system.
 *
 * @author jeffreyguenther
 */
public class SubjunctiveParametricSystem implements NodeEventListener, Scope {

    private Map<String, MultiFunction> multiFunctions; // multifunction symbol table
    private DAGraph<Port> depGraph;                   // realized dependency graph
    private Map<String, ParseTree> nodeASTDefinitions;// AST table
    private Map<String, Node> nodes;                   // realized node table
    private Map<String, SubjunctiveNode> subjNodes;    // realized subj. node table
    private PortAction graphNodeAction;
    private Set<SubjParametricSystemEventListener> listeners; // Event listeners

    public SubjunctiveParametricSystem() {
        multiFunctions = new HashMap<String, MultiFunction>();
        // load the multifunction map
        loadMultiFunctions(multiFunctions);

        depGraph = new DAGraph<Port>();
        nodeASTDefinitions = new HashMap<String, ParseTree>();
        nodes = new HashedMap<String, Node>();
        subjNodes = new HashMap<String, SubjunctiveNode>();
        graphNodeAction = new PortAction();

        listeners = new HashSet<SubjParametricSystemEventListener>();
    }

    /**
     * *
     * Creates a single instance of all of the multi-functions the system knows
     * about. This creates the symbol table for looking up multi-function
     * objects This method will need to be changed if we do runtime loading of
     * multi-functions
     *
     * @param funcMap
     */
    private void loadMultiFunctions(Map<String, MultiFunction> funcMap) {
        funcMap.put("Value", new ValueMFunc());
        funcMap.put("Multiply", new MultiplyMFunc());
        funcMap.put("Sum", new SumMFunc());

        // add graphics MFs
        funcMap.put("Point", new PointMFunc());
        funcMap.put("Line", new LineMFunc());
    }
    
    /**
     * Loads a multifunction.
     * @param name
     * @param mf
     */
    public void loadMultiFunction(String name, MultiFunction mf){
    	multiFunctions.put(name, mf);
    }

    /**
     * **
     * Looks up a multi-function object by name
     *
     * @param name of the multi-function
     * @return a multi-function
     */
    public MultiFunction getFunction(String name) {
        return multiFunctions.get(name);
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
        Node  matchedNode = nodes.get(p.getCurrentPathHead());
        if (matchedNode != null) {
            // pop the path head
            p.popPathHead();
            // attempt to find the path in the node
            matchedPort = matchedNode.resolvePath(p);
        } else if (nodeASTDefinitions.get(p.getCurrentPathHead()) != null) {
            // determine if desired path is a node not yet realized
            // create the new
            matchedNode = produceNodeFromPath(p.getCurrentPathHead(), p.getCurrentPathHead());
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

    /**
     * *
     * Duplicate a node and change its name.
     *
     * @param path of node to duplicate
     * @param newName of node
     * @return new node produced using path and newName.
     */
    public Node produceNodeFromPath(String path, String newName) {
        Node producedNode = createNode(path);

        // change the node's name
        producedNode.setFullName(newName);
        addNode(producedNode);

        // Check if the node being produced is being produced for the first time
        Node nodeToProduceFrom = nodes.get(path);

        // If the node is not new, change the node's name.
        if (nodeToProduceFrom != null) {
        }

        //update the full name of all of the ports in the node
        for (Port p : producedNode.getPorts()) {
            String newPath = producedNode.getFullName() + "." + p.getName();
            p.setFullName(newPath);
            // remove the null listeners created during duplication process
            p.clearListeners();
            p.addPortEventListener(producedNode);
        }

        // set the enclosing scope of the new node to the current SPS reference
        producedNode.setParentScope(this);

        return producedNode;
    }

    /**
     * Add a node to the system
     *
     * @param n
     */
    public void addNode(Node n) {
        nodes.put(n.getName(), n);
    }

    /**
     * Get a reference to a node by name
     *
     * @param name of node to be returned
     * @return a reference to the node of the given name.
     */
    public Node getNode(String name) {
        return nodes.get(name);
    }

    /**
     * Get a node for the path
     *
     * @param p path to lookup
     * @return node referenced by path
     */
    public Node getNode(Path p) {
        // check the realized nodes
        Node match = nodes.get(p.getCurrentPathHead());

        // check to see if path refers to an unrealized node
        if (match == null
                //&& nodeASTDefinitions.containsKey(p.getCurrentPathHead())
                ) {
            // If it does, build the node.
            match = createNode(p.getCurrentPathHead());

            // add to collection of realized nodes.
            nodes.put(match.getName(), match);
        }

        if (match != null) {
            p.popPathHead();


            // check if the path has any more
            if (match.hasNestedContainers() && !p.isEmpty() && !p.isPathToPortIndex()) {

                for (Container nested : match.getNestedContainers()) {
                    Node nestedNode = (Node) nested;
                    Node match2 = nestedNode.getNode(p);
                    if (match2 != null) {
                        return match2.getNode(p);
                    }
                }
            }

            // RESET the path
            p.resetPathHead();
        }
        return match;
    }

    /**
     * Get all of the nodes in the system.
     *
     * @return a set of all of the nodes in the system
     */
    public Set<Node> getNodes() {
        return new HashSet<Node>(nodes.values());
    }

    /**
     * Remove a node
     *
     * @param name of node to be removed
     */
    public void removeNode(String name) {
        removeNode(nodes.get(name));
    }

    /**
     * Remove a node
     *
     * @param node reference to the node to be removed
     */
    public void removeNode(Node node) {
        // remove all of the node's ports from the graph
        for (Port p : node.getPorts()) {
            //depGraph.removePort(p.getFullName());
        }

        // take care of removing the node if it is in aa subjunct
        for (SubjunctiveNode sn : subjNodes.values()) {
            if (sn.hasSubjunct(node)) {
                try {
                    sn.removeSubjunct(node);
                } catch (SubjunctNotFoundException ex) {
                    Logger.getLogger(SubjunctiveParametricSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        // remove the node
        nodes.remove(node.getName());
    }

    /**
     * Add a port to the dependency graph
     *
     * @param p port to be added
     */
    public void addPort(Port p) {
        depGraph.addNode(new GraphNode<Port>(p, graphNodeAction));
    }

    /**
     * Add a dependency between two ports
     *
     * @param dependency dependency relation to be added to the graph
     */
    public void addDependency(DependencyRelation<Port> dependency) {
        addDependency(dependency.getDependent(), dependency.getDependedOn());
    }

    /**
     * *
     * Add a dependency between two ports A - depends on -> B
     *
     * @param a the depended on port reference
     * @param b the dependent port reference
     */
    public void addDependency(Port a, Port b) {
        if (b == null) {
            depGraph.addDependency(new GraphNode<Port>(a, graphNodeAction), null);
        } else {
            depGraph.addDependency(depGraph.getNodeForValue(a, graphNodeAction),
                    depGraph.getNodeForValue(b, graphNodeAction));
        }
    }

    /**
     * Set the active node in a subjunctive node
     *
     * @param sNode the subjunctive node
     * @param activeNode the node to be set as active subjunct
     * @throws SubjunctNotFoundException
     */
    public void setActiveNode(SubjunctiveNode sNode, Node activeNode) throws SubjunctNotFoundException {
        sNode.setActiveNode(activeNode);
    }

    /**
     * Add the ASTs for each of the nodes. These trees are used to generate node
     * instances when the graph is being built.
     *
     * @param map map of node name to AST trees
     * @return the current state of the definitions map
     */
    public Map<String, ParseTree> addNodeASTDefinitions(Map<String, ParseTree> map) {
        nodeASTDefinitions.putAll(map);
        return nodeASTDefinitions;
    }

    /**
     * Print the names space the whole system
     */
    public void printNameSpace() {
        StringBuilder sb = new StringBuilder();
        sb.append("The parametic system has the following names:")
                .append("\n")
                .append("Nodes:\n");

        // Print the values of a node
        for (Node n : nodes.values()) {
            // TODO add recursive printing for nested nodes.
            sb.append(n.getName())
                    .append("\n");

            sb.append("\twith ports:\n");
            for (Port p : n.getPorts()) {
                if (p.getPortType() != PortType.Evaluated) {
                    sb.append("\t\t")
                            .append(p.getName())
                            .append("\n");
                }
            }
        }

        // trim off the last new line and print.
        System.out.println(sb.toString().trim());
    }

    public String printDependencyGraph() {
        StringBuilder sb = new StringBuilder();
        for (Node n : nodes.values()) {
            sb.append(n);
            sb.append("\n");
        }

        return sb.toString(); //depGraph.toString();
    }

    /**
     * Update the parametric system
     */
    public void update() {
        // sort the graph
        TopologicalSort<Port> sorter = new TopologicalSort<Port>(depGraph);
        List<GraphNode<Port>> topologicalOrdering = sorter.getTopologicalOrdering();

        // loop through all ports to update them.
        for (GraphNode<Port> gn : topologicalOrdering) {
            System.out.println(gn.getValue().getFullName());
            gn.doAction();
        }
    }

    /**
     * *
     * Register an object as a listener to the port's events
     *
     * @param l object to be registered
     */
    public synchronized void addPortEventListener(SubjParametricSystemEventListener l) {
        listeners.add(l);
    }

    /**
     * Unregister an object as a listener
     *
     * @param l object to be removed as a listener
     */
    public synchronized void removePortEventListener(SubjParametricSystemEventListener l) {
        listeners.remove(l);
    }

    /*
     * Fire the port event
     * @param msg the message to be passed along with the event
     */
    protected synchronized void firePortEvent(String msg) {
        SubjParametricSystemEvent event = new SubjParametricSystemEvent(this, msg);
        for (SubjParametricSystemEventListener l : listeners) {
            l.handlePortEvent(event);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node n : nodes.values()) {
            sb.append(n.toString());
        }

        for (SubjunctiveNode sn : subjNodes.values()) {
            sb.append(sn.toString());
        }

        return sb.toString();
    }

    @Override
    public void handleNodeEvent(NodeEvent ne) {
        System.out.println(ne.getMessage());
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getFullName() {
        return "";
    }

    @Override
    public Path getPath() {
        return null;
    }

    private Node createNode(String path) {
        // look up the parse tree of the node to duplicate
        ParseTree nodeAST = nodeASTDefinitions.get(path);
        // walk the parse tree to realize the node
        ParseTreeWalker walker = new ParseTreeWalker();
        // createa node production listener
        NodeProductionListener nodeBuilder = new NodeProductionListener(this);
        // walk the parse tree and build the node
        walker.walk(nodeBuilder, nodeAST );
        
        return nodeBuilder.getCreatedNode();
    }
}
