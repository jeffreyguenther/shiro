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
public class BooleanTest {
    @Test
    public void getType(){
        SBoolean b = new SBoolean(true);
        assertEquals("Boolean", b.getType());
    }
    
    @Test(expected = RuntimeException.class)
    public void getValueFail(){
        SBoolean d = new SBoolean(true);
        assertEquals(true, d.getValue());
    }
    
    @Test
    public void getValue(){
        SBoolean t = new SBoolean(true);
        t.evaluate();
        assertEquals(true, t.getValue());
        
        SBoolean f = new SBoolean(false);
        f.evaluate();
        assertEquals(false, f.getValue());
    }
    
     @Test
    public void getArgs(){
        SBoolean s = new SBoolean(true);
        assertTrue(s.getArgs().isEmpty());
    }
    
    @Test
    public void hasArgs(){
        SBoolean s = new SBoolean(false);
        assertFalse(s.hasArgs());
    }
    
    @Test
    public void argCount(){
        SBoolean s = new SBoolean();
        assertEquals(0, s.getMaxArgs());
        assertEquals(0, s.getMinArgs());
    }
    
    @Test
    public void makeLiteral(){
        SBoolean s = new SBoolean();
        assertFalse(s.isLiteral());
        s.makeLiteral();
        assertFalse(s.isLiteral());
    }
}
