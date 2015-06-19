package org.shirolang.values;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests a path segment
 */
public class PathSegmentTest {
    @Test
    public void constructors(){
        PathSegment simpleSegment = new PathSegment("x");
        Assert.assertEquals("Should be OUTPUT", SegmentType.PLAIN, simpleSegment.getType());
        Assert.assertFalse("Should not have index", simpleSegment.getIndex().isPresent());
        Assert.assertEquals("Should have the key", "x", simpleSegment.getKey().get());
        Assert.assertEquals("x", simpleSegment.toString());

        PathSegment plainSegment = new PathSegment(SegmentType.PLAIN, "a");
        Assert.assertEquals("Should be PLAIN", SegmentType.PLAIN, plainSegment.getType());
        Assert.assertFalse("Should not have an index", plainSegment.getIndex().isPresent());
        Assert.assertEquals("Should have the key \"a\"", "a", plainSegment.getKey().get());
        Assert.assertEquals("a", plainSegment.toString());

        PathSegment indexInputSegment = new PathSegment(SegmentType.INPUT, 0);
        Assert.assertEquals("Should be INPUT", SegmentType.INPUT, indexInputSegment.getType());
        Assert.assertFalse("Should not have key", indexInputSegment.getKey().isPresent());
        Assert.assertEquals("Should have the index", new Integer(0), indexInputSegment.getIndex().get());
        Assert.assertEquals("inputs[0]", indexInputSegment.toString());

        PathSegment indexOutputSegment = new PathSegment(SegmentType.OUTPUT, 0);
        Assert.assertEquals("Should be OUTPUT", SegmentType.OUTPUT, indexOutputSegment.getType());
        Assert.assertFalse("Should not have key", indexOutputSegment.getKey().isPresent());
        Assert.assertEquals("Should have the index", new Integer(0), indexOutputSegment.getIndex().get());
        Assert.assertEquals("outputs[0]", indexOutputSegment.toString());

        PathSegment keyInputSegment = new PathSegment(SegmentType.INPUT, "x");
        Assert.assertEquals("Should be INPUT", SegmentType.INPUT, keyInputSegment.getType());
        Assert.assertFalse("Should not have index", keyInputSegment.getIndex().isPresent());
        Assert.assertEquals("Should have the key", "x", keyInputSegment.getKey().get());
        Assert.assertEquals("inputs[\"x\"]", keyInputSegment.toString());

        PathSegment keyOutputSegment = new PathSegment(SegmentType.OUTPUT, "x");
        Assert.assertEquals("Should be OUTPUT", SegmentType.OUTPUT, keyOutputSegment.getType());
        Assert.assertFalse("Should not have index", keyOutputSegment.getIndex().isPresent());
        Assert.assertEquals("Should have the key", "x", keyOutputSegment.getKey().get());
        Assert.assertEquals("outputs[\"x\"]", keyOutputSegment.toString());
    }
}
