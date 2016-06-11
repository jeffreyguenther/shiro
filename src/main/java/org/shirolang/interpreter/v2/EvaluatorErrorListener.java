package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides a listener for generating Errors during evaluation
 */
public class EvaluatorErrorListener extends BaseErrorListener {
    private List<Error> errors;

    public EvaluatorErrorListener() {
        super();
        this.errors = new ArrayList<>();
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        errors.add(new SyntaxError("line "+line+":"+charPositionInLine+" "+msg));
    }

    public List<Error> getErrors() {
        return errors;
    }
}
