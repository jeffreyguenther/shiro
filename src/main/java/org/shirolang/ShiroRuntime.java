/*
 * The MIT License
 *
 * Copyright (c) 2012 - 2014 Jeffrey Guenther.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.shirolang;

import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.shirolang.interpreter.ShiroExpressionListener;
import org.shirolang.interpreter.ShiroLexer;
import org.shirolang.interpreter.ShiroParser;

/**
 *
 * @author jeffreyguenther
 */
public class ShiroRuntime implements Scope{
    private Map<String, SFuncBase> symbols;

    public ShiroRuntime() {
        symbols = new HashMap<>();
    }
    
    public void addSymbol(String s, SFuncBase v){
        symbols.put(s, v);
    }

    @Override
    public SFuncBase resolvePath(String s) {
        return symbols.get(s);
    }

    public SFunc executedExpr(String expr) {
        ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(expr));
        ShiroParser parser = new ShiroParser(new CommonTokenStream(lex));
        ParseTree tree = parser.expr();
        
        ParseTreeWalker walker = new ParseTreeWalker();
        ShiroExpressionListener expression = new ShiroExpressionListener();
        walker.walk(expression, tree);
        
        return expression.getExpr(tree);
    }
}
