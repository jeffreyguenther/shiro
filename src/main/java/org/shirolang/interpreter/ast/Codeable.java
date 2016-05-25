package org.shirolang.interpreter.ast;

/**
 * Defines an interface signalling the object can be turned into Shiro ast.
 */
public interface Codeable {
    String toCode();
}
