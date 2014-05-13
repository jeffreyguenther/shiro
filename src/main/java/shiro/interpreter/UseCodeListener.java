/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.interpreter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import shiro.dag.DependencyRelation;
import shiro.dag.GraphNode;

/**
 *
 * @author jeffreyguenther
 */
public class UseCodeListener extends ShiroBaseListener {

    private Set<DependencyRelation<Path>> sourceFiles;
    private Path parentDirectory;
    private Path sourceFile;

    public UseCodeListener(Path source) {
        sourceFiles = new HashSet<>();
        this.sourceFile = source;
        this.parentDirectory = source.getParent();
    }

    public Set<DependencyRelation<Path>> getSourceFiles() {
        return sourceFiles;
    }

    @Override
    public void enterUseStatement(ShiroParser.UseStatementContext ctx) {
        String importedFile = ctx.STRING_LITERAL().getText().replaceAll("\"", "");
        Path sourceAtRoot = parentDirectory.resolve(importedFile);
        Path sourceInLib = parentDirectory.resolve("lib").resolve(importedFile);

        try {
            if (Files.exists(sourceAtRoot)) {
                sourceFiles.add(new DependencyRelation<>(sourceFile, sourceAtRoot));
                sourceFiles.addAll(getSourceDependencies(sourceAtRoot));
                
            } else if (Files.exists(sourceInLib)) {
                sourceFiles.add(new DependencyRelation<>(sourceFile, sourceInLib));
                sourceFiles.addAll(getSourceDependencies(sourceInLib));
            }
        } catch (IOException ex) {
            Logger.getLogger(UseCodeListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Set<DependencyRelation<Path>> getSourceDependencies(Path file) throws IOException {
        ShiroLexer lexer = new ShiroLexer(new ANTLRFileStream(file.toString()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShiroParser parser = new ShiroParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.shiro();
        
        ParseTreeWalker walker = new ParseTreeWalker();
        UseCodeListener listener = new UseCodeListener(file);
        walker.walk(listener, tree);
        return listener.getSourceFiles();
    }

}
