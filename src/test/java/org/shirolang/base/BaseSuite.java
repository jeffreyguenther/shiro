package org.shirolang.base;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Tests the base classes
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    SGraphTest.class,
    SIndexedMapTest.class,
    SNodeTest.class,
    SStateTest.class,
    TypedValueTest.class
})
public class BaseSuite {
}
