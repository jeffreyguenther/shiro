/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import java.util.ArrayList;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author jeffreyguenther
 */
public class PortDeclarationTest {
    @Test
    public void portDeclaratioWithoutArgs(){
        PortDeclaration inputPort = new PortDeclaration(PortType.Input, "x", "Value", new ArrayList<>());
        Assert.assertEquals("should be port", "port", inputPort.getTypeString());
        Assert.assertEquals("should produce basic port", "port x Value()", inputPort.toCode());
        
        PortDeclaration outputPort = new PortDeclaration(PortType.Output, "point", "Value", new ArrayList<>());
        Assert.assertEquals("should produce basic port", "port point Value()", outputPort.toCode());
        
        PortDeclaration evalPort = new PortDeclaration(PortType.Evaluated, "update", "Point", new ArrayList<>());
        Assert.assertEquals("should produce eval port", "eval update Point()", evalPort.toCode());
    }
    
    @Test
    public void portDeclarationWithArgs(){
        PortDeclaration inputPort = new PortDeclaration(PortType.Input, "x", "Value", "3");
        Assert.assertEquals("should produce port with args", "port x Value(3)", inputPort.toCode());
        
        PortDeclaration outputPort = new PortDeclaration(PortType.Output, "point", "Value", "3", "5", "9");
        Assert.assertEquals("should produce port with args", "port point Value(3, 5, 9)", outputPort.toCode());
        
        PortDeclaration evalPort = new PortDeclaration(PortType.Evaluated, "update", "Point", "x[0]", "y[0]");
        Assert.assertEquals("should produce eval port with args", "eval update Point(x[0], y[0])", evalPort.toCode());
    }
}
