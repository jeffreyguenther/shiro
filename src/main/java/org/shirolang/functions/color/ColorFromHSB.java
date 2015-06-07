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
 * Creates a color from an hsba quad
 */
public class ColorFromHSB extends SFuncBase {

    public ColorFromHSB(){
        super();
        args.setKeyForIndex("hue", 0);
        args.add(TypedValue.asDouble());

        args.setKeyForIndex("saturation", 1);
        args.add(TypedValue.asDouble());

        args.setKeyForIndex("brightness", 2);
        args.add(TypedValue.asDouble());

        args.setKeyForIndex("alpha", 3);
        args.add(TypedValue.asDouble());

        results.setKeyForIndex("color", 0);
        results.add(new TypedValue("Color"));
    }

    @Override
    public void evaluate() {
        SDouble hue = (SDouble) getArg("hue").getResult();
        SDouble sat = (SDouble) getArg("g").getResult();
        SDouble brightness = (SDouble) getArg("b").getResult();
        SDouble alpha = (SDouble) getArg("a").getResult();

        if( !doesExpectedTypeMatch(0, hue)) {
            throw new RuntimeException("Arg:0, Found " + hue.getType() + ". Expected " + String.join(",", getAcceptedTypes(0)));
        }

        if( !doesExpectedTypeMatch(1, sat)){
            throw new RuntimeException("Arg:1, Found " + sat.getType() + ". Expected " + String.join(",", getAcceptedTypes(1)));
        }

        if (!doesExpectedTypeMatch(2, brightness)){
            throw new RuntimeException("Arg:2,Found " + brightness.getType() + ". Expected " + String.join(",", getAcceptedTypes(2)));
        }

        if (!doesExpectedTypeMatch(3, alpha)){
            throw new RuntimeException("Arg:3, Found " + alpha.getType() + ". Expected " + String.join(",", getAcceptedTypes(3)));
        }


        Color result = Color.hsb(hue.getValue(), sat.getValue(), brightness.getValue(), alpha.getValue());
        SColor color = new SColor(result);
        color.evaluate();
        setResult(0, color);
    }

    @Override
    public String getType() {
        return "ColorFromHSB";
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
