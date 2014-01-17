package shiro.events;

import java.util.EventObject;

/**
 *
 * @author jeffreyguenther
 */
public class SubjParametricSystemEvent extends EventObject{
        private String message;
    
    public SubjParametricSystemEvent(Object o, String msg) {
        super(o);
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
    
}
