package org.shirolang.fixtures;

import org.shirolang.interpreter.ast.*;

import java.util.Arrays;

/**
 * Creates expressions for use in testing
 */
public class ExpressionDefinitionFixture {
    /**
     * 3.5 + 6
     * @return
     */
    public static Expression addLiterals(){
        return new BinaryOperation(Literal.asDouble(3.5), BinaryOperator.ADD, Literal.asInteger(6));
    }

    /**
     * 1.0 == (3 + 2)
     * @return AST representing the expression
     */
    public static Expression complexComparison(){
        Expression rhs = new BinaryOperation(Literal.asInteger(3), BinaryOperator.ADD, Literal.asInteger((2)));
        Expression lhs = Literal.asDouble(1.0);

        return new BinaryOperation(lhs, BinaryOperator.EQUAL, new UnaryOperation(UnaryOperator.PARENS, rhs));
    }

    /**
     * a.b(10) / 0.1
     * @return
     */
    public static Expression withFunctionCall(){
        FunctionCall call = new FunctionCall("a.b", Arrays.asList(Literal.asInteger(10)));
        return new BinaryOperation(call, BinaryOperator.DIVIDE, Literal.asDouble(0.1));
    }
}
