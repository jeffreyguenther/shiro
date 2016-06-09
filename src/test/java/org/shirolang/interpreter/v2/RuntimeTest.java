package org.shirolang.interpreter.v2;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RuntimeTest {
    @Test
    public void create(){
        Runtime r = new Runtime();
        assertNotNull(r);
    }
}