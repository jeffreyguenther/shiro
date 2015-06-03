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

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.shirolang.dag.DependencyRelation;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Imports code referenced by the file being run.
 * It searches the standard library stored as a resource in the jar
 * then the current directory (.) and a lib directory (./lib)
 */
public class CodeImporter extends ShiroBaseListener{
    private Set<DependencyRelation<Path>> sourceFiles;
    private Path parentDirectory;
    private Path sourceFile;
    private URL stdLib;
    private Library lib;

    public CodeImporter(Library lib, Path source){
        sourceFiles = new HashSet<>();
        this.sourceFile = source;
        this.parentDirectory = source.getParent();
        this.lib = lib;
        stdLib = CodeImporter.class.getResource("lib");

    }

    public Set<DependencyRelation<Path>> getSourceFiles() {
        return sourceFiles;
    }

    @Override
    public void enterUseStatement(@NotNull ShiroParser.UseStatementContext ctx) {
        String importedFile = ctx.STRING_LITERAL().getText().replaceAll("\"", "");
        // look in standard lib folders first (org.shirolang.interpreter.lib)

        if(!importedFile.endsWith(".sro")){
            importedFile = importedFile + ".sro";
        }

        try{
            Path standardLib = Paths.get(stdLib.toURI()).resolve(importedFile);
            Path sourceAtRoot = parentDirectory.resolve(importedFile);
            Path sourceInLib = parentDirectory.resolve("lib").resolve(importedFile);


            if(Files.exists(standardLib)){
                sourceFiles.add(new DependencyRelation<>(sourceFile, standardLib));
                sourceFiles.addAll(getSourceDependencies(standardLib));
            }
            else if (Files.exists(sourceAtRoot)) {
                sourceFiles.add(new DependencyRelation<>(sourceFile, sourceAtRoot));
                sourceFiles.addAll(getSourceDependencies(sourceAtRoot));

            } else if (Files.exists(sourceInLib)) {
                sourceFiles.add(new DependencyRelation<>(sourceFile, sourceInLib));
                sourceFiles.addAll(getSourceDependencies(sourceInLib));
            }
        } catch (IOException ex) {
            Logger.getLogger(CodeImporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException e) {
            Logger.getLogger(CodeImporter.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Gets the sources files the source depends on
     * @param file source file to get the dependents of
     * @return a set containing the dependency relationships for each file {@code file} depends on
     * @throws IOException
     */
    private Set<DependencyRelation<Path>> getSourceDependencies(Path file) throws IOException {
        CommonTokenStream lex = lib.lex(file);
        ParseTree tree = lib.parse(lex);
        lib.saveParseResult(file, lex, tree);

        ParseTreeWalker walker = new ParseTreeWalker();
        CodeImporter listener = new CodeImporter(lib, file);
        walker.walk(listener, tree);
        return listener.getSourceFiles();
    }
}
