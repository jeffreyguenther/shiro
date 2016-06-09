package org.shirolang.interpreter.v2;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains visitor methods shared between visitors
 */
public abstract class BaseVisitor {
    protected List<Error> errors;

    public BaseVisitor() {
        this.errors = new ArrayList<>();
    }

    /**
     * Gets a list of errors
     * Errors are added in the order they occur
     * @return the list of errors
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * Determines if a visitor has errors
     * @return true if the list of errors is not empty, otherwise false
     */
    public boolean hasErrors(){
        return !errors.isEmpty();
    }
}
