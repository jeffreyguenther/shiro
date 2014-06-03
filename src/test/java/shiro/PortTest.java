/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import shiro.definitions.PortType;

/**
 *
 * @author jeffreyguenther
 */
public class PortTest {
    @Test
    public void getType(){
        Port input = new Port();
        input.setPortType(PortType.Input);
        assertEquals(PortType.Input, input.getPortType());
        assertTrue(input.isInput());
        assertFalse(input.isEval());
        assertFalse(input.isOutput());
        
        Port eval = new Port();
        eval.setPortType(PortType.Evaluated);
        assertEquals(PortType.Evaluated, eval.getPortType());
    }
}
