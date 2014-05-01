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
public class SymbolTypeTest {
    @Test
    public void isNode(){
        SymbolType node = SymbolType.NODE;
        SymbolType port = SymbolType.PORT;
        Assert.assertTrue("is node", node.isNode());
        Assert.assertFalse("is not node", port.isNode());
    }
    
    @Test
    public void isPort(){
        SymbolType node = SymbolType.NODE;
        SymbolType port = SymbolType.PORT;
        Assert.assertTrue("is port", port.isPort());
        Assert.assertFalse("is not port", node.isPort());
    }
}
