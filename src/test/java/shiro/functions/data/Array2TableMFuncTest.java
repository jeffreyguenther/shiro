/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.data;

import com.google.common.collect.Table;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import shiro.ResultTuple;
import shiro.Value;

/**
 *
 * @author jeffreyguenther
 */
public class Array2TableMFuncTest {
    @Test
    public void convert(){
        ArrayList<String> values = new ArrayList<>();
        values.add("A");
        values.add("B");
        values.add("C");
        String columnName = "test";
        
        Value arrayValue = new Value(values, ArrayList.class);
        Value name = new Value(columnName, String.class);
        
        List<Value> arguments = new ArrayList<>();
        arguments.add(arrayValue);
        arguments.add(name);
        
        Array2TableMFunc array2table = new Array2TableMFunc();
        ResultTuple result = array2table.evaluate(arguments);
        
        Table<Integer, String, Comparable> table = (Table<Integer, String, Comparable>) result.getValueForIndex(0).getValue();
        assertEquals("A", table.get(0, columnName));
        assertEquals("B", table.get(1, columnName));
        assertEquals("C", table.get(2, columnName));
    }
}
