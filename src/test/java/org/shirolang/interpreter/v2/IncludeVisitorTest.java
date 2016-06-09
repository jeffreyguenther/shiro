package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import org.shirolang.dag.DependencyRelation;
import org.shirolang.interpreter.CodeImporter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IncludeVisitorTest extends ParsingTest{
    @Test
    public void visitIncludes() throws IOException {
        // should have dependencies:
        // graph_with_includes.sro -> a_include.sro
        // graph_with_includes.sro -> b_include.sro
        // graph_with_includes.sro -> geom.sro

        // This test is a little deceiving as the path created when testing does not reference the normal
        // runtime resources location.
        SymbolTable symbolTable = new SymbolTable();
        Path path = Paths.get(CodeImporter.class.getResource("graph_with_includes.sro").getPath());

        ParseTree program = parse(new ANTLRInputStream(CodeImporter.class.getResourceAsStream("graph_with_includes.sro")));
        ParseTreeWalker walker = new ParseTreeWalker();
        ASTBuilder ast = new ASTBuilder();
        walker.walk(ast, program);

        IncludeVisitor visitor = new IncludeVisitor(symbolTable, path);
        Set<DependencyRelation<Path>> includes = visitor.visit(ast.getProgram());
        assertEquals(3, includes.size());
    }

    @Test
    public void visitHasErrors() throws IOException {
        SymbolTable symbolTable = new SymbolTable();
        Path path = Paths.get(CodeImporter.class.getResource("graph_with_include_errors.sro").getPath());

        ParseTree program = parse(new ANTLRInputStream(CodeImporter.class.getResourceAsStream("graph_with_include_errors.sro")));
        ParseTreeWalker walker = new ParseTreeWalker();
        ASTBuilder ast = new ASTBuilder();
        walker.walk(ast, program);

        IncludeVisitor visitor = new IncludeVisitor(symbolTable, path);
        Set<DependencyRelation<Path>> includes = visitor.visit(ast.getProgram());
        assertTrue(visitor.hasErrors());
        assertEquals("binclude.sro cannot be found.", visitor.getErrors().get(0).getMessage());
        assertEquals(2, includes.size());
    }
}