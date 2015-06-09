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
 * A list that can have keys assigned to an index, so values can
 * be looked up by key
 * @param <T>
 */
public class SIndexedMap<T> {
    private Map<String, Integer> keyToIndexMap;
    private Map<Integer, String> indexToKeyMap;
    List<T> values;

    public SIndexedMap() {
        indexToKeyMap = new HashMap<>();
        keyToIndexMap = new HashMap<>();
        values = new ArrayList<>();
    }

    /**
     * Sets the key for an index
     * @param key key to assign to the passed index
     * @param index index to which key is assigned
     */
    public void setKeyForIndex(String key, Integer index){
        keyToIndexMap.put(key, index);
        indexToKeyMap.put(index, key);
    }

    /**
     * Gets the key for an index
     * @param i index to retrieve
     * @return the key for the index. It returns null if no
     * key has been associated with the index
     */
    public String getKey(Integer i){
        return indexToKeyMap.get(i);
    }

    /**
     * Gets the key for the index
     * @param key the key to return.
     * @return It returns null if no index has been associated with the key
     */
    public Integer getIndex(String key){
        return keyToIndexMap.get(key);
    }

    /**
     * Gets the value at the given index
     * @param key key of the index being requested
     * @return value at the index associated with the key
     */
    public T get(String key){
        return values.get(keyToIndexMap.get(key));
    }

    /**
     * Gets the value at the given index
     * @param index index of the value to retrieve
     * @return value at the given index
     */
    public T get(Integer index){
        return values.get(index);
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
    public void add(T v){
        values.add(v);
    }

    /**
     * Sets the value at the given index
     * Behaves just like a list as the method delegates
     * to the list add method
     * @param v value to store
     * @param i index to store the value
     */
    public void set(T v, Integer i){
        values.set(i, v);
    }

    /**
     * Sets the value at the index associated with key
     * @param v value to store
     * @param s key associated with the index
     */
    public void set(T v, String s){
        values.set(keyToIndexMap.get(s), v);
    }

    /**
     * Gets the number of elements stored
     * @return the number of elements stored
     */
    public int size() {
        return values.size();
    }

    /**
     * Determines if the indexed map is empty
     * @return
     */
    public boolean isEmpty() {
        return values.isEmpty();
    }


    public void clear(){
        values.clear();
    }
    
    /**
     * Returns the list of keys.
     * The keys will be in the position they are mapped to.
     * @return 
     */
    public List<String> getKeys(){
        List<String> keys = new ArrayList<>(Collections.nCopies(indexToKeyMap.size(), ""));
        
        for(String key: keyToIndexMap.keySet()){
            keys.set(keyToIndexMap.get(key), key);
        }
        return keys;
    }
}
