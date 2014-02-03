package shiro;

import shiro.expressions.Path;

/**
 * Scope interface.
 * @author jeffreyguenther
 */
public interface Scope {
    public Symbol resolvePath(Path p) throws PathNotFoundException;
    //public Container getContainerFromPath(Path p) throws PathNotFoundException;
    public String getName();
    public String getFullName();
    public Path getPath();
}
