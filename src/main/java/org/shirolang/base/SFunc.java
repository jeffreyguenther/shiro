package org.shirolang.base;

import javafx.beans.property.BooleanProperty;
import org.shirolang.interpreter.Consoleable;
import org.shirolang.interpreter.ast.Access;
import org.shirolang.interpreter.ast.PathSegment;

import java.util.List;
import java.util.Set;

/**
 *
 * Defines a multi-function to be used in Shiro.
 * A multi-function is the most basic object in Shiro. It is used to represent numbers, expressions, and nodes.
 * A multi-function is simply a function that returns multiple values. It is deterministic - given the same
 * inputs, you will get the same add of results.
 *
 * Arguments and results can be access by name and by index. Implementing classes should map names to indices
 * in the implementing class' constructor.
 */
public interface SFunc extends Consoleable {
    /**
     * Evaluate the multifunction
     */
    void evaluate();

    /**
     * Get the the first argument of the multifunction.
     * This method delegates to getInput(0);
     * @return arg 0 of the multi-function
     */
    SFunc getInput();

    /**
     * Get the arg with the given name
     * @param name
     * @return the arg value for the given name
     */
    SFunc getInput(String name);

    /**
     * Get the arg at the given index
     * @param i index of arg to return
     * @return the arg value at the index i
     */
    SFunc getInput(Integer i);

    /**
     * Gets the list of inputs
     * @return the list of inputs
     */
    List<SFunc> getInputs();

    /**
     * Gets the list of expected types. The string's position
     * in the array corresponds to it's argument
     * @return the list of expected types
     */
    List<Set<String>> getInputTypes();

    /**
     * Gets the expected type of the argument
     * @param argIndex the index of the argument
     * @return the set of expected types of the argument
     */
    Set<String> getAcceptedTypes(int argIndex);

    /**
     * Gets the expected type of the argument
     * @param name the name of the argument
     * @return the set of expected type of the argument
     */
    Set<String> getAcceptedType(String name);

    /**
     * Determines if the type of the argument with given name
     * matches the type of the value
     * @param argName the name of the argument
     * @param value the value to be assigned to compared
     * @return true if the types match, otherwise false
     */
    boolean doesExpectedTypeMatch(String argName, SFunc value);

    /**
     * Determines if the type of the argument at the index matches
     * the type of the value
     * @param argIndex the index of the argument
     * @param value the value to be assigned to compared
     * @return true of the types match, otherwise false
     */
    boolean doesExpectedTypeMatch(int argIndex, SFunc value);

    /**
     * Get the maximum number of allowed inputs
     * @return max number of inputs the multi-function will take
     */
    int getMaxArgs();

    /**
     * Gets the minimum number of allowed inputs
     * @return minimum number of arguments the multi-function will take.
     */
    int getMinArgs();

    /**
     * Gets the value for the given path segment.
     * This method allows you to access the value of an argument or a result
     * via a path segment and takes care of all that logic for you.
     * @param segment to access
     * @return the {@code SFunc} matching the segment, returns null if nothing is found.
     */
    SFunc get(PathSegment segment);

    /**
     * Get the names of all of the inputs.
     * @return the list of inputs. The name of the argument will be stored in the corresponding position in the array
     */
    List<String> getInputKeys();

    /**
     * Sets the arg
     * @param name name of argument
     * @param v value to store
     */
    void setInput(String name, SFunc v);

    /**
     * Sets the arg
     * @param i index of the arg to add
     * @param v value to store
     */
    void setInput(Integer i, SFunc v);

    /**
     * Appends an argument to the multi-function.
     * Not this method bypasses the type
     * @param arg the port to be added as an argument
     */
    void appendInput(SFunc arg);

    /**
     * Determines whether the multi-function has inputs
     * @return true if the multi-function has inputs, otherwise false
     */
    boolean hasInputs();

    /**
     * Gets the multi-functions this multi-function depends on
     * @return the list of depended on multi-functions
     */
    List<SFunc> getDependencies();

    /**
     * Gets the first result in the results list
     * @return the result at position 0 in the results list
     */
    SFunc getResult();

