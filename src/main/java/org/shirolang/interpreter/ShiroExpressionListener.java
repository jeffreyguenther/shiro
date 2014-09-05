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
import java.util.Stack;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.shirolang.base.SFunc;
import org.shirolang.base.Scope;
import org.shirolang.ShiroRuntime;
import org.shirolang.functions.math.SAdd;
import org.shirolang.functions.math.SAnd;
import org.shirolang.functions.math.SDivide;
import org.shirolang.functions.math.SEqual;
import org.shirolang.functions.math.SGreaterThan;
import org.shirolang.functions.math.SGreaterThanOrEqual;
import org.shirolang.functions.math.SLessThan;
import org.shirolang.functions.math.SLessThanOrEqual;
import org.shirolang.functions.math.SModulo;
import org.shirolang.functions.math.SMultiply;
import org.shirolang.functions.math.SNegative;
import org.shirolang.functions.math.SNot;
import org.shirolang.functions.math.SNotEqual;
import org.shirolang.functions.math.SOr;
import org.shirolang.functions.math.SSubtract;
import org.shirolang.values.Path;
import org.shirolang.values.SBoolean;
import org.shirolang.values.SDouble;
import org.shirolang.values.SIdent;
import org.shirolang.values.SInteger;
import org.shirolang.values.SString;

/**
 * An listener to create expressions.
 *
 * @author jeffreyguenther
 */
public class ShiroExpressionListener extends ShiroBaseListener {

    protected Stack<Scope> scope;
    protected ParseTreeProperty<SFunc> expressions;
    private final List<SFunc> exprs;
    private final ShiroRuntime rt;

    public ShiroExpressionListener(ShiroRuntime rt) {
        this.expressions = new ParseTreeProperty<>();
        exprs = new ArrayList<>();
        scope = new Stack<>();
        scope.push(rt);
        this.rt = rt;
    }

    /**
     * Get an expression for a parse tree node
     *
     * @param node parse tree node to lookup
     * @return expression stored for that parse tree node
     */
    public SFunc getExpr(ParseTree node) {
        if (expressions.get(node) == null) {
            return expressions.get(node.getChild(0));
        }
        return expressions.get(node);
    }

    /**
     * Store the tree of multi-functions associated with the parse tree
     *
     * @param node parse tree node to associate expresssion with
     * @param expr expression to associate with parse tree node
     */
    protected void setExpr(ParseTree node, SFunc expr) {
        expressions.put(node, expr);
        exprs.add(expr);
    }

    public SFunc getRoot() {
        return exprs.get(exprs.size() - 1);
    }

    public List<SFunc> getExprs() {
        return exprs;
    }

    @Override
    public void exitNumExpr(ShiroParser.NumExprContext ctx) {
        String number = ctx.NUMBER().getText();

        SFunc expr;
        // Detect if the number is a double
        if (number.contains(".")) {
            expr = new SDouble(Double.parseDouble(number));
        } else {
            expr = new SInteger(Integer.parseInt(number));
        }

        setExpr(ctx, expr);
    }

    @Override
    public void exitBoolExpr(ShiroParser.BoolExprContext ctx) {
        String value = ctx.BOOLEAN_LITERAL().getText();
        
        SBoolean bool = new SBoolean(Boolean.parseBoolean(value));
        setExpr(ctx, bool);
    }

    @Override
    public void exitStringExpr(ShiroParser.StringExprContext ctx) {
        String literalString = ctx.STRING_LITERAL().getText();
        // Remove the quotes from around the string literal
        SString s = new SString(literalString.substring(1, literalString.length() -1));
        setExpr(ctx, s);
    }
    
    protected SFunc createPath(Scope currentScope, ShiroParser.PathContext ctx) {
        // Declare a list to store the path's parts
        List<String> parts = new ArrayList<>();

        Path p;

        // Convert the tree nodes into strings and add to list
        for (TerminalNode t : ctx.IDENT()) {
            parts.add(t.getText());
        }

        // prepend this, when used
        if (ctx.THIS() != null) {
            parts.add(0, "this");
        }

        if (ctx.pathIndex() != null) {
            ShiroParser.PathIndexContext pathIndex = ctx.pathIndex();

            if (pathIndex.index.getType() == ShiroParser.NUMBER) {
                int i = Integer.parseInt(pathIndex.NUMBER().getText());
                p = new Path(parts, i);
            } else {
                String key = pathIndex.STRING_LITERAL().getText().replace("\"", "");
                p = new Path(parts, key);
            }

        } else {
            p = new Path(parts);
        }

        if(ctx.REF() != null){
            p.makeReference();
        }

        if(ctx.SELECT() != null){
            p.makeSelector();
        }

        return new SIdent(currentScope, p);
    }

    @Override
    public void enterPath(ShiroParser.PathContext ctx) {
        setExpr(ctx, createPath(scope.peek(), ctx));
    }

