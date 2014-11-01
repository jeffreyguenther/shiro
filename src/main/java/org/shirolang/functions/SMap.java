/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shirolang.functions;

import org.shirolang.values.SList;
import java.util.ArrayList;
import java.util.List;
import org.shirolang.base.SFunc;
import org.shirolang.functions.math.SBinaryFunction;

/**
 * Represents a multi-function to map a list of values
 */
public class SMap extends SBinaryFunction{
    
    public SMap(SFunc list, SFunc func){
        // TODO fix me
//        args.add(list);
//        args.add(func);
    }

    @Override
    public void evaluate() {
        SList list = (SList) getArg(0);
        SFunc func = getArg(1);
        
        List<SFunc> rs = new ArrayList<>();
        List<SFunc> rawValues = list.getValue();
        for(SFunc v: rawValues){
            func.setArg(1, v);
            func.evaluate();
            
            SFunc r = func.getArg();
            rs.add(r);
        }

        //FIXME
//        results.add(new SList(rs));
    }

    @Override
    public String getType() {
        return "SMap";
    }
}
