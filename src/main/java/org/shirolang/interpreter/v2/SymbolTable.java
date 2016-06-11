package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.shirolang.base.SGraph;
import org.shirolang.interpreter.Defaults;
import org.shirolang.interpreter.FunctionFactory;
import org.shirolang.interpreter.NameManager;
import org.shirolang.interpreter.ParseResult;
import org.shirolang.interpreter.ast.GraphDefinition;
import org.shirolang.interpreter.ast.NodeDefinition;
import org.shirolang.interpreter.ast.Program;
import org.shirolang.interpreter.ast.StateDefinition;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * A SymbolTable is the central store of runtime information.
 * It contains a cache of the parse trees for each file referenced in a program.
 */
public class SymbolTable {
    private NameManager nameManager;
    private Map<String, FunctionFactory> functionFactories;

    private Map<String, SGraph> graphs;

    private Map<String, NodeDefinition> nodeDefs;
    private Map<String, GraphDefinition> graphDefs;
    private Map<String, StateDefinition> stateDefs;

    // Cache the results of a file being parsed
    private Map<Path, ParseResult> parseCache;

    public SymbolTable() {
        this.nameManager = new NameManager();
        this.functionFactories = new HashMap<>();

        graphs = new HashMap<>();

        nodeDefs = new HashMap<>();
        graphDefs = new HashMap<>();
        stateDefs = new HashMap<>();

        this.parseCache = new HashMap<>();
        createDefaultGraph();
    }

    /**
     * Gets the default graph
     * @return the default graph
     */
    public SGraph getDefaultGraph(){
        return graphs.get(Defaults.GRAPH_NAME);
    }

    /**
     * Adds the default graph to the library of graphs
     */
    private void createDefaultGraph(){
        graphs.put(Defaults.GRAPH_NAME, new SGraph(Defaults.GRAPH_NAME));
    }

    public void setNodeDefs(Map<String, NodeDefinition> nodeDefs) {
        this.nodeDefs = nodeDefs;
    }

    public Map<String, NodeDefinition> getNodeDefs() {
        return nodeDefs;
    }

    public Map<String, GraphDefinition> getGraphDefs() {
        return graphDefs;
    }

    public void setGraphDefs(Map<String, GraphDefinition> graphDefs) {
        this.graphDefs = graphDefs;
    }

    public Map<String, StateDefinition> getStateDefs() {
        return stateDefs;
    }

    public void setStateDefs(Map<String, StateDefinition> stateDefs) {
        this.stateDefs = stateDefs;
    }

    public Map<String, FunctionFactory> getFunctionFactories() {
        return functionFactories;
    }

    /**
     * Saves the result of a parse
     * @param path path to file that was parsed
     * @param tokens tokens representing the file
     * @param tree the parse tree created by the parser
     * @param ast the abstract representation of the file
     * @return The parse result that is stored
     */
    public ParseResult saveParseResult(Path path, CommonTokenStream tokens, ParseTree tree, Program ast){
        ParseResult parseResult = new ParseResult(tokens, tree, ast);
        parseCache.put(path, parseResult);
        return parseResult;
    }

    /**
     * Gets the cached parse tree for the source at the given path
     * @param path the path to lookup
     * @return parse tree for the source at the path. Return null if path cannot be found.
     */
    public ParseTree getParseTree(Path path){
        ParseResult result = parseCache.get(path);

        if(result != null){
            return result.getParseTree();
        }
        return null;
    }

    /**
     * Gets the cached token stream for the source at the path
     * @param path the path to the source to retrieve from the cache
     * @return the token stream of the source at path. Returns null if path cannot be found.
     */
    public CommonTokenStream getTokens(Path path){
        ParseResult result = parseCache.get(path);

        if(result != null){
            return result.getTokens();
        }
        return null;
    }

    /**
     * Gets the cached AST for the source at the path
     * @param path the path of the AST to retrience
     * @return {@code Program} instance representing the file at the path. Returns null if path cannot be found.
     */
    public Program getProgram(Path path) {
        ParseResult result = parseCache.get(path);

        if(result != null){
            return result.getAst();
        }
        return null;
    }
}