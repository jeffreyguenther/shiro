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
import static org.junit.Assert.assertNotNull;

public class GraphVisitorTest extends ParsingTest{
    @Test
    public void anonymousExpr() throws IOException {
        ProgramEvaluator evaluator = new ProgramEvaluator(new SymbolTable());

        Program program = generateProgram(new ANTLRInputStream(CodeImporter.class.getResourceAsStream("graph_inline_expression.sro")));
        assertEquals(3, program.getDefaultGraph().getFunctions().size());

        GraphVisitor visitor = new GraphVisitor(evaluator);
        SGraph graph = visitor.visit(program.getDefaultGraph());
        assertNotNull(graph);
        assertEquals(1, graph.getAnonymousPorts().size());
        assertNotNull(graph.getPort("a"));
        assertNotNull(graph.getPort("b"));
        assertNotNull(graph.getPort("c"));
    }

    @Test
    public void anonymousExprWithNode() throws IOException {
        SymbolTable table = new SymbolTable();
        ProgramEvaluator evaluator = new ProgramEvaluator(table);

        Program program = generateProgram(new ANTLRInputStream(CodeImporter.class.getResourceAsStream("graph_inline_node_instantiation_assignment.sro")));
        assertEquals(3, program.getDefaultGraph().getAssignments().size());

        table.setNodeDefs(program.getNodeDefsByName());

        GraphVisitor visitor = new GraphVisitor(evaluator);
        SGraph graph = visitor.visit(program.getDefaultGraph());
        visitor.setPass(MultiPassVisitor.SECOND_PASS);
        visitor.visit(program.getDefaultGraph());
        assertNotNull(graph);

        SNode n = graph.getNode("b");
        assertNotNull(n);

        SDouble length = (SDouble) n.getPort("length");
        length.evaluate();
        assertEquals(100.0, length.getValue(), 1E-15);
        assertNotNull(length);

        assertNotNull(n.getPort("width"));
        assertNotNull(n.getPort("height"));
    }

    @Test
    public void anonymousGraphWithNodeWithKeywordArgs() throws IOException {
        SymbolTable table = new SymbolTable();
        ProgramEvaluator evaluator = new ProgramEvaluator(table);

        Program program = generateProgram(new ANTLRInputStream(CodeImporter.class.getResourceAsStream("graph_inline_node_instantiation_named_args.sro")));
        table.setNodeDefs(program.getNodeDefsByName());

        GraphVisitor visitor = new GraphVisitor(evaluator);
        SGraph graph = visitor.visit(program.getDefaultGraph());
        assertNotNull(graph);

        SNode n = graph.getNode("b");
        assertNotNull(n);
        SDouble length = (SDouble) n.getPort("length");
        length.evaluate();
        assertEquals(100.0, length.getValue(), 1E-15);

        assertNotNull(n.getPort("width"));
        assertNotNull(n.getPort("height"));
    }

    @Test
    public void anonymousGraphWithOptions() throws IOException {
        SymbolTable table = new SymbolTable();
        ProgramEvaluator evaluator = new ProgramEvaluator(table);

        Program program = generateProgram(new ANTLRInputStream(CodeImporter.class.getResourceAsStream("graph_inline_node_instantiation_with_options.sro")));
        table.setNodeDefs(program.getNodeDefsByName());

        GraphVisitor visitor = new GraphVisitor(evaluator);
        SGraph graph = visitor.visit(program.getDefaultGraph());
        visitor.setPass(MultiPassVisitor.SECOND_PASS);
        visitor.visit(program.getDefaultGraph());
        assertNotNull(graph);

        SNode n = graph.getNode("b");
        SFunc activeOption = n.getActiveOption();
        assertEquals("base", activeOption.getName());
        assertNotNull(n);

        SDouble length = (SDouble) n.getPort("length");
        length.evaluate();
        assertEquals(100.0, length.getValue(), 1E-15);

        assertNotNull(n.getPort("width"));
        assertNotNull(n.getPort("height"));
    }

    @Test
    public void createNestedNode() throws IOException {
        SymbolTable table = new SymbolTable();
        ProgramEvaluator evaluator = new ProgramEvaluator(table);

        Program program = generateProgram(new ANTLRInputStream(CodeImporter.class.getResourceAsStream("node_nested_definition.sro")));
        table.setNodeDefs(program.getNodeDefsByName());

        GraphVisitor visitor = new GraphVisitor(evaluator);
        SGraph graph = visitor.visit(program.getDefaultGraph());
        visitor.setPass(MultiPassVisitor.SECOND_PASS);
        visitor.visit(program.getDefaultGraph());
        assertNotNull(graph);

        SNode nA = graph.getNode("a");
        assertEquals("A", nA.getType());

        SNode nB = graph.getNode("b");
        assertEquals("B", nB.getType());
    }
}