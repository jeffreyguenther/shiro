/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.expressions;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Test a path expression object
 * @author jeffreyguenther
 */
public class PathTest {
    @Test
    public void equals(){
        List<String> elements = new ArrayList<>();
        elements.add("P1");
        elements.add("x");
        
        List<String> elements2 = new ArrayList<>();
        elements2.add("P1");
        elements2.add("x");
        Path p1  = new Path(null, elements);
        Path p2  = new Path(null, elements2);
        
        Assert.assertEquals("paths should be equal", true, p1.equals(p2));
    }
}
