package org.shirolang.interpreter;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;
import org.shirolang.base.SFunc;
import org.shirolang.base.SNode;

import java.io.IOException;

public class NodeInstantiatorTest extends ShiroBaseTest {
    @Test
    public void produceNode() throws IOException {
        Library l = new Library();

        ShiroParser parse = parse("node.sro");
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

        ShiroParser parse = parse("node_nested_definition.sro");
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
