/*
 * Copyright (c) 2012-2014 Jeffrey Guenther
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software  and associated documentation files (the
 * "Software"), to deal in the Software without restriction,  including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute,  sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT  NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.shirolang.interpreter;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Tests the code importer
 */
public class CodeImporterTest extends ShiroBaseTest{
    @Test
    public void includeCode() throws IOException {
        // should have dependencies:
            // graph_with_includes.sro -> a_include.sro
            // graph_with_includes.sro -> b_include.sro
            // graph_with_includes.sro -> geom.sro

        Library lib = new Library();
        Path path = Paths.get(getClass().getResource("graph_with_includes.sro").getPath());
        CodeImporter codeImporter = new CodeImporter(lib, path);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(codeImporter, parse("graph_with_includes.sro").shiro());

        Assert.assertEquals(3, codeImporter.getSourceFiles().size());
    }

}
