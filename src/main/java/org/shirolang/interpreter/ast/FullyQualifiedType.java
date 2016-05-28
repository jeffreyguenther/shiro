package org.shirolang.interpreter.ast;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Describe a fully qualified type
 */
public class FullyQualifiedType implements Expression{
    private List<String> types;

    public FullyQualifiedType(List<String> types) {
        this.types = types;
    }

    public FullyQualifiedType(String... types) {
        this.types = Arrays.asList(types);
    }

    public FullyQualifiedType(String type) {
        this(type.split("\\."));
    }

    public List<String> getTypes() {
        return types;
    }

    @Override
    public String toCode() {
        return types.stream().collect(Collectors.joining("."));
    }
}
