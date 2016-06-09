package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.shirolang.base.*;
import org.shirolang.exceptions.NameUsedException;
import org.shirolang.functions.color.ColorFromHSB;
import org.shirolang.functions.color.ColorFromRGB;
import org.shirolang.functions.color.SColor;
import org.shirolang.functions.conditionals.SConditionalReturn;
import org.shirolang.functions.conditionals.SConditionalReturnNode;
import org.shirolang.functions.geometry.*;
import org.shirolang.functions.lists.SMap;
import org.shirolang.functions.math.*;
import org.shirolang.interpreter.*;
import org.shirolang.interpreter.ast.Program;
import org.shirolang.values.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A SymbolTable is the central store of runtime information.
 * It contains a cache of the parse trees for each file referenced in a program.
 */
public class SymbolTable {
    private NameManager nameManager;
    private Map<String, FunctionFactory> functionFactories;

    private Map<String, SGraph> graphs;

    // Cache the results of a file being parsed
    private Map<Path, ParseResult> parseCache;

    public SymbolTable() {
        this.nameManager = new NameManager();
        this.functionFactories = new HashMap<>();

        graphs = new HashMap<>();

        this.parseCache = new HashMap<>();

        loadRuntimeFunctions();
        loadGraphicsFunctions();
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

    /**
     * Creates a function with of the given type
     * @param type type of the function
     * @return the function corresponding to the type. The function might be a node or a port
     */
    public SFunc createFunction(SGraph g, String type){
        FunctionFactory factory = functionFactories.get(type);
        if(factory != null){
            SFunc port = factory.create();
            port.setSymbolType(SymbolType.PORT);
            return port;
        }

//        ParseTree nodeDef = nodeDefs.get(type);
//        if(nodeDef != null){
//            NodeInstantiator nodeProducer = new NodeInstantiator(this, g);
//            ParseTreeWalker walker = new ParseTreeWalker();
//            walker.walk(nodeProducer, nodeDef);
//            SNode node = nodeProducer.getCreatedNode();
//            return node;
//        }

        throw new RuntimeException(type + " cannot be found.");
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

        functionFactories.put(name, f);
    }

    /**
     * Checks if type already used
     * @param type name of type being checked
     * @return true if the name is already being used, otherwise false
     */
    public boolean isTypeNameUsed(String type){
        return functionFactories.keySet().contains(type);
    }

    /**
     * Creates FunctionFactories for all of the internal Shiro multi-functions
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

    /**
     * Lexes and parse the code
     * Does not cache the result in the SymbolTable
     * @param code string of the Shiro code to lex and parse
     * @return a parse tree representing the code
     */
    public ParseTree lexAndParse(String code){
        CommonTokenStream lex = lex(code);
        ParseTree tree = parse(lex);

        return tree;
    }

    /**
     * Lexes and parse the code
     * Caches the result in the SymbolTables
     * @param file path of the Shiro code to lex and parse
     * @return a parse tree representing the code
     */
    public ParseTree lexAndParse(Path file) throws IOException {
        CommonTokenStream lex = lex(file);
        ParseTree tree = parse(lex);
        saveParseResult(file, lex, tree);

        return tree;
    }

    /**
     * Gets an instance of the Shiro lexer
     * @param input code to be lexed
     * @return a {@code ShiroLexer} initialized with the source
     */
    public ShiroLexer getLexer(CharStream input){
        ShiroLexer lexer = new ShiroLexer(input);
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
    public CommonTokenStream lex(Path path) throws IOException {
        return new CommonTokenStream(getLexer(new ANTLRFileStream(path.toRealPath().toString())));
    }

    /**
     * Creates an instance of {@code ShiroParser} initialized with the token stream
     * @param tokens the token stream
     * @return instance of Shiro parser
     */
    public ShiroParser getParser(CommonTokenStream tokens){
        ShiroParser parser = new ShiroParser(tokens);
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
    public ParseResult saveParseResult(Path path, CommonTokenStream tokens, ParseTree tree){
        ParseResult parseResult = new ParseResult(tokens, tree);
        parseCache.put(path, parseResult);
        return parseResult;
    }

    /**
     * Gets the cached parse tree for the source at the given path
     * @param path the path to lookup
     * @return parse tree for the source at the path
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
     * @param path the path to the source to retrieve from teh cache
     * @return the token stream of the source at {@code path}
     */
    public CommonTokenStream getTokens(Path path){
        ParseResult result = parseCache.get(path);

        if(result != null){
            return result.getTokens();
        }
        return null;
    }
}