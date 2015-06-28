package org.shirolang.functions.lists;

import org.junit.Assert;
import org.junit.Test;
import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.interpreter.ShiroRuntime;
import org.shirolang.values.*;

import java.util.List;

/**
 * Tests mapping across a list
 */
public class SMapTest {
    @Test
    public void literalReference(){
        ShiroRuntime rt = new ShiroRuntime();

        String nodeDef = "node DoubleIt begin\n" +
                "    input v Double\n" +
                "    output newValue Double (v * 2)\n" +
                "end";

        rt.executeStatement(nodeDef);

        SMap mapper = new SMap();
        mapper.setLibrary(rt.getLibrary());

        SList l = new SList();
        SDouble d1 = new SDouble(1.0);
        l.appendInput(d1);
        SDouble d2 = new SDouble(2.0);
        l.appendInput(d2);
        SDouble d3 = new SDouble(3.0);
        l.appendInput(d3);

        SGraph g = new SGraph();

        Path p = new Path("v");
        p.makeSelector();
        SIdent inputSelector = new SIdent(g, p);

        Path p2 = new Path("newValue");
        p2.makeSelector();
        SIdent outputSelector = new SIdent(g, p2);

        SReference reference = new SReference("DoubleIt");

        mapper.setInput("list", l);
        mapper.setInput(1, reference);
        mapper.setInput("inputSelector", inputSelector);
        mapper.setInput("outputSelector", outputSelector);

        d1.evaluate();
        d2.evaluate();
        d3.evaluate();
        l.evaluate();
        reference.evaluate();
        inputSelector.evaluate();
        outputSelector.evaluate();

        mapper.evaluate();

        SList result = (SList) mapper.getResult();
        List<SFunc> numbers = result.getValue();
        Assert.assertEquals(2.0, ((SDouble)numbers.get(0)).getValue(), 1e-15);
        Assert.assertEquals(4.0, ((SDouble)numbers.get(1)).getValue(), 1e-15);
        Assert.assertEquals(6.0, ((SDouble)numbers.get(2)).getValue(), 1e-15);
    }
}
