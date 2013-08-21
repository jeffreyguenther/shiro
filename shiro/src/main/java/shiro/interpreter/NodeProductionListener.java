package shiro.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import main.java.shiro.interpreter.ShiroBaseListener;
import main.java.shiro.interpreter.ShiroParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import shiro.Node;
import shiro.Port;
import shiro.PortType;
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
import shiro.functions.MultiFunction;

/**
 * Listener used to realize node parse trees
 *
 * @author jeffreyguenther
 */
public class NodeProductionListener extends ShiroBaseListener {
    // instance of the parametric system for manipulating dep. graph

    private SubjunctiveParametricSystem pSystem;
    // current scope
    private Scope currentScope;
    private Node currentContainerNode;
    // expressions to be used when creating argument lists
    private ParseTreeProperty<Expression> expressions;
    // node that listener is to create
    private Node createdNode;

    public NodeProductionListener(SubjunctiveParametricSystem ps) {
        pSystem = ps;
        currentScope = ps;
        currentContainerNode = null;
        expressions = new ParseTreeProperty<Expression>();
        createdNode = null;
    }

    /**
     * Get an expression for a parse tree node
     *
     * @param node parse tree node
     * @return expression stored for that parse tree node
     */
    private Expression getExpr(ParseTree node) {
        return expressions.get(node);
    }

    private void setExpr(ParseTree node, Expression expr) {
        expressions.put(node, expr);
    }

    public Node getCreatedNode() {
        return createdNode;
    }

    @Override
    public void enterNodestmt(ShiroParser.NodestmtContext ctx) {
        System.out.println("Enter Node Statement");

        if (currentContainerNode != null) {
            // create a new node
            createdNode = new Node(ctx.IDENT().getText(), currentScope);
            // add the node as a nested node
            currentContainerNode.addNestedContainer(currentContainerNode);

        } else {
            createdNode = new Node(ctx.IDENT().getText(), currentScope);
        }
    }

    @Override
    public void exitNodestmt(ShiroParser.NodestmtContext ctx) {
        System.out.println("Exit Node Statement");

        // set the active evalauated port. 
        // This depends on nodeInternal adding ports and nodes before hand.
        Set<Port> evalPorts = createdNode.getEvaluatedPorts();

        // find the active port and activate it in the node
        if (evalPorts.size() > 1) {
            for (Port p : evalPorts) {
                // Get the token for the active port selector
                Token activeSelector = ctx.activeSelector().IDENT().getSymbol();
                if (p.getName().equals(activeSelector.getText())) {
                    try {
                        createdNode.activate(p.getName());
                    } catch (Exception epnf) {
                        System.out.println(activeSelector.getLine() + ":" + activeSelector.getCharPositionInLine()
                                + " " + activeSelector.getText()
                                + " is not defined.");
                        System.err.println(epnf);
                    }
                }
            }
        } else { // activate the only evaluated port in the node
            Port p = new Port();
            try {
                p = (Port) evalPorts.toArray()[0];
                createdNode.activate(p.getName());
            } catch (Exception epnf) {
                System.out.println(p.getName()
                        + " is not defined.");
                System.err.println(epnf);
            }
        }
    }

    @Override
    public void enterNodeInternal(ShiroParser.NodeInternalContext ctx) {
        System.out.println("Enter Node Internal");
    }

    @Override
    public void exitPortDeclInit(ShiroParser.PortDeclInitContext ctx) {
        // Get the token of the multifunction name
        Token mfName = ctx.mfCall().mfName().IDENT().getSymbol();
        Token portName = ctx.portName().IDENT().getSymbol();

        // Get the port type
        String portType = ctx.portType().getText();

        // Create a port instance
        Port p = new Port();

        // create a new port
        MultiFunction mf = pSystem.getFunction(mfName.getText());

        // detect if the multifunction exists
        if (mf != null) {
            // create a path based on the node's name
            String path = createdNode.getFullName() + "." + portName.getText();

            List<Expression> mfExpressions = new ArrayList<Expression>();

            for (ParseTree pt : ctx.mfCall().mfparams().expr()) {
                mfExpressions.add(expressions.get(pt.getChild(0)));
            }

            // create the port
            p = new Port(path, mfExpressions, mf);
        } else {
            System.out.println(mfName.getLine() + ":" + mfName.getCharPositionInLine() + " Unknown multifunction: " + mfName.getText());
        }

        if (portType.equals("eval")) {
            p.setPortType(PortType.Evaluated);
            createdNode.addEvaluatedPort(p);
        } else {
            createdNode.addPort(p);
        }
    }

    @Override
    public void exitPortDecl(ShiroParser.PortDeclContext ctx) {
        // Get the token of the multifunction name
        Token mfName = ctx.mfName().IDENT().getSymbol();
        Token portName = ctx.portName().IDENT().getSymbol();

        // Get the port type
        String portType = ctx.portType().getText();

        // Create a port instance
        Port p = new Port();

        // create a new port
        MultiFunction mf = pSystem.getFunction(mfName.getText());

        // detect if the multifunction exists
        if (mf != null) {
            // create the port
            String path = createdNode.getFullName() + "." + portName.getText();
            // create the port
            p = new Port(path, mf);
        } else {
            System.out.println(mfName.getLine() + ":" + mfName.getCharPositionInLine() + " Unknown multifunction: " + mfName.getText());
        }

        if (portType.equals("eval")) {
            p.setPortType(PortType.Evaluated);
        }

        createdNode.addPort(p);
    }

    @Override
    public void enterPortstmt(ShiroParser.PortstmtContext ctx) {
    }

    @Override
    public void enterOrExp(ShiroParser.OrExpContext ctx) {
        Expression op1 = getExpr(ctx.expr(0));
        Expression op2 = getExpr(ctx.expr(1));

        Or or = new Or(op1, op2);
        setExpr(ctx, or);
    }

    @Override
    public void enterMultExp(ShiroParser.MultExpContext ctx) {
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
    public void enterAddExp(ShiroParser.AddExpContext ctx) {
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

        // save the path for later
        setExpr(ctx, p);
    }
}
