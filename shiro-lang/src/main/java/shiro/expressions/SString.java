/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.expressions;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import shiro.PathNotFoundException;
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
}