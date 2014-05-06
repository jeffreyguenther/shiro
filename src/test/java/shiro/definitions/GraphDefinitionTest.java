/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import shiro.expressions.Path;

/**
 *
 * @author jeffreyguenther
 */
public class GraphDefinitionTest {
    
    
    @Test
    public void toCode() throws IOException{
        String expectedDef = Definitions.loadDef("graphdef.sro", getClass());
        GraphDefinition graphDef = new GraphDefinition("line");
        graphDef.addProduction("Point", "startPoint");
        graphDef.addProduction("EndPoints", "endPoint");
        graphDef.addProduction("Line", "l");
        graphDef.addPortAssignment(new PortAssignment(Path.createPath("startPoint.x"), new shiro.expressions.Number(100d)));
        graphDef.addPortAssignment(new PortAssignment(Path.createPath("startPoint.y"), new shiro.expressions.Number(0d)));
        
        Assert.assertEquals("should match", expectedDef, graphDef.toCode());    
    }
    
    @Test
    public void addPortAssignment(){
        // Create a new graph definition
        GraphDefinition graphDef = new GraphDefinition("line");
        
        // Add a port assignemtn
        PortAssignment pa1 = new PortAssignment(Path.createPath("startPoint.x"), new shiro.expressions.Number(100d));
        graphDef.addPortAssignment(pa1);
        
        // Create list of expected elements
        List<PortAssignment> expected = new ArrayList<>();
        expected.add(pa1);
        
        // Test if port assignments match
        List<PortAssignment> result = graphDef.getPortAssignments();
        Assert.assertEquals("should have the same port assignments", expected, result);
        
        // Add another port assignment, but this time for the same path
        PortAssignment pa2 = new PortAssignment(Path.createPath("startPoint.x"), new shiro.expressions.Number(400d));
        graphDef.addPortAssignment(pa2);
        
        // Test to ensure the port assignment for the path was updated
        List<PortAssignment> expected2 = new ArrayList<>();
        expected2.add(pa2);
        Assert.assertEquals(1, graphDef.getPortAssignments().size());
        Assert.assertEquals(expected2, graphDef.getPortAssignments());
    }
}
