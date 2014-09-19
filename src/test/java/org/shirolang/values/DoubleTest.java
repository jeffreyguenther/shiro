/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shirolang.values;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * Test a Shiro double
 */
public class DoubleTest {
    private static final double DELTA = 1e-15;
    @Test
    public void constructors(){
        SDouble d = new SDouble();
        org.junit.Assert.assertTrue(d.getSymbolType().isLiteral());

        SDouble d1 = new SDouble(12.3232);
        d1.evaluate();
        org.junit.Assert.assertTrue(d1.getSymbolType().isLiteral());
        org.junit.Assert.assertEquals(12.3232, d1.getValue(), DELTA);

        SDouble d2 = new SDouble("a", 12.3232);
        org.junit.Assert.assertTrue(d2.getSymbolType().isPort());
        org.junit.Assert.assertEquals("a", d2.getName());
    }
    
    
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

    @Test
    public void getResult(){
        SDouble d = new SDouble(12.344);
        d.evaluate();
        assertSame(d, d.getResult());
    }

    @Test
    public void toConsole(){
        SDouble d = new SDouble(0.34);
        d.evaluate();
        org.junit.Assert.assertEquals("0.34", d.toConsole());

        SDouble d1 = new SDouble("a", 12.345);
        d1.evaluate();
        org.junit.Assert.assertEquals("#<Double args:[], results:[12.345]>", d1.toConsole());
    }
}
