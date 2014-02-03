package shiro.functions;

import shiro.ResultTuple;
import shiro.Value;
import java.util.List;

/**
 * A multifunction that calculates the sum of each of its arguments.
 * It assumes each argument is a float.
 * @author jeffreyguenther
 */
public class SumMFunc implements MultiFunction{
    private static final String NAME = "Sum";
    
    public SumMFunc(){
        
    }

    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        float sum = 0;
        
        // sum the arguments
        for(Value v: arguments){
            float f = v.getValueAsFloat();
            sum += f;
        }
        
        return ResultTuple.createTuple(sum);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
