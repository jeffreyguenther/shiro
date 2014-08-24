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
import static org.junit.Assert.assertFalse;
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
        assertEquals(20, (int) i.getValue());
    }
    
    @Test
    public void parseDouble(){
        ShiroRuntime rt = new ShiroRuntime();
        SFunc result = rt.executedExpr("2110.032");
        assertTrue(result.isDouble());
        
        SDouble i = (SDouble) result;
        assertEquals(2110.032, i.getValue(), 1e-16);
    }
    
    @Test
    public void parseEquals(){
        ShiroRuntime rt = new ShiroRuntime();
        SFunc result = rt.executedExpr("1 == 1");
        
        SBoolean b = (SBoolean) result.getArg();
        assertTrue(b.isBoolean());
        assertTrue(b.getValue());
        
        SFunc result2 = rt.executedExpr("2 == 1");
        
        SBoolean b2 = (SBoolean) result2.getArg();
        assertTrue(b2.isBoolean());
        assertFalse(b2.getValue());
        
        SFunc result3 = rt.executedExpr("1 == 2");
        
        SBoolean r3 = (SBoolean) result3.getArg();
        System.out.println(r3);
        assertTrue(r3.isBoolean());
        assertFalse(r3.getValue());
    }
    
    @Test
    public void parseNotEquals(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("1 != 1");
        SBoolean b = (SBoolean) result.getArg();
        assertTrue(b.isBoolean());
        assertFalse(b.getValue());
        
        SFunc result2 = rt.executedExpr("2 != 1");
        SBoolean b2 = (SBoolean) result2.getArg();
        assertTrue(b2.isBoolean());
        assertTrue(b2.getValue());
        
        SFunc result3 = rt.executedExpr("1 != 2");
        SBoolean b3 = (SBoolean) result3.getArg();
        assertTrue(b3.isBoolean());
        assertTrue(b3.getValue());
    }
    
    @Test
    public void lessThan(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("1 < 2");
        SBoolean a = (SBoolean) result.getArg();
        assertTrue(a.isBoolean());
        assertTrue(a.getValue());
        
        SFunc r2 = rt.executedExpr("2 < 1");
        SBoolean b = (SBoolean) r2.getArg();
        assertTrue(b.isBoolean());
        assertFalse(b.getValue());
    }
    
    @Test
    public void lessThanEqual(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("1 <= 2");
        SBoolean a = (SBoolean) result.getArg();
        assertTrue(a.isBoolean());
        assertTrue(a.getValue());
        
        SFunc r2 = rt.executedExpr("2 <= 1");
        SBoolean b = (SBoolean) r2.getArg();
        assertTrue(b.isBoolean());
        assertFalse(b.getValue());
        
        SFunc r3 = rt.executedExpr("2 <= 2");
        SBoolean c = (SBoolean) r3.getArg();
        assertTrue(c.isBoolean());
        assertTrue(c.getValue());
    }
    
    @Test
    public void greaterThan(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("1 > 2");
        SBoolean a = (SBoolean) result.getArg();
        assertTrue(a.isBoolean());
        assertFalse(a.getValue());
        
        SFunc r2 = rt.executedExpr("2 > 1");
        SBoolean b = (SBoolean) r2.getArg();
        assertTrue(b.isBoolean());
        assertTrue(b.getValue());
    }
    
    @Test
    public void greaterThanEqual(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("1 >= 2");
        SBoolean a = (SBoolean) result.getArg();
        assertTrue(a.isBoolean());
        assertFalse(a.getValue());
        
        SFunc r2 = rt.executedExpr("2.5 >= 1.0");
        SBoolean b = (SBoolean) r2.getArg();
        assertTrue(b.isBoolean());
        assertTrue(b.getValue());
        
        SFunc r3 = rt.executedExpr("2 >= 2");
        SBoolean c = (SBoolean) r3.getArg();
        assertTrue(c.isBoolean());
        assertTrue(c.getValue());
    }
    
    @Test
    public void add(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("1 + 2");
        SInteger a = (SInteger) result.getArg();
        assertTrue(a.isInteger());
        assertEquals(3, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("5.3 + 2.23");
        SDouble b = (SDouble) result2.getArg();
        assertTrue(b.isDouble());
        assertEquals(7.53, b.getValue(), 1e-14);
    }
    
    @Test
    public void subtract(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("1 - 2");
        SInteger a = (SInteger) result.getArg();
        assertTrue(a.isInteger());
        assertEquals(-1, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("5.3 - 2.23");
        SDouble b = (SDouble) result2.getArg();
        assertTrue(b.isDouble());
        assertEquals(3.07, b.getValue(), 1e-14);
    }
    
    @Test
    public void multiply(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("1 * 2");
        SInteger a = (SInteger) result.getArg();
        assertTrue(a.isInteger());
        assertEquals(2, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("0.5 * 2.0");
        SDouble b = (SDouble) result2.getArg();
        assertTrue(b.isDouble());
        assertEquals(1.0, b.getValue(), 1e-14);
    }
    
    @Test
    public void divide(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("4 / 2");
        SInteger a = (SInteger) result.getArg();
        assertTrue(a.isInteger());
        assertEquals(2, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("0.5 / 2.0");
        SDouble b = (SDouble) result2.getArg();
        assertTrue(b.isDouble());
        assertEquals(0.25, b.getValue(), 1e-14);
    }
    
    @Test
    public void mod(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("4 % 2");
        SInteger a = (SInteger) result.getArg();
        assertTrue(a.isInteger());
        assertEquals(0, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("3.2 % 0.5");
        SDouble b = (SDouble) result2.getArg();
        assertTrue(b.isDouble());
        assertEquals(0.2, b.getValue(), 1e-14);
    }
    
    @Test
    public void and(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("true &&  false");
        SBoolean a = (SBoolean) result.getArg();
        assertTrue(a.isBoolean());
        assertFalse(a.getValue());
        
        SFunc result2 = rt.executedExpr("false && false");
        SBoolean b = (SBoolean) result2.getArg();
        assertTrue(b.isBoolean());
        assertFalse(b.getValue());
        
        SFunc result3 = rt.executedExpr("false && true");
        SBoolean c = (SBoolean) result3.getArg();
        assertTrue(c.isBoolean());
        assertFalse(c.getValue());
        
        SFunc result4 = rt.executedExpr("true && true");
        SBoolean d = (SBoolean) result4.getArg();
        assertTrue(d.isBoolean());
        assertTrue(d.getValue());
    }
    
    @Test
    public void parseBooleans(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("false");
        assertTrue(result.isBoolean());
        SBoolean b = (SBoolean) result;
        assertFalse(b.getValue());
        
        SFunc result2 = rt.executedExpr("true");
        assertTrue(result2.isBoolean());
        SBoolean b2 = (SBoolean) result2;
        assertTrue(b2.getValue());
    }
    
     @Test
    public void or(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("true ||  false");
        SBoolean a = (SBoolean) result.getArg();
        assertTrue(a.isBoolean());
        assertTrue(a.getValue());
        
        SFunc result2 = rt.executedExpr("false || false");
        SBoolean b = (SBoolean) result2.getArg();
        assertTrue(b.isBoolean());
        assertFalse(b.getValue());
        
        SFunc result3 = rt.executedExpr("false || true");
        SBoolean c = (SBoolean) result3.getArg();
        assertTrue(c.isBoolean());
        assertTrue(c.getValue());
        
        SFunc result4 = rt.executedExpr("true || true");
        SBoolean d = (SBoolean) result4.getArg();
        assertTrue(d.isBoolean());
        assertTrue(d.getValue());
    }
    
    @Test
    public void not(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("!true");
        SBoolean a = (SBoolean) result.getArg();
        assertTrue(a.isBoolean());
        assertFalse(a.getValue());
        
        SFunc result2 = rt.executedExpr("!false");
        SBoolean b = (SBoolean) result2.getArg();
        assertTrue(b.isBoolean());
        assertTrue(b.getValue());
    }
    
    @Test
    public void parens(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("(4 + 2) * 2");
        SInteger a = (SInteger) result.getArg();
        assertTrue(a.isInteger());
        assertEquals(12, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("4 + 2 * 2");
        SInteger b = (SInteger) result2.getArg();
        assertTrue(b.isInteger());
        assertEquals(8, (int) b.getValue());
    }
    
    @Test
    public void makeNegative(){
        ShiroRuntime rt = new ShiroRuntime();
        
        SFunc result = rt.executedExpr("-2");
        SInteger a = (SInteger) result.getArg();
        assertTrue(a.isInteger());
        assertEquals(-2, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("-2.0");
        SDouble b = (SDouble) result2.getArg();
        assertTrue(b.isDouble());
        assertEquals(-2.0, b.getValue(), 1e-15);
    }
}
