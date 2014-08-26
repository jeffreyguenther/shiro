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

package org.shirolang;

import java.util.List;

/**
 * A base class to handle the common requirements
 * of implementing multi-functions.
 * @author jeffreyguenther
 */
public abstract class SFuncBase implements SFunc {
    protected SIndexedMap<SFunc> args;
    protected SIndexedMap<SFunc> results; 
    protected boolean isLiteral;

    public SFuncBase() {
        args = new SIndexedMap<>();
        results = new SIndexedMap<>();
        this.isLiteral = false;
    }
    
    @Override
    public SFunc getArg() {
        return args.get(0);
    }

    @Override
    public SFunc getArg(String s) {
        return args.get(s);
    }

    @Override
    public SFunc getArg(Integer i) {
        return args.get(i);
    }

    @Override
    public List<SFunc> getArgs() {
        return args.getAll();
    }

    @Override
    public List<String> getArgKeys() {
        return args.getKeys();
    }

    @Override
    public boolean hasArgs() {
        return !args.isEmpty();
    }

    @Override
    public SFunc getResult() {
        return results.get(0);
    }

    @Override
    public SFunc getResult(Integer i) {
        return results.get(i);
    }

    @Override
    public SFunc getResult(String s) {
        return results.get(s);
    }

    @Override
    public List<SFunc> getResults() {
        return results.getAll();
    }

    @Override
    public List<String> getResultKeys() {
        return results.getKeys();
    }
    
    @Override
    public boolean isLiteral(){
        return isLiteral;
    }
    
    @Override
    public void setArg(String s, SFunc v){
        args.set(v, s);
    }
    
    @Override
    public void setArg(Integer i, SFunc v){
        args.set(v, i);
    }
    
    @Override
    public void appendArg(SFunc v){
        args.set(v);
    }
    
    private boolean isType(String t){
        return getType().equals(t);
    }
    
    @Override
    public boolean isInteger(){
        return isType(SType.INTEGER);
    }
    
    @Override
    public boolean isDouble(){
        return isType(SType.DOUBLE);
    }
    
    @Override
    public boolean isString(){
        return isType(SType.STRING);
    }
    
    @Override
    public boolean isBoolean(){
        return isType(SType.BOOLEAN);
    }
}
