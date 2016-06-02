package org.shirolang.interpreter.ast;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void fullyQualifiedType(){
        Expression t = Literal.asFullyQualifiedType("A.B.C");
        assertEquals("A.B.C", t.toCode());
    }

    @Test
    public void path(){
        Path p = Path.create("a.b.c");
        Expression t = Literal.asPath(p);
        assertEquals("a.b.c", t.toCode());
    }

    @Test
    public void referencePath(){
        Path p = Path.createReference("a.b.c");
        Expression t = Literal.asPath(p);
        assertEquals("~a.b.c", t.toCode());
    }

    @Test
    public void selectorPath(){
        Path p = Path.createSelector("a.b.c");
        Expression t = Literal.asPath(p);
        assertEquals("@a.b.c", t.toCode());
    }

    @Test
    public void equals(){
        Path p = Path.createReference("a.b.c");
        Expression t = Literal.asPath(p);

        Path p1 = Path.createReference("a.b.c");
        Expression t1 = Literal.asPath(p1);

        assertTrue(t.equals(t1));
    }
}
