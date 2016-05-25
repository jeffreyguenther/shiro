package org.shirolang.fixtures;

import org.shirolang.interpreter.ast.OptionSelection;
import org.shirolang.interpreter.ast.StateDefinition;

/**
 * Constructs states to be used in test ast
 */
public class StateDefinitionFixture {
    public static StateDefinition nested(){
        OptionSelection innerBinnerA = new OptionSelection("D", "e");
        OptionSelection innerB = new OptionSelection("B", "c", innerBinnerA);
        OptionSelection innerA = new OptionSelection("H", "i");
        OptionSelection selection = new OptionSelection("A", "b", innerB, innerA);

        OptionSelection selection2 = new OptionSelection("F", "g");
        return new StateDefinition("s1", "g1", selection, selection2);
    }

    public static StateDefinition singleOption(){
        OptionSelection selection = new OptionSelection("A", "b");
        return new StateDefinition("s1", "g1", selection);
    }

    public static StateDefinition defaultGraph(String name){
        return new StateDefinition(name);
    }

    public static StateDefinition defaultGraphAndState (){
        return new StateDefinition();
    }
}
