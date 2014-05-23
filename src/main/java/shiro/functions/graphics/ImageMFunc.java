/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 *
 * @author jeffreyguenther
 */
public class ImageMFunc implements MultiFunction{
    private static final String NAME = "Image";
    private static final int PATH = 0;

    public ImageMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value originValue = arguments.get(PATH);
        String path = originValue.getValueAsString();
        
        Path p = Paths.get(path);
        
        Image img = new Image("file:" + path);
        ImageView viewer = new ImageView();
        viewer.setImage(img);
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, new Value(viewer, ImageView.class));
        result.setNameforIndex("image", 0);
        return result;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
}