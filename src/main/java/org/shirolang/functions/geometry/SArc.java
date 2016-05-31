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

package org.shirolang.functions.geometry;

import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;

/**
 * Represents an ellipse in Shiro
 */
public class SArc extends SFuncBase{
    public SArc(){
        super();

        inputs.setKeyForIndex("centerX", 0);
        inputs.add(TypedValue.asDouble());

        inputs.setKeyForIndex("centerY", 1);
        inputs.add(TypedValue.asDouble());

        inputs.setKeyForIndex("radiusX", 2);
        inputs.add(TypedValue.asDouble());

        inputs.setKeyForIndex("radiusY", 3);
        inputs.add(TypedValue.asDouble());

        inputs.setKeyForIndex("length", 4);
        inputs.add(TypedValue.asDouble());

        inputs.setKeyForIndex("startAngle", 5);
        inputs.add(TypedValue.asDouble());

        inputs.setKeyForIndex("arcType", 6);
        inputs.add(TypedValue.asString());

        inputs.setKeyForIndex("fill", 7);
        inputs.add(new TypedValue("Color"));

        inputs.setKeyForIndex("stroke", 8);
        inputs.add(new TypedValue("Color"));

        inputs.setKeyForIndex("strokeWeight", 9);
        inputs.add(new TypedValue("Double"));

        results.add(new TypedValue(getType()));
    }

    @Override
    public void evaluate() {
    }

    @Override
    public int getMaxArgs() {
        return 10;
    }

    @Override
    public int getMinArgs() {
        return 10;
    }

    @Override
    public String getType() {
        return "SArc";
    }
}
