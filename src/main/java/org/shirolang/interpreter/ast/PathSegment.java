package org.shirolang.interpreter.ast;

import org.shirolang.values.SegmentType;

import java.util.Optional;

/**
 * Describes part of a path
 */
public class PathSegment {
    private static final String ACTIVE_KEYWORD = "active";
    private SegmentType type;
    private String key = null;
    private Integer index = null;

    /**
     * Creates a segment with the type and key
     * @param type type of the segment
     * @param key key of the segment. Used to represent "a", or "outputs["a"]"
     */
    public PathSegment(SegmentType type, String key){
        this.type = type;
        this.key = key;
    }

    /**
     * Creates a segment with the type and key*
     * @param type type of the segment
     * @param index index of the segment. Used to represent inputs[0] or outputs[0]
     * @throws IllegalArgumentException if you try to create a SIMPLE segment with an index
     */
    public PathSegment(SegmentType type, int index) throws IllegalArgumentException {
        if(type.isSimple()){
            throw new IllegalArgumentException("Cannot create a plain path segment with an index.");
        }

        this.type = type;
        this.index = index;
    }

    /**
     * Creates a SIMPLE path segment, like "a" in a.b.c
     * @param key the value of the segment
     */
    public PathSegment(String key){
        type = SegmentType.SIMPLE;
        this.key = key;
    }

    /**
     * Gets the type of the segment
     * @return the type of the segment
     */
    public SegmentType getType() {
        return type;
    }

    /**
     * Gets the index of the segment
     * @return an optional representing the index of the segment
     */
    public Optional<Integer> getIndex() {
        return Optional.ofNullable(index);
    }

    /**
     * Gets the key of the segment
     * @return an optional representing the key of the segment
     */
    public Optional<String> getKey() {
        return Optional.ofNullable(key);
    }

    /**
     * Gets whether the path segment is simple. For example,
     * a in a.b.c
     * @return true if there is a key and the type is simple
     */
    public boolean isSimple(){
        return getKey().isPresent() && type.isSimple();
    }

    /**
     * Gets whether the path segment references an input
     * @return true if the path segment's type is INPUT, otherwise false
     */
    public boolean isInput(){
        return type.isInput();
    }

    /**
     * Gets whether the path segment references an output
     * @return true if the path segment's type is OUTPUT, otherwise false
     */
    public boolean isOutput(){
        return type.isOutput();
    }

    /**
     * Gets whether the path segment is the keyword "active"
     * @return true if the type is simple and there is a key present that equals "active"
     */
    public boolean isActiveKeyword(){
        return type.isSimple() && getKey().isPresent() && getKey().get().equals(ACTIVE_KEYWORD);
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

    /**
     * Gets the accessor value
     * @return the accessor value as a string
     */
    public String accessorValue(){
        if(getKey().isPresent()){
          return "" + getKey().get();
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
