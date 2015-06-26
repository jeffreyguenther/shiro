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
import org.shirolang.values.SString;

/**
 *
 * @author jeffreyguenther
 */
public class ModuloTest {
    @Test
    public void getType(){
        SModulo s = new SModulo(null, null);
        assertEquals("Should be 'Modulo'", "Modulo", s.getType());
    }
    
    @Test
    public void modDoubles(){
        SDouble a = new SDouble(13.0);
        SDouble b = new SDouble(2.5);
        SModulo remainder = new SModulo(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        remainder.evaluate();
        
        SDouble r = (SDouble) remainder.getResult();
        assertTrue(r.isDouble());
        assertEquals(0.5, r.getValue(), 1e-16);
    }
    
    @Test
    public void modInts(){
        SInteger a = new SInteger(13);
        SInteger b = new SInteger(-1);
        SModulo remainder = new SModulo(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        remainder.evaluate();
        
        SInteger r = (SInteger) remainder.getResult();
        assertTrue(remainder.getResult().isInteger());
        assertEquals(0, (int)r.getValue());
    }
    
    @Test(expected= RuntimeException.class)
    public void multDoubleString(){
        SDouble a = new SDouble(25.9);
        SString s = new SString("5");
        SModulo product = new SModulo(a, s);
        
        a.evaluate();
        s.evaluate();
        product.evaluate();
    }
    
    @Test(expected= RuntimeException.class)
    public void multStringInt(){
        SInteger a = new SInteger(25);
        SString s = new SString("5");
        SModulo product = new SModulo(s, a);
        
        a.evaluate();
        s.evaluate();
        product.evaluate();
    }
    
    @Test
    public void args(){
        SInteger a = new SInteger(25);
        SString s = new SString("5");
        SModulo product = new SModulo(s, a);
        
        assertTrue(product.hasInputs());
        assertTrue(product.getInputs().contains(a));
        assertTrue(product.getInputs().contains(s));
    }
    
    @Test
    public void argCount(){
        SModulo s = new SModulo();
        assertEquals(2, s.getMaxArgs());
        assertEquals(2, s.getMinArgs());
    }

    @Test
    public void toConsole(){
        SDouble a = new SDouble(13.0);
        SDouble b = new SDouble(2.5);
        SModulo remainder = new SModulo(a, b);

        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        remainder.evaluate();

        assertEquals("#<Modulo args:[a:13.0, b:2.5], results:[0.5]>", remainder.toConsole());
    }
}
