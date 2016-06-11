package org.shirolang.interpreter.v2;

/**
 * Created by jeffrey on 2016-06-11.
 */
public class BaseError implements Error {
    protected String message;

    public BaseError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
