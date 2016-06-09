package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.shirolang.interpreter.ShiroLexer;
import org.shirolang.interpreter.ShiroParser;
import org.shirolang.interpreter.ast.Program;

/**
 * A class which contains parse helpers
 */
public abstract class ParsingTest {
    protected ParseTree parse(String code){
       return parse(new ANTLRInputStream(code));
    }

    protected ParseTree parse(ANTLRInputStream code){
        ShiroLexer lexer = new ShiroLexer(code);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShiroParser parser = new ShiroParser(tokens);

        parser.setBuildParseTree(true);
        return parser.shiro();
    }

    protected Program generateProgram(ANTLRInputStream code){
        return walkTree(parse(code));
    }

    protected Program generateProgram(String code){
        return walkTree(parse(code));
    }

    private Program walkTree(ParseTree program) {
        ParseTreeWalker walker = new ParseTreeWalker();
        ASTBuilder ast = new ASTBuilder();
        walker.walk(ast, program);
        return ast.getProgram();
    }
}
