package shiro.functions.graphics;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * A multi-function that draws shapes in a window
 * @author jeffreyguenther
 */
public class CanvasMFunc implements MultiFunction{
    private List<Shape> shapes;
    
    public CanvasMFunc() {
        shapes = new ArrayList<Shape>();
    }
    
    public static void createAndShowGUI(List<Shape> shapes) {
        JFrame frame = new JFrame("Test Canvas");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( new ShiroCanvas(shapes));
        frame.setVisible(true);
    }

    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        shapes = new ArrayList<Shape>();
        
        
        for(Value v: arguments){
            shapes.add((Shape) v.getValue());
        }
        
        Runnable doCreateAndShowGUI = new Runnable() {
            @Override
            public void run() {
                createAndShowGUI(shapes);
            }
        };
        SwingUtilities.invokeLater(doCreateAndShowGUI);
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, Value.createInteger(shapes.size()));
        
        
        return result;
        
    }

    @Override
    public String getName() {
        return "Canvas";
    }
    
   
}
