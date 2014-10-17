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
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.Scope;
import org.shirolang.exceptions.GraphNotFoundException;
import org.shirolang.functions.math.*;
import org.shirolang.values.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * An listener to create expressions.
 *
 * @author jeffreyguenther
 */
public class InlineGraphBuilder extends GraphBuilder {
    public static final int FIRST_PASS = 1;
    public static final int SECOND_PASS = 2;
    private SFunc lastFuncProcessed;
    private final SGraph defaultGraph;
    private boolean isInLine;
    private int pass;


    public InlineGraphBuilder(Library lib) {
        super(lib, lib.getDefaultGraph());
        lastFuncProcessed = null;
        defaultGraph = library.getDefaultGraph();
        scope.push(defaultGraph);
        isInLine = false;
        pass = FIRST_PASS;
    }

    /**
     * Sets the pass of the walker
     * @param pass
     */
    public void setPass(int pass) {
        this.pass = pass;
    }

    /**
     * Store the tree of multi-functions associated with the parse tree
     *
     * @param node parse tree node to associate expresssion with
     * @param expr expression to associate with parse tree node
     */
    @Override
    protected void setExpr(ParseTree node, SFunc expr) {
        super.setExpr(node, expr);

        if(expr.getName().isEmpty()){
            defaultGraph.addAnonymousPort(expr);
        }else{
            defaultGraph.addPort(expr);
        }

        lastFuncProcessed = expr;
    }

    public SFunc getLastLine() {
        return lastFuncProcessed;
    }

    @Override
    public void enterInLineExpr(@NotNull ShiroParser.InLineExprContext ctx) {
        isInLine = true;
    }

    @Override
    public void exitInLineExpr(@NotNull ShiroParser.InLineExprContext ctx) {
        isInLine = false;
    }

    @Override
    public void exitNodeProduction(@NotNull ShiroParser.NodeProductionContext ctx) {
        if(isInLine && pass == FIRST_PASS) {
            super.exitNodeProduction(ctx);
        }
    }

    @Override
    public void exitPortDeclInit(@NotNull ShiroParser.PortDeclInitContext ctx) {
        if(isInLine && pass == SECOND_PASS) {
            super.exitPortDeclInit(ctx);
        }
    }

    @Override
    public void enterPath(@NotNull ShiroParser.PathContext ctx) {
        if(isInLine && pass == SECOND_PASS) {

            super.enterPath(ctx);
            // add the symbol to the runtime. Throw a runtime exception
            try {
                library.addSymbolToGraph(Library.DEFAULT_GRAPH_NAME, getExpr(ctx));
            } catch (GraphNotFoundException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    public void exitPortAssignment(@NotNull ShiroParser.PortAssignmentContext ctx) {
        if(isInLine && pass == SECOND_PASS) {
            super.exitPortAssignment(ctx);
        }
    }
}