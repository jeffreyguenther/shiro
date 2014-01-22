package shiro;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Test the parametric system
 * @author jeffreyguenther
 */
public class SubjunctiveParametricSystemTest {
    @Test
    public void loadNodeDefinitions(){
        SubjunctiveParametricSystem pSystem = new SubjunctiveParametricSystem();
        pSystem.loadDefinitions();
        Assert.assertNotNull(pSystem.getNodeDef("Point"));
        Assert.assertNotNull(pSystem.getNodeDef("Line"));
    }
    
    @Test
    public void loadNodeDefinitionsFromURL(){
        SubjunctiveParametricSystem pSystem = new SubjunctiveParametricSystem();
        pSystem.loadDefinitions("example_code/example.sro");
        Assert.assertNotNull(pSystem.getNodeDef("Point"));
        Assert.assertNotNull(pSystem.getNodeDef("Line"));
    }
}
