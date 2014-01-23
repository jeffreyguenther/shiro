package shiro;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Test the parametric system
 * @author jeffreyguenther
 */
public class SubjunctiveParametricSystemTest {
    
    private SubjunctiveParametricSystem setupPSystem(){
        SubjunctiveParametricSystem pSystem = new SubjunctiveParametricSystem();
        pSystem.loadDefinitions();
        return pSystem;
    }
    
    @Test
    public void loadNodeDefinitions(){
        SubjunctiveParametricSystem pSystem = setupPSystem();
        Assert.assertNotNull(pSystem.getNodeDef("Point"));
        Assert.assertNotNull(pSystem.getNodeDef("Line"));
    }
    
    @Test
    public void loadNodeDefinitionsFromURL(){
        SubjunctiveParametricSystem pSystem = new SubjunctiveParametricSystem();
        pSystem.loadDefinitions("example_code/example.sro");
        Assert.assertNotNull("Should have one node def \"Point\"", pSystem.getNodeDef("Point"));
        Assert.assertNotNull("Should have one node def \"Line\"", pSystem.getNodeDef("Line"));
    }
    
    @Test
    public void getInstanceCount(){
        SubjunctiveParametricSystem pSystem = setupPSystem();
        Assert.assertEquals(0, pSystem.getInstanceCountForNode("Line"));
        Assert.assertEquals(0, pSystem.getInstanceCountForNode("Point"));
        Assert.assertEquals(0, pSystem.getInstanceCountForNode("Georgeo"));
        
    }
    
    @Test
    public void createNodeName(){
        SubjunctiveParametricSystem pSystem = new SubjunctiveParametricSystem();
        Assert.assertEquals("Should be \"Point_1\"", "Point_1", pSystem.generateNodeInstanceName("Point", 1));
    }
    
    @Test
    public void incrementCountOfNodes(){
        SubjunctiveParametricSystem pSystem = setupPSystem();
        Assert.assertEquals(0, pSystem.getInstanceCountForNode("Point"));
        Assert.assertEquals(1, pSystem.incrementCountOfInstances("Point"));
        Assert.assertEquals(2, pSystem.incrementCountOfInstances("Point"));
    }
    
    @Test
    public void createNode(){
        SubjunctiveParametricSystem pSystem = setupPSystem();
        Assert.assertNotNull("Should have one node def \"Point\"", pSystem.getNodeDef("Point"));
        
        pSystem.createNode("Point");
        Node node = pSystem.getNode("Point_1");
        Assert.assertEquals("Should have one instance \"Point\"",1, pSystem.getNodes().size());
        Assert.assertEquals("Should have name", "Point_1", node.getFullName());
    }
}
