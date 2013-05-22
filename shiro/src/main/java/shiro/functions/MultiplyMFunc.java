package shiro.functions;

import shiro.ResultTuple;
import shiro.Value;
import java.util.List;

/**
 * A port for multiplying the values of each of a ports dependents
 * @author jeffreyguenther
 */
public class MultiplyMFunc implements MultiFunction{
    private static final String NAME = "Multiply";
    
    public MultiplyMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        float product = 1;
        for(Value v: arguments){
            product *= v.getValueAsFloat();
        }
        ResultTuple result = ResultTuple.createTuple(product);
        result.setNameforIndex("value", 0);
        
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }
    
}
