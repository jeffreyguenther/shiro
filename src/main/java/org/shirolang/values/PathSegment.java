package org.shirolang.values;

import java.util.Optional;

/**
 * Describes part of a path
 */
public class PathSegment {
    private SegmentType type;
    private String key = null;
    private Integer index = null;

    public PathSegment(SegmentType type, String key){
        this.type = type;
        this.key = key;
    }

    public PathSegment(SegmentType type, int index){
        if(type.isPlain()){
            throw new IllegalArgumentException("Cannot create a plain path segment with an index.");
        }

        this.type = type;
        this.index = index;
    }

    public PathSegment(String key){
        type = SegmentType.PLAIN;
        this.key = key;
    }

    public SegmentType getType() {
        return type;
    }

    public Optional<Integer> getIndex() {
        return Optional.ofNullable(index);
    }

    public Optional<String> getKey() {
        return Optional.ofNullable(key);
    }

    @Override
    public String toString() {
        switch(type){
            case PLAIN:
                return key;
            case INPUT:
                return "inputs[" + accessorValue() + "]";
            case OUTPUT:
                return "outputs[" + accessorValue() + "]";

        }
        return super.toString();
    }

    private String accessorValue(){
        if(getKey().isPresent()){
          return "\"" + getKey().get() + "\"";
        }else if(getIndex().isPresent()){
            return "" + getIndex().get();
        }else{
            throw new RuntimeException("Path segment has neither a key or index. It's lost in space.");
        }
    }
}
