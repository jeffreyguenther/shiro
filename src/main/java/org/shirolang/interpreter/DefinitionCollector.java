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

import org.antlr.v4.runtime.tree.ParseTree;
import org.shirolang.interpreter.ShiroBaseListener;

import java.util.HashMap;
import java.util.Map;

/**
 *  Collects all of the object definitions
 */
public class DefinitionCollector extends ShiroBaseListener {
    private Map<String, ParseTree> defs;
    private Map<String, ParseTree> alternativeDefs;
    private Map<String, ParseTree> graphs;
    private boolean locked;

    public DefinitionCollector() {
        super();
        defs = new HashMap<>();
        alternativeDefs = new HashMap<>();
        graphs = new HashMap<>();
        locked = false;
    }

    /**
     * Get the node ParseTrees
     * @return map of node definitions mapped by name
     */
    public Map<String, ParseTree> getNodeDefinitions() {
        return defs;
    }

    /**
     * Get the alternative ParseTrees
     * @return map of parse trees mapped by name
     */
    public Map<String, ParseTree> getAlternativeDefinitions(){
        return alternativeDefs;
    }

    /**
     * Get the parse tree for the graph definition
     * @return parse tree for the graph definition
     */
    public Map<String, ParseTree> getGraphs() {
        return graphs;
    }

    @Override
    public void enterNodestmt(ShiroParser.NodestmtContext ctx) {
        // The locked flag is used to prevent nested nodes from being captured
        // as individual trees. Nested nodes will be children of their root
        // node
        if(!locked){
            String name = ctx.MFNAME().getText();
            defs.put(name, ctx);
            locked = true;
        }
    }

    @Override
    public void exitNodestmt(ShiroParser.NodestmtContext ctx) {
        locked = false;
    }

//    @Override
//    public void enterGraphDecl(ShiroParser.GraphDeclContext ctx) {
//        graphs.put(ctx.IDENT().getText(), ctx);
//    }
//
//    @Override
//    public void enterStatestmt(ShiroParser.StatestmtContext ctx) {
//        alternativeDefs.put(ctx.stateName().getText(), ctx);
//    }
}