    @Override
    public void exitEqualityExpr(ShiroParser.EqualityExprContext ctx) {
        String operator = ctx.getChild(1).getText();
        SFunc op1 = getExpr(ctx.expr(0));
        SFunc op2 = getExpr(ctx.expr(1));

        switch (operator) {
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

    @Override
    public void exitComparisonExpr(ShiroParser.ComparisonExprContext ctx) {
        String operator = ctx.getChild(1).getText();
        SFunc op1 = getExpr(ctx.expr(0));
        SFunc op2 = getExpr(ctx.expr(1));
        
        switch(operator){
            case "<":
                SLessThan lt = new SLessThan(op1, op2);
                setExpr(ctx, lt);
                break;
            case "<=":
                SLessThanOrEqual lte = new SLessThanOrEqual(op1, op2);
                setExpr(ctx, lte);
                break;
            case ">":
                SGreaterThan gt = new SGreaterThan(op1, op2);
                setExpr(ctx, gt);
                break;
            case ">=":
                SGreaterThanOrEqual gte = new SGreaterThanOrEqual(op1, op2);
                setExpr(ctx, gte);
                break;
        }
    }

    @Override
    public void exitAddExpr(ShiroParser.AddExprContext ctx) {
        String operator = ctx.getChild(1).getText();
        SFunc op1 = getExpr(ctx.expr(0));
        SFunc op2 = getExpr(ctx.expr(1));
        
        switch(operator){
            case "+":
                SAdd add = new SAdd(op1, op2);
                setExpr(ctx, add);
                break;
            case "-":
                SSubtract minus = new SSubtract(op1, op2);
                setExpr(ctx, minus);
                break;
        }
    }

    @Override
    public void exitMultExpr(ShiroParser.MultExprContext ctx) {
        String operator = ctx.getChild(1).getText();
        SFunc op1 = getExpr(ctx.expr(0));
        SFunc op2 = getExpr(ctx.expr(1));
        
        switch(operator){
            case "*":
                SMultiply mult = new SMultiply(op1, op2);
                setExpr(ctx, mult);
                break;
            case "/":
                SDivide div = new SDivide(op1, op2);
                setExpr(ctx, div);
                break;
            case "%":
                SModulo mod = new SModulo(op1, op2);
                setExpr(ctx, mod);
                break;
        }
    }

    @Override
    public void exitAndExpr(ShiroParser.AndExprContext ctx) {
        SFunc op1 = getExpr(ctx.expr(0));
        SFunc op2 = getExpr(ctx.expr(1));
        
        SAnd and = new SAnd(op1, op2);
        setExpr(ctx, and);
    }

    @Override
    public void exitOrExpr(ShiroParser.OrExprContext ctx) {
        SFunc op1 = getExpr(ctx.expr(0));
        SFunc op2 = getExpr(ctx.expr(1));
        
        SOr or = new SOr(op1, op2);
        setExpr(ctx, or);
    }

    @Override
    public void exitNotExpr(ShiroParser.NotExprContext ctx) {
        SFunc op1 = getExpr(ctx.expr());
        
        SNot not = new SNot(op1);
        setExpr(ctx, not);
    }

    @Override
    public void exitParensExpr(ShiroParser.ParensExprContext ctx) {
        setExpr(ctx, getExpr(ctx.expr()));
    }

    @Override
    public void exitNegExpr(ShiroParser.NegExprContext ctx) {
        SFunc op1 = getExpr(ctx.expr());
        SNegative negate = new SNegative(op1);
        setExpr(ctx, negate);
    }

    @Override
    public void exitPortDeclInit(ShiroParser.PortDeclInitContext ctx) {
        System.out.println("entering decl init");
        String portName = ctx.portName().IDENT().getText();
        String portType = ctx.portType().getText();
        String mfName = ctx.mfCall().mfName().getText();
        List<ShiroParser.ExprContext> args = ctx.mfCall().mfparams().expr();
        
        // get the type name
        SFunc function = rt.createFunction(mfName);
        if(function == null){
            throw new RuntimeException("A multifunction by the name " + mfName
            + "does not exist.");
        }
        
        // if the function is not one of the literals (number, string, etc.)
        if (!function.getSymbolType().isLiteral() && args.size() > 1) {
            // check to see if the right number of exprs came back
            if (args.size() < function.getMinArgs()) {
                throw new RuntimeException("Expected at least " + function.getMinArgs()
                        + " arguments to be provided.");
            }

            if (args.size() > function.getMaxArgs()) {
                throw new RuntimeException("Expected at most " + function.getMinArgs()
                        + " arguments to be provided.");
            }
            
            // todo add argument type checking

            // append the args to the function
            for (ShiroParser.ExprContext arg : args) {
                SFunc exp = getExpr(arg);
                function.appendArg(exp);
            }
            setExpr(ctx, function);
        }else{
            function = getExpr(args.get(0));
            
            if(!function.getType().equals(mfName)){
                throw new RuntimeException("Literal expression is " 
                        + function.getType() + ", but should be " + mfName);
            }
        }
        
        // add the symbol to the runtime
        rt.addSymbol(portName, function);
    }
}
