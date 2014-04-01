/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.definitions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author jeffreyguenther
 */
public class NodeDefinitionTest {
    
    private static String loadNodeDef(String def) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(
                NodeDefinition.class.getResource(def).getFile()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        String newLine = "\n";
        while( (line = reader.readLine()) != null){
            sb.append(line);
            sb.append(newLine);
        }
        
        return sb.toString().trim();
    }
    
    @Test
    public void getPorts(){
        List<PortDeclaration> expectedOrder = new ArrayList<>();
        expectedOrder.add(new PortDeclaration(PortType.Input, "x", "Value", "3"));
        expectedOrder.add(new PortDeclaration(PortType.Input, "z", "Value", "3"));
        expectedOrder.add(new PortDeclaration(PortType.Evaluated, "dogs", "Point", "x[0]", "y[0]"));
        expectedOrder.add(new PortDeclaration(PortType.Evaluated, "update", "Point", "x[0]", "y[0]"));
        expectedOrder.add(new PortDeclaration(PortType.Output, "area", "Value", "3", "5", "9"));
        expectedOrder.add(new PortDeclaration(PortType.Output, "point", "Value", "3", "5", "9"));
        
        List<PortDeclaration> ports = new ArrayList<>(expectedOrder);
        Collections.shuffle(ports);
        Assert.assertFalse("should not match", expectedOrder.equals(ports));
        
        NodeDefinition def = new NodeDefinition("Point");
        def.setPorts(ports);
        
        Assert.assertEquals("should be order: INPUT, EVALUATED, OUTPUT", expectedOrder, def.getPorts());
    }

    @Test
    public void basicNode() throws IOException {
        String nodeDef = loadNodeDef("nodedef.sro");
        
        NodeDefinition def = new NodeDefinition("Point");
        List<PortDeclaration> ports = new ArrayList<>();
        ports.add(new PortDeclaration(PortType.Input, "x", "Value", "3"));
        ports.add(new PortDeclaration(PortType.Input, "y", "Value", "3"));
        ports.add(new PortDeclaration(PortType.Output, "point", "Value", "3", "5", "9"));
        ports.add(new PortDeclaration(PortType.Evaluated, "update", "Point", "x[0]", "y[0]"));
        def.setPorts(ports);
        Assert.assertEquals("should match", nodeDef, def.toCode());
        
        String nodedefWOY = loadNodeDef("nodedef_wo_x.sro");
        def.removePortDeclaration(new PortDeclaration(PortType.Input, "x", "Value", "3"));
        Assert.assertEquals("should match", nodedefWOY, def.toCode());
        
        def.addPortDeclaration(new PortDeclaration(PortType.Input, "x", "Value", "3"));
        Assert.assertEquals("should match", nodeDef, def.toCode());
    }
}
