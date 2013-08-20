package shiro.interpreter;

import java.util.ArrayList;
import java.util.List;
import main.java.shiro.interpreter.ShiroBaseListener;
import main.java.shiro.interpreter.ShiroParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import shiro.Scope;
import shiro.expressions.Add;
import shiro.expressions.Expression;
import shiro.expressions.Path;
import shiro.expressions.Subtract;

/**
 *
 * @author jeffreyguenther
 */
public class GraphPass extends ShiroBaseListener {
    
    Scope currentScope;
    ParseTreeProperty<Expression> expressions;
    

    public GraphPass() {
        currentScope = null;
        expressions = new ParseTreeProperty<Expression>();
    }
    
    private Expression getExpr(ParseTree node){
        return expressions.get(node);
    }
    
    private void setExpr(ParseTree node, Expression expr){
        expressions.put(node, expr);
    }

    @Override
    public void enterNodestmt(ShiroParser.NodestmtContext ctx) {
        System.out.format("Realize the node %s\n", ctx.IDENT().getText());
    }

    @Override
    public void enterPortstmt(ShiroParser.PortstmtContext ctx) {
        
    }

    @Override
    public void enterMfparams(ShiroParser.MfparamsContext ctx) {
        
    }

    @Override
    public void enterMfName(ShiroParser.MfNameContext ctx) {
        
    }

    @Override
    public void enterAddExp(ShiroParser.AddExpContext ctx) {
        Expression op1 = getExpr(ctx.expr(0));
        Expression op2 = getExpr(ctx.expr(1));
        
        if(ctx.getChild(1).getText().equals("+")){
            Add add = new Add(op1, op2);
            setExpr(ctx, add);
        }else{
            Subtract subtract = new Subtract(op1, op2);
            setExpr(ctx, subtract);
        }
        
    }

    @Override
    public void enterNumberExp(ShiroParser.NumberExpContext ctx) {
        shiro.expressions.Number n = new shiro.expressions.Number(
                Float.parseFloat(ctx.NUMBER().getText()));
        
        // save the result for later
       setExpr(ctx, n);
    }

    @Override
    public void enterPath(ShiroParser.PathContext ctx) {
        // Declare a list to store the path's parts
        List<String> parts = new ArrayList<String>();
        
        Path p = null;
        
        // Convert the tree nodes into strings and add to list
        for(TerminalNode t: ctx.IDENT()){
            parts.add(t.getText());
        }
        
        if(ctx.pathIndex() != null){
            ShiroParser.PathIndexContext pathIndex = ctx.pathIndex();
            
            if(pathIndex.index.getType() == ShiroParser.NUMBER){
               int i = Integer.parseInt(pathIndex.NUMBER().getText());
               p = new Path(currentScope, parts, i);
            }else{
                String key = pathIndex.STRING_LITERAL().getText().replace("\"", "");
                p = new Path(currentScope, parts, key);
            }
           
        }else{
            p = new Path(currentScope, parts);
        }
        
        // save the path for later
        setExpr(ctx, p);
    }
    
    
    
    
    
}
