package org.shirolang.values;

import java.util.Optional;

/**
 * Describes part of a path
 */
public class PathSegment {
    private SegmentType segmentType;
    private String key = null;
    private Integer index = null;

    public PathSegment(SegmentType type, String key){
        segmentType = type;
        this.key = key;
    }

    public PathSegment(SegmentType type, int index){
        if(type.isPlain()){
            throw new IllegalArgumentException("Cannot create a plain path segment with an index.");
        }

        segmentType = type;
        this.index = index;
    }

    public PathSegment(String key){
        segmentType = SegmentType.PLAIN;
        this.key = key;
    }

    public SegmentType getSegmentType() {
        return segmentType;
    }

    public Optional<Integer> getIndex() {
        return Optional.ofNullable(index);
    }

    public Optional<String> getKey() {
        return Optional.ofNullable(key);
    }
}
