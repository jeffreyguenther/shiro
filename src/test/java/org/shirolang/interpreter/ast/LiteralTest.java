package org.shirolang.interpreter.ast;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LiteralTest {
    @Test
    public void stringLiteral(){
        String expected = "Hi";
        Literal<String> s = new Literal<>(expected);

        assertEquals(expected, s.getValue());
        assertEquals("\"Hi\"", s.toCode());
    }

    @Test
    public void DoubleLiteral(){
        Double number = 1.233;
        Literal<Double> d = new Literal<>(number);

        assertEquals(number, d.getValue(), 1.0E-16);
        assertEquals("1.233", d.toCode());
    }

    @Test
    public void booleanLiteral(){
        Boolean bool = false;
        Literal<Boolean> b = new Literal<>(bool);

        assertFalse(b.getValue());
        assertEquals("false", b.toCode());
    }
}
