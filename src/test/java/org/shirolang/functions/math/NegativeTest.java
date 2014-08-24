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

package org.shirolang.functions.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.shirolang.values.SDouble;
import org.shirolang.values.SInteger;

/**
 *
 * @author jeffreyguenther
 */
public class NegativeTest {
    @Test
    public void getType(){
        SNegative a = new SNegative(null);
        assertEquals("Should be 'Negative'", "Negative", a.getType());
    }
    
    @Test
    public void minusInt(){
        SInteger a = new SInteger(2);
        SNegative result = new SNegative(a);
        
        // simulate evaluation order
        a.evaluate();
        result.evaluate();
        
        SInteger r = (SInteger) result.getArg();
        assertTrue(r.isInteger());
        assertEquals(-2, (int)r.getValue());
        
        SInteger b = new SInteger(-2);
        SNegative result2 = new SNegative(b);
        
        // simulate evaluation order
        b.evaluate();
        result2.evaluate();
        
        SInteger r2 = (SInteger) result2.getArg();
        assertTrue(r2.isInteger());
        assertEquals(2, (int)r2.getValue());
    }
    
    @Test
    public void minusDouble(){
        SDouble a = new SDouble(2.0);
        SNegative result = new SNegative(a);
        
        // simulate evaluation order
        a.evaluate();
        result.evaluate();
        
        SDouble r = (SDouble) result.getArg();
        assertTrue(r.isDouble());
        assertEquals(-2.0, r.getValue(), 1e-15);
        
        SDouble b = new SDouble(-2.0);
        SNegative result2 = new SNegative(b);
        
        // simulate evaluation order
        b.evaluate();
        result2.evaluate();
        
        SDouble r2 = (SDouble) result2.getArg();
        assertTrue(r2.isDouble());
        assertEquals(2.0, r2.getValue(), 1e-15);
    }
    
    @Test
    public void args(){
        SDouble a = new SDouble(2.0);
        SNegative result = new SNegative(a);
        
        assertTrue(result.hasArgs());
        assertTrue(result.getArgs().contains(a));
    }
}
