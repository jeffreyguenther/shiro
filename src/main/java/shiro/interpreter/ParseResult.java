/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.interpreter;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author jeffreyguenther
 */
public class ParseResult {
    private final CommonTokenStream tokens;
    private final ParseTree parseTree;

    public ParseResult(CommonTokenStream tokens, ParseTree parseTree) {
        this.tokens = tokens;
        this.parseTree = parseTree;
    }
    
    public CommonTokenStream getTokens() {
        return tokens;
    }

    public ParseTree getParseTree() {
        return parseTree;
    }
}
