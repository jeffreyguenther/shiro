package shiro.expressions;

import java.util.Set;
import shiro.PathNotFoundException;
import shiro.Port;
import shiro.PortNotActiveException;
import shiro.Value;

/**
 * Expression evaluator
 * @author jeffreyguenther
 */
public interface Expression {
    public Value evaluate() throws PortNotActiveException;
    public Set<Port> getPortsDependedOn() throws PathNotFoundException;
}
