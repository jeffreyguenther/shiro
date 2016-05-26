package org.shirolang.interpreter.ast;

import org.junit.Test;
import org.shirolang.fixtures.FunctionCallFixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FunctionCallTest {
    @Test
    public void canCreateFunctionCall(){
        FunctionCall call = FunctionCallFixture.withPathAndKeywordargs();
        assertNotNull(call);
    }

    @Test
    public void toCode(){
        FunctionCall call1 = FunctionCallFixture.withPathAndKeywordargs();
        assertEquals("x.y(a: 1, b: 10)", call1.toCode());

        FunctionCall call2 = FunctionCallFixture.withPathOptionAndKeywordargs();
        assertEquals("x.y[b](a: 1, b: 10)", call2.toCode());

        FunctionCall call3 = FunctionCallFixture.withPathAndListOfArgs();
        assertEquals("a.y(1, 2, 3)", call3.toCode());

        FunctionCall call4 = FunctionCallFixture.withPathOptionAndListOfArgs();
        assertEquals("a.y[b](1, 2, 3)", call4.toCode());

        FunctionCall call5 = FunctionCallFixture.withFunctionCallArgs();
        assertEquals("a.x(a.b(10) / 0.1, 10)", call5.toCode());
    }
}