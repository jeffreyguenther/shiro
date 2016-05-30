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

import org.shirolang.base.SType;
import org.shirolang.base.SymbolType;

/**
 * Defines a double in Shiro
 */
public class SDouble extends SValue<Double>{

    /**
     * Creates a Shiro double with the value NAN.
     * The symboltype is also initialized to LITERAL
     */
    public SDouble(){
        this(Double.NaN);
    }

    /**
     * Creates a Shiro double
     * Makes the double a LITERAL
     * @param d
     */
    public SDouble(Double d) {
        super(d);
    }

    /**
     * Creates a Shiro double with the given name
     * @param name name of the the double
     * @param d value of the Shiro double
     */
    public SDouble(String name, Double d) {
        super(name, d);
    }
   
    @Override
    public String getType() {
        return SType.DOUBLE;
    }
}
