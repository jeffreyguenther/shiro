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

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.shirolang.functions.geometry.SText;
import org.shirolang.values.SDouble;
import org.shirolang.values.SString;

/**
 *
 */
public class TextViz extends Text {
    public TextViz(SText t){
        super();

        SDouble originX = (SDouble) t.getInput("originX").getResult();
        setX(originX.getValue());

        SDouble originY = (SDouble) t.getInput("originY").getResult();
        setY(originY.getValue());

        SDouble rotate = (SDouble) t.getInput("rotate").getResult();
        setRotate(rotate.getValue());

        SString text = (SString) t.getInput("text").getResult();
        setText(text.getValue());

        SString font = (SString) t.getInput("font").getResult();
        SDouble size = (SDouble) t.getInput("size").getResult();
        SString weight = (SString) t.getInput("weight").getResult();

        setFont(Font.font(font.getValue(), FontWeight.findByName(weight.getValue()), size.getValue()));

        System.out.println("Setting up a text element.");
    }
}
