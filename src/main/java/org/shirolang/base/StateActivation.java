package org.shirolang.base;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a state activation
 */
public class StateActivation {
    private String name;
    private String option;
    private Set<StateActivation> nestedActivations;

    public StateActivation(String name, String option) {
        this.name = name;
        this.option = option;
        this.nestedActivations = new HashSet<>();
    }

    public StateActivation(){
        this("", "");
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getOption() {
        return option;
    }

    public boolean addNestedActivation(StateActivation stateActivation) {
        return nestedActivations.add(stateActivation);
    }

    public Set<StateActivation> getNestedActivations() {
        return nestedActivations;
    }

    public boolean hasNestedActivations(){
        return !nestedActivations.isEmpty();
    }
}
