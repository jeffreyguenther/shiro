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

package org.shirolang.interpreter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.shirolang.base.SFunc;
import org.shirolang.dag.DAGraph;

/**
 * The Shiro runtime. This class parses and evaluates Shiro expressions.
 * It is the main class for the library.
 */
public class ShiroRuntime{

    private Library library;
    private DAGraph<SFunc> graph = new DAGraph<>();

    private StringProperty errorMesages;
    private StringProperty output;


    public ShiroRuntime() {
        library = new Library();
        errorMesages = new SimpleStringProperty();
        output = new SimpleStringProperty();
    }

    /**
     * Error messages emit by the runtime. In interactive
     * mode, the property is overwritten after each call of
     * executeStatement
     * @return a string property containing the current runtime
     * errors
     */
    public StringProperty errorMessagesProperty(){
        return errorMesages;
    }

    public StringProperty outputProperty(){
        return output;
    }

    public String executeStatement(String input){
        // execute the statement
        // lex
        ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(input));
        // parse
        ShiroParser parser = new ShiroParser(new CommonTokenStream(lex));
        parser.setBuildParseTree(true);
        ParseTree tree = parser.shiro();

        ParseTreeWalker walker = new ParseTreeWalker();
        // walk to get definitions
        // store the definitions in the library as it goes
        DefinitionCollector definitionCollector = new DefinitionCollector();
        walker.walk(definitionCollector, tree);

        // store the definitions in the
//        definitionListener.getAlternativeDefinitions();
//        definitionListener.getGraphs();
        library.addNodeDefs(definitionCollector.getNodeDefinitions());

        ShiroInlineExpressionListener inline = new ShiroInlineExpressionListener(library);
        walker.walk(inline, tree);

        // walk to realize graphs(including anonymous)
//        library.

        // walk to realize states

        library.getDefaultGraph().evaluate();

        SFunc lastLine = inline.getLastLine();
        String outputText = lastLine.toConsole();
        output.set(outputText);
        return outputText;
        // build dependency graph
        // evaluate dep graph
        // get last statement in declaration if there is more than one
        // get it's console output
        // update output stringproperty
        // return output

        // add the result to the history list
            // add command that was processed
            // add graph state
            // capture completed time
    }

    public void evaluate(){
        //clear the existing library
        // lex
        // parse
        // defs
        // realize states
            // realize graphs
                // realize nodes
        // build dependency graph
        // evaluate
    }

    public SFunc executedExpr(String expr) {
        graph.removeAllDependencies();
        ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(expr));
        ShiroParser parser = new ShiroParser(new CommonTokenStream(lex));
        parser.setBuildParseTree(true);
        ParseTree tree = parser.shiro();
        
        ParseTreeWalker walker = new ParseTreeWalker();
        ShiroExpressionListener expression = new ShiroExpressionListener(library);
        walker.walk(expression, tree);
//        List<SFunc> exprs = expression.getExprs();

//        for(SFunc f: exprs){
//            for(SFunc arg: f.getDependencies()){
//                addDependency(f, arg);
//            }
//
//            if(!f.hasArgs()){
//                addDependency(f, null);
//            }
//        }
//
//        TopologicalSort<SFunc> sorter = new TopologicalSort<>(graph);
//        List<GraphNode<SFunc>> topologicalOrdering = sorter.getTopologicalOrdering();
//
//        // loop through all ports to update them.
//        for (GraphNode<SFunc> gn : topologicalOrdering) {
//            gn.doAction();
//        }
//        return expression.getRoot();
        return null;
    }
}
