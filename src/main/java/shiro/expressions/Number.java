package shiro.expressions;

import java.util.HashSet;
import java.util.Set;
import shiro.PathNotFoundException;
import shiro.Port;
import shiro.PortNotActiveException;
import shiro.Value;

/**
 * This class represents a number in the shiro lang
 * @author jeffreyguenther
 */
public class Number implements Expression{
    private final Double number;

    public Number(Double number) {
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
    
    
}
