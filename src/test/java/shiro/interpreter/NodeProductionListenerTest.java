/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.interpreter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;
import shiro.Node;
import shiro.Port;
import shiro.ShiroRuntime;
import shiro.definitions.PortType;
import shiro.shared.CodeLoader;

/**
 *
 * @author jeffreyguenther
 */
public class NodeProductionListenerTest extends CodeLoader{
    @Test
    public void getCreatedNode() throws IOException{
//        ShiroParser parser = parse("/shiro/SimpleSubjunctiveExample.sro");
//        
//        ParseTreeWalker walker = new ParseTreeWalker();
//        NodeDefinitionListener defs = new NodeDefinitionListener();
//        walker.walk(defs, parser.shiro());
//        
//        Map<String, ParseTree> nodeDefinitions = defs.getNodeDefinitions();
//        ShiroRuntime ps = new ShiroRuntime();
//        ps.addNodeASTDefinitions(nodeDefinitions);
//        
//        NodeProductionListener nodeProducer = new NodeProductionListener(ps);
//        walker.walk(nodeProducer, nodeDefinitions.get("Point"));
//        
//        Node createdNode = nodeProducer.getCreatedNode();
//        // add tests
//        Assert.assertEquals("Point", createdNode.getName());
//        Assert.assertEquals("Point", createdNode.getFullName());
//        Assert.assertEquals("Point", createdNode.getType());
//        Assert.assertEquals(4, createdNode.getPorts().size());
//        
//        Map<PortType, List<Port>> portTypes = createdNode.getPorts().stream()
//                .collect(Collectors.groupingBy(Port::getPortType));
//        
//        List<Port> evals = portTypes.get(PortType.Evaluated);
//        Assert.assertEquals("should have one eval port", 1, evals.size());
//        
//        Port eval = evals.get(0);
//        Assert.assertEquals(2, eval.getArguments().size());
//        
//        
//        List<Port> inputs = portTypes.get(PortType.Input);
//        Assert.assertEquals("should have one input port", 2, inputs.size());
//        List<Port> outputs = portTypes.get(PortType.Output);
//        Assert.assertEquals("should have one ouput port", 1, outputs.size());
//        
//        walker.walk(nodeProducer, nodeDefinitions.get("EndPoints"));
//        Node withOptions = nodeProducer.getCreatedNode();
//        
//        Assert.assertEquals("EndPoints", withOptions.getName());
//        Assert.assertEquals("EndPoints", withOptions.getFullName());
//        Assert.assertEquals("EndPoints", withOptions.getType());
//        Assert.assertEquals(0, withOptions.getPorts().size());
//        Assert.assertEquals("should have two nested nodes", 2, withOptions.getNestedNodes().size());
//        Map<String, Node> nestedNodes = new HashMap<>();
//        withOptions.getNestedNodes().stream().forEach((n) -> nestedNodes.put(n.getFullName(), n));
//        Assert.assertTrue(nestedNodes.containsKey(("EndPoints.P1")));
//        Assert.assertTrue(nestedNodes.containsKey(("EndPoints.P2")));
//        Assert.assertFalse(nestedNodes.containsKey(("P1")));
    }
}
