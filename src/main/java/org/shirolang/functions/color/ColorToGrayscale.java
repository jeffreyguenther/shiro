/*
 * Copyright (c) 2012-2014 Jeffrey Guenther
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software  and associated documentation files (the
 * "Software"), to deal in the Software without restriction,  including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute,  sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT  NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.shirolang.functions.color;

import javafx.scene.paint.Color;
import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;

/**
 * Converts a Shiro color to grayscale
 */
public class ColorToGrayscale extends SFuncBase{
    public ColorToGrayscale(){
        inputs.setKeyForIndex("color", 0);
        inputs.add(new TypedValue("Color"));;

        results.setKeyForIndex("grey", 0);
        results.add(new TypedValue("Color"));
    }

    @Override
    public void evaluate() {
        SColor color = (SColor) getInput("color").getResult();

        if( !doesExpectedTypeMatch(0, color)){
            throw new RuntimeException("Arg:0, Found " + color.getType() + ". Expected " + String.join(",", getAcceptedTypes(0)));
        }

        Color gray = color.getValue().grayscale();
        SColor asGrey = new SColor(gray);
        asGrey.evaluate();
        setOutput(0, asGrey);
    }

    @Override
    public int getMaxArgs() {
        return 1;
    }

    @Override
    public int getMinArgs() {
        return 1;
    }

    @Override
    public String getType() {
        return "ColorToGrayscale";
    }
}
