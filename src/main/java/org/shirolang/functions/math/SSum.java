package org.shirolang.functions.math;

import org.shirolang.base.SFuncBase;
import org.shirolang.base.SType;
import org.shirolang.base.TypedValue;
import org.shirolang.values.SInteger;
import org.shirolang.values.SList;

/**
 * Calculates the sum of a list
 */
public class SSum extends SFuncBase {

    public SSum(){
        super();

        inputs.add(new TypedValue(SType.LIST));
    }

    @Override
    public void evaluate() {
        SList values = (SList) getInput(0).getResult();
        int sum = values.getValue().stream().mapToInt(sFunc -> ((SInteger) sFunc.getResult()).getValue()).sum();

        SInteger r = new SInteger(sum);
        r.evaluate();
        results.add(new TypedValue(SType.INTEGER, r));
    }

    @Override
    public int getMinArgs() {
        return 0;
    }

    @Override
    public int getMaxArgs() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getType() {
        return SType.SUM;
    }
}
