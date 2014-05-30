package shiro.functions.data;

import java.util.List;
import java.util.Map;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 * 
 * @author Jeffrey Guenther
 *
 */
public class SelectColumnMFunc implements MultiFunction {

    @Override
    public String getName() {
        return "SelectColumn";
    }

    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Table<Integer, String, Object> table = (Table<Integer, String, Object>) arguments
                .get(0).getValue();
        String columnSelected = arguments.get(1).getValueAsString();
        
        Table<Integer, String, Object> tableMatches = HashBasedTable
                .<Integer, String, Object> create();
        
        Map<Integer, Object> column = table.column(columnSelected);
        
        for(Integer i: column.keySet()){
            tableMatches.put(i, columnSelected, column.get(i));
        }

        ResultTuple rt = new ResultTuple();
        rt.setValueForIndex(0, new Value(tableMatches, Table.class));
        return rt;
    }

}
