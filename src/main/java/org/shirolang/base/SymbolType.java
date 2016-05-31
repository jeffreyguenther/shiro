package org.shirolang.base;

/**
 * Defines the basic types of symbols used in the runtime.
 */
public enum SymbolType {
    NODE, PORT, LITERAL, IDENT;

    /**
     * Determines if the type is a node
     * @return true if the type is a node, otherwise false
     */
    public boolean isNode(){
        return this.equals(NODE);
    }

    /**
     * Determines if the type is a port
     * @return true if the type is a port, otherwise false
     */
    public boolean isPort(){
        return this.equals(PORT);
    }

    /**
     * Determines if the type is a literal
     * @return true if the type is a literal, otherwise false
     */
    public boolean isLiteral(){
        return this.equals(LITERAL);
    }

    /**
     * Determines if the type is a identifier
     * @return true if the type is an identifier, otherwise false
     */
    public boolean isIdent(){
        return this.equals(IDENT);
    }
}
