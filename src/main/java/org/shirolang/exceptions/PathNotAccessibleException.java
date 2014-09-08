package org.shirolang.exceptions;

/**
 *
 * @author jeffreyguenther
 */
public class PathNotAccessibleException extends Exception {

    /**
     * Creates a new instance of
     * <code>PathNotFoundException</code> without detail message.
     */
    public PathNotAccessibleException() {
    }

    /**
     * Constructs an instance of
     * <code>PathNotFoundException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public PathNotAccessibleException(String msg) {
        super(msg);
    }
}
