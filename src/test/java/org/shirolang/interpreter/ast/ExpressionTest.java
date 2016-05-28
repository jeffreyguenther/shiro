package org.shirolang.interpreter.ast;

import org.junit.Test;
import org.shirolang.fixtures.ExpressionDefinitionFixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExpressionTest {
    @Test
    public void toCode(){
        Expression expr = ExpressionDefinitionFixture.complexComparison();
        assertEquals("1.0 == (3 + 2)", expr.toCode());

        Expression expr2 = ExpressionDefinitionFixture.addLiterals();
        assertEquals("3.5 + 6", expr2.toCode());
    }

    @Test
    public void nestedFunctionCalls(){
        Expression expr = ExpressionDefinitionFixture.nestedFunctionCall();
        assertEquals("F(G(H(1)))", expr.toCode());
    }
}