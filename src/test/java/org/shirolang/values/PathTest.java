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
import org.shirolang.interpreter.ast.Path;
import org.shirolang.interpreter.ast.PathSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * Test a path expression object
 */
public class PathTest {
    @Test
    public void constructors(){
        Path p1 = new Path();
        Assert.assertFalse(p1.hasSegmentsLeft());
        Assert.assertEquals("path head should be 0", 0, p1.getHead());

        Path p2 = new Path("hello", "world");
        Assert.assertTrue(p2.hasSegmentsLeft());
        Assert.assertEquals("path head should be 0", 0, p2.getHead());
        Assert.assertEquals("should be 'Hello.World'", "hello.world", p2.getPath());
    }

    @Test
    public void addSegment(){
        Path p = new Path();
        PathSegment segment = new PathSegment("a");
        p.addSegment(segment);

        Assert.assertEquals(1, p.getSegments().size());
        Assert.assertEquals("a", p.getSegmentAtHead().getKey().get());
    }

    @Test
    public void movePathHead(){
        Path p = new Path("a", "b", "c", "d", "e", "f");
        Assert.assertEquals("a", p.getSegmentAtHead().getKey().get());
        p.advanceHead(); // move to b
        Assert.assertEquals("b", p.getSegmentAtHead().getKey().get());
        Assert.assertEquals(1, p.getHead());

        p.advanceHead(); //move to c
        p.advanceHead(); // move to d
        Assert.assertFalse(p.atSecondLast());

        p.advanceHead(); // move to e
        Assert.assertTrue(p.atSecondLast());

        p.resetHead();
        Assert.assertEquals("a", p.getSegmentAtHead().getKey().get());
        Assert.assertEquals(0, p.getHead());
    }

    @Test
    public void setHead(){
        Path p = new Path("a", "b", "c", "d", "e", "f");
        p.setHead(4);
        Assert.assertEquals("e", p.getSegmentAtHead().getKey().get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setHeadOutOfBounds(){
        Path p = new Path("a", "b", "c", "d", "e", "f");
        p.setHead(7);
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
        Assert.assertEquals("(~ [])", p.toString());
    }

    @Test
    public void isSelector(){
        Path p = new Path();
        Assert.assertFalse(p.isSelector());
        p.makeSelector();
        Assert.assertTrue(p.isSelector());
    }

    @Test
    public void referencesPortValue(){
        Path p = new Path();
        Assert.assertFalse(p.doesReferencePortValue());
        p.setReferencesPortValue(true);
        Assert.assertTrue(p.doesReferencePortValue());
    }

    @Test(expected = RuntimeException.class)
    public void alreadySelectorMakeReference(){
        Path p = new Path();
        p.makeSelector();
        p.makeReference();
    }
}
