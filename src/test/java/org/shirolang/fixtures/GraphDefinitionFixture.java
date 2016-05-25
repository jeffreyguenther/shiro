package org.shirolang.fixtures;

import org.shirolang.interpreter.ast.FunctionDefinition;
import org.shirolang.interpreter.ast.GraphDefinition;

/**
 * Creates Graph definitions for testing.
 */
public class GraphDefinitionFixture {
    /**
     * Creates an empty graph named g
     */
    public static GraphDefinition emptyGraph(){
        return new GraphDefinition("g");
    }

    public static GraphDefinition withFunctionDeclarations(){
        GraphDefinition graph = emptyGraph();
        graph.addDefinition(FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());

        return graph;
    }
}
