package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import org.shirolang.fixtures.ast.GraphDefinitionFixture;
import org.shirolang.fixtures.interpreter.InterpreterFixture;
import org.shirolang.interpreter.ShiroLexer;
import org.shirolang.interpreter.ShiroParser;
import org.shirolang.interpreter.ast.GraphDefinition;
import org.shirolang.interpreter.ast.Program;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ASTBuilderTest {
    @Test
    public void inLineGraph(){
        ASTBuilder builder = new ASTBuilder();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(builder, parse(InterpreterFixture.inlineGraph()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();

        GraphDefinition expected = GraphDefinitionFixture.inlineGraphMath();
        assertTrue(expected.equals(defaultGraph));
    }

    private ParseTree parse(String code){
        ShiroLexer lexer = new ShiroLexer(new ANTLRInputStream(code));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShiroParser parser = new ShiroParser(tokens);

        parser.setBuildParseTree(true);
        return parser.shiro();
    }
}
