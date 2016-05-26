package org.shirolang.interpreter.ast;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryOperationTest {
    @Test
    public void toCode(){
        BinaryOperation binaryOperator = new BinaryOperation(new Literal<Double>(1.222), BinaryOperator.ADD, new Literal<Double>(13.0));
        assertEquals("1.222 + 13.0", binaryOperator.toCode());

        BinaryOperation op2 = new BinaryOperation(new Literal<Double>(1.222), BinaryOperator.SUBTRACT, new Literal<Double>(13.0));
        assertEquals("1.222 - 13.0", op2.toCode());
    }
}
