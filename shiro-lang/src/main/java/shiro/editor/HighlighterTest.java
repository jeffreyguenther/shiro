package shiro.editor;

import shiro.interpreter.ShiroLexer;
import shiro.interpreter.ShiroParser;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class HighlighterTest {

	public static void main(String args[]) throws Exception {
		System.out.println("File:" + args[0]);

        System.out.println("Lex Started.");
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

		SyntaxHighlighter sh = new SyntaxHighlighter();
		
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(sh, tree);
		
		System.out.println(sh.getStyles());
	}
}