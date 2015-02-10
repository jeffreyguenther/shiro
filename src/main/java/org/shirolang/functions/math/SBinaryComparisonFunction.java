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

package org.shirolang.functions.math;

import org.shirolang.base.SFunc;
import org.shirolang.base.TypedValue;
import org.shirolang.values.SBoolean;
import org.shirolang.values.SDouble;
import org.shirolang.values.SInteger;
import org.shirolang.values.SString;

import java.util.function.BiFunction;

/**
 * Implements methods common to logic methods
 */
public abstract class SBinaryComparisonFunction extends SBinaryFunction{

    public SBinaryComparisonFunction(){
        super();

    }

    protected void setupBinaryComparison(){
        // name the arguments
        args.setKeyForIndex(A, 0);
        args.add(new TypedValue("Integer", "Double"));

        args.setKeyForIndex(B, 1);
        args.add(new TypedValue("Integer", "Double"));

        results.add(new TypedValue("Boolean"));
    }

    protected void compute(SFunc lhs, SFunc rhs,
                           BiFunction<Double, Double, Boolean> dd,
                           BiFunction<Integer, Integer, Boolean> ii,
                           BiFunction<String, String, Boolean> ss){
        if(lhs.isDouble() && rhs.isDouble()){
            Double l = ((SDouble) lhs).getValue();
            Double r = ((SDouble) rhs).getValue();
            Boolean gt = dd.apply(l, r);

            SBoolean s = new SBoolean(gt);
            s.evaluate();

            setResult(0, s);
        }else if(lhs.isInteger() && lhs.isInteger()){
            Integer l = ((SInteger) lhs).getValue();
            Integer r = ((SInteger) rhs).getValue();
            Boolean gt = ii.apply(l, r);

            SBoolean s = new SBoolean(gt);
            s.evaluate();

            setResult(0, s);
        }else if(lhs.isString() && rhs.isString()){
            String l = ((SString) lhs).getValue();
            String r = ((SString) rhs).getValue();
            Boolean gt = ss.apply(l, r);

            SBoolean s = new SBoolean(gt);
            s.evaluate();

            setResult(0, s);
        }else{
            throw new RuntimeException("Only Doubles, Integers, and Strings can be compared.");
        }
    }

    protected void compute(SFunc lhs, SFunc rhs,
                           BiFunction<Double, Double, Boolean> dd,
                           BiFunction<Integer, Integer, Boolean> ii){
        if(lhs.isDouble() && rhs.isDouble()){
            Double l = ((SDouble) lhs).getValue();
            Double r = ((SDouble) rhs).getValue();
            Boolean gt = dd.apply(l, r);

            SBoolean s = new SBoolean(gt);
            s.evaluate();

            setResult(0, s);
        }else if(lhs.isInteger() && lhs.isInteger()){
            Integer l = ((SInteger) lhs).getValue();
            Integer r = ((SInteger) rhs).getValue();
            Boolean gt = ii.apply(l, r);

            SBoolean s = new SBoolean(gt);
            s.evaluate();

            setResult(0, s);
        }else{
            throw new RuntimeException("Only Doubles and Integers can be compared.");
        }
    }
}
