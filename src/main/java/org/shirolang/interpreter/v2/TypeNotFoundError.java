package org.shirolang.interpreter.v2;

/**
 * Represents a type that cannot be found
 */
public class TypeNotFoundError extends BaseError{
    public TypeNotFoundError(String message) {
        super(message);
    }
}
