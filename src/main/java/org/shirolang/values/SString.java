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

/**
 *
 * Represents a string in Shiro
 */
public class SString extends SValue<String>{

    /**
     * Creates a Shiro string with the value of
     * the empty string.
     */
    public SString() {
        super("");
    }

    /**
     * Creates a Shiro string
     * @param s value of the Shiro string
     */
    public SString(String s) {
        super(s);
    }

    /**
     * Creates a Shiro string with the given name
     * @param name name of the string
     * @param s value of the Shiro string
     */
    public SString(String name, String s) {
        super(name, s);
    }

    @Override
    public String getType() {
        return SType.STRING;
    }

    @Override
    public String toConsole(){
        if(getSymbolType().isLiteral()){
            return "\""+ getValue() + "\"";
        }else{
            return super.toConsole();
        }
    }
}
