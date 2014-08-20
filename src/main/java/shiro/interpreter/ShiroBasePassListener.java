package shiro.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import shiro.exceptions.PathNotFoundException;
import shiro.Port;
import shiro.Scope;
import shiro.expressions.Add;
import shiro.expressions.Divide;
import shiro.expressions.Expression;
import shiro.expressions.Mod;
import shiro.expressions.Multiply;
import shiro.expressions.Or;
import shiro.expressions.Path;
import shiro.expressions.SBoolean;
import shiro.expressions.SNumber;
import shiro.expressions.SString;
import shiro.expressions.Subtract;

/**
 *
 * @author jeffreyguenther
 */
public class ShiroBasePassListener extends ShiroBaseListener {

    protected Stack<Scope> scope;
    protected ParseTreeProperty<Expression> expressions;

    public ShiroBasePassListener(Scope rootScope) {
        this.expressions = new ParseTreeProperty<>();
        this.scope = new Stack<>();
        this.scope.push(rootScope);
    }

    /**
     * Get an expression for a parse tree node
     *
     * @param node parse tree node
     * @return expression stored for that parse tree node
     */
    public Expression getExpr(ParseTree node) {
        if (expressions.get(node) == null) {
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
        switch (ctx.getChild(1).getText()) {
            case "*":
                Multiply mult = new Multiply(op1, op2);
                setExpr(ctx, mult);
                break;
            case "/":
                Divide div = new Divide(op1, op2);
                setExpr(ctx, div);
                break;
            default:
                Mod mod = new Mod(op1, op2);
                setExpr(ctx, mod);
                break;
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
        SNumber n = new SNumber(
                Double.parseDouble(ctx.NUMBER().getText()));

        // save the result for later
        setExpr(ctx, n);
    }

    @Override
    public void exitBooleanExp(ShiroParser.BooleanExpContext ctx) {
        SBoolean b = new SBoolean(Boolean.parseBoolean(ctx.BOOLEAN_LITERAL().getText()));
        
        setExpr(ctx, b);
    }

    @Override
    public void exitStringExp(ShiroParser.StringExpContext ctx) {
        String literal = ctx.STRING_LITERAL().getText();
        SString s = new SString(literal.substring(1, literal.length() - 1));
        setExpr(ctx, s);
    }

    protected Path createPath(Scope currentScope, ShiroParser.PathContext ctx) {
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
        // save the path for later
        setExpr(ctx, createPath(scope.peek(), ctx));
    }

    @Override
    public void exitPortAssignment(ShiroParser.PortAssignmentContext ctx) {
        // look up port based on the path
        try {
            Path path = (Path) getExpr(ctx.path());
            // resolve path in current scope
            Port p = (Port) scope.peek().resolvePath(path);

            // prevent eval and output ports in the graph from being set
            if (p.isInput()) {

                List<Expression> mfExpressions = new ArrayList<>();

                for (ParseTree pt : ctx.mfparams().expr()) {
                    Expression exp = getExpr(pt);
                    mfExpressions.add(exp);
                }
                // set the port's expression
                p.setArguments(mfExpressions);
            }else{
                // notify the user of the error
                System.out.println("Tried to assign to an output port" + p + " in " + scope.peek());
            }

        } catch (PathNotFoundException pnfe) {

        }
    }
}
