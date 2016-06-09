package org.shirolang.interpreter.v2;

/**
 * Represents a syntax or runtime error in Shiro
 */
public interface Error {
    /**
     * Gets message describing error
     * @return message describing the error
     */
    String getMessage();
}
