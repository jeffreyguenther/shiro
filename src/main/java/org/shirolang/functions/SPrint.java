/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shirolang.functions;

import org.shirolang.SFuncBase;

/**
 *
 * @author jeffreyguenther
 */
public class SPrint extends SFuncBase{
    private SFuncBase value;

    public SPrint(SFuncBase value) {
        this.value = value;
    }

    @Override
    public void evaluate() {
//        if()
        
        System.out.println(value);
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
