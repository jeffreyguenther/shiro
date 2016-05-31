package org.shirolang.functions.geometry;

import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;

/**
 * Represents an ellipse in Shiro
 */
public class SEllipse extends SFuncBase{
    public SEllipse(){
        super();

        inputs.setKeyForIndex("centerX", 0);
        inputs.add(new TypedValue("Double"));

        inputs.setKeyForIndex("centerY", 1);
        inputs.add(new TypedValue("Double"));

        inputs.setKeyForIndex("rotate", 2);
        inputs.add(new TypedValue("Double"));

        inputs.setKeyForIndex("radiusX", 3);
        inputs.add(new TypedValue("Double"));

        inputs.setKeyForIndex("radiusY", 4);
        inputs.add(new TypedValue("Double"));

        inputs.setKeyForIndex("stroke", 5);
        inputs.add(new TypedValue("Color"));

        inputs.setKeyForIndex("strokeWeight", 6);
        inputs.add(new TypedValue("Double"));

        inputs.setKeyForIndex("fill", 7);
        inputs.add(new TypedValue("Color"));

        results.add(new TypedValue(getType()));
    }

    @Override
    public void evaluate() {
    }

    @Override
    public int getMaxArgs() {
        return 8;
    }

    @Override
    public int getMinArgs() {
        return 8;
    }

    @Override
    public String getType() {
        return "SEllipse";
    }
}
