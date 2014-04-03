package shiro.expressions;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import shiro.PathNotFoundException;
import shiro.Port;
import shiro.PortNotActiveException;
import shiro.Value;

/**
 *
 * @author jeffreyguenther
 */
public class Or implements Expression{
    private final Expression op1;
    private final Expression op2;
    
    public Or(Expression op1, Expression op2){
        this.op1 = op1;
        this.op2 = op2;
    }
    
    @Override
    public Value evaluate() throws PortNotActiveException{
        // determine which value is active
        // if op1 is active and returns a value
        // return it
        // else op2 is active
        // return it
        
        return null;
    }

    @Override
    public Set<Port> getPortsDependedOn() throws PathNotFoundException {
        Set<Port> ports = new HashSet<Port>();
        ports.addAll(op1.getPortsDependedOn());
        ports.addAll(op2.getPortsDependedOn());
        return ports;
    }

    @Override
    public String toString() {
        return "(" + op1 + " | " + op2 + ')';
    }

    @Override
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.op1);
        hash = 31 * hash + Objects.hashCode(this.op2);
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
        final Or other = (Or) obj;
        if (!Objects.equals(this.op1, other.op1)) {
            return false;
        }
        if (!Objects.equals(this.op2, other.op2)) {
            return false;
        }
        return true;
    }
}
