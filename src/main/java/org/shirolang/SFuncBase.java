/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shirolang;

/**
 * A base class to handle the common requirements
 * of implementing multi-functions.
 * @author jeffreyguenther
 */
public abstract class SFuncBase implements SFunc {
    public static final String INTEGER = "Integer";
    public static final String DOUBLE = "Double";
    public static final String STRING = "String";
    public static final String BOOLEAN = "Boolean";
    
    protected SIndexedMap<SFunc> args;
    protected SIndexedMap<SFunc> result; 
    protected boolean isLiteral;

    public SFuncBase() {
        args = new SIndexedMap<>();
        result = new SIndexedMap<>();
        this.isLiteral = false;
    }
    
    @Override
    public SFunc get() {
        return result.get(0);
    }

    @Override
    public SFunc get(String s) {
        return result.get(s);
    }

    @Override
    public SFunc get(Integer i) {
        return result.get(i);
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
    public void setArg(SFunc v){
        args.set(v);
    }
    
    private boolean isType(String t){
        return getType().equals(t);
    }
    
    @Override
    public boolean isInteger(){
        return isType(INTEGER);
    }
    
    @Override
    public boolean isDouble(){
        return isType(DOUBLE);
    }
    
    @Override
    public boolean isString(){
        return isType(STRING);
    }
}
