package org.shirolang.base;

import javafx.util.Pair;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.interpreter.ast.Path;
import org.shirolang.interpreter.ast.PathSegment;

import java.util.*;

import static java.util.stream.Collectors.toSet;

/**
 * Specifies a node in a subjunctive dependency graph. A node is simply a
 * reference to a collection of ports
 *
 */
public class SNode extends SFuncBase implements Scope{
    private SEvaluator evaluator; //object with methods to evaluate graphs

    // the node's parent scope; The value maybe another node or the global parametric system.
    private Scope parentScope;
    // type string for the node
    private String type;

    //--- Contained items ----
    // options - could be nodes, or ports
    private Map<String, SFunc> options;
    // active option
    private SFunc activeOption;
    // default option
    private SFunc defaultOption;
    // nested nodes
    private Map<String, SNode> nestedNodes;
    // ports mapped localname -> Port
    private Map<String, SFunc> ports;
    private Map<Integer, String> portNameByIndex;
    private int portIndex;

    /**
     * Creates a new node with it's name and
     * type add to the empty string.
     * It's scope is null.
     */
    public SNode() {
        super();
        initializeVars("", "", null);
    }

    public SNode(String type, String name){
        super();
        initializeVars(type, name, null);
    }

    /**
     * Creates a new node
     * @param type type of the node
     * @param name name of node
     * @param scope scope the node is in
     */
    public SNode(String type, String name, Scope scope) {
        initializeVars(type, name, scope);

    }

    /**
     * Initializes variables. This is a work around to using this() in
     * a constructor with super
     * @param type type of node
     * @param name name of node
     * @param scope scope the node is in
     */
    private void initializeVars(String type, String name, Scope scope) {
        evaluator = new SEvaluator();

        // type of node
        this.type = type;
        // add the enclosing scope
        this.parentScope = scope;

        // add the parent's full name based on the scope
        if (scope != null) {
            this.fullName.set(Path.createFullName(scope.getFullName(), name));
        } else {
            this.fullName.set(name);
        }

        // add the name of the node
        this.name.set(name);

        // create map of options
        options = new HashMap<>();
        // initialize the first option to null
        activeOption = null;
        // initialize default option
        defaultOption = null;
        // map of nested nodes
        nestedNodes = new HashMap<>();

        // create map for the node's ports
        ports = new HashMap<>();
        portNameByIndex = new HashMap<>();
        portIndex = 0;

        symbolType = SymbolType.NODE;
    }

    /**
     * Gets the scope of the node
     * @return the scope of the node
     */
    public Scope getScope() {
        return parentScope;
    }

    /**
     * Sets the parent scope
     * @param enclosingScope the scope the node is enclosed within
     */
    public void setScope(Scope enclosingScope) {
        this.parentScope = enclosingScope;
    }

    /**
     * Adds a port to the node
     * @param port the port to be added
     */
    public void addPort(SFunc port){
        port.setFullName(Path.createFullName(fullName.get(), port.getName()));

        if(port.getAccess().isReadWrite()){
            // create an input with the port's name
            inputs.add(new TypedValue(port.getType(), port));
            inputs.setKeyForIndex(port.getName(), inputs.size() - 1);
        }

        if(port.getAccess().isRead()){
            // create an output with the port's name
            results.add(new TypedValue(port.getType(), port));
            results.setKeyForIndex(port.getName(), results.size() - 1);
        }

        if(port.getAccess().isInternal()){
            ports.put(port.getName(), port);
            portNameByIndex.put(portIndex, port.getName());
            // increment so it's ready for the next time add is called
            portIndex++;
        }
    }

    /**
     * Add a function to a node
     * @param func the function to add
     */
    public void addFunction(SFunc func){
        if(func.getSymbolType().isNode()){
            addNestedNode((SNode) func);
        }else{
            addPort(func);
        }
    }

