package org.shirolang.interpreter.ast;

/**
 * Defines a unary operation
 */
public class UnaryOperation implements Expression {
    private Expression operand;
    private UnaryOperator operator;

    public UnaryOperation(UnaryOperator operator, Expression operand) {
        this.operand = operand;
        this.operator = operator;
    }

    public Expression getOperand() {
        return operand;
    }

    public UnaryOperator getOperator() {
        return operator;
    }

    @Override
    public String toCode() {
        if(getOperator().equals(UnaryOperator.PARENS)){
            return "(" + getOperand().toCode() +")";
        }else{
            return getOperator() + getOperand().toCode();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnaryOperation that = (UnaryOperation) o;

        if (!operand.equals(that.operand)) return false;
        return operator == that.operator;

    }

    @Override
    public int hashCode() {
        int result = operand.hashCode();
        result = 31 * result + operator.hashCode();
        return result;
    }
}