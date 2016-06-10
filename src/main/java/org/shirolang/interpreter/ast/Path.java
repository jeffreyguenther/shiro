package org.shirolang.interpreter.ast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Represents a path in Shiro. A path is a qualified identifier in Shiro.
 * It can be used to identify port values (p.x.outputs[0] or x.inputs["first"]).
 * 
 * A path is represented as a list of {@code PathSegment}s instead of a string like
 * "area.length". This is done to reduce the amount of string processing required
 * when working with paths.
 * 
 * A path has a "path head." The path head refers to the path segment the head index
 * points to. You can non-destructively pop(or increment) the head of path to 
 * move the path head down the list of strings that represent the path. 
 * This allows the path to be shortened as each part of the path is processed 
 * without losing the path as a whole. For example:
 * <code>
 * Path p = new Path("area", "Length");
 * System.out.println(p.getSegmentAtHead()); // "area"
 * p.advanceHead()
 * System.out.println(p.getSegmentAtHead()); // "length"
 * </code>
 */
public class Path implements Codeable{
    private List<PathSegment> path;         // Strings that make up the path
    private int head;                       // index of the path head
    private boolean isReference = false;    // indicates whether the path should be treated as a reference(pointer)
    private boolean isSelector = false;     // indicates whether the path should be treated as a selector, or path literal
    private boolean referencesPortValue = false; //indicates whether the path terminates with port values

    /**
     * Default constructor
     */
    public Path() {
        this(Collections.emptyList());
    }

    /**
     * Create a path from a splat of strings
     * @param parts the segments of the path
     */
    public Path(String...parts){
        this(Arrays.asList(parts));
    }

    /**
     * Creates a path of simple string path segments
     * @param path list of strings making up the path
     */
    public Path(List<String> path) {
        this.path = path.stream().map(PathSegment::toPathSegment).collect(toList());
        this.head = 0;
    }

    /**
     * Adds the current segment to the path
     * Delegates to Collection.add
     * @param segment the segment to add to the path
     * @return whether the element was added to the collection
     */
    public boolean addSegment(PathSegment segment){
        return path.add(segment);
    }

    /**
     * Determines if the path has any more elements
     *
     * @return true if the path has more segments, otherwise false
     * Will return true if the head is at the end of the list of segments.
     * Only when it's advanced past the end of the path will it return false
     */
    public boolean hasSegmentsLeft() {
        return head < path.size();
    }

    /**
     * Determine if a path has only one element remaining. 
     * A path to a port will be at the end of the path. For example, the path
     * <code>update["value"]</code> will return true. 
     * 
     * A path that points to a node will also be at the end of a path. 
     * For example, the path <code>Area.length</code> when the head points
     * to the last element in the list will also return true.
     *
     * @return true if the path is at the end, otherwise false
     */
    public boolean isAtEnd() {
        return head == path.size() - 1;
    }

    /**
     * Gets the path segments
     *
     * @return a list of path segments
     */
    public List<PathSegment> getSegments() {
        return path;
    }

    /**
     * Get the path as a dot separated string. For example, Area.length Note:
     * this string does not include the index
     *
     * @return the string representing the path.
     */
    public String getPath() {
        StringBuilder sb = new StringBuilder();
        for (PathSegment s : path) {
            sb.append(".").append(s);
        }
        sb.replace(0, 1, "");
        return sb.toString();
    }

    /**
     * *
     * Get the current path offset. Path offsets are used to preserve the list
     * while recursing down the containment hierarchy
     *
     * @return the current path offset
     */
    public int getHead() {
        return head;
    }

    /**
     * Set the path offset. See getter explanation for how it is used.
     * @param pathOffset
     */
    public void setHead(int pathOffset) {
        if(pathOffset > path.size() - 1 || pathOffset < 0){
            throw new IllegalArgumentException("Path offset is outside range of path. "
                    + "Range is " + 0 + "-" + (path.size() - 1) + " inclusive.");
        }
        
        this.head = pathOffset;
    }

    /**
     * Gets the path segment referred to by the path head
     *
     * @return path segment at the path head
     */
    public PathSegment getSegmentAtHead() {
        return path.get(head);
    }

    /**
     * Increment the path offset
     *
     * @return the current offset value
     */
    public int advanceHead() {
        return ++head;
    }

    /**
     * Resets the path head to the start of the path, index 0.
     * @return the current offset value
     */
    public int resetHead() {
        head = 0;
        return head;
    }

    /**
     * Make the path a reference
     * Use this method to represent paths like "~a" and "~Point"
     */
    public void makeReference(){
        if(isSelector){
            throw new RuntimeException("Path is a selector. Cannot be made a reference.");
        }

        isReference = true;
    }

