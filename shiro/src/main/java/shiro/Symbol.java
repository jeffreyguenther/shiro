package shiro;

/**
 * A tag for marking symbols
 * Allows a node, a port, or a subjunctive node to be handled as a common type
 * of object.
 * @author jeffreyguenther
 */
public interface Symbol {
    public SymbolType getType();
}
