/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import java.util.ArrayList;
import java.util.List;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 *
 * @author jeffreyguenther
 */
public class NodeDefinition implements Definition{
    private String name;
    private String activeUpdatePort;
    private boolean hasActiveUpdatePort;
    private String beginInLineComment;
    private List<PortDeclaration> ports;
    private String endInLineComment;

    public NodeDefinition(String name) {
        this.name = name;
        this.activeUpdatePort = null;
        this.hasActiveUpdatePort = false;
        this.ports = new ArrayList<>();
        this.beginInLineComment = null;
        this.endInLineComment = null;
    }

    public void setPorts(List<PortDeclaration> ports) {
        this.ports = ports;
    }

    public String getActiveUpdatePort() {
        return activeUpdatePort;
    }

    public String getBeginInLineComment() {
        return beginInLineComment;
    }

    public String getName() {
        return name;
    }

    public List<PortDeclaration> getPorts() {
        return ports;
    }

    public boolean hasActiveUpdatePort() {
        return hasActiveUpdatePort;
    }
    
    

    public String getEndInLineComment() {
        return endInLineComment;
    }
    
    
    
    @Override
    public String toCode() {
        STGroup group = new STGroupFile("shiro/definitions/shiro.stg");
        ST st = group.getInstanceOf("nodeDecl");
        st.add("n", this);
        return st.render();
    }
}
