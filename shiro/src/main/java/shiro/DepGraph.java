package shiro;

import java.util.*;

/**
 * A simple dependency graph implementation for experimentation as we develop
 * the subjunctive dependency graph </br>
 *
 * Terms <ul> 
 * <li>Port - a node in the computational representation of the dependency graph</li> 
 * <li>Leaf (sink) port - a port that has no dependents, that is no ports depend on it </li>
 * <li>Internal port - a port that has both dependents and ports it depends on</li>
 * <li>Source port - a port that depends on no other port, but may have dependents.</li> 
 * <li>Evaluated port - port that represents the update method for nodes</li>
 * <li>Node dependent port (output port) - depends on evaluated port
 * </ul>
 * Node - a cognitive box wrapped around a collection of ports.</br>
 * A node contains one or more evaluated ports. It can have other ports.
 * Some will be node dependent ports. Others will be source ports. 
 * Each node is represented by a subgraph of ports.
 * 
 * Does the graph need the ability to detect cycles?
 *
 * In this graph, a port knows what ports it depends on. When drawn, the arrows
 * DO NOT show data flow.
 *
 * @author jeffreyguenther
 */
public class DepGraph {
    // set of ports with no outgoing links
    private Set<Port> leafPorts;
    // all of the ports stored by full path
    private Map<String, Port> portsByPath;
    // the topo sorted list of all the ports
    private ArrayList<Port> topoList;

    public DepGraph() {
        leafPorts = new LinkedHashSet<Port>();
        portsByPath = new HashMap<String, Port>();
        topoList = new ArrayList<Port>();
    }
    
    /**
     * Add a port to the dependency graph without any relations.
     *
     * @param p to be added
     */
    public void addPort(Port p) {
        // add new port to hash map
        portsByPath.put(p.getFullName(), p);
        // set the port as a leaf
        p.setLeaf();
        //  add the port to the leaf nodes
        leafPorts.add(p);
    }

    /**
     * Add a dependency between two ports using the port names
     * B depends on A
     * @param nameA name of port A
     * @param nameB name of port B
     */
    public void addRelation(String nameA, String nameB) {
        Port portA = portsByPath.get(nameA);
        Port portB = portsByPath.get(nameB);
        portB.addDependencyOnPort(portA);
    }

    /**
     * *
     * Add a dependency. <code>addRelation(A, B)</code> means that B depends on A
     *
     * @param pathOfPortDependedOn the port B as described above.
     * @param p port to be added, A, the dependent port
     */
    public void addRelation(String pathOfPortDependedOn, Port p) {
        // Check if the port name is empty
        // This is how you add nodes that depend on nothing.
        if (!pathOfPortDependedOn.isEmpty()) {
            // get port depended on
            Port depPort = portsByPath.get(pathOfPortDependedOn);

            // add reference to depended on port to the port given
            p.addDependencyOnPort(depPort);
            
            // Check if dependent port is a leaf
            if (leafPorts.contains(depPort)) {
                // remove the port from the 
                leafPorts.remove(depPort);
                // remove mark as leaf
                depPort.unSetLeaf();
            }
            
            // add the port
            addPort(p);
        } else {
            // add the port
            addPort(p);
        }

        // get the new update order
        sortTopo();
    }

    /**
     * Remove a port from the graph
     *
     * @param path of port to be removed
     */
    public void removePort(String path) {
        // get a reference to the port
        Port portToBeRemoved = portsByPath.get(path);
        
        if(portToBeRemoved.isLeaf()){
            // mark for lazy delete
            portToBeRemoved.markForDelete();
        }else {
            //case 1: port depends on no other port
            // find the ports which depend on it.
            Set<Port> dependents = new HashSet<Port>();
            // check each leaf port
            for(Port p: leafPorts){
             dependents.addAll(getDependents(p, portToBeRemoved));
            }
            
            // set all the ports as unvisted
            setAllPortsUnvisited();
            
            // remove the reference to the port in its dependents
            for(Port dp : dependents){
                // remove the dependent port
                dp.removeDependencyOnPort(portToBeRemoved);
                
                // if the the dpendent port is made graph independent ( is completely unconnected )
                handleGraphIndependence(dp);
            }
            
            // check if the removal of port causes one of the ports
            // it was depending on to become graph independent
            for(Port dpOn: portToBeRemoved.getPortsDependedOn()){
                // if the the dpendent port is made graph independent ( is completely unconnected )
                handleGraphIndependence(dpOn);
            }
            
            // remove the port
            portsByPath.remove(path);
            
            // get new update order
            sortTopo();
        }
    }
    
