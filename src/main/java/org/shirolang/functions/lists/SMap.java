/**
 *  The MIT License
 *
 * Copyright (c) 2012 - 2015 Jeffrey Guenther.
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

package org.shirolang.functions.lists;

import org.shirolang.base.*;
import org.shirolang.interpreter.Library;
import org.shirolang.values.SIdent;
import org.shirolang.values.SList;
import org.shirolang.values.SReference;

/**
 * Represents a multi-function to map a list of values
 */
public class SMap extends SFuncBase{
    private Library library;

    public SMap(){
        super();
        inputs.add(new TypedValue(SType.LIST));
        inputs.setKeyForIndex("list", 0);

        inputs.add(new TypedValue(SType.REFERENCE));
        inputs.setKeyForIndex("type", 1);

        inputs.add(new TypedValue(SType.SELECTOR));
        inputs.setKeyForIndex("inputSelector", 2);

        inputs.add(new TypedValue(SType.SELECTOR));
        inputs.setKeyForIndex("outputSelector", 3);

        results.add(new TypedValue(SType.LIST));
    }

    @Override
    public void evaluate() {
        SList list = (SList) getInput(0).getResult();
        SFunc tempRef = getInput(1).getResult();
        SIdent inputSelector = (SIdent) getInput(2).getResult();
        SIdent outputSelector = (SIdent) getInput(3).getResult();

        String inputName = inputSelector.getPath().getSegments().get(0).getKey().get();
        String outputName = outputSelector.getPath().getSegments().get(0).getKey().get();
        SGraph g = new SGraph();

        SList output = new SList();
        if(tempRef.getSymbolType().isLiteral()){
            SReference ref = (SReference) tempRef;
            String type = ref.getValue();

            SFunc node = library.instantiateNode(g, type, "internal");
            g.addNode((SNode) node);

            // for each function in the list
            for(SFunc elem: list.getValue()) {
                // set the input specified by the input selector
                SFunc input = node.getInput(inputName);
                input.setInput(0, elem);

                // evaluate the node
                g.evaluate();
                // get the output specified by the output selector
                SFunc outputValue = node.getOutput(outputName);
                // save it as an element in a new list
                output.appendInput(outputValue.getResult());
            }
        }

        if (tempRef.getSymbolType().isIdent()){
            SFunc localfunction = tempRef.getResult();
            // for each function in the list
            for(SFunc elem: list.getValue()){
                // set the input specified by the input selector

                localfunction.evaluate();
                // get the output specified by the output selector

            }

        }
        output.evaluate();
        setOutput(0, output);
    }

    @Override
    public int getMinArgs() {
        return 4;
    }

    @Override
    public int getMaxArgs() {
        return 4;
    }

    @Override
    public String getType() {
        return SType.MAP;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
