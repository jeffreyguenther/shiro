package org.shirolang.interpreter.v2;

/**
 * An error when the include cannot be found
 */
public class IncludeNotFoundError implements Error{
    private String file;

    public IncludeNotFoundError(String file) {
        this.file = file;
    }

    @Override
    public String getMessage() {
        return file + " cannot be found.";
    }
}