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

/**
 * Represents a path in Shiro. A path is an identifier in Shiro. 
 * It can be used to identify node definitions (Point), ports within
 * node definitions (Point.x), and port values (x[0] or x["first"]).
 * 
 * A path is represented as a list of strings instead of a string like 
 * "Area.length". This is done to reduce the amount of string processing required
 * when working with paths.
 * 
 * A path has a "path head." The path head refers to the string the pathHead index
 * points to. You can non-destructively pop(or increment) the head of path to 
 * move the path head down the list of strings that represent the path. 
 * This allows the path to be shortened as each part of the path is processed 
 * without losing the path as a whole. For example:
 * <code>
 * Path p = new Path("Area", "Length");
 * System.out.println(p.getCurrentPathHead()); // "Area"
 * p.popPathHead()
 * System.out.println(p.getCurrentPathHead()); // "Length"
 * </code>
 *
 * @author jeffreyguenther
 */
public class Path {

    private List<String> path;      // Strings that make up the path
    private String indexKey = null; // A port index name
    private int index = -1;         // A port index number
    private int pathHead;           // index of the path head
    private boolean isReference = false;    // indicates whether the path should be treated as a reference(pointer)

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
     * Create a path
     *
     * @param path list of strings making up the path
     */
    public Path(List<String> path) {
        this.path = path;
        this.pathHead = 0;
    }

    /**
     * Create a path
     *
     * @param path list of strings making up the path
     * @param index path index; a nonnegative number
     */
    public Path(List<String> path, int index) {
        this(path);
        this.index = index;
    }

    /**
     * Create a path
     *
     * @param path list of strings that make up the path
     * @param key index key; a nonempty string
     */
    public Path(List<String> path, String key) {
        this(path);
        this.indexKey = key;
    }

    /**
     * *
     * Determine if a path points to a port index. A path that points to a port
     * will be have only one element or the path offset will have been
     * incremented such that only one element remains in the list of path
     * strings.
     *
     * @return true if it is a port, otherwise false
     */
    public boolean isPathToPortIndex() {
        // determine if the current path head is a port
        return (path.size() - pathHead) == 1 && (index > -1 || indexKey != null);
    }

    /**
     * Determines if the path has any more elements
     *
     * @return
     */
    public boolean isEmpty() {
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
     * Get the index of path
     *
     * @return the integer index if it has one, otherwise -1
     */
    public int getIndex() {
        return index;
    }

    /**
     * *
     * Get the string index of the path
     *
     * @return the string index of path if it has one, otherwise null
     */
    public String getIndexKey() {
        return indexKey;
    }

    /**
     * Get path as a list of strings
     *
     * @return a list of strings that make up the path
     */
    public List<String> getPathParts() {
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
        for (String s : path) {
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
    public int getPathHead() {
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
            throw new RuntimeException("Path offset is outside range of path. "
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
    public String getCurrentPathHead() {
        return path.get(pathHead);
    }

    /**
     * *
     * Increment the path offset
     *
     * @return the current offset value
     */
    public int popPathHead() {
        return ++pathHead;
    }

    /**
     * *
     * Reset the path offset to zero
     *
     * @return the current offset value
     */
    public int resetPathHead() {
        pathHead = 0;
        return pathHead;
    }

    /**
     * *
     * Determine if the path has a string index
     *
     * @return true if is a string, false if not a string
     */
    public boolean hasStringIndex() {
        // if the indexKey is a string
        return indexKey != null && index == -1;
    }

    /**
     * *
     * Determine if the path has an integer index
     *
     * @return true if an integer, false otherwise
     */
    public boolean hasIntegerIndex() {
        // if the indexKey is not a string, but index is an integer
        return indexKey == null && index != -1;
    }
    
    /**
     * Determines if path has an index
     * @return true if the path has an integer or string index, otherwise false
     */
    public boolean hasIndex(){
        return hasIntegerIndex() || hasStringIndex();
    }

    public void makeReference(){
        isReference = true;
    }

    public boolean isReference() {
        return isReference;
    }

    /**
     * Return return a string of the path
     *
     * @return ( <list of path strings>, Index: <index>, Key: <indexKey>)
     */
    @Override
    public String toString() {
        return "(" + path.toString() + ", Index:" + index + ", Key:" + indexKey + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.path);
        hash = 53 * hash + Objects.hashCode(this.indexKey);
        hash = 53 * hash + this.index;
        hash = 53 * hash + this.pathHead;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Path other = (Path) obj;
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.indexKey, other.indexKey)) {
            return false;
        }
        if (this.index != other.index) {
            return false;
        }
        if (this.pathHead != other.pathHead) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toCode() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getPath());
//        
//        if(hasIntegerIndex()){
//            sb.append("[").append(getIndex()).append("]");
//        }else if(hasStringIndex()){
//            sb.append("[\"").append(getIndexKey()).append("\"]");
//        }
//        
//        return sb.toString();
//    }
    
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
     * Creates a path for port with the passed node as scope
     * @param node scope of the port
     * @param port name of the port
     * @return path representing the port
     */
//    public static Path createPathForPort(Node node, String port){
//        List<String> pathParts = new ArrayList<>();
//            pathParts.add(node.getName());
//            pathParts.add(port);
//        return new Path(node, pathParts);
//    }
    
    /**
     * Create a path from a string.
     * For example, "Area.length" will be turned into a path
     * object representing it. It should be noted that scope of
     * the path is null.
     * @param path the string to be turned into a path object
     * @return path created from the string
     */
    public static Path create(String path){
        String[] pathParts = path.split("\\.");
        return new Path(pathParts);
    }
}