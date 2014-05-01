/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro;

import junit.framework.Assert;
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
        SubjunctiveParametricSystem ps = new SubjunctiveParametricSystem();
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
        SubjunctiveParametricSystem ps = new SubjunctiveParametricSystem();
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
        SubjunctiveParametricSystem ps = new SubjunctiveParametricSystem();
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
}
