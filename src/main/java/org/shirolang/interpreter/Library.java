/*
 * Copyright (c) 2012-2014 Jeffrey Guenther
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software  and associated documentation files (the
 * "Software"), to deal in the Software without restriction,  including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute,  sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT  NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.shirolang.interpreter;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.shirolang.base.*;
import org.shirolang.exceptions.GraphNotFoundException;
import org.shirolang.exceptions.NameUsedException;
import org.shirolang.exceptions.PortNotFoundException;
import org.shirolang.functions.color.ColorFromHSB;
import org.shirolang.functions.color.ColorFromRGB;
import org.shirolang.functions.color.SColor;
import org.shirolang.functions.geometry.*;
import org.shirolang.functions.math.*;
import org.shirolang.values.*;
import org.shirolang.values.Path;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A Library is the central store of runtime information.
 * It contains the symbol table and handles tasks like auto-generating
 * variable names
 */
public class Library {
    public static String DEFAULT_GRAPH_NAME = "^";
    public static String DEFAULT_STATE_NAME = "$";

    private NameManager nameManager;
    private Map<String, FunctionFactory> mfuncs;

    // Runtime instances
    private Map<String, SState> states;
    private Map<String, SGraph> graphs;
    private Map<String, SFunc> symbols;

    // Runtime definitions
    private Map<String, ParseTree> nodeDefs;
    private Map<String, ParseTree> graphDefs;
    private Map<String, ParseTree> alternativeDefs;

    // Cache the results of a file being parsed
    private Map<java.nio.file.Path, ParseResult> parseCache;

    /**
     * Creates a library
     * When the library is created, it loads all of the internal
     * Shiro multi-functions. These are used to handle expressions.
     */
    public Library(){
        nameManager = new NameManager();
        mfuncs = new HashMap<>();

        states = new HashMap<>();
        graphs = new HashMap<>();
        symbols = new HashMap<>();

        nodeDefs = new HashMap<>();
        graphDefs = new HashMap<>();
        alternativeDefs = new HashMap<>();

        parseCache = new HashMap<>();

        // load basic multi-functions
        loadRuntimeFunctions();
        loadGraphicsFunctions();
        createDefaultGraph();
    }

    /**
     * Clears the library and adds the default graph to the library
     */
    public void reset() {
        nameManager.reset();
        parseCache.clear();

        states.clear();
        graphs.clear();
        symbols.clear();

        nodeDefs.clear();
        graphDefs.clear();
        alternativeDefs.clear();
        createDefaultGraph();
    }

    /**
     * Adds the default graph to the library of graphs
     */
    private void createDefaultGraph(){
        graphs.put(DEFAULT_GRAPH_NAME, new SGraph(DEFAULT_GRAPH_NAME));
    }

    /**
     * Gets the name manager used by the runtime.
     * @return
     */
    public NameManager getNameManager() {
        return nameManager;
    }

    /**
     * Adds a symbol to the given graph
     * @param graphName name of the graph to add the symbol to
     * @param symbol symbol to add to the graph
     */
    public void addSymbolToGraph(String graphName, SFunc symbol) throws GraphNotFoundException {
        SGraph graph = graphs.get(graphName);

        if(graph == null){
            throw new GraphNotFoundException("A graph named \"" + graphName + "\" cannot be found.");
        }

        if(symbol.getSymbolType().isNode()){
            graph.addNode((SNode) symbol);
        }else if(symbol.getSymbolType().isPort()){
            graph.addPort(symbol);
        }
    }

    /**
     * Creates a function with of the given type
     * @param type type of the multi-function
     * @return the multi-function corresponding to the type
     */
    public SFunc createFunction(String type){
        FunctionFactory factory = mfuncs.get(type);
        if(factory != null){
            return factory.create();
        }else{
            throw new RuntimeException(type + " cannot be found.");
        }

    }

    /**
     * Create a function of the given type
     * Looks in the node definitions
     * @param p type of the node to create
     * @param g graph where the node should be stored
     * @param name name of the instance
     * @return an instance of the SNode of the given type
     */
    public SFunc instantiateNode(SGraph g, Path p, String name){
        // TODO handle the instantiation of nested nodes
        ParseTree nodeDef = nodeDefs.get(p.getPath());

        if(nodeDef == null){
            throw new RuntimeException(p.getPath() + " cannot be found");
        }

        NodeInstantiator nodeProducer = new NodeInstantiator(this, g);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(nodeProducer, nodeDef);
        SNode node = nodeProducer.getCreatedNode();
        node.setName(name);
        return node;
    }

