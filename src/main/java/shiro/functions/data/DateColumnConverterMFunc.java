package shiro.functions.data;

import java.util.List;
import java.util.Map;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 * 
 * @author jeffreyguenther
 */
public class DateColumnConverterMFunc implements MultiFunction {
    private static final String NAME = "DateColumnConverter";
    private static final int TABLE = 0;
    private static final int COLUMN = 1;
    private static final int DATE_PATTERN = 2;
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        
        
        Table<Integer, String, Comparable> table = (Table<Integer, String, Comparable>) arguments
                .get(TABLE).getValue();
        String columnToConvert = arguments.get(COLUMN).getValueAsString();
        String datePattern = arguments.get(DATE_PATTERN).getValueAsString();
        
        Map<Integer, Map<String, Comparable>> rowMap = table.rowMap();

        Table<Integer, String, Comparable> result = HashBasedTable
                .<Integer, String, Comparable> create();

        for (Integer rowKey : rowMap.keySet()) {
            Map<String, Comparable> row = rowMap.get(rowKey);
            
            String s = (String) row.get(columnToConvert);
            
            // convert the string to a date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            LocalDateTime d = LocalDateTime.parse(s, formatter);
            row.put(columnToConvert, d);
            putRowInTable(result, rowKey, row);
        }
        
        ResultTuple rt = new ResultTuple();
        rt.setValueForIndex(0, new Value(result, Table.class));
        return rt;
    }
    
    @Override
    public String getName() {
        return NAME;
    }

    private void putRowInTable(Table<Integer, String, Comparable> table,
            Integer rowKey, Map<String, Comparable> row) {
        for (String key : row.keySet()) {
            table.put(rowKey, key, row.get(key));
        }
    }
}
