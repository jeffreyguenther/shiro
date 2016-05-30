package org.shirolang.base;

import org.shirolang.dag.DAGraph;
import org.shirolang.dag.DependencyRelation;
import org.shirolang.dag.GraphNode;
import org.shirolang.dag.TopologicalSort;
import org.shirolang.interpreter.SFuncAction;
import org.shirolang.values.SIdent;

import java.util.List;
import java.util.Set;

/**
 * Walks a DAG and evaluates each node in topological order
 */
public class SEvaluator {
    private DAGraph<SFunc> graph;
    private SFuncAction graphNodeAction = new SFuncAction();

    public SEvaluator() {
        graph = new DAGraph<>();
    }

    public void evaluateGraph(Set<SFunc> ports) {
        graph.removeAllDependencies();
        for(SFunc f: ports){
            if(f.isIdent()){
                SIdent fAsId = (SIdent) f;
                if(!fAsId.isSelector()){
                    if(fAsId.getDependencies().isEmpty()){
                        addDependency(f, null);
                    }else{
                        for (SFunc arg : f.getDependencies()) {
                            addDependency(f, arg);
                        }
                    }
                }
            }else {
                for (SFunc arg : f.getDependencies()) {
                    addDependency(f, arg);
                }

                if (f.getSymbolType().isLiteral()) {
                    addDependency(f, null);
                }

                if (f.getDependencies().isEmpty() && f.getSymbolType().isPort()) {
                    addDependency(f, null);
                }
            }
        }

        TopologicalSort<SFunc> sorter = new TopologicalSort<>(graph);
        List<GraphNode<SFunc>> topologicalOrdering = sorter.getTopologicalOrdering();

        for (GraphNode<SFunc> gn : topologicalOrdering) {
            SFunc func = gn.getValue();
        }

        // loop through all ports to update them.
        for (GraphNode<SFunc> gn : topologicalOrdering) {
            gn.doAction();
        }
    }

    /**
     * Add a dependency between two SFuncs
     *
     * @param dependency dependency relation to be added to the graph
     */
    private void addDependency(DependencyRelation<SFunc> dependency) {
        addDependency(dependency.getDependent(), dependency.getDependedOn());
    }

    /**
     * *
     * Add a dependency between two SFuncs A - depends on -> B
     *
     * @param a the depended on SFunc reference
     * @param b the dependent SFunc reference
     */
    private void addDependency(SFunc a, SFunc b) {
        if (b == null) {
            GraphNode<SFunc> aNode = graph.getNodeForValue(a, graphNodeAction);
            graph.addDependency(aNode, null);
        } else {
            graph.addDependency(graph.getNodeForValue(a, graphNodeAction),
                    graph.getNodeForValue(b, graphNodeAction));
        }
    }

    public Set<GraphNode<SFunc>> getNodes() {
        return graph.getNodes();
    }
}
