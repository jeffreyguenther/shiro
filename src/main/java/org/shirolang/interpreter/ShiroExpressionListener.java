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

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.shirolang.base.*;
import org.shirolang.functions.math.*;
import org.shirolang.values.*;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

import static java.util.stream.Collectors.toList;

/**
 * An listener to create expressions.
 *
 * @author jeffreyguenther
 */
public class ShiroExpressionListener extends ShiroBaseListener {

    protected Stack<Scope> scope;
    protected ParseTreeProperty<SFunc> expressions;
    protected final Library library;

    public ShiroExpressionListener(Library lib) {
        this.expressions = new ParseTreeProperty<>();
        scope = new Stack<>();
        this.library = lib;
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

        // store the value on the arg
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
        String value = literalString.substring(1, literalString.length() - 1);
        SString s = new SString(value);
        setExpr(ctx, s);
    }
    
    protected SFunc createPath(Scope currentScope, ShiroParser.PathContext ctx) {
        Path p = new Path();
        ctx.segments.stream().map(this::createSegment).forEach(p::addSegment);

        if(ctx.REF() != null){
            p.makeReference();
        }

        if(ctx.SELECT() != null){
            p.makeSelector();
        }

        return new SIdent(currentScope, p);
    }

    private PathSegment createSegment(ShiroParser.PathSegmentContext ctx){
        if(ctx.IDENT() != null){
            return new PathSegment(ctx.IDENT().getText());
        }

        if(ctx.INPUTS() != null){
            ShiroParser.PathIndexContext pathIndex = ctx.pathIndex();
            if(pathIndex.index.getType() == ShiroParser.NUMBER){
                int i = Integer.parseInt(pathIndex.NUMBER().getText());
                return new PathSegment(SegmentType.INPUT, i);
            }else if( pathIndex.index.getType() == ShiroParser.STRING_LITERAL){
                String key = pathIndex.STRING_LITERAL().getText().replace("\"", "");
                return new PathSegment(SegmentType.INPUT, key);
            }
        }

        if(ctx.OUTPUTS() != null){
            ShiroParser.PathIndexContext pathIndex = ctx.pathIndex();
            if(pathIndex.index.getType() == ShiroParser.NUMBER){
                int i = Integer.parseInt(pathIndex.NUMBER().getText());
                return new PathSegment(SegmentType.OUTPUT, i);
            }else if( pathIndex.index.getType() == ShiroParser.STRING_LITERAL){
                String key = pathIndex.STRING_LITERAL().getText().replace("\"", "");
                return new PathSegment(SegmentType.OUTPUT, key);
            }
        }

        return null; // should never reach here.
    }

    @Override
    public void enterPath(@NotNull ShiroParser.PathContext ctx) {
        setExpr(ctx, createPath(scope.peek(), ctx));
    }

    @Override
    public void enterFullyQualifiedType(ShiroParser.FullyQualifiedTypeContext ctx) {
        setExpr(ctx, createFullyQualifiedType(ctx));
    }

