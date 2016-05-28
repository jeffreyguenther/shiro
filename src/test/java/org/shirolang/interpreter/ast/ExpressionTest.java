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

    @Test
    public void withReference(){
        Expression expr = ExpressionDefinitionFixture.referenceAsArg();
        assertEquals("Map(~DoubleIt())", expr.toCode());
    }

    @Test
    public void functionWithPath(){
        Expression expr = ExpressionDefinitionFixture.functionCallWithPath();
        assertEquals("A(a.b.c)", expr.toCode());
    }

    @Test
    public void functionWithFQTArg(){
        Expression expr = ExpressionDefinitionFixture.functionWithFQTArg();
        assertEquals("A(X.Y)", expr.toCode());
    }
}