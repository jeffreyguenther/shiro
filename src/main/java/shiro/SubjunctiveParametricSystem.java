package shiro;

import shiro.exceptions.PathNotFoundException;
import shiro.exceptions.PathNotAccessibleException;
import shiro.definitions.State;
import shiro.definitions.GraphDefinition;
import shiro.definitions.PortAssignment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import static java.util.stream.Collectors.toList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import shiro.dag.DAGraph;
import shiro.dag.DependencyRelation;
import shiro.dag.GraphNode;
import shiro.dag.TopologicalSort;
import shiro.definitions.NameManager;
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
import shiro.interpreter.DefinitionResult;
import shiro.interpreter.DescriptiveErrorListener;
import shiro.interpreter.EvaluateAlternativeListener;
import shiro.interpreter.GraphBuilderListener;
import shiro.interpreter.NodeDefinitionListener;
import shiro.interpreter.NodeProductionListener;
import shiro.interpreter.ParseResult;
import shiro.interpreter.ShiroBasePassListener;
import shiro.interpreter.ShiroLexer;
import shiro.interpreter.ShiroParser;
import shiro.interpreter.UseCodeListener;

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
public class SubjunctiveParametricSystem implements Scope {
    // cached results of parsed files
    private Map<java.nio.file.Path, ParseResult> parseResults;
    
    private Map<String, MultiFunction> multiFunctions; // multifunction symbol table
    private Map<String, ParseTree> nodeDefs;           // AST table
    private Map<String, GraphDefinition> graphDefs;    // AST Table
    private Map<String, ParseTree> graphTrees;         // temporary graph def table
    private Map<String, ParseTree> alternativeDefs;    // AST table

    private Map<String, Node> nodes;                   // realized node table
    private Map<String, DAGraph<Port>> graphs;          // realized graphs;
    private Map<String, State> alternatives;           // alternative specs
    private final NameManager nameManager;             // class to manage name generation
    private final PortAction graphNodeAction;          // action used in graph nodes

    private SimpleStringProperty codeProperty;
    private SimpleStringProperty errorMessagesProperty;
    private SimpleBooleanProperty hasErrorsProperty;

