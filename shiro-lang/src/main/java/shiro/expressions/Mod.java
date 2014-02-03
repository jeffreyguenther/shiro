/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.expressions;

import shiro.PathNotFoundException;
import shiro.Port;
import shiro.Value;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import shiro.PortNotActiveException;

/**
 *
 * @author jeffreyguenther
 */
public class Mod implements Expression{
    private final Expression op1;
    private final Expression op2;

    public Mod(Expression op1, Expression op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public Value evaluate() throws PortNotActiveException {
        return op1.evaluate().times(op2.evaluate());
    }

    @Override
    public Set<Port> getPortsDependedOn() throws PathNotFoundException{
        Set<Port> ports = new HashSet<Port>();
        ports.addAll(op1.getPortsDependedOn());
        ports.addAll(op2.getPortsDependedOn());
        return ports;
    }
    
    @Override
    public String toString() {
        return "(" + op1 + " % " + op2 + ')';
    }
    
}
