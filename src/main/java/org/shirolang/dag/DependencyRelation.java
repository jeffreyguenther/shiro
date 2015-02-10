package org.shirolang.dag;

/**
 * A basic pair describing a dependency relation. Follows the basic pattern of
 * A -> B, or A depends on B
 * @param <T> type of object used in the dependency relation
 * @author jeffreyguenther
 */
public class DependencyRelation<T> {
    private T dependent;
    private T dependedOn;

    public DependencyRelation( T dependent, T dependedOn ) {
        this.dependent = dependent;
        this.dependedOn = dependedOn;
    }

    /**
     * Get the object depended on. A in the relation.
     * @return the port depended on
     */
    public T getDependedOn() {
        return dependedOn;
    }

    /**
     * Get the dependent port. Be in the relation
     * @return the dependent port
     */
    public T getDependent() {
        return dependent;
    }

    @Override
    public String toString() {
        return "<" + dependent + "->" + dependedOn + ">";
    }
}
