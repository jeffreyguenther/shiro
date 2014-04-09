package shiro;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import shiro.dag.DependencyRelation;
import shiro.events.NodeEvent;
import shiro.events.NodeEventListener;
import shiro.events.PortEvent;
import shiro.events.PortEventListener;
import shiro.expressions.Expression;
import shiro.expressions.Path;

/**
 * Specifies a node in a subjunctive dependency graph.
 * A node is simply a reference to a collection of ports.
 * Each node has at least one special port called an "evaluated port." We will
 * call them e-ports for short. This port acts as an update method for the node.
 * All node dependent ports depend on the evaluated port.
 * @author jeffreyguenther
 */
public class Node implements PortEventListener, Container, Symbol{
    // the node's parent scope; The value maybe another node, subjunctive node,
    // or the global parametric system.
    private Scope parentScope;
    // type string for the node
    private String defPath;
    // node's fully qualified name
    private String fullName;
    // node name
    private String name;
    // flag to indicate if node is active
    private boolean active;
    // map of all  ports in node (includes e-ports); name -> Port
    private Map<String, Port> ports;
    // set of evaluated ports (e-port); name -> Port
    private Map<String, Port> evaluatedPorts;
    // currently select e-port
    private Port selectedEvaluatedPort;
    // map of the nested container objects; name -> Container
    private Map<String, Container> nestedContainers;

    // objects listening to the node
    private Set<NodeEventListener> listeners;
    

    /**
     * Create a node
     * @param type type string of the node such as "Point", or "Circle"
     * @param name name of the node
     * @param scope scope of the node
     */
    public Node(String type, String name, Scope scope) {
        // type of node
        this.defPath = type; 
        // set the enclosing scope
        this.parentScope = scope;
        // set the parent's full
        
        if(scope != null){
            this.fullName = Path.createFullName(scope.getFullName() , name);
        }else{
            this.fullName = name;
        }
        
        // set the name of the node
        this.name = name;
        // set the node active by default
        this.active = true;
        // create map for the node's ports
        ports = new HashMap<>();
        // create set for the node's evaluate ports
        evaluatedPorts = new HashMap<>();
        // no evaluated port is selected when the object is created
        selectedEvaluatedPort = null;
        //initialize map of nested contaiers
        nestedContainers = new HashMap<>();

        // intialize set of listeners
        listeners = new HashSet<>();
    }

    /**
     * Default constructor
     * Creates a node with no type, no name, and no scope
     */
    public Node(){
        this("", "", null);
    }

    /**
     * Nest a container inside the node.
     * @param c the container to be nested
     */
    @Override
    public void addNestedContainer(Container c){
        // add a contained item
        nestedContainers.put(c.getName(), c);
    }

    /**
     * Indicates whether the node has nested containers
     * @return true if node has nested containers, false if not
     */
    @Override
    public boolean hasNestedContainers(){
        return !nestedContainers.isEmpty();
    }

    /**
     * Get the set of nested nodes
     * @return set of nested nodes
     */
    @Override
    public Set<Container> getNestedContainers() {
        return (Set<Container>) nestedContainers.values();
    }

    public Node getNode(Path p){
        Node match = null;

        if(hasNestedContainers() && !p.isPathToPortIndex()){
            Container c_match = nestedContainers.get(p.getCurrentPathHead());
            match = (Node) c_match;
            if(match != null){
                match.getNode(p);
            }
        }else{
            return match;
        }

        return match;
    }

    /***
     * Add the evaluated port p to the node
     * Does not deal with dependencies. Dependencies are created by in DepGraph.
     * @param p port to be added as evaluated port
     */
    public void addEvaluatedPort(Port p){
        // add the port to both sets
        evaluatedPorts.put(p.getName(), p);
        addPort(p);
    }
    
    /**
     * Get the definition path of the node
     * @return the definition path of the node. Example, Point, or Area.Circle
     */
    public String getDefPath(){
        return defPath;
    }
    
    /**
     * Set the definition path of the node
     * @param path of the node's prototype
     */
    public void setDefPath(String path){
        this.defPath = path;
    }

    /**
     * Get the name of the node
     * @return the name of the node
     */
    @Override
    public String getName() {
        return name;
    }

    /***
     * Set the name of the node
     * @param name of the node to be set
     */
    public void setName(String name) {
        this.name = name;
        this.fullName = Path.replaceNameInPath(fullName, name);
    }

    /**
     * Get the node's full name
     * @return node's full name
     */
    @Override
    public String getFullName() {
        return fullName;
    }
    
