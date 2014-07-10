package shiro.functions.data;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Table;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 * This multifunction takes a Guava table and a column name as its inputs and
 * returns the average of the column.
 * 
 * @author Ankit Gupta
 *
 */
@SuppressWarnings("unchecked")
public class ColumnAverageMFunc implements MultiFunction {
    private static final String NAME = "ColumnAverage";
    private static final int TABLE = 0;
    private static final int COLUMN = 1;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value tableValue = arguments.get(TABLE);
        Value columnValue = arguments.get(COLUMN);
        
        Table<Integer, String, Comparable> table = (Table<Integer, String, Comparable>) tableValue.getValue();
        String columnKey = columnValue.getValueAsString();
        Map<Integer, Comparable> column = table.column(columnKey);
        Integer sum = 0;
        DoubleSummaryStatistics stats = column.values().stream().mapToDouble((x) -> Double.valueOf(x.toString())).summaryStatistics();
        return new ResultTuple(0, new Value(stats.getAverage(), Double.class));
    }

}
