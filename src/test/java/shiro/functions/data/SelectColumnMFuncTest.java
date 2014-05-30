package shiro.functions.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import shiro.ResultTuple;
import shiro.Value;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class SelectColumnMFuncTest {

    @Test
    public void selectColumn() {
        
        SelectColumnMFunc mfunc = new SelectColumnMFunc();
        List<Value> inputList = new ArrayList<>();
        inputList.add(new Value(getSampleTable(), Table.class));
        inputList.add(new Value("Country", String.class));
        ResultTuple evaluate = mfunc.evaluate(inputList);
        
        Table<Integer, String, Object> tableMatches = (Table<Integer, String, Object>) evaluate.getValueForIndex(0).getValue();
        
        assertEquals("{1={Country=X}, 2={Country=Y}, 3={Country=Z}}", tableMatches.toString());
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