    /**
     * Instantiate a graph object from a parse tree
     * Note: the graph is not stored in the library after it is created.
     * You must store it yourself
     * @param tree parse tree of graph to instantiate
     * @param name name of graph
     * @return the created graph
     */
    public SGraph instantiateNamedGraph(ParseTree tree, String name){
        ParseTreeWalker walker = new ParseTreeWalker();

        SGraph g = new SGraph(name);
        GraphBuilder graphBuilder = new GraphBuilder(this,g);
        walker.walk(graphBuilder, tree);

        return g;
    }

    /**
     * Registers a function factory with the library. Use this method to add
     * a custom type to the Shiro runtime.
     * @param name name of the multi-function you are adding
     * @param f factory being associated with the name
     */
    public final void registerFunction(String name, FunctionFactory f) throws NameUsedException {
        if(isTypeNameUsed(name)){
            throw new NameUsedException(name + " is already used. Please choose another name for your type.");
        }

        mfuncs.put(name, f);
    }

    /**
     * Checks if type already used
     * @param type name of type being checked
     * @return true if the name is already being used, otherwise false
     */
    public boolean isTypeNameUsed(String type){
        // check the mfuncs
        return mfuncs.keySet().contains(type);
    }

    /**
     * Get all the used type names
     * @return
     */
    public Set<String> getTypeNames(){
        return mfuncs.keySet();
    }

    /**
     * Gets the default graph
     * @return the default graph
     */
    public SGraph getDefaultGraph(){
        return graphs.get(DEFAULT_GRAPH_NAME);
    }

    /**
     * Gets the default runtime state
     * The state is lazily created
     * @return the default state
     */
    public SState getDefaultState(){
        if(!states.containsKey(DEFAULT_STATE_NAME)){
            states.put(DEFAULT_STATE_NAME, new SState(DEFAULT_STATE_NAME));
        }

        return states.get(DEFAULT_STATE_NAME);
    }

    /**
     * Adds the node definitions to the map
     * @param defs map of node names and their parse trees
     */
    public void addNodeDefs(Map<String, ParseTree> defs){
        nodeDefs.putAll(defs);
    }

    /**
     * Gets the node definitions
     * @return the map of parse tree and node names
     */
    public Map<String, ParseTree> getNodeDefs(){
        return nodeDefs;
    }

    /**
     * Stores the graph definitions to the map
     * @param defs map of graph names to their parse trees
     */
    public void addGraphDefs(Map<String, ParseTree> defs){
        graphDefs.putAll(defs);
    }

    /**
     * Stores the state definition to the map
     * @param defs map of state names to their parse trees
     */
    public void addAlternativeDefs(Map<String, ParseTree> defs){
        alternativeDefs.putAll(defs);
    }

    /**
     * Get all of the parse trees for the named graphs
     * Does not include anonymous graph
     * @return the map of named parse trees
     */
    public Map<String, ParseTree> getGraphDefs(){
        return graphDefs;
    }

    /**
     * Gets all parse trees for the alternatives
     * @return the map of named parse trees
     */
    public Map<String, ParseTree> getAlternativeDefs(){
        return alternativeDefs;
    }

    /**
     * Saves the graph to the library
     * @param g graph to be stored
     */
    public void addGraph(SGraph g){
        graphs.put(g.getName(), g);
    }

    /**
     * Stores the state in the library
     * @param state state to be stored
     */
    public void addState(SState state){
        states.put(state.getName(), state);
    }

    /**
     * Gets the states stored in the library
     * @return the map of states
     */
    public Map<String, SState> getStates(){
        return states;
    }

    /**
     * Determines if the library has user defined states
     * @return true if there are no state definitions stored in the
     * library, otherwise false
     */
    public boolean hasUserDefinedStates(){
        return !alternativeDefs.isEmpty();
    }

    /**
     * Determines if the library has user defined graphs
     * @return true if there are no graph definitions stored in the
     * library, otherwise false
     */
    public boolean hasUserDefinedGraphs(){
        return !graphDefs.isEmpty();
    }

    /**
     * Gets the graph with the given name
     * @param graphName the name of the graph to retrieve
     * @return the graph mapped to the passed name
     */
    public SGraph getGraph(String graphName) {
        return graphs.get(graphName);
    }

    /**
     * Gets the graphs stored in the library
     * @return add of all the graphs stored in the library
     * including the default graph
     */
    public Set<SGraph> getGraphs(){
        return new HashSet<SGraph>(graphs.values());
    }

