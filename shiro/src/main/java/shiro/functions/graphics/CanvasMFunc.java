package shiro.functions.graphics;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * A multi-function that draws shapes in a window
 * @author jeffreyguenther
 */
public class CanvasMFunc extends JComponent implements MultiFunction{
    private List<Shape> shapes;
    
    public CanvasMFunc() {
        setName("Canvas");
        shapes = new ArrayList<Shape>();
    }
    

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        
        Graphics2D g = (Graphics2D) getGraphics();
        
        for(Shape s: shapes){
            g.draw(s);
        }
    }
    
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Test Canvas");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new CanvasMFunc());
        frame.setVisible(true);
    }

    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        for(Value v: arguments){
            shapes.add((Shape) v.getValue());
        }
        
        Runnable doCreateAndShowGUI = new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        };
        SwingUtilities.invokeLater(doCreateAndShowGUI);
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, Value.createInteger(shapes.size()));
        return result;
        
    }
    
   
}
