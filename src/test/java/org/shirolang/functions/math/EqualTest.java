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
import org.shirolang.values.SInteger;
import org.shirolang.values.SString;

/**
 *
 * @author jeffreyguenther
 */
public class EqualTest {
    @Test
    public void getType(){
        SEqual a = new SEqual(null, null);
        assertEquals("Should be 'Equal'", "Equal", a.getType());
    }
    
    @Test
    public void greaterThanDoubles(){
        SDouble a = new SDouble(13.0);
        SDouble b = new SDouble(-0.13);
        SEqual result = new SEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertFalse(r.getValue());
    }
   
    @Test
    public void lessThanDoubles(){
        SDouble a = new SDouble(-1.0);
        SDouble b = new SDouble(10.0);
        SEqual result = new SEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertFalse(r.getValue());
    }
    
    @Test
    public void equalDoubles(){
        SDouble a = new SDouble(1.0);
        SDouble b = new SDouble(1.0);
        SEqual result = new SEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertTrue(r.getValue());
    }
    
    @Test
    public void greaterThanInts(){
        SInteger a = new SInteger(13);
        SInteger b = new SInteger(-1);
        SEqual result = new SEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertFalse(r.getValue());
    }
    
    @Test
    public void lessThanInts(){
        SInteger a = new SInteger(-1002);
        SInteger b = new SInteger(1);
        SEqual result = new SEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertFalse(r.getValue());
    }
    
    @Test
    public void equalInts(){
        SInteger a = new SInteger(-1);
        SInteger b = new SInteger(-1);
        SEqual result = new SEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertTrue(r.getValue());
        
        SInteger a1 = new SInteger(-1);
        SInteger b1 = new SInteger(1);
        SEqual result2 = new SEqual(a1, b1);
        
        // simulate evaluation order
        a1.evaluate();
        b1.evaluate();
        result2.evaluate();
        
        SBoolean r2 = (SBoolean) result2.getResult();
        assertTrue(r2.isBoolean());
        assertFalse(r2.getValue());
        
        SInteger a3 = new SInteger(2);
        SInteger b3 = new SInteger(1);
        SEqual result3 = new SEqual(a3, b3);
        
        // simulate evaluation order
        a3.evaluate();
        b3.evaluate();
        result3.evaluate();
        
        SBoolean r3 = (SBoolean) result3.getResult();
        assertTrue(r3.isBoolean());
        assertFalse(r3.getValue());
    }
    
    @Test
    public void equalStrings(){
        SString a = new SString("A1");
        SString b = new SString("A1");
        SEqual result = new SEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertTrue(r.getValue());
    }
    
    @Test
    public void notEqualStrings(){
        SString a = new SString("A");
        SString b = new SString("A1");
        SEqual result = new SEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertFalse(r.getValue());
    }
    
    @Test
    public void argCount(){
        SEqual s = new SEqual();
        assertEquals(2, s.getMaxArgs());
        assertEquals(2, s.getMinArgs());
    }

    @Test
    public void toConsole(){
        SDouble a = new SDouble(13.0);
        SDouble b = new SDouble(-0.13);
        SEqual result = new SEqual(a, b);

        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();

        assertEquals("#<Equal args:[a:13.0, b:-0.13], results:[false]>", result.toConsole());
    }
}
