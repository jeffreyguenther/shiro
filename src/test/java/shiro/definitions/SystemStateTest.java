/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import org.junit.Test;
import shiro.Node;
import shiro.SubjunctiveNode;

/**
 *
 * @author jeffreyguenther
 */
public class SystemStateTest {
    @Test
    public void toCode() throws IOException{
        String stateDef = Definitions.loadDef("state.sro", getClass());
        
        GraphDefinition g = new GraphDefinition("line");
        Node n = new Node("Point", "P1", null);
        SubjunctiveNode sn = new SubjunctiveNode("endPoint", null);
        
        Map<SubjunctiveNode, Node> subjuncts = new HashMap<>();
        subjuncts.put(sn, n);
        
        SystemState state = new SystemState(g, "DiagonalLine", "", subjuncts);
        Assert.assertEquals("should match", stateDef, state.toCode());
    }
}
