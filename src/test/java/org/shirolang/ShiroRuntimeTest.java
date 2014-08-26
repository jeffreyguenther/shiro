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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.shirolang.functions.math.SAdd;
import org.shirolang.values.SBoolean;
import org.shirolang.values.SDouble;
import org.shirolang.values.SIdent;
import org.shirolang.values.SInteger;

/**
 *
 * @author jeffreyguenther
 */
public class ShiroRuntimeTest {
    private ShiroRuntime rt;
    
    @Before
    public void setup(){
        rt = new ShiroRuntime();
    }
    
    @Test
    public void resolvePath(){
        SIdent aId = new SIdent(rt, "a");
        SDouble a1 = new SDouble(12.90);
        rt.addSymbol("a", a1);
        
        assertSame(a1, rt.resolvePath("a"));
        
        aId.evaluate();
        assertSame(a1, aId.getResult());
        assertTrue(a1.isDouble());
    }
    
    @Test
    public void parseInt(){
        
        SFunc result = rt.executedExpr("20");
        assertTrue(result.isInteger());
        
        SInteger i = (SInteger) result;
        assertEquals(20, (int) i.getValue());
    }
    
    @Test
    public void parseDouble(){
        
        SFunc result = rt.executedExpr("2110.032");
        assertTrue(result.isDouble());
        
        SDouble i = (SDouble) result;
        assertEquals(2110.032, i.getValue(), 1e-16);
    }
    
    @Test
    public void parseEquals(){
        
        SFunc result = rt.executedExpr("1 == 1");
        
        SBoolean b = (SBoolean) result.getResult();
        assertTrue(b.isBoolean());
        assertTrue(b.getValue());
        
        SFunc result2 = rt.executedExpr("2 == 1");
        
        SBoolean b2 = (SBoolean) result2.getResult();
        assertTrue(b2.isBoolean());
        assertFalse(b2.getValue());
        
        SFunc result3 = rt.executedExpr("1 == 2");
        
        SBoolean r3 = (SBoolean) result3.getResult();
        assertTrue(r3.isBoolean());
        assertFalse(r3.getValue());
    }
    
    @Test
    public void parseNotEquals(){
        
        
        SFunc result = rt.executedExpr("1 != 1");
        SBoolean b = (SBoolean) result.getResult();
        assertTrue(b.isBoolean());
        assertFalse(b.getValue());
        
        SFunc result2 = rt.executedExpr("2 != 1");
        SBoolean b2 = (SBoolean) result2.getResult();
        assertTrue(b2.isBoolean());
        assertTrue(b2.getValue());
        
        SFunc result3 = rt.executedExpr("1 != 2");
        SBoolean b3 = (SBoolean) result3.getResult();
        assertTrue(b3.isBoolean());
        assertTrue(b3.getValue());
    }
    
    @Test
    public void lessThan(){
        
        
        SFunc result = rt.executedExpr("1 < 2");
        SBoolean a = (SBoolean) result.getResult();
        assertTrue(a.isBoolean());
        assertTrue(a.getValue());
        
        SFunc r2 = rt.executedExpr("2 < 1");
        SBoolean b = (SBoolean) r2.getResult();
        assertTrue(b.isBoolean());
        assertFalse(b.getValue());
    }
    
    @Test
    public void lessThanEqual(){
        
        
        SFunc result = rt.executedExpr("1 <= 2");
        SBoolean a = (SBoolean) result.getResult();
        assertTrue(a.isBoolean());
        assertTrue(a.getValue());
        
        SFunc r2 = rt.executedExpr("2 <= 1");
        SBoolean b = (SBoolean) r2.getResult();
        assertTrue(b.isBoolean());
        assertFalse(b.getValue());
        
        SFunc r3 = rt.executedExpr("2 <= 2");
        SBoolean c = (SBoolean) r3.getResult();
        assertTrue(c.isBoolean());
        assertTrue(c.getValue());
    }
    
    @Test
    public void greaterThan(){
        
        
        SFunc result = rt.executedExpr("1 > 2");
        SBoolean a = (SBoolean) result.getResult();
        assertTrue(a.isBoolean());
        assertFalse(a.getValue());
        
        SFunc r2 = rt.executedExpr("2 > 1");
        SBoolean b = (SBoolean) r2.getResult();
        assertTrue(b.isBoolean());
        assertTrue(b.getValue());
    }
    
    @Test
    public void greaterThanEqual(){
        
        
        SFunc result = rt.executedExpr("1 >= 2");
        SBoolean a = (SBoolean) result.getResult();
        assertTrue(a.isBoolean());
        assertFalse(a.getValue());
        
        SFunc r2 = rt.executedExpr("2.5 >= 1.0");
        SBoolean b = (SBoolean) r2.getResult();
        assertTrue(b.isBoolean());
        assertTrue(b.getValue());
        
        SFunc r3 = rt.executedExpr("2 >= 2");
        SBoolean c = (SBoolean) r3.getResult();
        assertTrue(c.isBoolean());
        assertTrue(c.getValue());
    }
    
