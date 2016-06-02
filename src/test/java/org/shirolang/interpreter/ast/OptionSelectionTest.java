package org.shirolang.interpreter.ast;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OptionSelectionTest {
    public OptionSelection createOptionSelection(){
        String functionName = "A";
        String optionName = "o1";
        return new OptionSelection(functionName, optionName);
    }

    @Test
    public void getFunctionAndOption(){
        String functionName = "A";
        String optionName = "o1";
        OptionSelection selection = new OptionSelection(functionName, optionName);
        assertEquals(functionName, selection.getFunction());
        assertEquals(optionName, selection.getOption());
    }

    @Test
    public void hasNestedOptionSelections(){
        OptionSelection inner = createOptionSelection();
        OptionSelection selection = new OptionSelection("B", "v1", inner);
        assertTrue(selection.hasOptionSelections());

        OptionSelection selection1 = new OptionSelection("C", "c1", Arrays.asList(inner));
        assertTrue(selection1.hasOptionSelections());
    }

    @Test
    public void doesNotHaveNestedOptions(){
        OptionSelection selection = createOptionSelection();
        assertFalse(selection.hasOptionSelections());
    }

    @Test
    public void equals(){
        OptionSelection s1 = createOptionSelection();
        OptionSelection s2 = createOptionSelection();
        assertTrue(s1.equals(s2));
    }
}
