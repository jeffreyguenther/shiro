package shiro;


import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jeffreyguenther
 */
public class ValueTest {

    private Value value;

    public ValueTest() {
    }

    @Before
    public void setUpClass() {
        value = new Value("This is a test", String.class);
    }

    /**
     * Test of getType method, of class Value.
     */
    @Test
    public void testGetType() {
        assertEquals(String.class, value.getType());
    }

    /**
     * Test of setType method, of class Value.
     */
    @Test
    public void testSetType() {
        value.setType(Float.class);
        assertEquals(Float.class, value.getType());
        value.setType(String.class);
        assertEquals(String.class, value.getType());
    }

    /**
     * Test of getValue method, of class Value.
     */
    @Test
    public void testGetValue() {
        value = Value.createString("This is a test");
        if (value.getType().equals(String.class)) {
            String result = (String) value.getValue();
            assertEquals("This is a test", result);
        } else {
            fail();
        }

    }

    /**
     * Test of setValue method, of class Value.
     */
    @Test
    public void testSetValue() {
        String expected = "Mr. Magoo";
        value.setValue(expected);
        assertEquals(expected, value.getValueAsString());
    }

    /**
     * Test of createInteger method, of class Value.
     */
    @Test
    public void testCreateInteger() {
        Integer expectedValue = 234;
        value = Value.createInteger(expectedValue);
        assertEquals(expectedValue, value.getValueAsInt());
    }

    /**
     * Test of getValueAsInt method, of class Value.
     */
    @Test
    public void testGetValueAsInt() {
        value = Value.createInteger(234);
        if (234 == value.getValueAsInt()) {
            assert true;
        } else {
            fail();
        }
    }

    /**
     * Test of createString method, of class Value.
     */
    @Test
    public void testCreateString() {
        String expectedValue = "Dog";
        value = Value.createString(expectedValue);
        assertEquals(expectedValue, value.getValueAsString());
    }

    /**
     * Test of getValueAsString method, of class Value.
     */
    @Test
    public void testGetValueAsString() {
        String expectedValue = "Dog";
        value = Value.createString(expectedValue);
        assertEquals("Dog", value.getValueAsString());
    }

    /**
     * Test of createFloat method, of class Value.
     */
    @Test
    public void testCreateFloat() {
        Float expectedValue = 234.33435343f;
        value = Value.createFloat(expectedValue);
        assertEquals(expectedValue, value.getValueAsFloat());
    }

    /**
     * Test of getValueAsFloat method, of class Value.
     */
    @Test
    public void testGetValueAsFloat() {
        Float expectedValue = 234.33435343f;
        value = Value.createFloat(expectedValue);
        if (234.33435343f == value.getValueAsFloat()) {
            assert true;
        } else {
            fail();
        }
    }

    /**
     * Test of createFloat method, of class Value.
     */
    @Test
    public void testCreateDouble() {
        Double expectedValue = -0.00034d;
        value = Value.createDouble(expectedValue);
        assertEquals(expectedValue, value.getValueAsDouble());
    }

    /**
     * Test of getValueAsDouble method, of class Value.
     */
    @Test
    public void testGetValueAsDouble() {
        Double expectedValue = -0.00034d;
        value = Value.createDouble(expectedValue);
        if (-0.00034d == value.getValueAsDouble()) {
            assert true;
        } else {
            fail();
        }
    }

    /**
     * Test of equals method, of class Value.
     */
    @Test
    public void testEquals() {
        value = Value.createString("bananas");
        Value v2 = Value.createInteger(23);
        Value v3 = Value.createInteger(23);
        Assert.assertNotSame(value, v2);
        assertEquals(v2, v3);
    }

    /**
     * Test of toString method, of class Value.
     */
    @Test
    public void testToString() {
        value = Value.createString("bananas");
        assertEquals("bananas", value.toString());
    }
}
