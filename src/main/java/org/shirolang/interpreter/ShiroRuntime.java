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

import com.google.common.collect.Sets;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Pair;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.SNode;
import org.shirolang.base.SState;
import org.shirolang.dag.DAGraph;
import org.shirolang.exceptions.OptionNotFoundException;

import javax.naming.ldap.PagedResultsControl;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

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

        library.addGraphDefs(definitionCollector.getGraphs());
        library.addNodeDefs(definitionCollector.getNodeDefinitions());
        library.addAlternativeDefs(definitionCollector.getAlternativeDefinitions());

        InlineGraphBuilder inline = new InlineGraphBuilder(library);
        walker.walk(inline, tree);

        inline.setPass(GraphBuilder.SECOND_PASS);
        walker.walk(inline, tree);

        // realize named graphs
        for(Map.Entry<String, ParseTree> graphDef: library.getGraphDefs().entrySet()){
            String graphName = graphDef.getKey();
            ParseTree def = graphDef.getValue();

            // create instance of the new graph
            SGraph g = new SGraph(graphName);

            // create named graph
            GraphBuilder namedGraph = new GraphBuilder(library, g);
            walker.walk(namedGraph, def);

            namedGraph.setPass(GraphBuilder.SECOND_PASS);
            walker.walk(namedGraph, def);

            library.addGraph(g);
        }

        // generate the graphs
        for(Map.Entry<String, ParseTree> altDef: library.getAlternativeDefs().entrySet()){
            String altName = altDef.getKey();
            ParseTree def = altDef.getValue();
        }

        for(ParseTree def: library.getAlternativeDefs().values()){
            StateBuilder stateBuilder = new StateBuilder(library);
            walker.walk(stateBuilder, def);
            library.addState(stateBuilder.getState());
        }

        // if no states are defined
        if(!library.hasUserDefinedStates()){
            if(library.getGraphs().size() == 1 && library.getDefaultGraph().getNodesWithOptions().isEmpty()){
                SState defaultState = library.getDefaultState();
                defaultState.setGraph(library.DEFAULT_GRAPH_NAME);
            }else {

                // generate the states to evaluate the graphs
                Set<SState> sStates = generateAllStates(library.getGraphs());
                sStates.stream().forEach(s -> library.addState(s));
            }
        }

       for(SState state: library.getStates().values()){
           String graphName = state.getGraph();
           SGraph graph = library.getGraph(graphName);

           // only evaluate states that have a graph defined
           if(graph != null) {


               if (!state.getSubjunctTable().isEmpty()) {
                   try {
                       graph.evaluate(state.getSubjunctTable());
                       output.set(graph.toConsole());
                   } catch (OptionNotFoundException e) {
                       throw new RuntimeException("Error during evaluation: " + e.getMessage());
                   }
               } else {
                   graph.evaluate();
                   output.set(graph.toConsole());
               }
           }
       }

        return output.get();

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

    /**
     * Generates the combinatorial space of all alternatives.
     */
    private Set<SState> generateAllStates(Set<SGraph> graphs){
        Set<SState> states = new HashSet<>();

        // for each graph
        for(SGraph g: graphs){
            // get all the nodes with options in a given graph
            Set<SNode> nodesWithOptions = g.getNodesWithOptions();
            if(nodesWithOptions.isEmpty()){
                SState state = new SState(g.getName(), library.getNameManager().generateName("state"));
            }else {

                List<Set<Pair<String, String>>> rawOptions = nodesWithOptions.stream()
                        .map(SNode::getOptionPairs).collect(toList());

                // Calculate the cartesian product
                Set<List<Pair<String, String>>> cartesianProduct = Sets.cartesianProduct(rawOptions);
                // build all possible subjunct tables
                for (List<Pair<String, String>> table : cartesianProduct) {
                    // create a state
                    SState state = new SState(g.getName(), library.getNameManager().generateName("state"));
                    for (Pair<String, String> entry : table) {
                        state.addActiveNode(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return states;
    }
}
