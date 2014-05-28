/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.data;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import shiro.ResultTuple;
import shiro.Value;

/**
 *
 * @author jeffreyguenther
 */
public class IntersectMFuncTest {
    @Test
    public void evaluate(){
        Table<Integer, String, Object> tableA = HashBasedTable.create();
        tableA.put(0, "A", "A0 Content");
        tableA.put(0, "B", "B0 Content");
        tableA.put(1, "A", "A1 Content");
        tableA.put(1, "B", "B1 Content");
        
        Table<Integer, String, Object> tableB = HashBasedTable.create();
        tableB.put(1, "A", "A1 Content");
        tableB.put(1, "B", "B1 Content");
        
        Table<Integer, String, Object> tableC = HashBasedTable.create();
        tableC.put(0, "A", "A0 Content");
        tableC.put(0, "B", "B0 Content");
        
        IntersectMFunc mf = new IntersectMFunc();
        List<Value> args = Arrays.asList(new Value(tableA, Table.class), new Value(tableB, Table.class));
        
        ResultTuple evaluate = mf.evaluate(args);
        Value tableValue = evaluate.getValueForIndex(0);
        Table<Integer, String, Object> result = (Table<Integer, String, Object>) tableValue.getValue();
        
        Assert.assertNotEquals(tableC, result);
        Assert.assertEquals(tableB, result);
    }
}
