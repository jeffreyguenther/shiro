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

package org.shirolang.playground;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.shirolang.interpreter.ShiroBaseListener;
import org.shirolang.interpreter.ShiroParser;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Computes the the style class and segment for Shiro code.
 */
public class SyntaxHighlighter extends ShiroBaseListener{
    StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
    int lastEnd = 0;
    int textlength;

	public SyntaxHighlighter(int l) {
        textlength = l;
    }

    @Override
    public void enterShiro(@NotNull ShiroParser.ShiroContext ctx) {
        lastEnd = 0;
    }

    @Override
    public void enterPortName(@NotNull ShiroParser.PortNameContext ctx) {
        add(ctx.IDENT(), "ident");
    }

    @Override
    public void enterFullyQualifiedType(ShiroParser.FullyQualifiedTypeContext ctx) {
        add(ctx.MFNAME(), "mf");
    }

    @Override
    public void enterNodeDecl(ShiroParser.NodeDeclContext ctx) {
        add(ctx.MFNAME(), "node-type");
        add(ctx.BEGIN(), "begin-end");
    }

    @Override
    public void exitNodeDecl(ShiroParser.NodeDeclContext ctx) {
        add(ctx.END(), "begin-end");
    }

    /**
	 * @return the styles
	 */
	public StyleSpans<Collection<String>> getStyles() {
        spansBuilder.add(Collections.emptyList(), textlength - lastEnd);
		return spansBuilder.create();
	}

    private void add(List<TerminalNode> symbols, String style){
        symbols.stream().forEach(terminalNode -> add(terminalNode, style));
    }

	private void add(TerminalNode ident, String style) {
        if(ident != null) {
            Token t = ident.getSymbol();

            int spacer = t.getStartIndex() - lastEnd;
            if(spacer > 0) {
                spansBuilder.add(Collections.emptyList(), spacer);

                int gap = t.getText().length();
                spansBuilder.add(Collections.singleton(style), gap);
                lastEnd = t.getStopIndex() + 1;
            }
        }
	}
}
