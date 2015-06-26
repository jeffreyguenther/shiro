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

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.shirolang.base.SFunc;
import org.shirolang.values.SBoolean;
import org.shirolang.values.SDouble;
import org.shirolang.values.SInteger;
import org.shirolang.values.SString;

/**
 *
 * @author jeffreyguenther
 */
public class NotEqualTest {
    @Test
    public void getType(){
        SNotEqual a = new SNotEqual(null, null);
        assertEquals("Should be 'NotEqual'", "NotEqual", a.getType());
    }
    
    @Test
    public void greaterThanDoubles(){
        SDouble a = new SDouble(13.0);
        SDouble b = new SDouble(-0.13);
        SNotEqual result = new SNotEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertTrue(r.getValue());
    }
   
    @Test
    public void lessThanDoubles(){
        SDouble a = new SDouble(-1.0);
        SDouble b = new SDouble(10.0);
        SNotEqual result = new SNotEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertTrue(r.getValue());
    }
    
    @Test
    public void equalDoubles(){
        SDouble a = new SDouble(1.0);
        SDouble b = new SDouble(1.0);
        SNotEqual result = new SNotEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertFalse(r.getValue());
    }
    
    @Test
    public void greaterThanInts(){
        SInteger a = new SInteger(13);
        SInteger b = new SInteger(-1);
        SNotEqual result = new SNotEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertTrue(r.getValue());
    }
    
    @Test
    public void lessThanInts(){
        SInteger a = new SInteger(-1002);
        SInteger b = new SInteger(1);
        SNotEqual result = new SNotEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertTrue(r.getValue());
    }
    
    @Test
    public void equalInts(){
        SInteger a = new SInteger(-1);
        SInteger b = new SInteger(-1);
        SNotEqual result = new SNotEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertFalse(r.getValue());
    }
    
    @Test
    public void equalStrings(){
        SString a = new SString("A1");
        SString b = new SString("A1");
        SNotEqual result = new SNotEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertFalse(r.getValue());
    }
    
    @Test
    public void notEqualStrings(){
        SString a = new SString("A");
        SString b = new SString("A1");
        SNotEqual result = new SNotEqual(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();
        
        SBoolean r = (SBoolean) result.getResult();
        assertTrue(r.isBoolean());
        assertTrue(r.getValue());
    }
    
    @Test
    public void args(){
        SString a = new SString("A");
        SString b = new SString("A1");
        SNotEqual result = new SNotEqual(a, b);
        
        assertTrue(result.hasInputs());
        List<SFunc> args = result.getInputs();
        assertTrue(args.contains(a));
        assertTrue(args.contains(b));
    }
    
    @Test
    public void argCount(){
        SNotEqual s = new SNotEqual();
        assertEquals(2, s.getMaxArgs());
        assertEquals(2, s.getMinArgs());
    }

    @Test
    public void toConsole(){
        SDouble a = new SDouble(13.0);
        SDouble b = new SDouble(-0.13);
        SNotEqual result = new SNotEqual(a, b);

        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        result.evaluate();

        assertEquals("#<NotEqual args:[a:13.0, b:-0.13], results:[true]>", result.toConsole());
    }
}
