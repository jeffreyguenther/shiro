package org.shirolang.functions.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.values.SDouble;
import org.shirolang.values.SIdent;
import org.shirolang.values.SInteger;
import org.shirolang.values.SString;

public class AddTest {
    @Test
    public void getType(){
        SAdd a = new SAdd();
        assertEquals("Should be 'Add'", "Add", a.getType());
    }
    
    @Test
    public void addDoubles(){
        SDouble a = new SDouble(13.0);
        SDouble b = new SDouble(-0.13);
        SAdd sum = new SAdd(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        sum.evaluate();
        
        SDouble r = (SDouble) sum.getResult();
        assertTrue(r.isDouble());
        assertEquals(12.87, r.getValue(), 1e-16);
    }
    
    @Test
    public void addInts(){
        SInteger a = new SInteger(13);
        SInteger b = new SInteger(-1);
        SAdd sum = new SAdd(a, b);
        
        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        sum.evaluate();
        
        SInteger r = (SInteger) sum.getResult();
        assertTrue(sum.getResult().isInteger());
        assertEquals(12, (int)r.getValue());
    }

    @Test
    public void addDoubleAndInt(){
        SDouble a = new SDouble(13.0);
        SInteger b = new SInteger(-1);
        SAdd sum = new SAdd(a, b);

        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        sum.evaluate();

        SDouble r = (SDouble) sum.getResult();
        assertTrue(sum.getResult().isDouble());
        assertEquals(12.0, r.getValue(), 1e-15);


        SDouble a1 = new SDouble(13.0);
        SInteger b2 = new SInteger(-1);
        SAdd sum2 = new SAdd(b2, a1);

        // simulate evaluation order
        a1.evaluate();
        b2.evaluate();
        sum2.evaluate();

        SDouble r2 = (SDouble) sum2.getResult();
        assertTrue(sum2.getResult().isDouble());
        assertEquals(12.0, r2.getValue(), 1e-15);
    }

    @Test
    public void addIdent(){
        SGraph g = new SGraph();
        SDouble a = new SDouble("a", 23.0);
        g.addPort(a);

        SIdent id = new SIdent(g, "a");
        SDouble b = new SDouble(2.0);
        SAdd sum = new SAdd(id, b);

        a.evaluate();
        id.evaluate();
        b.evaluate();
        sum.evaluate();

        SFunc result = sum.getResult();
        SDouble asDouble = (SDouble)result;
        assertTrue(result.isDouble());
        assertEquals(25.0, asDouble.getValue(), 1e-15);
    }

    
    @Test(expected= RuntimeException.class)
    public void addDoubleString(){
        SDouble a = new SDouble(25.9);
        SString s = new SString("5");
        SAdd sum = new SAdd(a, s);
        
        a.evaluate();
        s.evaluate();
        sum.evaluate();
    }
    
    @Test(expected= RuntimeException.class)
    public void addStringInt(){
        SInteger a = new SInteger(25);
        SString s = new SString("5");
        SAdd sum = new SAdd(s, a);
        
        a.evaluate();
        s.evaluate();
        sum.evaluate();
    }
    
    @Test
    public void argCount(){
        SAdd s = new SAdd();
        assertEquals(2, s.getMaxArgs());
        assertEquals(2, s.getMinArgs());
    }

    @Test
    public void toConsole(){
        SDouble a = new SDouble(13.0);
        SDouble b = new SDouble(-0.13);
        SAdd sum = new SAdd(a, b);

        // simulate evaluation order
        a.evaluate();
        b.evaluate();
        sum.evaluate();

        assertEquals("#<Add args:[a:13.0, b:-0.13], results:[12.87]>", sum.toConsole());
    }
}
