/*
 * The MIT License
 *
 * Copyright (c) 2012 - 2014 Jeffrey Guenther.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.shirolang.functions.math;

import org.shirolang.base.SFunc;
import org.shirolang.values.SDouble;
import org.shirolang.values.SInteger;

/**
 *  Represents the binary operator "%" (modulo)
 */
public class SModulo extends SBinaryArithmeticFunction{

    public SModulo() {
        super();
        setupBinaryArithmeticArgs();
    }
    
    public SModulo(SFunc a, SFunc b) {
        this();
        setBinaryArgs(a, b);
    }

    @Override
    public void evaluate() {
        SFunc lhs = getInput(0).getResult();
        SFunc rhs = getInput(1).getResult();
        
        // Only allow doubles to be added     
        if(lhs.isDouble() && rhs.isDouble()){
            Double l = ((SDouble) lhs).getValue();
            Double r = ((SDouble) rhs).getValue();
            Double result = l % r;
            
            SDouble s = new SDouble(result);
            s.evaluate();

            returnDouble(s);
        }else if(lhs.isInteger() && lhs.isInteger()){
            Integer l = ((SInteger) lhs).getValue();
            Integer r = ((SInteger) rhs).getValue();
            Integer result = l % r;
            
            SInteger s = new SInteger(result);
            s.evaluate();

            returnInteger(s);
        }else{
            // identify which argument is not a double
            throw new RuntimeException("Only Doubles and Integers can be moduloed.");
        }
    }

    @Override
    public String getType() {
        return "Modulo";
    }
}
