/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shirolang;

import org.shirolang.values.SList;
import org.shirolang.functions.math.SAdd;
import org.shirolang.values.SDouble;
import java.util.ArrayList;
import java.util.List;
import org.shirolang.functions.SMap;
import org.shirolang.values.SIdent;

/**
 *
 * @author jeffreyguenther
 */
public class ShiroTypes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        SDouble a = new SDouble(13.0);
//        SDouble b = new SDouble(21.0);
//        SAdd result = new SAdd(a, b);
//        a.evaluate();
//        b.evaluate();
//        result.evaluate();
//        
//        SDouble c = new SDouble(110.0);
//        SAdd result2 = new SAdd(result.get(), c);
//        
//        
//        // simulate the topological sort
//        
//        c.evaluate();
//        result2.evaluate();
//        
//        System.out.println(result.get());
//        System.out.println(result2.get());
//        
//        SDouble i = new SDouble(1.0);
//        SDouble j = new SDouble(2.0);
//        SDouble k = new SDouble(3.0);
//        List<SFunc> values = new ArrayList<>();
//        values.add(i);
//        values.add(j);
//        values.add(k);
//        
//        SList list = new SList(values);
//        SDouble num = new SDouble(10.0);
//        SAdd adder = new SAdd();
//        adder.setArg(num);
//        adder.setArg(null);
//        SMap mapper = new SMap(list, adder);
//        
//        list.evaluate();
//        num.evaluate();
//        mapper.evaluate();
//        System.out.println(mapper.get());
        
        ShiroRuntime rt = new ShiroRuntime();
        SIdent aId = new SIdent(rt, "a");
        SDouble a1 = new SDouble(12.90);
        rt.addSymbol("a", a1);
        
        SDouble b2 = new SDouble(54.03);
        SAdd sum2 = new SAdd(aId, b2);
        
        a1.evaluate();
        aId.evaluate();
        b2.evaluate();
        sum2.evaluate();
        System.out.println(sum2.get());
        
        
        
    }
    
}
