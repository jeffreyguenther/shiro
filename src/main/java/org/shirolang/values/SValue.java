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

import org.shirolang.base.SFunc;
import org.shirolang.base.SFuncBase;
import org.shirolang.base.SymbolType;
import org.shirolang.base.TypedValue;

/**
 * A base class to  help speed writing functional representations of values.
 * This base class is used to represent both constants and variables.
 * When acting as a constant such as literal string "hello" or a double 14.322,
 * these functions simply pass through the value. To add support for 
 * additional types to Shiro, extend this class and register it as a type with
 * runtime.
 * @param <T>
 */
public abstract class SValue<T> extends SFuncBase{
    private final T v;
    
    public SValue(T f) {
        super();
        this.v = f;
        symbolType = SymbolType.LITERAL;
        args.add(new TypedValue(getType()));
    }

    public SValue(String name, T v){
        this(v);
        symbolType = SymbolType.PORT;
        setFullName(name);
    }

    @Override
    public void evaluate() {
        if (getSymbolType().isLiteral()
                || (getSymbolType().isPort() && !hasArgs())) {
            if (results.isEmpty()) {
                TypedValue v = new TypedValue(this.getType(), this);

                results.add(v);
            } else {
                TypedValue v = results.get(0);
                v.setAcceptedTypes(this.getType());
                v.setValue(this);

                results.set(v, 0);
            }

        } else {
            SFunc arg = getArg(0);
            SFunc result = arg.getResult();
            if (result.getType().equals(this.getType())) {
                if (results.isEmpty()) {
                    TypedValue v = new TypedValue(result.getType(), result);

                    results.add(v);
                } else {
                    TypedValue v = results.get(0);
                    v.setAcceptedTypes(result.getType());
                    v.setValue(result);

                    results.set(v, 0);
                }
            } else {
                throw new RuntimeException("Types don't match in " + toString() + ". The type of the result 0 of arg 0 is " + result.getType() + ". "
                        + getType() + " was expected.");
            }
        }
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
    public int getMaxArgs() {
        if(getSymbolType().isLiteral()){
            return 0;
        }else if(getSymbolType().isPort()){
            return 1;
        }

        return 0;
    }

    @Override
    public int getMinArgs() {
        if(getSymbolType().isLiteral()){
            return 0;
        }else if (getSymbolType().isPort()){
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {
       return getFullName() + ":" + v ;
    }

    @Override
    public String toConsole(){
        if(getSymbolType().isLiteral()){
            return "" + getValue();
        }else{
            return super.toConsole();
        }
    }
}
