/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.expressions;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import shiro.exceptions.PathNotFoundException;
import shiro.Port;
import shiro.Value;

/**
 * Represents a boolean expression in Shiro.
 * @author jeffreyguenther
 */
@SuppressWarnings("serial")
public class SBoolean implements Expression, Serializable{
    private final Boolean booolean;

    public SBoolean(Boolean bool) {
        this.booolean = bool;
    }
    
    @Override
    public Value evaluate() {
           return Value.createBoolean(booolean);
        
    }

    @Override
    public Set<Port> getPortsDependedOn() throws PathNotFoundException{
        Set<Port> ports = new HashSet<Port>();
        return ports;
    }
    
    @Override
    public String toString() {
        return "(" + booolean + ')';
    }

    @Override
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.booolean);
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
        final SBoolean other = (SBoolean) obj;
        if (!Objects.equals(this.booolean, other.booolean)) {
            return false;
        }
        return true;
    }
}