    @Test
    public void add(){
        
        
        SFunc result = rt.executedExpr("1 + 2");
        SInteger a = (SInteger) result.getResult();
        assertTrue(a.isInteger());
        assertEquals(3, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("5.3 + 2.23");
        SDouble b = (SDouble) result2.getResult();
        assertTrue(b.isDouble());
        assertEquals(7.53, b.getValue(), 1e-14);
    }
    
    @Test
    public void subtract(){
        
        
        SFunc result = rt.executedExpr("1 - 2");
        SInteger a = (SInteger) result.getResult();
        assertTrue(a.isInteger());
        assertEquals(-1, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("5.3 - 2.23");
        SDouble b = (SDouble) result2.getResult();
        assertTrue(b.isDouble());
        assertEquals(3.07, b.getValue(), 1e-14);
    }
    
    @Test
    public void multiply(){
        
        
        SFunc result = rt.executedExpr("1 * 2");
        SInteger a = (SInteger) result.getResult();
        assertTrue(a.isInteger());
        assertEquals(2, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("0.5 * 2.0");
        SDouble b = (SDouble) result2.getResult();
        assertTrue(b.isDouble());
        assertEquals(1.0, b.getValue(), 1e-14);
    }
    
    @Test
    public void divide(){
        
        
        SFunc result = rt.executedExpr("4 / 2");
        SInteger a = (SInteger) result.getResult();
        assertTrue(a.isInteger());
        assertEquals(2, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("0.5 / 2.0");
        SDouble b = (SDouble) result2.getResult();
        assertTrue(b.isDouble());
        assertEquals(0.25, b.getValue(), 1e-14);
    }
    
    @Test
    public void mod(){
        
        
        SFunc result = rt.executedExpr("4 % 2");
        SInteger a = (SInteger) result.getResult();
        assertTrue(a.isInteger());
        assertEquals(0, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("3.2 % 0.5");
        SDouble b = (SDouble) result2.getResult();
        assertTrue(b.isDouble());
        assertEquals(0.2, b.getValue(), 1e-14);
    }
    
    @Test
    public void and(){
        
        
        SFunc result = rt.executedExpr("true &&  false");
        SBoolean a = (SBoolean) result.getResult();
        assertTrue(a.isBoolean());
        assertFalse(a.getValue());
        
        SFunc result2 = rt.executedExpr("false && false");
        SBoolean b = (SBoolean) result2.getResult();
        assertTrue(b.isBoolean());
        assertFalse(b.getValue());
        
        SFunc result3 = rt.executedExpr("false && true");
        SBoolean c = (SBoolean) result3.getResult();
        assertTrue(c.isBoolean());
        assertFalse(c.getValue());
        
        SFunc result4 = rt.executedExpr("true && true");
        SBoolean d = (SBoolean) result4.getResult();
        assertTrue(d.isBoolean());
        assertTrue(d.getValue());
    }
    
    @Test
    public void parseBooleans(){
        
        
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
        
        
        SFunc result = rt.executedExpr("true ||  false");
        SBoolean a = (SBoolean) result.getResult();
        assertTrue(a.isBoolean());
        assertTrue(a.getValue());
        
        SFunc result2 = rt.executedExpr("false || false");
        SBoolean b = (SBoolean) result2.getResult();
        assertTrue(b.isBoolean());
        assertFalse(b.getValue());
        
        SFunc result3 = rt.executedExpr("false || true");
        SBoolean c = (SBoolean) result3.getResult();
        assertTrue(c.isBoolean());
        assertTrue(c.getValue());
        
        SFunc result4 = rt.executedExpr("true || true");
        SBoolean d = (SBoolean) result4.getResult();
        assertTrue(d.isBoolean());
        assertTrue(d.getValue());
    }
    
    @Test
    public void not(){
        
        
        SFunc result = rt.executedExpr("!true");
        SBoolean a = (SBoolean) result.getResult();
        assertTrue(a.isBoolean());
        assertFalse(a.getValue());
        
        SFunc result2 = rt.executedExpr("!false");
        SBoolean b = (SBoolean) result2.getResult();
        assertTrue(b.isBoolean());
        assertTrue(b.getValue());
    }
    
    @Test
    public void parens(){
        
        
        SFunc result = rt.executedExpr("(4 + 2) * 2");
        SInteger a = (SInteger) result.getResult();
        assertTrue(a.isInteger());
        assertEquals(12, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("4 + 2 * 2");
        SInteger b = (SInteger) result2.getResult();
        assertTrue(b.isInteger());
        assertEquals(8, (int) b.getValue());
    }
    
    @Test
    public void makeNegative(){
        SFunc result = rt.executedExpr("-2");
        SInteger a = (SInteger) result.getResult();
        assertTrue(a.isInteger());
        assertEquals(-2, (int) a.getValue());
        
        SFunc result2 = rt.executedExpr("-2.0");
        SDouble b = (SDouble) result2.getResult();
        assertTrue(b.isDouble());
        assertEquals(-2.0, b.getValue(), 1e-15);
    }
    
    @Test
    public void parsePortDecl(){
        SFunc executedExpr = rt.executedExpr("port a Double(12.0)");
        assertTrue(executedExpr.getArg().isDouble());
        assertFalse(rt.resolvePath("a").isDouble());
        assertEquals(SType.IDENT, executedExpr.getType());
        assertNotNull(executedExpr.getResult());
    }
    
    @Test
    public void registerMFuncByName(){
        String name = "myfunction";
        rt.registerFunction(name, () -> new SAdd());
        SFunc function = rt.createFunction(name);
        assertEquals("Add", function.getType());
    }
}
