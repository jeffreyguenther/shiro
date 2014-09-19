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

package org.shirolang.base;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Tests the IndexedMap
 */
public class SIndexedMapTest {
    @Test
    public void size(){
        SIndexedMap<Integer> ints = new SIndexedMap<>();
        ints.set(1);
        assertEquals(1, ints.size());
    }
    
    @Test
    public void getAll(){
        SIndexedMap<Integer> ints = new SIndexedMap<>();
        ints.set(1);
        ints.set(3);
        ints.set(5);
        
        List<Integer> values = ints.getAll();
        assertEquals(3, values.size());
        assertTrue(values.contains(1));
        assertTrue(values.contains(3));
        assertTrue(values.contains(5));
    }
    
    @Test
    public void isEmpty(){
        SIndexedMap<Integer> ints = new SIndexedMap<>();
        assertTrue(ints.isEmpty());
    }

    @Test
    public void getKey(){
        SIndexedMap<Integer> ints = new SIndexedMap<>();
        ints.setKeyForIndex("a", 0);
        Assert.assertEquals("a", ints.getKey(0));
        Assert.assertNull("a", ints.getKey(1));
    }
    
    @Test
    public void getKeys(){
        SIndexedMap<Integer> ints = new SIndexedMap<>();
        ints.setKeyForIndex("a", 0);
        ints.setKeyForIndex("b", 1);
        ints.setKeyForIndex("c", 2);
        
        List<String> expectedNames = new ArrayList<>();
        expectedNames.add("a");
        expectedNames.add("b");
        expectedNames.add("c");
        
        List<String> keys = ints.getKeys();
        assertEquals("lists of keys should match", expectedNames, keys);
    }
    
    
}
