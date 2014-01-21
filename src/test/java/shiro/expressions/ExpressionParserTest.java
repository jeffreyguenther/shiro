package shiro.expressions;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import shiro.interpreter.ExpressionLexer;
import shiro.interpreter.ExpressionParser;

public class ExpressionParserTest{
    @Test
    public void testStringParse(){
        ANTLRInputStream is = new ANTLRInputStream("blue[\"sdfds\"]");
        ExpressionLexer lex = new ExpressionLexer(is);
        CommonTokenStream ts = new CommonTokenStream(lex);
        
        ExpressionParser parser = new ExpressionParser(ts);
        parser.setBuildParseTree(true);
        
        ParseTree tree = parser.expression();
        System.out.println(tree.toStringTree(parser));
    }
}