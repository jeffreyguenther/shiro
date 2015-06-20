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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * Represents a path in Shiro. A path is a qualified identifier in Shiro.
 * It can be used to identify port values (p.x.outputs[0] or x.inputs["first"]).
 * 
 * A path is represented as a list of {@code PathSegments} instead of a string like
 * "area.length". This is done to reduce the amount of string processing required
 * when working with paths.
 * 
 * A path has a "path head." The path head refers to the path segment the pathHead index
 * points to. You can non-destructively pop(or increment) the head of path to 
 * move the path head down the list of strings that represent the path. 
 * This allows the path to be shortened as each part of the path is processed 
 * without losing the path as a whole. For example:
 * <code>
 * Path p = new Path("area", "Length");
 * System.out.println(p.getCurrentHead()); // "area"
 * p.popHead()
 * System.out.println(p.getCurrentHead()); // "length"
 * </code>
 */
public class Path {
    private List<PathSegment> path;         // Strings that make up the path
    private int pathHead;                   // index of the path head
    private boolean isReference = false;    // indicates whether the path should be treated as a reference(pointer)
    private boolean isSelector = false;

    /**
     * Default constructor
     */
    public Path() {
        this(Collections.emptyList());
    }
    
    public Path(String...parts){
        this(Arrays.asList(parts));
    }

    /**
     * Creates a path of simple path segments
     *
     * @param path list of strings making up the path
     */
    public Path(List<String> path) {
        this.path = path.stream().map(PathSegment::toPathSegment).collect(toList());
        this.pathHead = 0;
    }

    /**
     * Adds the current segment to the path
     * Delegates to Collection.add
     * @param segment the segement to add to the path
     * @return whether the element was added to the collection
     */
    public boolean addSegment(PathSegment segment){
        return path.add(segment);
    }

    /**
     * Determines if the path has any more elements
     *
     * @return
     */
    public boolean hasSegments() {
        // if the path head and length are equal then the path head is 1
        // greater than the length of the list and is therefore empty
        return pathHead == path.size();
    }

    /**
     * Determine if a path has only one element remaining. 
     * A path to a port will be at the end of the path. For example, the path
     * <code>update["value"]</code> will return true. 
     * 
     * A path that points to a node will also be at the end of a path. 
     * For example, the path <code>Area.length</code> when the pathHead points 
     * to the last element in the list will also return true.
     *
     * @return true if the path is at the end, otherwise false
     */
    public boolean isAtEnd() {
        return pathHead == path.size() - 1;
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
        return pathHead;
    }

    /**
     * *
     * Set the path offset. See getter explanation for how it is used.
     *
     * @param pathOffset
     */
    public void setPathHead(int pathOffset) {
        if(pathOffset > path.size() - 1 || pathOffset < 0){
            throw new IllegalArgumentException("Path offset is outside range of path. "
                    + "Range is " + 0 + "-" + (path.size() - 1) + " inclusive.");
        }
        
        this.pathHead = pathOffset;
    }

    /**
     * *
     * Based on the current offset, get the head of the path
     *
     * @return first element of the path as calculated by the offset
     */
    public PathSegment getCurrentHead() {
        return path.get(pathHead);
    }

    /**
     * *
     * Increment the path offset
     *
     * @return the current offset value
     */
    public int popHead() {
        return ++pathHead;
    }

    /**
     * *
     * Reset the path offset to zero
     *
     * @return the current offset value
     */
    public int resetHead() {
        pathHead = 0;
        return pathHead;
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
     * Returns whether the path is a reference
     * @return true if the path is a reference, otherwise false
     */
    public boolean isReference() {
        return isReference;
    }

    /**
     * Make the path a selector
     * This method is used to represent paths like "@a" and "@Point.x"
     */
    public void makeSelector(){
        if(isReference){
            throw new RuntimeException("Path is a reference. Cannot be made a selector.");
        }

        isSelector = true;
    }

    /**
     * Returns whether the path is a selector
     * @return true if the path is a selector, otherwise false
     */
    public boolean isSelector(){
        return isSelector;
    }

    /**
     * Return return a string of the path
     *
     * @return (<list of path strings>)
     */
    @Override
    public String toString() {
        return "(" + path.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Path path1 = (Path) o;

        if (pathHead != path1.pathHead) return false;
        if (isReference != path1.isReference) return false;
        if (isSelector != path1.isSelector) return false;
        return path.equals(path1.path);

    }

    @Override
    public int hashCode() {
        int result = path.hashCode();
        result = 31 * result + pathHead;
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
     * Create the node's full name
     * @param parentFullName the node's parent's full name
     * @param nodeName name of the node
     * @return the node's full name
     */
    public static String createFullName(String parentFullName, String nodeName) {
        if("".equals(parentFullName)){
           return nodeName;
        }else{
            return parentFullName + "." + nodeName;
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