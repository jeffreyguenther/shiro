package org.shirolang.interpreter.ast;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FullyQualifiedTypeTest {
    @Test
    public void asVarArgs(){
        FullyQualifiedType type = new FullyQualifiedType("A", "B", "C");
        assertEquals("A.B.C", type.toCode());
    }

    @Test
    public void asList(){
        FullyQualifiedType type = new FullyQualifiedType(Arrays.asList("A", "B", "C"));
        assertEquals("A.B.C", type.toCode());
    }

    @Test
    public void asString(){
        FullyQualifiedType type = new FullyQualifiedType("A.B.C");
        assertEquals("A.B.C", type.toCode());
    }
}
