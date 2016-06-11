package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.shirolang.base.*;
import org.shirolang.dag.DAGraph;
import org.shirolang.dag.DependencyRelation;
import org.shirolang.dag.GraphNode;
import org.shirolang.dag.TopologicalSort;
import org.shirolang.exceptions.NameUsedException;
import org.shirolang.functions.color.ColorFromHSB;
import org.shirolang.functions.color.ColorFromRGB;
import org.shirolang.functions.color.SColor;
import org.shirolang.functions.conditionals.SConditionalReturn;
import org.shirolang.functions.conditionals.SConditionalReturnNode;
import org.shirolang.functions.geometry.*;
import org.shirolang.functions.lists.SMap;
import org.shirolang.functions.math.*;
import org.shirolang.interpreter.Defaults;
import org.shirolang.interpreter.FunctionFactory;
import org.shirolang.interpreter.ShiroLexer;
import org.shirolang.interpreter.ShiroParser;
import org.shirolang.interpreter.ast.NodeDefinition;
import org.shirolang.interpreter.ast.Program;
import org.shirolang.values.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Defines a class for evaluating Shiro code
 */
public class ProgramEvaluator {
    private SymbolTable symbolTable;
    private EvaluatorErrorListener parseLexErrorListener;
    private List<Error> errors;

    public ProgramEvaluator(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.parseLexErrorListener = new EvaluatorErrorListener();
        errors = new ArrayList<>();

        loadRuntimeFunctions();
        loadGraphicsFunctions();
    }

    /**
     * Gets the symbol table
     * @return the current symbol table for the runtime
     */
    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    /**
     * Evaluates a string of Shiro code
     * @param code the code to evaluate
     * @return the list of errors. If the evaluation was successful, the list will be empty.
     */
    public List<Error> evaluate(String code) {
        Path mainProgramFile = Paths.get("main.sro");

        return evaluate(mainProgramFile, lex(code));
    }

    /**
     * Evaluates a string of Shiro code
     * @param code the code to evaluate
     * @return the list of errors. If the evaluation was successful, the list will be empty.
     */
    public List<Error> evaluate(Path code){
        CommonTokenStream tokens = lex(code);
        if(tokens != null){
            return evaluate(code, tokens);
        }else {
            return errors;
        }
    }

    /**
     * Evaluates a program
     * @param mainProgramFile the path of the file
     * @param tokens the tokens for the file
     * @return the list of errors. If the evaluation was successful, the list will be empty.
     */
    private List<Error> evaluate(Path mainProgramFile, CommonTokenStream tokens){
        symbolTable = new SymbolTable();

        // Bail if there are errors from initial lex of code
        if(hasErrors()){
            return errors;
        }

        ParseTree tree = parse(tokens);
        if(hasErrors()){
            return errors;
        }

        Program program = buildAST(tree);
        List<Path> dependencies = resolveDependencies(program, mainProgramFile);

        if(hasErrors()){
            return errors;
        }

        dependencies.forEach(this::loadDependency);
        loadProgram(program);

        // Generate states
//        generateStates();
        // Realize state
//        symbolTable.getStateDefs().values().forEach(s -> {
//            System.out.println(s.getName());
//        });

        // Realize Graph
        GraphVisitor graphVisitor = new GraphVisitor(this);
        SGraph g = graphVisitor.visit(symbolTable.getGraphDefs().get(Defaults.GRAPH_NAME));
        // Bail if there are errors
        if(graphVisitor.hasErrors()){
            return graphVisitor.getErrors();
        }

        graphVisitor.setPass(MultiPassVisitor.SECOND_PASS);
        graphVisitor.visit(symbolTable.getGraphDefs().get(Defaults.GRAPH_NAME));
        // Bail if there are errors
        if(graphVisitor.hasErrors()){
            return graphVisitor.getErrors();
        }

        // Activate Options

        // Bail if there are errors
        // evaluate

        return errors;
    }

    /**
     * Determines if the evaluator has errors
     * @return true if the evaluator has errors, otherwise false
     */
    public boolean hasErrors(){
        return !errors.isEmpty();
    }

    /**
     * Loads a dependency
     * @param file the file to load
     *             This method looks up the path in the symbol table's cache and loads it's node definitions
     */
    private void loadDependency(Path file){
        Program program = symbolTable.getProgram(file);
        symbolTable.setNodeDefs(program.getNodeDefsByName());
    }

    /**
     * Loads the programs nodes, states, and graph definitions
     * @param p the program to lad
     */
    private void loadProgram(Program p){
        symbolTable.setNodeDefs(p.getNodeDefsByName());
        symbolTable.setGraphDefs(p.getGraphDefsByName());
        if(p.hasDefaultGraph()){
            symbolTable.getGraphDefs().put(Defaults.GRAPH_NAME, p.getDefaultGraph());
        }
        symbolTable.setStateDefs(p.getStateDefsByName());
    }

