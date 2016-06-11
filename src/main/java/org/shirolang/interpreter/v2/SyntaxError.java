package org.shirolang.interpreter.v2;

/**
 * Represents a syntax error
 */
public class SyntaxError implements Error {
    private String message;

    public SyntaxError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
