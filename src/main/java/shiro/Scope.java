package shiro;

import shiro.exceptions.PathNotFoundException;
import shiro.exceptions.PathNotAccessibleException;
import shiro.expressions.Path;

/**
 * Scope interface.
 * @author jeffreyguenther
 */
public interface Scope {
    public Symbol resolvePath(Path path) throws PathNotFoundException;
    public Symbol resolvePath(String path) throws PathNotFoundException;
    public Symbol find(String path) throws PathNotFoundException, PathNotAccessibleException;
    public Symbol find(Path path) throws PathNotFoundException, PathNotAccessibleException;
    public String getName();
    public String getFullName();
    public Path getPath();
    public boolean isRoot();
}
