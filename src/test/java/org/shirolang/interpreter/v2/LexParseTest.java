package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import org.shirolang.fixtures.interpreter.InterpreterFixture;
import org.shirolang.interpreter.ShiroLexer;
import org.shirolang.interpreter.ShiroParser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LexParseTest {

    @Test
    public void parseBadCode(){
        assertFalse(parse(InterpreterFixture.badCode(), false));
    }

    @Test
    public void parseInlineGraph(){
        assertTrue(parse(InterpreterFixture.inlineGraph()));
    }

    @Test
    public void parseInlineGraphWithNode(){
        assertTrue(parse(InterpreterFixture.inlineGraphWithNode()));
    }

    @Test
    public void parseNamedGraphWithNode(){
        assertTrue(parse(InterpreterFixture.namedGraphWithNode()));
    }

    @Test
    public void parseNamedGraphWithNodeAndState(){
        assertTrue(parse(InterpreterFixture.namedGraphWithNodeAndState()));
    }

    @Test
    public void parseNodeWithOption(){
        assertTrue(parse(InterpreterFixture.nodeWithOptions()));
    }

    private boolean parse(String code){
        return parse(code, true);
    }

    /**
     * Current suppresses error output by removing the ConsoleErrorListener
     * @param code code to parse
     * @return true if the code parses properly, otherwise false
     */
    private boolean parse(String code, boolean showOutput){
        LexParseErrorListener error = new LexParseErrorListener();
        ShiroLexer lexer = new ShiroLexer(new ANTLRInputStream(code));

        if(!showOutput)
            lexer.getErrorListeners().clear();

        lexer.addErrorListener(error);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShiroParser parser = new ShiroParser(tokens);

        if(!showOutput)
            parser.getErrorListeners().clear();

        parser.addErrorListener(error);
        parser.setBuildParseTree(true);
        parser.shiro();
        return !error.hasErrors;
    }
}
