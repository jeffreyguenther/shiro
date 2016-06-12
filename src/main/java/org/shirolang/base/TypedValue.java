package org.shirolang.base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an argument and result of a function.
 * Has a field to indicate what types should be expected
 */
public class TypedValue {
    private Set<String> acceptedTypes;
    private SFunc value;

    public static TypedValue asDouble(){
        return new TypedValue("Double");
    }

    public static TypedValue asInteger(){
        return new TypedValue("Integer");
    }

    public static TypedValue asString(){
        return new TypedValue("String");
    }

    public TypedValue(String... types){
        acceptedTypes = convertArrayToSet(types);
        this.value = null;
    }

    public TypedValue(String type, SFunc value){
        acceptedTypes = new HashSet<>();
        acceptedTypes.add(type);
        this.value = value;
    }

    /**
     * Gets the type expected by the value
     * @return
     */
    public Set<String> getAcceptedTypes() {
        return acceptedTypes;
    }

    /**
     * Gets the value of the typed value
     * @return the {@code SFunc} of the typed value
     * {@code null} is returned if the value has never been set
     */
    public SFunc getValue() {
        return value;
    }

    /**
     * Sets the value of the typed value
     * @param value the {@code SFunc} to be stored
     */
    public void setValue(SFunc value) {
        this.value = value;
    }

    /**
     * Sets the accepted types of the value
     * @param types the type to accept
     */
    public void setAcceptedTypes(Set<String> types) {
        acceptedTypes = types;
    }

    /**
     * Sets the accepted types for the value
     * @param types the types to accept
     */
    public void setAcceptedTypes(String... types){
        acceptedTypes = convertArrayToSet(types);
    }

    /**
     * Determines if the type of the {@code SFunc} matches the expected type
     * @param func the function to test
     * @return true if the types match, otherwise false
     */
    public boolean doesTypeMatch(SFunc func){
        return doesTypeMatch(func.getType());
    }

    /**
     * Determines if the type represented by the string matches the expected type
     * @param type the type to test
     * @return true if the types match, otherwise false
     */
    public boolean doesTypeMatch(String type){
        return acceptedTypes.contains(type);
    }

    /**
     * Converts an array of strings to a set of strings
     * @param array the array of strings to be converted
     * @return a set containing the elements of the array
     */
    private Set<String> convertArrayToSet(String[] array){
        return new HashSet<>(Arrays.asList(array));
    }
}
