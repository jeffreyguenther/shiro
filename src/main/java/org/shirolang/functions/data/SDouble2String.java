package org.shirolang.functions.data;

import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;
import org.shirolang.values.SDouble;
import org.shirolang.values.SString;

/**
 * Convert a Double to a string.
 */
public class SDouble2String extends SFuncBase{

    public SDouble2String() {
        super();

        inputs.setKeyForIndex("d", 0);
        inputs.add(new TypedValue("Double"));

        results.setKeyForIndex("s", 0);
        results.add(new TypedValue("String"));
    }


    @Override
    public void evaluate() {
        SDouble d = (SDouble) getInput("d").getResult();
        Double value = d.getValue();
        SString string = new SString(value.toString());
        string.evaluate();

        setOutput(0, string);
    }

    @Override
    public int getMaxArgs() {
        return 1;
    }

    @Override
    public int getMinArgs() {
        return 1;
    }

    @Override
    public String getType() {
        return "Double2String";
    }
}
