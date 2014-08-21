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
public class DoubleTest {
    private static final double DELTA = 1e-15;
    
    @Test
    public void getType(){
        SDouble d = new SDouble(15.0);
        assertEquals("Double", d.getType());
    }
    
    @Test(expected = RuntimeException.class)
    public void getValueFail(){
        SDouble d = new SDouble(15.0);
        assertEquals(15.0, d.getValue(), DELTA);
    }
    
    @Test
    public void getValue(){
        SDouble d = new SDouble(15.0);
        d.evaluate();
        assertEquals(15.0, d.getValue(), DELTA);
    }
}
