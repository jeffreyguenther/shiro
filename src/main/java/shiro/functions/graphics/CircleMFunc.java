/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 *
 * @author jeffreyguenther
 */
public class CircleMFunc implements MultiFunction{
    private static final String NAME = "Circle";
    private static final int CENTER = 0;
    private static final int RADIUS = 1;
    private static final int STROKE = 2;
    private static final int STROKE_WIDTH = 3;
    private static final int FILL = 4;

    public CircleMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value centerValue = arguments.get(CENTER);
        Point2D center = (Point2D) centerValue.getValue();
        
        Value radiusValue = arguments.get(RADIUS);
        double radius = radiusValue.getValueAsDouble();
        
        Circle c = new Circle();
        c.setCenterX(center.getX());
        c.setCenterY(center.getY());
        c.setRadius(radius);
        
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
        
        if(arguments.size() >= FILL + 1){
            Value fill = arguments.get(FILL);
            String color = fill.getValueAsString();
            c.setFill(Color.web("rgb("+ color +")"));
        }
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, new Value(c, Circle.class));
        result.setNameforIndex("circle", 0);
        return result;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}
