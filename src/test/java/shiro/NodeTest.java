/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author jeffreyguenther
 */
public class NodeTest {
    @Test
    public void defaultConstructor(){
        Node n = new Node();
        Assert.assertNull("should be null", n.getParentScope());
        Assert.assertEquals("should have no name", "", n.getName());
        Assert.assertEquals("should have no full name", "", n.getFullName());
        Assert.assertFalse("should have no options", n.hasOptions());
        Assert.assertFalse("should have no nested nodes", n.hasNestedNodes());
        Assert.assertFalse("should have no ports", n.hasPorts());
        Assert.assertNull("should not have active option", n.getActiveOption());
    }
    
    @Test
    public void threeArgConstructor(){
        Runtime ps = new Runtime();
        String type = "EndPoints";
        String name = "endPoints";
        
        Node n = new Node(type, name, ps);
        Assert.assertEquals("should be null",ps ,n.getParentScope());
        Assert.assertEquals("should have type",type ,n.getType());
        Assert.assertEquals("should have name", name, n.getName());
        Assert.assertEquals("should have full name", name, n.getFullName());
        Assert.assertFalse("should have no options", n.hasOptions());
        Assert.assertFalse("should have no nested nodes", n.hasNestedNodes());
        Assert.assertFalse("should have no ports", n.hasPorts());
        Assert.assertNull("should not have active option", n.getActiveOption());
        
        String childNodeType = "Point";
        String childNodeName = "P1";
        String expectedFullName = "endPoints.P1";
        Node childNode = new Node(childNodeType, childNodeName, n);
        Assert.assertEquals("should have name", childNodeName, childNode.getName());
        Assert.assertEquals("should have full name", expectedFullName, childNode.getFullName());
        Assert.assertEquals("should have type", childNodeType, childNode.getType());
    }
    
    @Test
    public void addNestedNode(){
        Runtime ps = new Runtime();
        String type = "EndPoints";
        String name = "endPoints";
        Node n = new Node(type, name, ps);
        
        String childNodeType = "Point";
        String childNodeName = "P1";
        String expectedFullName = "endPoints.P1";
        Node childNode = new Node(childNodeType, childNodeName, ps);
        
        n.addNestedNode(childNode);
        Assert.assertEquals("should have full name", expectedFullName, childNode.getFullName());
        Assert.assertEquals("should have name", childNodeName, childNode.getName());
        Assert.assertEquals("should have new scope", n, childNode.getParentScope());
        Assert.assertTrue("should have nested nodes", n.hasNestedNodes());
    }
    
    @Test
    public void setNameAndFullName(){
        Runtime ps = new Runtime();
        String type = "EndPoints";
        String name = "endPoints";
        Node n = new Node(type, name, ps);
        
        String childNodeType = "Point";
        String expectedChildNodeName = "P1";
        Node childNode = new Node(childNodeType, expectedChildNodeName, ps);
        Assert.assertEquals("should update name", expectedChildNodeName, childNode.getName());
        n.addNestedNode(childNode);
        
        // Add ports
        Port p1 = new Port();
        p1.setName("x");
        
        childNode.addPort(p1);
        
        childNode.setName("A1");
        Assert.assertEquals("should update full name", "endPoints.A1", childNode.getFullName());
        Assert.assertEquals("should update name", "A1", childNode.getName());
        
        Assert.assertEquals("port should be renamed", "endPoints.A1.x", p1.getFullName());
        
        n.setName("greenPoints");
        Assert.assertEquals("should update full name", "greenPoints.A1", childNode.getFullName());
        Assert.assertEquals("should update name", "A1", childNode.getName());
        Assert.assertEquals("port should be renamed", "greenPoints.A1.x", p1.getFullName());
        
        n.setFullName("dogs.cats.endPoints");
        Assert.assertEquals("should update full name", "dogs.cats.endPoints.A1", childNode.getFullName());
        Assert.assertEquals("should update name", "A1", childNode.getName());
        Assert.assertEquals("port should be renamed", "dogs.cats.endPoints.A1.x", p1.getFullName());
    }
    
    @Test
    public void options(){
        Node n = new Node();
        
        // case: option is port
        Port xPort = new Port("x", null);
        n.addOption(xPort);
        Assert.assertTrue(n.getPorts().contains(xPort));
        Assert.assertTrue(n.getOptions().contains(xPort));
        Assert.assertTrue("should have options", n.hasOptions());
        Assert.assertFalse("should not have a default", n.hasDefaultOption());
        
        // case: option is node
        Node iNode = new Node("P", "i", null);
        n.addOption(iNode);
        Assert.assertTrue(n.getNestedNodes().contains(iNode));
        Assert.assertTrue(n.getOptions().contains(iNode));
        Assert.assertFalse("should not have a default", n.hasDefaultOption());

        // case: set active option
        n.setActiveOption("x");
        Assert.assertEquals("should match", xPort, n.getActiveOption());
        n.setActiveOption("i");
        Assert.assertEquals("should match", iNode, n.getActiveOption());
        
        // case: set default option where the option exists
        n.setDefaultOption(xPort);
        Assert.assertEquals("should match", xPort, n.getDefaultOption());
        n.setDefaultOption(iNode);
        Assert.assertEquals("should match", iNode, n.getDefaultOption());
        
        // case: set default option where the option does not exist
        // add port
        Port defaultPort = new Port("a", null);
        n.setDefaultOption(defaultPort);
        Assert.assertEquals("should match", defaultPort, n.getDefaultOption());
        
        // add nested node
        Node defaultNode = new Node("T", "defaultNode", null);
        n.setDefaultOption(defaultNode);
        Assert.assertEquals("should match", defaultNode, n.getDefaultOption());
        
        // case:  activate default option
        n.setActiveOption("x");
        Assert.assertEquals("should match", xPort, n.getActiveOption());
        Assert.assertFalse(iNode.isActive());
        Assert.assertFalse(defaultNode.isActive());
        Assert.assertFalse(defaultPort.isActive());
        
        n.activateDefaultOption();
        Assert.assertEquals("should match", defaultNode, n.getDefaultOption());
    }
    
    @Test
    public void activate(){
        Node n = new Node();
        Node nested = new Node("T", "nested", n);
        Port p = new Port("port", null);
        n.addNestedNode(nested);
        n.addPort(p);
        
        n.activate();
        Assert.assertTrue(p.isActive());
        Assert.assertTrue(nested.isActive());
        Assert.assertTrue(n.isActive());
    }
    
    @Test
    public void deactivate(){
        Node n = new Node();
        Node nested = new Node("T", "nested", n);
        Port p = new Port("port", null);
        n.addNestedNode(nested);
        n.addPort(p);
        
        n.deactivate();
        Assert.assertFalse(p.isActive());
        Assert.assertFalse(nested.isActive());
        Assert.assertFalse(n.isActive());
    }
    
    @Test
    public void afterDeactivateThenActivateDefaultOptionIsActive(){
        Node n = new Node();
        Node nested = new Node("T", "nested", n);
        Port p = new Port("port", null);
        n.setDefaultOption(nested);
        n.addOption(p);
        
        n.deactivate();
        Assert.assertFalse(p.isActive());
        Assert.assertFalse(nested.isActive());
        Assert.assertFalse(n.isActive());
        n.activate();
        Assert.assertEquals(nested, n.getDefaultOption());
        Assert.assertEquals(nested, n.getActiveOption());
        Assert.assertFalse(p.isActive());
    }
}
