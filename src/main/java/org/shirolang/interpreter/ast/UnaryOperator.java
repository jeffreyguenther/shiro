package org.shirolang.interpreter.ast;

/**
 * Define a unary operator
 */
public enum UnaryOperator {
    NEGATE, NOT, PARENS;

    @Override
    public String toString() {
        switch(this){
            case NEGATE:
                return "-";
            case NOT:
                return "!";
            default:
                return "";
        }
    }
}
