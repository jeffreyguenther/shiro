package shiro.dag;

import java.util.List;

/**
 *
 * @param <T> 
 * @author Ankit Gupta, Jeffrey Guenther
 */
public class PathUpdater<T> implements Runnable{
    private List<GraphNode<T>> pathToUpdate = null;

    public void setPathToUpdate(List<GraphNode<T>> portPath) {
        pathToUpdate = portPath;
    }
    
    /**
     * Get the count of no yet updated dependencies
     * @param node graph node to check
     * @return the number of dependencies not yet updated
     */
    private int getUnupdatedDependencies(GraphNode<T> node){
        int counter = 0;
        for(GraphNode<T> dp: node.getNodesDependedOn()){
            if(dp.isUpdated()){
                counter++;
            }
        }
        
        return counter;
    }

    @Override
    public void run() {
        for (GraphNode<T> n : pathToUpdate) {
            synchronized (n) {
                if (!n.isUpdated() && n.isActive() && getUnupdatedDependencies(n) == 0) {
                    // do the action stored in the node
                    n.doAction();
                }
            }
        }
    }
}
