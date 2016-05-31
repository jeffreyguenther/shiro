package org.shirolang.interpreter;

import java.util.Set;

import org.shirolang.base.SFunc;
import org.shirolang.dag.GraphNode;
import org.shirolang.dag.NodeAction;

/**
 * A NodeAction that evaluates function
 */
public class SFuncAction implements NodeAction<SFunc>{

    @Override
    public void doAction(SFunc node, Set<GraphNode<SFunc>> deps) {
        if(node.isActive()) {
            node.evaluate();
        }
    }
}
