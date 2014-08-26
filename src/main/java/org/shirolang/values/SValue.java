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

package org.shirolang.values;

import org.shirolang.SFuncBase;

/**
 * A base class to  help speed writing functional representations of values.
 * This base class is used to represent both constants and variables.
 * When acting as a constant such as literal string "hello" or a double 14.322,
 * these functions simply pass through the value. To add support for 
 * additional types to Shiro, extend this class and register it as a type with
 * runtime.
 * @author jeffreyguenther
 * @param <T>
 */
public abstract class SValue<T> extends SFuncBase{
    private final T v;
    
    public SValue(T f) {
        super();
        this.v = f;
    }

    @Override
    public void evaluate() {
       results.set(this);
    }
    
    public T getValue(){
        if(results.size() < 1){
            throw new RuntimeException("Cannot return value. "
                    + "Multifunction has not been evaluated. "
                    + "Call evaluate() before getting value.");
        }
        
        return v;
    }

    @Override
    public String toString() {
       return "" + v;
    }

    @Override
    public int getMaxArgs() {
        return 0;
    }

    @Override
    public int getMinArgs() {
        return 0;
    }
}
