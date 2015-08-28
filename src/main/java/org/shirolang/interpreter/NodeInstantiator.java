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

import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.SNode;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.values.Path;

import java.util.Objects;

/**
 * An ANTLR listener used to realize node parse trees
 *
 * @author jeffreyguenther
 */
public class NodeInstantiator extends ShiroExpressionListener {
    private SNode createdNode;

    public NodeInstantiator(Library lib, SGraph graph) {
        super(lib);
        scope.push(graph);
        createdNode = null;
    }

    public SNode getCreatedNode() {
        return createdNode;
    }

    @Override
    public void enterNodeDecl(ShiroParser.NodeDeclContext ctx) {
        // if there is at least one node on the scope stack
        // stack will always have at least 1 element because of the graph
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
    public void exitNodeDecl(ShiroParser.NodeDeclContext ctx) {
        // Set the default options
        // This depends on nodeInternal adding ports and nodes before hand.
        if(ctx.optionSelector() != null){
            // Get a reference to the current node
            SNode node = (SNode) scope.peek();

            // add the node's active option
            try {
                node.setDefaultOption(ctx.optionSelector().IDENT().getText());
                node.setActiveOption(ctx.optionSelector().IDENT().getText());
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
    public void exitPortDeclInit(ShiroParser.PortDeclInitContext ctx) {
        super.exitPortDeclInit(ctx);
        SFunc function = getExpr(ctx.funcDeclInit());

        // Add the port to it's encapsulating node
        SNode node = (SNode) scope.peek();
        boolean isOption = Objects.nonNull(ctx.OPTION());
        if(isOption){
            node.addOption(function);
        }else{
            node.addFunction(function);
        }
    }

    @Override
    public void exitPortDecl(ShiroParser.PortDeclContext ctx) {
        super.exitPortDecl(ctx);
        SFunc function = getExpr(ctx.funcDecl());

        SNode node = (SNode) scope.peek();
        boolean isOption = Objects.nonNull(ctx.OPTION());

        if(isOption){
            node.addOption(function);
        }else{
            node.addFunction(function);
        }

    }
}
