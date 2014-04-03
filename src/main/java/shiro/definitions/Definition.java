/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import shiro.expressions.Expression;

/**
 *
 * @author jeffreyguenther
 */
public interface Definition {
    public String toCode();
    static STGroup getTemplate(){
        STGroupFile templates = new STGroupFile("shiro/definitions/shiro.stg");
        templates.registerModelAdaptor(Expression.class, new ExpressionAdapter());
        return templates;
    }
}
