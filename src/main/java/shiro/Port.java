package shiro;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import shiro.events.PortEvent;
import shiro.events.PortEventListener;
import shiro.expressions.Expression;
import shiro.expressions.Path;
import shiro.functions.MultiFunction;

/**
 * Definition of a port (node) in a dependency graph
 * 
 * @author jeffreyguenther
 */
public class Port implements Symbol{
    // type of port
    private PortType type;              
    // arguements to be passed to the multifunction
    private List<Expression> arguments;
    // multi function that is evaluted
    private MultiFunction function;
    private ResultTuple valueTuple;  // the value of the port
    private String name;        // name of the port
    private String fullName;    // fully qualified path name of the port
    private boolean visited;    // whether the node has been visited during the sort traversal
    private boolean deleted;    // deleted flag for lazy delete
    private boolean isLeaf;     // leaf flag
    private boolean updated;    // flag to indicate the status of the port
    private boolean active;     // flag to indicate if port is active or not
    
    // set of all the ports this port has edges to
    // A port knows who it depends upon.
    private Set<Port> portsDependedOn;
    
    // references to objects listening for port events
    private Set<PortEventListener> listeners;

    /**
     * Create a port
     * @param fullName full name of the port
     * @param function multi-function to use when evaluating
     */
    public Port(String fullName, MultiFunction function){
        this.type = PortType.Input;
        this.function = function;
        this.arguments = new ArrayList<Expression>();
        this.valueTuple = null;
        this.name = PathHelpers.getNameFromPath(fullName);
        this.fullName = fullName; 
        this.visited = false;
        this.deleted = false;
        this.isLeaf = false;
        this.updated = false;
        this.active = true;
        portsDependedOn = new LinkedHashSet<Port>();
        
        // initialize listener lists
        listeners = new HashSet<PortEventListener>();
    }
    
    /**
     * Create a port
     * @param fullName with the given full name
     * @param args list of arguments
     * @param function multi-function to use when evaluating
     */
    public Port(String fullName, List<Expression> args, MultiFunction function) {
        this.type = PortType.Input;
        this.function = function;
        this.arguments = args;
        this.valueTuple = null;
        this.name = PathHelpers.getNameFromPath(fullName);
        this.fullName = fullName; 
        this.visited = false;
        this.deleted = false;
        this.isLeaf = false;
        this.updated = false;
        this.active = true;
        portsDependedOn = new LinkedHashSet<Port>();
        
        // initialize listener lists
        listeners = new HashSet<PortEventListener>();
    }
    
    /**
     * Default constructor.
     * Creates a port with no name, expressions, or multi-function
     */
    public Port(){
        this("", null);
    }
    
