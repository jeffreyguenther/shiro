/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.definitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import shiro.expressions.Expression;

/**
 *
 * @author jeffreyguenther
 */
public class NodeDefinitionTest {    
    @Test
    public void getPorts(){
        List<PortDeclaration> expectedOrder = new ArrayList<>();
        expectedOrder.add(new PortDeclaration(PortType.Input, "x", "Value", Expression.number(3)));
        expectedOrder.add(new PortDeclaration(PortType.Input, "z", "Value", Expression.number(3)));
        expectedOrder.add(new PortDeclaration(PortType.Evaluated, "dogs", "Point", 
                Expression.path("x", 0), Expression.path("y", 0)));
        expectedOrder.add(new PortDeclaration(PortType.Evaluated, "update", "Point", 
                Expression.path("x", 0), Expression.path("y", 0)));
        expectedOrder.add(new PortDeclaration(PortType.Output, "area", "Value", 
                Expression.number(3), Expression.number(5), Expression.number(9)));
        expectedOrder.add(new PortDeclaration(PortType.Output, "point", "Value", 
            Expression.number(3), Expression.number(5), Expression.number(9)));
        
        List<PortDeclaration> ports = new ArrayList<>(expectedOrder);
        Collections.shuffle(ports);
        Assert.assertFalse("should not match", expectedOrder.equals(ports));
        
        NodeDefinition def = new NodeDefinition("Point");
        def.setPorts(ports);
        
        Assert.assertEquals("should be order: INPUT, EVALUATED, OUTPUT", expectedOrder, def.getPorts());
    }

    @Test
    public void basicNode() throws IOException {
        String nodeDef = Definitions.loadDef("nodedef.sro", this.getClass());
        
        NodeDefinition def = new NodeDefinition("Point");
        List<PortDeclaration> ports = new ArrayList<>();
        ports.add(new PortDeclaration(PortType.Input, "x", "Value", Expression.number(3)));
        ports.add(new PortDeclaration(PortType.Input, "y", "Value", Expression.number(3)));
        ports.add(new PortDeclaration(PortType.Output, "point", "Value", 
                Expression.number(3), Expression.number(5), Expression.number(9)));
        ports.add(new PortDeclaration(PortType.Evaluated, "update", "Point", 
                Expression.path("x", 0), Expression.path("y", 0)));
        def.setPorts(ports);
        Assert.assertEquals("should match", nodeDef, def.toCode());
        
        String nodedefWOX = Definitions.loadDef("nodedef_wo_x.sro", this.getClass());
        def.removePortDeclaration(new PortDeclaration(PortType.Input, "x", "Value", Expression.number(3)));
        Assert.assertEquals("should match", nodedefWOX, def.toCode());
        
        def.addPortDeclaration(new PortDeclaration(PortType.Input, "x", "Value", Expression.number(3)));
        Assert.assertEquals("should match", nodeDef, def.toCode());
    }
}
