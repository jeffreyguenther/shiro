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
public class MultiplyTest {
    @Test
    public void getType(){
        SMultiply s = new SMultiply(null, null);
        assertEquals("Should be 'Multiply'", "Multiply", s.getType());
    }
    
    @Test
    public void multDoubles(){
        SDouble a = new SDouble(13.0);
        SDouble b = new SDouble(-0.13);
        SMultiply product = new SMultiply(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        product.evaluate();
        
        SDouble r = (SDouble) product.get();
        assertTrue(r.isDouble());
        assertEquals(-1.69, r.getValue(), 1e-16);
    }
    
    @Test
    public void multInts(){
        SInteger a = new SInteger(13);
        SInteger b = new SInteger(-1);
        SMultiply product = new SMultiply(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        product.evaluate();
        
        SInteger r = (SInteger) product.get();
        assertTrue(product.get().isInteger());
        assertEquals(-13, (int)r.getValue());
    }
    
    @Test(expected= RuntimeException.class)
    public void multDoubleString(){
        SDouble a = new SDouble(25.9);
        SString s = new SString("5");
        SMultiply product = new SMultiply(a, s);
        
        a.evaluate();
        s.evaluate();
        product.evaluate();
    }
    
    @Test(expected= RuntimeException.class)
    public void multStringInt(){
        SInteger a = new SInteger(25);
        SString s = new SString("5");
        SMultiply product = new SMultiply(s, a);
        
        a.evaluate();
        s.evaluate();
        product.evaluate();
    }
}
