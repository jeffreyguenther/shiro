package shiro;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toSet;
import shiro.dag.DependencyRelation;
import shiro.definitions.PortType;
import shiro.events.NodeEvent;
import shiro.events.NodeEventListener;
import shiro.events.PortEvent;
import shiro.events.PortEventListener;
import shiro.expressions.Expression;
import shiro.expressions.Path;

/**
 * Specifies a node in a subjunctive dependency graph. A node is simply a
 * reference to a collection of ports. Each node has at least one special port
 * called an "evaluated port." We will call them e-ports for short. This port
 * acts as an update method for the node. All node dependent ports depend on the
 * evaluated port.
 *
 * @author jeffreyguenther
 */
public class Node implements Symbol, Scope {

    // the node's parent scope; The value maybe another node or the global parametric system.
    private Scope parentScope;
    // type string for the node
    private String type;
    // node's fully qualified name
    private String fullName;
    // node name
    private String name;
    // flag to indicate if node is active
    private boolean active;

    //--- Contained items ----
    // options - could be nodes, or ports
    private Map<String, Symbol> options;
    // active option
    private Symbol activeOption;
    // nested nodes
    private Map<String, Node> nestedNodes;
    // ports mapped localname -> Port
    private Map<String, Port> ports;

    // objects listening to the node
    private Set<NodeEventListener> listeners;

    /**
     * Create a node
     *
     * @param type type string of the node such as "Point", or "Circle"
     * @param name name of the node
     * @param scope scope of the node
     */
    public Node(String type, String name, Scope scope) {
        // type of node
        this.type = type;
        // set the enclosing scope
        this.parentScope = scope;
        // set the parent's full

        if (scope != null) {
            this.fullName = Path.createFullName(scope.getFullName(), name);
        } else {
            this.fullName = name;
        }

        // set the name of the node
        this.name = name;
        // set the node active by default
        this.active = true;

        // create map of options
        options = new HashMap<>();
        // initialize the first option to null
        activeOption = null;
        // map of nested nodes
        nestedNodes = new HashMap<>();

        // create map for the node's ports
        ports = new HashMap<>();
    }

    /**
     * Default constructor Creates a node with no type, no name, and no scope
     */
    public Node() {
        this("", "", null);
    }

    /**
     * Adds a nested node.
     * The node is stored in a map by its name.
     * @param n node to nest
     */
    public void addNestedNode(Node n) {
        nestedNodes.put(n.getName(), n);
    }
    
    /**
     * Gets whether the node has nested nodes
     * @return true if there are nested nodes, otherwise false
     */
    public boolean hasNestedNodes(){
        return !nestedNodes.isEmpty();
    }
    
    /**
     * Gets the nested nodes
     * @return the set of nested nodes
     */
    public Set<Node> getNestedNodes(){
        return new HashSet<>(nestedNodes.values());
    }

    /**
     * Sets the node's active option.
     * Options are stored in a node in map by the options name.
     * To set the active option use the symbol's name, not it's full name
     * @param name name of symbol to set active
     */
    public void setActiveOption(String name) {
        Symbol activeItem = options.get(name);

        Set<Symbol> inactive = new HashSet<>(options.values());
        inactive.remove(activeItem);

        inactive.stream().forEach((s) -> s.deactivate());

        activeItem.activate();
        activeOption = activeItem;
    }
    
    
    /**
     * Gets the node's active option
     * @return Returns the symbol of the node's active option. If no option is
     * active returns null.
     */
    public Symbol getActiveOption(){
        return activeOption;
    }
    
    /**
     * Gets the node's active eval port.
     * The method is a shortcut to determine if a node has an eval port
     * as an option and it is currently the active option.
     * @return the active eval port of the node. If the activeOption is anything
     * else, it returns null.
     */
    public Port getActiveEvalPort(){
        if(activeOption.getSymbolType().isPort()){
            Port p = (Port) activeOption;
            if(p.isEval())
            return p;
        }
        
        return null;
    }
    
    public void addOption(Symbol option){
        if(option.getSymbolType().equals(SymbolType.NODE)){
            option.setFullName(Path.createFullName(fullName, option.getName()));
            
            Node n = (Node) option;
            options.put(n.getName(), option);
            addNestedNode(n);
        }
    }
    
