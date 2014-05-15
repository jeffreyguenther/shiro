/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.expressions;

import shiro.exceptions.PathNotFoundException;
import shiro.Port;
import shiro.Value;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import shiro.exceptions.PortNotActiveException;

/**
 *
 * @author jeffreyguenther
 */
public class Multiply implements Expression{
    private final Expression op1;
    private final Expression op2;

    public Multiply(Expression op1, Expression op2) {
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
        return "(" + op1 + " * " + op2 + ')';
    }

    @Override
    public String toCode() {
        return op1.toCode() + " * " + op2.toCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.op1);
        hash = 71 * hash + Objects.hashCode(this.op2);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Multiply other = (Multiply) obj;
        if (!Objects.equals(this.op1, other.op1)) {
            return false;
        }
        if (!Objects.equals(this.op2, other.op2)) {
            return false;
        }
        return true;
    }
    
    
}
