/*
 * The MIT License
 *
 * Copyright (c) 2012 - 2014 Jeffrey Guenther.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.shirolang.base;

import javafx.beans.property.BooleanProperty;
import org.shirolang.interpreter.Consoleable;

import java.util.List;

/**
 *
 * Defines a multi-function to be used in Shiro.
 * A multi-function is the most basic object in Shiro. It is used to represent numbers, expressions, and nodes.
 * A multi-function is simply a function that returns multiple values. It is deterministic - given the same
 * inputs, you will get the same add of results.
 *
 * Arguments and results can be access by name and by index. Implementing classes should map names to indices
 */
public interface SFunc extends Consoleable {
    /**
     * Evaluate the multifunction
     */
    void evaluate();

    /**
     * Get the the first argument of the multifunction.
     * This method should delegate to getArg(0);
     * @return arg 0 of the multi-function
     */
    SFunc getArg();

    /**
     * Get the arg with the given name
     * @param name
     * @return the arg value for the given name
     */
    SFunc getArg(String name);

    /**
     * Get the arg at the given index
     * @param i index of arg to return
     * @return the arg value at the index i
     */
    SFunc getArg(Integer i);

    /**
     * Gets the list of args
     * @return the list of args
     */
    List<SFunc> getArgs();

    /**
     * Get the maximum number of allowed args
     * @return max number of args the multi-function will take
     */
    int getMaxArgs();

    /**
     * Gets the minimum number of allowed args
     * @return minimum number of arguments the multi-function will take.
     */
    int getMinArgs();

    /**
     * Get the names of all of the args.
     * @return the list of args. The name of the argument will be stored in the corresponding position in the array
     */
    List<String> getArgKeys();

    /**
     * Sets the arg
     * @param name name of argument
     * @param v value to store
     */
    void setArg(String name, SFunc v);

    /**
     * Sets the arg
     * @param i index of the arg to add
     * @param v value to store
     */
    void setArg(Integer i, SFunc v);

    /**
     * Appends value to the end of the arg list
     * @param v value to store
     */
    void appendArg(SFunc v);

    /**
     * Determines whether the multi-function has args
     * @return true if the multi-function has args, otherwise false
     */
    boolean hasArgs();

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
    SFunc getResult(Integer i);

    /**
     * Gets the result for the given name
     * @param name name of the result to return
     * @return the result for the given name
     */
    SFunc getResult(String name);

    /**
     * Gets the list of results
     * @return the result list in index order
     */
    List<SFunc> getResults();

    /**
     * Gets the names mapped to the indices of the results list
     * @return the list of names of indices. The names are returned in the position of their corresponding index
     */
    List<String> getResultKeys();

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

    public String getName();
    public void setName(String name);
    public String getFullName();
    public void setFullName(String name);
}