    /**
     * Gets whether the node has ports
     * @return true if the node has ports, otherwise false
     */
    public boolean hasPorts() {
        return !ports.isEmpty() || !inputs.isEmpty() || !results.isEmpty();
    }

    /**
     * Gets a port by it's local name
     * @param name local name of the port
     * @return the port with the passed name. Returns null if a port with the given
     * name is not found.
     */
    public SFunc getPort(String name){
        SFunc port;
        // look in inputs
        port = getInput(name);

        // look in outputs
        if(port == null){
            port = getOutput(name);
        }

        // look in internal ports
        if(port == null){
            port = ports.get(name);
        }

        return port;
    }

    /**
     * Gets port by index
     * Ports are assigned indices in the order they are added to the node, starting with 0
     * @param index of port to return
     * @return the port for the given index
     */
    public SFunc getPort(int index){
        SFunc port;
        // look in inputs
        port = getInput(index);

        // look in outputs
        if(port == null){
            port = getOutput(index);
        }

        // look in internal ports
        if(port == null){
            port = getPort(portNameByIndex.get(index));
        }

        return port;
    }

    public Set<SFunc> getPorts(){
        Set<SFunc> inputs = this.inputs.getAll().stream().map(TypedValue::getValue).filter(SFunc::isActive).collect(toSet());
        Set<SFunc> outputs = results.getAll().stream().map(TypedValue::getValue).filter(SFunc::isActive).collect(toSet());
        Set<SFunc> internals = ports.values().stream().filter(SFunc::isActive).collect(toSet());
        Set<SFunc> all = new HashSet<>(inputs.size() + outputs.size() + internals.size());

        all.addAll(inputs);
        all.addAll(outputs);
        all.addAll(internals);

        // get the ports of the tree of nested nodes
        for(SNode nested: nestedNodes.values()){
            all.addAll(nested.getPorts());
        }
        return all;
    }

    /**
     * Gets the default option for the node
     * @return a reference to the default option. Returns null
     * if no default is add.
     */
    public SFunc getDefaultOption(){
        return defaultOption;
    }

    /**
     * Sets the node's default option.
     * @param name name of node or port to add as default
     * @throws OptionNotFoundException
     */
    public void setDefaultOption(String name) throws OptionNotFoundException {
        SFunc option = options.get(name);

        if(option == null){
            throw new OptionNotFoundException(this.getFullName() + " does not have an option " + name);
        }

        defaultOption = option;
    }

    /**
     * Adds the option and makes it the default
     * @param option option to be added
     */
    public void addOptionAsDefault(SFunc option){
        addOption(option);
        defaultOption = option;
    }


    /**
     * Adds an option to the node The option is renamed to have the correct full
     * name. If another option exists that has the same name, it is overwritten.
     *
     * @param option option to be added
     */
    public void addOption(SFunc option) {
        if(option.getName().isEmpty()){
            throw new RuntimeException(option + " has no name. Cannot add an option with an empty name");
        }

        // add the option to the appropriate collection
        if (option.getSymbolType().isNode()) {
            SNode n = (SNode) option;
            addNestedNode(n);
        } else { // option is a port
           addPort(option);
        }

        options.put(option.getName(), option);
    }

    /**
     * Gets the option with the given name
     * @param name name of the option
     * @return a reference to the option, or null it is not found
     */
    public SFunc getOption(String name){
        return options.get(name);
    }

    /**
     * Gets the options for the node
     * @return a reference to a new map containing the node's options
     */
    public Map<String, SFunc> getOptions(){
        return new HashMap<>(options);
    }

    /**
     * Gets the node name to option name pairs used
     * @return a set containing pairs of (nodename, optionName) for each
     * option in the node and all nested nodes
     */
    public Set<Pair<String, String>> getOptionPairs(){
        Set<Pair<String, String>> pairs = new HashSet<>();

        if(hasDefaultOption()) {
            pairs.add(new Pair<>(name.get(), getDefaultOption().getName()));
        }else {
            for (SFunc s : getOptions().values()) {
                pairs.add(new Pair<>(name.get(), s.getName()));
            }
        }

        for (SNode nested : nestedNodes.values()) {
            pairs.addAll(nested.getOptionPairs());
        }
        return pairs;
    }

