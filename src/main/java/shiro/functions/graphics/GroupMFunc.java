package shiro.functions.graphics;

import java.util.ArrayList;
import shiro.functions.*;
import shiro.ResultTuple;
import shiro.Value;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 *
 * @author jeffreyguenther
 */
public class GroupMFunc implements MultiFunction {
    private static final String NAME = "Group";
    private static final int ORIGIN = 0;
    private static final int CHILDREN = 1;
    
    public GroupMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value originValue = arguments.get(ORIGIN);
        Point2D origin = (Point2D) originValue.getValue();
        
        Value rawChildren = arguments.get(CHILDREN);
        ArrayList<Object> values = (ArrayList<Object>) rawChildren.getValue();
        
        Group g = new Group();
        g.relocate(origin.getX(), origin.getY());
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
