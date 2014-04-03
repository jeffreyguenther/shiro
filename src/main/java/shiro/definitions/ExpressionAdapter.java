/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import org.stringtemplate.v4.Interpreter;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.misc.ObjectModelAdaptor;
import org.stringtemplate.v4.misc.STNoSuchPropertyException;
import shiro.expressions.Expression;

/**
 *
 * @author jeffreyguenther
 */
public class ExpressionAdapter extends ObjectModelAdaptor{

    @Override
    public synchronized Object getProperty(Interpreter interp, ST self, Object o, Object property, String propertyName) throws STNoSuchPropertyException {
        // respond to toCode()
        if(propertyName.equals("code")) return ((Expression) o).toCode();
        return super.getProperty(interp, self, o, property, propertyName); //To change body of generated methods, choose Tools | Templates.
    }
}
