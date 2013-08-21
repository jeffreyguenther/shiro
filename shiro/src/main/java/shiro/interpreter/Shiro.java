package shiro.interpreter;

import java.util.Map;
import main.java.shiro.interpreter.ShiroLexer;
import main.java.shiro.interpreter.ShiroParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import shiro.Node;
import shiro.SubjunctiveParametricSystem;

/**
 * Shiro Runtime
 *
 * @author jeffreyguenther
 */
public class Shiro {

    public static void main(String args[]) throws Exception {
        SubjunctiveParametricSystem subjPSystem = new SubjunctiveParametricSystem();
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
        System.out.println();

        // PASS 1: Add a node -> AST mapping in the parametric system
        NodeDefinitionListener defPass = new NodeDefinitionListener();
        // Walk the tree with the def pass listener
        walker.walk(defPass, tree);

        // Get the node definitions
        Map<String, ParseTree> nodeDefinitions = defPass.getNodeDefinitions();

        // Store the ASTs in the tree
        subjPSystem.addNodeASTDefinitions(nodeDefinitions);
        NodeProductionListener nodeBuilder = new NodeProductionListener(subjPSystem);
        walker.walk(nodeBuilder, nodeDefinitions.get("Point") );
        Node createdNode = nodeBuilder.getCreatedNode();

        // PASS 2: Build the dependency graph
        System.out.println("Build graph");
        System.out.println();
        
        // walk parse tree to get the graph definitions
        walker.walk( new GraphBuilderListener(), tree);

        System.out.println("Parametric system built!");
        System.out.println();
        System.out.println("Evaluating parametric system...");

        // Evaluate parametric system
        subjPSystem.update();

        System.out.println("\nResults:\n");
        System.out.println(subjPSystem.printDependencyGraph());
        System.out.println("Done");
    }
}
