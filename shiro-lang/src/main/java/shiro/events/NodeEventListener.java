package shiro.events;

import java.util.EventListener;

/**
 * A node event listener interface
 * @author jeffreyguenther
 */
public interface NodeEventListener extends EventListener {
    public void handleNodeEvent(NodeEvent ne);
      
}
