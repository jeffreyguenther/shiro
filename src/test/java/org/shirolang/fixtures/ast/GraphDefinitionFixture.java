package org.shirolang.fixtures.ast;

import org.antlr.v4.misc.Graph;
import org.shirolang.interpreter.ast.*;
import org.shirolang.values.SegmentType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 1 + 1
     */
    public static GraphDefinition add(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asInteger(1), BinaryOperator.ADD, Literal.asInteger(1)));
        return g;
    }

    /**
     * 1 + 1.0
     */
    public static GraphDefinition addIntegerAndFloat(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asInteger(1), BinaryOperator.ADD, Literal.asDouble(1.0)));
        return g;
    }

    /**
     * 4 - 1.0
     */
    public static GraphDefinition subtractIntegerAndFloat(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asInteger(4), BinaryOperator.SUBTRACT, Literal.asDouble(1.0)));
        return g;
    }

    /**
     * 4 * 1.0
     */
    public static GraphDefinition multiplyIntegerAndFloat(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asInteger(4), BinaryOperator.MULTIPLY, Literal.asDouble(1.0)));
        return g;
    }

    /**
     * 4 / 1.0
     */
    public static GraphDefinition divideIntegerAndFloat(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asInteger(4), BinaryOperator.DIVIDE, Literal.asDouble(1.0)));
        return g;
    }

    /**
     * 4 % 1.0
     */
    public static GraphDefinition moduloIntegerAndFloat(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asInteger(4), BinaryOperator.MOD, Literal.asDouble(1.0)));
        return g;
    }

    /**
     * -1.0
     */
    public static GraphDefinition negateFloat(){
        GraphDefinition g = new GraphDefinition();
        g.add(new UnaryOperation(UnaryOperator.NEGATE, Literal.asDouble(1.0)));
        return g;
    }

    /**
     * !true
     */
    public static GraphDefinition negateBoolean(){
        GraphDefinition g = new GraphDefinition();
        g.add(new UnaryOperation(UnaryOperator.NOT, Literal.asBoolean(true)));
        return g;
    }

    /**
     * (1 + 1) * 3
     */
    public static GraphDefinition parens(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(new UnaryOperation(UnaryOperator.PARENS, new BinaryOperation(Literal.asInteger(1), BinaryOperator.ADD, Literal.asInteger(1))), BinaryOperator.MULTIPLY, Literal.asInteger(3)));
        return g;
    }

    /**
     * true && true
     */
    public static GraphDefinition and(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asBoolean(true), BinaryOperator.AND, Literal.asBoolean(true)));
        return g;
    }

    /**
     * true || false
     */
    public static GraphDefinition or(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asBoolean(true), BinaryOperator.OR, Literal.asBoolean(false)));
        return g;
    }

    /**
     * 2 > 1
     */
    public static GraphDefinition greaterThan(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asInteger(2), BinaryOperator.GREATER_THAN, Literal.asInteger(1)));
        return g;
    }

    /**
     * 2 >= 1
     */
    public static GraphDefinition greaterThanEqual(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asInteger(2), BinaryOperator.GREATER_THAN_OR_EQUAL, Literal.asInteger(1)));
        return g;
    }

    /**
     * 2 < 1
     */
    public static GraphDefinition lessThan(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asInteger(2), BinaryOperator.LESS_THAN, Literal.asInteger(1)));
        return g;
    }

    /**
     * 2 <= 1
     */
    public static GraphDefinition lessThanEqual(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asInteger(2), BinaryOperator.LESS_THAN_OR_EQUAL, Literal.asInteger(1)));
        return g;
    }

    /**
     * 2 == 1
     */
    public static GraphDefinition equal(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asInteger(2), BinaryOperator.EQUAL, Literal.asInteger(1)));
        return g;
    }

    /**
     * 2 != 1
     */
    public static GraphDefinition notEqual(){
        GraphDefinition g = new GraphDefinition();
        g.add(new BinaryOperation(Literal.asInteger(2), BinaryOperator.NOT_EQUAL, Literal.asInteger(1)));
        return g;
    }

    /**
     * a.b.c
     */
    public static GraphDefinition simplePath(){
        GraphDefinition g = new GraphDefinition();
        Path p = new Path("a", "b", "c");
        g.add(Literal.asPath(p));

        return g;
    }

    /**
     * a.inputs[0]
     */
    public static GraphDefinition inputsWithNumberedIndexInPath(){
        GraphDefinition g = new GraphDefinition();
        Path p = new Path();
        p.addSegment(new PathSegment("a"));
        p.addSegment(new PathSegment(SegmentType.INPUT, 0));
        g.add(Literal.asPath(p));

        return g;
    }

    /**
     * a.inputs[b]
     */
    public static GraphDefinition inputsWithNamedIndexInPath(){
        GraphDefinition g = new GraphDefinition();
        Path p = new Path();
        p.addSegment(new PathSegment("a"));
        p.addSegment(new PathSegment(SegmentType.INPUT, "b"));
        g.add(Literal.asPath(p));

        return g;
    }

    /**
     * a.outputs[0]
     */
    public static GraphDefinition outputsWithNumberedIndexInPath(){
        GraphDefinition g = new GraphDefinition();
        Path p = new Path();
        p.addSegment(new PathSegment("a"));
        p.addSegment(new PathSegment(SegmentType.OUTPUT, 0));
        g.add(Literal.asPath(p));

        return g;
    }

    /**
     * a.outputs[b]
     */
    public static GraphDefinition outputsWithNamedIndexInPath(){
        GraphDefinition g = new GraphDefinition();
        Path p = new Path();
        p.addSegment(new PathSegment("a"));
        p.addSegment(new PathSegment(SegmentType.OUTPUT, "b"));
        g.add(Literal.asPath(p));

        return g;
    }

    /**
     * A.B.C
     */
    public static GraphDefinition fullyQualifiedType(){
        GraphDefinition g = new GraphDefinition();
        g.add(new FullyQualifiedType("A.B.C"));

        return g;
    }

    /**
     * ~F()
     */
    public static GraphDefinition reference(){
        GraphDefinition g = new GraphDefinition();
        g.add(new Reference("F"));

        return g;
    }

    /**
     * ~F[a]()
     */
    public static GraphDefinition referenceWithOptionSelection(){
        GraphDefinition g = new GraphDefinition();
        g.add(new Reference("F", "a"));

        return g;
    }

    /**
     * ~F(a: 1, b: 2)
     */
    public static GraphDefinition referenceWithKeywordArguments(){
        GraphDefinition g = new GraphDefinition();

        Map<String, Expression> argMap = new HashMap<>();
        argMap.put("a", Literal.asInteger(1));
        argMap.put("b", Literal.asInteger(2));
        g.add(new Reference("F", argMap));

        return g;
    }

    /**
     * ~F[a](a: 1, b: 2)
     */
    public static GraphDefinition referenceWithOptionSelectionAndKeywordArguments(){
        GraphDefinition g = new GraphDefinition();
        Map<String, Expression> argMap = new HashMap<>();
        argMap.put("a", Literal.asInteger(1));
        argMap.put("b", Literal.asInteger(2));
        g.add(new Reference("F", "a", argMap));

        return g;
    }

    /**
     * ~F[a](a: 1, b: 2)[1]
     */
    public static GraphDefinition referenceWithOptionSelectionKeywordArgumentsAndIndexOutputSelector(){
        GraphDefinition g = new GraphDefinition();
        Map<String, Expression> argMap = new HashMap<>();
        argMap.put("a", Literal.asInteger(1));
        argMap.put("b", Literal.asInteger(2));
        g.add(new Reference("F", "a", argMap, "1"));

        return g;
    }

    /**
     * ~F[a](a: 1, b: 2)[x]
     */
    public static GraphDefinition referenceWithOptionSelectionKeywordArgumentsAndKeywordOutputSelector(){
        GraphDefinition g = new GraphDefinition();
        Map<String, Expression> argMap = new HashMap<>();
        argMap.put("a", Literal.asInteger(1));
        argMap.put("b", Literal.asInteger(2));
        g.add(new Reference("F", "a", argMap, "x"));

        return g;
    }

    /**
     * ~F(1, 2)
     */
    public static GraphDefinition referenceWithArguments(){
        GraphDefinition g = new GraphDefinition();
        g.add(new Reference("F", Arrays.asList(Literal.asInteger(1), Literal.asInteger(2))));

        return g;
    }

    /**
     * ~F[a](1, 2)
     */
    public static GraphDefinition referenceWithOptionSelectionAndArguments(){
        GraphDefinition g = new GraphDefinition();
        g.add(new Reference("F", "a", Arrays.asList(Literal.asInteger(1), Literal.asInteger(2))));

        return g;
    }

    /**
     * ~F[a](1, 2)[1]
     */
    public static GraphDefinition referenceWithOptionSelectionAndArgumentsAndIndexOutputSelector(){
        GraphDefinition g = new GraphDefinition();
        g.add(new Reference("F", "a", Arrays.asList(Literal.asInteger(1), Literal.asInteger(2)),"1"));

        return g;
    }

    /**
     * ~F[a](1, 2)[x]
     */
    public static GraphDefinition referenceWithOptionSelectionAndArgumentsAndKeywordOutputSelector(){
        GraphDefinition g = new GraphDefinition();
        g.add(new Reference("F", "a", Arrays.asList(Literal.asInteger(1), Literal.asInteger(2)),"x"));

        return g;
    }
}