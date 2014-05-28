package shiro.functions.data;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import shiro.ResultTuple;
import shiro.Value;
import au.com.bytecode.opencsv.CSVReader;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class ColumnAverageMFuncTest {
    String filePath = "D:\\Code\\Jeff\\Shiro\\src\\test\\resources\\shiro\\functions\\data\\TestCSV.csv";

    @Test
    public void test_average() {
        ColumnAverageMFunc averageMFunc = new ColumnAverageMFunc();
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

        List<Value> inputList = new ArrayList<Value>();
        inputList.add(0, new Value(table, Table.class));
        inputList.add(1, new Value("Measure", String.class));
        ResultTuple rt = averageMFunc.evaluate(inputList);
        assertEquals((100 + 101.1 - 12.03) / 3, rt.getValueForIndex(0)
                .getValueAsDouble(), 0.001d);
    }

}
