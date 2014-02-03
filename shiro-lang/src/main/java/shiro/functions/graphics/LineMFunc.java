package shiro.functions.graphics;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * A port for multiplying the values of each of a ports dependents
 * @author jeffreyguenther
 */
public class LineMFunc implements MultiFunction{
    private static final String NAME = "Line";
    private static final int P1 = 0;
    private static final int P2 = 1;
    
    public LineMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value p1Value = arguments.get(P1);
        Value p2Value = arguments.get(P2);
        
        Point2D p1 = (Point2D) p1Value.getValue();
        Point2D p2 = (Point2D) p2Value.getValue();
        
        // create a line 
        Line2D line = new Line2D.Float(p1, p2);
        Value lineValue = new Value(line, Line2D.class);
        
        Float length = (float) p1.distance(p2);
        Value lengthValue = new Value(length, Float.class);
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, lineValue);
        result.setValueForIndex(1, lengthValue);
        result.setNameforIndex("line", 0);
        result.setNameforIndex("length", 1);
        
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }
    
}
