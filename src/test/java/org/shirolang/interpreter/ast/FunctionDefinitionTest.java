package org.shirolang.interpreter.ast;

import org.junit.Test;
import org.shirolang.fixtures.ast.FunctionDefinitionFixture;
import org.shirolang.interpreter.ast.FunctionBase.ArgumentsType;

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

    @Test
    public void equals(){
        FunctionDefinition def = FunctionDefinitionFixture.withNameAndType();
        FunctionDefinition def1 = FunctionDefinitionFixture.withNameAndType();
        assertTrue(def.equals(def1));

        FunctionDefinition defWithTypeAndKeyword = FunctionDefinitionFixture.withNameTypeAndKeywordArgs();
        FunctionDefinition defWithTypeAndKeyword1 = FunctionDefinitionFixture.withNameTypeAndKeywordArgs();
        assertTrue(defWithTypeAndKeyword.equals(defWithTypeAndKeyword1));

        FunctionDefinition defWithTypeAndListArgs = FunctionDefinitionFixture.withNameTypeAndListOfArgs();
        FunctionDefinition defWithTypeAndListArgs1 = FunctionDefinitionFixture.withNameTypeAndListOfArgs();
        assertTrue(defWithTypeAndListArgs.equals(defWithTypeAndListArgs1));

        FunctionDefinition defWithOption = FunctionDefinitionFixture.withNameTypeAndOption();
        FunctionDefinition defWithOption1 = FunctionDefinitionFixture.withNameTypeAndOption();
        assertTrue(defWithOption.equals(defWithOption1));

        FunctionDefinition defWithTypeOptionAndListArgs = FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs();
        FunctionDefinition defWithTypeOptionAndListArgs1 = FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs();
        assertTrue(defWithTypeOptionAndListArgs.equals(defWithTypeOptionAndListArgs1));

        FunctionDefinition defWithTypeOptionAndKeywordArgs = FunctionDefinitionFixture.withNameTypeOptionAndKeywordArgs();
        FunctionDefinition defWithTypeOptionAndKeywordArgs1 = FunctionDefinitionFixture.withNameTypeOptionAndKeywordArgs();
        assertTrue(defWithTypeOptionAndKeywordArgs.equals(defWithTypeOptionAndKeywordArgs1));
    }
}
