package org.shirolang.playground.editors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.shirolang.functions.geometry.SImage;
import org.shirolang.values.SDouble;
import org.shirolang.values.SString;

/**
 * Render an Image
 */
public class ImageViz extends ImageView{
    public ImageViz(SImage img) {
        super();

        SString file = (SString) img.getInput("file").getResult();
        setImage(new Image(file.getValue()));

        SDouble x = (SDouble) img.getInput("originX").getResult();
        setTranslateX(x.getValue());

        SDouble y = (SDouble) img.getInput("originY").getResult();
        setTranslateY(y.getValue());
    }
}
