package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Assert;
import org.junit.Test;
import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.SNode;
import org.shirolang.interpreter.CodeImporter;
import org.shirolang.interpreter.ast.Program;
import org.shirolang.values.SDouble;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class NodeVisitorTest extends ParsingTest{
    @Test
    public void createNode() throws IOException {
        SymbolTable symbolTable = new SymbolTable();
        Program program = generateProgram(new ANTLRInputStream(CodeImporter.class.getResourceAsStream("node.sro")));

        NodeVisitor visitor = new NodeVisitor(symbolTable, symbolTable.getDefaultGraph());

        SNode createdNode = visitor.visit(program.getNodeDefs().get(0));

        assertEquals("Box", createdNode.getName());
        Assert.assertNotNull(createdNode.getPort("length"));
        Assert.assertNotNull(createdNode.getPort("width"));
        Assert.assertNotNull(createdNode.getPort("height"));
        SFunc update = createdNode.getPort("update");
        Assert.assertNotNull(update);
        assertEquals(2, update.getInputs().size());
    }

    @Test
    public void createNodeWithMapArgs() throws IOException {
        SymbolTable symbolTable = new SymbolTable();
        Program program = generateProgram(new ANTLRInputStream(CodeImporter.class.getResourceAsStream("node_with_map_args.sro")));

        NodeVisitor visitor = new NodeVisitor(symbolTable, symbolTable.getDefaultGraph());

        SNode createdNode = visitor.visit(program.getNodeDefs().get(0));

        assertEquals("Box", createdNode.getName());
        Assert.assertNotNull(createdNode.getPort("length"));
        Assert.assertNotNull(createdNode.getPort("width"));
        Assert.assertNotNull(createdNode.getPort("height"));
        SFunc update = createdNode.getPort("update");
        Assert.assertNotNull(update);
        assertEquals(2, update.getInputs().size());
    }

    @Test
    public void createNodeWithPortAssignment() throws IOException {
        SymbolTable symbolTable = new SymbolTable();
        Program program = generateProgram(new ANTLRInputStream(CodeImporter.class.getResourceAsStream("node_with_port_assignment.sro")));

        SGraph defaultGraph = symbolTable.getDefaultGraph();
        NodeVisitor visitor = new NodeVisitor(symbolTable, defaultGraph);


        SNode createdNode = visitor.visit(program.getNodeDefs().get(0));
        defaultGraph.addFunction(createdNode);

        visitor.setPass(NodeVisitor.SECOND_PASS);
        visitor.visit(program.getNodeDefs().get(0));

        assertEquals("Box", createdNode.getName());
        SDouble length = (SDouble) createdNode.getPort("length");
        length.evaluate();
        assertEquals(10, length.getValue(), 1E-15);
        Assert.assertNotNull(createdNode.getPort("width"));
        Assert.assertNotNull(createdNode.getPort("height"));
        SFunc update = createdNode.getPort("update");
        Assert.assertNotNull(update);
        assertEquals(2, update.getInputs().size());
    }
}