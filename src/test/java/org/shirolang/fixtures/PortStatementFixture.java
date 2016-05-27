package org.shirolang.fixtures;

import org.shirolang.interpreter.ast.Access;
import org.shirolang.interpreter.ast.PortDefinition;

/**
 * Creates port statements
 */
public class PortStatementFixture {
    /**
     * option b Box
     */
    public static PortDefinition internalOption(){
        return new PortDefinition(true, Access.INTERNAL, FunctionDefinitionFixture.withNameAndType());
    }

    /**
     * b Box
     */
    public static PortDefinition internal(){
        return new PortDefinition(Access.INTERNAL, FunctionDefinitionFixture.withNameAndType());
    }

    /**
     * option input b Box
     */
    public static PortDefinition inputOption(){
        return new PortDefinition(true, Access.READWRITE, FunctionDefinitionFixture.withNameAndType());
    }

    /**
     * input b Box
     */
    public static PortDefinition input(){
        return new PortDefinition(Access.READWRITE, FunctionDefinitionFixture.withNameAndType());
    }

    /**
     * option output b Box
     */
    public static PortDefinition outputOption(){
        return new PortDefinition(true, Access.READ, FunctionDefinitionFixture.withNameAndType());
    }

    /**
     * output b Box
     */
    public static PortDefinition output(){
        return new PortDefinition(Access.READ, FunctionDefinitionFixture.withNameAndType());
    }
}
