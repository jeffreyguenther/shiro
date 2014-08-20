/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import org.junit.Assert;
import org.junit.Test;
import shiro.expressions.Path;

/**
 *
 * @author jeffreyguenther
 */
public class PortAssignmentTest {
    @Test
    public void toCode(){
        String expected = "startPoint.x(100)";
        PortAssignment a = new PortAssignment(Path.createPath("startPoint.x"), 
                new shiro.expressions.SNumber(100d));
        Assert.assertEquals("should match", expected, a.toCode());
    }
    
    @Test
    public void equals(){
        PortAssignment a = new PortAssignment(Path.createPath("startPoint.x"), 
                new shiro.expressions.SNumber(100d));
        
        PortAssignment b = new PortAssignment(Path.createPath("startPoint.x"), 
                new shiro.expressions.SNumber(100d));
        
        PortAssignment c = new PortAssignment(Path.createPath("startPoint.y"), 
                new shiro.expressions.SNumber(100d));
        
        Assert.assertEquals(a, b);
        Assert.assertFalse(a.equals(c));
    }
}
