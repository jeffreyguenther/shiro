package shiro;

import shiro.exceptions.PathNotAccessibleException;
import shiro.exceptions.PathNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import shiro.dag.DependencyRelation;
import shiro.expressions.Path;
import shiro.shared.CodeLoader;

/**
 * Test the parametric system
 *
 * @author jeffreyguenther
 */
public class SubjunctiveParametricSystemTest extends CodeLoader{
    @Test
    public void loadNodeDefinitions() throws IOException {
        Runtime pSystem = setupPSystem();
        Assert.assertNotNull(pSystem.getNodeDef("Point"));
        Assert.assertNotNull(pSystem.getNodeDef("Line"));
    }

    @Test
    public void loadNodeDefinitionsFromURL() throws URISyntaxException, IOException{
        Runtime pSystem = new Runtime();
        URL resource = getClass().getClassLoader().getResource("shiro/SimpleSubjunctiveExample.sro");
        pSystem.loadDefinitions(Paths.get(resource.toURI()));
        Assert.assertNotNull("Should have one node def \"Point\"", pSystem.getNodeDef("Point"));
        Assert.assertNotNull("Should have one node def \"Line\"", pSystem.getNodeDef("Line"));
    }

    @Test
    public void getInstanceCount() throws IOException {
        Runtime pSystem = setupPSystem();
        Assert.assertEquals(0, pSystem.getInstanceCountForNode("Line"));
        Assert.assertEquals(0, pSystem.getInstanceCountForNode("Point"));
        Assert.assertEquals(0, pSystem.getInstanceCountForNode("Georgeo"));

    }

    @Test
    public void createNode() throws IOException {
        Runtime pSystem = setupPSystem();
        Assert.assertNotNull("Should have one node def \"Point\"", pSystem.getNodeDef("Point"));

        Node createNode = pSystem.createNode("Point");
        pSystem.addNode(createNode);
        Node node = pSystem.getNode("point1");
        Assert.assertEquals("Should have one instance \"Point\"", 1, pSystem.getNodes().size());
        Assert.assertEquals(1, pSystem.getInstanceCountForNode("Point"));
        Assert.assertEquals("Should have name", "point1", node.getFullName());
    }

//    @Test
//    public void setPortExpression() throws PathNotFoundException {
//
//        // Load the basic parametric system with Point and Line
//        Runtime pSystem = setupPSystem();
//
//        // Create a node
//        Node node = pSystem.createNode("Point");
//        List<String> pathParts = new ArrayList<>();
//        pathParts.add(node.getName());
//        pathParts.add("x");
//
//        // create an path object for the port x
//        Path x = new Path(node, pathParts);
//        Expression parsedExpression = pSystem.parseExpression(node, 10 + "");
//        Port portX = pSystem.setPortExpression(x, parsedExpression);
//
//        Assert.assertNotNull("Port should not be null", portX);
//        assertThat(parsedExpression.getPortsDependedOn().isEmpty(), is(equalTo(true)));
//        assertThat(portX.getArgumentForPosition(0).toString(), containsString("(10.0)"));
//        //TODO add test code for more complicated expressions
//        //TODO add test code for multiple args
//        //TODO add test code for previously set args
//    }

