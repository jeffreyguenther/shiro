/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shirolang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jeffreyguenther
 * @param <T>
 */
public class SIndexedMap<T> {
    Map<String, Integer> indexToKeyMap;
    List<T> values;

    public SIndexedMap() {
        indexToKeyMap = new HashMap<>();
        values = new ArrayList<>();
    }
    
    public void setKeyForIndex(String s, Integer i){
        indexToKeyMap.put(s, i);
    }
    
    public T get(String s){
        return values.get(indexToKeyMap.get(s));
    }
    
    public T get(Integer i){
        return values.get(i);
    }
    
    /**
     * Adds the value to the arg list.
     * Calls List.add
     * @param v 
     */
    public void set(T v){
        values.add(v);
    }
    
    public void set(T v, Integer i){
        values.set(i, v);
    }
    
    public void set(T v, String s){
        values.set(indexToKeyMap.get(s), v);
    }

    public int size() {
        return values.size();
    }
}
