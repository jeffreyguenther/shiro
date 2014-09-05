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

import org.shirolang.values.Path;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Specifies a node in a subjunctive dependency graph. A node is simply a
 * reference to a collection of ports. Each node has at least one special port
 * called an "evaluated port." We will call them e-ports for short. This port
 * acts as an update method for the node. All node dependent ports depend on the
 * evaluated port.
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

    /**
     * Creates a new node with it's name and
     * type set to the empty string.
     * It's scope is null.
     */
    public SNode() {
        super();
        initializeVars("", "", null);
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
     * Initialize variables. This is a work around to using this() in
     * a constructor with super
     * @param type type of node
     * @param name name of node ( the same as the type for prototype instances)
     * @param scope scope the node is in
     */
    private final void initializeVars(String type, String name, Scope scope) {
        // type of node
        this.type = type;
        // set the enclosing scope
        this.parentScope = scope;
        // set the parent's full

        // set the parent's full name based on the scope
        if (scope != null) {
            this.fullName.set(Path.createFullName(scope.getFullName(), name));
        } else {
            this.fullName.set(name);
        }

        // set the name of the node
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
    }

    /**
     * Get the scope of the node
     * @return the scope of the node
     */
    public Scope getScope() {
        return parentScope;
    }

    /**
     * Set the parent scope
     *
     * @param enclosingScope
     */
    public void setScope(Scope enclosingScope) {
        this.parentScope = enclosingScope;
    }

    /**
     * Gets whether the node has ports
     * @return true if the node has ports, otherwise false
     */
    public boolean hasPorts() {
        return !ports.isEmpty();
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
     * @param fullName name to be set
     */
    @Override
    public void setFullName(String fullName) {
        this.fullName.set(fullName);
        this.name.set(Path.getNameFromPath(fullName));

        updateNames(this.fullName.get());
    }

    @Override
    public void setName(String name) {
        this.name.set(name);
        this.fullName.set(Path.replaceNameInPath(fullName.get(), name));

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
    public SFunc resolvePath(Path s) {
        throw new UnsupportedOperationException("Feature not implemented yet!");
    }

    @Override
    public SFunc resolvePath(String path) {
        throw new UnsupportedOperationException("Feature not implemented yet!");
    }

    @Override
    public boolean isRoot() {
        return false;
    }

    public void addPort(SFunc port){
        if(!port.getSymbolType().isPort()){
            throw new RuntimeException("Only SFuncs of SymbolType.PORT can be added to nodes.");
        }

        ports.put(port.getName(), port);
    }
}
