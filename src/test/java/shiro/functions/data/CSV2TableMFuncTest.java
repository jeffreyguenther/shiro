package shiro.functions.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shiro.ResultTuple;
import shiro.Value;

/**
 *
 * @author Ankit Gupta
 */
public class CSV2TableMFuncTest {

    String filePath = "D:\\Code\\Jeff\\Shiro\\src\\test\\resources\\shiro\\functions\\data\\TestCSV.csv";
    CSV2TableMFunc csvMFunc = new CSV2TableMFunc();

    public CSV2TableMFuncTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void read_csv_test() {
        List<Value> inputList = new ArrayList<>();
        inputList.add(new Value(filePath, String.class));
        ResultTuple rt = csvMFunc.evaluate(inputList);

        assertEquals(
                "{0={Year=2012, Measure=100, Country=X}, 1={Year=2012, Measure=101.1, Country=Y}, 2={Year=2012, Measure=-12.03, Country=Z}}",
                rt.getValueForIndex(0).toString());
    }

}
