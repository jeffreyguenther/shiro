package shiro;

import java.util.Set;
import shiro.dag.GraphNode;
import shiro.dag.NodeAction;

/**
 * Action for evaluating port
 * @author jeffreyguenther
 */
public class PortAction implements NodeAction<Port> {
    
    public PortAction(){

    }

    @Override
    public void doAction(Port p, Set<GraphNode<Port>> deps) {
        p.evaluate();
    }
}
