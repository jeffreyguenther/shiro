/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shirolang.values;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author jeffreyguenther
 */
public class IntegerTest {
    @Test
    public void getType(){
        SInteger d = new SInteger(10);
        assertEquals("Integer", d.getType());
    }
    
    @Test(expected = RuntimeException.class)
    public void getValueFail(){
        SInteger d = new SInteger(10);
        assertEquals(10, (int) d.getValue());
    }
    
    @Test
    public void getValue(){
        SInteger d = new SInteger(10);
        d.evaluate();
        assertEquals(10, (int) d.getValue());
        
        SInteger nd = new SInteger(-10);
        nd.evaluate();
        assertEquals(-10, (int) nd.getValue());
    }
}
