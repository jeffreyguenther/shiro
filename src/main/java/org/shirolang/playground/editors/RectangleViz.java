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

package org.shirolang.playground.editors;

import javafx.scene.shape.Rectangle;
import org.shirolang.functions.color.SColor;
import org.shirolang.functions.geometry.SRectangle;
import org.shirolang.values.SDouble;

/**
 *
 */
public class RectangleViz extends Rectangle {
    public RectangleViz(SRectangle rect ){
        super();

        SDouble originX = (SDouble) rect.getArg("originX").getResult();
        setX(originX.getValue());

        SDouble originY = (SDouble) rect.getArg("originY").getResult();
        setY(originY.getValue());

        SDouble rotate = (SDouble) rect.getArg("rotate").getResult();
        setRotate(rotate.getValue());

        SDouble width = (SDouble) rect.getArg("width").getResult();
        setWidth(width.getValue());

        SDouble height = (SDouble) rect.getArg("height").getResult();
        setHeight(height.getValue());

        SColor stroke = (SColor) rect.getArg("stroke").getResult();
        setStroke(stroke.getValue());

        SDouble strokeWeight = (SDouble) rect.getArg("strokeWeight").getResult();
        setStrokeWidth(strokeWeight.getValue());

        SColor fill = (SColor) rect.getArg("fill").getResult();
        setFill(fill.getValue());

        SDouble arcHeight = (SDouble) rect.getArg("arcHeight").getResult();
        setArcHeight(arcHeight.getValue());

        SDouble arcWidth = (SDouble) rect.getArg("arcWidth").getResult();
        setArcHeight(arcWidth.getValue());
    }
}