    /**
     * Gets whether the path is a reference
     * @return true if the path is a reference, otherwise false
     */
    public boolean isReference() {
        return isReference;
    }

    /**
     * Makes the path a selector
     * This method is used to represent paths like "@a" and "@Point.x"
     */
    public void makeSelector(){
        if(isReference){
            throw new RuntimeException("Path is a reference. Cannot be made a selector.");
        }

        isSelector = true;
    }

    /**
     * Gets whether the path is a selector
     * @return true if the path is a selector, otherwise false
     */
    public boolean isSelector(){
        return isSelector;
    }

    /**
     * Returns whether the path references a port value
     * @return true it references a port value, otherwise false
     */
    public boolean doesReferencePortValue() {
        return referencesPortValue;
    }

    /**
     * Sets whether the path references a port value
     * @param referencesPortValue whether the path references a port value
     */
    public void setReferencesPortValue(boolean referencesPortValue) {
        this.referencesPortValue = referencesPortValue;
    }

    /**
     * Gets the last element in the path
     * @return the last path segment in the path
     */
    public PathSegment getLast(){
        return path.get(path.size() - 1);
    }

    /**
     * Determines if path is at second last segment
     * Uses <code>head == (path.size() - 2)</code> to calculate
     * @return true if the head is at second last position, otherwise fase
     */
    public boolean atSecondLast(){
        return head == (path.size() - 2);
    }

    /**
     * Gets a string representation of the path
     * @return (list of path strings)
     */
    @Override
    public String toString() {
        String ref = isReference() ? "~ " : "";
        return "("+ ref + path.toString() + ")";
    }

    @Override
    public String toCode() {
        String prefix = prefix();
        return prefix + path.stream().map(PathSegment::toString).collect(Collectors.joining("."));
    }

    private String prefix(){
        if(isReference()){
            return "~";
        }else if(isSelector) {
            return "@";
        }else{
            return "";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Path path1 = (Path) o;

        if (head != path1.head) return false;
        if (isReference != path1.isReference) return false;
        if (isSelector != path1.isSelector) return false;
        return path.equals(path1.path);

    }

    @Override
    public int hashCode() {
        int result = path.hashCode();
        result = 31 * result + head;
        result = 31 * result + (isReference ? 1 : 0);
        result = 31 * result + (isSelector ? 1 : 0);
        return result;
    }

    /**
     * Extracts the name of a port from a full path name.
     * Given a full name like "Area.length", the results is "length"
     * @param fullName fully qualified name of the port
     * @return the name of the port
     */
    public static String getNameFromPath(String fullName){
        // update name
        int lastDotIndex = fullName.lastIndexOf(".");
        if( lastDotIndex != -1){
            return fullName.substring(lastDotIndex + 1, fullName.length());
        }else{
            return fullName;
        }
    }
    
    /**
     * Replaces the name in a full name
     * If you want to change Area.update to Area.calc
     * pass fullName = "Area.update", and name ="calc"
     * @param fullName old fully qualified name
     * @param name new name
     * @return the new fully qualified name
     */
    public static String replaceNameInPath(String fullName, String name){
        
        // Check if the fullName is a path.
        if(fullName.contains(".")){
            int prefixIndex = fullName.lastIndexOf(".");
            String prefix = fullName.substring(0, prefixIndex);
            return prefix + "." + name;
        }else{
            return name;
        }
    }
    
    /***
     * Create the fully qualified name for port or node
     * @param parentFullName the parent scope's full name
     * @param name name of the port or node
     * @return the fully qualified name
     */
    public static String createFullName(String parentFullName, String name) {
        if("".equals(parentFullName)){
           return name;
        }else{
            return parentFullName + "." + name;
        }
    }
    
    /**
     * Create a path from a string.
     * For example, "area.length" will be turned into a path
     * object representing it.
     * @param path the string to be turned into a path object
     * @return path created from the string
     */
    public static Path create(String path){
        String[] pathParts = path.split("\\.");
        return new Path(pathParts);
    }

    /**
     * Create a path from a string and make it a reference
     * For example, "area.length" will be turned into a path
     * object representing it.
     * @param path the string to be turned into a path object
     * @return path created from the string that has it's isReference
     * flag set to true
     */
    public static Path createReference(String path){
        Path p = create(path);
        p.makeReference();
        return p;
    }

    /**
     * Create a path from a string and make it a selector
     * For example, "area.length" will be turned into a path
     * object representing it.
     * @param path the string to be turned into a path object
     * @return path created from the string that has it's isSelector
     * flag set to true
     */
    public static Path createSelector(String path){
        Path p = create(path);
        p.makeSelector();
        return p;
    }
}