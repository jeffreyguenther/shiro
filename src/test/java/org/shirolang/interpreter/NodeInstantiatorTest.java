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
import org.shirolang.base.SFunc;
import org.shirolang.base.SNode;

import java.io.IOException;

/**
 *
 */
public class NodeInstantiatorTest extends ShiroBaseTest {
    @Test
    public void produceNode() throws IOException {
        Library l = new Library();

        ShiroParser parse = parse("node_with_eval.sro");
        ParseTree shiro = parse.shiro();
        ParseTreeWalker walker = new ParseTreeWalker();
        NodeInstantiator produceNode = new NodeInstantiator(l, l.getDefaultGraph());
        walker.walk(produceNode, shiro);

        SNode createdNode = produceNode.getCreatedNode();

        Assert.assertEquals("Box", createdNode.getName());
        Assert.assertNotNull(createdNode.getPort("length"));
        Assert.assertNotNull(createdNode.getPort("width"));
        Assert.assertNotNull(createdNode.getPort("height"));
        SFunc update = createdNode.getPort("update");
        Assert.assertNotNull(update);
        Assert.assertEquals(2, update.getArgs().size());

    }
    @Test
    public void produceNestedNode(){
        Assert.fail("nested nodes not yet implemented");
    }
}