    /**
     * Set the node's full name
     * @param fullName name to be set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
        this.name = Path.getNameFromPath(fullName);
    }

    /**
     * Get a path object representing the full name
     * @return a Path object corresponding to the full name.
     */
    @Override
    public Path getPath(){
    	List<String> pathParts = Arrays.asList(fullName.split("\\."));
    	return new Path(parentScope, pathParts);
    }

    /**
     * Get a reference to the scop object the node is contained in
     * @return a reference to the parent scope
     */
    public Scope getParentScope() {
        return parentScope;
    }

    /**
     * Set the parent scope
     * @param enclosingScope 
     */
    public void setParentScope(Scope enclosingScope) {
        this.parentScope = enclosingScope;
    }

    /**
     * Determine if the node is active
     * @return true/false depending if the node is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Activate the node.
     * Goes through all the ports the node references and sets them active.
     * Takes care to only activate the selected evaluated port. All other e-ports
     * are left inactive.
     */
    @Override
    public void activate() {
        // activate the node
        this.active = true;

        // Activate all the ports
        for(Port p: ports.values()){
            // except the unselected evaluated ports
            if(!evaluatedPorts.containsValue(p)){
                p.activate();
            }else if(evaluatedPorts.containsValue(p) && p.equals(selectedEvaluatedPort)){
                p.activate();
            }
        }

        // activate the nested containers
        if(hasNestedContainers()){
            for(Container c: nestedContainers.values()){
                c.activate();
            }
        }
    }

    /***
     * Deactivate the node by deactivating all of its ports.
     */
    @Override
    public void deactivate(){
        // Deactivate the node
        this.active = false;

        // Deactivate all the ports
        for(Port p: ports.values()){
            p.deactivate();
        }

        // deactivate the nested container
        if(hasNestedContainers()){
            for(Container c: nestedContainers.values()){
                c.deactivate();
            }
        }
    }

    /**
     * Add a port to the node
     * Registers the node as a listener to the port p's events
     * @param p port to be added
     */
    public void addPort(Port p){
        p.addPortEventListener(this);
        ports.put(p.getName(), p);
    }

    /**
     * Remove a port from the node
     * @param p port to be removed
     * @return true if remove was successful, otherwise false
     */
    public boolean removePort(Port p){
        if(ports.remove(p.getName()) != null){
            return true;
        }else{
            return false;
        }
    }

    /***
     * Gets a set of all the names of the ports
     * @return the set of the names of all of the ports in the node.
     */
    public Set<String> getPortNames(){
        Set<String> names = new HashSet<String>();
        for(Port p: ports.values()){
             // only give the names of the none evaluated ports
             if(!evaluatedPorts.containsValue(p)){
                names.add(p.getName());
             }
        }
        return names;
    }

    public Set<Port> getPorts(){
        return new LinkedHashSet<Port>(ports.values());
    }

    public Set<Port> getEvaluatedPorts(){
        return new LinkedHashSet<>(evaluatedPorts.values());
    }

    public Port getSelectedEvaluatedPort(){
        return selectedEvaluatedPort;
    }
    
    @Override
    public Symbol find(Path p) throws PathNotFoundException, PathNotAccessibleException{
        Symbol matched = null;
        
        if(ports.containsKey(p.getCurrentPathHead())){
            if(!evaluatedPorts.containsKey(p.getCurrentPathHead())){
                matched = ports.get(p.getCurrentPathHead());
            }else if(evaluatedPorts.containsKey(p.getCurrentPathHead())){
                throw new PathNotAccessibleException(p + " is not accessible. "
                        + "It is an evaluated port.");
            }
        }else{
            throw new PathNotFoundException(p + "cannot be found.");
        }
        
        return matched;
    }

    @Override
    public Symbol find(String path) throws PathNotFoundException, PathNotAccessibleException {
        return find(Path.createPath(path));
    }
    
    

    @Override
    public Symbol resolvePath(Path path) throws PathNotFoundException{
        Symbol portReferenced = null;

        // if the value is local to the current node
        if (path.isAtEnd()) {
            String portName = path.getCurrentPathHead();


            // check each port to see if it is a match
            portReferenced = ports.get(portName);
            path.resetPathHead();

        }else {
            // check the nested nodes
            if (hasNestedContainers()) {
                Scope nestedNodeMatch = nestedContainers.get(path.getCurrentPathHead());
                if(nestedNodeMatch != null){
                    // pop the head and begin exploring
                        path.popPathHead();
                        portReferenced = nestedNodeMatch.resolvePath(path);
                }
            }else{ //CASE: no nested nodes to check

                // reset the path offset to allow future downward traversal
                path.resetPathHead();
                // pop up to the next level of scope
                portReferenced = parentScope.resolvePath(path);
            }
        }

        return portReferenced;

    }

