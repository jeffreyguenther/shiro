package shiro;

import shiro.expressions.Path;

/**
 * Scope interface.
 * @author jeffreyguenther
 */
public interface Scope {
    public Symbol resolvePath(Path path) throws PathNotFoundException;
    public Symbol resolvePath(String path) throws PathNotFoundException;
    //public Container getContainerFromPath(Path p) throws PathNotFoundException;
    public String getName();
    public String getFullName();
    public Path getPath();
}
