package shiro;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private SubjunctiveParametricSystem setupPSystemWithSubjuncts() throws IOException {
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
    public void createNode() {
        SubjunctiveParametricSystem pSystem = setupPSystem();
        Assert.assertNotNull("Should have one node def \"Point\"", pSystem.getNodeDef("Point"));

        pSystem.createNode("Point");
        Node node = pSystem.getNode("point1");
        Assert.assertEquals("Should have one instance \"Point\"", 1, pSystem.getNodes().size());
        Assert.assertEquals(1, pSystem.getInstanceCountForNode("Point"));
        Assert.assertEquals("Should have name", "point1", node.getFullName());
    }

    @Test
    public void setPortExpression() throws PathNotFoundException {

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
    }

    @Test
    public void resolvePrototypePath() {
        try {
            SubjunctiveParametricSystem pSystem = setupPSystemWithSubjuncts();

            Path nodePath = new Path("Point");
            Symbol pointStringPath = pSystem.find("Point");
            Symbol pointPath = pSystem.find(nodePath);
            Assert.assertSame("should be equal", pointStringPath, pointPath);
            Assert.assertEquals("Should be a node", SymbolType.NODE, pointStringPath.getType());
            Assert.assertEquals("Should be a node", SymbolType.NODE, pointPath.getType());

            Path subjNodePath = new Path("EndPoints");
            Symbol endPointStringPath = pSystem.find("EndPoints");
            Symbol endPointPath = pSystem.find(subjNodePath);
            Assert.assertSame("should be equal", endPointStringPath, endPointPath);
            Assert.assertEquals("Should be a subjunctive node", SymbolType.SUBJ, endPointStringPath.getType());
            Assert.assertEquals("Should be a subjunctive node", SymbolType.SUBJ, endPointPath.getType());

        } catch (IOException | PathNotFoundException | PathNotAccessibleException ex) {
            Logger.getLogger(SubjunctiveParametricSystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(expected = PathNotFoundException.class)
    public void invalidPath() throws PathNotFoundException, IOException, PathNotAccessibleException {

        SubjunctiveParametricSystem pSystem = setupPSystemWithSubjuncts();

        Path error = new Path("startP4ints");
        pSystem.find("startP4ints");
        pSystem.find(error);
    }

    @Test
    public void resolveInstancePath() throws IOException,
            PathNotAccessibleException, PathNotFoundException {
        SubjunctiveParametricSystem pSystem = setupPSystemWithSubjuncts();
        Path instance = new Path("startPoint");
        Symbol startPointStringPath = pSystem.find("startPoint");
        Symbol startPointPath = pSystem.find(instance);

        Assert.assertNotNull(startPointStringPath);
        Assert.assertNotNull(startPointPath);
        Assert.assertSame("should be equal", startPointStringPath, startPointPath);
        Assert.assertEquals("Should be a node", SymbolType.NODE, startPointStringPath.getType());
        Assert.assertEquals("Should be a node", SymbolType.NODE, startPointPath.getType());

        Path subjInstance = new Path("endPoint");
        Symbol endPointsStringPath = pSystem.find("endPoint");
        Symbol endPointsPath = pSystem.find(subjInstance);

        Assert.assertNotNull(endPointsStringPath);
        Assert.assertNotNull(endPointsPath);
        Assert.assertSame("should be equal", endPointsStringPath, endPointsPath);
        Assert.assertEquals("Should be a node", SymbolType.SUBJ, endPointsStringPath.getType());
        Assert.assertEquals("Should be a node", SymbolType.SUBJ, endPointsPath.getType());

        Path p1 = new Path("endPoint", "P1");
        Symbol p1StringPath = pSystem.find("endPoint.P1");
        Symbol p1Path = pSystem.find(p1);

        Assert.assertNotNull(p1StringPath);
        Assert.assertNotNull(p1Path);
        Assert.assertSame("should be equal", p1StringPath, p1Path);
        Assert.assertEquals("Should be a node", SymbolType.NODE, p1StringPath.getType());
        Assert.assertEquals("Should be a node", SymbolType.NODE, p1Path.getType());

        Path p2 = new Path("endPoint", "P2");
        Symbol p2StringPath = pSystem.find("endPoint.P2");
        Symbol p2Path = pSystem.find(p2);

        Assert.assertNotNull(p2StringPath);
        Assert.assertNotNull(p2Path);
        Assert.assertSame("should be equal", p2StringPath, p2Path);
        Assert.assertEquals("Should be a node", SymbolType.NODE, p2StringPath.getType());
        Assert.assertEquals("Should be a node", SymbolType.NODE, p2Path.getType());
        Node n = (Node) p2Path;
        Assert.assertEquals("endPoint.P2", n.getFullName());

    }

    @Test(expected = PathNotAccessibleException.class)
    public void inaccessiblePort() throws IOException, PathNotAccessibleException, PathNotFoundException {
        SubjunctiveParametricSystem pSystem = setupPSystemWithSubjuncts();
        pSystem.find("Point"); // need to create prototype

        Path update = new Path("Point", "update");
        Symbol updatePortPath = pSystem.find(update);
        Symbol updatePortString = pSystem.find("Point.update");
        Assert.assertSame("should be the same reference", updatePortPath, updatePortString);
        assertThat("should be a port", updatePortPath.getType(), is(equalTo(SymbolType.PORT)));
        assertThat("should be a port", updatePortString.getType(), is(equalTo(SymbolType.PORT)));
    }

    @Test
    public void resolvePorts() throws PathNotAccessibleException, IOException, PathNotFoundException {
        SubjunctiveParametricSystem pSystem = setupPSystemWithSubjuncts();
        pSystem.find("Point"); // needed to create the Point prototype

        Path x = new Path("Point", "x");
        Symbol xPortPath = pSystem.find(x);
        Symbol xPortString = pSystem.find("Point.x");
        Assert.assertSame("should be the same reference", xPortPath, xPortString);
        assertThat("should be a port", xPortPath.getType(), is(equalTo(SymbolType.PORT)));
        assertThat("should be a port", xPortString.getType(), is(equalTo(SymbolType.PORT)));

        Path y = new Path("Point", "y");
        Symbol yPortPath = pSystem.find(y);
        Symbol yPortString = pSystem.find("Point.y");
        Assert.assertSame("should be the same reference", yPortPath, yPortString);
        assertThat("should be a port", yPortPath.getType(), is(equalTo(SymbolType.PORT)));
        assertThat("should be a port", yPortString.getType(), is(equalTo(SymbolType.PORT)));

        Path point = new Path("Point", "point");
        Symbol pointPortPath = pSystem.find(point);
        Symbol pointPortString = pSystem.find("Point.point");
        Assert.assertSame("should be the same reference", pointPortPath, pointPortString);
        assertThat("should be a port", pointPortPath.getType(), is(equalTo(SymbolType.PORT)));
        assertThat("should be a port", pointPortString.getType(), is(equalTo(SymbolType.PORT)));

        Path startPointX = new Path("startPoint", "x");
        Symbol startPointXPath = pSystem.find(startPointX);
        Symbol startPointXString = pSystem.find("startPoint.x");
        Assert.assertSame("should be the same reference", startPointXPath, startPointXString);
        assertThat("should be a port", startPointXPath.getType(), is(equalTo(SymbolType.PORT)));
        assertThat("should be a port", startPointXString.getType(), is(equalTo(SymbolType.PORT)));
        Port p = (Port) startPointXPath;
        Assert.assertEquals("should have the same name", "startPoint.x", p.getFullName());

        Path activeX = new Path("endPoint", "active", "x");
        Symbol activeXPath = pSystem.find(activeX);
        Symbol activeXString = pSystem.find("endPoint.active.x");
        Assert.assertSame("should be the same reference", activeXString, activeXPath);
        Assert.assertEquals("should be port", SymbolType.PORT, activeXPath.getType());
        Assert.assertEquals("should be port", SymbolType.PORT, activeXString.getType());

        Path p1X = new Path("endPoint", "P1", "x");
        Symbol p1XPath = pSystem.find(p1X);
        Symbol p1XString = pSystem.find("endPoint.P1.x");
        Assert.assertSame("should be the same reference", p1XString, p1XPath);
        Assert.assertEquals("should be port", SymbolType.PORT, p1XPath.getType());
        Assert.assertEquals("should be port", SymbolType.PORT, p1XString.getType());

        Path p2X = new Path("endPoint", "P2", "x");
        Symbol p2XPath = pSystem.find(p2X);
        Symbol p2XString = pSystem.find("endPoint.P2.x");
        Assert.assertSame("should be the same reference", p2XString, p2XPath);
        Assert.assertEquals("should be port", SymbolType.PORT, p2XPath.getType());
        Assert.assertEquals("should be port", SymbolType.PORT, p2XString.getType());
    }

    @Test
    public void findAndReplace() throws IOException {
        SubjunctiveParametricSystem pSystem = setupPSystemWithSubjuncts();
        Path p = Path.createPath("startPoint");
        List<Port> ports = pSystem.findAll(p);
        Assert.assertEquals("should have one port", 1, ports.size());
        Port found = ports.get(0);

        pSystem.replace(found, p, "StartPoints", true);

        Path p1 = Path.createPath("StartPoints");
        ports = pSystem.findAll(p1);
        Assert.assertEquals("should have one port", 1, ports.size());
        Assert.assertSame("should be the same port", found, ports.get(0));
    }

    @Test
    public void split() throws IOException, PathNotFoundException, PathNotAccessibleException {
        SubjunctiveParametricSystem pSystem = setupPSystemWithSubjuncts();
        
        Map<Path, List<Expression>> newValues = new HashMap<>();
        newValues.put(Path.createPath("x"), Arrays.asList(new shiro.expressions.Number(200d)));
        newValues.put(Path.createPath("y"), Arrays.asList(new shiro.expressions.Number(200d)));
        
        Node n = (Node) pSystem.find("startPoint");
        SubjunctiveNode subjunctiveNode = pSystem.split(n, "StartPoints", "P1", newValues);
        pSystem.addSubjunctiveNode(subjunctiveNode);

        Assert.assertNotNull(subjunctiveNode);
        Assert.assertEquals("StartPoints", subjunctiveNode.getFullName());
        
//        pSystem.update(pSystem.getState("state1"));
        System.out.println(pSystem.toCode());
    }
}
