package org.shirolang.interpreter.v2;

/**
 * Represents when a path cannot be resolved
 */
public class PathNotFoundError extends BaseError {
    public PathNotFoundError(String message) {
        super(message);
    }
}
