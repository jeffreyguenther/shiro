package shiro.events;

import java.util.EventObject;

/**
 * A basic port event
 * @author jeffreyguenther
 */
public class PortEvent extends EventObject {
    private String message;
    
    public PortEvent(Object o, String message) {
        super(o);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
