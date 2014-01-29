package shiro.functions.graphics;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;
import java.util.List;
import javafx.geometry.Point2D;

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
        
        double x = arguments.get(X).getValueAsDouble();
        double y = arguments.get(Y).getValueAsDouble();
        
        Point2D point = new Point2D(x,y);
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
