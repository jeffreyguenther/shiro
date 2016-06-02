package org.shirolang.interpreter.ast;

import org.junit.Test;
import org.shirolang.fixtures.ast.ReferenceFixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReferenceTest {
    @Test
    public void withType(){
        Reference ref = ReferenceFixture.withType();
        assertEquals("~Box()", ref.toCode());
    }

    @Test
    public void withTypeAndOutputSelector(){
        Reference ref = ReferenceFixture.withTypeAndOutputSelector();
        assertEquals("~Box()[b]", ref.toCode());
    }

    @Test
    public void withNameTypeAndOption(){
        Reference ref = ReferenceFixture.withNameTypeAndOption();
        assertEquals("~Box[a]()", ref.toCode());
    }

    @Test
    public void withNameTypedOptionAndOutputSelector(){
        Reference ref = ReferenceFixture.withNameTypedOptionAndOutputSelector();
        assertEquals("~Box[b]()[a]", ref.toCode());
    }

    @Test
    public void withTypeAndListOfArgs(){
        Reference ref = ReferenceFixture.withTypeAndListOfArgs();
        assertEquals("~Box(1, 2, 3)", ref.toCode());
    }

    @Test
    public void withTypeAndKeywordArgs(){
        Reference ref = ReferenceFixture.withTypeAndKeywordArgs();
        assertEquals("~Box(a: 1, b: 10)", ref.toCode());
    }

    @Test
    public void withTypeOptionAndListOfArgs(){
        Reference ref = ReferenceFixture.withTypeOptionAndListOfArgs();
        assertEquals("~Box[a](1, 2, 3)", ref.toCode());
    }

    @Test
    public void withTypeOptionListOfArgsAndOutputSelector(){
        Reference ref = ReferenceFixture.withTypeOptionListOfArgsAndOutputSelector();
        assertEquals("~Box[a](1, 2, 3)[b]", ref.toCode());
    }

    @Test
    public void withTypeOptionAndKeywordArgs(){
        Reference ref = ReferenceFixture.withTypeOptionAndKeywordArgs();
        assertEquals("~Box[a](a: 1, b: 10)", ref.toCode());
    }

    @Test
    public void withTypeOptionKeywordArgsAndOutputSelector(){
        Reference ref = ReferenceFixture.withTypeOptionKeywordArgsAndOutputSelector();
        assertEquals("~Box[a](a: 1, b: 10)[b]", ref.toCode());
    }

    @Test
    public void equals(){
        Reference r1 = new Reference("A", "a", "x");
        Reference r2 = new Reference("A", "a", "x");
        assertTrue(r2.equals(r1));

        Reference r3 = new Reference("A", "a");
        Reference r4 = new Reference("A", "a");
        assertTrue(r3.equals(r4));

        Reference r5 = new Reference("A");
        Reference r6 = new Reference("A");
        assertTrue(r5.equals(r6));
    }
}