package shiro.functions.graphics;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * A port for multiplying the values of each of a ports dependents
 * @author jeffreyguenther
 */
public class PointMFunc implements MultiFunction{
    private static final String NAME = "Point";
    private static final int X = 0;
    private static final int Y = 1;
    
    public PointMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        
        float x = arguments.get(X).getValueAsFloat();
        float y = arguments.get(Y).getValueAsFloat();
        
        Point2D point = new Point2D.Float(x, y);
        Value pointValue = new Value(point, Point2D.class);
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, pointValue);
        
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }
    
}
