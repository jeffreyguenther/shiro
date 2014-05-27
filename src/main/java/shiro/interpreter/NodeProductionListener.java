package shiro.interpreter;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import shiro.Graph;
import shiro.Node;
import shiro.Port;
import shiro.Scope;
import shiro.definitions.PortType;
import shiro.ShiroRuntime;
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
    private Node createdNode;
    private ShiroRuntime runtime;
    private Graph graph;

    public NodeProductionListener(ShiroRuntime rt, Graph graph) {
        super(graph);
        runtime = rt;
        this.graph = graph;
        createdNode = null;
    }

    public Node getCreatedNode() {
        return createdNode;
    }

    @Override
    public void enterNodestmt(ShiroParser.NodestmtContext ctx) {
        // if there is at least one node on the scope stack
        // stack will always be size 1 because of the parametric system
        if (scope.size() > 1) {
            Node parentNode = (Node) scope.peek();
            
            // create a new node
            String type = Path.createFullName(parentNode.getFullName(), ctx.IDENT().getText());
            createdNode = new Node(type, ctx.IDENT().getText(), parentNode);
            
            // add the node as a nested node
            parentNode.addNestedNode(createdNode);
            scope.push(createdNode);

        } else {
            // the type is the same as the names
            createdNode = new Node(ctx.IDENT().getText(), ctx.IDENT().getText(), scope.peek());
            scope.push(createdNode);
        }
    }

    @Override
    public void exitNodestmt(ShiroParser.NodestmtContext ctx) {
        // Set the default options
        // This depends on nodeInternal adding ports and nodes before hand.
        if(ctx.activeSelector() != null){
            // Get a reference to the current node
            Node node = (Node) scope.peek();
            
            // set the node's active option
            node.setActiveOption(ctx.activeSelector().IDENT().getText());
        }
        
        // if the stack is not empty, pop
        if(scope.size() > 1){
            createdNode = (Node) scope.pop();
        }
    }

    @Override
    public void enterSubjunctDeclNodeProd(ShiroParser.SubjunctDeclNodeProdContext ctx) {
        // create node
        String name = ctx.type.getText();
        String newName = ctx.instanceName.getText();

        // store the current node
        createdNode = runtime.produceNodeWithName(graph, name, newName);
        createdNode.setParentScope(scope.peek());
       
        // add the created node to subjunctive node, so the scope tree is preserved
        Node parentNode = (Node) scope.peek();
        parentNode.addOption(createdNode);
        scope.push(createdNode);

    }

    @Override
    public void exitSubjunctDeclNodeProd(ShiroParser.SubjunctDeclNodeProdContext ctx) {
        scope.pop();
    }

    @Override
    public void exitNodeProduction(ShiroParser.NodeProductionContext ctx) {
        // get the path of LHS of production operator
        Path leftHandSide = (Path) getExpr(ctx.path());

        // for each activation
        for (ShiroParser.ActivationContext ac : ctx.activation()) {
            String nodeName = ac.nodeName.getText();

            // need to differentiate between creating nodes and subjunctive nodes
            Symbol producedSymbol = runtime.produceSymbolFromName(graph, leftHandSide.getPath(), nodeName);
            Node producedNode = (Node) producedSymbol;
            
            Scope currentScope = scope.peek();
            if(!currentScope.isRoot()){
               Node inNode = (Node) currentScope;
               inNode.addNestedNode(producedNode);
            }
            
            producedNode.setParentScope(currentScope);
            graph.addNode(producedNode);
            
            //TODO types of errors to handle
            // leftside is not found. do a lower case check to inform the user

            if (ac.activeObject != null) {
                String updatePort = ac.activeObject.getText();
                producedNode.setActiveOption(updatePort);
            }
        }
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
        MultiFunction mf = runtime.getMultiFunction(mfName.getText());

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
        
        if(ctx.OPTION() == null){
            createdNode.addPort(p);
        }else{
            createdNode.addOption(p);
        }
        
        
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
        }else if (type.equals("output")){
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
        // TODO and multifunction not found error
        MultiFunction mf = runtime.getMultiFunction(mfName.getText());

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

        if(ctx.OPTION() == null){
            createdNode.addPort(p);
        }else{
            createdNode.addOption(p);
        }
    }
}
