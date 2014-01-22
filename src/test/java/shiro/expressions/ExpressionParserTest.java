package shiro.expressions;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;
import shiro.interpreter.ExpressionLexer;
import shiro.interpreter.ExpressionParser;
import shiro.interpreter.ShiroLexer;
import shiro.interpreter.ShiroParser;

public class ExpressionParserTest {

    @Test
    public void pareseLiteralNumber() {
        String result = generateParseTree("10");
        Assert.assertEquals("(expression (expr 10))", result);
    }

    @Test
    public void parseLiteralDecimalNumber() {
        String result = generateParseTree("234.5");
        Assert.assertEquals("(expression (expr 234.5))", result);
        String result1 = generateParseTree("0.00025");
        Assert.assertEquals("(expression (expr 0.00025))", result1);
    }

    @Test
    public void parseAddIntegers() {
        String result = generateParseTree("2 + 10");
        Assert.assertEquals("(expression (expr (expr 2) + (expr 10)))", result);
    }

    @Test
    public void parseAddMixedNumbers() {
        String result = generateParseTree("2333.45 + 10");
        Assert.assertEquals("(expression (expr (expr 2333.45) + (expr 10)))", result);
        String result1 = generateParseTree("0.1 + 4.5");
        Assert.assertEquals("(expression (expr (expr 0.1) + (expr 4.5)))", result1);

    }

    @Test
    public void parseSubtractIntegers() {
        String result = generateParseTree("2 - 10");
        Assert.assertEquals("(expression (expr (expr 2) - (expr 10)))", result);
    }

    @Test
    public void parseSubtractMixedNumbers() {
        String result = generateParseTree("2333.45 - 10");
        Assert.assertEquals("(expression (expr (expr 2333.45) - (expr 10)))", result);
    }

    @Test
    public void parseMultiplyIntegers() {
        String result = generateParseTree("2 * 10");
        Assert.assertEquals("(expression (expr (expr 2) * (expr 10)))", result);
    }

    @Test
    public void parseMultiplyMixedNumbers() {
        String result = generateParseTree("2333.45 * 10");
        Assert.assertEquals("(expression (expr (expr 2333.45) * (expr 10)))", result);
    }

    @Test
    public void parseDivideIntegers() {
        String result = generateParseTree("2 / 10");
        Assert.assertEquals("(expression (expr (expr 2) / (expr 10)))", result);
    }

    @Test
    public void parseDivideMixedNumbers() {
        String result = generateParseTree("2333.45 / 10");
        Assert.assertEquals("(expression (expr (expr 2333.45) / (expr 10)))", result);
    }

    private String generateEXRParseTree(String expression) {
        ANTLRInputStream is = new ANTLRInputStream(expression);
        ExpressionLexer lex = new ExpressionLexer(is);
        CommonTokenStream ts = new CommonTokenStream(lex);

        ExpressionParser parser = new ExpressionParser(ts);
        parser.setBuildParseTree(true);

        ParseTree tree = parser.expression();
        return tree.toStringTree(parser);
    }

    
    private String generateParseTree(String expression) {
        ANTLRInputStream is = new ANTLRInputStream(expression);
        ShiroLexer lex = new ShiroLexer(is);
        CommonTokenStream ts = new CommonTokenStream(lex);

        ShiroParser parser = new ShiroParser(ts);
        parser.setBuildParseTree(true);

        ParseTree tree = parser.expression();
        return tree.toStringTree(parser);
    }
}