    /**
     * Resolves the depencies for a program
     * @param p the program whose dependencies are to be loaded
     * @param pathToMainProgram the file path of the program
     * @return a list of paths in topological order
     */
    private List<Path> resolveDependencies(Program p, Path pathToMainProgram) {
        IncludeVisitor visitor = new IncludeVisitor(this, pathToMainProgram);
        Set<DependencyRelation<Path>> includes = visitor.visit(p);

        DAGraph<Path> graph = new DAGraph<>();
        for (DependencyRelation<Path> dep : includes) {
            graph.addDependency(graph.getNodeForValue(dep.getDependent(), null), graph.getNodeForValue(dep.getDependedOn(), null));
        }

        TopologicalSort<Path> topoSort = new TopologicalSort<>(graph);
        List<GraphNode<Path>> topologicalOrdering = topoSort.getTopologicalOrdering();
        List<Path> sortedPaths = topologicalOrdering.stream().map(GraphNode::getValue).collect(toList());
        sortedPaths.remove(pathToMainProgram);

        return sortedPaths;
    }

    /**
     * Builds an instance of Program for the file
     * @param file the file to lex, parse, and convert into an AST
     * @return an Optional program. The optional will be empty if there were syntax errors
     * @throws IOException if the file cannot be opened
     */
    public Optional<Program> buildAST(Path file) throws IOException {
        CommonTokenStream tokens = lex(file);
        if(hasErrors()){
            return Optional.empty();
        }

        ParseTree tree = parse(tokens);
        if(hasErrors()){
            return Optional.empty();
        }

        Program program = buildAST(tree);
        symbolTable.saveParseResult(file, tokens, tree, program);
        return Optional.of(program);
    }

    /**
     * Builds an instance of Program for the parse tree
     * @param tree a parse tree to turn into an AST tree
     * @return a Program instance representing the parse tree
     */
    private Program buildAST(ParseTree tree){
        ParseTreeWalker walker = new ParseTreeWalker();
        ASTBuilder ast = new ASTBuilder();
        walker.walk(ast, tree);
        return ast.getProgram();
    }

    /**
     * Gets an instance of the Shiro lexer
     * @param input code to be lexed
     * @return a {@code ShiroLexer} initialized with the source
     */
    public ShiroLexer getLexer(CharStream input){
        updateErrorListener();

        ShiroLexer lexer = new ShiroLexer(input);
        lexer.addErrorListener(parseLexErrorListener);
        if(!parseLexErrorListener.getErrors().isEmpty()){
            errors.addAll(parseLexErrorListener.getErrors());
        }
        return lexer;
    }

    /**
     * Lex the code
     * @param code code to be lexed
     * @return stream of tokens
     */
    public CommonTokenStream lex(String code){
        return new CommonTokenStream(getLexer(new ANTLRInputStream(code)));
    }

    /**
     * Lex the code at the given path
     * @param path path of the source code
     * @return stream of tokens
     */
    public CommonTokenStream lex(Path path) {
        try {
            return new CommonTokenStream(getLexer(new ANTLRFileStream(path.toRealPath().toString())));
        } catch (IOException e) {
            errors.add(new SyntaxError(path + " cannot be opened."));
        }

        return null;
    }

    /**
     * Creates an instance of {@code ShiroParser} initialized with the token stream
     * @param tokens the token stream
     * @return instance of Shiro parser
     */
    public ShiroParser getParser(CommonTokenStream tokens){
        updateErrorListener();

        ShiroParser parser = new ShiroParser(tokens);
        parser.setBuildParseTree(true);
        parser.addErrorListener(parseLexErrorListener);

        return parser;
    }

    /**
     * Creates a new instance of the EvaluatorErrorListener
     */
    private void updateErrorListener(){
        parseLexErrorListener = new EvaluatorErrorListener();
    }

    /**
     * Parses the token stream
     * @param tokens tokens to be parsed
     * @return the parse tree of the token stream
     */
    public ParseTree parse(CommonTokenStream tokens){
        ShiroParser.ShiroContext shiro = getParser(tokens).shiro();
        if(!parseLexErrorListener.getErrors().isEmpty()){
            errors.addAll(parseLexErrorListener.getErrors());
        }
        return shiro;
    }

    /**
     * Creates a function with of the given type
     * @param type type of the function
     * @return the function corresponding to the type. The function might be a node or a port
     */
    public Optional<SFunc> createFunction(SGraph g, String type, String name){
        FunctionFactory factory = symbolTable.getFunctionFactories().get(type);
        if(factory != null){
            SFunc port = factory.create();
            port.setName(name);
            port.setSymbolType(SymbolType.PORT);
            return Optional.of(port);
        }

        Optional<NodeDefinition> nodeDef = resolveTypeToDefinition(type);
        if(nodeDef.isPresent()){
            NodeVisitor visitor = new NodeVisitor(this, g);
            SNode node = visitor.visit(nodeDef.get());
            node.setName(name);

            visitor.setPass(MultiPassVisitor.SECOND_PASS);
            visitor.visit(nodeDef.get());

            errors.addAll(visitor.getErrors());

            return Optional.of(node);
        }
        return Optional.empty();
    }

