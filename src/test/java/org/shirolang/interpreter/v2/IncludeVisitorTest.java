package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;
import org.shirolang.interpreter.CodeImporter;
import org.shirolang.interpreter.Library;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IncludeVisitorTest extends ParsingTest{
    @Test
    public void visitIncludes() throws IOException {
        // should have dependencies:
        // graph_with_includes.sro -> a_include.sro
        // graph_with_includes.sro -> b_include.sro
        // graph_with_includes.sro -> geom.sro

        // This test is a little deceiving as the path created when testing does not reference the normal
        // runtime resources location.
        Library lib = new Library();
        Path path = Paths.get(CodeImporter.class.getResource("graph_with_includes.sro").getPath());

        ParseTree program = parse(new ANTLRInputStream(CodeImporter.class.getResourceAsStream("graph_with_includes.sro")));
        ParseTreeWalker walker = new ParseTreeWalker();
        ASTBuilder ast = new ASTBuilder();
        walker.walk(ast, program);

        IncludeVisitor visitor = new IncludeVisitor(lib, path);
        visitor.visit(ast.getProgram());
        Assert.assertEquals(3, visitor.getSourceFiles().size());
    }
}
