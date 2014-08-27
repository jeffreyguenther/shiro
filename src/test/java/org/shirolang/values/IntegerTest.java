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
    
    @Test
    public void getArgs(){
        SInteger s = new SInteger(2);
        assertTrue(s.getArgs().isEmpty());
    }
    
    @Test
    public void hasArgs(){
        SInteger s = new SInteger(-1);
        assertFalse(s.hasArgs());
    }
    
    @Test
    public void argCount(){
        SInteger s = new SInteger();
        assertEquals(0, s.getMaxArgs());
        assertEquals(0, s.getMinArgs());
    }
    
    @Test
    public void makeLiteral(){
        SInteger s = new SInteger();
        assertFalse(s.isLiteral());
        s.makeLiteral();
        assertFalse(s.isLiteral());
    }
}
