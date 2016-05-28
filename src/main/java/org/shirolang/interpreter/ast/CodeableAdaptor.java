package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.Interpreter;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.misc.ObjectModelAdaptor;
import org.stringtemplate.v4.misc.STNoSuchPropertyException;

/**
 * StringTemplate model adaptor to allow access to toCode() method defined in Codeable
 */
public class CodeableAdaptor extends ObjectModelAdaptor {
    @Override
    public synchronized Object getProperty(Interpreter interp, ST self, Object o, Object property, String propertyName) throws STNoSuchPropertyException {

        if(propertyName.equals("code")) return ((Codeable)o).toCode();

        return super.getProperty(interp, self, o, property, propertyName);
    }
}
