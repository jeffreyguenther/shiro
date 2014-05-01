package shiro;

/**
 *
 * @author jeffreyguenther
 */
public enum SymbolType {
    NODE, PORT;
    public boolean isNode(){
        return this.equals(NODE);
    }
    
    public boolean isPort(){
        return this.equals(PORT);
    }
}
