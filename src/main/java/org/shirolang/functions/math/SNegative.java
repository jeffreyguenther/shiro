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
 *
 * @author jeffreyguenther
 */
public class SNegative extends SUnaryFunction{

    public SNegative() {
        super();
        // setup args
        args.setKeyForIndex("a", 0);
        
        results.add(null);
    }
    
    public SNegative(SFunc a){
        this();
        args.add(a);
    }

    @Override
    public void evaluate() {
        SFunc lhs = args.get(0).getResult();
     
        if(lhs.isInteger()){
            Integer l = ((SInteger) lhs).getValue();
            Integer product = -1 * l;
            
            SInteger s = new SInteger(product);
            s.evaluate();
            
            results.set(s, 0);
        }else if(lhs.isDouble()){
            Double d = ((SDouble) lhs).getValue();
            Double product = -1 * d;
            
            SDouble s = new SDouble(product);
            s.evaluate();
            
            results.set(s, 0);
        }else{
            // identify which argument is not a double
            throw new RuntimeException("Only Integers and Doubles can be made negative. "
                    + "Instead we found " + lhs.getType() + ".");
        }
    }

    @Override
    public String getType() {
        return "Negative";
    }
}
