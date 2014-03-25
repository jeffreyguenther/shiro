package shiro;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import org.hamcrest.core.IsSame;
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
    
    private SubjunctiveParametricSystem setupPSystemWithSubjuncts() throws IOException{
        SubjunctiveParametricSystem pSystem = new SubjunctiveParametricSystem();
        URL resource = this.getClass().getResource("SimpleSubjunctiveExample.sro");
        pSystem.loadCode(new File(resource.getPath()));
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
    
    @Test
    public void resolvePrototypePath(){
        try {
            SubjunctiveParametricSystem pSystem = setupPSystemWithSubjuncts();
            
            Path nodePath = new Path("Point");
            Symbol pointStringPath = pSystem.findObject("Point");
            Symbol pointPath = pSystem.findObject(nodePath);
            Assert.assertSame("should be equal", pointStringPath, pointPath);
            Assert.assertEquals("Should be a node", SymbolType.NODE, pointStringPath.getType());
            Assert.assertEquals("Should be a node", SymbolType.NODE, pointPath.getType());
            
            Path subjNodePath = new Path("EndPoints");
            Symbol endPointStringPath = pSystem.findObject("EndPoints");
            Symbol endPointPath = pSystem.findObject(subjNodePath);
            Assert.assertSame("should be equal", endPointStringPath, endPointPath);
            Assert.assertEquals("Should be a subjunctive node", SymbolType.SUBJ, endPointStringPath.getType());
            Assert.assertEquals("Should be a subjunctive node", SymbolType.SUBJ, endPointPath.getType());
            
        } catch (IOException | PathNotFoundException | PathNotAccessibleException ex) {
            Logger.getLogger(SubjunctiveParametricSystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(expected = PathNotFoundException.class)
    public void resolveInstancePath() throws PathNotFoundException{
        try {
            SubjunctiveParametricSystem pSystem = setupPSystemWithSubjuncts();
            
            Path error = new Path("startP4ints");
            Symbol errorPathString = pSystem.findObject("startPoint");
            Symbol errorpath = pSystem.findObject(error);
            Assert.assertNull("should be null", errorPathString);
            Assert.assertNull("should be null", errorpath);
            
            Path instance = new Path("startPoint");
            Symbol startPointStringPath = pSystem.findObject("startPoint");
            Symbol startPointPath = pSystem.findObject(instance);
            Assert.assertNotNull(startPointStringPath);
            Assert.assertNotNull(startPointPath);
            Assert.assertSame("should be equal", startPointStringPath, startPointPath);
            Assert.assertTrue("Should be a node", SymbolType.PORT == startPointStringPath.getType());
//            Assert.assertEquals("Should be a node", , startPointPath.getType());
           
            
        } catch (IOException | PathNotAccessibleException ex) {
            Logger.getLogger(SubjunctiveParametricSystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test(expected = PathNotAccessibleException.class)
    public void resolvePorts() throws PathNotAccessibleException{
        try {
            SubjunctiveParametricSystem pSystem = setupPSystemWithSubjuncts();
            pSystem.findObject("Point");
            
            Path x = new Path("Point", "x");
            Symbol xPortPath = pSystem.findObject(x);
            Symbol xPortString = pSystem.findObject("Point.x");
            Assert.assertSame("should be the same reference", xPortPath, xPortString);
            assertThat("should be a port", xPortPath.getType(), is(equalTo(SymbolType.PORT)));
            assertThat("should be a port", xPortString.getType(), is(equalTo(SymbolType.PORT)));
            
            Path y = new Path("Point", "y");
            Symbol yPortPath = pSystem.findObject(y);
            Symbol yPortString = pSystem.findObject("Point.y");
            Assert.assertSame("should be the same reference", yPortPath, yPortString);
            assertThat("should be a port", yPortPath.getType(), is(equalTo(SymbolType.PORT)));
            assertThat("should be a port", yPortString.getType(), is(equalTo(SymbolType.PORT)));
            
            Path update = new Path("Point", "update");
            Symbol updatePortPath = pSystem.findObject(update);
            Symbol updatePortString = pSystem.findObject("Point.update");
            Assert.assertSame("should be the same reference", updatePortPath, updatePortString);
            assertThat("should be a port", updatePortPath.getType(), is(equalTo(SymbolType.PORT)));
            assertThat("should be a port", updatePortString.getType(), is(equalTo(SymbolType.PORT)));
            
            Path point = new Path("Point", "point");
            Symbol pointPortPath = pSystem.findObject(point);
            Symbol pointPortString = pSystem.findObject("Point.point");
            Assert.assertSame("should be the same reference", pointPortPath, pointPortString);
            assertThat("should be a port", pointPortPath.getType(), is(equalTo(SymbolType.PORT)));
            assertThat("should be a port", pointPortString.getType(), is(equalTo(SymbolType.PORT)));
        } catch (IOException | PathNotFoundException ex) {
            Logger.getLogger(SubjunctiveParametricSystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}