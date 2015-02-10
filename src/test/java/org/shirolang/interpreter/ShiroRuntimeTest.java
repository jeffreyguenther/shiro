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

package org.shirolang.interpreter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.shirolang.interpreter.ShiroRuntime;

/**
 *
 * Test the Shiro runtime
 */
public class ShiroRuntimeTest{
    private ShiroRuntime rt;
    
    @Before
    public void setup(){
        rt = new ShiroRuntime();
    }

    @Test
    public void executeLiteralInteger(){
        Assert.assertEquals("3", rt.executeStatement("3\n"));
    }

    @Test
    public void executeLiteralDouble(){
        Assert.assertEquals("3.123", rt.executeStatement("3.123\n"));
    }

    @Test
    public void executeLiteralString(){
        Assert.assertEquals("\"hello world\"", rt.executeStatement("\"hello world\"\n"));
    }

    @Test
    public void executePortDecl(){
        Assert.assertEquals("#<Double args:[], results:[12.3]>", rt.executeStatement("port a Double(12.3)\n"));
    }

    @Test
    public void executePath(){
        Assert.assertEquals("a was not found", rt.executeStatement("a\n"));

        rt.executeStatement("port a Double(12.3)\n");
        Assert.assertEquals("12.3", rt.executeStatement("a\n"));
    }

    @Test
    public void executeReferencePath(){
//        Assert.assertEquals("~a was not found", rt.executeStatement("~a\n"));

        rt.executeStatement("port a Double(12.3)\n");
        Assert.assertEquals("#<Double args:[], results:[12.3]>", rt.executeStatement("~a\n"));
    }

    @Test
    public void executeSelector(){
        Assert.assertEquals("a.b.c", rt.executeStatement("@a.b.c\n"));
    }
//
//    @Test
//    public void executeNode(){
//        String input = "node Box begin\n" +
//                 "input width Double(12.3)\n" +
//                 "input height Double(100)\n" +
//                 "eval update Double( width * height)\n" +
//                "end";
//        String expected = "#<Box input:[width, height], eval:[], output:[], " +
//                "dependencies:[#<Double args:[width * height], results:[1230]> => #<Double args:[], results:[12.3]>," +
//                " #<Double args:[width * height] results:[1230]> => #<Double args:[] results:[100]>]>";
//        Assert.assertEquals(expected, rt.executeStatement(input));
//    }
//
//    @Test
//    public void executeGraph(){
//        String expected = "";
//        fail();
//    }
//
//    @Test
//    public void executeBlock(){
//        Assert.fail();
//    }
    
    @Test
    public void executeEquals(){
        Assert.assertEquals("#<Equal args:[a:1, b:1], results:[true]>", rt.executeStatement("1 == 1\n"));
        Assert.assertEquals("#<Equal args:[a:2, b:1], results:[false]>", rt.executeStatement("2 == 1\n"));
        Assert.assertEquals("#<Equal args:[a:1, b:2], results:[false]>", rt.executeStatement("1 == 2\n"));
    }

    @Test
    public void parseNotEquals(){
        Assert.assertEquals("#<NotEqual args:[a:1, b:1], results:[false]>", rt.executeStatement("1 != 1\n"));
        Assert.assertEquals("#<NotEqual args:[a:2, b:1], results:[true]>", rt.executeStatement("2 != 1\n"));
        Assert.assertEquals("#<NotEqual args:[a:1, b:2], results:[true]>", rt.executeStatement("1 != 2\n"));
    }

    @Test
    public void lessThan(){
        Assert.assertEquals("#<LessThan args:[a:1, b:2], results:[true]>", rt.executeStatement("1 < 2\n"));
        Assert.assertEquals("#<LessThan args:[a:2, b:1], results:[false]>", rt.executeStatement("2 < 1\n"));
    }

    @Test
    public void lessThanEqual(){
        Assert.assertEquals("#<LessThanOrEqual args:[a:1, b:2], results:[true]>", rt.executeStatement("1 <= 2\n"));
        Assert.assertEquals("#<LessThanOrEqual args:[a:2, b:1], results:[false]>", rt.executeStatement("2 <= 1\n"));
        Assert.assertEquals("#<LessThanOrEqual args:[a:2, b:2], results:[true]>", rt.executeStatement("2 <= 2\n"));
    }

    @Test
    public void greaterThan(){
        Assert.assertEquals("#<GreaterThan args:[a:1, b:2], results:[false]>", rt.executeStatement("1 > 2\n"));
        Assert.assertEquals("#<GreaterThan args:[a:2, b:1], results:[true]>", rt.executeStatement("2 > 1\n"));
    }

    @Test
    public void greaterThanEqual(){
        Assert.assertEquals("#<GreaterThanOrEqual args:[a:1, b:2], results:[false]>", rt.executeStatement("1 >= 2\n"));
        Assert.assertEquals("#<GreaterThanOrEqual args:[a:2.5, b:1.0], results:[true]>", rt.executeStatement("2.5 >= 1.0\n"));
        Assert.assertEquals("#<GreaterThanOrEqual args:[a:2, b:2], results:[true]>", rt.executeStatement("2 >= 2\n"));
    }
    
    @Test
    public void add(){
        Assert.assertEquals("#<Add args:[a:1, b:2], results:[3]>", rt.executeStatement("1 + 2\n"));
    }

    @Test
    public void subtract(){
        Assert.assertEquals("#<Subtract args:[a:1, b:2], results:[-1]>", rt.executeStatement("1 - 2\n"));
    }

    @Test
    public void multiply(){
        Assert.assertEquals("#<Multiply args:[a:1, b:2], results:[2]>", rt.executeStatement("1 * 2\n"));
    }

    @Test
    public void divide(){
        Assert.assertEquals("#<Divide args:[a:4, b:2], results:[2]>", rt.executeStatement("4 / 2\n"));
    }

    @Test
    public void mod(){
        Assert.assertEquals("#<Modulo args:[a:4, b:2], results:[0]>", rt.executeStatement("4 % 2\n"));
    }

    @Test
    public void and(){
        Assert.assertEquals("#<And args:[a:true, b:false], results:[false]>", rt.executeStatement("true &&  false\n"));
    }

    @Test
    public void parseBooleans(){
        Assert.assertEquals("false", rt.executeStatement("false\n"));
        Assert.assertEquals("true", rt.executeStatement("true\n"));
    }

     @Test
    public void or(){
         Assert.assertEquals("#<Or args:[a:true, b:false], results:[true]>", rt.executeStatement("true ||  false\n"));
    }

    @Test
    public void not(){
        Assert.assertEquals("#<Not args:[a:true], results:[false]>", rt.executeStatement("!true\n"));
    }


    @Test
    public void makeNegative(){
        Assert.assertEquals("#<Negative args:[a:2], results:[-2]>", rt.executeStatement("-2\n"));
    }

    @Test
    public void complicatedExpr(){
        String code = "port a Double(11.0)\n" +
                "port b Double(2.0)\n" +
                "port c Power(a, b)\n" +
                "c + b\n";

        Assert.assertEquals("#<Add args:[a:121.0, b:2.0], results:[123.0]>", rt.executeStatement(code));
    }

    @Test
    public void crazyExpr(){
        String code = "port a Double(11.0)\n" +
                "port b Double(2.0)\n" +
                "port c Power(a, b)\n" +
                "(c + b) * 2 - 10\n";

        Assert.assertEquals("#<Subtract args:[a:#<Multiply args:[a:#<Add args:[a:121.0, b:2.0], results:[123.0]>, b:2], " +
                "results:[246.0]>, b:10], results:[236.0]>", rt.executeStatement(code));
    }
}
