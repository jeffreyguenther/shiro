package org.shirolang.interpreter;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *  Represents the result of a parsed file
 *  Used to avoid re-parsing when evaluating a file
 *  after dependencies are resolved
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParseResult that = (ParseResult) o;

        if (!tokens.equals(that.tokens)) return false;
        return parseTree.equals(that.parseTree);

    }

    @Override
    public int hashCode() {
        int result = tokens.hashCode();
        result = 31 * result + parseTree.hashCode();
        return result;
    }
}
