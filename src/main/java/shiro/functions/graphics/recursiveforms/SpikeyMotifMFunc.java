/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics.recursiveforms;

import java.util.List;
import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 * Creates a spikey space filling motif
 * @author jeffreyguenther
 */
public class SpikeyMotifMFunc implements MultiFunction{
    private static final String NAME = "SymmetricalTree";
    

    @SuppressWarnings("unchecked")
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        
        SpikeyPattern pattern = new SpikeyPattern();
        
        ResultTuple result = new ResultTuple(0, new Value(pattern, SpikeyPattern.class));
        return result;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
    
}
