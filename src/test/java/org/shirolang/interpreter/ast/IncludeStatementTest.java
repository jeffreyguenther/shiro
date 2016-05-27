package org.shirolang.interpreter.ast;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IncludeStatementTest {
    @Test
    public void toCode(){
        IncludeStatement include = new IncludeStatement("code.sro");
        assertEquals("include \"code.sro\"", include.toCode());
    }
}