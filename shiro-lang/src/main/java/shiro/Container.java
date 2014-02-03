package shiro;

import java.util.Set;
import shiro.expressions.Path;

/**
 * Defines the actions that a container object can execute.
 * Nodes and subjunctive nodes are containers in Shiro.
 * @author jeffreyguenther
 */
public interface Container extends Scope {
    /**
     * Activate the container's default update port or subjunct
     */
    public void activate();
    
    /**
     * Activate a path in the container
     * This option will search the whole scope tree
     * @param path path to be activated
     * @throws PathNotFoundException 
     */
    public void activate(Path path) throws PathNotFoundException;
    
    /**
     * Activate a name
     * Note this method DOES NOT search the whole scope tree
     * @param name name of object to be activated
     * @throws PathNotFoundException 
     */
    public void activate(String name) throws PathNotFoundException;
    
    /**
     * Deactivate the whole container
     */
    public void deactivate();
    
    public void addNestedContainer(Container c);
    public Set<Container> getNestedContainers();
    public boolean hasNestedContainers();
}
    