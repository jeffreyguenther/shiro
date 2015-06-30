package org.shirolang.functions.conditionals;

import org.junit.Assert;
import org.junit.Test;
import org.shirolang.base.SFunc;
import org.shirolang.values.SBoolean;
import org.shirolang.values.SInteger;

import static org.junit.Assert.*;

/**
 * Tests a conditional return
 */
public class SConditionalReturnTest {

    @Test
    public void testEvaluateFalse() throws Exception {
        SBoolean condition = new SBoolean(false);
        SInteger falseValue = new SInteger(100);
        SInteger trueValue = new SInteger(1);

        SConditionalReturn ret = new SConditionalReturn();
        ret.setInput(0, condition);
        ret.setInput(1, trueValue);
        ret.setInput(2, falseValue);

        condition.evaluate();
        falseValue.evaluate();
        trueValue.evaluate();
        ret.evaluate();

        SInteger result = (SInteger) ret.getResult();
        Assert.assertEquals(100, result.getValue().intValue());


    }

    @Test
    public void testEvaluateTrue() throws Exception {
        SBoolean condition = new SBoolean(true);
        SInteger falseValue = new SInteger(100);
        SInteger trueValue = new SInteger(1);

        SConditionalReturn ret = new SConditionalReturn();
        ret.setInput(0, condition);
        ret.setInput(1, trueValue);
        ret.setInput(2, falseValue);

        condition.evaluate();
        falseValue.evaluate();
        trueValue.evaluate();
        ret.evaluate();

        SInteger result = (SInteger) ret.getResult();
        Assert.assertEquals(1, result.getValue().intValue());
    }
}