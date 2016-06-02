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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryOperation that = (BinaryOperation) o;

        if (!left.equals(that.left)) return false;
        if (!right.equals(that.right)) return false;
        return operator == that.operator;

    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        result = 31 * result + operator.hashCode();
        return result;
    }
}
