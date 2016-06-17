package org.shirolang.functions.data;

import com.google.common.collect.Table;
import org.shirolang.base.SFunc;
import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;
import org.shirolang.values.SDouble;
import org.shirolang.values.SString;

import java.util.DoubleSummaryStatistics;
import java.util.Map;

/**
 * This multifunction takes a Guava table and a column name as its inputs and
 * returns the average of the column
 *
 */
public class ColumnAverageMFunc extends SFuncBase {
    private static final String NAME = "ColumnAverage";
    private static final String TABLE = "table";
    private static final String AVERAGE = "average";
    private static final String COLUMN = "column";

    public ColumnAverageMFunc() {
        super();

        inputs.setKeyForIndex(TABLE, 0);
        inputs.add(new TypedValue("Table"));

        inputs.setKeyForIndex(COLUMN, 1);
        inputs.add(new TypedValue("String"));

        results.setKeyForIndex(AVERAGE, 0);
        results.add(new TypedValue("Double"));
    }

    @Override
    public void evaluate() {
        SFunc table = getInput(TABLE).getResult();
        SFunc column = getInput(COLUMN).getResult();

        Table<Integer, String, Comparable> tableValue = ((STable) table).getValue();
        String columnKey = ((SString) column).getValue();

        Map<Integer, Comparable> tColumn = tableValue.column(columnKey);
        DoubleSummaryStatistics stats = tColumn.values().stream().mapToDouble((x) -> Double.valueOf(x.toString())).summaryStatistics();

        SDouble av = new SDouble(stats.getAverage());
        av.evaluate();

        setOutput(AVERAGE, av);
    }

    @Override
    public int getMaxArgs() {
        return 2;
    }

    @Override
    public int getMinArgs() {
        return 2;
    }

    @Override
    public String getType() {
        return NAME;
    }
}
