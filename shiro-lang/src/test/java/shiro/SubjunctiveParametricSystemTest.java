package shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.junit.matchers.JUnitMatchers.containsString;
import shiro.expressions.Expression;
import shiro.expressions.Path;

/**
 * Test the parametric system
 *
 * @author jeffreyguenther
 */
public class SubjunctiveParametricSystemTest {

    private SubjunctiveParametricSystem setupPSystem() {
        SubjunctiveParametricSystem pSystem = new SubjunctiveParametricSystem();
        pSystem.loadDefinitions();
        return pSystem;
    }

    @Test
    public void loadNodeDefinitions() {
        SubjunctiveParametricSystem pSystem = setupPSystem();
        Assert.assertNotNull(pSystem.getNodeDef("Point"));
        Assert.assertNotNull(pSystem.getNodeDef("Line"));
    }

    @Test
    public void loadNodeDefinitionsFromURL() {
        SubjunctiveParametricSystem pSystem = new SubjunctiveParametricSystem();
        pSystem.loadDefinitions("example_code/example.sro");
        Assert.assertNotNull("Should have one node def \"Point\"", pSystem.getNodeDef("Point"));
        Assert.assertNotNull("Should have one node def \"Line\"", pSystem.getNodeDef("Line"));
    }

    @Test
    public void getInstanceCount() {
        SubjunctiveParametricSystem pSystem = setupPSystem();
        Assert.assertEquals(0, pSystem.getInstanceCountForNode("Line"));
        Assert.assertEquals(0, pSystem.getInstanceCountForNode("Point"));
        Assert.assertEquals(0, pSystem.getInstanceCountForNode("Georgeo"));

    }

    @Test
    public void createNodeName() {
        SubjunctiveParametricSystem pSystem = new SubjunctiveParametricSystem();
        Assert.assertEquals("Should be \"Point_1\"", "Point_1", pSystem.generateNodeInstanceName("Point", 1));
    }

    @Test
    public void incrementCountOfNodes() {
        SubjunctiveParametricSystem pSystem = setupPSystem();
        Assert.assertEquals(0, pSystem.getInstanceCountForNode("Point"));
        Assert.assertEquals(1, pSystem.incrementCountOfInstances("Point"));
        Assert.assertEquals(2, pSystem.incrementCountOfInstances("Point"));
    }

    @Test
    public void createNode() {
        SubjunctiveParametricSystem pSystem = setupPSystem();
        Assert.assertNotNull("Should have one node def \"Point\"", pSystem.getNodeDef("Point"));

        pSystem.createNode("Point");
        Node node = pSystem.getNode("Point_1");
        Assert.assertEquals("Should have one instance \"Point\"", 1, pSystem.getNodes().size());
        Assert.assertEquals(1, pSystem.getInstanceCountForNode("Point"));
        Assert.assertEquals("Should have name", "Point_1", node.getFullName());
    }

    @Test
    public void setPortExpression() {
        try {
            // Load the basic parametric system with Point and Line
            SubjunctiveParametricSystem pSystem = setupPSystem();
            
            // Create a node
            Node node = pSystem.createNode("Point");
            List<String> pathParts = new ArrayList<>();
            pathParts.add(node.getName());
            pathParts.add("x");
            
            // create an path object for the port x
            Path x = new Path(node, pathParts);
            Expression parsedExpression = pSystem.parseExpression(node, 10 + "");
            Port portX = pSystem.setPortExpression(x, parsedExpression);
            
            Assert.assertNotNull("Port should not be null", portX);
            assertThat(parsedExpression.getPortsDependedOn().isEmpty(), is(equalTo(true)));
            assertThat(portX.getArgumentForPosition(0).toString(), containsString("(10.0)"));
            //TODO add test code for more complicated expressions
            //TODO add test code for multiple args
            //TODO add test code for previously set args
        } catch (PathNotFoundException ex) {
            Logger.getLogger(SubjunctiveParametricSystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
