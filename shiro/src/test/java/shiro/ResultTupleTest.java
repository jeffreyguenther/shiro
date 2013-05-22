package shiro;

import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jeffreyguenther
 */
public class ResultTupleTest {
    private static ResultTuple tuple;
    private static final String x = "x";
    private static final String y = "y";
    private static final String flag = "flag";
    
    public ResultTupleTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        tuple = new ResultTuple();
    }

    /**
     * Test of getNameForIndex method, of class ResultTuple.
     */
    @Test
    public void testGetSetNameForIndex() {
        tuple.setNameforIndex(x, 0);
        tuple.setNameforIndex(y, 1);
        tuple.setNameforIndex(flag, 2);
        
        assertEquals(x, tuple.getNameForIndex(0));
        assertEquals(y, tuple.getNameForIndex(1));
        assertEquals(flag, tuple.getNameForIndex(2));
    }
    
    /**
     * Test of getNames method, of class ResultTuple.
     */
    @Test
    public void testGetNames() {
        Set<String> names = new HashSet<String>();
        names.add(x);
        names.add(y);
        names.add(flag);
        
        assertEquals(names, tuple.getNames());
    }
    
    /**
     * Test of setValueForIndex method, of class ResultTuple.
     */
    @Test
    public void testGetSetValueForIndex() {
        tuple.setValueForIndex(0, Value.createInteger(2));
        tuple.setValueForIndex(1, Value.createFloat(4.566f));
        tuple.setValueForIndex(2, Value.createString("blue"));
        
        assertEquals(tuple.getValueForIndex(0), Value.createInteger(2));
        assertEquals(tuple.getValueForIndex(1), Value.createFloat(4.566f));
        assertEquals(tuple.getValueForIndex(2), Value.createString("blue"));
    }


    /**
     * Test of setValueForName method, of class ResultTuple.
     */
    @Test
    public void testSetGetValueForName() {
        tuple.setValueForName(x, Value.createInteger(23));
        tuple.setValueForName(y, Value.createFloat(4.5636f));
        tuple.setValueForName(flag, Value.createString("blues"));
        
        assertEquals(tuple.getValueForName(x), Value.createInteger(23));
        assertEquals(tuple.getValueForName(y), Value.createFloat(4.5636f));
        assertEquals(tuple.getValueForName(flag), Value.createString("blues"));
    }
    
    /**
     * Test of createTuple method, of class ResultTuple.
     */
    @Test
    public void testCreateTuple_Integer() {
        ResultTuple newTuple = ResultTuple.createTuple(234);
        assertEquals(newTuple.getValueForIndex(0), Value.createInteger(234));
        
    }

    /**
     * Test of createTuple method, of class ResultTuple.
     */
    @Test
    public void testCreateTuple_Float() {
        ResultTuple newTuple = ResultTuple.createTuple(34.56f);
        assertEquals(newTuple.getValueForIndex(0), Value.createFloat(34.56f));
    }

    /**
     * Test of createTuple method, of class ResultTuple.
     */
    @Test
    public void testCreateTuple_String() {
        ResultTuple newTuple = ResultTuple.createTuple("elephants");
        assertEquals(newTuple.getValueForIndex(0), Value.createString("elephants"));
    }

    /**
     * Test of toString method, of class ResultTuple.
     */
    @Test
    public void testToString() {
        String expected = "23, 4.5636, blues";
        assertEquals(expected, tuple.toString());
    }

    /**
     * Test of equals method, of class ResultTuple.
     */
    @Test
    public void testEquals() {
        ResultTuple t1 = ResultTuple.createTuple("elephants");
        t1.setValueForIndex(1, Value.createDouble(2.3d));
        ResultTuple t2 = ResultTuple.createTuple(23);
        ResultTuple t3 = new ResultTuple();
        t3.setNameforIndex(x, 0);
        t3.setNameforIndex(y, 1);
        t3.setNameforIndex(flag, 2);
        t3.setValueForIndex(0, Value.createInteger(23));
        t3.setValueForName(y, Value.createFloat(4.5636f));
        t3.setValueForIndex(2, Value.createString("blues"));
        assertNotSame(t2, t1);
        assertNotSame(tuple, t1);
        assertEquals(tuple, t3);
        
    }
}
