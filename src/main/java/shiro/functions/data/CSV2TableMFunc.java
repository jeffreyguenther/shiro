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
@SuppressWarnings("unchecked")
public class CSV2TableMFunc implements MultiFunction {
     private static final String NAME = "CSV2Table";
    private static final int PATH = 0;
    
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        String filePath = arguments.get(PATH).getValueAsString();
        
        Table<Integer, String, Comparable> table = HashBasedTable
                .<Integer, String, Comparable> create();
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