    @Test
    public void resolvePrototypePath() throws URISyntaxException {
        try {
            Runtime pSystem = setupPSystemWithSubjuncts();

            Path nodePath = new Path("Point");
            Symbol pointStringPath = pSystem.find("Point");
            Symbol pointPath = pSystem.find(nodePath);
            Assert.assertSame("should be equal", pointStringPath, pointPath);
            Assert.assertEquals("Should be a node", SymbolType.NODE, pointStringPath.getSymbolType());
            Assert.assertEquals("Should be a node", SymbolType.NODE, pointPath.getSymbolType());

            Path subjNodePath = new Path("EndPoints");
            Symbol endPointStringPath = pSystem.find("EndPoints");
            Symbol endPointPath = pSystem.find(subjNodePath);
            Assert.assertSame("should be equal", endPointStringPath, endPointPath);
            Assert.assertEquals("Should be a subjunctive node", SymbolType.NODE, endPointStringPath.getSymbolType());
            Assert.assertEquals("Should be a subjunctive node", SymbolType.NODE, endPointPath.getSymbolType());
            Node s = (Node) endPointPath;
            Assert.assertTrue("Should have options", s.hasOptions());

        } catch (IOException | PathNotFoundException | PathNotAccessibleException ex) {
            Logger.getLogger(SubjunctiveParametricSystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test(expected = PathNotFoundException.class)
    public void invalidPath() throws PathNotFoundException, IOException, PathNotAccessibleException, URISyntaxException {

        Runtime pSystem = setupPSystemWithSubjuncts();

        Path error = new Path("startP4ints");
        pSystem.find("startP4ints");
        pSystem.find(error);
    }

    @Test
    public void resolveInstancePath() throws IOException,
            PathNotAccessibleException, PathNotFoundException, URISyntaxException {
        Runtime pSystem = setupPSystemWithSubjuncts();
        Path instance = new Path("startPoint");
        Symbol startPointStringPath = pSystem.find("startPoint");
        Symbol startPointPath = pSystem.find(instance);

        Assert.assertNotNull(startPointStringPath);
        Assert.assertNotNull(startPointPath);
        Assert.assertSame("should be equal", startPointStringPath, startPointPath);
        Assert.assertEquals("Should be a node", SymbolType.NODE, startPointStringPath.getSymbolType());
        Assert.assertEquals("Should be a node", SymbolType.NODE, startPointPath.getSymbolType());

        Path subjInstance = new Path("endPoint");
        Symbol endPointsStringPath = pSystem.find("endPoint");
        Symbol endPointsPath = pSystem.find(subjInstance);

        Assert.assertNotNull(endPointsStringPath);
        Assert.assertNotNull(endPointsPath);
        Assert.assertSame("should be equal", endPointsStringPath, endPointsPath);
        Assert.assertEquals("Should be a node", SymbolType.NODE, endPointsStringPath.getSymbolType());
        Assert.assertEquals("Should be a node", SymbolType.NODE, endPointsPath.getSymbolType());
        Node s = (Node) endPointsPath;
            Assert.assertTrue("Should have options", s.hasOptions());

        Path p1 = new Path("endPoint", "P1");
        Symbol p1StringPath = pSystem.find("endPoint.P1");
        Symbol p1Path = pSystem.find(p1);

        Assert.assertNotNull(p1StringPath);
        Assert.assertNotNull(p1Path);
        Assert.assertSame("should be equal", p1StringPath, p1Path);
        Assert.assertEquals("Should be a node", SymbolType.NODE, p1StringPath.getSymbolType());
        Assert.assertEquals("Should be a node", SymbolType.NODE, p1Path.getSymbolType());

        Path p2 = new Path("endPoint", "P2");
        Symbol p2StringPath = pSystem.find("endPoint.P2");
        Symbol p2Path = pSystem.find(p2);

        Assert.assertNotNull(p2StringPath);
        Assert.assertNotNull(p2Path);
        Assert.assertSame("should be equal", p2StringPath, p2Path);
        Assert.assertEquals("Should be a node", SymbolType.NODE, p2StringPath.getSymbolType());
        Assert.assertEquals("Should be a node", SymbolType.NODE, p2Path.getSymbolType());
        Node n = (Node) p2Path;
        Assert.assertEquals("endPoint.P2", n.getFullName());

    }

    @Test(expected = PathNotAccessibleException.class)
    public void inaccessiblePort() throws IOException, PathNotAccessibleException, PathNotFoundException, URISyntaxException {
        Runtime pSystem = setupPSystemWithSubjuncts();
        pSystem.find("Point"); // need to create prototype

        Path update = new Path("Point", "update");
        Symbol updatePortPath = pSystem.find(update);
        Symbol updatePortString = pSystem.find("Point.update");
        Assert.assertSame("should be the same reference", updatePortPath, updatePortString);
        assertThat("should be a port", updatePortPath.getSymbolType(), is(equalTo(SymbolType.PORT)));
        assertThat("should be a port", updatePortString.getSymbolType(), is(equalTo(SymbolType.PORT)));
    }

    @Test
    public void resolvePorts() throws PathNotAccessibleException, IOException, PathNotFoundException, URISyntaxException {
        Runtime pSystem = setupPSystemWithSubjuncts();
        pSystem.find("Point"); // needed to create the Point prototype

        Path x = new Path("Point", "x");
        Symbol xPortPath = pSystem.find(x);
        Symbol xPortString = pSystem.find("Point.x");
        Assert.assertSame("should be the same reference", xPortPath, xPortString);
        assertThat("should be a port", xPortPath.getSymbolType(), is(equalTo(SymbolType.PORT)));
        assertThat("should be a port", xPortString.getSymbolType(), is(equalTo(SymbolType.PORT)));

        Path y = new Path("Point", "y");
        Symbol yPortPath = pSystem.find(y);
        Symbol yPortString = pSystem.find("Point.y");
        Assert.assertSame("should be the same reference", yPortPath, yPortString);
        assertThat("should be a port", yPortPath.getSymbolType(), is(equalTo(SymbolType.PORT)));
        assertThat("should be a port", yPortString.getSymbolType(), is(equalTo(SymbolType.PORT)));

        Path point = new Path("Point", "point");
        Symbol pointPortPath = pSystem.find(point);
        Symbol pointPortString = pSystem.find("Point.point");
        Assert.assertSame("should be the same reference", pointPortPath, pointPortString);
        assertThat("should be a port", pointPortPath.getSymbolType(), is(equalTo(SymbolType.PORT)));
        assertThat("should be a port", pointPortString.getSymbolType(), is(equalTo(SymbolType.PORT)));

        Path startPointX = new Path("startPoint", "x");
        Symbol startPointXPath = pSystem.find(startPointX);
        Symbol startPointXString = pSystem.find("startPoint.x");
        Assert.assertSame("should be the same reference", startPointXPath, startPointXString);
        assertThat("should be a port", startPointXPath.getSymbolType(), is(equalTo(SymbolType.PORT)));
        assertThat("should be a port", startPointXString.getSymbolType(), is(equalTo(SymbolType.PORT)));
        Port p = (Port) startPointXPath;
        Assert.assertEquals("should have the same name", "startPoint.x", p.getFullName());

        Path activeX = new Path("endPoint", "active", "x");
        Symbol activeXPath = pSystem.find(activeX);
        Symbol activeXString = pSystem.find("endPoint.active.x");
        Assert.assertSame("should be the same reference", activeXString, activeXPath);
        Assert.assertEquals("should be port", SymbolType.PORT, activeXPath.getSymbolType());
        Assert.assertEquals("should be port", SymbolType.PORT, activeXString.getSymbolType());

        Path p1X = new Path("endPoint", "P1", "x");
        Symbol p1XPath = pSystem.find(p1X);
        Symbol p1XString = pSystem.find("endPoint.P1.x");
        Assert.assertSame("should be the same reference", p1XString, p1XPath);
        Assert.assertEquals("should be port", SymbolType.PORT, p1XPath.getSymbolType());
        Assert.assertEquals("should be port", SymbolType.PORT, p1XString.getSymbolType());

        Path p2X = new Path("endPoint", "P2", "x");
        Symbol p2XPath = pSystem.find(p2X);
        Symbol p2XString = pSystem.find("endPoint.P2.x");
        Assert.assertSame("should be the same reference", p2XString, p2XPath);
        Assert.assertEquals("should be port", SymbolType.PORT, p2XPath.getSymbolType());
        Assert.assertEquals("should be port", SymbolType.PORT, p2XString.getSymbolType());
    }

    @Test
    public void findAndReplace() throws IOException, URISyntaxException {
        Runtime pSystem = setupPSystemWithSubjuncts();
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
    public void getNodesOfType(){
        Runtime ps = new Runtime();
        
        Node root = new Node("Point", "root", ps);
        ps.addNode(root);
        
        Node root1 = new Node("Other", "root1", ps);
        ps.addNode(root1);
        
        Node n1 = new Node("Point", "1", root);
        root.addNestedNode(n1);
        Node n11 = new Node("Point", "11", n1);
        n1.addNestedNode(n11);
        Node n12 = new Node("Other", "12", n1);
        n1.addNestedNode(n12);
        
        Node n2 = new Node("Point", "2", root);
        root.addNestedNode(n2);
        Node n21 = new Node("Point", "21", root);
        n2.addNestedNode(n21);
        Node n22 = new Node("Other", "22", root);
        n2.addNestedNode(n22);
        
        Assert.assertEquals(5, ps.getNodesOfType("Point").size());
        Assert.assertEquals(3, ps.getNodesOfType("Other").size());
    }
    
    @Test
    public void loadCode() throws IOException, URISyntaxException{
        Runtime ps = setupPSystemWithSubjuncts();
        
        // check to see if types are correct
        Assert.assertNotNull(ps.getNodeDef("Line"));
        Assert.assertNotNull(ps.getNodeDef("Point"));
        Assert.assertNotNull(ps.getNodeDef("EndPoints"));
        Assert.assertEquals(2, ps.getStateNames().size());
        
        Set<DependencyRelation<Port>> total = new LinkedHashSet<>();
        for(Node n: ps.getNodes()){
            total.addAll(n.getDependencies());
        }
        
        System.out.println(ps.printDependencyGraph());
        
//        Node startPoint = ps.getNode("startPoint");
//        Set<DependencyRelation<Port>> dependencies = startPoint.getDependencies();
//        
//        Set<DependencyRelation<Port>> expected = new LinkedHashSet<>();
//        
//        Port x = startPoint.getPort("x");
//        Port y = startPoint.getPort("x");
//        Port update = startPoint.getPort("update");
//        Port point = startPoint.getPort("point");
//        
//        expected.add(new DependencyRelation<>(update, x));
//        expected.add(new DependencyRelation<>(update, y));
//        expected.add(new DependencyRelation<>(point, update));
    }

//    @Test
//    public void split() throws IOException, PathNotFoundException, PathNotAccessibleException {
//        Runtime pSystem = setupPSystemWithSubjuncts();
//        
//        Map<Path, List<Expression>> newValues = new HashMap<>();
//        newValues.put(Path.createPath("x"), Arrays.asList(new shiro.expressions.Number(200d)));
//        newValues.put(Path.createPath("y"), Arrays.asList(new shiro.expressions.Number(200d)));
//        
//        Node n = (Node) pSystem.find("startPoint");
//        SubjunctiveNode subjunctiveNode = pSystem.split(n, "StartPoints", "P1", newValues);
//        pSystem.addSubjunctiveNode(subjunctiveNode);
//
//        Assert.assertNotNull(subjunctiveNode);
//        Assert.assertEquals("StartPoints", subjunctiveNode.getFullName());
//        
////        pSystem.update(pSystem.getState("state1"));
//        System.out.println(pSystem.toCode());
//    } //TODO fix the split method
    
}
