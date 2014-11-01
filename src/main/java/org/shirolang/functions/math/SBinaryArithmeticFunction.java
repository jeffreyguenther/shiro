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
import org.shirolang.values.SDouble;
import org.shirolang.values.SInteger;

import java.util.function.BiFunction;

/**
 * Implements common methods used by binary arithmetic operators
 */
public abstract class SBinaryArithmeticFunction extends SBinaryFunction {

    protected void returnDouble(SDouble d){
        results.add(new TypedValue("Double"));
        setResult(0, d);
    }

    protected void returnInteger(SInteger i){
        results.add(new TypedValue("Integer"));
        setResult(0, i);
    }

    protected void setupBinaryArithmeticArgs(){
        // name the args
        args.setKeyForIndex(A, 0);
        args.add(new TypedValue("Integer", "Double"));

        args.setKeyForIndex(B, 1);
        args.add(new TypedValue("Integer", "Double"));
    }

    protected void computeBinaryArithmetic(SFunc lhs, SFunc rhs,
                                           String nameOfOperation,
                                           BiFunction<Double, Double, Double> dd,
                                           BiFunction<Integer, Integer, Integer> ii,
                                           BiFunction<Double, Integer, Double> di,
                                           BiFunction<Integer, Double, Double> id){
        if(lhs.isDouble() && rhs.isDouble()){
            Double l = ((SDouble) lhs).getValue();
            Double r = ((SDouble) rhs).getValue();
            Double sum = dd.apply(l, r);

            SDouble s = new SDouble(sum);
            s.evaluate();

            returnDouble(s);
        }else if(lhs.isInteger() && rhs.isInteger()){
            Integer l = ((SInteger) lhs).getValue();
            Integer r = ((SInteger) rhs).getValue();
            Integer sum = ii.apply(l, r);

            SInteger s = new SInteger(sum);
            s.evaluate();

            returnInteger(s);
        }else if (lhs.isDouble() && rhs.isInteger()){
            Double l = ((SDouble) lhs).getValue();
            Integer r = ((SInteger) rhs).getValue();
            Double sum = di.apply(l, r);

            SDouble s = new SDouble(sum);
            s.evaluate();

            returnDouble(s);
        }else if (lhs.isInteger() && rhs.isDouble()){
            Integer l = ((SInteger) lhs).getValue();
            Double r = ((SDouble) rhs).getValue();
            Double sum = id.apply(l, r);

            SDouble s = new SDouble(sum);
            s.evaluate();

            returnDouble(s);
        }else{
            throw new RuntimeException("Only Doubles and Integers can be " +  nameOfOperation + ". "
                    + lhs.getType() + " and " + rhs.getType() + " were found.");
        }
    }
}
