package shiro;

/**
 *
 * @author jeffreyguenther
 */
public class PathNotFoundException extends Exception {

    /**
     * Creates a new instance of
     * <code>PathNotFoundException</code> without detail message.
     */
    public PathNotFoundException() {
    }

    /**
     * Constructs an instance of
     * <code>PathNotFoundException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public PathNotFoundException(String msg) {
        super(msg);
    }
}
