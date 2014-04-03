/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import shiro.expressions.Expression;

/**
 * Defines a port declartion in shiro.
 * This class is used to create the abstract model of the source code that is 
 * modified at runtime.
 * @author jeffreyguenther
 */
public class PortDeclaration implements Definition{
    private PortType type;
    private String name;
    private String multifunction;
    private boolean hasArgs;
    private List<Expression> args;
    
    public PortDeclaration(PortType type, String name, String multifunction){
        this.type = type;
        this.name = name;
        this.multifunction = multifunction;
        this.hasArgs = false;
        args = new ArrayList<>();
    }

    public PortDeclaration(PortType type, String name, String multifunction, List<Expression> args) {
        this.type = type;
        this.name = name;
        this.multifunction = multifunction;
        this.args = args;
        this.hasArgs = true;
    }
    
    public PortDeclaration(PortType type, String name, String multifunction, Expression... args) {
        this.type = type;
        this.name = name;
        this.multifunction = multifunction;
        this.args = Arrays.asList(args);
        this.hasArgs = true;
    }

    public PortType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getMultifunction() {
        return multifunction;
    }
    
    public boolean hasArgs(){
        return hasArgs;
    }

    public List<Expression> getArguments() {
        return args;
    }
    
    public Expression getArgument(int i){
        return args.get(i);
    }
    
    public void setType(PortType type) {
        this.type = type;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setMultifunction(String multifunction) {
        this.multifunction = multifunction;
    }

    public void setArguments(List<Expression> args) {
        this.args = args;
        hasArgs = true;
    }
    
    public void setArgument(int i, Expression arg){
        args.set(i, arg);
        hasArgs = true;
    }

    @Override
    public String toString() {
        return toCode();
    }
    
    public String getTypeString(){
        if(type.equals(PortType.Evaluated)){
            return "eval";
        }else{
            return "port";
        }
    }

    @Override
    public String toCode() {
        STGroup group = Definition.getTemplate();
        ST st = group.getInstanceOf("portDecl");
        st.add("p", this);
        
        return st.render();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.type);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.multifunction);
        hash = 67 * hash + (this.hasArgs ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.args);
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
        final PortDeclaration other = (PortDeclaration) obj;
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.multifunction, other.multifunction)) {
            return false;
        }
        if (this.hasArgs != other.hasArgs) {
            return false;
        }
        if (!Objects.equals(this.args, other.args)) {
            return false;
        }
        return true;
    }
}
