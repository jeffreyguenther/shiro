/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shirolang.playground;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

/**
 * This class defines a Canvas object. A canvas for drawing on.
 * @author jeffreyguenther
 */
public class Canvas extends Pane{
    private Group drawing;

    public Canvas() {
        drawing = new Group();
        this.getChildren().add(drawing);

        setPadding(new Insets(10));
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }
    
    /***
     * Get the group representing the drawing.
     * @return the drawing
     */
    public Group getDrawing() {
        return drawing;
    }
}
