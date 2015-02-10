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

package org.shirolang.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests a Shiro string.
 */
public class StringTest {
    @Test
    public void constructors(){
        SString s = new SString();
        Assert.assertTrue(s.getSymbolType().isLiteral());

        SString s1 = new SString("Test");
        s1.evaluate();
        Assert.assertTrue(s1.getSymbolType().isLiteral());
        Assert.assertEquals("Test", s1.getValue());

        SString s2 = new SString("a", "Test");
        Assert.assertTrue(s2.getSymbolType().isPort());
        Assert.assertEquals("a", s2.getName());
    }

    @Test
    public void getType(){
        SString s = new SString("Hello");
        assertEquals("String", s.getType());
    }
    
    @Test(expected = RuntimeException.class)
    public void getValueFail(){
        SString s = new SString("Hello");
        assertEquals("Hello", s.getValue());
    }
    
    @Test
    public void getValue(){
        SString s = new SString("Hello");
        s.evaluate();
        assertEquals("Hello", s.getValue());
    }
    
    @Test
    public void getArgs(){
        SString s = new SString("Hello");
        assertTrue(s.getArgs().isEmpty());
    }
    
    @Test
    public void hasArgs(){
        SString s = new SString("Hello");
        assertFalse(s.hasArgs());
    }
    
    @Test
    public void argCount(){
        SString s = new SString();
        assertEquals(0, s.getMaxArgs());
        assertEquals(0, s.getMinArgs());
    }

    @Test
    public void toConsole(){
        SString s = new SString("hello");
        s.evaluate();
        Assert.assertEquals("\"hello\"", s.toConsole());

        SString s1 = new SString("a", "hello");
        s1.evaluate();
        Assert.assertEquals("#<String args:[], results:[\"hello\"]>", s1.toConsole());
    }
}
