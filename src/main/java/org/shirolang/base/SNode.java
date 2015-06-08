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

package org.shirolang.base;

import javafx.util.Pair;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.values.Path;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Specifies a node in a subjunctive dependency graph. A node is simply a
 * reference to a collection of ports.
 *
 *
 */
public class SNode extends SFuncBase implements Scope{
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
        initializeVars(type, name, null);
    }

    /**
     * Creates a new node
     * @param type type of the node
     * @param name name of node ( the same as the type for prototype instances)
     * @param scope scope the node is in
     */
    public SNode(String type, String name, Scope scope) {
        initializeVars(type, name, scope);

    }

    /**
     * Initializes variables. This is a work around to using this() in
     * a constructor with super
     * @param type type of node
     * @param name name of node ( the same as the type for prototype instances)
     * @param scope scope the node is in
     */
    private final void initializeVars(String type, String name, Scope scope) {
        // type of node
        this.type = type;
        // add the enclosing scope
        this.parentScope = scope;
        // add the parent's full

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
        ports.put(port.getName(), port);
        portNameByIndex.put(portIndex, port.getName());
        // increment so it's ready for the next time add is called
        portIndex++;
    }

    /**
     * Gets whether the node has ports
     * @return true if the node has ports, otherwise false
     */
    public boolean hasPorts() {
        return !ports.isEmpty();
    }

    /**
     * Gets a port by name
     * @param name name of the port get
     * @return the port with the passed name. Returns null if a port with the given
     * name is not found.
     */
    public SFunc getPort(String name){
        return ports.get(name);
    }

    /**
     * Gets port by index
     * Ports are assigned indices in the order they are added to the node, starting with 0
     * @param index of port to return
     * @return the port for the given index
     */
    public SFunc getPort(int index){
        return getPort(portNameByIndex.get(index));
    }

    public Set<SFunc> getPorts(){
        Set<SFunc> allPorts = new HashSet<>();
        for(SFunc f: ports.values()){
            if(f.isActive()) {
                allPorts.add(f);
            }
        }

        for(SNode nested: nestedNodes.values()){
            allPorts.addAll(nested.getPorts());
        }
        return allPorts;
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
        // add the option to the appropriate collection
        if (option.getSymbolType().isNode()) {
            SNode n = (SNode) option;
            addNestedNode(n);
        } else { // option is a port
           addPort(option);
        }

        if(option.getName().isEmpty()){
            throw new RuntimeException(option + " has no name. Cannot add an option with an empty name");
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
     * @return a add containing pairs of (nodename, optionName) for each
     * option in the node
     */
    public Set<Pair<String, String>> getOptionPairs(){
        Set<Pair<String, String>> pairs = new HashSet<>();
        for(SFunc s: getOptions().values()){
            pairs.add(new Pair<>(name.get(), s.getName()));
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
        itemsToUpdate.addAll(ports.values());
        itemsToUpdate.addAll(nestedNodes.values());

        for(SFunc p: itemsToUpdate){
            p.setFullName(Path.createFullName(fullName, p.getName()));
        }
    }

    @Override
    public void evaluate() {
        // LEAVE empty. A node should never be evaluated
        // Evaluation in shiro happens based on ports.

        // todo handle node evaluation.
        // This is where we will deal with the mechanics of having a
        // node evaluate itself when it's not attached to a graph.
        // this will happen when a node is passed as a lambda
    }

    @Override
    public int getMaxArgs() {
        return 0;
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

        String pathHead = path.getCurrentPathHead();
        if(pathHead.equals("active") || pathHead.equals("this") || path.isAtEnd()){
            if(pathHead.equals("active")){
                // if active refers to a port
                if(path.isAtEnd()){
                    portReferenced = activeOption;
                    path.resetPathHead();
                }else if (activeOption.getSymbolType().isNode()){ // if active refers to a node
                    path.popPathHead();
                    SNode subjunct = (SNode) activeOption;
                    portReferenced = subjunct.resolvePath(path);
                }
            }else {

                if (pathHead.equals("this")) {
                    // move up the path
                    path.popPathHead();
                }

                String portName = path.getCurrentPathHead();
                portReferenced = ports.get(portName);

                if(path.hasIndex()){
                    if(path.hasIntegerIndex()){
                        portReferenced = portReferenced.getResult(path.getIndex());
                    }

                    if(path.hasStringIndex()){
                        portReferenced = portReferenced.getResult(path.getIndexKey());
                    }
                }

                // reset the path for future use
                path.resetPathHead();
            }
        }else{ // check the nested nodes
            if(hasNestedNodes()){
                Scope nestedNodeMatch = nestedNodes.get(path.getCurrentPathHead());
                if (nestedNodeMatch != null) {
                    // pop the head and search the nested node
                    path.popPathHead();
                    portReferenced = nestedNodeMatch.resolvePath(path);
                }
            }else{
                path.resetPathHead();

                portReferenced = parentScope.resolvePath(path);
            }
        }

        if(portReferenced == null){
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
