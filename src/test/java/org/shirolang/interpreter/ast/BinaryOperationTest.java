package org.shirolang.interpreter.ast;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BinaryOperationTest {
    @Test
    public void toCode(){
        BinaryOperation binaryOperator = new BinaryOperation(new Literal<>(1.222), BinaryOperator.ADD, new Literal<>(13.0));
        assertEquals("1.222 + 13.0", binaryOperator.toCode());

        BinaryOperation op2 = new BinaryOperation(new Literal<>(1.222), BinaryOperator.SUBTRACT, new Literal<>(13.0));
        assertEquals("1.222 - 13.0", op2.toCode());
    }

    @Test
    public void equals(){
        BinaryOperation b1 = new BinaryOperation(new Literal<>(1.222), BinaryOperator.ADD, new Literal<>(13.0));
        BinaryOperation b2 = new BinaryOperation(new Literal<>(1.222), BinaryOperator.ADD, new Literal<>(13.0));

        assertTrue(b1.equals(b2));
        assertEquals(BinaryOperator.ADD, BinaryOperator.ADD);
    }
}
