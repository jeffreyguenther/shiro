package shiro.expressions;

import java.util.HashSet;
import java.util.Set;
import shiro.PathNotFoundException;
import shiro.Port;
import shiro.PortNotActiveException;
import shiro.Value;

/**
 * A Subtraction operator
 * @author jeffreyguenther
 */
public class Subtract implements Expression{
    private final Expression op1;
    private final Expression op2;

    public Subtract(Expression op1, Expression op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public Value evaluate() throws PortNotActiveException {
        return op1.evaluate().subtract(op2.evaluate());
    }

    @Override
    public Set<Port> getPortsDependedOn() throws PathNotFoundException{
        Set<Port> ports = new HashSet<Port>();
        ports.addAll(op1.getPortsDependedOn());
        ports.addAll(op2.getPortsDependedOn());
        return ports;
    }
}
