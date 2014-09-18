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

/**
 *
 */
public class NameManagerTest {
    NameManager nm;

    @Before
    public void setup(){
        nm = new NameManager();
    }

    @Test
    public void getNextName(){
        Assert.assertEquals("l1", nm.getNextName("L"));
        Assert.assertEquals("l2", nm.getNextName("L"));
        Assert.assertEquals("p1", nm.getNextName("P"));
    }

    @Test
    public void getNumberOfInstances(){
        Assert.assertEquals("l1", nm.getNextName("L"));
        Assert.assertEquals("l2", nm.getNextName("L"));
        Assert.assertEquals("p1", nm.getNextName("P"));

        Assert.assertEquals(2, nm.getNumberOfInstances("L"));
        Assert.assertEquals(1, nm.getNumberOfInstances("P"));
        Assert.assertEquals(0, nm.getNumberOfInstances("M"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setInstanceCountFail(){
        nm.setInstanceCount("L", -22);
    }

    @Test
    public void setInstanceCount(){
        nm.setInstanceCount("L", 22);
        Assert.assertEquals("l23", nm.getNextName("L"));
        nm.setInstanceCount("P", 2);
        Assert.assertEquals("p3", nm.getNextName("P"));
    }

    @Test
    public void reset(){
        nm.getNextName("L");
        nm.getNextName("L");
        nm.getNextName("P");

        nm.reset();
        Assert.assertEquals(0, nm.getNumberOfInstances("L"));
        Assert.assertEquals(0, nm.getNumberOfInstances("P"));
    }
}
