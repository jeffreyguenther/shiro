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
import org.shirolang.interpreter.ShiroExpressionListener;
import org.shirolang.interpreter.ShiroLexer;
import org.shirolang.interpreter.ShiroParser;

/**
 *
 * @author jeffreyguenther
 */
public class ShiroRuntime implements Scope{
    private Map<String, SFuncBase> symbols;
    private DAGraph<SFunc> graph = new DAGraph<>();
    private SFuncAction graphNodeAction = new SFuncAction();

    public ShiroRuntime() {
        symbols = new HashMap<>();
    }
    
    public void addSymbol(String s, SFuncBase v){
        symbols.put(s, v);
    }

    @Override
    public SFuncBase resolvePath(String s) {
        return symbols.get(s);
    }

    public SFunc executedExpr(String expr) {
        graph.removeAllDependencies();
        ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(expr));
        ShiroParser parser = new ShiroParser(new CommonTokenStream(lex));
        ParseTree tree = parser.expr();
        
        ParseTreeWalker walker = new ParseTreeWalker();
        ShiroExpressionListener expression = new ShiroExpressionListener();
        walker.walk(expression, tree);
        List<SFunc> exprs = expression.getExprs();
        
        for(SFunc f: exprs){
            for(SFunc arg: f.getArgs()){
                addDependency(f, arg);
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
