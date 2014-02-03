package shiro.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import shiro.Container;
import shiro.Node;
import shiro.PathNotFoundException;
import shiro.Port;
import shiro.PortType;
import shiro.Scope;
import shiro.SubjunctiveNode;
import shiro.SubjunctiveParametricSystem;
import shiro.Symbol;
import shiro.expressions.Expression;
import shiro.expressions.Path;
import shiro.functions.MultiFunction;

/**
 * Listener used to realize node parse trees
 *
 * @author jeffreyguenther
 */
public class NodeProductionListener extends ShiroBasePassListener {
    /**
     *  To produce a node, you need the new name for the node and the containing
     *  scope.
     */
    
    private String name;
    private Symbol symbol;
    private Scope scope;

    private Container currentContainerNode;
    // node that listener is to create
    private Node createdNode;
    private SubjunctiveNode createdSNode;

    public NodeProductionListener(SubjunctiveParametricSystem ps) {
        super(ps);
        currentContainerNode = null;
        createdNode = null;
        createdSNode = null;
    }
    
    public NodeProductionListener(SubjunctiveParametricSystem ps, String name){
        super(ps);
        this.name = name;
        this.symbol = null;
        this.scope = ps;
        this.currentContainerNode = null;
        this.createdNode = null;
        this.createdSNode = null;
    }
    
    public Symbol getSymbol(){
        return symbol;
    }

    public Node getCreatedNode() {
        return createdNode;
    }
    
    public SubjunctiveNode getCreatedSubjNode(){
        return createdSNode;
    }

    @Override
    public void enterNodestmt(ShiroParser.NodestmtContext ctx) {
        System.out.println("Enter Node Statement");

        if (currentContainerNode != null) {
            // create a new node
            createdNode = new Node(currentContainerNode.getFullName() + ctx.IDENT().getText(), ctx.IDENT().getText(), currentScope);
            // add the node as a nested node
            currentContainerNode.addNestedContainer(createdNode);

        } else {
            createdNode = new Node(ctx.IDENT().getText(), ctx.IDENT().getText(), currentScope);
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
    public void enterSNode(ShiroParser.SNodeContext ctx) {
        System.out.println("Entered subjunctive node");
        // TODO update to use the passed name
        String nodeName = ctx.nodeName.getText();
       
        createdSNode = new SubjunctiveNode(nodeName, currentScope);
        currentScope = createdSNode;
    }

    @Override
    public void exitSNode(ShiroParser.SNodeContext ctx) {
        System.out.println("Exited subjunctive node");
        String activeSubjunct  = ctx.selectedSubjunct.getText();
        try {
            createdSNode.activate(activeSubjunct);
        } catch (PathNotFoundException ex) {
            Logger.getLogger(NodeProductionListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void enterSubjunctDeclNodeProd(ShiroParser.SubjunctDeclNodeProdContext ctx) {
        // create node
        String name = ctx.nodeName.getText();
        String newName = ctx.newName.getText();

        // store the current node
        createdNode = pSystem.produceNodeFromName(name, newName);
       
        // add the created node to subjunctive node, so the scope tree is preserved
        createdNode.setFullName(createdSNode.getFullName() + "." + createdNode.getFullName());
        createdSNode.addSubjunct(createdNode);

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
    
    @Override
    public void enterPortAssignment(ShiroParser.PortAssignmentContext ctx) {
        // Get the node matched by the path to set the scope for expressions
        Path p = createPath(currentScope, ctx.path());
        createdNode = pSystem.getNode(p);
        currentScope = createdNode;
    }

    @Override
    public void exitPortAssignment(ShiroParser.PortAssignmentContext ctx) {
        // look up port based on the path
        try {
            Port p = (Port) pSystem.resolvePath((Path) getExpr(ctx.path()));

            List<Expression> mfExpressions = new ArrayList<Expression>();

            for (ParseTree pt : ctx.mfparams().expr()) {
                Expression exp = getExpr(pt);
                mfExpressions.add(exp);
            }
            // set the port's expression
            p.setArguments(mfExpressions);
            System.out.println("Set port args: " + p);
            System.out.println("Node is now:\n" + createdNode);
        } catch (PathNotFoundException pnfe) {
            System.out.println(pnfe);
        }
    }
}
