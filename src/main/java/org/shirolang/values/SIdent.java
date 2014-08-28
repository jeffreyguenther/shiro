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

package org.shirolang.values;

import java.util.Collections;
import java.util.List;
import org.shirolang.SFunc;
import org.shirolang.SFuncBase;
import org.shirolang.Scope;

/**
 * Defines an identifier points to. This
 * is a functional representation of the identifier.
 * 
 * When it is evaluated it resolves its path object to
 * a SFunc.
 * @author jeffreyguenther
 */
public class SIdent extends SFuncBase{
    private Scope scope;
    private Path value;
    
    public SIdent(){
        this(null, "");
    }
    
    public SIdent(Scope scope, String path){
        this(scope, Path.create(path));
    }
    
    public SIdent(Scope scope, Path s) {
        super();
        this.scope = scope;
        value = s;
    }
    
    public void setValue(Path s){
        value = s;
    }
    
    /**
     * Sets the value of the identifier to
     * path represented by the string. The
     * string is converted into path object
     * before being stored.
     * @param path path the identifier points to
     */
    public void setValue(String path){
        value = Path.create(path);
    }
    
    public void setScope(Scope s){
        this.scope = s;
    }
    
    @Override
    public void evaluate() {
        results.set(scope.resolvePath(value));
    }

    @Override
    public List<SFunc> getDependencies() {
        return Collections.singletonList(scope.resolvePath(value));
    }

    @Override
    public String getType() {
        return "Ident";
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int getMaxArgs() {
        return 0;
    }

    @Override
    public int getMinArgs() {
        return 0;
    }

    public boolean isReference(){
        return value.isReference();
    }

    public boolean isSelector(){
        return value.isSelector();
    }
}
