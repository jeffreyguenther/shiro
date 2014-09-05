/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shirolang.functions;

import org.shirolang.base.SFunc;
import org.shirolang.base.SFuncBase;

/**
 *
 * @author jeffreyguenther
 */
public class SPrint extends SFuncBase{
    private SFunc value;

    public SPrint(SFunc value) {
        this.value = value;
    }

    @Override
    public void evaluate() {
        System.out.println(value);
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxArgs() {
        return 1;
    }

    @Override
    public int getMinArgs() {
        return 1;
    }
}
