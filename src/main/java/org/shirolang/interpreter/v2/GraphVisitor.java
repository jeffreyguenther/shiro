package org.shirolang.interpreter.v2;

import org.shirolang.base.SGraph;
import org.shirolang.interpreter.ast.GraphDefinition;

/**
 * Walks a graph AST to generate a SGraph instance
 */
public class GraphVisitor extends MultiPassVisitor{

    public GraphVisitor(SymbolTable symbolTable) {
        super(symbolTable);
    }

    public SGraph visit(GraphDefinition def){
        return null;
    }
}