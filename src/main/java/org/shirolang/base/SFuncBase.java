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
import org.shirolang.values.SIdent;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * A base class to handle the common requirements
 * of implementing multi-functions.
 */
public abstract class SFuncBase implements SFunc {
    protected SIndexedMap<TypedValue> args;
    protected SIndexedMap<TypedValue> results;
    protected BooleanProperty isActive;

    protected StringProperty name;
    protected StringProperty fullName;
    protected SymbolType symbolType;

    public SFuncBase() {
        args = new SIndexedMap<>();
        results = new SIndexedMap<>();
        isActive = new SimpleBooleanProperty(true);
        name = new SimpleStringProperty("");
        fullName = new SimpleStringProperty("");
        symbolType = SymbolType.PORT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SFunc getArg() {
        return args.get(0).getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SFunc getArg(String name) {
        return args.get(name).getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SFunc getArg(Integer i) {
        return args.get(i).getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SFunc> getArgs() {
        // note the null filter. Because arguments can be stored without a value, some maybe null.
        // We need to filter these out.
        return args.values.stream().map(a -> a.getValue()).filter(Objects::nonNull).collect(toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Set<String>> getArgTypes() {
        return args.values.stream().map(a -> a.getAcceptedTypes()).collect(toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getAcceptedTypes(int argIndex) {
        return args.get(argIndex).getAcceptedTypes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getAcceptedType(String name) {
        return args.get(name).getAcceptedTypes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doesExpectedTypeMatch(int argIndex, SFunc value) {
        return args.get(argIndex).doesTypeMatch(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doesExpectedTypeMatch(String argName, SFunc value) {
        return args.get(argName).doesTypeMatch(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getArgKeys() {
        return args.getKeys();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasArgs() {
        return !args.values.stream().map(a -> a.getValue()).anyMatch(Objects::isNull);
//        return !args.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SFunc> getDependencies() {
        return getArgs();
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
    @Override
    public SFunc getResult() {
        if(isIdent()){
            SIdent id = (SIdent) this;
            if (id.isReference()) {
                return getResult(0);
            } else {

                return id.getResult(0).getResult();
            }
        }
        return getResult(0);
    }

    private boolean isPrimitive(SIdent id){
        SFunc result = id.getResult(0);
        if(result.getSymbolType().isLiteral() || (!result.hasArgs() && result.getSymbolType().isPort())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SFunc getResult(Integer i) {
        return results.get(i).getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SFunc getResult(String name) {
        return results.get(name).getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SFunc> getResults() {
        return results.values.stream().map(r -> r.getValue()).collect(toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Set<String>> getResultTypes() {
        return results.values.stream().map(r -> r.getAcceptedTypes()).collect(toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getReturnedType(int resultIndex) {
        return results.get(resultIndex).getAcceptedTypes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getReturnedType(String resultName) {
        return results.get(resultName).getAcceptedTypes();
    }

    /**
     {@inheritDoc}
    **/
    @Override
    public void setResult(String name, SFunc value) {
        results.get(name).setValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResult(Integer resultIndex, SFunc value) {
        results.get(resultIndex).setValue(value);
    }

    @Override
    public List<String> getResultKeys() {
        return results.getKeys();
    }
    
    @Override
    public boolean isActive(){
        return isActive.get();
    }

    @Override
    public void setActive(boolean b) {
        isActive.set(b);
    }

    @Override
    public BooleanProperty isActiveProperty() {
        return isActive;
    }

    @Override
    public void setArg(String name, SFunc v){
        args.get(name).setValue(v);
    }
    
    @Override
    public void setArg(Integer i, SFunc v){
        args.get(i).setValue(v);
    }

    @Override
    public void appendArg(SFunc arg) {
        args.add(new TypedValue(arg.getType(), arg));
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
    public SymbolType getSymbolType(){
        return symbolType;
    }

    @Override
    public void setSymbolType(SymbolType type){
        this.symbolType = type;
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
            SymbolType type = func.getSymbolType();
            if(func == this){
                func.setSymbolType(SymbolType.LITERAL);
            }

            String toConsole = func.toConsole();
            func.setSymbolType(type);

            sb.append(toConsole).append(", ");
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
        sb.append(printIndexMap(args)).append("], ");

        sb.append("results:[");

        sb.append(printIndexMap(results)).append("]>");
        return sb.toString();
    }
}
