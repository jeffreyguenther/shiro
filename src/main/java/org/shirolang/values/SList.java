/*
 * The MIT License
 *
 * Copyright (c) 2012 - 2015 Jeffrey Guenther.
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

import java.util.ArrayList;
import java.util.List;
import org.shirolang.base.SFunc;
import org.shirolang.base.SType;
import org.shirolang.base.TypedValue;

/**
 * Defines a list
 */
public class SList extends SValue<List<SFunc>>{
    
    public SList(){
        super(new ArrayList<>());
        inputs.clear();
    }

    public SList(List<SFunc> l){
        super(l);
    }

    public SList(String name, List<SFunc> l){
        super(name, l);
    }

    @Override
    public void setInput(Integer i, SFunc v) {
        setArgItem(i, v);
        setListItem(i, v);
    }

    @Override
    public void setInput(String name, SFunc v) {
        Integer i = inputs.getIndex(name);
        setArgItem(i, v);
        setListItem(i, v);
    }

    /**
     * Sets the multi-function arg
     * @param i index of the arg
     * @param v value of the arg
     */
    private void setArgItem(Integer i, SFunc v){
        if(i > inputs.size() - 1){
            TypedValue typedValue = new TypedValue(v.getType());
            typedValue.setValue(v);
            inputs.add(typedValue);
        }else{
            inputs.get(i).setValue(v);
        }
    }

    /**
     * Sets the list arg
     * @param i index of the list element
     * @param v value to store in the list
     */
    private void setListItem(Integer i, SFunc v){
        if(i > super.v.size() - 1) {
            super.v.add(v);
        }else{
            super.v.set(i, v);
        }
    }

    @Override
    public void appendInput(SFunc arg) {
        super.appendInput(arg);
        v.add(arg);
    }

    @Override
    public String getType() {
        return SType.LIST;
    }

    @Override
    public int getMinArgs() {
        return 0;
    }

    @Override
    public int getMaxArgs() {
        return Integer.MAX_VALUE;
    }
}