    /**
     * Gets whether the node has options
     * @return true if the node has options, otherwise false
     */
    public boolean hasOptions() {
        return !options.isEmpty();
    }

    /**
     * Gets the node's active option
     *
     * @return Returns the SFunc of the node's active option. If no option is
     * active returns null.
     */
    public SFunc getActiveOption() {
        return activeOption;
    }

    /**
     * Adds an option and makes it active
     * @param option option to be added
     */
    public void addOptionAsActive(SFunc option){
        addOption(option);
        activateOption(option);
    }

    /**
     * Sets the node's active option. Options are stored in a node in map by the
     * options name. To add the active option use the symbol's name, not it's
     * full name
     *
     * @param name name of symbol to add active
     * @return the symbol add active, returns null if name is not found
     * @throws OptionNotFoundException
     */
    public SFunc setActiveOption(String name) throws OptionNotFoundException {
        SFunc activeItem = options.get(name);

        if(activeItem == null){
            throw new OptionNotFoundException(this.getFullName() + " does not have an option " + name);
        }
        activateOption(activeItem);

        return activeOption;
    }

    /**
     * Activates the options for this node and all the nested nodes
     * @param activation activation to apply to the node
     * @throws OptionNotFoundException
     */
    public void activateOptions(StateActivation activation) throws OptionNotFoundException{
        setActiveOption(activation.getOption());

        if(activation.hasNestedActivations()){
            for(StateActivation a: activation.getNestedActivations()) {
                SNode n = getNestedNode(a.getName());
                n.activateOptions(a);
            }
        }
    }

    public boolean hasDefaultOption(){
        return Objects.nonNull(getDefaultOption());
    }

    /**
     * Activates the passed option
     * Deactivates all other options
     * @param option the option to activate
     */
    private void activateOption(SFunc option) {
        // Create a add of the inactive options
        Set<SFunc> inactive = new HashSet<>(options.values());
        inactive.remove(option);

        // deactivate the inactive options
        inactive.stream().forEach((s) -> s.setActive(false));

        // activate the passed option
        option.setActive(true);

        activeOption = option;
    }

    /**
     * Gets the nested node of the given name
     * @param name name of the node
     * @return null if node is not found
     */
    public SNode getNestedNode(String name){
        return nestedNodes.get(name);
    }

    /**
     * Gets whether the node has nested nodes
     *
     * @return true if there are nested nodes, otherwise false
     */
    public boolean hasNestedNodes() {
        return !nestedNodes.isEmpty();
    }

    /**
     * Adds a nested node. The node is stored in a map by its name. When a node
     * is nested it's name is changed to reflect its position in the hierarchy
     *
     * @param n node to nest
     */
    public void addNestedNode(SNode n) {
        n.setScope(this);
        n.setFullName(Path.createFullName(fullName.get(), n.getName()));
        nestedNodes.put(n.getName(), n);
    }

    /**
     * Set the node's full name
     *
     * @param fullName name to be add
     */
    @Override
    public void setFullName(String fullName) {
        super.setFullName(fullName);

        updateNames(this.fullName.get());
    }

    @Override
    public void setName(String name) {
        super.setName(name);

        // update all the contained nodes and options
        updateNames(fullName.get());
    }

    /**
     * Updates the names of all nested nodes and ports
     * @param fullName
     */
    private void updateNames(String fullName) {
        // update both the nested nodes and ports
        // Since they both implement SFunc, create
        // one list and update
        Set<SFunc> itemsToUpdate = new HashSet<>();
        itemsToUpdate.addAll(getInputs());
        itemsToUpdate.addAll(getOutputs());
        itemsToUpdate.addAll(ports.values());
        itemsToUpdate.addAll(nestedNodes.values());

        for(SFunc p: itemsToUpdate){
            p.setFullName(Path.createFullName(fullName, p.getName()));
        }
    }

