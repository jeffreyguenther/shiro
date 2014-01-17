package shiro.expressions;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import shiro.Value;

/**
 *
 * @author aga53
 */
public class SStringTest {

    SString stringExpression;

    @Before
    public void setUp() {
        stringExpression = new SString("Hello World");
    }

    @Test
    public void testInitialization() {
        Value expressionValue = stringExpression.evaluate();
        assertEquals("The value must have type as String", String.class,
                expressionValue.getType());
        assertEquals(expressionValue.getValue(), "Hello World");
        assertEquals(expressionValue.getValueAsString(), "Hello World");
    }
}
