package org.shirolang.interpreter.ast;

import org.junit.Test;
import org.shirolang.fixtures.FunctionDefinitionFixture;
import org.shirolang.interpreter.ast.Function.ArgumentsType;

import static org.junit.Assert.*;

public class FunctionDefinitionTest {
    @Test
    public void getTypeAndName(){
        String name = "b";
        String type = "Box";
        FunctionDefinition def = new FunctionDefinition(type, name);
        assertEquals(type, def.getType());
        assertEquals(name, def.getName());
    }

    @Test
    public void hasActiveOption(){
        FunctionDefinition def = FunctionDefinitionFixture.withNameAndType();
        assertFalse(def.hasActiveOption());
        assertTrue(def.getOption().isEmpty());

        FunctionDefinition defWithOption = FunctionDefinitionFixture.withNameTypeAndOption();
        assertTrue(defWithOption.hasActiveOption());
        assertFalse(defWithOption.getOption().isEmpty());
    }

    @Test
    public void hasArgsDefined(){
        FunctionDefinition def = FunctionDefinitionFixture.withNameAndType();
        assertFalse(def.hasArgsDefined());
        assertEquals(ArgumentsType.LIST, def.getArgsType());

        FunctionDefinition def1 = FunctionDefinitionFixture.withNameTypeAndKeywordArgs();
        assertTrue(def1.hasArgsDefined());
        assertEquals(ArgumentsType.KEYWORDS, def1.getArgsType());

        FunctionDefinition def2 = FunctionDefinitionFixture.withNameTypeAndListOfArgs();
        assertTrue(def2.hasArgsDefined());
        assertEquals(ArgumentsType.LIST, def2.getArgsType());
    }

    @Test
    public void getTypeOfArgs(){
        FunctionDefinition def = FunctionDefinitionFixture.withNameAndType();
        assertEquals(ArgumentsType.LIST, def.getArgsType());
    }

    @Test
    public void canOutputFunctionDefAsCode(){
        FunctionDefinition def = FunctionDefinitionFixture.withNameAndType();
        assertEquals("b Box", def.toCode());

        FunctionDefinition defWithTypeAndKeyword = FunctionDefinitionFixture.withNameTypeAndKeywordArgs();
        assertEquals("b Box(a: 1, b: 10)", defWithTypeAndKeyword.toCode());

        FunctionDefinition defWithTypeAndListArgs = FunctionDefinitionFixture.withNameTypeAndListOfArgs();
        assertEquals("b Box(1, 2, 3)", defWithTypeAndListArgs.toCode());

        FunctionDefinition defWithOption = FunctionDefinitionFixture.withNameTypeAndOption();
        assertEquals("b Box[a]", defWithOption.toCode());

        FunctionDefinition defWithTypeOptionAndListArgs = FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs();
        assertEquals("b Box[a](1, 2, 3)", defWithTypeOptionAndListArgs.toCode());

        FunctionDefinition defWithTypeOptionAndKeywordArgs = FunctionDefinitionFixture.withNameTypeOptionAndKeywordArgs();
        assertEquals("b Box[a](a: 1, b: 10)", defWithTypeOptionAndKeywordArgs.toCode());
    }
}