    /**
     * Recursively resolves types to definition
     * @param def parent node definition
     * @param types stack of types to explore
     * @return the node definition for the type, or null if no definition can be found
     */
    private Optional<NodeDefinition> resolveTypeToDefinition(NodeDefinition def, Stack<String> types){
        Optional<NodeDefinition> parentDef = def.getNodes().stream().filter(d -> d.getName().equals(types.pop())).findFirst();
        if(parentDef.isPresent() && types.empty()){
            return parentDef;
        }else if(parentDef.isPresent()){
            return resolveTypeToDefinition(parentDef.get(), types);
        }else {
            return Optional.empty();
        }
    }

    /**
     * Resolve the string type a NodeDefinition
     * @param type type of NodeDefinition
     * @return an Optional containing the NodeDefinition if it exists
     */
    private Optional<NodeDefinition> resolveTypeToDefinition(String type){
        Optional<NodeDefinition> def;

        if(type.contains(".")){
            List<String> types = Arrays.asList(type.split("\\."));

            Stack<String> typeStack = new Stack<>();
            Collections.reverse(types);
            typeStack.addAll(types);

            String t = typeStack.pop();
            def = Optional.of(symbolTable.getNodeDefs().get(t));
            if(!typeStack.isEmpty()){
                def = resolveTypeToDefinition(def.get(), typeStack);
            }
        }else{
            def = Optional.ofNullable(symbolTable.getNodeDefs().get(type));
        }

        return def;
    }

    /**
     * Registers a function factory with the library. Use this method to add
     * a custom type to the Shiro runtime.
     * @param type name of the function you are adding
     * @param f factory being associated with the name
     */
    public final void registerFunction(String type, FunctionFactory f) throws NameUsedException {
        if(isFactoryRegisteredForType(type)){
            throw new NameUsedException(type + " is already used. Please choose another name for your type.");
        }

        symbolTable.getFunctionFactories().put(type, f);
    }

    /**
     * Checks if type already used
     * @param type name of type being checked
     * @return true if the name is already being used, otherwise false
     */
    public boolean isFactoryRegisteredForType(String type){
        return symbolTable.getFunctionFactories().containsKey(type);
    }

    /**
     * Creates FunctionFactories for all of the internal Shiro functions
     */
    private void loadRuntimeFunctions(){
        try {
            registerFunction(SType.BOOLEAN, SBoolean::new);
            registerFunction(SType.DOUBLE, SDouble::new);
            registerFunction(SType.IDENT, SIdent::new);
            registerFunction(SType.INTEGER, SInteger::new);
            registerFunction(SType.STRING, SString::new);
            registerFunction(SType.LIST, SList::new);

            registerFunction(SType.ADD, SAdd::new);
            registerFunction(SType.AND, SAnd::new);
            registerFunction(SType.DIVIDE, SDivide::new);
            registerFunction(SType.EQUAL, SEqual::new);
            registerFunction(SType.GREATERTHAN, SGreaterThan::new);
            registerFunction(SType.GREATERTHAN_OR_EQUAL, SGreaterThanOrEqual::new);
            registerFunction(SType.LESSTHAN, SLessThan::new);
            registerFunction(SType.LESSTHAN_OR_EQUAL, SLessThanOrEqual::new);
            registerFunction(SType.MODULO, SModulo::new);
            registerFunction(SType.MULTIPLY, SMultiply::new);
            registerFunction(SType.NEGATIVE, SNegative::new);
            registerFunction(SType.NOT, SNot::new);
            registerFunction(SType.NOT_EQUAL, SNotEqual::new);
            registerFunction(SType.OR, SOr::new);
            registerFunction(SType.POWER, SPower::new);
            registerFunction(SType.SUBTRACT, SSubtract::new);
            registerFunction(SType.SUM, SSum::new);

            registerFunction(SType.MAP, SMap::new);
            registerFunction(SType.CONDITIONAL_RETURN, SConditionalReturn::new);
            registerFunction(SType.CONDITIONAL_RETURN_NODE, SConditionalReturnNode::new);
        } catch (NameUsedException e) {
            throw new RuntimeException("Something crazy happened and an internal type is already defined!");
        }
    }

    /**
     * Creates FunctionFactories for internal graphics functions
     */
    private void loadGraphicsFunctions(){
        try{
            registerFunction("Color", SColor::new);
            registerFunction("ColorFromRGB", ColorFromRGB::new);
            registerFunction("ColorFromHSB", ColorFromHSB::new);
            registerFunction("ColorToGrayscale", ColorFromHSB::new);

            registerFunction("SRectangle", SRectangle::new);
            registerFunction("SEllipse", SEllipse::new);
            registerFunction("SArc", SArc::new);
            registerFunction("SLine", SLine::new);
            registerFunction("SText", SText::new);
            registerFunction("SGroup", SGroup::new);
        }catch (NameUsedException e) {
            throw new RuntimeException("Something crazy happened and an internal type is already defined!");
        }
    }
}