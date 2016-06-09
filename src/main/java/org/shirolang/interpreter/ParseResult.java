package org.shirolang.interpreter;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.shirolang.interpreter.ast.Program;

/**
 *  Represents the result of a parsed file
 *  Used to avoid re-parsing when evaluating a file
 *  after dependencies are resolved
 */
public class ParseResult {
    private final CommonTokenStream tokens;
    private final ParseTree parseTree;
    private final Program ast;

    //TODO remove on migration
    public ParseResult(CommonTokenStream tokens, ParseTree parseTree) {
        this.tokens = tokens;
        this.parseTree = parseTree;
        this.ast = null;
    }

    public ParseResult(CommonTokenStream tokens, ParseTree parseTree, Program ast) {
        this.tokens = tokens;
        this.parseTree = parseTree;
        this.ast = ast;
    }

    public CommonTokenStream getTokens() {
        return tokens;
    }

    public ParseTree getParseTree() {
        return parseTree;
    }

    public Program getAst() {
        return ast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParseResult that = (ParseResult) o;

        if (!tokens.equals(that.tokens)) return false;
        if (!parseTree.equals(that.parseTree)) return false;
        return ast != null ? ast.equals(that.ast) : that.ast == null;

    }

    @Override
    public int hashCode() {
        int result = tokens.hashCode();
        result = 31 * result + parseTree.hashCode();
        result = 31 * result + (ast != null ? ast.hashCode() : 0);
        return result;
    }
}
