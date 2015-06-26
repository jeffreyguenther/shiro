/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shirolang.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Tests a Shiro integer
 */
public class IntegerTest {
    @Test
    public void constructors(){
        SInteger i = new SInteger();
        Assert.assertTrue(i.getSymbolType().isLiteral());

        SString i1 = new SString("Test");
        i1.evaluate();
        Assert.assertTrue(i1.getSymbolType().isLiteral());
        Assert.assertEquals("Test", i1.getValue());

        SInteger s2 = new SInteger("a", 12);
        Assert.assertTrue(s2.getSymbolType().isPort());
        Assert.assertEquals("a", s2.getName());
    }

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
        assertTrue(s.getInputs().isEmpty());
    }
    
    @Test
    public void hasArgs(){
        SInteger s = new SInteger(-1);
        assertFalse(s.hasInputs());
    }
    
    @Test
    public void argCount(){
        SInteger s = new SInteger();
        assertEquals(0, s.getMaxArgs());
        assertEquals(0, s.getMinArgs());
    }

    @Test
    public void toConsole(){
        SInteger i = new SInteger(12);
        i.evaluate();
        Assert.assertEquals("12", i.toConsole());

        SInteger i2 = new SInteger("a", 12);
        i2.evaluate();
        Assert.assertEquals("#<Integer args:[], results:[12]>", i2.toConsole());
    }
}
