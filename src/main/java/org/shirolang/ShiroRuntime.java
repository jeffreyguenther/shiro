/*
 * The MIT License
 *
 * Copyright (c) 2012 - 2014 Jeffrey Guenther.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.shirolang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.shirolang.dag.DAGraph;
import org.shirolang.dag.DependencyRelation;
import org.shirolang.dag.GraphNode;
import org.shirolang.dag.TopologicalSort;
import org.shirolang.functions.math.SAdd;
import org.shirolang.functions.math.SAnd;
import org.shirolang.functions.math.SDivide;
import org.shirolang.functions.math.SEqual;
import org.shirolang.functions.math.SGreaterThan;
import org.shirolang.functions.math.SGreaterThanOrEqual;
import org.shirolang.functions.math.SLessThan;
import org.shirolang.functions.math.SLessThanOrEqual;
import org.shirolang.functions.math.SModulo;
import org.shirolang.functions.math.SMultiply;
import org.shirolang.functions.math.SNegative;
import org.shirolang.functions.math.SNot;
import org.shirolang.functions.math.SNotEqual;
import org.shirolang.functions.math.SOr;
import org.shirolang.functions.math.SPower;
import org.shirolang.functions.math.SSubtract;
import org.shirolang.interpreter.ShiroExpressionListener;
import org.shirolang.interpreter.ShiroLexer;
import org.shirolang.interpreter.ShiroParser;
import org.shirolang.values.SBoolean;
import org.shirolang.values.SDouble;
import org.shirolang.values.SIdent;
import org.shirolang.values.SInteger;
import org.shirolang.values.SString;

/**
 *
 * @author jeffreyguenther
 */
public class ShiroRuntime implements Scope{
    private Map<String, SFunc> symbols;
    private DAGraph<SFunc> graph = new DAGraph<>();
    private SFuncAction graphNodeAction = new SFuncAction();
    private Map<String, FunctionFactory> mfuncs;

    public ShiroRuntime() {
        symbols = new HashMap<>();
        mfuncs = new HashMap<>();
        loadRuntimeFunctions();
    }
    
    public final void registerFunction(String name, FunctionFactory f){
        mfuncs.put(name, f);
    }
    
    public SFunc createFunction(String name){
        return mfuncs.get(name).create();
    }
    
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
    
    public void addSymbol(String s, SFunc v){
        symbols.put(s, v);
    }

    @Override
    public SFunc resolvePath(String s) {
        return symbols.get(s);
    }

    public SFunc executedExpr(String expr) {
        graph.removeAllDependencies();
        ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(expr));
        ShiroParser parser = new ShiroParser(new CommonTokenStream(lex));
        ParseTree tree = parser.shiro();
        
        ParseTreeWalker walker = new ParseTreeWalker();
        ShiroExpressionListener expression = new ShiroExpressionListener(this);
        walker.walk(expression, tree);
        List<SFunc> exprs = expression.getExprs();
        
        for(SFunc f: exprs){
            for(SFunc arg: f.getArgs()){
                addDependency(f, arg);
            }
            
            if(!f.hasArgs()){
                addDependency(f, null);
            }
        }
        
        TopologicalSort<SFunc> sorter = new TopologicalSort<>(graph);
        List<GraphNode<SFunc>> topologicalOrdering = sorter.getTopologicalOrdering();

        // loop through all ports to update them.
        for (GraphNode<SFunc> gn : topologicalOrdering) {
            gn.doAction();
        }
        
//        System.out.println(graph.getLeafNodes());
        return expression.getRoot();
    }
    
    /**
     * Add a dependency between two SFuncs
     *
     * @param graph
     * @param dependency dependency relation to be added to the graph
     */
    private void addDependency(DependencyRelation<SFunc> dependency) {
        addDependency(dependency.getDependent(), dependency.getDependedOn());
    }
    
    /**
     * *
     * Add a dependency between two SFuncs A - depends on -> B
     *
     * @param graph
     * @param a the depended on SFunc reference
     * @param b the dependent SFunc reference
     */
    private void addDependency(SFunc a, SFunc b) {

        if (b == null) {
            graph.addDependency(new GraphNode<>(a, graphNodeAction), null);
        } else {
            graph.addDependency(graph.getNodeForValue(a, graphNodeAction),
                    graph.getNodeForValue(b, graphNodeAction));
        }
    }
}
