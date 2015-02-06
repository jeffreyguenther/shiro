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

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;
import org.shirolang.values.SDouble;
import org.shirolang.values.SString;

/**
 * Represents a block of text on the canvas
 */
public class SText extends SFuncBase {
    public SText(){
        super();

        args.setKeyForIndex("originX", 0);
        args.add(new TypedValue("Double"));

        args.setKeyForIndex("originY", 1);
        args.add(new TypedValue("Double"));

        args.setKeyForIndex("rotate", 2);
        args.add(new TypedValue("Double"));

        args.setKeyForIndex("text", 3);
        args.add(new TypedValue("String"));

        args.setKeyForIndex("font", 4);
        args.add(new TypedValue("String"));

        args.setKeyForIndex("size", 5);
        args.add(new TypedValue("Double"));

        args.setKeyForIndex("weight", 6);
        args.add(new TypedValue("String"));

        results.add(new TypedValue(getType()));
    }

    @Override
    public void evaluate() {
    }

    @Override
    public int getMaxArgs() {
        return 7;
    }

    @Override
    public int getMinArgs() {
        return 7;
    }

    @Override
    public String getType() {
        return "Text";
    }
}
