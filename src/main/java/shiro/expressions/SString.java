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
 *
 * @author ankit
 */
@SuppressWarnings("serial")
public class SString implements Expression, Serializable{
    private final String string;

    public SString(String string) {
        this.string = string;
    }
    
    @Override
    public Value evaluate() {
           return Value.createString(string);
        
    }

    @Override
    public Set<Port> getPortsDependedOn() throws PathNotFoundException{
        Set<Port> ports = new HashSet<Port>();
        return ports;
    }
    
    @Override
    public String toString() {
        return "(" + string + ')';
    }

    @Override
    public String toCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.string);
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
        final SString other = (SString) obj;
        if (!Objects.equals(this.string, other.string)) {
            return false;
        }
        return true;
    }
    
    
}