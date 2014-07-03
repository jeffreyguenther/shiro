package shiro.functions.data;

import java.util.List;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.ArrayList;

/**
 * This multifunction converts and array to a table
 * 
 * @author Jeffrey Guenther
 */
public class Array2TableMFunc implements MultiFunction {
    private static final String NAME = "Array2Table";
    private static final int ARRAY = 0;
    private static final int COLUMN_NAME = 1;
    
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        ArrayList<Object> array = (ArrayList<Object>) arguments.get(ARRAY).getValue();
        String columnName = arguments.get(COLUMN_NAME).getValueAsString();
        
        Table<Integer, String, Comparable> table = HashBasedTable
                .<Integer, String, Comparable> create();
        
        for(int i = 0; i < array.size(); i++){
            table.put(i, columnName, (Comparable) array.get(i));
        }

        return new ResultTuple(0, new Value(table, Table.class));
    }

}
