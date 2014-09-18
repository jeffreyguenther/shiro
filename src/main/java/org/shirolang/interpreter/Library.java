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

import org.antlr.runtime.tree.ParseTree;
import org.shirolang.FunctionFactory;
import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.SNode;
import org.shirolang.base.SType;
import org.shirolang.exceptions.GraphNotFoundException;
import org.shirolang.functions.math.*;
import org.shirolang.values.*;

import java.util.HashMap;
import java.util.Map;

/**
 * A Library is the central store of runtime information.
 * It contains the symbol table and handles tasks like auto-generating
 * variable names
 */
public class Library {
    public static String DEFAULT_GRAPH_NAME = "";
    private Map<String, FunctionFactory> mfuncs;

    // Runtime instances
    private Map<String, SGraph> graphs;
    private Map<String, SFunc> symbols;

    // Runtime definitions
    private Map<String, ParseTree> nodeDefs;
    private Map<String, ParseTree> graphDefs;
    private Map<String, ParseTree> alternativeDefs;

    /**
     * Creates a library
     * When the library is created, it loads all of the internal
     * Shiro multi-functions. These are used to handle expressions.
     */
    public Library(){
        mfuncs = new HashMap<>();
        graphs = new HashMap<>();
        symbols = new HashMap<>();

        nodeDefs = new HashMap<>();
        graphDefs = new HashMap<>();
        alternativeDefs = new HashMap<>();

        // load basic multi-functions
        loadRuntimeFunctions();
        graphs.put(DEFAULT_GRAPH_NAME, new SGraph());
    }

    /**
     * Adds a symbol to the given graph
     * @param graphName name of the graph to add the symbol to
     * @param symbol symbol to add to the graph
     */
    public void addSymbolToGraph(String graphName, SFunc symbol) throws GraphNotFoundException {
        SGraph graph = graphs.get(graphName);

        if(graph == null){
            throw new GraphNotFoundException("A graph named " + graphName + " cannot be found.");
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
        return mfuncs.get(type).create();
    }

    /**
     * Registers a function factory with the library. This is done to add
     * a custom type to the Shiro runtime.
     * @param name name of the multi-function you are adding
     * @param f factory being associated with the name
     */
    public final void registerFunction(String name, FunctionFactory f){
        // TODO check the runtime to ensure the name is free

        mfuncs.put(name, f);
    }

    /**
     * Creates FunctionFactories for all of the internal Shiro multi-functions
     */
    private void loadRuntimeFunctions(){
        registerFunction(SType.BOOLEAN, () -> new SBoolean());
        registerFunction(SType.DOUBLE, () -> new SDouble());
        registerFunction(SType.IDENT, () -> new SIdent());
        registerFunction(SType.INTEGER, () -> new SInteger());
        registerFunction(SType.STRING, () -> new SString());

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
    }
}
