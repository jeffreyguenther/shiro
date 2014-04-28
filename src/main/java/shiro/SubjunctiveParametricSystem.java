package shiro;

import shiro.definitions.SystemState;
import shiro.definitions.GraphDefinition;
import shiro.definitions.PortAssignment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import shiro.dag.DAGraph;
import shiro.dag.DependencyRelation;
import shiro.dag.GraphNode;
import shiro.dag.TopologicalSort;
import shiro.definitions.NameManager;
import shiro.events.NodeEvent;
import shiro.events.NodeEventListener;
import shiro.events.SubjParametricSystemEvent;
import shiro.events.SubjParametricSystemEventListener;
import shiro.expressions.Expression;
import shiro.expressions.Path;
import shiro.functions.MultiFunction;
import shiro.functions.MultiplyMFunc;
import shiro.functions.SumMFunc;
import shiro.functions.ValueMFunc;
import shiro.functions.graphics.ArcMFunc;
import shiro.functions.graphics.CircleMFunc;
import shiro.functions.graphics.LineMFunc;
import shiro.functions.graphics.PointMFunc;
import shiro.functions.graphics.RectMFunc;
import shiro.interpreter.EvaluateAlternativeListener;
import shiro.interpreter.GraphBuilderListener;
import shiro.interpreter.NodeDefinitionListener;
import shiro.interpreter.NodeProductionListener;
import shiro.interpreter.ShiroBasePassListener;
import shiro.interpreter.ShiroLexer;
import shiro.interpreter.ShiroParser;

/**
 * This class defines the model of a subjunctive parametric system.
 *
 * It manages all instances of shiro nodes and is responsible for managing
 * updates. On instantiation it loads the multi-functions available to the the
 * runtime.
 *
 * In the future, runtime loading of multi-functions will be supported.
 *
 * To update the parametric system each of the ports is placed in a list. The
 * graph of ports is topologically sorted into a list. The list is evaluated in
 * sequence.
 *
 * @author jeffreyguenther
 */
public class SubjunctiveParametricSystem implements NodeEventListener, Scope {

    private Map<String, MultiFunction> multiFunctions; // multifunction symbol table
    private DAGraph<Port> depGraph;                    // realized dependency graph
    private Map<String, ParseTree> nodeDefs;           // AST table
    private Map<String, ParseTree> subjNodeDefs;       // AST table
    private Map<String, ParseTree> alternativeDefs;    // AST table
    private Map<String, GraphDefinition> graphDefs;    // AST Table
    private GraphDefinition currentGraphDef;

    private Map<String, Node> nodes;                   // realized node table
    private Map<String, SystemState> alternatives;     // alternative specs
    private final NameManager names;                   // class for managing name generation
    private final PortAction graphNodeAction;          // action used in graph nodes

    private CommonTokenStream tokens;                  // reference to the tokens
    // to the last file loaded with loadCode()

    private Set<SubjParametricSystemEventListener> listeners; // Event listeners

    private SimpleStringProperty codeProperty;

    public SubjunctiveParametricSystem() {
        multiFunctions = new HashMap<>();
        // load the multifunction map
        loadMultiFunctions(multiFunctions);

        depGraph = new DAGraph<>();
        nodeDefs = new HashMap<>();
        subjNodeDefs = new HashMap<>();
        alternativeDefs = new HashMap<>();
        graphDefs = new HashMap<>();
        currentGraphDef = null;

        nodes = new HashMap<>();
        alternatives = new HashMap<>();
        names = new NameManager();
        graphNodeAction = new PortAction();

        listeners = new HashSet<>();

        // create default graph and system state
        createDefaultState();

        codeProperty = new SimpleStringProperty("");
    }

    public SimpleStringProperty codeProperty() {
        return codeProperty;
    }

    /**
     * Remove all instances from the runtime. This method does not affect node
     * and subjunctive node definitions or alternatives. Note, this also removes
     * all graph definitions as graphs define instances. The definition is
     * erased. It is not emptied
     */
    public void clear() {
        names.clear();
        nodes.clear();
        graphDefs.clear();
    }

