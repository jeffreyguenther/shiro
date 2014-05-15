package shiro.dag;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A parallel evaluation of a direct acyclic graph
 * @param <T> 
 * @author Ankit Gupta, jeffreyguenther
 */
public class ParallelDAGEvaluator<T> implements DAGEvaluator {
    private PathFinder<T> pathFinder;
    private DAGraph<T> graph;

    public ParallelDAGEvaluator(DAGraph<T> graph) {
        this.graph = graph;
        this.pathFinder = new PathFinder<T>();
    }
    
    /**
     * Parallel update method
     */
    @Override
    public void evaluate() {
        //getting the input ready
        List<List<GraphNode<T>>> allIndependentPaths = pathFinder.getIndependentPathsToRoot(graph.getLeafNodes());
        
        //initializing thread structures
        List<PathUpdater<T>> updaterThreads = new ArrayList<PathUpdater<T>>();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < availableProcessors; i++) {
            updaterThreads.add(new PathUpdater<T>());
        }
        //while the list is not empty
        List<Thread> threads = new ArrayList<Thread>();
        while (!allIndependentPaths.isEmpty()) {
            for (int i = 0; i < availableProcessors; i++) {
                List<GraphNode<T>> nextPath;
                synchronized (this) {
                    if (!allIndependentPaths.isEmpty()) {
                        nextPath = allIndependentPaths.remove(0);
                        updaterThreads.get(i).setPathToUpdate(nextPath);

                        Thread temp = new Thread(updaterThreads.get(i));
                        temp.start();
                        threads.add(temp);
                    }
                }
            }

            for (int i = 0; i < threads.size(); i++) {
               
                try {
                    threads.get(i).join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ParallelDAGEvaluator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
