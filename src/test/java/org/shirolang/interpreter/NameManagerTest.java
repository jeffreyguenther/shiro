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
import org.shirolang.exceptions.NameUsedException;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class NameManagerTest {
    NameManager nm;

    @Before
    public void setup(){
        nm = new NameManager();
    }

    @Test(expected = NameUsedException.class)
    public void saveName() throws NameUsedException {
        nm.saveName("T", "n");
        nm.saveName("T", "n");
    }

    @Test(expected = NameUsedException.class)
    public void saveNameDifferentType() throws NameUsedException {
        nm.saveName("T", "n");
        nm.saveName("L", "n");
    }

    @Test
    public void generateName(){
        Assert.assertEquals("l1", nm.generateName("L"));
    }

    @Test
    public void generateNameAfterOtherTypeUsesName() throws NameUsedException {
        Assert.assertEquals("l1", nm.generateName("L"));
        nm.saveName("P", "l2");
        Assert.assertEquals("l3", nm.generateName("L"));
    }

    @Test
    public void getNames() throws NameUsedException {
        Set<String> namesOfL = new HashSet<>();
        namesOfL.add("l1");
        namesOfL.add("l3");
        namesOfL.add("l2");

        Set<String> allNames = new HashSet<>();
        allNames.add("l1");
        allNames.add("l2");
        allNames.add("l3");
        allNames.add("p1");

        nm.saveName("L", nm.generateName("L"));
        nm.saveName("L", nm.generateName("L"));
        nm.saveName("L", nm.generateName("L"));
        nm.saveName("P", "p1");

        Assert.assertEquals(namesOfL, nm.getNames("L"));
        Assert.assertEquals(allNames, nm.getNames());
    }
}
