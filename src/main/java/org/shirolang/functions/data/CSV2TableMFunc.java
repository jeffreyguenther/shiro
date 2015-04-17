package org.shirolang.functions.data;

import au.com.bytecode.opencsv.CSVReader;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.shirolang.base.SFunc;
import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;
import org.shirolang.values.SString;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class CSV2TableMFunc extends SFuncBase {
    private static final String NAME = "CSV2Table";
    private static final String PATH = "path";
    private static final String TABLE = "table";
    
    public CSV2TableMFunc(){
        super();

        args.setKeyForIndex(PATH, 0);
        args.add(new TypedValue("String"));

        results.setKeyForIndex(TABLE, 0);
        results.add(new TypedValue("Table"));
    }

    @Override
    public void evaluate(){
        SFunc path = getArg(PATH).getResult();

        String p = ((SString) path).getValue();

        Table<Integer, String, Comparable> table = HashBasedTable
                .<Integer, String, Comparable> create();
        int rowCount = 0;
        File f = new File(p);

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

        STable tableOut = new STable(table);
        tableOut.evaluate();

       setResult(TABLE, tableOut);
    }

    @Override
    public int getMaxArgs() {
        return 1;
    }

    @Override
    public int getMinArgs() {
        return 1;
    }

    @Override
    public String getType() {
        return NAME;
    }
}