    public boolean hasOptions(){
        return !options.isEmpty();
    }
    
    public void activateDefaultOption(){
        if(activeOption != null){
            Set<Symbol> inactive = new HashSet<>(options.values());
            inactive.remove(activeOption);

            for (Symbol s : inactive) {
                s.deactivate();
            }

            activeOption.activate();
        }
    }

    public Node getNode(Path p) {
        Node match = null;

        if (!nestedNodes.isEmpty() && !p.isPathToPortIndex()) {
            match = nestedNodes.get(p.getCurrentPathHead());
            if (match != null) {
                match.getNode(p);
            }
        } else {
            return match;
        }

        return match;
    }

    /**
     * Get the definition path of the node
     *
     * @return the definition path of the node. Example, Point, or Area.Circle
     */
    public String getType() {
        return type;
    }

    /**
     * Set the definition path of the node
     *
     * @param path of the node's prototype
     */
    public void setType(String path) {
        this.type = path;
    }

    /**
     * Get the name of the node
     *
     * @return the name of the node
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * *
     * Set the name of the node
     *
     * @param name of the node to be set
     */
    @Override
    public void setName(String name) {
        this.name = name;
        this.fullName = Path.replaceNameInPath(fullName, name);
    }

    /**
     * Get the node's full name
     *
     * @return node's full name
     */
    @Override
    public String getFullName() {
        return fullName;
    }

    /**
     * Set the node's full name
     *
     * @param fullName name to be set
     */
    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
        this.name = Path.getNameFromPath(fullName);
        
        for(Port p: ports.values()){
            p.setFullName(Path.createFullName(fullName, p.getName()));
        }
        
        for(Node nested: nestedNodes.values()){
            nested.setFullName(Path.createFullName(fullName, nested.getName()));
        }
        
