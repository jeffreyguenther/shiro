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
import static org.shirolang.base.SType.LESSTHAN;
import org.shirolang.values.SBoolean;
import org.shirolang.values.SDouble;
import org.shirolang.values.SInteger;

/**
 *
 * @author jeffreyguenther
 */
public class SLessThan extends SBinaryFunction{

    public SLessThan() {
        super();
        // setup args
        args.setKeyForIndex("a", 0);
        args.setKeyForIndex("b", 1);
        
        results.add(null);
    }
    
    public SLessThan(SFunc a, SFunc b){
        this();
        args.add(a);
        args.add(b);
    }

    @Override
    public void evaluate() {
        SFunc lhs = args.get(0).getResult();
        SFunc rhs = args.get(1).getResult();
        
        // Only allow doubles to be added     
        if(lhs.isDouble() && rhs.isDouble()){
            Double l = ((SDouble) lhs).getValue();
            Double r = ((SDouble) rhs).getValue();
            Boolean gt = l < r;
            
            SBoolean s = new SBoolean(gt);
            s.evaluate();
            
            results.set(s, 0);
        }else if(lhs.isInteger() && lhs.isInteger()){
            Integer l = ((SInteger) lhs).getValue();
            Integer r = ((SInteger) rhs).getValue();
            Boolean gt = l < r;
            
            SBoolean s = new SBoolean(gt);
            s.evaluate();
            
            results.set(s, 0);
        }else{
            // identify which argument is not a double
            throw new RuntimeException("Only Doubles and Integers can be compared.");
        }
    }

    @Override
    public String getType() {
        return LESSTHAN;
    }
}
