package org.shirolang.interpreter.ast;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnaryOperationTest {
    @Test
    public void toCode(){
        UnaryOperation negate = new UnaryOperation(UnaryOperator.NEGATE, new Literal<Double>(-1.2));
        assertEquals("--1.2", negate.toCode());

        UnaryOperation not = new UnaryOperation(UnaryOperator.NOT, new Literal<Boolean>(true));
        assertEquals("!true", not.toCode());
    }

    @Test
    public void parentheses(){
        UnaryOperation parens = new UnaryOperation(UnaryOperator.PARENS, new Literal<Boolean>(true));
        assertEquals("(true)", parens.toCode());
    }
}
