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
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.util.Callback;
import javafx.util.Pair;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.SNode;
import org.shirolang.base.SState;
import org.shirolang.dag.DAGraph;
import org.shirolang.dag.DependencyRelation;
import org.shirolang.dag.GraphNode;
import org.shirolang.dag.TopologicalSort;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.functions.geometry.SGroup;
import org.shirolang.playground.editors.GroupViz;
import org.shirolang.values.SList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * The Shiro runtime. This class parses and evaluates Shiro expressions.
 * It is the main class for the library.
 */
public class ShiroRuntime{
    private Library library;
    private StringProperty errorMesages;
    private StringProperty output;
    private BooleanProperty hasErrors;

    private Map<String, Callback<SFunc, Node>> visualCallBacks;

    public ObservableMap<String, Set<Node>> nodes;

    public ShiroRuntime() {
        library = new Library();
        errorMesages = new SimpleStringProperty("");
        output = new SimpleStringProperty("");
        hasErrors = new SimpleBooleanProperty(false);

        visualCallBacks = new HashMap<>();
        nodes = FXCollections.observableHashMap();
    }

    public Library getLibrary() {
        return library;
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


    public String executeFile(Path file) throws IOException {
        library.reset();

        return eval(file, library.lex(file));
    }

    private String eval(Path rootPath, CommonTokenStream tokens){
        output.set("");
        nodes.clear();

        List<Path> dependencies = resolveDependencies(rootPath, tokens);
        loadDependencies(dependencies);
        loadMainCode(rootPath);

        ParseTreeWalker walker = new ParseTreeWalker();
        ParseTree tree = library.getParseTree(rootPath);
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
            defaultState.setGraph(Library.DEFAULT_GRAPH_NAME);

        }else if (!library.hasUserDefinedStates()){ // has a graph (includes default), but no states
            // generate the states to evaluate the graphs
            Set<SState> sStates = generateAllStates(library.getGraphs());
            sStates.stream().forEach(library::addState);
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

            // render the current state using the callbacks
//            Set<Node> visualized = new HashSet<>();
            Map<SFunc, Node> visualized = new HashMap<>();
            for(String type: library.getTypeNames()){
                List<SFunc> collect = graph.getPorts().stream().filter(p -> p.getType().equals(type)).collect(toList());

                for (SFunc sFunc : collect) {
                    if (!type.equals("Group")) {
                        Callback<SFunc, Node> callback = visualCallBacks.get(type);
                        if (callback != null) {
//                        visualized.add(callback.call(sFunc));
                            visualized.computeIfAbsent(sFunc, callback::call);
                            System.out.println("rendering " + type);
                        }

                    } else { // if the type is a "Group"
                        // Render the group
                        SGroup group = (SGroup) sFunc;
                        Callback<SFunc, Node> groupCallback = visualCallBacks.get("Group");
                        GroupViz groupViz = (GroupViz) groupCallback.call(group);

                        // Render each of the children
                        SList args = (SList) group.getInput("children").getResult();
                        List<SFunc> shapes = args.getValue().stream().map(SFunc::getResult).collect(toList());
                        shapes.forEach((s) ->{
                            Callback<SFunc, Node> callback = visualCallBacks.get(s.getType());
                            groupViz.getChildren().add(visualized.computeIfAbsent(s, callback::call));
                            visualized.remove(s);
                        });

                        visualized.put(sFunc, groupViz);
                    }
                }
            }
            nodes.put(state.getName(), new HashSet<>(visualized.values()));
        }

        return output.get();
    }

    /**
     * Executes a string containing a Shiro program
     * @param input a string of Shiro code
     * @return the console output after the evaluation
     */
    public String executeStatement(String input){
        library.reset();

        // create a dummy file path for the program
        Path rootPath = Paths.get("root.sro");

        // lex
        CommonTokenStream tokens = library.lex(input);

        return eval(rootPath, tokens);
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
     * Gets all of the states known by the runtime
     * Delegates the to Library.getStates()
     * @return the set of states
     */
    public Set<SState> getStates(){
        return new HashSet<>(library.getStates().values());
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
                    states.add(state);
                }
            }
        }
        return states;
    }

    /**
     * Resolves the file dependencies for the given Shiro program
     * @param rootFile path to the the Shiro program to resolve
     * @param tokens tokens for the Shiro program
     * @return list of files in dependency order
     */
    private List<java.nio.file.Path> resolveDependencies(java.nio.file.Path rootFile, CommonTokenStream tokens){
        // lookup path in cache to see if it has already been parsed
        ParseTree tree = library.getParseTree(rootFile);
        if(tree == null){
            tree = library.parse(tokens);
            library.saveParseResult(rootFile, tokens, tree);
        }

        CodeImporter codeImporter = new CodeImporter(library, rootFile);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(codeImporter, tree);

        DAGraph<java.nio.file.Path> graph = new DAGraph<>();
        for (DependencyRelation<Path> dep : codeImporter.getSourceFiles()) {
            graph.addDependency(graph.getNodeForValue(dep.getDependent(), null), graph.getNodeForValue(dep.getDependedOn(), null));
        }

        TopologicalSort<java.nio.file.Path> topoSort = new TopologicalSort<>(graph);
        List<GraphNode<Path>> topologicalOrdering = topoSort.getTopologicalOrdering();
        List<java.nio.file.Path> sortedPaths = topologicalOrdering.stream().map(GraphNode::getValue).collect(toList());
        sortedPaths.remove(rootFile);

        return sortedPaths;
    }

    /**
     * Parses code at a list of paths and recursively gathers all of
     * the node definitions
     * @param deps the list of paths from which to gather the nodes
     */
    private void loadDependencies(List<java.nio.file.Path> deps){
        for(java.nio.file.Path p: deps){

            ParseTree parseTree = library.getParseTree(p);

            // walk to get definitions
            // store the definitions in the library as it goes
            ParseTreeWalker walker = new ParseTreeWalker();
            DefinitionCollector definitionCollector = new DefinitionCollector();
            walker.walk(definitionCollector, parseTree);

            library.addNodeDefs(definitionCollector.getNodeDefinitions());
        }
    }

    /**
     * Loads the nodes, graphs, and states defined in the Shiro program
     * @param p path to the Shiro program to be loaded
     */
    private void loadMainCode(Path p){
        ParseTree parseTree = library.getParseTree(p);

        // walk to get definitions
        // store the definitions in the library as it goes
        ParseTreeWalker walker = new ParseTreeWalker();
        DefinitionCollector definitionCollector = new DefinitionCollector();
        walker.walk(definitionCollector, parseTree);

        library.addGraphDefs(definitionCollector.getGraphs());
        library.addNodeDefs(definitionCollector.getNodeDefinitions());
        library.addAlternativeDefs(definitionCollector.getAlternativeDefinitions());
    }
}
