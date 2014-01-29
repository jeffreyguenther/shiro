package shiro.functions.graphics;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

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
        
        Line line = LineBuilder.create()
                .startX(p1.getX())
                .startY(p1.getY())
                .endX(p2.getX())
                .endY(p2.getY())
                .build();
        
        // create a line 
//        Line2D line = new Line2D.Float(p1, p2);
        Value lineValue = new Value(line, Line.class);
        
        Double length = (double) p1.distance(p2);
        Value lengthValue = new Value(length, Double.class);
        
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