    @Override
    public Symbol resolvePath(String path) throws PathNotFoundException {
        return resolvePath(Path.createPath(path));
    }

    /**
     * Activate the evaluated port
     * @param key path of the port to be activated
     * @throws PathNotFoundException
     */
    @Override
    public void activate(Path key) throws PathNotFoundException {
        if(key.isAtEnd()){
            try {
                activateEvalPort(key.getCurrentPathHead());
            } catch (EvaluatedPortNotFoundException ex) {
                throw new PathNotFoundException(ex.getMessage());
            }
        }else{
            throw new PathNotFoundException(key + " was not found."
                    + " The path given is too long and points somewhere beyond "
                    + "this node.");
        }

    }

    /**
     * Active the evaluated port with the given name.
     * @param ePortName name of the evaluated port to be activated
     * @throws PathNotFoundException
     */
    @Override
    public void activate(String ePortName)throws PathNotFoundException{
        try {
            activateEvalPort(ePortName);
        } catch (EvaluatedPortNotFoundException ex) {
            throw new PathNotFoundException(ex.getMessage());
        }
    }

    private void activateEvalPort(String ePortName) throws EvaluatedPortNotFoundException{
        if(evaluatedPorts.containsKey(ePortName)){
            // activate the evaluated port
            selectedEvaluatedPort = evaluatedPorts.get(ePortName);
            selectedEvaluatedPort.activate();

            // deactive all other evaluated ports
            for(Port q: evaluatedPorts.values()){
                if(!q.equals(selectedEvaluatedPort)){
                    q.deactivate();
                }
            }
        }else{
            throw new EvaluatedPortNotFoundException(ePortName + " is not an evaluated port.");
        }
    }


    public Set<DependencyRelation<Port>> getDependencies() {
        // Store all dependency relations
        Set<DependencyRelation<Port>> deps = new HashSet<DependencyRelation<Port>>();

        // For each expression in the node's ports
        for (Port p : ports.values()) {
            
            // get the expressions
            List<Expression> exps = p.getArguments();
            
            // Resolve the dependencies for each expression.
            for (Expression e : exps) {
                try {
                    // Get the ports the expression depends on
                    Set<Port> portsDependedOn = e.getPortsDependedOn();
                    
                    // If the expression depends on other port
                    if (!portsDependedOn.isEmpty()) {
                        // create the dependencies
                        for (Port dependedOn : portsDependedOn) {
                            deps.add(new DependencyRelation<Port>(p, dependedOn));
                        }
                    }
                } catch (PathNotFoundException ex) {
                    Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return deps;
    }

    /**
     * Register listener with the node
     * @param l object that implements the interface
     */
    public synchronized void addNodeEventListener(NodeEventListener l){
        listeners.add(l);
    }

    /**
     * Unregister the listener with the node
     * @param l object that has been registered with the node as a listener
     */
    public synchronized void removeNodeEventListener(NodeEventListener l){
        listeners.remove(l);
    }

    /**
     * Fire the node event
     * @param msg to be passed to the listeners
     */
    private synchronized void fireNodeEvent(String msg){
        NodeEvent event = new NodeEvent(this, msg);
        for(NodeEventListener l: listeners){
            l.handleNodeEvent(event);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        // print node header
        sb.append(getFullName())
          .append(":")
          .append(getType())
          .append("\n");

        // print each port
        for(Port p: ports.values()){
            sb.append("\t")
              .append(p.getName())
              .append(": ")
              .append(p.toString())
              .append("\n");
        }

        if(hasNestedContainers()){
            sb.append(name)
              .append(" has the following nested nodes:\n");
            for(Container nested: getNestedContainers()) {
                sb.append(nested.toString());
            }
        }

        return sb.toString();
    }

    /*
     * Handle the port event
     * @param event port event to be handled
     */
    @Override
    public void handlePortEvent(PortEvent event) {
        // handle the port events bubbling up
        // fire the node event
        fireNodeEvent("Node updated.");
        System.out.println( getFullName() + ":" + getType() + " handled event: " + event.getMessage());
    }

    @Override
    public SymbolType getType() {
        return SymbolType.NODE;
    }
    
    public String toCode(){
        StringBuilder sb = new StringBuilder();
        sb.append("node ").append(getName()).append(" begin\n");
        
        
        for(Port p: getPorts()){
            sb.append("\t").append(p.toCode()).append("\n");
        }
        
        sb.append("end\n");
        
        return sb.toString();
    }
}
