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

package org.shirolang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.shirolang.values.SBoolean;
import org.shirolang.values.SDouble;
import org.shirolang.values.SIdent;
import org.shirolang.values.SInteger;

/**
 *
 * @author jeffreyguenther
 */
public class ShiroRuntimeTest {
    @Test
    public void resolvePath(){
        ShiroRuntime rt = new ShiroRuntime();
        SIdent aId = new SIdent(rt, "a");
        SDouble a1 = new SDouble(12.90);
        rt.addSymbol("a", a1);
        
        assertSame(a1, rt.resolvePath("a"));
        
        aId.evaluate();
        assertSame(a1, aId.getArg());
        assertTrue(a1.isDouble());
    }
    
    @Test
    public void parseInt(){
        ShiroRuntime rt = new ShiroRuntime();
        SFunc result = rt.executedExpr("20");
        assertTrue(result.isInteger());
        
        SInteger i = (SInteger) result;
        result.evaluate();
        assertEquals(20, (int) i.getValue());
    }
    
    @Test
    public void parseDouble(){
        ShiroRuntime rt = new ShiroRuntime();
        SFunc result = rt.executedExpr("2110.032");
        assertTrue(result.isDouble());
        
        SDouble i = (SDouble) result;
        result.evaluate();
        assertEquals(2110.032, i.getValue(), 1e-16);
    }
    
    @Test
    public void parseEquals(){
        ShiroRuntime rt = new ShiroRuntime();
        SFunc result = rt.executedExpr("1 == 1");
        
        assertTrue(result.isBoolean());
        SBoolean b = (SBoolean) result;
        assertTrue(b.getValue());
    }
}
