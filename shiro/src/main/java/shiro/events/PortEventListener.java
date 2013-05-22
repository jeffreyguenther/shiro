package shiro.events;

import java.util.EventListener;

/**
 * A listener interface for port events (update, etc)
 * @author jeffreyguenther
 */
public interface PortEventListener extends EventListener{
    public void handlePortEvent(PortEvent event);
}
