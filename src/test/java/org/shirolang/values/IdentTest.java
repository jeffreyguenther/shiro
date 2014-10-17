/*
 * The MIT License
 *
 * Copyright (c) 2012 - 2014 Jeffrey Guenther.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.shirolang.values;

import org.junit.Assert;
import org.junit.Test;
import org.shirolang.base.SGraph;

import static org.junit.Assert.*;

/**
 * Test an identifier
 */
public class IdentTest {
    @Test
    public void getType(){
        SIdent b = new SIdent(null, "s");
        assertEquals("Ident", b.getType());
    }
    
    @Test
    public void evaluate(){
        SGraph g = new SGraph();
        SDouble d = new SDouble("d", 0.12);
        g.addPort(d);
        d.evaluate();

        
        SIdent id = new SIdent(g, "d");
        id.evaluate();
        
        assertSame("Should return the stored function", d, id.getResult());
    }
    
    @Test
    public void getArgs(){
        SGraph g = new SGraph();
        SDouble d = new SDouble("d", 0.12);
        g.addPort(d);
        
        SIdent id = new SIdent(g, "d");
        
        assertEquals(0, id.getArgs().size());
    }
    
    @Test
    public void hasArgs(){
        SGraph g = new SGraph();
        SDouble d = new SDouble("d", 0.12);
        g.addPort( d);
        
        SIdent id = new SIdent(g, "d");
        assertFalse(id.hasArgs());
    }
    
    @Test
    public void argCount(){
        SIdent s = new SIdent();
        assertEquals(0, s.getMaxArgs());
        assertEquals(0, s.getMinArgs());
    }

    @Test
    public void isReference(){
        Path p = new Path("a");
        p.makeReference();
        SIdent s = new SIdent(null, p);
        assertTrue(s.isReference());
    }

    @Test
    public void isSelector(){
        Path p = new Path("a");
        p.makeSelector();
        SIdent s = new SIdent(null, p);
        assertTrue(s.isSelector());
    }

    @Test
    public void toConsolePath(){
        SGraph g = new SGraph();
        SDouble d = new SDouble("d", 0.12);
        d.evaluate();
        g.addPort(d);

        SIdent id = new SIdent(g, "d");
        id.evaluate();
        Assert.assertEquals("0.12", id.toConsole());
    }

    @Test
    public void toConsoleReference(){
        SGraph g = new SGraph();
        SDouble d = new SDouble("d", 0.12);
        d.evaluate();
        g.addPort(d);

        SIdent id = new SIdent(g, Path.createReference("d"));
        id.evaluate();
        Assert.assertEquals(d.toConsole(), id.toConsole());
    }

    @Test
    public void toConsoleSelector(){
        SGraph g = new SGraph();
        SDouble d = new SDouble("d", 0.12);
        d.evaluate();
        g.addPort(d);

        SIdent id = new SIdent(g, Path.createSelector("d"));
        id.evaluate();
        Assert.assertEquals("d", id.toConsole());
    }
}
