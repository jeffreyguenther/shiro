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

import java.util.List;
import org.antlr.v4.runtime.Token;
import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.SNode;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.values.Path;
import org.shirolang.values.SIdent;

/**
 * An ANTLR listener used to realize node parse trees
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
            
            // add the node's active option
            try {
                node.setDefaultOption(ctx.activeSelector().IDENT().getText());
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

    @Override
    public void exitOptionalNodeProduction(ShiroParser.OptionalNodeProductionContext ctx) {

        //  get the path of LHS of production operator
        SIdent lhs = (SIdent) getExpr(ctx.nodeProduction().path());
        Path p = lhs.getPath();

        SNode parentNode = (SNode) scope.peek();
        // for each activation
        for (ShiroParser.ActivationContext ac : ctx.nodeProduction().activation()) {
            String nodeName = ac.nodeName.getText();

            SNode producedNode = (SNode) library.instantiateNode(graph, p, nodeName);

            ShiroParser.NodeAssignmentContext assignment = ac.nodeAssignment();
            if(assignment != null){
                if( assignment.argMap() != null ){
                    List<Token> keys = assignment.argMap().keys;
                    List<ShiroParser.ExprContext> values = assignment.argMap().values;

                    for(int i = 0; i < keys.size(); i++){
                        SFunc port = producedNode.getPort(keys.get(i).getText());

                        if(port == null){
                            throw new RuntimeException(keys.get(i).getText() + " cannot be found in " + producedNode.getFullName());
                        }

                        port.setInput(0, getExpr(values.get(i)));
                    }
                }

                if(assignment.mfparams() != null ){
                    List<ShiroParser.ExprContext> exprs = assignment.mfparams().expr();
                    for (int i = 0; i < exprs.size(); i++) {
                        SFunc port = producedNode.getPort(i);
                        port.setInput(0, getExpr(exprs.get(i)));
                    }
                }
            }

            if (ac.activeObject != null) {
                String updatePort = ac.activeObject.getText();
                try {
                    producedNode.setActiveOption(updatePort);
                } catch (OptionNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }

            if(ctx.OPTION() == null){
                parentNode.addNestedNode(producedNode);
            }else{
                parentNode.addOption(producedNode);
            }
        }
    }
    
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
//     * @param p port to add
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
