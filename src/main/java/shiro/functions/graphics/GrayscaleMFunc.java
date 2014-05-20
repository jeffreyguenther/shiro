package shiro.functions.graphics;

import shiro.functions.*;
import shiro.ResultTuple;
import shiro.Value;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author jeffreyguenther
 */
public class GrayscaleMFunc implements MultiFunction {
    private static final String NAME = "Desaturate";
    private static final int COLOR = 0;
    
    public GrayscaleMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value originalColor = arguments.get(COLOR);
        String s = (String) originalColor.getValue();
        
        Color c = Color.web("rgb(" + s + ")");
        
        Color d = c.grayscale();
        int red = (int) Math.floor(d.getRed() * 255);
        int green = (int) Math.floor(d.getGreen()* 255);
        int blue = (int) Math.floor(d.getBlue() * 255);
        String colorString = "" + red  + "," + green + "," + blue;
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, new Value(colorString, String.class));
        
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }
    
}
