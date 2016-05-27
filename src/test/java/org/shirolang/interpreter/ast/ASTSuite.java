package org.shirolang.interpreter.ast;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProgramTest.class,
        IncludeStatementTest.class,
        StateDefinitionTest.class,
        OptionSelectionTest.class,
        GraphDefinitionTest.class,
        NodeDefinitionTest.class,
        FunctionDefinitionTest.class,
        FunctionCallTest.class,
        LiteralTest.class,
        BinaryOperationTest.class,
        UnaryOperationTest.class,
        ExpressionTest.class
})
public class ASTSuite {
}
