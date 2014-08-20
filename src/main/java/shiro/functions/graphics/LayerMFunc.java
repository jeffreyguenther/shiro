package shiro.functions.graphics;

import java.util.ArrayList;
import shiro.functions.*;
import shiro.ResultTuple;
import shiro.Value;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 *
 * @author jeffreyguenther
 */
@SuppressWarnings("unchecked")
public class LayerMFunc implements MultiFunction {
    private static final String NAME = "Layer";
    private static final int CHILDREN = 0;
    
    public LayerMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value rawChildren = arguments.get(CHILDREN);
        ArrayList<Object> values = (ArrayList<Object>) rawChildren.getValue();
        
        Group g = new Group();
        for(Object o: values){
            g.getChildren().add((Node) o);
        }
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, new Value(g, Group.class));
        
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }
    
}
