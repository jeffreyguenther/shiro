package shiro.functions.data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;
import au.com.bytecode.opencsv.CSVReader;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * This multifunction takes the path of a CSV file as String and returns a Guava
 * table datastructure corresponding to the CSV. The first line of the CSV file
 * is used as the header (i.e. for column names)
 * 
 * @author Ankit Gupta
 */
public class CSV2TableMFunc implements MultiFunction {

    @Override
    public String getName() {
        return "CSV2Table";
    }

    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        String filePath = arguments.get(0).getValueAsString();
        Table<Integer, String, Object> table = HashBasedTable
                .<Integer, String, Object> create();
        int rowCount = 0;
        File f = new File(filePath);
        String[] nextLine;
        boolean firstLine = true;
        String[] columnNames = null;
        try (CSVReader csvReader = new CSVReader(new FileReader(f))) {
            while ((nextLine = csvReader.readNext()) != null) {
                if (!firstLine) {
                    int i = 0;
                    for (String key : columnNames) {
                        table.put(rowCount, key, nextLine[i++]);
                    }
                    rowCount++;
                } else {
                    // for the first line, store the column names
                    columnNames = nextLine;
                    firstLine = !firstLine;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResultTuple(0, new Value(table, Table.class));
    }

}
