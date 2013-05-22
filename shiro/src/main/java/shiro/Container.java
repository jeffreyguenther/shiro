package shiro;

import java.util.Set;
import shiro.expressions.Path;

/**
 * Defines the actions that a container object can execute.
 * Nodes and subjunctive nodes are containers in Shiro.
 * @author jeffreyguenther
 */
public interface Container extends Scope {
    public void activate();
    public void activate(Path key) throws PathNotFoundException;
    public void activate(String key) throws PathNotFoundException;
    public void deactivate();
    
    public void addNestedContainer(Container c);
    public Set<Container> getNestedContainers();
    public boolean hasNestedContainers();
}
    