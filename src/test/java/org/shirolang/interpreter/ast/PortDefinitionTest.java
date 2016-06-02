package org.shirolang.interpreter.ast;

import org.junit.Test;
import org.shirolang.fixtures.ast.FunctionDefinitionFixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PortDefinitionTest {
    @Test
    public void createWithOptionInternalFunctionDef(){
        PortDefinition def = new PortDefinition(true, Access.INTERNAL, FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        assertEquals(
            "option b Box[a](1, 2, 3)", def.toCode()
        );
    }

    @Test
    public void createWithOptionInputAccessFunctionDef(){
        PortDefinition def = new PortDefinition(true, Access.READWRITE, FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        assertEquals(
                "option input b Box[a](1, 2, 3)", def.toCode()
        );
    }

    @Test
    public void createWithOptionOutputAccessFunctionDef(){
        PortDefinition def = new PortDefinition(true, Access.READ, FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        assertEquals(
                "option output b Box[a](1, 2, 3)", def.toCode()
        );
    }

    @Test
    public void createWithInternalFunctionDef(){
        PortDefinition def = new PortDefinition(Access.INTERNAL, FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        assertEquals(
                "b Box[a](1, 2, 3)", def.toCode()
        );
    }

    @Test
    public void createWithInputAccessFunctionDef(){
        PortDefinition def = new PortDefinition(Access.READWRITE, FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        assertEquals(
                "input b Box[a](1, 2, 3)", def.toCode()
        );
    }

    @Test
    public void createWithOutputAccessFunctionDef(){
        PortDefinition def = new PortDefinition(Access.READ, FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        assertEquals(
                "output b Box[a](1, 2, 3)", def.toCode()
        );
    }

    @Test
    public void equals(){
        PortDefinition d1 = new PortDefinition(Access.READ, FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        PortDefinition d2 = new PortDefinition(Access.READ, FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        assertTrue(d1.equals(d2));

        PortDefinition d3 = new PortDefinition(Access.READWRITE, FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        PortDefinition d4 = new PortDefinition(Access.READWRITE, FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        assertTrue(d3.equals(d4));
    }
}