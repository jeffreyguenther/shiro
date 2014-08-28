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

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test a path expression object
 * @author jeffreyguenther
 */
public class PathTest {
    @Test
    public void constructors(){
        Path p1 = new Path();
        Assert.assertTrue(p1.isEmpty());
        Assert.assertFalse(p1.isPathToPortIndex());
        Assert.assertEquals("path head should be 0", 0, p1.getPathHead());
        Assert.assertNull("index key should be null", p1.getIndexKey());
        Assert.assertEquals("should be -1", -1, p1.getIndex());
        
        Path p2 = new Path("Hello", "World");
        Assert.assertFalse(p2.isEmpty());
        Assert.assertFalse(p2.isPathToPortIndex());
        Assert.assertEquals("path head should be 0", 0, p2.getPathHead());
        Assert.assertNull("index key should be null", p2.getIndexKey());
        Assert.assertEquals("should be -1", -1, p2.getIndex());
        Assert.assertEquals("should be 'Hello.World'", "Hello.World", p2.getPath());
        
        List<String> path = new ArrayList<>();
        path.add("a");
        path.add("b");
        Path p3 = new Path(path, "i");
        Assert.assertFalse(p3.isEmpty());
        Assert.assertFalse(p3.isPathToPortIndex());
        Assert.assertTrue(p3.hasIndex());
        Assert.assertTrue(p3.hasStringIndex());
        Assert.assertFalse(p3.hasIntegerIndex());
        Assert.assertEquals("path head should be 0", 0, p3.getPathHead());
        Assert.assertEquals("index key should be 'i'", "i", p3.getIndexKey());
        Assert.assertEquals("should be -1", -1, p3.getIndex());
        Assert.assertEquals("should be 'a.b'", "a.b", p3.getPath());
        
        Path p4 = new Path(path, 0);
        Assert.assertFalse(p4.isEmpty());
        Assert.assertFalse(p4.isPathToPortIndex());
        Assert.assertTrue(p4.hasIndex());
        Assert.assertTrue(p4.hasIntegerIndex());
        Assert.assertFalse(p4.hasStringIndex());
        Assert.assertEquals("path head should be 0", 0, p4.getPathHead());
        Assert.assertNull("index key should be null", p4.getIndexKey());
        Assert.assertEquals("should be 0", 0, p4.getIndex());
        Assert.assertEquals("should be 'a.b'", "a.b", p4.getPath());
    }
    
    @Test
    public void movePathHead(){
        Path p = new Path("a", "b", "c", "d", "e", "f");
        Assert.assertEquals("a", p.getCurrentPathHead());
        p.popPathHead();
        Assert.assertEquals("b", p.getCurrentPathHead());
        Assert.assertEquals(1, p.getPathHead());
        p.popPathHead();
        p.popPathHead();
        p.resetPathHead();
        Assert.assertEquals("a", p.getCurrentPathHead());
        Assert.assertEquals(0, p.getPathHead());
    }
    
    @Test
    public void equals(){
        List<String> elements = new ArrayList<>();
        elements.add("P1");
        elements.add("x");
        
        List<String> elements2 = new ArrayList<>();
        elements2.add("P1");
        elements2.add("x");
        Path p1  = new Path(elements);
        Path p2  = new Path(elements2);
        
        Assert.assertEquals("paths should be equal", true, p1.equals(p2));
    }
    
    @Test
    public void isAtEnd(){
        Path p = new Path("Point");
        Assert.assertTrue("should be at end", p.isAtEnd());
        
        Path p1 = new Path("Point", "x");
        Assert.assertEquals("length should be 2", 2, p1.getPathParts().size());
        Assert.assertEquals("path head should be 0", 0, p1.getPathHead());
        Assert.assertFalse("should not be at end", p1.isAtEnd());
        Assert.assertEquals("should be path", "Point.x", p1.getPath());
    }
    
    @Test
    public void getNameFromPath(){
        Assert.assertEquals("f", Path.getNameFromPath("a.b.c.d.e.f"));
        Assert.assertEquals("f", Path.getNameFromPath("f"));
    }
    
    @Test
    public void replaceNameInPath(){
        Assert.assertEquals("a.b", Path.replaceNameInPath("a.c", "b"));
        Assert.assertEquals("b", Path.replaceNameInPath("a", "b"));
    }
    
    @Test
    public void createFullName(){
        Assert.assertEquals("a.b.c", Path.createFullName("a.b", "c"));
        Assert.assertEquals("c", Path.createFullName("", "c"));
    }
    
    @Test
    public void create(){
        Path path = Path.create("a.b.c");
        List<String> parts = new ArrayList<>();
        parts.add("a");
        parts.add("b");
        parts.add("c");
        
        Assert.assertEquals(parts, path.getPathParts());
    }

    @Test
    public void isReference(){
        Path p = new Path();
        Assert.assertFalse(p.isReference());
        p.makeReference();
        Assert.assertTrue(p.isReference());
    }
}
