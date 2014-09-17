/*
 * Copyright (c) 2012-2014 Jeffrey Guenther
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software  and associated documentation files (the
 * "Software"), to deal in the Software without restriction,  including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute,  sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT  NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.shirolang.base;

import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.values.Path;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Defines a graph instance in Shiro.
 *
 * A graph functions as the root of the scope tree for
 * a Shiro program.
 */
public class SGraph implements Scope{
    private String name; // name of the graph
    private Map<String, SNode> nodes; // nodes stored in the graph
    private Map<String, SFunc> ports;

    public SGraph(){
        this("");
    }

    public SGraph(String name){
        this.name = name;
        nodes = new HashMap<>();
        ports = new HashMap<>();
    }

    /**
     * Adds the node to the graph.
     * The node is stored by it's full name.
     * Updates the node's scope to the graph
     * @param node node to be added
     */
    public void addNode(SNode node){
        node.setScope(this);
        nodes.put(node.getName(), node);
    }

    /**
     * Gets the node stored in the graph by name
     * @param name
     * @return
     */
    public SNode getNode(String name){
        return nodes.get(name);
    }

    /**
     * Gets the node instances in the graph
     * @return the set of node instances in the graph
     */
    public Set<SNode> getNodes(){
        return new HashSet<>(nodes.values());
    }

    /**
     * Adds a port to the graph
     * @param port port to be added to the graph
     */
    public void addPort(SFunc port){
        if(!port.getSymbolType().isPort()){
            throw new IllegalArgumentException("Only multi-functions whose SymbolType " +
                    "is SymbolType.PORT can be added.");
        }

        ports.put(port.getName(), port);
    }

    /**
     * Gets all of the ports in the graph
     * @return the set of named ports in the graph
     */
    public Set<SFunc> getPorts(){
        return new HashSet<>(ports.values());
    }

    /**
     * Gets the port with the given name
     * @param name name of the port to be found
     * @return the port with the given name
     */
    public SFunc getPort(String name){
        return ports.get(name);
    }

    @Override
    public SFunc resolvePath(Path path) throws PathNotFoundException {
        SFunc funcReferenced = null;

        if(path.isAtEnd()){
            // check the nodes
            funcReferenced = nodes.get(path.getCurrentPathHead());

            // check the ports
            if(funcReferenced == null){
                funcReferenced = ports.get(path.getCurrentPathHead());
            }
        }else{ // recursively examine the node
            if(nodes.containsKey(path.getCurrentPathHead())){
                SNode nodeReferenced = nodes.get(path.getCurrentPathHead());
                path.popPathHead();
                funcReferenced = nodeReferenced.resolvePath(path);
            }
        }

        if(funcReferenced == null){
            throw new PathNotFoundException(path + " was not found.");
        }
        
        return funcReferenced;
    }

    @Override
    public SFunc resolvePath(String path) throws PathNotFoundException {
        return resolvePath(Path.create(path));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullName() {
        return name;
    }

    @Override
    public boolean isRoot() {
        return true;
    }
}
