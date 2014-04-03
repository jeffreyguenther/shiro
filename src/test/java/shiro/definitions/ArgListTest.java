/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import shiro.expressions.Expression;

/**
 *
 * @author jeffreyguenther
 */
public class ArgListTest {
    @Test
    public void args(){
        List<Expression> exprs = new ArrayList<>();
        exprs.add(Expression.number(3));
        exprs.add(Expression.path("x", 0));
        exprs.add(Expression.number(6.2));
        
        STGroup template = Definition.getTemplate();
        ST list = template.getInstanceOf("argList");
        list.add("args", exprs);
        
        Assert.assertEquals("should match", "3, x[0], 6.2", list.render());
    }
}
