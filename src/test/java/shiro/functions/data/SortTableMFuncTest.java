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
import org.junit.Assert;

public class SortTableMFuncTest {

    @Test
    public void sortDoublesASC() {
        
        SortTableMFunc mfunc = new SortTableMFunc();
        List<Value> inputList = new ArrayList<>();
        inputList.add(new Value(getDoubleTable(), Table.class));
        inputList.add(new Value("Measure", String.class));
        inputList.add(new Value("ASC", String.class));
        ResultTuple evaluate = mfunc.evaluate(inputList);
        
        Table<Integer, String, Comparable> sortTable = (Table<Integer, String, 
                Comparable>) evaluate.getValueForIndex(0).getValue();
        
        assertEquals("should be ascending", getDoubleTable(), sortTable);
    }
    
    @Test
    public void sortDoublesDSC() {
        
        SortTableMFunc mfunc = new SortTableMFunc();
        List<Value> inputList = new ArrayList<>();
        inputList.add(new Value(getDoubleTable(), Table.class));
        inputList.add(new Value("Measure", String.class));
        inputList.add(new Value("DSC", String.class));
        ResultTuple evaluate = mfunc.evaluate(inputList);
        
        Table<Integer, String, Comparable> sortTable = (Table<Integer, String, 
                Comparable>) evaluate.getValueForIndex(0).getValue();
        
        Double d1 = (Double) sortTable.get(1, "Measure");
        assertTrue("should be descending", 11d == d1);
    }
    
    @Test
    public void sortStringsDSC(){
        SortTableMFunc mfunc = new SortTableMFunc();
        List<Value> inputList = new ArrayList<>();
        inputList.add(new Value(getStringTable(), Table.class));
        inputList.add(new Value("Measure", String.class));
        inputList.add(new Value("DSC", String.class));
        ResultTuple evaluate = mfunc.evaluate(inputList);
        
        Table<Integer, String, Comparable> sortTable = (Table<Integer, String, 
                Comparable>) evaluate.getValueForIndex(0).getValue();
        
        String d1 = (String) sortTable.get(1, "Measure");
        assertTrue("should be descending", "C".equals(d1));
    }
    
    @Test
    public void sortDatesASC(){
        SortTableMFunc mfunc = new SortTableMFunc();
        List<Value> inputList = new ArrayList<>();
        inputList.add(new Value(getDateTable(), Table.class));
        inputList.add(new Value("Measure", String.class));
        inputList.add(new Value("ASC", String.class));
        ResultTuple evaluate = mfunc.evaluate(inputList);
        
        Table<Integer, String, Comparable> sortTable = (Table<Integer, String, 
                Comparable>) evaluate.getValueForIndex(0).getValue();
        System.out.println(getDateTable());
        System.out.println(sortTable);
        
        assertEquals("should be ascending", getDateTable(), sortTable);
    }
    
    @Test
    public void sortDatesDSC(){
        SortTableMFunc mfunc = new SortTableMFunc();
        List<Value> inputList = new ArrayList<>();
        inputList.add(new Value(getDateTable(), Table.class));
        inputList.add(new Value("Measure", String.class));
        inputList.add(new Value("DSC", String.class));
        ResultTuple evaluate = mfunc.evaluate(inputList);
        
        Table<Integer, String, Comparable> sortTable = (Table<Integer, String, 
                Comparable>) evaluate.getValueForIndex(0).getValue();
        System.out.println(getDateTable());
        System.out.println(sortTable);
        
        LocalDateTime d1 = (LocalDateTime) sortTable.get(1, "Measure");
        LocalDateTime expected = LocalDateTime.of(2006, Month.JUNE, 1, 10, 33);
        assertTrue("should be descending", expected.isEqual(d1));
    }
    
    private Table<Integer, String, Comparable> getDoubleTable(){
        Table<Integer, String, Comparable> table = HashBasedTable.<Integer, String, Comparable>create();
        table.put(1, "Country", "X");
        table.put(1, "Measure", 10d);
        
        table.put(2, "Country", "Y");
        table.put(2, "Measure", 10.5d);
        
        table.put(3, "Country", "Z");
        table.put(3, "Measure", 11d);
        
        return table;
    }
    
    private Table<Integer, String, Comparable> getStringTable(){
        Table<Integer, String, Comparable> table = HashBasedTable.<Integer, String, Comparable>create();
        table.put(1, "Country", "X");
        table.put(1, "Measure", "A");
        
        table.put(2, "Country", "Y");
        table.put(2, "Measure", "B");
        
        table.put(3, "Country", "Z");
        table.put(3, "Measure", "C");
        
        return table;
    }
    
    private Table<Integer, String, Comparable> getDateTable(){
        Table<Integer, String, Comparable> table = HashBasedTable.<Integer, String, Comparable>create();
        table.put(1, "Country", "X");
        table.put(1, "Measure", LocalDateTime.of(2006, Month.JUNE, 1, 0, 8));
        
        table.put(2, "Country", "Y");
        table.put(2, "Measure", LocalDateTime.of(2006, Month.JUNE, 1, 1, 49));
        
        table.put(3, "Country", "Z");
        table.put(3, "Measure", LocalDateTime.of(2006, Month.JUNE, 1, 10, 33));
        
        return table;
    }

}
