package org.shirolang.functions.conditionals;

import org.shirolang.base.*;
import org.shirolang.functions.Instantiator;
import org.shirolang.interpreter.Library;
import org.shirolang.values.SBoolean;
import org.shirolang.values.SInteger;
import org.shirolang.values.SReference;

/**
 * A multi-function that returns different values depending on the condition
 */
public class SConditionalReturnNode extends SFuncBase implements Instantiator{

    private Library library;

    public SConditionalReturnNode(){
        super();

        inputs.add(new TypedValue(SType.BOOLEAN));
        inputs.setKeyForIndex("condition", 0);

        inputs.add(new TypedValue(SType.INTEGER));
        inputs.setKeyForIndex("value", 1);

        inputs.add(new TypedValue(SType.REFERENCE));
        inputs.setKeyForIndex("trueRef", 2);

        inputs.add(new TypedValue(SType.INTEGER));
        inputs.setKeyForIndex("falseValue", 3);
    }

    @Override
    public void evaluate() {
        SBoolean condition = (SBoolean) getInput(0).getResult();
        SInteger value = (SInteger) getInput(1).getResult();
        SReference trueRef = (SReference) getInput(2).getResult();
        SInteger falseValue = (SInteger) getInput(3).getResult();

        if(condition.getValue()){
            SGraph g = new SGraph();
            String type = trueRef.getValue();
            SFunc node = library.instantiateNode(g, type, "internal");
            g.addNode((SNode) node);

            SFunc input = node.getInput(0);
            input.setInput(0, value);

            // evaluate the node
            g.evaluate();
            SInteger outputValue = (SInteger) node.getOutput(0).getResult();

            SInteger result = new SInteger(outputValue.getValue());
            result.evaluate();
            results.add(new TypedValue(SType.INTEGER, result));
        }else{
            SInteger result = new SInteger(falseValue.getValue());
            result.evaluate();
            results.add(new TypedValue(SType.INTEGER, result));
        }
    }

    @Override
    public int getMaxArgs() {
        return 4;
    }

    @Override
    public int getMinArgs() {
        return 4;
    }

    @Override
    public String getType() {
        return SType.CONDITIONAL_RETURN_NODE;
    }

    @Override
    public void setLibrary(Library library) {
        this.library = library;
    }
}
