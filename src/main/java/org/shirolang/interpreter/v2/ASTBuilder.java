package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.shirolang.interpreter.ShiroBaseListener;
import org.shirolang.interpreter.ShiroParser;
import org.shirolang.interpreter.ast.*;
import org.shirolang.values.SegmentType;

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
        String type = convertFullyQualifiedTypeToString(ctx.fullyQualifiedType());

    }

    @Override
    public void exitAnonExpr(ShiroParser.AnonExprContext ctx) {
        p.getDefaultGraph().add(expressions.get(ctx.expr()));
    }

    @Override
    public void enterPath(ShiroParser.PathContext ctx) {
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

    protected Expression createPath(ShiroParser.PathContext ctx) {
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

    private String convertFullyQualifiedTypeToString(ShiroParser.FullyQualifiedTypeContext ctx){
        return ctx.types.stream().map(Token::getText).collect(Collectors.joining("."));
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
        return expressions.get(t);
    }

    public Program getProgram() {
        return p;
    }
}
