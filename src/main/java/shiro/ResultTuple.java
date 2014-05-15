package shiro;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.ArrayList;
import java.util.Set;

/**
 * Defines a tuple of results
 * It is a list with indices that have names.
 * @author jeffreyguenther
 */
public class ResultTuple{
    private ArrayList<Value> values;
    private BiMap<String, Integer> nameToIndexMap;

    public ResultTuple() {
        values = new ArrayList<>();
        nameToIndexMap = HashBiMap.create();
    }
    
    public ResultTuple(int index, Value v) {
        values = new ArrayList<>();
        nameToIndexMap = HashBiMap.create();
        setValueForIndex(index, v);
        
    }
    
    /**
     * Set the name for the given tuple index
     * @param name of the index
     * @param index to be named
     */
    public void setNameforIndex(String name, int index){
        nameToIndexMap.put(name, index);
    }
    
    /**
     * Get the name for an index
     * @param index to get the name of
     * @return the name of the index
     */
    public String getNameForIndex(int index){
        return nameToIndexMap.inverse().get(index);
    }
    
    /**
     * Get the value of a index by name
     * @param name of the index to be returned
     * @return value of the name given
     */
    public Value getValueForName(String name){
       Integer index = nameToIndexMap.get(name);
       return values.get(index);
    }
    
    /**
     * Get the value of an index by index number
     * @param index of value
     * @return the value of the index
     */
    public Value getValueForIndex(int index){
        return values.get(index);
    }
    
    /**
     * Set the value for a given index.
     * @param index index to be set
     * @param v the value to be set
     */
    public void setValueForIndex(Integer index, Value v){
        if(index > values.size() - 1){
            values.add(v);
        }else{
            values.set(index, v);
        }
    }
    
    /**
     * Set the value for the given index name
     * @param name of the index to be set
     * @param v value of the index
     */
    public void setValueForName(String name, Value v){
        Integer index = nameToIndexMap.get(name);
        if(index >= values.size()){
            values.add(v);
            nameToIndexMap.put(name, values.size() - 1);
        }else{
            values.set(index, v);
        }
        
    }
    
    /**
     * Get the names of the of the indices
     * @return set of index names
     */
    public Set<String> getNames(){
        return nameToIndexMap.keySet();
    }
    
    /**
     * Factory method for creating tuples with a single integer value
     * @param i integer to be set as the value
     * @return tuple with the value i in the first index
     */
    public static ResultTuple createTuple(Integer i){
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, Value.createInteger(i));
        return result;
    }
    
    /**
     * Factory method for creating tuples with a single float value
     * @param f float to be set as the value
     * @return tuple with the value f in the first index
     */
    public static ResultTuple createTuple(Float f){
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, Value.createFloat(f));
        return result;
    }
    
    /**
     * Factory method for creating tuples with a single String value
     * @param str string to be set as the value
     * @return tuple with the value <code>str</code> in the first index
     */
    public static ResultTuple createTuple(String str){
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, Value.createString(str));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResultTuple other = (ResultTuple) obj;
        if (this.values != other.values && (this.values == null || !this.values.equals(other.values))) {
            return false;
        }
        if (this.nameToIndexMap != other.nameToIndexMap && (this.nameToIndexMap == null || !this.nameToIndexMap.equals(other.nameToIndexMap))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.values != null ? this.values.hashCode() : 0);
        hash = 47 * hash + (this.nameToIndexMap != null ? this.nameToIndexMap.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString(){
        String output = values.toString().substring(1, values.toString().length() - 1);
        output = output.trim();
        return output;
    }
}
