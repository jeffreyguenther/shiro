/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.interpreter;

import java.io.IOException;
import java.util.Map;
import junit.framework.Assert;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;
import shiro.shared.CodeLoader;

/**
 *
 * @author jeffreyguenther
 */
public class NodeDefinitionListenerTest extends CodeLoader{
    private NodeDefinitionListener defs;
    
    @Before
    public void loadCode() throws IOException{
        ShiroParser parser = parse("/shiro/SimpleSubjunctiveExample.sro");
        ParseTreeWalker walker = new ParseTreeWalker();
        defs = new NodeDefinitionListener();
        walker.walk(defs, parser.shiro());
    }
    
    @Test
    public void getNodes() {
        Map<String, ParseTree> nodeDefinitions = defs.getNodeDefinitions();
        Assert.assertEquals("should have values", 3, nodeDefinitions.size());
        Assert.assertTrue("should be present", nodeDefinitions.containsKey("Point"));
        Assert.assertTrue("should be present", nodeDefinitions.containsKey("Line"));
        Assert.assertTrue("should be present", nodeDefinitions.containsKey("EndPoints"));
        Assert.assertFalse("should not be present", nodeDefinitions.containsKey("P1"));
        Assert.assertFalse("should not be present", nodeDefinitions.containsKey("P2"));
    }
    
    @Test
    public void getGraphs(){
        Map<String, ParseTree> graphs = defs.getGraphs();
        Assert.assertEquals("should have single graph", 1, graphs.size());
        Assert.assertTrue("should have name", graphs.containsKey("line"));
    }
    
    @Test
    public void getAlternatives(){
        Map<String, ParseTree> alts = defs.getAlternativeDefinitions();
        Assert.assertEquals("should have single graph", 2, alts.size());
        Assert.assertTrue("should have name", alts.containsKey("DiagonalLine"));
        Assert.assertTrue("should have name", alts.containsKey("VerticalLine"));
        Assert.assertFalse("should not have name", alts.containsKey("line"));
    }
}
