/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.interpreter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;
import shiro.SubjunctiveParametricSystem;
import shiro.dag.DAGraph;
import shiro.dag.DependencyRelation;
import shiro.dag.GraphNode;
import shiro.dag.TopologicalSort;

/**
 *
 * @author jeffreyguenther
 */
public class CodeImportTest{
    @Test
    public void getFiles() throws IOException, URISyntaxException{
        URL resource = getClass().getClassLoader().getResource("shiro/interpreter/a.sro");
        Path source = Paths.get(resource.toURI());
        Path rootFolder = source.getParent();
        Assert.assertTrue(Files.isDirectory(rootFolder));
        
        Path libFolder = rootFolder.resolve("lib");
        Assert.assertTrue(Files.isDirectory(libFolder));
        Assert.assertTrue(Files.exists(libFolder));
        
        ShiroLexer lexer = new ShiroLexer(new ANTLRFileStream(source.toString()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShiroParser parser = new ShiroParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.shiro();
        
        SubjunctiveParametricSystem ps = new SubjunctiveParametricSystem();
        ParseTreeWalker walker = new ParseTreeWalker();
        UseCodeListener useCode = new UseCodeListener(source, ps);
        walker.walk(useCode, tree);
        
        Assert.assertTrue(useCode.getSourceFiles().size() == 3);
    }
}
