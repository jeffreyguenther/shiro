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
import shiro.Symbol;

/**
 *
 * @author jeffreyguenther
 */
public class StateTest {
    @Test
    public void toCode() throws IOException{
        String stateDef = Definitions.loadDef("state.sro", getClass());
        
        GraphDefinition g = new GraphDefinition("line");
        Node n = new Node("Point", "P1", null);
        Node sn = new Node("EndPoint", "endPoint", null);
        
        Map<Node, Symbol> subjuncts = new HashMap<>();
        subjuncts.put(sn, n);
        
        State state = new State(g, "DiagonalLine", "", subjuncts);
        Assert.assertEquals("should match", stateDef, state.toCode());
    }
}
