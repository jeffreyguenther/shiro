package shiro.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import shiro.Node;
import shiro.PathNotFoundException;
import shiro.Port;
import shiro.definitions.PortType;
import shiro.SubjunctiveParametricSystem;
import shiro.expressions.Expression;
import shiro.expressions.Path;
import shiro.functions.MultiFunction;

/**
 * Listener used to realize node parse trees
 *
 * @author jeffreyguenther
 */
public class NodeProductionListener extends ShiroBasePassListener {
    private Node parentNode;
    private Node createdNode;
    private Stack<Node> nodes;

    public NodeProductionListener(SubjunctiveParametricSystem ps) {
        super(ps);
        parentNode = null;
        createdNode = null;
        nodes = new Stack<>();
    }

    public Node getCreatedNode() {
        return createdNode;
    }

    @Override
    public void enterNodestmt(ShiroParser.NodestmtContext ctx) {
        System.out.println("Enter Node Statement");
        
        // if the parent node is defined, then the node is nested.
        if (!nodes.isEmpty()) {
            currentScope = nodes.peek();
            parentNode = nodes.peek();
            
            // create a new node
            String type = Path.createFullName(parentNode.getFullName(), ctx.IDENT().getText());
            createdNode = new Node(type, ctx.IDENT().getText(), currentScope);
            
            // add the node as a nested node
            parentNode.addNestedNode(createdNode);
            nodes.push(createdNode);

        } else {
            // the type is the same as the names
            createdNode = new Node(ctx.IDENT().getText(), ctx.IDENT().getText(), currentScope);
            nodes.push(createdNode);
        }
    }

    @Override
    public void exitNodestmt(ShiroParser.NodestmtContext ctx) {
        System.out.println("Exit Node Statement");

        // Set the default options
        // This depends on nodeInternal adding ports and nodes before hand.
        nodes.peek().setActiveOption(ctx.activeSelector().IDENT().getText());
        
        // if the stack is not empty, pop
        if(!nodes.empty()){
            nodes.pop();
        }
    }

    @Override
    public void enterSubjunctDeclNodeProd(ShiroParser.SubjunctDeclNodeProdContext ctx) {
        // create node
        String name = ctx.type.getText();
        String newName = ctx.instanceName.getText();

        // store the current node
        createdNode = pSystem.produceNodeFromName(name, newName);
       
        // add the created node to subjunctive node, so the scope tree is preserved
        createdNode.setFullName(Path.createFullName(nodes.peek().getFullName(), newName));
        nodes.peek().addOption(createdNode);

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

            List<Expression> mfExpressions = new ArrayList<>();

            for (ParseTree pt : ctx.mfCall().mfparams().expr()) {
                Expression exp = getExpr(pt);
                mfExpressions.add(exp);
            }

            // create the port
            p = new Port(path, mfExpressions, mf);
        } else {
            System.out.println(mfName.getLine() + ":" + mfName.getCharPositionInLine() + " Unknown multifunction: " + mfName.getText());
        }

        setPortType(portType, p);
    }
    
    /**
     * Determine a port's type based on its declaration
     * @param type type of port
     * @param p port to set
     */
    private void setPortType(String type, Port p){
        if (type.equals("eval")) {
            p.setPortType(PortType.Evaluated);
        }else if(type.equals("input")){
            p.setPortType(PortType.Input);
        }else if (type.equals("ouput")){
            p.setPortType(PortType.Output);
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

        setPortType(portType, p);

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

            List<Expression> mfExpressions = new ArrayList<>();

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
