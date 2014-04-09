/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jeffreyguenther
 */
public class NameManagerTest {
    private NameManager manager;
    
    @Before
    public void setup(){
        manager = new NameManager();
    }
    
    @Test
    public void getNextName(){
        Assert.assertEquals(0, manager.getNumberOfInstances("Point"));
        
        Assert.assertEquals("should match", "point1", manager.getNextName("Point"));
        Assert.assertEquals(1, manager.getNumberOfInstances("Point"));
        
        Assert.assertEquals("should match", "point2", manager.getNextName("Point"));
        Assert.assertEquals(2, manager.getNumberOfInstances("Point"));
        
        Assert.assertEquals(0, manager.getNumberOfInstances("Line"));
        
        Assert.assertEquals("should match", "line1", manager.getNextName("Line"));
        Assert.assertEquals(1, manager.getNumberOfInstances("Line"));
        
        Assert.assertEquals("should match", "line2", manager.getNextName("Line"));
        Assert.assertEquals(2, manager.getNumberOfInstances("Line"));
    }
    
    @Test
    public void setInstanceCount(){
        String expectedType = "Point";
        int expectNum = 10;
        manager.setInstanceCount(expectedType,expectNum);
        Assert.assertEquals("should match", 10, manager.getNumberOfInstances(expectedType));
    }
    
    @Test
    public void clear(){
        manager.getNextName("Point");
        manager.getNextName("Point");
        
        manager.getNextName("Line");
        manager.getNextName("Line");
        
        manager.clear();
        
        Assert.assertEquals("should be empty", 0, manager.getNumberOfInstances("Point"));
        Assert.assertEquals("should be empty", 0, manager.getNumberOfInstances("Line"));
    }
}