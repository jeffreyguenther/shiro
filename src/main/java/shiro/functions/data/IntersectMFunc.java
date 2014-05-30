package shiro.functions.data;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import shiro.functions.*;
import shiro.ResultTuple;
import shiro.Value;
import java.util.List;
import java.util.Map;

/**
 * Calculates the intersection of two tables.
 * @author jeffreyguenther
 */
public class IntersectMFunc implements MultiFunction {
    private static final String NAME = "Intersect";
    private static final int TABLE_A = 0;
    private static final int TABLE_B = 1;
    
    public IntersectMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value tableAValue = arguments.get(TABLE_A);
        Table<Integer, String, Comparable> tableA = (Table<Integer, String, Comparable>) tableAValue.getValue();
        
        Value tableBValue = arguments.get(TABLE_B);
        Table<Integer, String, Comparable> tableB = (Table<Integer, String, Comparable>) tableBValue.getValue();
        
        Table<Integer, String, Comparable> resultTable = HashBasedTable.create();
        // check to see if the tables 
        if(tableA.columnKeySet().equals(tableB.columnKeySet())){
            // for each row in table A, check to see if there is a corresponding 
            // row in table B. If there is, add the row to the result
            MapDifference<Integer, Map<String, Comparable>> difference = Maps.difference(tableA.rowMap(), tableB.rowMap());
            Map<Integer, Map<String, Comparable>> entriesInCommon = difference.entriesInCommon();
            
            
            for(Integer rowKey: entriesInCommon.keySet()){
                Map<String, Comparable> rowValues = entriesInCommon.get(rowKey);
                for(String colKey: rowValues.keySet()){
                    resultTable.put(rowKey, colKey, rowValues.get(colKey));
                }
            }
        }
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, new Value(resultTable, Table.class));
        
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }
    
}