    /**
     * Creates FunctionFactories for all of the internal Shiro multi-functions
     */
    private void loadRuntimeFunctions(){
        try {
            registerFunction(SType.BOOLEAN, () -> new SBoolean());
            registerFunction(SType.DOUBLE, () -> new SDouble());
            registerFunction(SType.IDENT, () -> new SIdent());
            registerFunction(SType.INTEGER, () -> new SInteger());
            registerFunction(SType.STRING, () -> new SString());
            registerFunction(SType.LIST, () -> new SList());

            registerFunction(SType.ADD, () -> new SAdd());
            registerFunction(SType.AND, () -> new SAnd());
            registerFunction(SType.DIVIDE, () -> new SDivide());
            registerFunction(SType.EQUAL, () -> new SEqual());
            registerFunction(SType.GREATERTHAN, () -> new SGreaterThan());
            registerFunction(SType.GREATERTHAN_OR_EQUAL, () -> new SGreaterThanOrEqual());
            registerFunction(SType.LESSTHAN, () -> new SLessThan());
            registerFunction(SType.LESSTHAN_OR_EQUAL, () -> new SLessThanOrEqual());
            registerFunction(SType.MODULO, () -> new SModulo());
            registerFunction(SType.MULTIPLY, () -> new SMultiply());
            registerFunction(SType.NEGATIVE, () -> new SNegative());
            registerFunction(SType.NOT, () -> new SNot());
            registerFunction(SType.NOT_EQUAL, () -> new SNotEqual());
            registerFunction(SType.OR, () -> new SOr());
            registerFunction(SType.POWER, () -> new SPower());
            registerFunction(SType.SUBTRACT, () -> new SSubtract());

        } catch (NameUsedException e) {
            throw new RuntimeException("Something crazy happened and an internal type is already defined!");
        }
    }

    private void loadGraphicsFunctions(){
        try{
            registerFunction("Color", () -> new SColor());
            registerFunction("ColorFromRGB", () -> new ColorFromRGB());
            registerFunction("ColorFromHSB", () -> new ColorFromHSB());
            registerFunction("ColorToGrayscale", () -> new ColorFromHSB());

            registerFunction("Rectangle", () -> new SRectangle());
            registerFunction("Ellipse", () -> new SEllipse());
            registerFunction("Arc", () -> new SArc());
            registerFunction("Line", () -> new SLine());
            registerFunction("Text", () -> new SText());
        }catch (NameUsedException e) {
            throw new RuntimeException("Something crazy happened and an internal type is already defined!");
        }
    }

    /**
     * Gets an instance of the Shiro lexer
     * @param input code to be lexed
     * @return a {@code ShiroLexer} initialized with the source
     */
    public ShiroLexer getLexer(CharStream input){
        ShiroLexer lexer = new ShiroLexer(input);
//        lexer.addErrorListener(new DescriptiveErrorListener(this));
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
     * @throws IOException
     */
    public CommonTokenStream lex(java.nio.file.Path path) throws IOException {
        return new CommonTokenStream(getLexer(new ANTLRFileStream(path.toRealPath().toString())));
    }

    /**
     * Creates an instance of {@code ShiroParser} initialized with the token stream
     * @param tokens the token stream
     * @return instance of Shiro parser
     */
    public ShiroParser getParser(CommonTokenStream tokens){
        ShiroParser parser = new ShiroParser(tokens);
//        parser.addErrorListener(new DescriptiveErrorListener(this));
        parser.setBuildParseTree(true);
        return parser;
    }

    /**
     * Parses the token stream
     * @param tokens tokens to be parsed
     * @return the parse tree of the token stream
     */
    public ParseTree parse(CommonTokenStream tokens){
        return getParser(tokens).shiro();
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
        parseCache.put(path, parseResult);
        return parseResult;
    }

    /**
     * Gets the cached parse tree for the source at the given path
     * @param path the path to lookup
     * @return parse tree for the source at the path
     */
    public ParseTree getParseTree(java.nio.file.Path path){
        ParseResult result = parseCache.get(path);

        if(result != null){
            return result.getParseTree();
        }
        return null;
    }

    /**
     * Gets the cached token stream for the source at the path
     * @param path the path to the source to retrieve from teh cache
     * @return the token stream of the source at {@code path}
     */
    public CommonTokenStream getTokens(java.nio.file.Path path){
        ParseResult result = parseCache.get(path);

        if(result != null){
            return result.getTokens();
        }
        return null;
    }
}