    public SubjunctiveParametricSystem() {
        parseResults = new HashMap<>();
        
        multiFunctions = new HashMap<>();
        loadMultiFunctions(multiFunctions);

        nodeDefs = new HashMap<>();
        graphDefs = new HashMap<>();
        graphTrees = new HashMap<>();
        alternativeDefs = new HashMap<>();
        
        nodes = new HashMap<>();
        graphs = new HashMap<>();
        alternatives = new HashMap<>();
        nameManager = new NameManager();
        graphNodeAction = new PortAction();

        codeProperty = new SimpleStringProperty("");
        errorMessagesProperty = new SimpleStringProperty("");
        hasErrorsProperty = new SimpleBooleanProperty(false);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Parsing Methods">
    public ShiroLexer getLexer(CharStream input){
        ShiroLexer lexer = new ShiroLexer(input);
        lexer.addErrorListener(new DescriptiveErrorListener(this));
        return lexer;
    }
    
    public CommonTokenStream lex(String code){
        return new CommonTokenStream(getLexer(new ANTLRInputStream(code)));
    }
    
    public CommonTokenStream lex(java.nio.file.Path path) throws IOException{
        return new CommonTokenStream(getLexer(new ANTLRFileStream(path.toRealPath().toString())));
    }
    
    public ShiroParser getParser(CommonTokenStream tokens){
        ShiroParser parser = new ShiroParser(tokens);
        parser.addErrorListener(new DescriptiveErrorListener(this));
        parser.setBuildParseTree(true);
        return parser;
    }
    
    public ParseTree parseFromRootRule(CommonTokenStream tokens){
        return getParser(tokens).shiro();
    }
    
    public ParseTree parseFromExpression(CommonTokenStream tokens) {
        return getParser(tokens).expression();
    }
    
    /**
     * Parse an expression in the given scope
     *
     * @param scope of the new expression
     * @param expr string
     * @return the expression object representing the {@code expr}
     */
    public Expression parseExpression(Scope scope, String expr) {
        ShiroParser.ExpressionContext expression; 
        expression = (ShiroParser.ExpressionContext) parseFromExpression(lex(expr));
        
        ShiroBasePassListener ls = new ShiroBasePassListener(this, scope);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(ls, expression);

        return ls.getExpr(expression.expr());
    }
    
    /**
     * Saves the result of a parse
     * @param path path to file that was parsed
     * @param tokens tokens representing the file
     * @param tree the parse tree created by the parser
     * @return The parse result that is stored
     */
    public ParseResult saveParseResult(java.nio.file.Path path, CommonTokenStream tokens, ParseTree tree){
        ParseResult parseResult = new ParseResult(tokens, tree);
        parseResults.put(path, parseResult);
        return parseResult;
    }
    
    public ParseTree getParseTree(java.nio.file.Path path){
        ParseResult result = parseResults.get(path);
        
        if(result != null){
            return result.getParseTree();
        }
        return null;
    }
    
    public CommonTokenStream getTokens(java.nio.file.Path path){
        ParseResult result = parseResults.get(path);
        
        if(result != null){
            return result.getTokens();
        }
        return null;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Modify Runtime">
    private Map<String, ParseTree> getNodeDefinitions(java.nio.file.Path path){
        ParseTree parseTree = getParseTree(path);
        NodeDefinitionListener definitionWalker = new NodeDefinitionListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(definitionWalker, parseTree);
        return definitionWalker.getNodeDefinitions();
    }
    
    private DefinitionResult getNodeStateGraphDefinitions(java.nio.file.Path path){
        ParseTree parseTree = getParseTree(path);
        NodeDefinitionListener defPass = new NodeDefinitionListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(defPass, parseTree);
        return new DefinitionResult(defPass.getNodeDefinitions(), defPass.getGraphs(), defPass.getAlternativeDefinitions());
    }
    
    private Node realizeNode(ParseTree nodeDef) {
        NodeProductionListener nodeBuilder = new NodeProductionListener(this);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(nodeBuilder, nodeDef);
        return nodeBuilder.getCreatedNode();
    }
    
    private DAGraph<Port> realizeGraph(ParseTree graph){
        GraphBuilderListener graphBuilder = new GraphBuilderListener(this);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(graphBuilder, graph);
        return graphBuilder.getGraph();
    }
    
    private void realizeGraphs(){
        for(Entry<String, ParseTree> e: graphTrees.entrySet()){
           graphs.put(e.getKey(), realizeGraph(e.getValue()));
        }
    }
    
    private void addGraphParseTrees(Map<String, ParseTree> map){
        graphTrees.putAll(map);
    }
    
    private void realizeStates(){
        alternativeDefs.values().forEach((ParseTree p) -> addAlternative(realizeState(p)));
    }
    
    private State realizeState(ParseTree stateDef){
        EvaluateAlternativeListener genAlts = new EvaluateAlternativeListener(this);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(genAlts, stateDef);
        return genAlts.getState();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Load Code">
    public void loadCode(String code) {
        CommonTokenStream lex = lex(code);
        java.nio.file.Path tempPath = Paths.get("temp.sro");
        load(tempPath, lex);
    }
    
    public void loadCode(java.nio.file.Path path) throws IOException {
        CommonTokenStream lex = lex(path);
        load(path, lex);
    }
    
    private void loadDependency(java.nio.file.Path dep){
        Map<String, ParseTree> nodeDefinitions = getNodeDefinitions(dep);
        addNodeASTDefinitions(nodeDefinitions);
        // TODO load graphs and states from dependents
    }
    
    private void loadDependencies(List<java.nio.file.Path> deps){
        for(java.nio.file.Path p: deps){
            loadDependency(p);
        }
    }
    
    private void loadNodeStateGraphDefinitions(java.nio.file.Path path){
        DefinitionResult defs = getNodeStateGraphDefinitions(path);
        addNodeASTDefinitions(defs.getNodeDefinitions());
        addAlternativeASTDefinitions(defs.getAlternativeDefinitions());
        addGraphParseTrees(defs.getGraphDefinitions());
    }
    
    private void load(java.nio.file.Path rootFile, CommonTokenStream tokens){
        List<java.nio.file.Path> dependedOnFiles = resolveDependencies(rootFile, tokens);
        loadDependencies(dependedOnFiles);
        loadNodeStateGraphDefinitions(rootFile);
        realizeGraphs();
        realizeStates();
        update();
    }
    
    private List<java.nio.file.Path> resolveDependencies(java.nio.file.Path rootFile, CommonTokenStream tokens){
        // lookup path in cache to see if it has already been parsed
        ParseTree tree = getParseTree(rootFile);
        if(tree == null){
            tree = parseFromRootRule(tokens);
            saveParseResult(rootFile, tokens, tree);
        }
        
        UseCodeListener useCode = new UseCodeListener(rootFile, this);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(useCode, tree);

        DAGraph<java.nio.file.Path> graph = new DAGraph<>();
        for (DependencyRelation<java.nio.file.Path> dep : useCode.getSourceFiles()) {
            graph.addDependency(graph.getNodeForValue(dep.getDependent(), null), graph.getNodeForValue(dep.getDependedOn(), null));
        }
        
        TopologicalSort<java.nio.file.Path> topoSort = new TopologicalSort<>(graph);
        List<GraphNode<java.nio.file.Path>> topologicalOrdering = topoSort.getTopologicalOrdering();
        List<java.nio.file.Path> sortedPaths = topologicalOrdering.stream().map(GraphNode<java.nio.file.Path>::getValue).collect(toList());
        sortedPaths.remove(rootFile);
        
        return sortedPaths;
    }
    
    //</editor-fold>
    
    //<editor-fold desc="Setup and Reset">
    /**
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
    public MultiFunction getMultiFunction(String name) {
        return multiFunctions.get(name);
    }
    
    /**
     * Resets the runtime. All definitions and instances are cleared.
     */
    public void reset() {
        removeAllDefinitions();
        removeAllInstances();
    }
    
    private void removeAllDefinitions(){
        nodeDefs.clear();
        graphDefs.clear();
        alternativeDefs.clear();
        parseResults.clear();
    }
    
    /**
     * Remove all instances from the runtime. This method does not affect node
     * and subjunctive node definitions or alternatives. Note, this also removes
     * all graph definitions as graphs define instances. The definition is
     * erased. It is not emptied
     */
    public void removeAllInstances() {
        nameManager.reset();
        nodes.clear();
        alternatives.clear();
        clearErrorMessages();
    }
    
    /**
     * Method to create a default state to allow the graph to update
     */
    private void createDefaultState() {
        // add a graph definition
        GraphDefinition defaultGraph = new GraphDefinition("Default");
        graphDefs.put(defaultGraph.getName(), defaultGraph);

        // add an alternative
        addAlternative(new State(defaultGraph.getName(), "Default"));
    }
    
    /**
     * Load node definitions This method is helpful to load node definitions for
     * use by a GUI
     *
     * @param filePath file to be loaded
     * @throws java.io.IOException
     */
    public void loadDefinitions(java.nio.file.Path filePath) throws IOException {
        CommonTokenStream lex = lex(filePath);
        ParseTree parseTree = parseFromRootRule(lex);
        saveParseResult(filePath, lex, parseTree);
        
        this.addNodeASTDefinitions(getNodeDefinitions(filePath));
    }
    
    
    /**
     * Load node definitions specified in the shiro/node_defs.sro package
     * @throws java.io.IOException
     */
    public void loadDefaultDefinitions() throws IOException {
        URL resource = getClass().getClassLoader().getResource("shiro/node_defs.sro");
        loadDefinitions(Paths.get(resource.getPath()));
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Evaluation">
    /**
     * Update the parametric system
     */
    public void update() {
       //TODO generate all alternatives automatically if there are none specified
        // also handle the case where there is not subjunctive nodes.
        // for each graph instance, evaluate
        
        alternatives.values().stream().forEach((a) -> {
            update(a);
        });
    }

    public void update(State alt) {
        Map<Node, Symbol> subjunctTable = alt.getSubjunctsMapping();

        for (Node s : subjunctTable.keySet()) {
            s.setActiveOption(subjunctTable.get(s).getName());
        }

        List<DependencyRelation<Port>> deps = new ArrayList<>();

        // look up the graph referenced by the state
        DAGraph<Port> graphReferenced = graphs.get(alt.getGraphDef());

        TopologicalSort<Port> sorter = new TopologicalSort<>(graphReferenced);
        List<GraphNode<Port>> topologicalOrdering = sorter.getTopologicalOrdering();

        // loop through all ports to update them.
        for (GraphNode<Port> gn : topologicalOrdering) {
            gn.doAction();
        }
    }
    //</editor-fold>
    
    public GraphDefinition getGraphDef(String name) {
        return graphDefs.get(name);
    }
    
    public int getInstanceCountForNode(String type) {
        return nameManager.getNumberOfInstances(type);
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
//        for(State s: getStates()){
//            if(first){
//                State newState = new State(currentGraphDef, names.getNextName("state"));
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
    //<editor-fold defaultstate="collapsed" desc="Look up">
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
        } else if (nodeDefs.get(p.getCurrentPathHead()) != null) {
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
    //</editor-fold>

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
        String name = nameManager.getNextName(type);

        // produce the new node
        Node node = produceNodeFromName(type, name);

        return node;
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
        ParseTree nodeDef = nodeDefs.get(name);
        Node producedNode = realizeNode(nodeDef);

        // change the node's name
        producedNode.setFullName(newName);
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

    public Set<Node> getNodesOfType(String type) {
        Set<Node> matches = new HashSet<>();
        for (shiro.Node n : getNodes()) {
            if (n.getType().equals(type)) {
                matches.add(n);

            }
            matches.addAll(n.getNodesOfType(type));
        }
        return matches;
    }

    /**
     * Gets a reference to a node by name This method checks the collection of
     * top level node instances for a node with the given. It does not traverse
     * the tree of nested nodes.
     *
     * @param name of node to be returned
     * @return a reference to the node of the given name.
     */
    public Node getNode(String name) {
        return nodes.get(name);
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

    public Port setPortExpression(String graphName, Path pathToPort, Expression expr) throws PathNotFoundException {
        return setPortExpression(graphName, pathToPort, expr, 0);
    }

    public Port setPortExpression(String graphName, Path pathToPort, Expression expr, int argPos) throws PathNotFoundException {
        Port port = (Port) resolvePath(pathToPort);
        port.setArgumentForPosition(argPos, expr);

        GraphDefinition graph = graphDefs.get(graphName);
        graph.addPortAssignment(new PortAssignment(pathToPort, expr));
        return port;
    }

    public Port setPortExpression(String graphName, PortAssignment assign) throws PathNotFoundException {
        Port port = (Port) resolvePath(assign.getPath());

        port.setArguments(assign.getArgs());
        
        GraphDefinition graph = graphDefs.get(graphName);
        graph.addPortAssignment(assign);
        return port;
    }

    /**
     * Add a dependency between two ports
     *
     * @param graph
     * @param dependency dependency relation to be added to the graph
     */
    public void addDependency(DAGraph<Port> graph, DependencyRelation<Port> dependency) {
        addDependency(graph, dependency.getDependent(), dependency.getDependedOn());
    }

    /**
     * *
     * Add a dependency between two ports A - depends on -> B
     * @param graph
     * @param a the depended on port reference
     * @param b the dependent port reference
     */
    public void addDependency(DAGraph<Port> graph, Port a, Port b) {
        
        if (b == null) {
            graph.addDependency(new GraphNode<>(a, graphNodeAction), null);
        } else {
            graph.addDependency(graph.getNodeForValue(a, graphNodeAction),
                    graph.getNodeForValue(b, graphNodeAction));
        }
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node n : nodes.values()) {
            sb.append(n.toString());
        }

        return sb.toString();
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
     * Add an alternative to the the parametric system
     *
     * @param state
     */
    public void addAlternative(State state) {
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
            // TODO fix code output
//            sb.append(tokens.getText((RuleContext) t)).append("\n\n");
        });

        // print graphs
        graphDefs.values().stream().forEach((GraphDefinition gd) -> {
            sb.append(gd.toCode()).append("\n\n");
        });

        // print states
        alternatives.values().stream().forEach((State state) -> {
            sb.append(state.toCode()).append("\n\n");
        });

        return sb.toString();
    }

    public void setHasSyntaxErrors(boolean value) {
        hasErrorsProperty.set(value);
    }

    public boolean hasErrors() {
        return hasErrorsProperty.get();
    }

    public SimpleBooleanProperty hasErrorsProperty() {
        return hasErrorsProperty;
    }

    public void appendErrorMessage(String msg) {
        errorMessagesProperty.set(msg);
    }

    public void clearErrorMessages() {
        errorMessagesProperty.set("");
        setHasSyntaxErrors(false);
    }

    public SimpleStringProperty errorMessagesProperty() {
        return errorMessagesProperty;
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

    public Collection<String> getStateNames() {
        return alternatives.keySet();
    }

    public State getState(String name) {
        return alternatives.get(name);
    }

    public Set<State> getStates() {
        return new HashSet<>(alternatives.values());
    }
    
    public SimpleStringProperty codeProperty() {
        return codeProperty;
    }
}
