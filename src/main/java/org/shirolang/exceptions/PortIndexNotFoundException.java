/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.exceptions;

/**
 *
 * @author jeffreyguenther
 */
public class PortIndexNotFoundException extends Exception {

    /**
     * Creates a new instance of
     * <code>PortIndexNotFoundException</code> without detail message.
     */
    public PortIndexNotFoundException() {
    }

    /**
     * Constructs an instance of
     * <code>PortIndexNotFoundException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public PortIndexNotFoundException(String msg) {
        super(msg);
    }
}