    /**
     * Evaluate the port's expression and fire all associate events.
     */
    public void evaluate(){
        if(isActive()){
            // Create a list to store the result of the evaluated expressions
            List<Value> args = new ArrayList<Value>();
            
            // evaluate each of the expressions in the arguments list
            for(Expression exp: arguments){
                try {
                    // pass the arguments by value
                    args.add(exp.evaluate());
                } catch (PortNotActiveException ex) {
                    Logger.getLogger(Port.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // evaluate the function
            valueTuple = function.evaluate(args);
            updated = true;
            firePortEvent(name + " evaluated.");
        }
    }

    /**
     * Set the arguments for the port
     * @param arguments to set
     */
    public void setArguments(List<Expression> arguments) {
        this.arguments = arguments;
    }
    
    /***
     * Get the list of arguments for the port's multifunction
     * @return the list of arguments
     */
    public List<Expression> getArguments(){
        return arguments;
    }
    
    /***
     * Get the argument for the specified position
     * @param pos of the argument
     * @return the expression at the given position <code>pos</code>
     */
    public Expression getArgumentForPosition(int pos){
        return arguments.get(pos);
    }
    
    /**
     * Set the argument for the given argument position
     * If the position is beyond the last index (size() -1),
     * the expression is appended to the list
     * @param pos of the argument
     * @param exp expression to passed
     */
    public void setArgumentForPosition(int pos, Expression exp){
        if(arguments.isEmpty() || pos > arguments.size() -1){
            arguments.add(exp);
        }else{
            arguments.set(pos, exp);
        }
    }
    
    /**
     * Get the value for the tuple name
     * Wraps the ResultTuple method
     * @param name of the index
     * @return the value of result tuple index
     */
    public Value getValueForName(String name){
       return valueTuple.getValueForName(name);
       
    }
    
    /**
     * Get the value for the tuple index
     * Wraps the ResultTuple method
     * @param index tuple index
     * @return the value of the tuple index
     */
    public Value getValueForIndex(int index){
        return valueTuple.getValueForIndex(index);
    }
    
    /**
     * Set the value of the tuple index
     * @param index of the tuple to set
     * @param v value to set
     */
    public void setValueForIndex(Integer index, Value v){
        valueTuple.setValueForIndex(index, v);
    }
    
    /**
     * Set the value for the index with the given name
     * @param name of the tuple index
     * @param v value to be stored
     */
    public void setValueForName(String name, Value v){
        valueTuple.setValueForName(name, v);
    }
    
    /***
     * Get the value for a path
     * @param pathToPort value to be returned
     * @return Value object for the path
     * @throws PortIndexNotFoundException  
     */
    public Value getValueForPath(Path pathToPort) throws PortIndexNotFoundException {
        Value value = null; // value of the index

        // determine which type of index the path has
        if (pathToPort.hasIntegerIndex()) {
            value = getValueForIndex(pathToPort.getIndex());
        } else if (pathToPort.hasStringIndex()) {
            value = getValueForName(pathToPort.getIndexKey());
        }else{
           throw new PortIndexNotFoundException(pathToPort.toString()
                  + "does not have an index.");
        }
        
        return value;
    }
    
    /**
     * Get the names of all the value indices for the port
     * @return a set of the value indices for the port
     */
    public Set<String> getValueIndexNames(){
        return valueTuple.getNames();
    }

    /**
     * Determine of the port is active
     * @return true/false depending if the port is active or not
     */
    public boolean isActive() {
        return active;
    }
    
    /***
     * Activate the port
     */
    public void activate(){
        active = true;
    }
    
    /**
     * Deactivate the port
     */
    public void deactivate(){
        active = false;
    }
    
    /***
     * Gets whether a port has been updated
     * @return whether a port has been updated
     */
    public boolean isUpdated() {
        return updated;
    }
    
    /**
     * Set the port as leaf
     */
    public void setLeaf(){
       isLeaf = true;
    }
    
    /**
     * Unset the port as a leaf
     */
    public void unSetLeaf(){
        isLeaf = false;
    }

    /***
     * Determine if the port is a leaf
     * @return if the port is a leaf
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * Mark the port to be deleted
     */
    public void markForDelete(){
        this.deleted = true;
    }

    /***
     * Determine if a node has been flagged as deleted
     * @return if the port has been deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /***
     * Remove an edge between the ports
     * @param p dependent port
     */
    public void removeDependencyOnPort(Port p){
        portsDependedOn.remove(p);
    }

    /**
     * Add a dependency between the port and the port passed
     * @param p port to be depended on
     */
    public void addDependencyOnPort(Port p){
        portsDependedOn.add(p);
    }
    
    /**
     * Determine if port has an edge between a port p
     * @param p 
     * @return whether there is a dependency 
     */
    public boolean doesDependOn(Port p){
        return portsDependedOn.contains(p);
    }

    /**
     * Check if the port has edges (dependencies)
     * @return boolean whether port has edges
     */
    public boolean hasDependencies(){
        return !portsDependedOn.isEmpty();
    }


    /****
     * Get the set of depended on ports
     * @return set of depended on ports
     */
    public Set<Port> getPortsDependedOn() {
        return portsDependedOn;
    }

    /**
     * Set the ports this port depends on
     * @param dependedOn set of ports depended on
     */
    public void setPortsDependedOn(Set<Port> dependedOn) {
        this.portsDependedOn = dependedOn;
    }

    /**
     * Get name of port
     * @return name of the port
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the port
     * @param name of the port
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the full path name to the port
     * @return the full path name of the node
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Set the path name of the port
     * @param path of the port
     */
    public void setFullName(String path) {
        this.fullName = path;
        this.name = PathHelpers.getNameFromPath(path);
    }
    
    /**
     * Determine if the port has been visited
     * @return true/false whether the port has been visited
     * Used in graph traversal.
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Set the visited flag for the port
     * @param visited the value visited should be set to
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    /**
     * Mark the port as visited
     */
    public void visit(){
        this.visited = true;
    }

    /**
     * Get the port's permissions
     * Port permissions are used to generate connecting points in the UI.
     * Computationally, there is no such thing as an input/output port.
     * This is a flag for simplification in the UI.
     * @return the port's read/write permissions
     */
    public Permissions getPermissions() {
        Permissions p;
        switch(type){
            case Input:
                p = Permissions.READWRITE;
                break;
            case Output:
                p = Permissions.READ;
                break;
            case Evaluated:
                p = Permissions.NONE;
                break;
            default:
                p = Permissions.NONE;
                break;
                
        }
        return p;
    }

    /**
     * Get a port's type
     * @return the type of a port
     */
    public PortType getPortType() {
        return type;
    }
    
    /**
     * Get the port's symbol type
     * @return SymbolType.Port
     */
    @Override
    public SymbolType getType(){
        return SymbolType.PORT;
    }
    
    /**
     * Set the type of a port
     * @param type of the port
     */
    public void setPortType(PortType type) {
        this.type = type;
    }
    
    /**
     * Get the value of the port
     * @return value of the port
     */
    public ResultTuple getValueTuple() {
        return valueTuple;
    }

    /**
     * Set the value of the port
     * @param value of the port to be set
     */
    public void setValueTuple(ResultTuple value) {
        this.valueTuple = value;
    }
    
    /**
     * Clear all of the port's listeners
     */
    public void clearListeners(){
        listeners.clear();
    }
    
    /***
     * Register an object as a listener to the port's events
     * @param l object to be registered
     */
    public synchronized void addPortEventListener(PortEventListener l){
        listeners.add(l);
        
    }
    
    /**
     * Unregister an object as a listener
     * @param l object to be removed as a listener
     */
    public synchronized void removePortEventListener(PortEventListener l){
        listeners.remove(l);
    }
    
   /**
    * Fire the port event
    * @param msg the message to be passed along with the event
    */
    protected synchronized void firePortEvent(String msg){
        PortEvent event = new PortEvent(this, msg);
        for(PortEventListener l: listeners){
            l.handlePortEvent(event);
        }
    }
    
    
    /**
     * Create a string form for the port object.
     * The format will be: [Port: <fullname>, Value: <value>, Expressions: <expressions>, deleted: true|false]
     * @return string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
          .append("Port: ")
          .append(fullName)
          .append(", ")
          .append("Value: ")
          .append(valueTuple)
          .append(", ")
          .append("Expression: ")
          .append(arguments)
          .append(", ")
          .append("deleted: ")
          .append(deleted)
          .append("]");
        return sb.toString();
    }
    
    public String toCode(){
        StringBuilder sb = new StringBuilder();
        if(getPortType().equals(PortType.Evaluated)){
            sb.append("eval");
        }else{
            sb.append("port");
        }
        
        sb.append(" ")
          .append(getName())
          .append(" ")
          .append(function.getName());
        
        if(!arguments.isEmpty()){
            sb.append("(");
            
            for(Expression e: arguments){
                sb.append(e.toCode())
                  .append(",");
            }
            
            sb.deleteCharAt(sb.length() - 1)
              .append(")");
        }
        
        return sb.toString();
    }
}
