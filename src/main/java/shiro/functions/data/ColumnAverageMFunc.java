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
public class ColumnAverageMFunc implements MultiFunction {

    @Override
    public String getName() {
        return "ColumnAverage";
    }

    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value tableValue = arguments.get(0);
        Value columnValue = arguments.get(1);
        
        Table<Integer, String, Object> table = (Table<Integer, String, Object>) tableValue.getValue();
        String columnKey = columnValue.getValueAsString();
        Map<Integer, Object> column = table.column(columnKey);
        Integer sum = 0;
        DoubleSummaryStatistics stats = column.values().stream().mapToDouble((x) -> Double.valueOf(x.toString())).summaryStatistics();
        return new ResultTuple(0, new Value(stats.getAverage(), Double.class));
    }

}
