package org.shirolang.interpreter.ast;

/**
 * Defines a binary operation
 */
public class BinaryOperation implements Expression {
    private Expression left;
    private Expression right;
    private BinaryOperator operator;

    public BinaryOperation(Expression left, BinaryOperator operator, Expression right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public BinaryOperator getOperator() {
        return operator;
    }

    @Override
    public String toCode() {
        return left.toCode() + " " + operator +  " " + right.toCode();
    }
}
