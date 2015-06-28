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
import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;

/**
 * An listener to create expressions.
 *
 * @author jeffreyguenther
 */
public class InlineGraphBuilder extends GraphBuilder {
    private SFunc lastFuncProcessed;
    private final SGraph defaultGraph;
    private boolean isInLine;
    private boolean isAnonExpr;


    public InlineGraphBuilder(Library lib) {
        super(lib, lib.getDefaultGraph());
        lastFuncProcessed = null;
        defaultGraph = library.getDefaultGraph();
        scope.push(defaultGraph);
        isInLine = false;
        isAnonExpr = false;
    }

    /**
     * Store the tree of multi-functions associated with the parse tree
     *
     * @param node parse tree node to associate expression with
     * @param expr expression to associate with parse tree node
     */
    @Override
    protected void setExpr(ParseTree node, SFunc expr) {
        super.setExpr(node, expr);

        lastFuncProcessed = expr;
    }

    public SFunc getLastLine() {
        return lastFuncProcessed;
    }

    @Override
    public void enterAndExpr(@NotNull ShiroParser.AndExprContext ctx) {
        isAnonExpr = true;
    }

    @Override
    public void exitAnonExpr(@NotNull ShiroParser.AnonExprContext ctx) {
        isAnonExpr = false;

        if(pass == SECOND_PASS){
            defaultGraph.addAnonymousPort(getExpr(ctx.expr()));
        }
    }

    @Override
    public void exitAddExpr(ShiroParser.AddExprContext ctx) {
        super.exitAddExpr(ctx);
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
    public void enterPath(@NotNull ShiroParser.PathContext ctx) {
        if(isInLine && pass == FIRST_PASS) {
            super.enterPath(ctx);
        }
    }

    @Override
    public void exitPortDeclInit(@NotNull ShiroParser.PortDeclInitContext ctx) {
        if(isInLine && pass == SECOND_PASS) {
            super.exitPortDeclInit(ctx);
        }
    }

    @Override
    public void exitPortAssignment(@NotNull ShiroParser.PortAssignmentContext ctx) {
        if(isInLine && pass == SECOND_PASS) {
            super.exitPortAssignment(ctx);
        }
    }
}