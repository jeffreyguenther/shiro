package org.shirolang.interpreter.ast;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IncludeStatementTest {
    @Test
    public void toCode(){
        IncludeStatement include = new IncludeStatement("badCode.sro");
        assertEquals("include \"badCode.sro\"", include.toCode());
    }

    @Test
    public void equals(){
        IncludeStatement thiss = new IncludeStatement("code.sro");
        IncludeStatement that = new IncludeStatement("code.sro");
        assertTrue(thiss.equals(that));
    }
}