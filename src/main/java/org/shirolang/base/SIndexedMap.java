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

package org.shirolang.base;

import java.util.ArrayList;
import java.util.Collections;
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
     * Get all of the values stored in the map
     * @return the list of values stored in the map by index order
     */
    public List<T> getAll(){
        return values;
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

    public boolean isEmpty() {
        return indexToKeyMap.isEmpty();
    }
    
    /**
     * Returns the list of keys.
     * The keys will be in the position they are mapped to.
     * @return 
     */
    public List<String> getKeys(){
        List<String> keys = new ArrayList<>(Collections.nCopies(indexToKeyMap.size(), ""));
        
        for(String key: indexToKeyMap.keySet()){
            keys.set(indexToKeyMap.get(key), key);
        }
        return keys;
    }
}
