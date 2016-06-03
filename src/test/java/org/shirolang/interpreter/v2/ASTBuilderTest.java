package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.BeforeClass;
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
    private static ParseTreeWalker walker;
    private ASTBuilder builder;

    @BeforeClass
    public static void setupTest(){
        walker = new ParseTreeWalker();
    }

    @Before
    public void beforeTest(){
        builder= new ASTBuilder();
    }

    @Test
    public void add(){
        walker.walk(builder, parse(InterpreterFixture.add()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.add();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void addIntegerAndFloat(){
        walker.walk(builder, parse(InterpreterFixture.addIntegerAndFloat()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.addIntegerAndFloat();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void subtractIntegerAndFloat(){
        walker.walk(builder, parse(InterpreterFixture.subtractIntegerAndFloat()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.subtractIntegerAndFloat();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void multiplyIntegerAndFloat(){
        walker.walk(builder, parse(InterpreterFixture.multiplyIntegerAndFloat()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.multiplyIntegerAndFloat();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void divideIntegerAndFloat(){
        walker.walk(builder, parse(InterpreterFixture.divideIntegerAndFloat()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.divideIntegerAndFloat();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void moduloIntegerAndFloat(){
        walker.walk(builder, parse(InterpreterFixture.moduloIntegerAndFloat()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.moduloIntegerAndFloat();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void negateFloat(){
        walker.walk(builder, parse(InterpreterFixture.negateFloat()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.negateFloat();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void negateBoolean(){
        walker.walk(builder, parse(InterpreterFixture.negateBoolean()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.negateBoolean();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void parens(){
        walker.walk(builder, parse(InterpreterFixture.parens()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.parens();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void and(){
        walker.walk(builder, parse(InterpreterFixture.and()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.and();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void or(){
        walker.walk(builder, parse(InterpreterFixture.or()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.or();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void greaterThan(){
        walker.walk(builder, parse(InterpreterFixture.greaterThan()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.greaterThan();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void greaterThanEqual(){
        walker.walk(builder, parse(InterpreterFixture.greaterThanEqual()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.greaterThanEqual();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void lessThan(){
        walker.walk(builder, parse(InterpreterFixture.lessThan()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.lessThan();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void equal(){
        walker.walk(builder, parse(InterpreterFixture.equal()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.equal();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void notEqual(){
        walker.walk(builder, parse(InterpreterFixture.notEqual()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.notEqual();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void lessThanEqual(){
        walker.walk(builder, parse(InterpreterFixture.lessThanEqual()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.lessThanEqual();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void simplePath(){
        walker.walk(builder, parse(InterpreterFixture.simplePath()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.simplePath();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void inputsWithNumberedIndexInPath(){
        walker.walk(builder, parse(InterpreterFixture.inputsWithNumberedIndexInPath()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.inputsWithNumberedIndexInPath();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void inputsWithNamedIndexInPath(){
        walker.walk(builder, parse(InterpreterFixture.inputsWithNamedIndexInPath()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.inputsWithNamedIndexInPath();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void outputsWithNumberedIndexInPath(){
        walker.walk(builder, parse(InterpreterFixture.outputsWithNumberedIndexInPath()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.outputsWithNumberedIndexInPath();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void outputsWithNamedIndexInPath(){
        walker.walk(builder, parse(InterpreterFixture.outputsWithNamedIndexInPath()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.outputsWithNamedIndexInPath();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void fullyQualifiedType(){
        walker.walk(builder, parse(InterpreterFixture.fullyQualifiedType()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.fullyQualifiedType();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void reference(){
        walker.walk(builder, parse(InterpreterFixture.reference()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.reference();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void referenceWithOptionSelection(){
        walker.walk(builder, parse(InterpreterFixture.referenceWithOptionSelection()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.referenceWithOptionSelection();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void referenceWithKeywordArguments(){
        walker.walk(builder, parse(InterpreterFixture.referenceWithKeywordArguments()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.referenceWithKeywordArguments();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void referenceWithOptionSelectionAndKeywordArguments(){
        walker.walk(builder, parse(InterpreterFixture.referenceWithOptionSelectionAndKeywordArguments()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.referenceWithOptionSelectionAndKeywordArguments();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void referenceWithOptionSelectionKeywordArgumentsAndIndexOutputSelector(){
        walker.walk(builder, parse(InterpreterFixture.referenceWithOptionSelectionKeywordArgumentsAndIndexOutputSelector()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.referenceWithOptionSelectionKeywordArgumentsAndIndexOutputSelector();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void referenceWithOptionSelectionKeywordArgumentsAndKeywordOutputSelector(){
        walker.walk(builder, parse(InterpreterFixture.referenceWithOptionSelectionKeywordArgumentsAndKeywordOutputSelector()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.referenceWithOptionSelectionKeywordArgumentsAndKeywordOutputSelector();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void referenceWithArguments(){
        walker.walk(builder, parse(InterpreterFixture.referenceWithArguments()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.referenceWithArguments();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void referenceWithOptionSelectionAndArguments(){
        walker.walk(builder, parse(InterpreterFixture.referenceWithOptionSelectionAndArguments()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.referenceWithOptionSelectionAndArguments();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void referenceWithOptionSelectionAndArgumentsAndIndexOutputSelector(){
        walker.walk(builder, parse(InterpreterFixture.referenceWithOptionSelectionAndArgumentsAndIndexOutputSelector()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.referenceWithOptionSelectionAndArgumentsAndIndexOutputSelector();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void functionWithArgs(){
        walker.walk(builder, parse(InterpreterFixture.functionWithArgs()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.functionWithArgs();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void functionWithOptionAndArgs(){
        walker.walk(builder, parse(InterpreterFixture.functionWithOptionAndArgs()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.functionWithOptionAndArgs();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void nestedFunctionCalls(){
        walker.walk(builder, parse(InterpreterFixture.nestedFunctionCalls()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.nestedFunctionCalls();
        assertTrue(expected.equals(defaultGraph));
    }

    @Test
    public void referenceWithOptionSelectionAndArgumentsAndKeywordOutputSelector(){
        walker.walk(builder, parse(InterpreterFixture.referenceWithOptionSelectionAndArgumentsAndKeywordOutputSelector()));

        Program program = builder.getProgram();
        GraphDefinition defaultGraph = program.getDefaultGraph();
        GraphDefinition expected = GraphDefinitionFixture.referenceWithOptionSelectionAndArgumentsAndKeywordOutputSelector();
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
