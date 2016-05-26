package org.shirolang.functions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.shirolang.functions.conditionals.SConditionalReturnTest;
import org.shirolang.functions.finance.FinanceSuite;
import org.shirolang.functions.lists.SMapTest;
import org.shirolang.functions.math.MathSuite;

/**
 * Tests the internal runtime functions
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SConditionalReturnTest.class,
        FinanceSuite.class,
        SMapTest.class,
        MathSuite.class
})
public class FunctionsSuite {
}
