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
import org.shirolang.values.SDouble;
import org.shirolang.values.SInteger;

/**
 * Creates a color from an rgba quad
 */
public class ColorFromRGB extends SFuncBase {

    public ColorFromRGB(){
        super();
        inputs.setKeyForIndex("r", 0);
        inputs.add(new TypedValue("Integer"));

        inputs.setKeyForIndex("g", 1);
        inputs.add(new TypedValue("Integer"));

        inputs.setKeyForIndex("b", 2);
        inputs.add(new TypedValue("Integer"));

        inputs.setKeyForIndex("alpha", 3);
        inputs.add(new TypedValue("Double"));

        results.setKeyForIndex("color", 0);
        results.add(new TypedValue("Color"));
    }

    @Override
    public void evaluate() {
        SInteger r = (SInteger) getInput("r").getResult();
        SInteger g = (SInteger) getInput("g").getResult();
        SInteger b = (SInteger) getInput("b").getResult();
        SDouble a = (SDouble) getInput("alpha").getResult();

        if( !doesExpectedTypeMatch(0, r)) {
            throw new RuntimeException("Arg:0, Found " + r.getType() + ". Expected " + String.join(",", getAcceptedTypes(0)));
        }

        if( !doesExpectedTypeMatch(1, g)){
            throw new RuntimeException("Arg:1, Found " + g.getType() + ". Expected " + String.join(",", getAcceptedTypes(1)));
        }

        if (!doesExpectedTypeMatch(2, b)){
            throw new RuntimeException("Arg:2,Found " + b.getType() + ". Expected " + String.join(",", getAcceptedTypes(2)));
        }

        if (!doesExpectedTypeMatch(3, a)){
            throw new RuntimeException("Arg:3, Found " + a.getType() + ". Expected " + String.join(",", getAcceptedTypes(3)));
        }

        Color result = Color.rgb(r.getValue(), g.getValue(), b.getValue(), a.getValue() );
        SColor color = new SColor(result);
        color.evaluate();
        setOutput(0, color);
    }

    @Override
    public String getType() {
        return "ColorFromRGB";
    }

    @Override
    public int getMaxArgs() {
        return 4;
    }

    @Override
    public int getMinArgs() {
        return 4;
    }
}
