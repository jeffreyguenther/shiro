/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import java.io.IOException;
import junit.framework.Assert;
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
}
