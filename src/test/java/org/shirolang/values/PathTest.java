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
        Assert.assertTrue(p1.hasSegments());
        Assert.assertEquals("path head should be 0", 0, p1.getHead());

        Path p2 = new Path("Hello", "World");
        Assert.assertFalse(p2.hasSegments());
        Assert.assertEquals("path head should be 0", 0, p2.getHead());
        Assert.assertEquals("should be 'Hello.World'", "Hello.World", p2.getPath());
    }

    @Test
    public void movePathHead(){
        Path p = new Path("a", "b", "c", "d", "e", "f");
        Assert.assertEquals("a", p.getCurrentHead().getKey().get());
        p.popHead();
        Assert.assertEquals("b", p.getCurrentHead().getKey().get());
        Assert.assertEquals(1, p.getHead());
        p.popHead();
        p.popHead();
        p.resetHead();
        Assert.assertEquals("a", p.getCurrentHead().getKey().get());
        Assert.assertEquals(0, p.getHead());
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
        Assert.assertEquals("length should be 2", 2, p1.getSegments().size());
        Assert.assertEquals("path head should be 0", 0, p1.getHead());
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
        List<PathSegment> parts = new ArrayList<>();
        parts.add(new PathSegment("a"));
        parts.add(new PathSegment("b"));
        parts.add(new PathSegment("c"));

        Assert.assertEquals(parts, path.getSegments());
    }

    @Test
    public void isReference(){
        Path p = new Path();
        Assert.assertFalse(p.isReference());
        p.makeReference();
        Assert.assertTrue(p.isReference());
    }

    @Test
    public void isSelector(){
        Path p = new Path();
        Assert.assertFalse(p.isSelector());
        p.makeSelector();
        Assert.assertTrue(p.isSelector());
    }

    @Test(expected = RuntimeException.class)
    public void alreadySelectorMakeReference(){
        Path p = new Path();
        p.makeSelector();
        p.makeReference();
    }
}
