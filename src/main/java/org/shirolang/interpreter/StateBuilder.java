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
import org.shirolang.base.SState;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class StateBuilder extends ShiroExpressionListener{
    private Map<String, String> subjunctTable;
    private SState state;
    private String graphName;

    public StateBuilder(Library lib) {
        super(lib);
        subjunctTable = new HashMap<>();
    }

    public SState getState(){
        return state;
    }

    @Override
    public void enterStateStmt(@NotNull ShiroParser.StateStmtContext ctx) {
        String stateName = ctx.stateName().getText();
        state = new SState(stateName);
        state.setSubjunctTable(subjunctTable);
    }

    @Override
    public void enterStateGraphSelection(@NotNull ShiroParser.StateGraphSelectionContext ctx) {
        String graphName = "";
        if (ctx.IDENT() != null){
            graphName = ctx.IDENT().getText();
        }

        state.setGraph(graphName);
    }

    @Override
    public void enterStateActivation(@NotNull ShiroParser.StateActivationContext ctx) {
        String nodeName = ctx.nodeName.getText();
        String activeOption = ctx.activeObject.getText();
        subjunctTable.put(nodeName, activeOption);
    }
}
