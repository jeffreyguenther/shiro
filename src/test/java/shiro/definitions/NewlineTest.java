/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author jeffreyguenther
 */
public class NewlineTest {
    @Test
    public void toCode(){
        Newline n = new Newline();
        Assert.assertEquals("should be newline", "\n", n.toCode());
    }
}