    /**
     * Gets the result at the specified index
     * @param i index of result to return
     * @return result at position i
     */
    SFunc getOutput(Integer i);

    /**
     * Gets the result for the given name
     * @param name name of the result to return
     * @return the result for the given name
     */
    SFunc getOutput(String name);

    /**
     * Gets the list of results
     * @return the result list in index order
     */
    List<SFunc> getOutputs();

    /**
     * Sets the result at the given index
     * @param resultIndex the index of the result
     * @param value the value to be assigned
     */
    void setOutput(Integer resultIndex, SFunc value);

    /**
     * Sets the result with the given name
     * @param name
     * @param value
     */
    void setOutput(String name, SFunc value);

    /**
     * Appends a result to the multi-function.
     * Note this method bypasses the type
     * @param arg the port to be added as an argument
     */
    void appendOutput(SFunc arg);

    /**
     * Gets the names mapped to the indices of the results list
     * @return the list of names of indices. The names are returned in the position of their corresponding index
     */
    List<String> getOutputKeys();

    /**
     * Gets the types returned by the multi-function
     * @return the list of types. The types are returned in the position of their corresponding
     * value
     */
    List<Set<String>> getOutputTypes();

    /**
     * Gets the type of the returned result
     * @param resultName
     * @return the type of the result
     */
    Set<String> getOutputType(String resultName);

    /**
     * Gets the type of the value returned at the index
     * @param resultIndex the index of the result
     * @return the type of the result
     */
    Set<String> getOutputType(int resultIndex);

    /**
     * Gets the Shiro language type the function represents
     * For example, Ident, or Boolean.
     * @return the Shiro type of the multi-function
     */
    String getType();

    /**
     * Determines if the multi-function represents an identifier
     * @return if true, the multi-function's type is "Ident"
     */
    boolean isIdent();

    /**
     * Determines if the multi-function represents an integer
     * @return if true, the multi-function's type is "Integer"
     */
    boolean isInteger();

    /**
     * Determines if the multi-function represents a double
     * @return if true, the multi-function's type is "Double"
     */
    boolean isDouble();

    /**
     * Determines if the multi-function represents a string
     * @return if true, the multi-function's type is "String"
     */
    boolean isString();

    /**
     * Determines if the multi-function represents a boolean
     * @return if true, the multi-function's type is "Boolean"
     */
    boolean isBoolean();

    /**
     * Determines if the multi-function represent a list
     * @return if true, the multi-functions' type is a "List"
     */
    boolean isList();

    /**
     * Gets the symbol type of the multi-function
     * This method is used internally to distinguish nodes, ports, and literals
     * @return the internal type of the multi-function
     */
    SymbolType getSymbolType();

    /**
     * Sets the symbol type of the multi-function
     * This methods is used to change the interal type of the multi-function
     * @param type the internal type of the multifunction
     */
    void setSymbolType(SymbolType type);

    /**
     * Gets the access value
     * @return the access value of the port
     * READ - means the port is an output
     * READWRITE - means the port is an input
     * INTERNAL - means the port is inaccessible outside the node
     */
    Access getAccess();

    /**
     * Sets the access of the port
     * @param a access value for the port
     */
    void setAccess(Access a);

    /**
     * Sets the multi-function as active
     * The active field is used to control whether a multi-function is evaluated
     * when the graph is traversed.
     * @param b
     */
    public void setActive(boolean b);

    /**
     * Determines if a multi-function is active
     * @return true if the multi-function is active, otherwise false
     */
    public boolean isActive();

    /**
     * Returns whether the multi-function is active
     * @return
     */
    public BooleanProperty isActiveProperty();

    /**
     * Gets the name of the multi-function
     * @return the name of the multi-function. It returns an empty string if it was
     * never set
     */
    public String getName();

    /**
     * Sets the name of the multi-function
     * @param name the name of multi-function
     */
    public void setName(String name);

    /**
     * Gets the full name of the multi-function
     * @return the full name of the multi-function.
     */
    public String getFullName();

    /**
     * Sets the full name of the multi-function
     * @param name the full name of the multi-function
     */
    public void setFullName(String name);
}
