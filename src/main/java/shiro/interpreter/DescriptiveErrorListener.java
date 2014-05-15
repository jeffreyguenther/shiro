/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.interpreter;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import shiro.SubjunctiveParametricSystem;

/**
 * An ANTLR error listener that reroutes the ANTLR error messages 
 * @author jeffreyguenther
 */
public class DescriptiveErrorListener extends BaseErrorListener{
    SubjunctiveParametricSystem ps;

    public DescriptiveErrorListener(SubjunctiveParametricSystem ps) {
        this.ps = ps;
    }
    
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        ps.setHasSyntaxErrors(true);
        ps.appendErrorMessage(String.format("%d:%d: %s", line, charPositionInLine, msg));
    }
}
