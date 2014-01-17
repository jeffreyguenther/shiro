package shiro.events;

/**
 *
 * @author jeffreyguenther
 */
public interface PortUpdateEventListener extends PortEventListener{
    public void handlePortUpdateEvent(PortEvent event);
}
