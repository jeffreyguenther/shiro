package org.shirolang.interpreter.ast;

/**
 * Defines the legal binary operators
 */
public enum BinaryOperator {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    MOD,
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_OR_EQUAL,
    LESS_THAN_OR_EQUAL,
    EQUAL,
    NOT_EQUAL,
    AND,
    OR;

    @Override
    public String toString() {
        switch(this){
            case ADD:
                return "+";
            case SUBTRACT:
                return "-";
            case MULTIPLY:
                return "*";
            case DIVIDE:
                return "/";
            case MOD:
                return "%";
            case GREATER_THAN:
                return ">";
            case LESS_THAN:
                return "<";
            case GREATER_THAN_OR_EQUAL:
                return ">=";
            case LESS_THAN_OR_EQUAL:
                return "<=";
            case EQUAL:
                return "==";
            case NOT_EQUAL:
                return "!=";
            case AND:
                return "&&";
            case OR:
                return "||";
            default:
                return "";
        }
    }
}
