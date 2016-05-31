package org.shirolang.base;

import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.interpreter.ast.Path;

/**
 *
 * Defines a scope in Shiro
 */
public interface Scope {
    SFunc resolvePath(Path path) throws PathNotFoundException;
    SFunc resolvePath(String path) throws PathNotFoundException;
    String getName();
    String getFullName();
    boolean isRoot();
}