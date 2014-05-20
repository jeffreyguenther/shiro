package shiro.functions;

import java.util.ArrayList;
import shiro.ResultTuple;
import shiro.Value;
import java.util.List;

/**
 *
 * @author jeffreyguenther
 */
public class ArrayMFunc implements MultiFunction {
    private static final String NAME = "Array";
    
    public ArrayMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        
        ArrayList<Object> array = new ArrayList<>();
        for(int i = 0; i < arguments.size(); i++){
            array.add(arguments.get(i).getValue());
        }
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, new Value(array, ArrayList.class));
        
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
