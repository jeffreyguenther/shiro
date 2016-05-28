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
}