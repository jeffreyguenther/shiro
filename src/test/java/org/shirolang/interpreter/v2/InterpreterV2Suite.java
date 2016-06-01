package org.shirolang.interpreter.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.shirolang.interpreter.ast.ASTSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    InterpreterFixtureTest.class,
    LexParseTest.class,
    ASTSuite.class
})
public class InterpreterV2Suite {
}
