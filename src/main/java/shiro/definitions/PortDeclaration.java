/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

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
    private List<String> args;
    
    public PortDeclaration(PortType type, String name, String multifunction){
        this.type = type;
        this.name = name;
        this.multifunction = multifunction;
        this.hasArgs = false;
        args = new ArrayList<>();
    }

    public PortDeclaration(PortType type, String name, String multifunction, List<String> args) {
        this.type = type;
        this.name = name;
        this.multifunction = multifunction;
        this.args = args;
        this.hasArgs = true;
    }
    
    public PortDeclaration(PortType type, String name, String multifunction, String... args) {
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

    public List<String> getArguments() {
        return args;
    }
    
    public String getArgument(int i){
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

    public void setArguments(List<String> args) {
        this.args = args;
        hasArgs = true;
    }
    
    public void setArgument(int i, String arg){
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
        STGroup group = new STGroupFile("shiro/definitions/shiro.stg");
        ST st = group.getInstanceOf("portDecl");
        st.add("p", this);
        
        
        return st.render();
    }
}
