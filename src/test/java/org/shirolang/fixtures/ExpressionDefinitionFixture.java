package org.shirolang.fixtures;

import org.shirolang.interpreter.ast.*;

import java.util.Arrays;

/**
 * Creates expressions for use in testing
 */
public class ExpressionDefinitionFixture {
    /**
     * 3.5 + 6
     */
    public static Expression addLiterals(){
        return new BinaryOperation(Literal.asDouble(3.5), BinaryOperator.ADD, Literal.asInteger(6));
    }

    /**
     * 1.0 == (3 + 2)
     */
    public static Expression complexComparison(){
        Expression rhs = new BinaryOperation(Literal.asInteger(3), BinaryOperator.ADD, Literal.asInteger((2)));
        Expression lhs = Literal.asDouble(1.0);

        return new BinaryOperation(lhs, BinaryOperator.EQUAL, new UnaryOperation(UnaryOperator.PARENS, rhs));
    }

    /**
     * F(G(H(1)))
     */
    public static Expression nestedFunctionCall(){
        return new FunctionCall("F", Arrays.asList(new FunctionCall("G", Arrays.asList(new FunctionCall("H", Arrays.asList(Literal.asInteger(1)))))));
    }
}