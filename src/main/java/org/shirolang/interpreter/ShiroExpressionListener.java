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

package org.shirolang.interpreter;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.shirolang.SFunc;
import org.shirolang.functions.math.SEqual;
import org.shirolang.functions.math.SNotEqual;
import org.shirolang.values.SDouble;
import org.shirolang.values.SInteger;

/**
 * An listener to create expressions.
 * @author jeffreyguenther
 */
public class ShiroExpressionListener extends ShiroBaseListener{
    protected ParseTreeProperty<SFunc> expressions;
    private final List<SFunc> exprs;

    public ShiroExpressionListener() {
        this.expressions = new ParseTreeProperty<>();
        exprs = new ArrayList<>();
    }
    
    /**
     * Get an expression for a parse tree node
     * @param node parse tree node to lookup
     * @return expression stored for that parse tree node
     */
    public SFunc getExpr(ParseTree node){
        if (expressions.get(node) == null) {
            return expressions.get(node.getChild(0));
        }
        return expressions.get(node);
    }
    
    /**
     * Store the tree of multi-functions associated with the parse tree
     * @param node parse tree node to associate expresssion with
     * @param expr expression to associate with parse tree node
     */
    protected void setExpr(ParseTree node, SFunc expr){
        expressions.put(node, expr);
        exprs.add(expr);
    }
    
    public SFunc getRoot(){
        return exprs.get(exprs.size() - 1);
    }
    
    public List<SFunc> getExprs(){
        return exprs;
    }

    @Override
    public void exitNumExpr(ShiroParser.NumExprContext ctx) {
        String number = ctx.NUMBER().getText();
        
        SFunc expr;
        // Detect if the number is a double
        if(number.contains(".")){
            expr = new SDouble(Double.parseDouble(number));
        }else{
            expr = new SInteger(Integer.parseInt(number));
        }
        
        setExpr(ctx, expr);
    }

    @Override
    public void exitEqualityExpr(ShiroParser.EqualityExprContext ctx) {
        String operator = ctx.getChild(1).getText();
        SFunc op1 = getExpr(ctx.expr(0));
        SFunc op2 = getExpr(ctx.expr(1));
        
        switch(operator){
            case "==":
                SEqual eq = new SEqual(op1, op2);
                setExpr(ctx, eq);
                break;
            case "!=":
                SNotEqual neq = new SNotEqual(op1, op2);
                setExpr(ctx, neq);
                break;
        }
    }
}
