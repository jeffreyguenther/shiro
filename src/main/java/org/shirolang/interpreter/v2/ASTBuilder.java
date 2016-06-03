package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.shirolang.interpreter.ShiroBaseListener;
import org.shirolang.interpreter.ShiroParser;
import org.shirolang.interpreter.ast.*;
import org.shirolang.values.SegmentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.shirolang.interpreter.ast.BinaryOperator.*;
import static org.shirolang.interpreter.ast.UnaryOperator.*;

/**
 * Walks the ParseTree to build the AST
 */
public class ASTBuilder extends ShiroBaseListener{
    private Program p;
    private ParseTreeProperty<Expression> expressions;

    public ASTBuilder() {
        p = new Program();
        expressions = new ParseTreeProperty<>();
    }

    @Override
    public void enterAnonymousGraphStmt(ShiroParser.AnonymousGraphStmtContext ctx) {
        p.add(new GraphDefinition());
    }

    @Override
    public void exitFuncDeclInit(ShiroParser.FuncDeclInitContext ctx) {
    }

    @Override
    public void exitAnonExpr(ShiroParser.AnonExprContext ctx) {
        p.getDefaultGraph().add(get(ctx.expr()));
    }

    @Override
    public void exitPath(ShiroParser.PathContext ctx) {
        expressions.put(ctx, createPath(ctx));
    }

    @Override
    public void exitAddExpr(ShiroParser.AddExprContext ctx) {
        BinaryOperator op;

        if(ctx.MINUS_OP() != null){
            op = BinaryOperator.SUBTRACT;
        }else{
            op = BinaryOperator.ADD;
        }

        expressions.put(ctx, new BinaryOperation(expressions.get(ctx.expr(0)), op, expressions.get(ctx.expr(1))));
    }

    @Override
    public void exitMultExpr(ShiroParser.MultExprContext ctx) {
        BinaryOperator op;

        if(ctx.MULT_OP() != null){
            op = MULTIPLY;
        }else if(ctx.DIV_OP() != null){
            op = DIVIDE;
        }else{
            op = BinaryOperator.MOD;
        }

        expressions.put(ctx, new BinaryOperation(expressions.get(ctx.expr(0)), op, expressions.get(ctx.expr(1))));
    }

    @Override
    public void exitAndExpr(ShiroParser.AndExprContext ctx) {
        BinaryOperator op = BinaryOperator.AND;
        expressions.put(ctx, new BinaryOperation(expressions.get(ctx.expr(0)), op, expressions.get(ctx.expr(1))));
    }

    @Override
    public void exitOrExpr(ShiroParser.OrExprContext ctx) {
        BinaryOperator op = BinaryOperator.OR;
        expressions.put(ctx, new BinaryOperation(expressions.get(ctx.expr(0)), op, expressions.get(ctx.expr(1))));
    }

    @Override
    public void exitComparisonExpr(ShiroParser.ComparisonExprContext ctx) {
        BinaryOperator op;

        if(ctx.GT() != null){
            op = GREATER_THAN;
        }else if(ctx.GTE() != null){
            op = GREATER_THAN_OR_EQUAL;
        }else if(ctx.LT() != null){
            op = LESS_THAN;
        }else{
            op = LESS_THAN_OR_EQUAL;
        }

        expressions.put(ctx, new BinaryOperation(expressions.get(ctx.expr(0)), op, expressions.get(ctx.expr(1))));
    }

    @Override
    public void exitEqualityExpr(ShiroParser.EqualityExprContext ctx) {
        BinaryOperator op;

        if(ctx.EQ() != null){
            op = EQUAL;
        }else{
            op = NOT_EQUAL;
        }

        expressions.put(ctx, new BinaryOperation(expressions.get(ctx.expr(0)), op, expressions.get(ctx.expr(1))));
    }

    @Override
    public void exitParensExpr(ShiroParser.ParensExprContext ctx) {
        expressions.put(ctx, new UnaryOperation(PARENS, expressions.get(ctx.expr())));
    }

    @Override
    public void exitNotExpr(ShiroParser.NotExprContext ctx) {
        expressions.put(ctx, new UnaryOperation(NOT, expressions.get(ctx.expr())));
    }

    @Override
    public void exitNegExpr(ShiroParser.NegExprContext ctx) {
        expressions.put(ctx, new UnaryOperation(NEGATE, expressions.get(ctx.expr())));
    }

    @Override
    public void exitTypeExpr(ShiroParser.TypeExprContext ctx) {
        expressions.put(ctx, createFullyQualifiedType(ctx.fullyQualifiedType()));
    }

    @Override
    public void exitAnonRefExpr(ShiroParser.AnonRefExprContext ctx) {
        expressions.put(ctx, create(ctx.anonymousRef().reference()));
    }

    @Override
    public void exitStringExpr(ShiroParser.StringExprContext ctx) {
        String literalString = ctx.STRING_LITERAL().getText();

        // Remove the quotes from around the string literal
        String value = literalString.substring(1, literalString.length() - 1);
        expressions.put(ctx, Literal.asString(value));
    }

