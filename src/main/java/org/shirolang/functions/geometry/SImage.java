package org.shirolang.functions.geometry;

import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;

/**
 * Represents an image in Shiro
 */
public class SImage extends SFuncBase {

    public SImage() {
        super();

        inputs.setKeyForIndex("originX", 0);
        inputs.add(new TypedValue("Double"));

        inputs.setKeyForIndex("originY", 1);
        inputs.add(new TypedValue("Double"));

        inputs.setKeyForIndex("file", 2);
        inputs.add(new TypedValue("String"));
    }

    @Override
    public void evaluate() {
    }

    @Override
    public int getMaxArgs() {
        return 3;
    }

    @Override
    public int getMinArgs() {
        return 3;
    }

    @Override
    public String getType() {
        return "Image";
    }
}