        for(Symbol s: options.values()){
            s.setFullName(Path.createFullName(fullName, s.getName()));
        }
    }

    /**
     * Get a path object representing the full name
     *
     * @return a Path object corresponding to the full name.
     */
    @Override
    public Path getPath() {
        List<String> pathParts = Arrays.asList(fullName.split("\\."));
        return new Path(parentScope, pathParts);
    }

    /**
     * Get a reference to the scop object the node is contained in
     *
     * @return a reference to the parent scope
     */
    public Scope getParentScope() {
        return parentScope;
    }

    /**
     * Set the parent scope
     *
     * @param enclosingScope
     */
    public void setParentScope(Scope enclosingScope) {
        this.parentScope = enclosingScope;
    }

    /**
     * Determine if the node is active
     *
     * @return true/false depending if the node is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Activate the node. Goes through all the ports the node references and
     * sets them active. Takes care to only activate the selected evaluated
     * port. All other e-ports are left inactive.
     */
    @Override
    public void activate() {
        // activate the node
        this.active = true;

        // Activate all the ports
        for (Port p : ports.values()) {
            p.activate();
        }

        // activate the nested containers
        for (Node n : nestedNodes.values()) {
            n.activate();
        }
        
        activateDefaultOption();
    }

    /**
     * *
     * Deactivate the node by deactivating all of its ports.
     */
    @Override
    public void deactivate() {
        // Deactivate the node
        this.active = false;

        // Deactivate all the ports
        for (Port p : ports.values()) {
            p.deactivate();
        }

        // deactivate the nested container
        for(Node n: nestedNodes.values()){
            n.deactivate();
        }
    }
    
    public boolean hasPorts(){
        return !ports.isEmpty();
    }

    /**
     * Add a port to the node Registers the node as a listener to the port p's
     * events
     *
     * @param p port to be added
     */
    public void addPort(Port p) {
        ports.put(p.getName(), p);
    }

    /**
     * Remove a port from the node
     *
     * @param p port to be removed
     * @return true if remove was successful, otherwise false
     */
    public boolean removePort(Port p) {
        return ports.remove(p.getName()) != null;
    }

    /**
     * *
     * Gets a set of all the names of the ports
     *
     * @return the set of the names of all of the ports in the node.
     */
    public Set<String> getPortNames() {
        Set<Port> ioPorts = ports.values().stream().filter((p) -> !p.getPortType().equals(PortType.Evaluated)).collect(toSet());
        
        return ioPorts.stream().map(p -> p.getName()).collect(toSet());
    }

    public Set<Port> getPorts() {
        return new LinkedHashSet<>(ports.values());
    }

    @Override
    public Symbol find(Path p) throws PathNotFoundException, PathNotAccessibleException {
        Symbol matched = null;
        
        if(p.getCurrentPathHead().equals("active")){
            p.popPathHead();
            if(activeOption.getSymbolType().equals(SymbolType.NODE)){
                Node n = (Node) activeOption;
                return n.find(p);
            }
        }
        
        if (ports.containsKey(p.getCurrentPathHead())) {
            Port match = ports.get(p.getCurrentPathHead());
            if (!match.getPortType().equals(PortType.Evaluated)) {
                matched = ports.get(p.getCurrentPathHead());
            } else{
                throw new PathNotAccessibleException(p + " is not accessible. "
                        + "It is an evaluated port.");
            }
        } else if(options.containsKey(p.getCurrentPathHead())){
            Symbol result = options.get(p.getCurrentPathHead());
            if(p.isAtEnd()){
                matched = result;
            }else{
                if(result.getSymbolType().equals(SymbolType.NODE)){
                    p.popPathHead();
                    matched = ((Node) result).find(p);
                }
            }
        }else {
            throw new PathNotFoundException(p + "cannot be found.");
        }

        return matched;
    }

    @Override
    public Symbol find(String path) throws PathNotFoundException, PathNotAccessibleException {
        return find(Path.createPath(path));
    }

    @Override
    public Symbol resolvePath(Path path) throws PathNotFoundException {
        Symbol portReferenced = null;

        // if the value is local to the current node
        String pathHead = path.getCurrentPathHead();
        if (pathHead.equals("active") 
                || pathHead.equals("this") || path.isAtEnd()) {
            if(pathHead.equals("active")){
                path.popPathHead();
                // TODO deal with different cases of active
                if(activeOption.getSymbolType().equals(SymbolType.NODE)){
                    Node subjunct = (Node) activeOption;
                    portReferenced = subjunct.resolvePath(path);
                }
            }else{
            
                if(pathHead.equals("this")){
                    path.popPathHead();
                }

                String portName = path.getCurrentPathHead();

                // check each port to see if it is a match
                portReferenced = ports.get(portName);
                path.resetPathHead();
            }

        } else {
            // check the nested nodes
            if (!nestedNodes.isEmpty()) {
                Scope nestedNodeMatch = nestedNodes.get(path.getCurrentPathHead());
                if (nestedNodeMatch != null) {
                    // pop the head and begin exploring
                    path.popPathHead();
                    portReferenced = nestedNodeMatch.resolvePath(path);
                }
            }else { //CASE: no nested nodes to check

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

    public Set<DependencyRelation<Port>> getDependencies() {
        // Store all dependency relations
        Set<DependencyRelation<Port>> deps = new HashSet<>();

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
                            deps.add(new DependencyRelation<>(p, dependedOn));
                        }
                    }
                } catch (PathNotFoundException ex) {
                    Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return deps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // print node header
        sb.append(getFullName())
                .append(":")
                .append(getSymbolType())
                .append("\n");

        // print each port
        for (Port p : ports.values()) {
            sb.append("\t")
                    .append(p.getName())
                    .append(": ")
                    .append(p.toString())
                    .append("\n");
        }

        if (!nestedNodes.isEmpty()) {
            sb.append(name)
                    .append(" has the following nested nodes:\n");
            for (Node nested : nestedNodes.values()) {
                sb.append(nested.toString());
            }
        }

        return sb.toString();
    }

    @Override
    public SymbolType getSymbolType() {
        return SymbolType.NODE;
    }

    public String toCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("node ").append(getName()).append(" begin\n");

        for (Port p : getPorts()) {
            sb.append("\t").append(p.toCode()).append("\n");
        }

        sb.append("end\n");

        return sb.toString();
    }
}