    /**
     * Clear all alternatives from the runtime
     */
    public void reset() {
        clear();
        alternatives.clear();
        createDefaultState();
    }

    public GraphDefinition getGraphDef(String name) {
        return graphDefs.get(name);
    }

    public int getInstanceCountForNode(String type) {
        return names.getNumberOfInstances(type);
    }

    /**
     * *
     * Creates a single instance of all of the multi-functions the system knows
     * about. This creates the symbol table for looking up multi-function
     * objects This method will need to be changed if we load multi-functions at
     * runtime.
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
        funcMap.put("Circle", new CircleMFunc());
        funcMap.put("Arc", new ArcMFunc());
        funcMap.put("Rectangle", new RectMFunc());
    }

    /**
     * Store a multi-function in the symbol table.
     *
     * @param name of multi-function
     * @param mf multi-function to be added
     */
    public void loadMultiFunction(String name, MultiFunction mf) {
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

//    public SubjunctiveNode split(Node nodeToSplit, String name,
//            String nameOfSubjunct, Map<Path, List<Expression>> newValues)
//            throws PathNotFoundException, PathNotAccessibleException {
//        // create a new subjunctive node
//        SubjunctiveNode result = new SubjunctiveNode(name, this);
//        Path toMatch = Path.createPath(nodeToSplit.getName());
//        List<Port> findAll = findAll(toMatch);
//        // replace reference to nodeToSplit in any expressions where it is used
//        // set expressions to use newly created subjunctive node active port
//        for (Port p : findAll) {
//            replace(p, toMatch, name, true);
//        }
//
//        // rename nodeToSplit nodeName_1
//        // lookup type
//        String type = nodeToSplit.getType();
//        // create instance of node
//        Node n = produceNodeFromName(type, nameOfSubjunct);
//
//        // set the arguments of the instance
//        for (Path p : newValues.keySet()) {
//            Port port = (Port) n.find(p);
//            port.setArguments(newValues.get(p));
//
//        }
//        
//        // add instance to subjunctive node
//        result.addSubjunct(nodeToSplit);
//        result.addSubjunct(n);
//
//        // remove node production from graph
//        currentGraphDef.removeProduction(nodeToSplit.getType(), nodeToSplit.getName());
//        
//        // add production for new subjunctive node
//        currentGraphDef.addProduction(result.getName(), names.getNextName(result.getName()));
//        
//        // add subjunctive node selection to all existing alternatives
//        boolean first = true;
//        for(SystemState s: getStates()){
//            if(first){
//                SystemState newState = new SystemState(currentGraphDef, names.getNextName("state"));
//                newState.setActiveNode(s.getSubjunctsMapping());
//                newState.setActiveNode(result, n);
//                addAlternative(newState);
//                
//                first = false;
//            }
//            s.setActiveNode(result, nodeToSplit);
//        }
//        
//        return result;
//    }

    @Override
    public Symbol find(Path p) throws PathNotFoundException, PathNotAccessibleException {
        Symbol matchedSymbol = null;

        // check proto instances
        // if the path matches a node type
        if (p.isAtEnd() && nodeDefs.containsKey(p.getCurrentPathHead())) {
            if (nodes.containsKey(p.getCurrentPathHead())) {
                matchedSymbol = nodes.get(p.getCurrentPathHead());
            } else {
                matchedSymbol = produceNodeFromName(p.getCurrentPathHead(), p.getCurrentPathHead());
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

    public void replace(Port found, Path match, String s, boolean active) {
        for (Expression e : found.getArguments()) {
            if (e instanceof Path
                    && ((Path) e).getPath().startsWith(match.getPath())) {
                Path expr = (Path) e;
                if (active) {
                    expr.getPathParts().set(0, s);
                    expr.getPathParts().add(1, "active");
                } else {
                    expr.getPathParts().set(0, s);
                }
            }
        }
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
        }else if (nodeDefs.get(p.getCurrentPathHead()) != null) {
            // determine if desired path is a node not yet realized
            // create the new
            matchedNode = produceNodeFromName(p.getCurrentPathHead(), p.getCurrentPathHead());
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

    /**
     * Produce a symbol from a type name
     *
     * @param type of shiro symbol to instantiate
     * @param name of the instance
     * @return the symbol instantiated. Returns null if the type is not found
     */
    public Symbol produceSymbolFromName(String type, String name) {
        // check to see if the name is a node
        if (nodeDefs.containsKey(type)) {
            return produceNodeFromName(type, name);
            // check if name is subjunctive node
        }

        return null;
    }

    /**
     * Creates an instance of a node
     *
     * @param type of node to create
     * @return a reference to created node
     */
    public Node createNode(String type) {
        // generate a new name for the node
        String name = names.getNextName(type);

        // produce the new node
        Node node = produceNodeFromName(type, name);

        currentGraphDef.addProduction(type, name);

        return node;
    }

    /**
     * Adds the ports in a node to the dependency graph
     *
     * @param node
     */
    public void addNodeToGraph(Node node) {
        for (DependencyRelation<Port> d : node.getDependencies()) {
            addDependency(d);
        }
    }

    /**
     * *
     * Duplicate a node and change its name.
     *
     * @param name of node to duplicate
     * @param newName of node
     * @return new node produced using path and newName.
     */
    public Node produceNodeFromName(String name, String newName) {

        Node producedNode = realizeNode(name);

        // change the node's name
        producedNode.setFullName(newName);
        addNode(producedNode);

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

    public Set<shiro.Node> getNodesOfType(String type) {
        Set<shiro.Node> matches = new HashSet<>();
        for (shiro.Node n : getNodes()) {
            if (n.getType().equals(type)) {
                matches.add(n);
            }
        }

        return matches;
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
        if (match == null) {
            // If it does, build the node.
            match = realizeNode(p.getCurrentPathHead());

            // add to collection of realized nodes.
            nodes.put(match.getName(), match);
        }

        if (match != null) {
            p.popPathHead();

            // check if the path has any more
            if (match.hasNestedNodes()&& !p.isEmpty() && !p.isPathToPortIndex()) {

                for (Node nested : match.getNestedNodes()) {
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
        return new HashSet<>(nodes.values());
    }

    public ParseTree getNodeDef(String path) {
        return nodeDefs.get(path);
    }

    public Port setPortExpression(Path pathToPort, Expression expr) throws PathNotFoundException {
        return setPortExpression(pathToPort, expr, 0);
    }

    public Port setPortExpression(Path pathToPort, Expression expr, int argPos) throws PathNotFoundException {
        Port port = (Port) resolvePath(pathToPort);
        port.setArgumentForPosition(argPos, expr);

        currentGraphDef.addPortAssignment(new PortAssignment(pathToPort, expr));
        return port;
    }

    public Port setPortExpression(PortAssignment assign) throws PathNotFoundException {
        Port port = (Port) resolvePath(assign.getPath());

        port.setArguments(assign.getArgs());

        currentGraphDef.addPortAssignment(assign);
        return port;
    }

    /**
     * Add a port to the dependency graph
     *
     * @param p port to be added
     */
    public void addPort(Port p) {
        depGraph.addNode(new GraphNode<>(p, graphNodeAction));
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
     */
    public void setActiveNode(Node sNode, Node activeNode){
            sNode.setActiveOption(activeNode.getName());
    }

    /**
     * Add the ASTs for each of the nodes. These trees are used to generate node
     * instances when the graph is being built.
     *
     * @param map map of node name to AST trees
     * @return the current state of the definitions map
     */
    public Map<String, ParseTree> addNodeASTDefinitions(Map<String, ParseTree> map) {
        nodeDefs.putAll(map);
        return nodeDefs;
    }

    /**
     * Add the parse trees for collection of subjunctive nodes
     *
     * @param map subjunctive node name to parse tree mappings
     * @return the complete map subjunctive nodes
     */
    public Map<String, ParseTree> addSubjNodeASTDefinitions(Map<String, ParseTree> map) {
        subjNodeDefs.putAll(map);
        return subjNodeDefs;
    }

    public Map<String, ParseTree> addAlternativeASTDefinitions(Map<String, ParseTree> map) {
        alternativeDefs.putAll(map);
        return alternativeDefs;
    }

    public String printDependencyGraph() {
        StringBuilder sb = new StringBuilder();
        for (Node n : nodes.values()) {
            sb.append(n);
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Method to create a default state to allow the graph to update
     */
    private void createDefaultState() {
        // add a graph definition
        currentGraphDef = new GraphDefinition("Default");
        graphDefs.put(currentGraphDef.getName(), currentGraphDef);

        // add an alternative
        addAlternative(new SystemState(currentGraphDef, "Default"));
    }

    /**
     * Update the parametric system
     */
    public void update() {

        ParseTreeWalker walker = new ParseTreeWalker();
        EvaluateAlternativeListener genAlts = new EvaluateAlternativeListener(this);

        // Create system state objects from alternative def'ns
        for (ParseTree altTree : alternativeDefs.values()) {
            walker.walk(genAlts, altTree);
        }

        //Evaluate each alternative
        for (SystemState a : alternatives.values()) {
            update(a);
        }
    }

    public void update(SystemState alt) {
        Map<Node, Node> subjunctTable = alt.getSubjunctsMapping();

        for (Node s : subjunctTable.keySet()) {
            s.setActiveOption(subjunctTable.get(s).getName());
        }

        List<DependencyRelation<Port>> deps = new ArrayList<>();

        // for each node generated in the graph generation process
        for (Node n : getNodes()) {
            // get all of the dependencies for each node
            deps.addAll(n.getDependencies());
        }

        for (DependencyRelation<Port> d : deps) {
            addDependency(d);
        }

        TopologicalSort<Port> sorter = new TopologicalSort<>(depGraph);
        List<GraphNode<Port>> topologicalOrdering = sorter.getTopologicalOrdering();

        // loop through all ports to update them.
        for (GraphNode<Port> gn : topologicalOrdering) {
            gn.doAction();
        }
        depGraph.removeAllDependencies();
    }

    /**
     * *
     * Register an object as a listener to the port's events
     *
     * @param l object to be registered
     */
    public synchronized void addPSystemEventListener(SubjParametricSystemEventListener l) {
        listeners.add(l);
    }

    /**
     * Unregister an object as a listener
     *
     * @param l object to be removed as a listener
     */
    public synchronized void removePSystemEventListener(SubjParametricSystemEventListener l) {
        listeners.remove(l);
    }

    /*
     * Fire the port event
     * @param msg the message to be passed along with the event
     */
    protected synchronized void firePSystemEvent(String msg) {
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

        return sb.toString();
    }

    @Override
    public void handleNodeEvent(NodeEvent ne) {
//        System.out.println(ne.getMessage());
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

    /**
     * Instantiates the node from the ParseTree. Looks up the ParseTree in the
     * map and generates the node
     *
     * @param name of node to produce
     * @return Node object
     */
    private Node realizeNode(String name) {
        // look up the parse tree of the node to duplicate
        ParseTree nodeAST = nodeDefs.get(name);
        // walk the parse tree to realize the node
        ParseTreeWalker walker = new ParseTreeWalker();
        // createa node production listener
        NodeProductionListener nodeBuilder = new NodeProductionListener(this);
        // walk the parse tree and build the node
        walker.walk(nodeBuilder, nodeAST);

        return nodeBuilder.getCreatedNode();
    }

    /**
     * Set the value of a port with an expression
     *
     * The value expression is parsed and any necessary dependencies are added
     * Note: This will NOT cause the parametric system to update. You must call
     * update() separately.
     *
     * @param portPath The path to the port to update. The path given must be
     * the port's full name. For example, <code>Point.x</code>.
     * @param value an expression giving the port's value.
     */
    public void setPortValue(String portPath, String value) {
        // NOTE: the scope of the expression is the node

        // Lex the expression
        // Parse the expression to generate the parse tree
        // Walk the parse tree to generate port dependencies
        // Add the dependencies to the graph
    }

    /**
     * Load node definitions specified in the shiro/node_defs.sro package
     */
    public void loadDefinitions() {
        ANTLRInputStream is = null;
        try {
            is = new ANTLRInputStream(
                    getClass().getClassLoader()
                    .getResourceAsStream("shiro/node_defs.sro"));
        } catch (IOException ex) {
            Logger.getLogger(SubjunctiveParametricSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        ShiroParser parser = parse(true, is);
        this.addNodeASTDefinitions(generateNodeDefs(parser.shiro()));
    }

    /**
     * Generate node AST's from the parse tree
     *
     * @param tree to generate AST frome
     * @return map of node to parse tree object
     */
    public Map<String, ParseTree> generateNodeDefs(ParseTree tree) {
        // Walk the parse tree to create the ndoe definitions
        ParseTreeWalker walker = new ParseTreeWalker();
        NodeDefinitionListener defPass = new NodeDefinitionListener();

        // Walk the tree with the def pass listener
        walker.walk(defPass, tree);

        return defPass.getNodeDefinitions();
    }

    /**
     * Load node definitions This method is helpful to load node definitions for
     * use by a GUI
     *
     * @param filePath file to be loaded
     */
    public void loadDefinitions(String filePath) {
        // Read in a file
        ANTLRInputStream is = null;
        try {
            is = new ANTLRFileStream(filePath);
        } catch (IOException ex) {
            Logger.getLogger(SubjunctiveParametricSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        // parse the input stream
        ShiroParser parser = parse(true, is);
        // generate the AST from the parse tree
        this.addNodeASTDefinitions(generateNodeDefs(parser.shiro()));
    }

    /**
     * Parse an expression in the given scope
     *
     * @param scope of the new expression
     * @param expr string
     * @return the expression object representing the {@code expr}
     */
    public Expression parseExpression(Scope scope, String expr) {
        ShiroParser parser = parse(false, new ANTLRInputStream(expr));
        ShiroParser.ExpressionContext expression = parser.expression();

        ParseTreeWalker walker = new ParseTreeWalker();
        ShiroBasePassListener ls = new ShiroBasePassListener(this, scope);
        walker.walk(ls, expression);

        return ls.getExpr(expression.expr());
    }

    /**
     * Parse an input stream of shiro code
     *
     * @param saveTokens determines whether the token stream should be saved
     * internally
     * @param inputStream a stream of the shiro code to be parsed
     * @return reference to parser
     */
    public ShiroParser parse(boolean saveTokens, ANTLRInputStream inputStream) {
        // lex the stream
        ShiroLexer lex = new ShiroLexer(inputStream);

        ShiroParser parser;

        if (saveTokens) {
            tokens = new CommonTokenStream(lex);
            parser = new ShiroParser(tokens);

        } else {
            CommonTokenStream ts = new CommonTokenStream(lex);
            parser = new ShiroParser(ts);
        }

        // generate the parse tree during parsing
        parser.setBuildParseTree(true);

        return parser;
    }

    /**
     * Add an alternative to the the parametric system
     *
     * @param state
     */
    public void addAlternative(SystemState state) {
        alternatives.put(state.getName(), state);
    }

    /**
     * Generate code for the runtime
     *
     * @return a string containing the shiro code representation of the current
     * state of the runtime.
     */
    public String toCode() {
        StringBuilder sb = new StringBuilder();

        // print node definitions
        nodeDefs.values().stream().forEach((ParseTree t) -> {
            sb.append(tokens.getText((RuleContext) t)).append("\n\n");
        });

        // print graphs
        graphDefs.values().stream().forEach((GraphDefinition gd) -> {
            sb.append(gd.toCode()).append("\n\n");
        });

        // print states
        alternatives.values().stream().forEach((SystemState state) -> {
            sb.append(state.toCode()).append("\n\n");
        });

        return sb.toString();
    }

    /**
     * Write out the current state of the runtime as shiro code
     *
     * @param file file to write code
     */
    public void writeCode(File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(toCode());
            bw.close();

        } catch (IOException e) {

        }
    }
    
    public void loadCode(String code){
        ParseTreeWalker walker = new ParseTreeWalker();

        // create a lexer
        ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(code));

        // Get the token stream
        tokens = new CommonTokenStream(lex);

        // Parse the file
        ShiroParser parser = new ShiroParser(tokens);
        parser.setBuildParseTree(true);

        // Create the parse tree object
        ParseTree tree = parser.shiro();

        // PASS 1: Add a node -> AST mapping in the parametric system
        NodeDefinitionListener defPass = new NodeDefinitionListener();
        // Walk the tree with the def pass listener
        walker.walk(defPass, tree);

        // Get the node definitions
        Map<String, ParseTree> nodeDefinitions = defPass.getNodeDefinitions();
        Map<String, ParseTree> alternativeDefinitions = defPass.getAlternativeDefinitions();
        ParseTree graph = defPass.getGraph();

        // Store the ASTs in the tree
        addNodeASTDefinitions(nodeDefinitions);
        addAlternativeASTDefinitions(alternativeDefinitions);

        // PASS 2: Build the dependency graph
        /**
         * Walk only the graph parse tree to prevent events from firing on the
         * other parts of the parse tree
         */
        GraphBuilderListener graphBuilder = new GraphBuilderListener(this);
        walker.walk(graphBuilder, graph);

        GraphDefinition graphDef = graphBuilder.getGraphDef();
        graphDefs.remove("Default");
        graphDefs.put(graphDef.getName(), graphDef);
        currentGraphDef = graphDef;

        // deal with name auto generation
        for (String key : nodeDefs.keySet()) {
            names.setInstanceCount(key, getNodesOfType(key).size());
        }

        alternatives.remove("Default");

        // Evaluate parametric system
        update();

        codeProperty.setValue(toCode());
    }

    /**
     * Load code from a file
     *
     * @param file to load code from
     * @throws IOException
     */
    public void loadCode(File file) throws IOException {
        ParseTreeWalker walker = new ParseTreeWalker();

        // create a lexer
        ShiroLexer lex = new ShiroLexer(new ANTLRFileStream(file.getAbsolutePath(), "UTF8"));

        // Get the token stream
        tokens = new CommonTokenStream(lex);

        // Parse the file
        ShiroParser parser = new ShiroParser(tokens);
        parser.setBuildParseTree(true);

        // Create the parse tree object
        ParseTree tree = parser.shiro();

        // PASS 1: Add a node -> AST mapping in the parametric system
        NodeDefinitionListener defPass = new NodeDefinitionListener();
        // Walk the tree with the def pass listener
        walker.walk(defPass, tree);

        // Get the node definitions
        Map<String, ParseTree> nodeDefinitions = defPass.getNodeDefinitions();
        Map<String, ParseTree> alternativeDefinitions = defPass.getAlternativeDefinitions();
        ParseTree graph = defPass.getGraph();

        // Store the ASTs in the tree
        addNodeASTDefinitions(nodeDefinitions);
        addAlternativeASTDefinitions(alternativeDefinitions);

        // PASS 2: Build the dependency graph
        /**
         * Walk only the graph parse tree to prevent events from firing on the
         * other parts of the parse tree
         */
        GraphBuilderListener graphBuilder = new GraphBuilderListener(this);
        walker.walk(graphBuilder, graph);

        GraphDefinition graphDef = graphBuilder.getGraphDef();
        graphDefs.remove("Default");
        graphDefs.put(graphDef.getName(), graphDef);
        currentGraphDef = graphDef;

        // deal with name auto generation
        for (String key : nodeDefs.keySet()) {
            names.setInstanceCount(key, getNodesOfType(key).size());
        }

        alternatives.remove("Default");

        // Evaluate parametric system
        update();

        codeProperty.setValue(toCode());
    }

    public Collection<String> getStateNames() {
        return alternatives.keySet();
    }

    public SystemState getState(String name) {
        return alternatives.get(name);
    }

    public Set<SystemState> getStates() {
        return new HashSet<>(alternatives.values());
    }
}
