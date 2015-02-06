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

import javafx.scene.shape.Line;
import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;

/**
 * Represents a line in Shiro
 */
public class SLine extends SFuncBase {
    public SLine(){
        super();
        args.setKeyForIndex("startX", 0);
        args.add(new TypedValue("Double"));

        args.setKeyForIndex("startY", 1);
        args.add(new TypedValue("Double"));

        args.setKeyForIndex("endX", 2);
        args.add(new TypedValue("Double"));

        args.setKeyForIndex("endY", 3);
        args.add(new TypedValue("Double"));

        args.setKeyForIndex("stroke", 4);
        args.add(new TypedValue("Color"));

        args.setKeyForIndex("strokeWeight", 5);
        args.add(new TypedValue("Double"));

        results.add(new TypedValue(getType()));
    }

    @Override
    public void evaluate() {
//        setResult(0, this);
    }

    @Override
    public int getMaxArgs() {
        return 6;
    }

    @Override
    public int getMinArgs() {
        return 6;
    }

    @Override
    public String getType() {
        return "Line";
    }
}
