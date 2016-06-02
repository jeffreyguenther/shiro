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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptionSelection that = (OptionSelection) o;

        if (!function.equals(that.function)) return false;
        if (!option.equals(that.option)) return false;
        return selections.equals(that.selections);

    }

    @Override
    public int hashCode() {
        int result = function.hashCode();
        result = 31 * result + option.hashCode();
        result = 31 * result + selections.hashCode();
        return result;
    }
}
