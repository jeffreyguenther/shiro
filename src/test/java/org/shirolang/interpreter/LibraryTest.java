/*
 * Copyright (c) 2012-2014 Jeffrey Guenther
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software  and associated documentation files (the
 * "Software"), to deal in the Software without restriction,  including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute,  sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT  NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.shirolang.interpreter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.shirolang.base.SFunc;
import org.shirolang.functions.math.SAdd;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LibraryTest {
    private  Library l;
    @Before
    public void setup(){
        l = new Library();
    }

    @Test
    public void library(){
        Library l = new Library();
    }

    @Test
    public void createFunction(){
        Library l = new Library();
        SFunc add = l.createFunction("Add");
        Assert.assertEquals("Add", add.getType());
    }

    @Test
    public void registerMFuncByName(){
        String name = "myfunction";
        l.registerFunction(name, () -> new SAdd());
        SFunc function = l.createFunction(name);
        assertEquals("Add", function.getType());
    }
}
