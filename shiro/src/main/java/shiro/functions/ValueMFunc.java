package shiro.functions;

import shiro.ResultTuple;
import shiro.Value;
import java.util.List;

/**
 *
 * @author jeffreyguenther
 */
public class ValueMFunc implements MultiFunction {
    private static final String NAME = "Value";
    
    public ValueMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        ResultTuple passThrough = new ResultTuple();
        passThrough.setValueForIndex(0, arguments.get(0));
        
        return passThrough;
    }

    @Override
    public String getName() {
        return NAME;
    }
    
}
