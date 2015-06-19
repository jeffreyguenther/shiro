package org.shirolang.values;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests a path segment
 */
public class PathSegmentTest {
    @Test
    public void constructors(){
        PathSegment plainSegment = new PathSegment(SegmentType.PLAIN, "a");
        Assert.assertEquals("Should be PLAIN", SegmentType.PLAIN, plainSegment.getSegmentType());
        Assert.assertFalse("Should not have an index", plainSegment.getIndex().isPresent());
        Assert.assertEquals("Should have the key \"a\"", "a", plainSegment.getKey().get());

        PathSegment indexInputSegment = new PathSegment(SegmentType.INPUT, 0);
        Assert.assertEquals("Should be INPUT", SegmentType.INPUT, indexInputSegment.getSegmentType());
        Assert.assertFalse("Should not have key", indexInputSegment.getKey().isPresent());
        Assert.assertEquals("Should have the index", new Integer(0), indexInputSegment.getIndex().get());

        PathSegment indexOutputSegment = new PathSegment(SegmentType.OUTPUT, 0);
        Assert.assertEquals("Should be OUTPUT", SegmentType.OUTPUT, indexOutputSegment.getSegmentType());
        Assert.assertFalse("Should not have key", indexOutputSegment.getKey().isPresent());
        Assert.assertEquals("Should have the index", new Integer(0), indexOutputSegment.getIndex().get());

        PathSegment keyInputSegment = new PathSegment(SegmentType.INPUT, "x");
        Assert.assertEquals("Should be INPUT", SegmentType.INPUT, keyInputSegment.getSegmentType());
        Assert.assertFalse("Should not have index", keyInputSegment.getIndex().isPresent());
        Assert.assertEquals("Should have the key", "x", keyInputSegment.getKey().get());

        PathSegment keyOutputSegment = new PathSegment(SegmentType.OUTPUT, "x");
        Assert.assertEquals("Should be OUTPUT", SegmentType.OUTPUT, keyOutputSegment.getSegmentType());
        Assert.assertFalse("Should not have index", keyOutputSegment.getIndex().isPresent());
        Assert.assertEquals("Should have the key", "x", keyOutputSegment.getKey().get());

        PathSegment simpleSegment = new PathSegment("x");
        Assert.assertEquals("Should be OUTPUT", SegmentType.OUTPUT, keyOutputSegment.getSegmentType());
        Assert.assertFalse("Should not have index", keyOutputSegment.getIndex().isPresent());
        Assert.assertEquals("Should have the key", "x", keyOutputSegment.getKey().get());
    }
}
