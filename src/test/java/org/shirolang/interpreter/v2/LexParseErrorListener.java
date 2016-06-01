package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Created by jeffrey on 2016-06-01.
 */
public class LexParseErrorListener extends BaseErrorListener{
    public boolean hasErrors = false;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        hasErrors = true;
    }
}