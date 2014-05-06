/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.expressions;

import org.junit.Assert;
import org.junit.Test;
import shiro.Value;

/**
 *
 * @author jeffreyguenther
 */
public class ValueTest {
    @Test
    public void getAsDouble(){
        Value v = new Value(3.0, Double.class);
        Assert.assertEquals("should be double", 3, v.getValueAsDouble(), 0);
        Assert.assertEquals("should be float", 3f, v.getValueAsFloat(), 0);
    }
}
