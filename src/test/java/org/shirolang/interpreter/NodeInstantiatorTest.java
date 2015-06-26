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
 * Tests node instantiation
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
        Assert.assertEquals(2, update.getInputs().size());

    }
    @Test
    public void produceNestedNode() throws IOException {
        Library l = new Library();

        ShiroParser parse = parse("nodes_nested.sro");
        ParseTree shiro = parse.shiro();

        DefinitionCollector c = new DefinitionCollector();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(c, shiro);

        NodeInstantiator produceNode = new NodeInstantiator(l, l.getDefaultGraph());
        walker.walk(produceNode, c.getNodeDefinitions().get("A"));

        NodeInstantiator b = new NodeInstantiator(l, l.getDefaultGraph());
        walker.walk(b, c.getNodeDefinitions().get("A.B"));

        SNode createdNode = produceNode.getCreatedNode();
        Assert.assertEquals("A", createdNode.getName());

        SNode bNode = b.getCreatedNode();
        Assert.assertEquals("^.B", bNode.getFullName());
        Assert.assertEquals("B", bNode.getType());

        // TODO Might have to add full type field to show the objects full type definition
    }
}
