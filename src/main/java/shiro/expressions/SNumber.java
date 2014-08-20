package shiro.expressions;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import shiro.exceptions.PathNotFoundException;
import shiro.Port;
import shiro.exceptions.PortNotActiveException;
import shiro.Value;

/**
 * This class represents a number in the shiro lang
 * @author jeffreyguenther
 */
public class SNumber implements Expression{
    private final Double number;

    public SNumber(Double number) {
        this.number = number;
    }
    
    @Override
    public Value evaluate() throws PortNotActiveException{
           return Value.createDouble(number);
        
    }

    @Override
    public Set<Port> getPortsDependedOn() throws PathNotFoundException {
        Set<Port> ports = new HashSet<>();
        return ports;
    }
    
    @Override
    public String toString() {
        return "(" + number + ')';
    }

    @Override
    public String toCode() {
        if(number % 1 == 0){
            return "" + number.intValue();
        }
        
        return number.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.number);
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
        final SNumber other = (SNumber) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        return true;
    }
}
