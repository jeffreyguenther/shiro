package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import org.shirolang.fixtures.interpreter.InterpreterFixture;
import org.shirolang.interpreter.ShiroLexer;
import org.shirolang.interpreter.ShiroParser;

import static org.junit.Assert.assertFalse;

public class LexParseTest {

    @Test
    public void parseBadCode(){
        assertFalse(parse(InterpreterFixture.badCode()));
    }

    /**
     * Current suppresses error output by removing the ConsoleErrorListener
     * @param code code to parse
     * @return true if the code parses properly, otherwise false
     */
    private boolean parse(String code){
        LexParseErrorListener error = new LexParseErrorListener();
        ShiroLexer lexer = new ShiroLexer(new ANTLRInputStream(code));
        lexer.getErrorListeners().clear();
        lexer.addErrorListener(error);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShiroParser parser = new ShiroParser(tokens);
        parser.getErrorListeners().clear();
        parser.addErrorListener(error);
        parser.setBuildParseTree(true);
        parser.shiro();
        return !error.hasErrors;
    }
}
