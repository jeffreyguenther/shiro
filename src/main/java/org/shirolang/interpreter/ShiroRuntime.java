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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.util.Callback;
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
import org.shirolang.playground.editors.DoubleViz;
import org.shirolang.values.SDouble;

import javax.naming.ldap.PagedResultsControl;
import java.util.*;

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
    private BooleanProperty hasErrors;

    private Map<String, Callback<SFunc, Node>> visualCallBacks;

    public ObservableList<Node> nodes;

    public ShiroRuntime() {
        library = new Library();
        errorMesages = new SimpleStringProperty("");
        output = new SimpleStringProperty("");
        hasErrors = new SimpleBooleanProperty(false);

        visualCallBacks = new HashMap<>();
        nodes = FXCollections.observableArrayList();
    }

    public void mapCallBack(String type, Callback<SFunc, Node> cb ){
        visualCallBacks.put(type, cb);
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

    /**
     * "Standard out for the runtime." Runtime messages are appended
     * to the property as the it runs
     * @return the string property containing the content of standard out
     */
    public StringProperty outputProperty(){
        return output;
    }

    /**
     * Indicates whether runtime has errors or not.
     * @return the boolean property with value true if there are errors, otherwise
     * false
     */
    public BooleanProperty hasErrorProperty(){
        return hasErrors;
    }


    public String executeStatement(String input){
        library.reset();
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

        if(!library.hasUserDefinedGraphs() && !library.hasUserDefinedStates()){
            SState defaultState = library.getDefaultState();
            defaultState.setGraph(library.DEFAULT_GRAPH_NAME);

        }else if (!library.hasUserDefinedStates()){ // has a graph (includes default), but no states
            // generate the states to evaluate the graphs
            Set<SState> sStates = generateAllStates(library.getGraphs());
            sStates.stream().forEach(s -> library.addState(s));
        }

       for(SState state: library.getStates().values()){
           String graphName = state.getGraph();
           SGraph graph = library.getGraph(graphName);

           // only evaluate states that have a graph defined
           if (!state.getSubjunctTable().isEmpty()) {
               try {
                   graph.evaluate(state.getSubjunctTable());
                   sendToOutput(graph.toConsole());
               } catch (OptionNotFoundException e) {
                   throw new RuntimeException("Error during evaluation: " + e.getMessage());
               }
           } else {
               graph.evaluate();
               sendToOutput(graph.toConsole());
           }
       }


        // for each type in the runtime
        for(String type: library.getTypeNames()){
            List<SFunc> collect = library.getDefaultGraph().getPorts().stream().filter(p -> p.getType().equals(type)).collect(toList());
            for (SFunc sFunc : collect) {
                Callback<SFunc, Node> callback = visualCallBacks.get(type);
                if(callback != null) {
                    nodes.add(callback.call(sFunc));
                }
            }
        }

        return output.get();
    }

    /**
     * Appends the passed string to the output property
     * if there is more than one state defined.
     * @param s string to append to output
     */
    private void sendToOutput(String s){
        if(library.getStates().size() > 1){
            String prev = output.get();
            String out;
            if(!prev.isEmpty()){
                out = prev + "\n" + s;
            }else{
                out = s;
            }

            output.set(out);
        }else{
            output.set(s);
        }
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
                SState state = new SState(library.getNameManager().generateName("state"), g.getName());
                states.add(state);
            }else {

                List<Set<Pair<String, String>>> rawOptions = nodesWithOptions.stream()
                        .map(SNode::getOptionPairs).collect(toList());

                // Calculate the cartesian product
                Set<List<Pair<String, String>>> cartesianProduct = Sets.cartesianProduct(rawOptions);
                // build all possible subjunct tables
                for (List<Pair<String, String>> table : cartesianProduct) {
                    // create a state
                    SState state = new SState(library.getNameManager().generateName("state"), g.getName());
                    for (Pair<String, String> entry : table) {
                        state.addActiveNode(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return states;
    }
}
