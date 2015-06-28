package org.shirolang.functions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.shirolang.functions.lists.SMapTest;

/**
 * Tests the internal runtime functions
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SMapTest.class,
        MathSuite.class,
        FinanceSuite.class
})
public class FunctionsSuite {
}
