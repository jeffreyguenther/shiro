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
}
