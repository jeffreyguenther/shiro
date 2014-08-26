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

/**
 *
 * @author jeffreyguenther
 */
public class NotTest {
    @Test
    public void getType(){
        SNot a = new SNot(null);
        assertEquals("Should be 'Not'", "Not", a.getType());
    }
    
    @Test
    public void notBoolean(){
        SBoolean a = new SBoolean(true);
        SNot result = new SNot(a);
        
        // simulate evaluation order
        a.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertFalse(r.getValue());
        
        SBoolean b = new SBoolean(false);
        SNot result2 = new SNot(b);
        
        // simulate evaluation order
        b.evaluate();
        result2.evaluate();
        
        SBoolean r2 = (SBoolean) result2.getResult();
        assertTrue(r2.isBoolean());
        assertTrue(r2.getValue());
    }
    
    
    @Test(expected= RuntimeException.class)
    public void addDoubleString(){
        SDouble a = new SDouble(25.9);
        SNot sum = new SNot(a);
        
        a.evaluate();
        sum.evaluate();
    }
    
    @Test
    public void args(){
        SBoolean a = new SBoolean(true);
        SNot result = new SNot(a);
        
        assertTrue(result.hasArgs());
        assertTrue(result.getArgs().contains(a));
    }
    
    @Test
    public void argCount(){
        SNot s = new SNot();
        assertEquals(1, s.getMaxArgs());
        assertEquals(1, s.getMinArgs());
    }
}
