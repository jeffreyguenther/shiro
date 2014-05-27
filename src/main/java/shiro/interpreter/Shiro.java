package shiro.interpreter;

import java.util.Map;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import shiro.ShiroRuntime;

/**
 * Shiro ShiroRuntime
 Reads a .sro file and prints the output to the console
 * @author jeffreyguenther
 */
public class Shiro {

    public static void main(String args[]) throws Exception {
        ShiroRuntime subjPSystem = new ShiroRuntime();
        ParseTreeWalker walker = new ParseTreeWalker();

        System.out.println("Starting Shiro Interpreter");
        System.out.println("File:" + args[0]);

        System.out.println("Lex Started.");
        // create a lexer
        ShiroLexer lex = new ShiroLexer(new ANTLRFileStream(args[0], "UTF8"));

        // Get the token stream
        CommonTokenStream tokens = new CommonTokenStream(lex);
        System.out.println("Lexing complete.");

        // Parse the file
        System.out.println("Parse Started.");
        ShiroParser parser = new ShiroParser(tokens);
        parser.setBuildParseTree(true);

        // Create the parse tree object
        ParseTree tree = parser.shiro();

        System.out.println("Parsing complete.");

        // PASS 1: Add a node -> AST mapping in the parametric system
        NodeDefinitionListener defPass = new NodeDefinitionListener();
        // Walk the tree with the def pass listener
        walker.walk(defPass, tree);

        // Get the node definitions
        Map<String, ParseTree> nodeDefinitions = defPass.getNodeDefinitions();
        Map<String, ParseTree> alternativeDefinitions = defPass.getAlternativeDefinitions();
//        ParseTree graph = defPass.getGraphs();

        // Store the ASTs in the tree
        subjPSystem.addNodeASTDefinitions(nodeDefinitions);
        subjPSystem.addAlternativeASTDefinitions(alternativeDefinitions);

        // PASS 2: Build the dependency graph
        System.out.println("Build graph");

        /**
         * Walk only the graph parse tree to prevent events from firing on the 
         * other parts of the parse tree
         */
//        walker.walk(new GraphBuilderListener(subjPSystem), graph);
        
        System.out.println("Evaluate alternatives");

        // Evaluate parametric system
        subjPSystem.update();
        
        System.out.println("Done");
    }
}