    @Override
    public void exitNumExpr(ShiroParser.NumExprContext ctx) {
        String number = ctx.NUMBER().getText();

        Expression expr;
        // Detect if the number is a double
        if (number.contains(".")) {
            expr = Literal.asDouble(Double.parseDouble(number));
        } else {
            expr = Literal.asInteger(Integer.parseInt(number));
        }

        expressions.put(ctx, expr);
    }

    @Override
    public void exitBoolExpr(ShiroParser.BoolExprContext ctx) {
        String value = ctx.BOOLEAN_LITERAL().getText();
        expressions.put(ctx, Literal.asBoolean(Boolean.parseBoolean(value)));
    }

    private Expression createPath(ShiroParser.PathContext ctx) {
        Path p = new Path();
        ctx.segments.stream().map(this::createSegment).forEach(p::addSegment);

        if(ctx.REF() != null){
            p.makeReference();
        }

        if(ctx.SELECT() != null){
            p.makeSelector();
        }

        return Literal.asPath(p);
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
            }else if( pathIndex.index.getType() == ShiroParser.IDENT){
                String key = pathIndex.IDENT().getText();
                return new PathSegment(SegmentType.INPUT, key);
            }
        }

        if(ctx.OUTPUTS() != null){
            ShiroParser.PathIndexContext pathIndex = ctx.pathIndex();
            if(pathIndex.index.getType() == ShiroParser.NUMBER){
                int i = Integer.parseInt(pathIndex.NUMBER().getText());
                return new PathSegment(SegmentType.OUTPUT, i);
            }else if( pathIndex.index.getType() == ShiroParser.IDENT){
                String key = pathIndex.IDENT().getText();
                return new PathSegment(SegmentType.OUTPUT, key);
            }
        }

        return null; // should never reach here.
    }

    private Reference create(ShiroParser.ReferenceContext ctx){
        Reference r;

        String type = convertFullyQualifiedTypeToString(ctx.fullyQualifiedType());
        String activeOption = "";
        String outputSelector = "";

        List<Expression> arglist = new ArrayList<>();
        Map<String, Expression> argMap = new HashMap<>();

        if(ctx.activeObject != null){
            activeOption = ctx.activeObject.getText();
        }

        if(ctx.outputSelector() != null){
            if(ctx.outputSelector().IDENT() != null){
                outputSelector = ctx.outputSelector().IDENT().getText();
            }else if(ctx.outputSelector().NUMBER() != null){
                outputSelector = ctx.outputSelector().NUMBER().getText();
            }
        }

        if(ctx.arguments() != null){
            if(ctx.arguments().argList() != null){
                arglist.addAll(ctx.arguments().argList().arg().stream()
                        .map(ShiroParser.ArgContext::expr)
                        .map(e -> expressions.get(e))
                        .collect(Collectors.toList()));
            }else if(ctx.arguments().argMap() != null){
                argMap.putAll(create(ctx.arguments().argMap()));
            }
        }

        if (!arglist.isEmpty()){
            if(!activeOption.isEmpty() && !outputSelector.isEmpty()){
                r = new Reference(type, activeOption, arglist, outputSelector);
            }else if(!activeOption.isEmpty()){
                r = new Reference(type, activeOption, arglist);
            }else if(!outputSelector.isEmpty()) {
                r = new Reference(type, arglist, outputSelector);
            }else {
                r = new Reference(type, arglist);
            }
        }else if (!argMap.isEmpty()){
            if(!activeOption.isEmpty() && !outputSelector.isEmpty()){
                r = new Reference(type, activeOption, argMap, outputSelector);
            }else if(!activeOption.isEmpty()){
                r = new Reference(type, activeOption, argMap);
            }else if(!outputSelector.isEmpty()) {
                r = new Reference(type, argMap, outputSelector);
            }else {
                r = new Reference(type, argMap);
            }
        }else{
            if(!activeOption.isEmpty() && !outputSelector.isEmpty()){
                r = new Reference(type, activeOption, outputSelector);
            }else if(!activeOption.isEmpty()){
                r = new Reference(type, activeOption);
            }else if(!outputSelector.isEmpty()) {
                r = new Reference(type, outputSelector);
            }else {
                r = new Reference(type);
            }
        }

        return r;
    }

    private Map<String, Expression> create(ShiroParser.ArgMapContext ctx){
        Map<String, Expression> argMap = new HashMap<>();
        List<String> keys = ctx.keys.stream().map(Token::getText).collect(Collectors.toList());
        List<Expression> values = ctx.values.stream()
                .map(ShiroParser.ArgContext::expr)
                .map(e -> expressions.get(e))
                .collect(Collectors.toList());

        for(int i = 0; i < keys.size(); i++){
            argMap.put(keys.get(i), values.get(i));
        }
        return argMap;
    }

    private String convertFullyQualifiedTypeToString(ShiroParser.FullyQualifiedTypeContext ctx){
        return ctx.types.stream().map(Token::getText).collect(Collectors.joining("."));
    }

    private Expression createFullyQualifiedType(ShiroParser.FullyQualifiedTypeContext ctx) {
        String type = convertFullyQualifiedTypeToString(ctx);
        return new FullyQualifiedType(type);
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

    public Expression get(ParseTree t){
        if (expressions.get(t) == null) {
            return expressions.get(t.getChild(0));
        }
        return expressions.get(t);
    }

    public Program getProgram() {
        return p;
    }
}
