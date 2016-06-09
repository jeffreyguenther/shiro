package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Assert;
import org.junit.Test;
import org.shirolang.base.SFunc;
import org.shirolang.base.SNode;
import org.shirolang.interpreter.CodeImporter;
import org.shirolang.interpreter.Library;
import org.shirolang.interpreter.ast.Program;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NodeVisitorTest extends ParsingTest{
    @Test
    public void createNode() throws IOException {
        Library l = new Library();
        Program program = generateProgram(new ANTLRInputStream(CodeImporter.class.getResourceAsStream("node.sro")));

        NodeVisitor visitor = new NodeVisitor(l, l.getDefaultGraph());

        SNode createdNode = visitor.visit(program.getNodeDefs().get(0));

        Assert.assertEquals("Box", createdNode.getName());
        Assert.assertNotNull(createdNode.getPort("length"));
        Assert.assertNotNull(createdNode.getPort("width"));
        Assert.assertNotNull(createdNode.getPort("height"));
        SFunc update = createdNode.getPort("update");
        Assert.assertNotNull(update);
        Assert.assertEquals(2, update.getInputs().size());
    }
}