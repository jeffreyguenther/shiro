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
        }else if(value instanceof Codeable){
            return ((Codeable) value).toCode();
        }else{
            return value.toString();
        }
    }

    public static Expression asBoolean(Boolean b){
        return new Literal<>(b);
    }

    public static Expression asInteger(Integer i){
        return new Literal<>(i);
    }

    public static Expression asDouble(Double d){
        return new Literal<>(d);
    }

    public static Expression asString(String s){
        return new Literal<>(s);
    }

    public static Expression asPath(Path p){
        return new Literal<>(p);
    }

    public static Expression asFullyQualifiedType(String type){
        return new FullyQualifiedType(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Literal<?> literal = (Literal<?>) o;

        return value.equals(literal.value);

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
