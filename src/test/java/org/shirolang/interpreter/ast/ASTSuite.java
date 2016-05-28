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
        PortDefinitionTest.class,
        PortAssignmentTest.class,
        FunctionDefinitionTest.class,
        FunctionCallTest.class,
        LiteralTest.class,
        FullyQualifiedTypeTest.class,
        ReferenceTest.class,
        BinaryOperationTest.class,
        UnaryOperationTest.class,
        ExpressionTest.class
})
public class ASTSuite {
}
