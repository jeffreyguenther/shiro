package org.shirolang.functions.math;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.shirolang.SFunc;
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
public class SubtractTest {
    @Test
    public void getType(){
        SSubtract s = new SSubtract(null, null);
        assertEquals("Should be 'Subtract'", "Subtract", s.getType());
    }
    
    @Test
    public void subDoubles(){
        SDouble a = new SDouble(13.0);
        SDouble b = new SDouble(-0.13);
        SSubtract diff = new SSubtract(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        diff.evaluate();
        
        SDouble r = (SDouble) diff.getArg();
        assertTrue(r.isDouble());
        assertEquals(13.13, r.getValue(), 1e-16);
    }
    
    @Test
    public void subInts(){
        SInteger a = new SInteger(13);
        SInteger b = new SInteger(-1);
        SSubtract diff = new SSubtract(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        diff.evaluate();
        
        SInteger r = (SInteger) diff.getArg();
        assertTrue(diff.getArg().isInteger());
        assertEquals(14, (int)r.getValue());
    }
    
    @Test(expected= RuntimeException.class)
    public void subDoubleString(){
        SDouble a = new SDouble(25.9);
        SString s = new SString("5");
        SSubtract diff = new SSubtract(a, s);
        
        a.evaluate();
        s.evaluate();
        diff.evaluate();
    }
    
    @Test(expected= RuntimeException.class)
    public void subStringInt(){
        SInteger a = new SInteger(25);
        SString s = new SString("5");
        SSubtract diff = new SSubtract(s, a);
        
        a.evaluate();
        s.evaluate();
        diff.evaluate();
    }
    
    @Test
    public void args(){
        SInteger a = new SInteger(25);
        SString s = new SString("5");
        SSubtract diff = new SSubtract(s, a);
        
        List<SFunc> deps = diff.getArgs();
        assertTrue(diff.hasArgs());
        assertEquals(2, deps.size());
        assertTrue(deps.contains(a));
        assertTrue(deps.contains(s));
    }
}
