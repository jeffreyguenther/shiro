package org.shirolang.interpreter.ast;

import org.junit.Test;
import org.shirolang.fixtures.ExpressionDefinitionFixture;

import static org.junit.Assert.assertEquals;

public class ExpressionTest {
    @Test
    public void toCode(){
        Expression expr = ExpressionDefinitionFixture.complexComparison();
        assertEquals("1.0 == (3 + 2)", expr.toCode());

        Expression expr2 = ExpressionDefinitionFixture.addLiterals();
        assertEquals("3.5 + 6", expr2.toCode());

        Expression expr3 = ExpressionDefinitionFixture.withFunctionCall();
        assertEquals("a.b(10) / 0.1", expr3.toCode());
    }
}
