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
        type = SegmentType.SIMPLE;
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

    /**
     * Convert a string to a path segment
     * @param segment the string to converted
     * @return returns a SIMPLE
     */
    public static PathSegment toPathSegment(String segment){
        return new PathSegment(segment);
    }

    @Override
    public String toString() {
        switch(type){
            case SIMPLE:
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PathSegment that = (PathSegment) o;

        if (type != that.type) return false;
        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        return !(index != null ? !index.equals(that.index) : that.index != null);

    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (index != null ? index.hashCode() : 0);
        return result;
    }
}
