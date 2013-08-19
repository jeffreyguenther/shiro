package shiro.interpreter;

import main.java.shiro.interpreter.ShiroLexer;
import main.java.shiro.interpreter.ShiroParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import shiro.SubjunctiveParametricSystem;

/**
 * Shiro Runtime
 *
 * @author jeffreyguenther
 */
public class Shiro {

    public static void main(String args[]) throws Exception {
        SubjunctiveParametricSystem subjPSystem = new SubjunctiveParametricSystem();

        System.out.println("Starting Shiro Interpreter");
        System.out.println("File:" + args[0]);

        // create a lexer
        ShiroLexer lex = new ShiroLexer(new ANTLRFileStream(args[0], "UTF8"));

        // Get the token stream
        CommonTokenStream tokens = new CommonTokenStream(lex);
        System.out.println("Lexing complete.");

        // Parse the file
        ShiroParser parser = new ShiroParser(tokens);
        parser.setBuildParseTree(true);

        // Create the parse tree object
        ParseTree tree = parser.shiro();

        System.out.println("Parsing complete.");
        System.out.println();
        
        ParseTreeWalker walker  = new ParseTreeWalker();
        DefPass defpass = new DefPass();
        walker.walk(defpass, tree);
        
        ParseTree p = defpass.defs.get("Point");
        walker.walk(new GraphPass(), p);
//            
//            // Add a node -> AST mapping in the parametric system
//            Map<String, CommonTree> nodeDefinitions = g.getNodeDefinitions();
//            // Store the ASTs in the tree
//            subjPSystem.addNodeASTDefinitions(nodeDefinitions);
//            
//            System.out.println("Producing nodes...");
//            System.out.println();
//            
//            // Get the AST
//            CommonTree tree = (CommonTree) prog.getTree();
//            // Create a "token stream" of tree nodes
//            CommonTreeNodeStream firstpass = new CommonTreeNodeStream(tree);
//            // Walk the AST to build the dependency graph            
//            GraphBuilderPass graphBuilder = new GraphBuilderPass(firstpass);
//            // Realize the nodes in the graph
//            graphBuilder.shiro(subjPSystem);
//            
//            System.out.println("Parametric system built!");
//            System.out.println();
//            System.out.println("Evaluating parametric system...");
//            
//            // Evaluate parametric system
//            subjPSystem.update();
//            
//            System.out.println("\nResults:\n");
//            System.out.println(subjPSystem.printDependencyGraph());
//            System.out.println("Done");
    }
}