    /**
     * Get all of the ports in the graph
     * @return the set of ports
     */
    public Set<Port> getPorts(){
        return new HashSet<Port>(portsByPath.values());
    }
    
    /**
     * Get the names of all the ports in the graph
     * @return the name of all ports in the graph
     */
    public Set<String> getPortPaths(){
        return portsByPath.keySet();
    }
    
    /**
     * Get a reference to a port by its path
     * @param path path of port to be retrieved
     * @return reference to port with the given path
     */
    public Port getPortByPath(String path){
        return portsByPath.get(path);
    }
    

    /**
     * Remove a relation between two ports. Remove A depends on B.
     *
     * @param aPort the A port in the relation
     * @param bPort the B port in the relation
     */
    public void removeRelation(String aPort, String bPort) {
        Port edgeToRemove = portsByPath.get(bPort);
        Port port = portsByPath.get(aPort);
        port.removeDependencyOnPort(edgeToRemove);
        
        if(!port.hasDependencies()){
            leafPorts.add(port);
        }
    }

    /**
     * Update every port in the graph
     */
    public void update() {
        // collect the garbage first
        collectGarbage();
        
        // update every port in the list
        for (Port p : topoList) {
            if (p.isActive()) {
                p.evaluate();
            }
        }
    }
    
    /**
     * Detect if a port has become graph independent. If it has, make it a leaf.
     * @param p port to be checked
     */
    private void handleGraphIndependence(Port p){
        // if the the dpendent port is made graph independent ( is completely unconnected )
        if(!p.hasDependencies()){
            // add port to leaf list
            leafPorts.add(p);
            p.setLeaf();
        }
    }

    /**
     * Remove all of the nodes in the graph flagged for deletion.
     */
    private void collectGarbage() {
        for (Port p : topoList) {
            if (p.isDeleted()) {
                // remove port from map
                portsByPath.remove(p.getFullName());
                // remove port from collection of leaves
                leafPorts.remove(p);
            }
        }
    }

    /**
     * Topologically sort the graph
     */
    private void sortTopo() {
        // clear the sorted list
        topoList.clear();

        // start with each node that has no outgoing links. We need a name for
        // this kind of node.
        for (Port p : leafPorts) {
            dfsVisit(p);
        }

        // reset visited flags
        setAllPortsUnvisited();
    }

    /**
     * Reset all of the port visited flags to false (not visited)
     */
    private void setAllPortsUnvisited() {
        // reset the visited flags
        for (Port p : portsByPath.values()) {
            p.setVisited(false);
        }
    }

    /**
     * Traverse the graph depth-first
     *
     * @param p starting node.
     */
    private void dfsVisit(Port p) {
        // Check if the node has been visited
        if (!p.isVisited()) {
            // visit the node
            p.visit();

            // get list of nodes with edges
            Set<Port> dependedOnPorts = p.getPortsDependedOn();
            for (Port cp : dependedOnPorts) {
                dfsVisit(cp);
            }

            //add the current port to the sorted list
            topoList.add(p);

        }
    }
    
    /***
     * Get the list of dependent ports
     * @param p port to find dependents of
     * @return list of ports dependent on p
     */
    private Set<Port> getDependents(Port p, Port portToMatch){
        Set<Port> portsDependentOnPortToMatch = new HashSet<Port>();
        
        // check if the port has been visited
        if(!p.isVisited()){
            // visit the port
            p.visit();
            
            // get set of ports p depends on
            Set<Port> dependedOnPorts = p.getPortsDependedOn();
            
            // check each depended on port if it matches
            for (Port cp : dependedOnPorts) {
                if(cp.equals(portToMatch)){
                    portsDependentOnPortToMatch.add(p);
                }else{
                    portsDependentOnPortToMatch
                            .addAll(getDependents(cp, portToMatch));
                }
            }
        }
        return portsDependentOnPortToMatch;
    }

    @Override
    public String toString() {
        return topoList.toString();
    }
}
