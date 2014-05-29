package shiro.functions.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import shiro.ResultTuple;
import shiro.Value;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class FilterTableMFuncTest {

    @Test
    public void test_filter_mfunc() {
        
        FilterTableMFunc mfunc = new FilterTableMFunc();
        List<Value> inputList = new ArrayList<Value>();
        inputList.add(new Value(getSampleTable(), Table.class));
        inputList.add(new Value("Measure", String.class));
        inputList.add(new Value(">", String.class));
        inputList.add(new Value(10.5d, Double.class));
        ResultTuple evaluate = mfunc.evaluate(inputList);
        
        Table<Integer, String, Object> tableMatches = (Table<Integer, String, Object>) evaluate.getValueForIndex(0).getValue();
        Table<Integer, String, Object> tableNotMatches = (Table<Integer, String, Object>) evaluate.getValueForIndex(1).getValue();
        
        assertEquals("{3={Measure=11.0, Country=Z}}", tableMatches.toString());
        assertEquals("{1={Measure=10.0, Country=X}, 2={Measure=10.5, Country=Y}}", tableNotMatches.toString());
    }
    
    private Table<Integer, String, Object> getSampleTable(){
        Table<Integer, String, Object> table = HashBasedTable.<Integer, String, Object>create();
        table.put(1, "Country", "X");
        table.put(1, "Measure", 10d);
        
        table.put(2, "Country", "Y");
        table.put(2, "Measure", 10.5d);
        
        table.put(3, "Country", "Z");
        table.put(3, "Measure", 11d);
        
        return table;
    }

}
