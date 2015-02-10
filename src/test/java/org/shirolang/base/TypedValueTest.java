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

package org.shirolang.base;

import org.junit.Assert;
import org.junit.Test;
import org.shirolang.values.SDouble;
import org.shirolang.values.SString;

/**
 *
 */
public class TypedValueTest {
    @Test
    public void doesTypeMatch(){
        TypedValue tv = new TypedValue("Double");
        SDouble d = new SDouble(12.34);

        tv.setValue(d);
        Assert.assertTrue(tv.doesTypeMatch(d));
        Assert.assertTrue(tv.doesTypeMatch(d.getType()));
    }

    @Test(expected = RuntimeException.class)
    public void doesTypeMatchFail(){
        TypedValue tv = new TypedValue("Double");
        SString s = new SString("sf");
        tv.setValue(s);
    }
}
