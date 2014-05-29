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
public class UnionMFunc implements MultiFunction {
    private static final String NAME = "Union";
    private static final int TABLE_A = 0;
    private static final int TABLE_B = 1;
    
    public UnionMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value tableAValue = arguments.get(TABLE_A);
        Table<Integer, String, Object> tableA = (Table<Integer, String, Object>) tableAValue.getValue();
        
        Value tableBValue = arguments.get(TABLE_B);
        Table<Integer, String, Object> tableB = (Table<Integer, String, Object>) tableBValue.getValue();
        
        Table<Integer, String, Object> resultTable = HashBasedTable.create();
        // check to see if the tables 
        if(tableA.columnKeySet().equals(tableB.columnKeySet())){
            // merge the two row maps
            resultTable.putAll(tableB);
            resultTable.putAll(tableA);
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
