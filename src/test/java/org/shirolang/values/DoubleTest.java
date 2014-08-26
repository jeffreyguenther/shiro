/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shirolang.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
    
     @Test
    public void getArgs(){
        SDouble s = new SDouble(2.0);
        assertTrue(s.getArgs().isEmpty());
    }
    
    @Test
    public void hasArgs(){
        SDouble s = new SDouble(-1.1);
        assertFalse(s.hasArgs());
    }
    
    @Test
    public void argCount(){
        SDouble s = new SDouble();
        assertEquals(0, s.getMaxArgs());
        assertEquals(0, s.getMinArgs());
    }
}
