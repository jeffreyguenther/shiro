package org.shirolang.interpreter.ast;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnaryOperationTest {
    @Test
    public void toCode(){
        UnaryOperation negate = new UnaryOperation(UnaryOperator.NEGATE, new Literal<>(-1.2));
        assertEquals("--1.2", negate.toCode());

        UnaryOperation not = new UnaryOperation(UnaryOperator.NOT, new Literal<>(true));
        assertEquals("!true", not.toCode());
    }

    @Test
    public void parentheses(){
        UnaryOperation parens = new UnaryOperation(UnaryOperator.PARENS, new Literal<>(true));
        assertEquals("(true)", parens.toCode());
    }

    @Test
    public void equals(){
        UnaryOperation n1 = new UnaryOperation(UnaryOperator.NEGATE, new Literal<>(-1.2));
        UnaryOperation n2 = new UnaryOperation(UnaryOperator.NEGATE, new Literal<>(-1.2));
        assertTrue(n1.equals(n2));
    }
}
