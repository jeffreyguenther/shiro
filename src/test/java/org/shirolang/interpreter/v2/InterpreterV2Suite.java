package org.shirolang.interpreter.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.shirolang.interpreter.ast.ASTSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    LexParseTest.class,
    ASTSuite.class,
    ASTBuilderTest.class,
    SymbolTableTest.class,
    IncludeVisitorTest.class,
    NodeVisitorTest.class,
    GraphVisitorTest.class,
    IncludeNotFoundErrorTest.class,
    RuntimeTest.class
})
public class InterpreterV2Suite {
}
