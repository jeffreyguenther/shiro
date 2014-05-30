package shiro.functions.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import shiro.ResultTuple;
import shiro.Value;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.time.LocalDateTime;
import java.time.Month;

public class DateColumnConverterMFuncTest {

    @Test
    public void convertToDate() {
        
        DateColumnConverterMFunc mfunc = new DateColumnConverterMFunc();
        List<Value> inputList = new ArrayList<>();
        inputList.add(new Value(getStringTable(), Table.class));
        inputList.add(new Value("DateTime", String.class));
        inputList.add(new Value("yyyyMMdd HHmm", String.class));
        ResultTuple evaluate = mfunc.evaluate(inputList);
        
        Table<Integer, String, Object> converted = (Table<Integer, String, Object>) evaluate.getValueForIndex(0).getValue();
        System.out.println(getDateTable());
        System.out.println(converted);
        assertEquals(getDateTable(), converted);
    }
    
    private Table<Integer, String, Object> getDateTable(){
        Table<Integer, String, Object> table = HashBasedTable.<Integer, String, Object>create();
        table.put(1, "Country", "X");
        table.put(1, "DateTime", LocalDateTime.of(2006, Month.JUNE, 1, 0, 8));
        
        table.put(2, "Country", "Y");
        table.put(2, "DateTime", LocalDateTime.of(2006, Month.JUNE, 1, 1, 49));
        
        table.put(3, "Country", "Z");
        table.put(3, "DateTime", LocalDateTime.of(2006, Month.JUNE, 1, 10, 33));
        
        return table;
    }
    
    private Table<Integer, String, Object> getStringTable(){
        Table<Integer, String, Object> table = HashBasedTable.<Integer, String, Object>create();
        table.put(1, "Country", "X");
        table.put(1, "DateTime", "20060601 0008");
        
        table.put(2, "Country", "Y");
        table.put(2, "DateTime", "20060601 0149");
        
        table.put(3, "Country", "Z");
        table.put(3, "DateTime", "20060601 1033");
        
        return table;
    }
}
