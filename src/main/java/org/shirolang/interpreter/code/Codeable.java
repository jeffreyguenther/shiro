package org.shirolang.interpreter.code;

/**
 * Defines an interface signalling the object can be turned into Shiro code.
 */
public interface Codeable {
    String toCode();
}
