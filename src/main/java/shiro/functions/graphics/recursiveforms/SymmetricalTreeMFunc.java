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
 * Creates a symmetrical tree motif
 * @author jeffreyguenther
 */
public class SymmetricalTreeMFunc implements MultiFunction{
    private static final String NAME = "SymmetricalTree";
    private static final int TRUNK_LENGTH = 0;
    private static final int SCALE_FACTOR = 1; 
    private static final int ANGLE = 2;

    @SuppressWarnings("unchecked")
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        double trunkLength = arguments.get(TRUNK_LENGTH).getValueAsDouble();
        double scaleFactor = arguments.get(SCALE_FACTOR).getValueAsDouble();
        double angle = arguments.get(ANGLE).getValueAsDouble();
        
        SymmetricalTree tree = new SymmetricalTree(trunkLength, scaleFactor, angle);
        
        ResultTuple result = new ResultTuple(0, new Value(tree, SymmetricalTree.class));
        return result;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
    
}
