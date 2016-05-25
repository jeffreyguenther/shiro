package org.shirolang.interpreter.ast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Defines an option selection
 */
public class OptionSelection {
    private String function;
    private String option;
    private List<OptionSelection> selections;

    public OptionSelection(String function, String option) {
        this.function = function;
        this.option = option;
        this.selections = new ArrayList<>();
    }

    public OptionSelection(String function, String option, OptionSelection... inner) {
        this.function = function;
        this.option = option;
        selections = Arrays.asList(inner);
    }

    public OptionSelection(String function, String option, List<OptionSelection> inner) {
        this.function = function;
        this.option = option;
        this.selections = inner;
    }

    public String getFunction() {
        return function;
    }

    public String getOption() {
        return option;
    }

    public List<OptionSelection> getSelections() {
        return selections;
    }

    public boolean hasOptionSelections() {
        return !selections.isEmpty();
    }
}
