package org.shirolang.interpreter.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.shirolang.interpreter.ast.ASTSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    LexParseTest.class,
    ASTBuilderTest.class,
    ASTSuite.class,
    ASTVisitorTest.class,
    IncludeVisitorTest.class,
    NodeVisitorTest.class,
    IncludeNotFoundErrorTest.class
})
public class InterpreterV2Suite {
}
