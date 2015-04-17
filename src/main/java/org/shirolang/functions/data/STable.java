package org.shirolang.functions.data;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.shirolang.values.SValue;

/**
 * Represents a table
 */
public class STable extends SValue<Table<Integer, String, Comparable>> {
    private static final String NAME = "Table";

    public STable(){
        this(HashBasedTable.<Integer, String, Comparable> create());
    }

    public STable(Table<Integer, String, Comparable> table){
        super(table);
    }

    public STable(String name, Table<Integer, String, Comparable> table){
        super(name, table);
    }

    @Override
    public String getType() {
        return NAME;
    }
}
