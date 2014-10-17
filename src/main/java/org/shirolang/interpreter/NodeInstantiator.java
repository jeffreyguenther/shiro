/*
 * Copyright (c) 2012-2014 Jeffrey Guenther
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software  and associated documentation files (the
 * "Software"), to deal in the Software without restriction,  including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute,  sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT  NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.shirolang.interpreter;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.shirolang.base.SGraph;
import org.shirolang.base.SNode;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.values.Path;

/**
 * Listener used to realize node parse trees
 *
 * @author jeffreyguenther
 */
public class NodeInstantiator extends ShiroExpressionListener {
    private SNode createdNode;
    private SGraph graph;

    public NodeInstantiator(Library lib, SGraph graph) {
        super(lib);
        this.graph = graph;
        scope.push(graph);
        createdNode = null;
    }

    public SNode getCreatedNode() {
        return createdNode;
    }

    @Override
    public void enterNodestmt(ShiroParser.NodestmtContext ctx) {
        // if there is at least one node on the scope stack
        // stack will always be size 1 because of the parametric system
        if (scope.size() > 1) {
            SNode parentNode = (SNode) scope.peek();
            
            // create a new node
            String type = Path.createFullName(parentNode.getFullName(), ctx.MFNAME().getText());
            createdNode = new SNode(type, ctx.MFNAME().getText(), parentNode);
            
            // add the node as a nested node
            parentNode.addNestedNode(createdNode);
            scope.push(createdNode);

        } else {
            // the type is the same as the names
            createdNode = new SNode(ctx.MFNAME().getText(), ctx.MFNAME().getText(), scope.peek());
            scope.push(createdNode);
        }
    }

    @Override
    public void exitNodestmt(ShiroParser.NodestmtContext ctx) {
        // Set the default options
        // This depends on nodeInternal adding ports and nodes before hand.
        if(ctx.activeSelector() != null){
            // Get a reference to the current node
            SNode node = (SNode) scope.peek();
            
            // set the node's active option
            try {
                node.setActiveOption(ctx.activeSelector().IDENT().getText());
            } catch (OptionNotFoundException e) {
                //output error message to error listener
            }
        }
        
        // if the stack is not empty, pop
        if(scope.size() > 1){
            createdNode = (SNode) scope.pop();
        }
    }

//    @Override
//    public void enterSubjunctDeclNodeProd(ShiroParser.SubjunctDeclNodeProdContext ctx) {
//        // create node
//        String name = ctx.type.getText();
//        String newName = ctx.instanceName.getText();
//
//        // store the current node
//        createdNode = runtime.produceNodeWithName(graph, name, newName);
//        createdNode.setParentScope(scope.peek());
//
//        // add the created node to subjunctive node, so the scope tree is preserved
//        Node parentNode = (Node) scope.peek();
//        parentNode.addOption(createdNode);
//        scope.push(createdNode);
//
//    }
//
//    @Override
//    public void exitSubjunctDeclNodeProd(ShiroParser.SubjunctDeclNodeProdContext ctx) {
//        scope.pop();
//    }

//    @Override
//    public void exitNodeProduction(ShiroParser.NodeProductionContext ctx) {
//        // get the path of LHS of production operator
//        Path leftHandSide = (Path) getExpr(ctx.path());
//
//        // for each activation
//        for (ShiroParser.ActivationContext ac : ctx.activation()) {
//            String nodeName = ac.nodeName.getText();
//
//            // need to differentiate between creating nodes and subjunctive nodes
//            Symbol producedSymbol = runtime.produceSymbolFromName(graph, leftHandSide.getPath(), nodeName);
//            Node producedNode = (Node) producedSymbol;
//
//            Scope currentScope = scope.peek();
//            if(!currentScope.isRoot()){
//               Node inNode = (Node) currentScope;
//               inNode.addNestedNode(producedNode);
//            }
//
//            producedNode.setParentScope(currentScope);
//            graph.addNode(producedNode);
//
//            //TODO types of errors to handle
//            // leftside is not found. do a lower case check to inform the user
//
//            if (ac.activeObject != null) {
//                String updatePort = ac.activeObject.getText();
//                producedNode.setActiveOption(updatePort);
//            }
//        }
//    }
    
    
    
    @Override
    public void exitPortDeclInit(ShiroParser.PortDeclInitContext ctx) {
        super.exitPortDeclInit(ctx);

        // Add the port to it's encapsulating node
        SNode node = (SNode) scope.peek();
        if(ctx.OPTION() == null){
            node.addPort(getExpr(ctx));
        }else{
            node.addOption(getExpr(ctx));
        }
    }
    
//    /**
//     * Determine a port's type based on its declaration
//     * @param type type of port
//     * @param p port to set
//     */
//    private void setPortType(String type, Port p){
//        if (type.equals("eval")) {
//            p.setPortType(PortType.Evaluated);
//        }else if(type.equals("input")){
//            p.setPortType(PortType.Input);
//        }else if (type.equals("output")){
//            p.setPortType(PortType.Output);
//        }
//    }

    @Override
    public void exitPortDecl(ShiroParser.PortDeclContext ctx) {
        super.exitPortDecl(ctx);

        SNode node = (SNode) scope.peek();
        if(ctx.OPTION() == null){
            node.addPort(getExpr(ctx));
        }else{
            node.addOption(getExpr(ctx));
        }
    }
}
