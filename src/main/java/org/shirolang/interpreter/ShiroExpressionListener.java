package org.shirolang.interpreter;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.shirolang.base.*;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.functions.Instantiator;
import org.shirolang.functions.math.*;
import org.shirolang.interpreter.ast.Access;
import org.shirolang.interpreter.ast.Path;
import org.shirolang.interpreter.ast.PathSegment;
import org.shirolang.values.*;

import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * A listener to create expressions.
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

    protected SGraph getGraph() {
        return (SGraph) scope.get(0);
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
                String key = pathIndex.IDENT().getText();
                return new PathSegment(SegmentType.INPUT, key);
            }
        }

        if(ctx.OUTPUTS() != null){
            ShiroParser.PathIndexContext pathIndex = ctx.pathIndex();
            if(pathIndex.index.getType() == ShiroParser.NUMBER){
                int i = Integer.parseInt(pathIndex.NUMBER().getText());
                return new PathSegment(SegmentType.OUTPUT, i);
            }else if( pathIndex.index.getType() == ShiroParser.STRING_LITERAL){
                String key = pathIndex.IDENT().getText();
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
    public void exitTypeExpr(ShiroParser.TypeExprContext ctx) {
        setExpr(ctx, createFullyQualifiedType(ctx.fullyQualifiedType()));
    }

    private String convertFullyQualifiedTypeToString(ShiroParser.FullyQualifiedTypeContext ctx){
        return ctx.types.stream().map(Token::getText).collect(Collectors.joining("."));
    }

    private SFunc createFullyQualifiedType(ShiroParser.FullyQualifiedTypeContext ctx) {
        String type = convertFullyQualifiedTypeToString(ctx);
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
    public void exitFuncCall(ShiroParser.FuncCallContext ctx) {
        String type = convertFullyQualifiedTypeToString(ctx.fullyQualifiedType());

        // get the type name
        SFunc function = library.createFunction(getGraph(), type);

        if(function == null){
            throw new RuntimeException("A function by the name " + type
                    + "does not exist.");
        }

        if(library.getTypesRequiringLibrary().contains(function.getType())){
            ((Instantiator) function).setLibrary(library);
        }

        if (ctx.activeObject != null) {
            // TODO provide Shiro error if function is not a node
            String updatePort = ctx.activeObject.getText();
            try {
                ((SNode) function).setActiveOption(updatePort);
            } catch (OptionNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        ShiroParser.ArgumentsContext arguments = ctx.arguments();
        function = setArgumentsInDecl(function, arguments);
        setExpr(ctx, function);
    }

    @Override
    public void exitFuncDecl(ShiroParser.FuncDeclContext ctx) {
        String type = convertFullyQualifiedTypeToString(ctx.fullyQualifiedType());

        // get the type name
        SFunc function = library.createFunction(getGraph(), type);
        if(function == null){
            throw new RuntimeException("A function by the name " + type
                    + "does not exist.");
        }

        if (ctx.activeObject != null) {
            // TODO provide Shiro error if function is not a node
            String updatePort = ctx.activeObject.getText();
            try {
                ((SNode) function).setActiveOption(updatePort);
            } catch (OptionNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        String portName = ctx.name.getText();
        function.setName(portName);

        setExpr(ctx, function);
    }

    @Override
    public void exitPortDecl(@NotNull ShiroParser.PortDeclContext ctx) {
        SFunc function = getExpr(ctx.funcDecl());

        String portType = Objects.isNull(ctx.accessModifier())? "" : ctx.accessModifier().getText();

        if(!function.getSymbolType().isNode()) {
            function.setSymbolType(SymbolType.PORT);
        }
        function.setAccess(determineAccess(portType));
    }


    @Override
    public void exitFuncDeclInit(ShiroParser.FuncDeclInitContext ctx) {
        String type = convertFullyQualifiedTypeToString(ctx.fullyQualifiedType());

        // get the type name
        SFunc function = library.createFunction(getGraph(), type);

        if(function == null){
            throw new RuntimeException("A function by the name " + type
                    + "does not exist.");
        }

        if(library.getTypesRequiringLibrary().contains(function.getType())){
            ((Instantiator) function).setLibrary(library);
        }

        if (ctx.activeObject != null) {
            // TODO provide Shiro error if function is not a node
            String updatePort = ctx.activeObject.getText();
            try {
                ((SNode) function).setActiveOption(updatePort);
            } catch (OptionNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        ShiroParser.ArgumentsContext arguments = ctx.arguments();
        function = setArgumentsInDecl(function, arguments);

        String portName = ctx.name.getText();
        function.setName(portName);

        setExpr(ctx, function);
    }

    @Override
    public void exitPortDeclInit(ShiroParser.PortDeclInitContext ctx) {
        SFunc function = getExpr(ctx.funcDeclInit());

        String portType = Objects.isNull(ctx.accessModifier())? "" : ctx.accessModifier().getText();

        if(!function.getSymbolType().isNode()) {
            function.setSymbolType(SymbolType.PORT);
        }
        function.setAccess(determineAccess(portType));

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
            arg0.setAccess(function.getAccess());
            arg0.setName(function.getName());
            arg0.setSymbolType(function.getSymbolType());

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