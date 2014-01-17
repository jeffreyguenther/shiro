package shiro.dag;

import java.util.Set;

/**
 * Describes what a graph node can do.
 * This sort of event occurs when the node is visited during a traversal
 * The action is parameterized to allow graph nodes to be used without
 * casting.
 * @param <T> Type of the action
 * @author jeffreyguenther
 */
public interface NodeAction<T> {
    /**
     * Specifies what the node can do when it is visited.
     * @param deps the set of graph node to be used in the action
     */
    public void doAction(T t, Set<GraphNode<T>> deps );
}