    private SFunc createFullyQualifiedType(ShiroParser.FullyQualifiedTypeContext ctx) {
        String type = String.join(".", ctx.types.stream().map(Token::getText).collect(toList()));
        return new SReference(type);
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
    public void exitPortDecl(@NotNull ShiroParser.PortDeclContext ctx) {
        String portName = ctx.portName().IDENT().getText();
        String portType = Objects.isNull(ctx.portType())? "" : ctx.portType().getText();
        String mfName = ctx.MFNAME().getText();

        // get the type name
        SFunc function = library.createFunction(mfName);
        if(function == null){
            throw new RuntimeException("A multifunction by the name " + mfName
                    + "does not exist.");
        }
        function.setName(portName);
        function.setSymbolType(SymbolType.PORT);
        function.setAccess(determineAccess(portType));
        setExpr(ctx, function);
    }

    @Override
    public void exitPortDeclInit(@NotNull ShiroParser.PortDeclInitContext ctx) {
        String portName = ctx.portName().IDENT().getText();
        String portType = Objects.isNull(ctx.portType())? "" : ctx.portType().getText();
        String mfName = ctx.mfCall().mfName().getText();
        
        // get the type name
        SFunc function = library.createFunction(mfName);
        if(function == null){
            throw new RuntimeException("A multifunction by the name " + mfName
            + "does not exist.");
        }
        function.setSymbolType(SymbolType.PORT);
        function.setAccess(determineAccess(portType));
        function.setName(portName);

        ShiroParser.ArgumentsContext arguments = ctx.mfCall().arguments();
        function = setArgumentsInDecl(function, arguments);

        setExpr(ctx, function);
    }

    protected SFunc setArgumentsInDecl(SFunc function, ShiroParser.ArgumentsContext arguments) {
        SFunc withArgs = null;
        if(Objects.nonNull(arguments.argMap())){
            withArgs = setArgsFromMap(function, arguments.argMap());
        }else if(Objects.nonNull(arguments.argList())){
            withArgs = setArgsFromList(function, arguments.argList());
        }
        return withArgs;
    }

    protected SFunc assignArguments(SFunc function, ShiroParser.ArgumentsContext arguments) {
        SFunc withArgs = null;
        if(Objects.nonNull(arguments.argMap())){
            withArgs = setArgsFromMap(function, arguments.argMap());
        }else if(Objects.nonNull(arguments.argList())) {
            withArgs = assignArgsFromList(function, arguments.argList());
        }
        return withArgs;
    }

    protected SFunc setArgList(SFunc function, List<ParseTree> args){
        // check to see if the right number of exprs came back
        if (args.size() < function.getMinArgs()) {
            throw new RuntimeException(function.getFullName() + ":" + function.getType() + " expected at least " + function.getMinArgs()
                    + " arguments to be provided.");
        }

        if (args.size() > function.getMaxArgs()) {
            throw new RuntimeException(function.getFullName() + ":" + function.getType() + " expected at most " + function.getMinArgs()
                    + " arguments to be provided.");
        }

        // append the inputs to the function
        for (int i = 0; i < args.size(); i++) {
            SFunc exp = getExpr(args.get(i));
            function.setInput(i, exp);
        }

        return function;
    }


    protected SFunc setArgsFromList(SFunc function, ShiroParser.ArgListContext ctx){
        List<ParseTree> args = ctx.arg().stream().map(argContext -> argContext.getChild(0)).collect(toList());

        SFunc arg0 = getExpr(args.get(0));
        if(args.size() == 1 && arg0.getType().equals(function.getType())){
            return arg0;
        }else{
            return setArgList(function, args);
        }
    }

    protected SFunc assignArgsFromList(SFunc function, ShiroParser.ArgListContext ctx){
        List<ParseTree> args = ctx.arg().stream().map(argContext -> argContext.getChild(0)).collect(toList());
        return setArgList(function, args);
    }

    protected SFunc setArgsFromMap(SFunc function, ShiroParser.ArgMapContext ctx){
        List<Token> keys = ctx.keys;
        List<ParseTree> values = ctx.values.stream().map(argContext -> argContext.getChild(0)).collect(toList());

        for(int i = 0; i < keys.size(); i++){
            function.setInput(keys.get(i).getText(), getExpr(values.get(i)));
        }
        return function;
    }

    protected SNode instantiateNode(String fullyQualfiedType, ShiroParser.ArgumentsContext assignment, String nodeName, SGraph g) {
        SNode producedNode = (SNode) library.instantiateNode(g, fullyQualfiedType, nodeName);

        if(assignment != null){
            if( assignment.argMap() != null ){
                List<Token> keys = assignment.argMap().keys;
                List<ParseTree> values = assignment.argMap().values.stream()
                        .map(argContext -> argContext.getChild(0))
                        .collect(toList());

                for(int i = 0; i < keys.size(); i++){
                    SFunc port = producedNode.getPort(keys.get(i).getText());

                    if(port == null){
                        throw new RuntimeException(keys.get(i).getText() + " cannot be found in " + producedNode.getFullName());
                    }

                    port.setInput(0, getExpr(values.get(i)));
                }
            }

            if(assignment.argList() != null ){
                List<ParseTree> exprs = assignment.argList().arg().stream()
                        .map(argContext -> argContext.getChild(0))
                        .collect(toList());

                for (int i = 0; i < exprs.size(); i++) {
                    SFunc port = producedNode.getPort(i);
                    port.setInput(0, getExpr(exprs.get(i)));
                }
            }
        }
        return producedNode;
    }

    private Access determineAccess(String access){
        switch(access){
            case "input":
                return Access.READWRITE;
            case "output":
                return Access.READ;
            default:
                return Access.INTERNAL;
        }
    }
}
