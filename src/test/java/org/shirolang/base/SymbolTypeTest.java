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

package org.shirolang.base;

import org.junit.Assert;
import org.junit.Test;
import org.shirolang.base.SymbolType;

/**
 * Test the internal type enum
 */
public class SymbolTypeTest {
    @Test
    public void isNode(){
        SymbolType node = SymbolType.NODE;
        SymbolType port = SymbolType.PORT;
        SymbolType literal = SymbolType.LITERAL;
        SymbolType ident = SymbolType.IDENT;


        Assert.assertTrue("is node", node.isNode());
        Assert.assertFalse("is not node", port.isNode());
        Assert.assertFalse("is not node", literal.isNode());
        Assert.assertFalse("is not node", ident.isNode());
    }

    @Test
    public void isPort(){
        SymbolType node = SymbolType.NODE;
        SymbolType port = SymbolType.PORT;
        SymbolType literal = SymbolType.LITERAL;
        SymbolType ident = SymbolType.IDENT;

        Assert.assertFalse("is not port", node.isPort());
        Assert.assertTrue("is port", port.isPort());
        Assert.assertFalse("is not port", literal.isPort());
        Assert.assertFalse("is not port", ident.isPort());
    }

    @Test
    public void isLiteral(){
        SymbolType node = SymbolType.NODE;
        SymbolType port = SymbolType.PORT;
        SymbolType literal = SymbolType.LITERAL;
        SymbolType ident = SymbolType.IDENT;

        Assert.assertFalse("is not literal", node.isLiteral());
        Assert.assertFalse("is not literal", port.isLiteral());
        Assert.assertTrue("is literal", literal.isLiteral());
        Assert.assertFalse("is not literal", ident.isLiteral());
    }

    @Test
    public void isIdent(){
        SymbolType node = SymbolType.NODE;
        SymbolType port = SymbolType.PORT;
        SymbolType literal = SymbolType.LITERAL;
        SymbolType ident = SymbolType.IDENT;

        Assert.assertFalse("is not ident", node.isIdent());
        Assert.assertFalse("is not ident", port.isIdent());
        Assert.assertFalse("is not ident", literal.isIdent());
        Assert.assertTrue("is ident", ident.isIdent());
    }
}
