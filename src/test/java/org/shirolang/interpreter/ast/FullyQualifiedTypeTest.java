package org.shirolang.interpreter.ast;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void equals(){
        FullyQualifiedType type = new FullyQualifiedType("A.B.C");
        FullyQualifiedType type1 = new FullyQualifiedType("A.B.C");
        assertTrue(type.equals(type1));
    }
}
