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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.shirolang.values.Path;
import org.shirolang.values.PathSegment;
import org.shirolang.values.SIdent;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * A base class to handle the common requirements
 * of implementing multi-functions.
 */
public abstract class SFuncBase implements SFunc {
    protected SIndexedMap<TypedValue> inputs;
    protected SIndexedMap<TypedValue> results;
    protected BooleanProperty isActive;

    protected StringProperty name;
    protected StringProperty fullName;
    protected SymbolType symbolType;
    protected Access access;

    public SFuncBase() {
        inputs = new SIndexedMap<>();
        results = new SIndexedMap<>();
        isActive = new SimpleBooleanProperty(true);

        name = new SimpleStringProperty("");
        fullName = new SimpleStringProperty("");
        symbolType = SymbolType.PORT;
        access = Access.READWRITE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SFunc getInput() {
        return getInput(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SFunc getInput(String name) {
        if(inputs.hasKey(name)){
            return inputs.get(name).getValue();
        }else{
            return null;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SFunc getInput(Integer i) {
        if(i >= 0 || i < inputs.size() ){
            return inputs.get(i).getValue();
        }else{
            return null;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SFunc> getInputs() {
        // note the null filter. Because arguments can be stored without a value, some maybe null.
        // We need to filter these out.
        return inputs.values.stream().map(TypedValue::getValue).filter(Objects::nonNull).collect(toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Set<String>> getInputTypes() {
        return inputs.values.stream().map(a -> a.getAcceptedTypes()).collect(toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getAcceptedTypes(int argIndex) {
        return inputs.get(argIndex).getAcceptedTypes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getAcceptedType(String name) {
        return inputs.get(name).getAcceptedTypes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doesExpectedTypeMatch(int argIndex, SFunc value) {
        return inputs.get(argIndex).doesTypeMatch(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doesExpectedTypeMatch(String argName, SFunc value) {
        return inputs.get(argName).doesTypeMatch(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getInputKeys() {
        return inputs.getKeys();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasInputs() {
        return !inputs.isEmpty() && !inputs.values.stream().map(a -> a.getValue()).anyMatch(Objects::isNull);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SFunc> getDependencies() {
        return getInputs();
    }

    /**
     * Detects if the multifunction is an identifier that refers to
     * another multifunction rather than the values at the multifunction
     * This allows the runtime to know if a path refers to multifunction or it's values
     * For example, if you wrote
     * <code>
     * port a Add(1, 2)
     * </code>
     *
     * The path `a` refers to the path's first result.
     * By adding the reference operator, the path `~a` refers the Add multifunction
     * This method ensure that identifiers return the correct value.
     * @return the first value of the results.
     */
    public SFunc getResolvedResult(){
        if(isIdent()){
            SIdent id = (SIdent) this;
            if (id.isReference()) {
                return getOutput(0);
            } else {

                return id.getOutput(0).getResult();
            }
        }
        return getOutput(0);
    }

    /** {@inheritDoc} */
    @Override
    public SFunc get(PathSegment segment) {
        if (segment.getType().isInput()){
            if(segment.getKey().isPresent()){
                return getInput(segment.getKey().get());
            }else if (segment.getIndex().isPresent()){
                return getInput(segment.getIndex().get());
            }
        }else if(segment.getType().isOutput() || segment.getType().isSimple()){
            if(segment.getKey().isPresent()){
                return getOutput(segment.getKey().get());
            }else if (segment.getIndex().isPresent()){
                return getOutput(segment.getIndex().get());
            }
        }

        return null;
    }

    @Override
    public SFunc getResult() {
       return getOutput(0);
    }

    private static boolean isPrimitive(SIdent id){
        SFunc result = id.getOutput(0);
        return result.getSymbolType().isLiteral() || (!result.hasInputs() && result.getSymbolType().isPort());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SFunc getOutput(Integer i) {
        if(i >= 0 || i < results.size() ){
            return results.get(i).getValue();
        }else{
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SFunc getOutput(String name) {
        if(results.hasKey(name)){
            return results.get(name).getValue();
        }else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SFunc> getOutputs() {
        return results.values.stream().map(TypedValue::getValue).collect(toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Set<String>> getOutputTypes() {
        return results.values.stream().map(TypedValue::getAcceptedTypes).collect(toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getOutputType(int resultIndex) {
        return results.get(resultIndex).getAcceptedTypes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getOutputType(String resultName) {
        return results.get(resultName).getAcceptedTypes();
    }

    /**
     {@inheritDoc}
    **/
    @Override
    public void setOutput(String name, SFunc value) {
        results.get(name).setValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOutput(Integer resultIndex, SFunc value) {
        results.get(resultIndex).setValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void appendOutput(SFunc arg) {
        inputs.add(new TypedValue(arg.getType(), arg));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getOutputKeys() {
        return results.getKeys();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive(){
        return isActive.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActive(boolean b) {
        isActive.set(b);
    }

    @Override
    public BooleanProperty isActiveProperty() {
        return isActive;
    }

    @Override
    public void setInput(String name, SFunc v){
        inputs.get(name).setValue(v);
    }
    
    @Override
    public void setInput(Integer i, SFunc v){
        inputs.get(i).setValue(v);
    }

    @Override
    public void appendInput(SFunc arg) {
        inputs.add(new TypedValue(arg.getType(), arg));
    }

    private boolean isType(String t){
        return getType().equals(t);
    }
    
    @Override
    public boolean isInteger(){
        return isType(SType.INTEGER);
    }
    
    @Override
    public boolean isDouble(){
        return isType(SType.DOUBLE);
    }
    
    @Override
    public boolean isString(){
        return isType(SType.STRING);
    }
    
    @Override
    public boolean isBoolean(){
        return isType(SType.BOOLEAN);
    }

    @Override
    public boolean isIdent() {
        return isType(SType.IDENT);
    }

    @Override
    public boolean isList() {
        return isType(SType.LIST);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SymbolType getSymbolType(){
        return symbolType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSymbolType(SymbolType type){
        this.symbolType = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Access getAccess() {
        return access;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAccess(Access access) {
        this.access = access;
    }

    public StringProperty nameProperty(){
        return name;
    }

    public StringProperty fullNameProperty(){
        return fullName;
    }

    public String getFullName(){
        return fullName.get();
    }

    public void setFullName(String fullName){
        this.fullName.set(fullName);
        this.name.set(Path.getNameFromPath(fullName));
    }

    public String getName(){
        return name.get();
    }

    public void setName(String name){
        this.name.set(name);
        this.fullName.set(Path.replaceNameInPath(fullName.get(), name));
    }

    /**
     * Prints index map
     * @param values
     * @return
     */
    private String printIndexMap(SIndexedMap<TypedValue> values){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            // if there is a key associated with the index
            // prepend it
            String key = values.getKey(i);
            if (key != null) {
                sb.append(key).append(":");
            }

            SFunc func = values.get(i).getValue();
            if(func != null) {
                SymbolType type = func.getSymbolType();
                if (func == this) {
                    func.setSymbolType(SymbolType.LITERAL);
                }

                String toConsole = func.toConsole();
                func.setSymbolType(type);

                sb.append(toConsole).append(", ");
            }
        }

        if(!sb.toString().isEmpty()){
            sb.delete(sb.length() -2, sb.length() -1);
        }

        return sb.toString().trim();
    }

    public String toConsole(){
        StringBuilder sb = new StringBuilder();
        sb.append("#<")
                .append(getType())
                .append(" ")
                .append("args:[");

        // if there is name for the element
        // write it out
        sb.append(printIndexMap(inputs)).append("], ");

        sb.append("results:[");

        sb.append(printIndexMap(results)).append("]>");
        return sb.toString();
    }
}
