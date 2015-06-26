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

import javafx.scene.shape.Ellipse;
import org.shirolang.functions.color.SColor;
import org.shirolang.functions.geometry.SEllipse;
import org.shirolang.values.SDouble;

/**
 *
 */
public class EllipseViz extends Ellipse {
    public EllipseViz(SEllipse ellipse){
        SDouble originX = (SDouble) ellipse.getInput("centerX").getResult();
        setCenterX(originX.getValue());

        SDouble originY = (SDouble) ellipse.getInput("centerY").getResult();
        setCenterY(originY.getValue());

        SDouble rotate = (SDouble) ellipse.getInput("rotate").getResult();
        setRotate(rotate.getValue());

        SDouble radiusX = (SDouble) ellipse.getInput("radiusX").getResult();
        setRadiusX(radiusX.getValue());

        SDouble radiusY = (SDouble) ellipse.getInput("radiusY").getResult();
        setRadiusY(radiusY.getValue());

        SColor stroke = (SColor) ellipse.getInput("stroke").getResult();
        setStroke(stroke.getValue());

        SDouble strokeWeight = (SDouble) ellipse.getInput("strokeWeight").getResult();
        setStrokeWidth(strokeWeight.getValue());

        SColor fill = (SColor) ellipse.getInput("fill").getResult();
        setFill(fill.getValue());
    }
}
