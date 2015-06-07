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

import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import org.shirolang.functions.color.SColor;
import org.shirolang.functions.geometry.SArc;
import org.shirolang.functions.geometry.SEllipse;
import org.shirolang.values.SDouble;
import org.shirolang.values.SString;

/**
 *
 */
public class ArcViz extends Arc {
    public ArcViz(SArc arc){
        SDouble originX = (SDouble) arc.getArg("centerX").getResult();
        setCenterX(originX.getValue());

        SDouble originY = (SDouble) arc.getArg("centerY").getResult();
        setCenterY(originY.getValue());

        SDouble radiusX = (SDouble) arc.getArg("radiusX").getResult();
        setRadiusX(radiusX.getValue());

        SDouble radiusY = (SDouble) arc.getArg("radiusY").getResult();
        setRadiusY(radiusY.getValue());

        SDouble length = (SDouble) arc.getArg("length").getResult();
        setLength(length.getValue());

        SDouble startAngle = (SDouble) arc.getArg("startAngle").getResult();
        setStartAngle(startAngle.getValue());

        SString arcType = (SString) arc.getArg("arcType").getResult();

        switch(arcType.getValue()){
            case "open":
                setType(ArcType.OPEN);
                break;
            case "chord":
                setType(ArcType.CHORD);
                break;
            case "round":
                setType(ArcType.ROUND);
                break;
        }

        SColor file = (SColor) arc.getArg("fill").getResult();
        setFill(file.getValue());

        SColor stroke = (SColor) arc.getArg("stroke").getResult();
        setStroke(stroke.getValue());

        SDouble strokeWeight = (SDouble) arc.getArg("strokeWeight").getResult();
        setStrokeWidth(strokeWeight.getValue());
    }
}
