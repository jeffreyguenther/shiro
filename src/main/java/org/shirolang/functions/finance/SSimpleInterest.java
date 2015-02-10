/*
 * Copyright (c) 2012-2014 Jeffrey Guenther
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software  and associated documentation files (the
 * "Software"), to deal in the Software without restriction,  including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute,  sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT  NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.shirolang.functions.finance;

import org.shirolang.base.SFunc;
import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;
import org.shirolang.values.SDouble;

/**
 * Represents a multi-function to calculate simple interest
 */
public class SSimpleInterest extends SFuncBase {
    public static final String RATE = "rate";
    public static final String PRINCIPAL = "principal";
    public static final String DURATION = "duration";
    public static final String INTEREST = "interest";
    public static final String VALUE = "value";

    public SSimpleInterest(){
        super();

        // name the function's arguments
        args.setKeyForIndex(RATE, 0);
        args.add(new TypedValue("Double"));

        args.setKeyForIndex(PRINCIPAL, 1);
        args.add(new TypedValue("Double"));

        args.setKeyForIndex(DURATION, 2);
        args.add(new TypedValue("Double"));

        // name the function's results
        results.setKeyForIndex(INTEREST, 0);
        results.add(new TypedValue("Double"));

        results.setKeyForIndex(VALUE, 1);
        results.add(new TypedValue("Double"));
    }

    @Override
    public void evaluate() {
        SFunc rate = getArg(RATE).getResult();
        SFunc principal = getArg(PRINCIPAL).getResult();
        SFunc duration = getArg(DURATION).getResult();

        Double r = ((SDouble) rate).getValue();
        Double p = ((SDouble) principal).getValue();
        Double t = ((SDouble) duration).getValue();

        Double interest = p * r * t;
        Double newValue = p + interest;

        SDouble iOut = new SDouble(interest);
        SDouble valueOut = new SDouble(newValue);

        iOut.evaluate();
        valueOut.evaluate();

        setResult(INTEREST, iOut);
        setResult(VALUE, valueOut);
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
        return "SimpleInterest";
    }
}
