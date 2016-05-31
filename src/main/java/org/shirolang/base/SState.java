package org.shirolang.base;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a state
 */
public class SState {
    private String name;
    private String graph;
    private Set<StateActivation> optionActivations;

    public SState(String name, String graph){
        this.name = name;
        this.graph = graph;
        this.optionActivations = new HashSet<>();
    }

    public SState(String name){
        this(name, "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGraph() {
        return graph;
    }

    public void setGraph(String graph) {
        this.graph = graph;
    }

    public Set<StateActivation> getActivations() {
        return optionActivations;
    }

    public void addActivation(StateActivation activation) {
        optionActivations.add(activation);
    }

    public boolean hasOptionActivations(){
        return !optionActivations.isEmpty();
    }
}
