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

import org.antlr.v4.runtime.misc.NotNull;
import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.SNode;
import org.shirolang.base.SymbolType;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.values.Path;
import org.shirolang.values.SIdent;
import org.shirolang.values.SReference;

/**
 * An ANTLR expression listener used to build Graph instances.
 */
public class GraphBuilder extends ShiroExpressionListener {

    public static final int FIRST_PASS = 1;
    public static final int SECOND_PASS = 2;
    protected int pass;

    public GraphBuilder(Library lib, SGraph g) {
        super(lib);
        scope.push(g);

        pass = FIRST_PASS;
    }

    /**
     * Sets the pass of the walker
     * @param pass
     */
    public void setPass(int pass) {
        this.pass = pass;
    }

    @Override
    public void exitNodeProduction(@NotNull ShiroParser.NodeProductionContext ctx) {
        if(pass == FIRST_PASS) {
            //  get the path of LHS of production operator
            SReference lhs = (SReference) getExpr(ctx.fullyQualifiedType());
            String fullyQualfiedType = lhs.getValue();

            // for each activation
            for (ShiroParser.ActivationContext ac : ctx.activation()) {
                String nodeName = ac.nodeName.getText();

                SGraph g = (SGraph) scope.peek();
                SNode producedNode = instantiateNode(fullyQualfiedType, ac.arguments(), nodeName, g);

                g.addNode(producedNode);

                if (ac.activeObject != null) {
                    String updatePort = ac.activeObject.getText();
                    try {
                        producedNode.setActiveOption(updatePort);
                    } catch (OptionNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public void exitPortAssignment(@NotNull ShiroParser.PortAssignmentContext ctx) {
        if(pass == SECOND_PASS) {
            // look up port based on the path
            try {
                SIdent lhs = (SIdent) getExpr(ctx.path());
                Path path = lhs.getPath();

                SFunc function = scope.peek().resolvePath(path);
                function.setSymbolType(SymbolType.PORT);
                if(function.getAccess().isReadWrite()){
                    function = assignArguments(function, ctx.arguments());
                }else{
                    throw new RuntimeException(path + " is " + function.getAccess() + ". It's inputs cannot be set.");
                }

            } catch (PathNotFoundException pnfe) {
                System.out.println(pnfe.getMessage());
            }
        }
    }

    @Override
    public void exitPortDeclInit(@NotNull ShiroParser.PortDeclInitContext ctx) {
        if(pass == SECOND_PASS) {
            super.exitPortDeclInit(ctx);
            SFunc port = getExpr(ctx);
            SGraph g = (SGraph) scope.peek();
            g.addPort(port);
        }
    }
}
