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
 *
 * @author jeffreyguenther
 */
public class SMap extends SBinaryFunction{
    
    public SMap(SFunc list, SFunc func){
        args.set(list);
        args.set(func);
    }

    @Override
    public void evaluate() {
        SList list = (SList) args.get(0);
        SFunc func = args.get(1);
        
        List<SFunc> rs = new ArrayList<>();
        List<SFunc> rawValues = list.getValue();
        for(SFunc v: rawValues){
            func.setArg(1, v);
            func.evaluate();
            
            SFunc r = func.getArg();
            rs.add(r);
        }
        
        results.set(new SList(rs));
    }

    @Override
    public String getType() {
        return "SMap";
    }
}
