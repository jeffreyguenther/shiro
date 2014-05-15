package shiro;

/**
 * Defines the type of symbol
 * @author jeffreyguenther
 */
public enum SymbolType {
    NODE, PORT;
    
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
}
