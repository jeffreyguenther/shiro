package shiro.expressions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import shiro.*;

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
 * A path may not have its portReferenced, or scope defined if it is being
 * used to represent a simple path. For example, a simple path, like Point.x can,
 * be create to access a. In this case, the path object does not have a scope and is
 * used to encapsulate the dot separated strings.
 *
 * @author jeffreyguenther
 */
public class Path implements Expression {

    private List<String> path;      // Strings that make up the path
    private String indexKey = null; // A port index name
    private int index = -1;         // A port index number
    private Scope scope;            // The enclosing scope (a node)
    private Port portReferenced;    // port the path references
    private int pathHead;           // index of the path head

    /**
     * Default constructor
     */
    public Path() {
        this(null, null);
    }
    
    public Path(String...parts){
        this(null, Arrays.asList(parts));
    }

    /**
     * Create a path
     *
     * @param scope scope of the path
     * @param path list of strings making up the path
     */
    public Path(Scope scope, List<String> path) {
        this.path = path;
        this.scope = scope;
        this.pathHead = 0;
        portReferenced = null;
    }

    /**
     * Create a path
     *
     * @param scope scope of the path
     * @param path list of strings making up the path
     * @param index path index; a nonnegative number
     */
    public Path(Scope scope, List<String> path, int index) {
        this(scope, path);
        this.index = index;
        portReferenced = null;
    }

    /**
     * Create a path
     *
     * @param scope scope of the path
     * @param path list of strings that make up the path
     * @param key index key; a nonempty string
     */
    public Path(Scope scope, List<String> path, String key) {
        this(scope, path);
        this.indexKey = key;
        portReferenced = null;
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
        if ((path.size() - pathHead) == 1 && (index > -1 || indexKey != null)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determines if the path has any more elements
     *
     * @return
     */
    public boolean isEmpty() {
        // if the path head and length are equal then the path head is 1
        // greater than the length of the list and is therefore empty
        if (pathHead == path.size()) {
            return true;
        } else {
            return false;
        }
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
        if (indexKey != null && index == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * *
     * Determine if the path has an integer index
     *
     * @return true if an integer, false otherwise
     */
    public boolean hasIntegerIndex() {
        // if the indexKey is not a string, but index is an integer
        if (indexKey == null && index != -1) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Determines if path has an index
     * @return true if the path has an integer or string index, otherwise false
     */
    public boolean hasIndex(){
        return hasIntegerIndex() || hasStringIndex();
    }

    /**
     * Get the value of the port in the referenced port
     * @return the value of the port in the referenced port
     * @throws PortIndexNotFoundException - if path is not found
     */
    private Value getValue() throws PortIndexNotFoundException {
        return portReferenced.getValueForPath(this);
    }

    /**
     * Get the scope of the path
     *
     * @return the scope of the path. Returns null if there is no scope defined
     */
    public Scope getScope() {
        return scope;
    }

    /**
     * Get the ports depended upon. This method returns a set because the set is
     * recursively added to for all paths in an expression.
     *
     * @return a set of one port is returned
     * @throws PathNotFoundException
     */
    @Override
    public Set<Port> getPortsDependedOn() throws PathNotFoundException {
        Set<Port> ports = new HashSet<>();
        portReferenced = (Port) scope.resolvePath(this);
        ports.add(portReferenced);
        return ports;
    }

    /**
     * Evaluate the path expression
     *
     * @return the value for the path expression
     * @throws PortNotActiveException
     */
    @Override
    public Value evaluate() throws PortNotActiveException {
        Value v = null; // value of the path to be output

        // look up the port
        try {
            // look up the value at a given path
            portReferenced = (Port) scope.resolvePath(this);

            // if the port is active return the value
            if (portReferenced.isActive()) {
                // get the value
                v = getValue();
            } else {
                throw new PortNotActiveException();
            }
        } catch (PathNotFoundException | PortIndexNotFoundException ex) {
            Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
        }

        return v;
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
    public String toCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getPath());
        
        if(hasIntegerIndex()){
            sb.append("[").append(getIndex()).append("]");
        }else if(hasStringIndex()){
            sb.append("[\"").append(getIndexKey()).append("\"]");
        }
        
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.path);
        hash = 67 * hash + Objects.hashCode(this.indexKey);
        hash = 67 * hash + this.index;
        hash = 67 * hash + Objects.hashCode(this.scope);
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
        if (!Objects.equals(this.scope, other.scope)) {
            return false;
        }
        return true;
    }
}
