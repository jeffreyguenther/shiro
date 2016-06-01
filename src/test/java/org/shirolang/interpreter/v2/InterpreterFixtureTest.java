package org.shirolang.interpreter.v2;

import org.junit.Test;
import org.shirolang.fixtures.interpreter.InterpreterFixture;

import static org.junit.Assert.assertEquals;

public class InterpreterFixtureTest {
    @Test
    public void readsFiles(){
        assertEquals(
            "node Box begin\n" +
            "    input length Double\n" +
            "    input width Double\n" +
            "    input height Double\n" +
            "    update Multiply(length, width)\n" +
            "end", InterpreterFixture.fromFile("node.sro")
        );
    }
}
