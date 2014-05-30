package shiro.functions.data;

import java.util.List;
import java.util.Map;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 * This multifunction takes a table, a column name, a conditional operator and
 * an operand to give two outputs. The first output port is the table that
 * matches the predicate and the second output is the rest of the table.
 * 
 * @author Ankit Gupta
 *
 */
public class FilterTableMFunc implements MultiFunction {
    private boolean stringMode = false;
    
    @Override
    public String getName() {
        return "FilterTable";
    }

    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        
        
        Table<Integer, String, Object> table = (Table<Integer, String, Object>) arguments
                .get(0).getValue();
        String columnToFilterOn = arguments.get(1).getValueAsString();
        String operator = arguments.get(2).getValueAsString();
        
        Value toCompare = arguments.get(3);
        
        if( toCompare.getType().equals(String.class)){
            stringMode = true;
        }else if ( toCompare.getType().equals(Double.class)){
            stringMode = false;
        }
        
        Map<Integer, Map<String, Object>> rowMap = table.rowMap();

        Table<Integer, String, Object> tableMatches = HashBasedTable
                .<Integer, String, Object> create();
        Table<Integer, String, Object> tableNotMatches = HashBasedTable
                .<Integer, String, Object> create();

        for (Integer rowKey : rowMap.keySet()) {
            Map<String, Object> row = rowMap.get(rowKey);
            
            
            if (!stringMode) {
                
                Double valueToCompare = toCompare.getValueAsDouble();
                Double valuetoCompareWith;
                
                Object get = row.get(columnToFilterOn);
                if(get instanceof Double){
                    valuetoCompareWith = (Double) get;
                }else{
                    valuetoCompareWith = Double.parseDouble((String)get);
                }
                
                if (doubleCompare(operator, valuetoCompareWith,
                        valueToCompare)) {
                    putRowInTable(tableMatches, rowKey, row);
                } else {
                    putRowInTable(tableNotMatches, rowKey, row);
                }
            }else{
                System.out.println(row);
                System.out.println(toCompare);
                
                String valueToCompare = toCompare.getValueAsString();
                if (stringCompare(operator, (String) row.get(columnToFilterOn), valueToCompare)) {
                    putRowInTable(tableMatches, rowKey, row);
                } else {
                    putRowInTable(tableNotMatches, rowKey, row);
                }
            }
        }
        
        ResultTuple rt = new ResultTuple();
        rt.setValueForIndex(0, new Value(tableMatches, Table.class));
        rt.setValueForIndex(1, new Value(tableNotMatches, Table.class));
        return rt;
    }

    private void putRowInTable(Table<Integer, String, Object> table,
            Integer rowKey, Map<String, Object> row) {
        for (String key : row.keySet()) {
            table.put(rowKey, key, row.get(key));
        }
    }

    private boolean doubleCompare(String operator, Double valueToCompare,
            Double valueToCompareWith) {
        switch (operator) {
        case "==":
            return valueToCompare == valueToCompareWith;
        case ">":
            return valueToCompare > valueToCompareWith;
        case "<":
            return valueToCompare < valueToCompareWith;
        case ">=":
            return valueToCompare >= valueToCompareWith;
        case "<=":
            return valueToCompare <= valueToCompareWith;
        }
        return false;
    }
    
    private boolean stringCompare(String operator, String valueToCompare, String valueToCompareWith){
        switch(operator){
            case "==":
                return 0 == valueToCompare.compareTo(valueToCompareWith);
            case ">":
                return valueToCompare.compareTo(valueToCompareWith) > 0;
            case "<":
                return valueToCompare.compareTo(valueToCompareWith) < 0;
        }
        
        return false;
    }

}
