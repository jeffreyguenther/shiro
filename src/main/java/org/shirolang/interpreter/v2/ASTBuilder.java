package org.shirolang.interpreter.v2;

import org.shirolang.interpreter.ShiroBaseListener;
import org.shirolang.interpreter.ShiroParser;
import org.shirolang.interpreter.ast.FunctionDefinition;
import org.shirolang.interpreter.ast.GraphDefinition;
import org.shirolang.interpreter.ast.Program;

/**
 * Walks the ParseTree to build the AST
 */
public class ASTBuilder extends ShiroBaseListener{
    private Program p;

    public ASTBuilder() {
        p = new Program();
    }

    @Override
    public void enterAnonymousGraphStmt(ShiroParser.AnonymousGraphStmtContext ctx) {
        p.add(new GraphDefinition());
    }

    @Override
    public void exitFuncDeclInit(ShiroParser.FuncDeclInitContext ctx) {
    }

    public Program getProgram() {
        return p;
    }
}
