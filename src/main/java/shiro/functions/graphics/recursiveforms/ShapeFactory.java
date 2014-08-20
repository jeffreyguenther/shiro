/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.functions.graphics.recursiveforms;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

/**
 * Encapsulates a method to create shape
 * @author jeffreyguenther
 */
public interface ShapeFactory {

    public Shape createShape();

    public static ShapeFactory createTree(double width, double length) {
        return () -> {
            Rectangle r = new Rectangle(20, length);
            r.setStrokeLineCap(StrokeLineCap.BUTT);
            return r;
        };
    }

    public static ShapeFactory createSpikey() {
        return () -> {
            Path p = new Path();
            p.getElements().add(new MoveTo(0, 0));
            p.getElements().add(new LineTo(0, -75));
            p.getElements().add(new LineTo(45, -15));
            p.getElements().add(new LineTo(155, 0));
            p.setStrokeWidth(3);
            p.setStrokeLineJoin(StrokeLineJoin.ROUND);
            p.setStrokeLineCap(StrokeLineCap.BUTT);
            return p;
        };
    }
}
