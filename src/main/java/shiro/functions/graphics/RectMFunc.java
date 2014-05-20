/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 *
 * @author jeffreyguenther
 */
public class RectMFunc implements MultiFunction{
    private static final String NAME = "Rectangle";
    private static final int ORIGIN = 0;
    private static final int WIDTH = 1;
    private static final int HEIGHT = 2;
    private static final int STROKE = 3;
    private static final int STROKE_WIDTH = 4;
    private static final int FILL = 5;

    public RectMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value originValue = arguments.get(ORIGIN);
        Point2D origin = (Point2D) originValue.getValue();
        
        Value widthValue = arguments.get(WIDTH);
        double width = widthValue.getValueAsDouble();
        
        Value heightValue = arguments.get(HEIGHT);
        double height = heightValue.getValueAsDouble();
        
        Rectangle r = new Rectangle();
        r.setX(origin.getX());
        r.setY(origin.getY());
        r.setWidth(width);
        r.setHeight(height);
        
        if(arguments.size() >= STROKE + 1){
            Value strokeValue = arguments.get(STROKE);
            String stroke = strokeValue.getValueAsString();
            r.setStroke(Color.web("rgb("+ stroke +")"));
        }
        
        if(arguments.size() >= STROKE_WIDTH + 1){
            Value strokeWidthValue = arguments.get(STROKE_WIDTH);
            double stroke = strokeWidthValue.getValueAsDouble();
            r.setStrokeWidth(stroke);
        }
        
        if(arguments.size() >= FILL + 1){
            Value fill = arguments.get(FILL);
            String color = fill.getValueAsString();
            r.setFill(Color.web("rgb("+ color +")"));
        }
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, new Value(r, Rectangle.class));
        result.setNameforIndex("rectangle", 0);
        return result;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}