package org.shirolang.functions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Tests the internal runtime functions
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MathSuite.class,
        FinanceSuite.class
})
public class FunctionsSuite {
}
