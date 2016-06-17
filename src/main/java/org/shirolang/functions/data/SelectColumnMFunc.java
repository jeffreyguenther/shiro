package org.shirolang.functions.data;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.shirolang.base.SFunc;
import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;
import org.shirolang.values.SString;

import java.util.Map;

/**
 *
 *
 */
public class SelectColumnMFunc extends SFuncBase {
    private static final String TYPE = "SSelectColumn";
    private static final String TABLE = "table";
    private static final String COLUMN = "column";

    private static final String NEW_TABLE = "newTable";

    public SelectColumnMFunc(){
        super();

        inputs.setKeyForIndex(TABLE, 0);
        inputs.add(new TypedValue("Table"));

        inputs.setKeyForIndex(COLUMN, 1);
        inputs.add(new TypedValue("String"));

        results.setKeyForIndex(NEW_TABLE, 0);
        results.add(new TypedValue("Table"));
    }

    @Override
    public void evaluate() {
        SFunc table = getInput(TABLE).getResult();
        SFunc column = getInput(COLUMN).getResult();

        Table<Integer, String, Comparable> tableValue = ((STable) table).getValue();
        String columnSelected = ((SString) column).getValue();

        Table<Integer, String, Comparable> tableMatches = HashBasedTable.<Integer, String, Comparable> create();
        Map<Integer, Comparable> columnMatch = tableValue.column(columnSelected);

        for(Integer i: columnMatch.keySet()){
            tableMatches.put(i, columnSelected, columnMatch.get(i));
        }

        STable t = new STable(tableMatches);
        t.evaluate();
        setOutput(NEW_TABLE, t);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public int getMaxArgs() {
        return 2;
    }

    @Override
    public int getMinArgs() {
        return 2;
    }
}
