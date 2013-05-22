package shiro.events;

import java.util.EventListener;

/**
 * 
 * @author jeffreyguenther
 */
public interface SubjParametricSystemEventListener extends EventListener {
    public void handlePortEvent(SubjParametricSystemEvent event);
}
