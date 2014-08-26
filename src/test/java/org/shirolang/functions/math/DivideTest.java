package org.shirolang.functions.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.shirolang.values.SDouble;
import org.shirolang.values.SInteger;
import org.shirolang.values.SString;

/*
 * The MIT License
 *
 * Copyright (c) 2012 - 2014 Jeffrey Guenther.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 *
 * @author jeffreyguenther
 */
public class DivideTest {
    @Test
    public void getType(){
        SDivide s = new SDivide(null, null);
        assertEquals("Should be 'Divide'", "Divide", s.getType());
    }
    
    @Test(expected= RuntimeException.class)
    public void divByZero(){
        SDouble a = new SDouble(13.0);
        SDouble b = new SDouble(0.0);
        SDivide quotient = new SDivide(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        quotient.evaluate();
        
        SDouble r = (SDouble) quotient.getResult();
        assertTrue(r.isDouble());
        assertEquals(0.0, r.getValue(), 1e-16);
    }
    
    @Test
    public void divDoubles(){
        SDouble a = new SDouble(13.0);
        SDouble b = new SDouble(-0.34);
        SDivide quotient = new SDivide(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        quotient.evaluate();
        
        SDouble r = (SDouble) quotient.getResult();
        assertTrue(r.isDouble());
        assertEquals(-38.23529411764706, r.getValue(), 1e-16);
    }
    
    @Test
    public void divInts(){
        SInteger a = new SInteger(13);
        SInteger b = new SInteger(-1);
        SDivide quotient = new SDivide(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        quotient.evaluate();
        
        SInteger r = (SInteger) quotient.getResult();
        assertTrue(quotient.getResult().isInteger());
        assertEquals(-13, (int)r.getValue());
    }
    
    @Test(expected= RuntimeException.class)
    public void divDoubleString(){
        SDouble a = new SDouble(25.9);
        SString s = new SString("5");
        SDivide product = new SDivide(a, s);
        
        a.evaluate();
        s.evaluate();
        product.evaluate();
    }
    
    @Test(expected= RuntimeException.class)
    public void divStringInt(){
        SInteger a = new SInteger(25);
        SString s = new SString("5");
        SDivide product = new SDivide(s, a);
        
        a.evaluate();
        s.evaluate();
        product.evaluate();
    }
    
    @Test
    public void argCount(){
        SDivide s = new SDivide();
        assertEquals(2, s.getMaxArgs());
        assertEquals(2, s.getMinArgs());
    }
}
