package shiro.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import shiro.dag.GraphNode;
import shiro.dag.NodeAction;

/**
 * Basic integer addition node action
 *
 * @param <T>
 * @author jeffreyguenther
 */
public class IntegerAddAction implements NodeAction<Integer> {
    // A map of node -> node value
    // This is needed because all graph nodes contain the same reference to an a
    // action.
    private Map<Integer, Integer> result;

    public IntegerAddAction() {
        result = new HashMap<Integer, Integer>();
    }

    public Map<Integer, Integer> getResult() {
        return result;
    }

    @Override
    public void doAction(Integer t, Set<GraphNode<Integer>> deps) {
        Integer v = t;
        
        int sum = 0;

        if (!deps.isEmpty()) {
            for (GraphNode<Integer> node : deps) {
                // get the node name
                Integer n = (Integer) node.getValue();
                
                // get the value of the node
                Integer valueOfN = result.get(n);
                // if there is already a value stored
                if(valueOfN != null){
                    // add the value of the node to the sum
                    sum += valueOfN;
                }else{
                    // else add node name to the sum
                    sum += n;
                }
            }
        }
        
        sum += v;
        
        // put the 
        result.put(v, sum);
    }
}
