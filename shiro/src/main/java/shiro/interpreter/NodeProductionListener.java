package shiro.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import main.java.shiro.interpreter.ShiroParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import shiro.Node;
import shiro.Port;
import shiro.PortType;
import shiro.SubjunctiveParametricSystem;
import shiro.expressions.Expression;
import shiro.functions.MultiFunction;

/**
 * Listener used to realize node parse trees
 *
 * @author jeffreyguenther
 */
public class NodeProductionListener extends ShiroBasePassListener {
    private Node currentContainerNode;
    // node that listener is to create
    private Node createdNode;

    public NodeProductionListener(SubjunctiveParametricSystem ps) {
        super(ps);
        currentContainerNode = null;
        createdNode = null;
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
            currentScope = createdNode;
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
        //System.out.println("Enter Node Internal");
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
                Expression exp = getExpr(pt);
                mfExpressions.add(exp);
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
}
