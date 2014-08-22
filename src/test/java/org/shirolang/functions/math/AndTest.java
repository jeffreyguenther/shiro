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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.shirolang.values.SBoolean;
import org.shirolang.values.SDouble;
import org.shirolang.values.SString;

/**
 *
 * @author jeffreyguenther
 */
public class AndTest {
    @Test
    public void getType(){
        SAnd a = new SAnd(null, null);
        assertEquals("Should be 'And'", "And", a.getType());
    }
    
    @Test
    public void andBooleans(){
        SBoolean a = new SBoolean(true);
        SBoolean b = new SBoolean(true);
        SAnd truetrue = new SAnd(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        truetrue.evaluate();
        
        SBoolean r = (SBoolean) truetrue.get();
        assertTrue(truetrue.get().isBoolean());
        assertTrue(r.getValue());
        
        SBoolean a1 = new SBoolean(false);
        SBoolean b1 = new SBoolean(false);
        SAnd falsefalse = new SAnd(a1, b1);
        
        // simulate evaluation order
        a1.evaluate();
        b1.evaluate();
        falsefalse.evaluate();
        
        SBoolean r1 = (SBoolean) falsefalse.get();
        assertTrue(falsefalse.get().isBoolean());
        assertFalse(r1.getValue());
        
        SBoolean a2 = new SBoolean(true);
        SBoolean b2 = new SBoolean(false);
        SAnd truefalse = new SAnd(a2, b2);
        
        // simulate evaluation order
        a2.evaluate();
        b2.evaluate();
        truefalse.evaluate();
        
        SBoolean r2 = (SBoolean) truefalse.get();
        assertTrue(truefalse.get().isBoolean());
        assertFalse(r2.getValue());
    }
    
    @Test(expected= RuntimeException.class)
    public void andDoubleString(){
        SDouble a = new SDouble(25.9);
        SString s = new SString("5");
        SAnd sum = new SAnd(a, s);
        
        a.evaluate();
        s.evaluate();
        sum.evaluate();
    }
}
