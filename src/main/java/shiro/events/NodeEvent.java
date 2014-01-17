package shiro.events;

import java.util.EventObject;

/**
 *
 * @author jeffreyguenther
 */
public class NodeEvent extends EventObject {
    private String message;
    
    public NodeEvent(Object o, String msg) {
        super(o);
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
    
    
    
}
