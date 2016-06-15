package org.shirolang.playground;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.Background;
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
        setBackground(Background.EMPTY);
    }
    
    /***
     * Get the group representing the drawing.
     * @return the drawing
     */
    public Group getDrawing() {
        return drawing;
    }
}
