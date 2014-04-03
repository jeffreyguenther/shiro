/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author jeffreyguenther
 */
public class ProductionTest {
    @Test
    public void toCode(){
        String expectProduction = "Point -> p";
        Production p = new Production("Point", "p");
        Assert.assertEquals("should match", expectProduction, p.toCode());
        
        String content = "create a point";
        String productionWithComment = expectProduction + " // "+ content;
        Production pc = new Production("Point", "p", content);
        Assert.assertEquals("should match", productionWithComment, pc.toCode());
    }
    
    @Test
    public void equals(){
        Production p = new Production("Dog", "cat");
        Production p2 = new Production("Dog", "cat");
        Assert.assertEquals("should be equal", p2, p);
        
        Production p3 = new Production("Dog", "cat", "puppies don't like cats");
        Production p4 = new Production("Dog", "cat", "puppies don't like cats");
        Assert.assertEquals("should be equal", p3, p4);
    }
}
