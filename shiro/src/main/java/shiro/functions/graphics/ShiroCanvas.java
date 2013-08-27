/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.functions.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author jeffreyguenther
 */
public class ShiroCanvas extends JComponent {
    private List<Shape> shapes;
    
    public ShiroCanvas(List<Shape> shapes) {
        super();
        this.shapes = shapes;
        setName("Canvas");
        setSize(400, 400);
    }
    
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        
        Graphics2D g = (Graphics2D) grphcs;
        
        g.setPaint( Color.BLACK);
        g.setStroke(new BasicStroke(5f));
        
        for(Shape s: shapes){
            g.draw(s);
        }
        
        
    }
}
