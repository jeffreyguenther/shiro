package org.shirolang.fixtures.ast;

import org.antlr.v4.misc.Graph;
import org.shirolang.interpreter.ast.FunctionDefinition;
import org.shirolang.interpreter.ast.GraphDefinition;
import org.shirolang.interpreter.ast.Literal;
import org.shirolang.interpreter.ast.Path;

import java.util.Arrays;

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
        GraphDefinition g = emptyGraph();
        g.add(FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        return g;
    }

    /**
     *  graph g begin
     *      1.0 == (3 + 2)
     *  end
     */
    public static GraphDefinition withAnonymousExpressions(){
        GraphDefinition g = emptyGraph();
        g.add(ExpressionDefinitionFixture.complexComparison());
        return g;
    }

    /**
     *  graph g begin
     *      a.y(1, 2, 3)
     *  end
     */
    public static GraphDefinition withPortAssignments(){
        GraphDefinition g = emptyGraph();
        g.add(PortAssignmentFixture.withPathAndListOfArgs());
        return g;
    }

    /**
     *  graph g begin
     *      b Box[a](1, 2, 3)
     *      a.y(1, 2, 3)
     *  end
     */
    public static GraphDefinition withFunctionDeclarationsAndPortAssignments(){
        GraphDefinition g = emptyGraph();
        g.add(FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        g.add(PortAssignmentFixture.withPathAndListOfArgs());
        return g;
    }

    /**
     *  graph g begin
     *      b Box[a](1, 2, 3)
     *      1.0 == (3 + 2)
     *  end
     */
    public static GraphDefinition withFunctionDeclarationsAndAnonExpressions(){
        GraphDefinition g = emptyGraph();
        g.add(FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        g.add(ExpressionDefinitionFixture.complexComparison());
        return g;
    }

    /**
     *  graph g begin
     *      a.y(1, 2, 3)
     *      1.0 == (3 + 2)
     *  end
     */
    public static GraphDefinition withPortAssignmentsAndAnonExpressions(){
        GraphDefinition g = emptyGraph();
        g.add(PortAssignmentFixture.withPathAndListOfArgs());
        g.add(ExpressionDefinitionFixture.complexComparison());
        return g;
    }

    /**
     *  graph g begin
     *      b Box[a](1, 2, 3)
     *      a.y(1, 2, 3)
     *      1.0 == (3 + 2)
     *  end
     */
    public static GraphDefinition withFunctionDeclarationsPortAssignmentsAndAnonExpressions(){
        GraphDefinition g = emptyGraph();
        g.add(FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        g.add(PortAssignmentFixture.withPathAndListOfArgs());
        g.add(ExpressionDefinitionFixture.complexComparison());
        return g;
    }

    /**
     * b Box[a](1, 2, 3)
     */
    public static GraphDefinition defaultGraphWithFunctionDeclarations(){
        GraphDefinition g = new GraphDefinition();
        g.add(FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        return g;
    }
    /**
     * a.y(1, 2, 3)
     */
    public static GraphDefinition defaultGraphWithPortAssignment(){
        GraphDefinition g = new GraphDefinition();
        g.add(PortAssignmentFixture.withPathAndListOfArgs());
        return g;
    }

    /**
     * 1.0 == (3 + 2)
     */
    public static GraphDefinition defaultGraphWithAnonExpressions(){
        GraphDefinition g = new GraphDefinition();
        g.add(ExpressionDefinitionFixture.complexComparison());
        return g;
    }

    /**
     * b Box[a](1, 2, 3)
     * a.y(1, 2, 3)
     */
    public static GraphDefinition defaultGraphWithFunctionDeclarationsAndPortAssignments(){
        GraphDefinition g = new GraphDefinition();
        g.add(FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        g.add(PortAssignmentFixture.withPathAndListOfArgs());
        return g;
    }

    /**
     * b Box[a](1, 2, 3)
     * 1.0 == (3 + 2)
     */
    public static GraphDefinition defaultGraphWithFunctionDeclarationsAndAnonExpressions(){
        GraphDefinition g = new GraphDefinition();
        g.add(FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        g.add(ExpressionDefinitionFixture.complexComparison());
        return g;
    }

    /**
     * a.y(1, 2, 3)
     * 1.0 == (3 + 2)
     */
    public static GraphDefinition defaultGraphWithPortAssignmentsAndAnonExpressions(){
        GraphDefinition g = new GraphDefinition();
        g.add(PortAssignmentFixture.withPathAndListOfArgs());
        g.add(ExpressionDefinitionFixture.complexComparison());
        return g;
    }

    /**
     * b Box[a](1, 2, 3)
     * a.y(1, 2, 3)
     * 1.0 == (3 + 2)
     */
    public static GraphDefinition defaultGraphWithFunctionDeclarationsPortAssignmentsAnonExpressions(){
        GraphDefinition g = new GraphDefinition();
        g.add(FunctionDefinitionFixture.withNameTypeOptionAndListOfArgs());
        g.add(PortAssignmentFixture.withPathAndListOfArgs());
        g.add(ExpressionDefinitionFixture.complexComparison());
        return g;
    }

    /**
     * result Add(1.0, 1)
     * m Multiply(result, 2)
     */
    public static GraphDefinition inlineGraphMath(){
        GraphDefinition g = new GraphDefinition();
        g.add(new FunctionDefinition("Add", "result", Arrays.asList(Literal.asDouble(1.0), Literal.asInteger(1))));
        g.add(new FunctionDefinition("Multiply", "m", Arrays.asList(Literal.asPath(Path.create("result")), Literal.asInteger(2))));
        return g;
    }
}