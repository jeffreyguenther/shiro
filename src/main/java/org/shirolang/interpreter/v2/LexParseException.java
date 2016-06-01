package org.shirolang.interpreter.v2;

/**
 * A parse error exception
 */
public class LexParseException extends Exception {
    public LexParseException() {
    }

    public LexParseException(String message) {
        super(message);
    }

    public LexParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LexParseException(Throwable cause) {
        super(cause);
    }

    public LexParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
