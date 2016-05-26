package org.shirolang.interpreter.ast;

/**
 * Defines a literal
 * @param <T> type of literal
 */
public class Literal<T> implements Expression {
    private T value;

    public Literal(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toCode() {
        if(value instanceof String){
            return "\"" + value.toString() +"\"";
        }else{
            return value.toString();
        }
    }

    @Override
    public String toString() {
        return toCode();
    }

    public static Literal<Boolean> asBoolean(Boolean b){
        return new Literal<Boolean>(b);
    }

    public static Literal<Integer> asInteger(Integer i){
        return new Literal<Integer>(i);
    }

    public static Literal<Double> asDouble(Double d){
        return new Literal<Double>(d);
    }

    public static Literal<String> asString(String s){
        return new Literal<String>(s);
    }
}
