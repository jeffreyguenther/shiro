package shiro.interpreter;

import java.util.ArrayList;
import java.util.List;
import main.java.shiro.interpreter.ShiroBaseListener;
import main.java.shiro.interpreter.ShiroParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import shiro.Scope;
import shiro.SubjunctiveParametricSystem;
import shiro.expressions.Add;
import shiro.expressions.Divide;
import shiro.expressions.Expression;
import shiro.expressions.Mod;
import shiro.expressions.Multiply;
import shiro.expressions.Or;
import shiro.expressions.Path;
import shiro.expressions.Subtract;

/**
 *
 * @author jeffreyguenther
 */
public class ShiroBasePassListener extends ShiroBaseListener {
    protected SubjunctiveParametricSystem pSystem;
    protected ParseTreeProperty<Expression> expressions;
    protected Scope currentScope;

    public ShiroBasePassListener(SubjunctiveParametricSystem pSystem) {
        this.pSystem = pSystem;
        this.expressions = new ParseTreeProperty<Expression>();
        currentScope = pSystem;
    }
    
    /**
     * Get an expression for a parse tree node
     *
     * @param node parse tree node
     * @return expression stored for that parse tree node
     */
    protected Expression getExpr(ParseTree node) {
        if(expressions.get(node) == null){
            return expressions.get(node.getChild(0));
        }
        
        return expressions.get(node);
    }

    protected void setExpr(ParseTree node, Expression expr) {
        expressions.put(node, expr);
    }
    
    @Override
    public void exitOrExp(ShiroParser.OrExpContext ctx) {
        Expression op1 = getExpr(ctx.expr(0));
        Expression op2 = getExpr(ctx.expr(1));

        Or or = new Or(op1, op2);
        setExpr(ctx, or);
    }

    @Override
    public void exitMultExp(ShiroParser.MultExpContext ctx) {
        Expression op1 = getExpr(ctx.expr(0));
        Expression op2 = getExpr(ctx.expr(1));

        if (ctx.getChild(1).getText().equals("*")) {
            Multiply mult = new Multiply(op1, op2);
            setExpr(ctx, mult);
        } else if (ctx.getChild(1).getText().equals("/")) {
            Divide div = new Divide(op1, op2);
            setExpr(ctx, div);
        } else {
            Mod mod = new Mod(op1, op2);
            setExpr(ctx, mod);
        }
    }

    @Override
    public void exitAddExp(ShiroParser.AddExpContext ctx) {
        Expression op1 = getExpr(ctx.expr(0));
        Expression op2 = getExpr(ctx.expr(1));

        if (ctx.getChild(1).getText().equals("+")) {
            Add add = new Add(op1, op2);
            setExpr(ctx, add);
        } else {
            Subtract subtract = new Subtract(op1, op2);
            setExpr(ctx, subtract);
        }
    }

    @Override
    public void exitNumberExp(ShiroParser.NumberExpContext ctx) {
        shiro.expressions.Number n = new shiro.expressions.Number(
                Float.parseFloat(ctx.NUMBER().getText()));

        // save the result for later
        setExpr(ctx, n);
    }
    
    protected Path createPath(Scope currentScope, ShiroParser.PathContext ctx){
        // Declare a list to store the path's parts
        List<String> parts = new ArrayList<String>();

        Path p = new Path();

        // Convert the tree nodes into strings and add to list
        for (TerminalNode t : ctx.IDENT()) {
            parts.add(t.getText());
        }

        if (ctx.pathIndex() != null) {
            ShiroParser.PathIndexContext pathIndex = ctx.pathIndex();

            if (pathIndex.index.getType() == ShiroParser.NUMBER) {
                int i = Integer.parseInt(pathIndex.NUMBER().getText());
                p = new Path(currentScope, parts, i);
            } else {
                String key = pathIndex.STRING_LITERAL().getText().replace("\"", "");
                p = new Path(currentScope, parts, key);
            }

        } else {
            p = new Path(currentScope, parts);
        }
        
        return p;
    }
    
    @Override
    public void enterPath(ShiroParser.PathContext ctx) {
        //System.out.println("Enter PathContext");
        // save the path for later
        setExpr(ctx, createPath(currentScope, ctx));
    }
}
