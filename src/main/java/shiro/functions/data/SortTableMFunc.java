package shiro.functions.data;

import com.google.common.collect.BiMap;
import java.util.List;
import java.util.Map;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Table;
import java.util.ArrayList;
import java.util.Collections;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 * 
 * @author jeffreyguenther
 */
@SuppressWarnings("unchecked")
public class SortTableMFunc implements MultiFunction {
    private static final String NAME = "SortTable";
    private static final int TABLE  = 0;
    private static final int COLUMN = 1;
    private static final int DIRECTION = 2;
    
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        // Get the values passed in
        Table<Integer, String, Comparable> table = (Table<Integer, String, Comparable>) arguments
                .get(TABLE).getValue();
        String columnToSortOn = arguments.get(COLUMN).getValueAsString();
        String direction = arguments.get(DIRECTION).getValueAsString();
        
        // Extract the column to sort on
        BiMap<Integer, Comparable> column = HashBiMap.create(table.column(columnToSortOn));
        // Sort the column
        List<Comparable> sortedCol = sortColumn(column, direction.equals("ASC"));
        
        // Create the table to store the sorted results in
        Table<Integer, String, Comparable> sortedTable = HashBasedTable
                .<Integer, String, Comparable> create();
        
        Map<Integer, Map<String, Comparable>> rows = table.rowMap();
        
        // Fill the new table
        for(int i = 0; i < sortedCol.size(); i++ ){
            Integer rowIndex = column.inverse().get(sortedCol.get(i));
            putRowInTable(sortedTable, i + 1, rows.get(rowIndex));
        }
        
        ResultTuple rt = new ResultTuple();
        rt.setValueForIndex(0, new Value(sortedTable, Table.class));
        
        return rt;
    }

    private void putRowInTable(Table<Integer, String, Comparable> table,
            Integer rowKey, Map<String, Comparable> row) {
        for (String key : row.keySet()) {
            table.put(rowKey, key, row.get(key));
        }
    }
    
    private List<Comparable> sortColumn( Map<Integer, Comparable> column, boolean ascending){
        List<Comparable> values = new ArrayList<>(column.values());
        
        Collections.sort(values);
        
        if(!ascending){
            Collections.reverse(values);
        }
        
        return values;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
