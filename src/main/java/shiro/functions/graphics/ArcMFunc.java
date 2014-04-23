/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 *
 * @author jeffreyguenther
 */
public class ArcMFunc implements MultiFunction{
    private static final String NAME = "Arc";
    private static final int CENTER = 0;
    private static final int RADIUS = 1;
    private static final int START_ANGLE = 2;
    private static final int END_ANGLE = 3;
    private static final int STROKE = 4;
    private static final int STROKE_WIDTH = 5;

    public ArcMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value centerValue = arguments.get(CENTER);
        Point2D center = (Point2D) centerValue.getValue();
        
        Value radiusValue = arguments.get(RADIUS);
        double radius = radiusValue.getValueAsDouble();
        
        Value startAngleValue = arguments.get(START_ANGLE);
        double startAngle = startAngleValue.getValueAsDouble();
        
        Value endAngleValue = arguments.get(END_ANGLE);
        double endAngle = endAngleValue.getValueAsDouble();
        
        Arc c = new Arc();
        c.setCenterX(center.getX());
        c.setCenterY(center.getY());
        c.setRadiusX(radius);
        c.setRadiusY(radius);
        c.setStartAngle(startAngle);
        c.setLength(endAngle);
        c.setType(ArcType.OPEN);
        c.setFill(Color.TRANSPARENT);
        
        if(arguments.size() >= STROKE + 1){
            Value strokeValue = arguments.get(STROKE);
            String stroke = strokeValue.getValueAsString();
            c.setStroke(Color.web("rgb("+ stroke +")"));
        }
        
        if(arguments.size() >= STROKE_WIDTH + 1){
            Value strokeWidthValue = arguments.get(STROKE_WIDTH);
            double stroke = strokeWidthValue.getValueAsDouble();
            c.setStrokeWidth(stroke);
        }
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, new Value(c, Arc.class));
        result.setNameforIndex("arc", 0);
        return result;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}
