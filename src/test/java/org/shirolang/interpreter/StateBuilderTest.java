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
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;
import org.shirolang.base.SState;

import java.io.IOException;

/**
 * Test the state builder
 */
public class StateBuilderTest extends ShiroBaseTest{
    @Test
    public void instantiateAlternativeWithJustGraph() throws IOException {
        ShiroParser parser = parse("graph_named_state.sro");
        ParseTree tree = parser.shiro();

        ParseTreeWalker walker = new ParseTreeWalker();
        DefinitionCollector dc = new DefinitionCollector();
        walker.walk(dc, tree);

        ParseTree s1 = dc.getAlternativeDefinitions().get("s1");
        Assert.assertNotNull(s1);

        Library l = new Library();
        StateBuilder sb = new StateBuilder(l);
        walker.walk(sb, s1);

        SState state = sb.getState();
        Assert.assertEquals("s1", state.getName());
        Assert.assertEquals("box_calc", state.getGraph());
        Assert.assertTrue(state.getSubjunctTable().isEmpty());
    }
}
