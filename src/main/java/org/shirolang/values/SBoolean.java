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
 *
 * A multi-function to represent a boolean in Shiro
 */
public class SBoolean extends SValue<Boolean>{

    /**
     * Creates an SBoolean initialized to false
     */
    public SBoolean(){
        this(false);
    }

    /**
     * Creates an SBoolean with the passed value
     * @param b value to set the SBoolean
     */
    public SBoolean(Boolean b) {
        super(b);
    }

    /**
     * Creates an SBoolean with the passed value
     * @param name name of the boolean
     * @param b value to set the SBoolean
     */
    public SBoolean(String name, Boolean b) {
        super(name, b);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return SType.BOOLEAN;
    }

    /**
     * Creates a SBoolean with SymbolType PORT
     * @param b value to set the SBoolean
     * @return An SBoolean with it's symbolType field set to
     * SymbolType.PORT
     */
    public static SBoolean createAsPort(Boolean b){
        SBoolean bool = new SBoolean(b);
        bool.setSymbolType(SymbolType.PORT);
        return bool;
    }
}
