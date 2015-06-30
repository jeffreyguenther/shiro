package org.shirolang.functions.conditionals;

import org.shirolang.base.SFuncBase;
import org.shirolang.base.SType;
import org.shirolang.base.TypedValue;
import org.shirolang.values.SBoolean;
import org.shirolang.values.SInteger;

/**
 * A multi-function that returns different values depending on the condition
 */
public class SConditionalReturn extends SFuncBase {

    public SConditionalReturn(){
        super();

        inputs.add(new TypedValue(SType.BOOLEAN));
        inputs.setKeyForIndex("condition", 0);

        inputs.add(new TypedValue(SType.INTEGER));
        inputs.setKeyForIndex("trueValue", 1);

        inputs.add(new TypedValue(SType.INTEGER));
        inputs.setKeyForIndex("falseValue", 2);
    }

    @Override
    public void evaluate() {
        SBoolean condition = (SBoolean) getInput(0).getResult();
        SInteger trueValue = (SInteger) getInput(1).getResult();
        SInteger falseValue = (SInteger) getInput(2).getResult();

        if(condition.getValue()){
            SInteger result = new SInteger(trueValue.getValue());
            result.evaluate();
            results.add(new TypedValue(SType.INTEGER, result));
        }else{
            SInteger result = new SInteger(falseValue.getValue());
            result.evaluate();
            results.add(new TypedValue(SType.INTEGER, result));
        }
    }

    @Override
    public int getMaxArgs() {
        return 3;
    }

    @Override
    public int getMinArgs() {
        return 3;
    }

    @Override
    public String getType() {
        return SType.CONDITIONAL_RETURN;
    }
}