    @Override
    public void evaluate() {

    }

    public void evaluateSubgraph(){
        evaluator.evaluateGraph(getPorts());
    }

    @Override
    public int getMaxArgs() {
        return inputs.size();
    }

    @Override
    public int getMinArgs() {
        return 0;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public SFunc resolvePath(Path path) throws PathNotFoundException {
        SFunc portReferenced = null;

        PathSegment head = path.getSegmentAtHead();
        if (path.isReference()) {
            if (path.isAtEnd() && getName().equals(head.getKey().get())) {
                portReferenced = this;
            }
        }

        // match active
        if (portReferenced == null && head.isActiveKeyword()) {
            portReferenced = activeOption;
            if (portReferenced.getSymbolType().isPort()) {
                if (path.atSecondLast()) {
                    path.setReferencesPortValue(true);
                }
                path.resetHead();

            } else if (!path.isAtEnd()) {
                path.advanceHead();
                SNode subjunct = (SNode) activeOption;
                portReferenced = subjunct.resolvePath(path);
            }
        }

        if (portReferenced == null && head.isSimple()) {
            String portName = head.getKey().get();

            portReferenced = getInput(portName);

            if (portReferenced == null) {
                portReferenced = getOutput(portName);
            }

            if (portReferenced == null) {
                portReferenced = ports.get(portName);
            }

            if (portReferenced != null) {
                if (path.atSecondLast()) {
                    path.setReferencesPortValue(true);

                }

                path.resetHead();
            }
        }

        if (portReferenced == null && head.isInput()) {
            // look in the inputs list of ports
            // look for a named port if there is a key
            if (head.getKey().isPresent()) {
                String portName = head.getKey().get();
                // get the input port with the name
                portReferenced = getInput(portName);
            }

            // look for the port if there is an index
            if (head.getIndex().isPresent()) {
                int portIndex = head.getIndex().get();
                // get the input port with the index
                portReferenced = getInput(portIndex);
            }

            if (portReferenced != null) {
                if (path.atSecondLast()) {
                    path.setReferencesPortValue(true);

                }

                path.resetHead();
            }
        }

        if (portReferenced == null && head.isOutput()) {
            // look in the results list of ports
            // look for a named port if there is a key
            if (head.getKey().isPresent()) {
                String portName = head.getKey().get();
                // get the input port with the name
                portReferenced = getOutput(portName);
            }

            // look for the port if there is an index
            if (head.getIndex().isPresent()) {
                int portIndex = head.getIndex().get();

                // get the input port with the index
                portReferenced = getOutput(portIndex);
            }

            if (portReferenced != null) {
                if (path.atSecondLast()) {
                    path.setReferencesPortValue(true);

                }

                path.resetHead();
            }
        }

        if (portReferenced == null && hasNestedNodes()) {
            // check the nested nodes
            Scope nestedNodeMatch = nestedNodes.get(head.getKey().get());
            if (nestedNodeMatch != null) {
                // pop the head and search the nested node
//                if(!path.isReference()) {
                path.advanceHead();
//                }
                portReferenced = nestedNodeMatch.resolvePath(path);
            }
        }

        // since we didn't find anything, pop up one level in the scope tree.
        if (!path.isAtEnd() && portReferenced == null && parentScope != null) {
            path.resetHead();
            portReferenced = parentScope.resolvePath(path);
        }

        if (portReferenced == null) {
            throw new PathNotFoundException(path + " was not found.");
        }

        return portReferenced;
    }

    @Override
    public SFunc resolvePath(String path) throws PathNotFoundException {
        return resolvePath(Path.create(path));
    }

    @Override
    public boolean isRoot() {
        return false;
    }
}
