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

import java.util.Objects;
import org.shirolang.base.SFunc;
import static org.shirolang.base.SType.NOT_EQUAL;
import org.shirolang.values.SBoolean;
import org.shirolang.values.SDouble;
import org.shirolang.values.SInteger;
import org.shirolang.values.SString;

/**
 *
 * @author jeffreyguenther
 */
public class SNotEqual extends SBinaryFunction{

    public SNotEqual() {
        super();
        // setup args
        args.setKeyForIndex("a", 0);
        args.setKeyForIndex("b", 1);
        
        results.set(null);
    }
    
    public SNotEqual(SFunc a, SFunc b){
        this();
        args.set(a);
        args.set(b);
    }

    @Override
    public void evaluate() {
        SFunc lhs = args.get(0).getResult();
        SFunc rhs = args.get(1).getResult();
        
        // Only allow doubles to be added     
        if(lhs.isDouble() && rhs.isDouble()){
            Double l = ((SDouble) lhs).getValue();
            Double r = ((SDouble) rhs).getValue();
            Boolean gt = !l.equals(r);
            
            SBoolean s = new SBoolean(gt);
            s.evaluate();
            
            results.set(s, 0);
        }else if(lhs.isInteger() && lhs.isInteger()){
            Integer l = ((SInteger) lhs).getValue();
            Integer r = ((SInteger) rhs).getValue();
            Boolean gt = !Objects.equals(l, r);
            
            SBoolean s = new SBoolean(gt);
            s.evaluate();
            
            results.set(s, 0);
        }else if(lhs.isString() && rhs.isString()){
            String l = ((SString) lhs).getValue();
            String r = ((SString) rhs).getValue();
            Boolean gt = !l.equals(r);
            
            SBoolean s = new SBoolean(gt);
            s.evaluate();
            
            results.set(s, 0);
        }else{
            // identify which argument is not a double
            throw new RuntimeException("Only Doubles, Integers, and Strings can be compared.");
        }
    }

    @Override
    public String getType() {
        return NOT_EQUAL;
    }

    @Override
    public String toString() {
        return "!=";
    }
}
