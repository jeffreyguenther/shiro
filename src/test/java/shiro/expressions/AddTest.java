package shiro.expressions;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import shiro.exceptions.PortNotActiveException;
import shiro.Value;

/**
 *
 * @author aga53
 */
public class AddTest {

    private static Add floatExpression1, floatExpression2, intExpression1, intExpression2, intFloatExpression;
    private static Add invalidAddExpression;

    @Before
    public void setup() {
        SNumber n1 = new SNumber(1.1d);
        SNumber n2 = new SNumber(2.1d);
        SNumber n3 = new SNumber(100.3d);
        floatExpression1 = new Add(n1, n2);
        floatExpression2 = new Add(n3, floatExpression1);

        invalidAddExpression = new Add(new SString("Hello"), new SNumber(
                10d));

        SNumber n4 = new SNumber(1d);
        SNumber n5 = new SNumber(2d);
        SNumber n6 = new SNumber(100d);
        intExpression1 = new Add(n4, n5);
        intExpression2 = new Add(intExpression1, n6);

        intFloatExpression = new Add(floatExpression2, intExpression2);
      
    }

    @Test
    public void testFloatAdd() {
        Value value = null;
        try {
            value = floatExpression1.evaluate();
        } catch (PortNotActiveException ex) {
            Assert.fail("The expression must evaluate fine");
        }
        //Allowing an imprecision of 10^-6
        Assert.assertEquals("The addition result must be 3.2",
                value.getValueAsDouble(), 3.2, 0.000001);

        try {
            value = floatExpression2.evaluate();
        } catch (PortNotActiveException ex) {
            Assert.fail("The expression must evaluate fine");
        }

        Assert.assertEquals("Th expression must fetch 103.5",
                value.getValueAsFloat(), 103.5, 0);
    }

    @Test
    public void testIntegerAddition() {
        Value value = null;
        try {
            value = intExpression1.evaluate();
        } catch (PortNotActiveException ex) {
            Assert.fail("The expression must evaluate fine");
        }

        Assert.assertEquals("The addition result must be 3",
                value.getValueAsFloat(), 3f, 0);

        try {
            value = intExpression2.evaluate();
        } catch (PortNotActiveException ex) {
            Assert.fail("The expression must evaluate fine");
        }

        Assert.assertEquals("Th expression must fetch 103",
                value.getValueAsFloat(), 103, 0);
    }

    @Test
    public void testIntegerFloatAddition() {
        Value value = null;

        try {
            value = intFloatExpression.evaluate();
        } catch (PortNotActiveException ex) {
            Assert.fail("The expression must evaluate fine");
        }

        Assert.assertEquals("The sum of 103 and 103.5 should be 206.5",
                value.getValueAsFloat(), 206.5, 0);

    }

    /**
     * A test for an invalid expression. This should throw an error.
     */
    @Test(expected = Exception.class)
    public void testInvalidExpressionFailure() {
//        TODO add a more meaningful exception for this case instead of a null pointer exception.
        try {
            Value invalidValue = invalidAddExpression.evaluate();
            System.out.println(invalidValue.getValue());
        } catch (PortNotActiveException ex) {
            Logger.getLogger(AddTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
