/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.expressions;

import java.util.HashSet;
import java.util.Set;
import shiro.Port;
import shiro.PortNotActiveException;
import shiro.Value;

/**
 *
 * @author jeffreyguenther
 */
public class Number implements Expression{
    private final Float number;

    public Number(Float number) {
        this.number = number;
    }
    
    @Override
    public Value evaluate() throws PortNotActiveException{
           return Value.createFloat(number);
        
    }

    @Override
    public Set<Port> getPortsDependedOn() {
        Set<Port> ports = new HashSet<Port>();
        return ports;
    }
    
    @Override
    public String toString() {
        return "(" + number + ')';
    }
    
    
}
