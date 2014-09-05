package org.shirolang.functions.math;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.shirolang.base.SFunc;
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
        
        SDouble r = (SDouble) diff.getResult();
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
        
        SInteger r = (SInteger) diff.getResult();
        assertTrue(diff.getResult().isInteger());
        assertEquals(14, (int)r.getValue());
    }

    @Test
    public void subDoubleAndInt(){
        SDouble a = new SDouble(13.0);
        SInteger b = new SInteger(-1);
        SSubtract sum = new SSubtract(a, b);

        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        sum.evaluate();

        SDouble r = (SDouble) sum.getResult();
        assertTrue(sum.getResult().isDouble());
        assertEquals(14.0, r.getValue(), 1e-15);

        SDouble a1 = new SDouble(13.0);
        SInteger b2 = new SInteger(-1);
        SSubtract sum2 = new SSubtract(b2, a1);

        // simulate evaluation order
        a1.evaluate();
        b2.evaluate();
        sum2.evaluate();

        SDouble r2 = (SDouble) sum2.getResult();
        assertTrue(sum2.getResult().isDouble());
        assertEquals(-14.0, r2.getValue(), 1e-15);
    }

    @Test
    public void complicatedExpression(){
        SDouble a = new SDouble(2.0);
        SInteger b = new SInteger(3);
        SAdd sum = new SAdd(a, b);
        SDouble c = new SDouble(0.5);
        SSubtract diff = new SSubtract(sum, c);

        a.evaluate();
        b.evaluate();
        c.evaluate();
        sum.evaluate();
        c.evaluate();
        diff.evaluate();

        SDouble result = (SDouble) diff.getResult();
        assertEquals(4.5, result.getValue() ,1e-15);
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
    
    @Test
    public void argCount(){
        SSubtract s = new SSubtract();
        assertEquals(2, s.getMaxArgs());
        assertEquals(2, s.getMinArgs());
    }
}
