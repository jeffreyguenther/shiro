package org.shirolang.functions.data;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.shirolang.base.SFunc;
import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;
import org.shirolang.values.SDouble;
import org.shirolang.values.SString;

import java.util.Map;

/**
 * This multifunction takes a table, a column name, a conditional operator and
 * an operand to give two outputs. The first output port is the table that
 * matches the predicate and the second output is the rest of the table.
 * 
 * @author Ankit Gupta, Jeffrey Guenther
 *
 */
public class FilterTableMFunc extends SFuncBase{
    // inputs
    private static final String TYPE = "FilterTable";
    private static final String TABLE = "table";
    private static final String COLUMN = "column";
    private static final String OPERATOR = "operator";
    private static final String VALUE = "value";

    // outputs
    private static final String MATCH = "match";
    private static final String DID_NOT_MATCH = "didNotMatch";

    // internal fields
    private boolean stringMode = false;

    public FilterTableMFunc(){
        super();

        inputs.setKeyForIndex(TABLE, 0);
        inputs.add(new TypedValue("Table"));

        inputs.setKeyForIndex(COLUMN, 1);
        inputs.add(new TypedValue("String"));

        inputs.setKeyForIndex(OPERATOR, 2);
        inputs.add(new TypedValue("String"));

        inputs.setKeyForIndex(VALUE, 3);
        inputs.add(new TypedValue("Double"));

        results.setKeyForIndex(MATCH, 0);
        results.add(new TypedValue("Table"));

        results.setKeyForIndex(DID_NOT_MATCH, 1);
        results.add(new TypedValue("Table"));
    }

    @Override
    public void evaluate() {
        SFunc table = getInput(TABLE).getResult();
        SFunc column = getInput(COLUMN).getResult();
        SFunc op = getInput(OPERATOR).getResult();
        SFunc value = getInput(VALUE).getResult();

        Table<Integer, String, Comparable> tableValue = ((STable) table).getValue();
        String columnToFilterOn = ((SString) column).getValue();
        String operator = ((SString)op ).getValue();
        Double val = 0.0;
        String valString = "";


        if( value.getType().equals("String")){
            stringMode = true;
            valString = ((SString) value).getValue();
        }else if ( value.getType().equals("Double")){
            stringMode = false;
            val = ((SDouble) value).getValue();
        }

        Map<Integer, Map<String, Comparable>> rowMap = tableValue.rowMap();

        Table<Integer, String, Comparable> tableMatches = HashBasedTable.create();
        Table<Integer, String, Comparable> tableNotMatches = HashBasedTable.create();

        for (Integer rowKey : rowMap.keySet()) {
            Map<String, Comparable> row = rowMap.get(rowKey);

            if (!stringMode) {

                Double valueToCompare = val;
                Double valuetoCompareWith;

                Comparable get = row.get(columnToFilterOn);
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
            }
            else{

                String valueToCompare = valString;
                if (stringCompare(operator, (String) row.get(columnToFilterOn), valueToCompare)) {
                    putRowInTable(tableMatches, rowKey, row);
                } else {
                    putRowInTable(tableNotMatches, rowKey, row);
                }
            }
        }

        STable matchesTable = new STable(tableMatches);
        matchesTable.evaluate();
        setOutput(MATCH, matchesTable);

        STable notMatchesTable = new STable(tableNotMatches);
        notMatchesTable.evaluate();
        setOutput(DID_NOT_MATCH, notMatchesTable);
    }

    @Override
    public int getMinArgs() {
        return 3;
    }

    @Override
    public int getMaxArgs() {
        return 3;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    private void putRowInTable(Table<Integer, String, Comparable> table,
            Integer rowKey, Map<String, Comparable> row) {
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
