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
 * Represent a JavaFX Group in Shiro
 */
public class SGroup extends SFuncBase{
    public SGroup(){
        inputs.setKeyForIndex("originX", 0);
        inputs.add(TypedValue.asDouble());

        inputs.setKeyForIndex("originY", 1);
        inputs.add(TypedValue.asDouble());

        inputs.setKeyForIndex("rotate", 2);
        inputs.add(TypedValue.asDouble());

        inputs.setKeyForIndex("children", 3);
        inputs.add(new TypedValue("List"));

        results.add(new TypedValue(getType()));
    }

    @Override
    public void evaluate() {
    }

    @Override
    public int getMaxArgs() {
        return 4;
    }

    @Override
    public int getMinArgs() {
        return 4;
    }

    @Override
    public String getType() {
        return "Group";
    }
}
