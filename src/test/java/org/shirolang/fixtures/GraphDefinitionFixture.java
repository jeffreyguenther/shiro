package org.shirolang.fixtures;

import org.shirolang.interpreter.ast.FunctionDefinition;
import org.shirolang.interpreter.ast.GraphDefinition;

/**
 * Creates Graph definitions for testing.
 */
public class GraphDefinitionFixture {
    /**
     * graph g begin
     * end
     */
    public static GraphDefinition emptyGraph(){
        return new GraphDefinition("g");
    }

    /**
     *  graph g begin
     *      b Box[a](1, 2, 3)
     *  end
     */
    public static GraphDefinition withFunctionDeclarations(){
        GraphDefinition graph = emptyGraph();
        graph.addDefinition(FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());

        return graph;
    }

    /**
     * b Box[a](1, 2, 3)
     */
    public static GraphDefinition defaultGraphWithFunctionDeclarations(){
        GraphDefinition g = new GraphDefinition();
        g.addDefinition(FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        return g;
    }
}
