package org.shirolang.fixtures.interpreter;

import org.shirolang.interpreter.InterpreterSuite;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Provides Shiro programs to use in tests
 */
public class InterpreterFixture {
    public static String fromFile(String file){
        byte[] bytes;
        try {
            URI uri = InterpreterSuite.class.getResource(file).toURI();
            bytes = Files.readAllBytes(Paths.get(uri));
            return new String(bytes);
        } catch (IOException | URISyntaxException e) {
            return "";
        }
    }

    public static String badCode(){
        return "asdfsdfs dsafsd \n890989089089080sd**9349(@@)()fsdfs89f8s908df908sd09fsf23423$42$$";
    }

    /**
     * 1 + 1
     */
    public static String add(){
        return "1 + 1";
    }

    /**
     * 1 + 1.0
     */
    public static String addIntegerAndFloat(){
        return "1 + 1.0";
    }

    /**
     * 4 - 1.0
     */
    public static String subtractIntegerAndFloat(){
        return "4 - 1.0";
    }

    /**
     * 4 * 1.0
     */
    public static String multiplyIntegerAndFloat(){
        return "4 * 1.0";
    }

    /**
     * 4 / 1.0
     */
    public static String divideIntegerAndFloat(){
        return "4 / 1.0";
    }

    /**
     * 4 % 1.0
     */
    public static String moduloIntegerAndFloat(){
        return "4 % 1.0";
    }

    /**
     * -1.0
     */
    public static String negateFloat(){
        return "-1.0";
    }

    /**
     * !true
     */
    public static String negateBoolean(){
        return "!true";
    }

    /**
     * (1 + 1) * 3
     */
    public static String parens(){
        return "(1 + 1) * 3";
    }

    /**
     * true || false
     */
    public static String or(){
        return "true || false";
    }

    /**
     * true && true
     */
    public static String and(){
        return "true && true";
    }

    /**
     * 2 > 1
     */
    public static String greaterThan(){
        return "2 > 1";
    }

    /**
     * 2 >= 1
     */
    public static String greaterThanEqual(){
        return "2 >= 1";
    }

    /**
     * 2 <= 1
     */
    public static String lessThanEqual(){
        return "2 <= 1";
    }

    /**
     * 2 < 1
     */
    public static String lessThan(){
        return "2 < 1";
    }

    /**
     * 2 == 1
     */
    public static String equal(){
        return "2 == 1";
    }

    /**
     * 2 != 1
     */
    public static String notEqual(){
        return "2 != 1";
    }

    /**
     * a.b.c
     */
    public static String simplePath(){
        return "a.b.c";
    }

    /**
     * a.inputs[0]
     */
    public static String inputsWithNumberedIndexInPath(){
        return "a.inputs[0]";
    }

    /**
     * a.inputs[b]
     */
    public static String inputsWithNamedIndexInPath(){
        return "a.inputs[b]";
    }

    /**
     * a.outputs[b]
     */
    public static String outputsWithNamedIndexInPath(){
        return "a.outputs[b]";
    }

    /**
     * a.outputs[0]
     */
    public static String outputsWithNumberedIndexInPath(){
        return "a.outputs[0]";
    }

    /**
     * A.B.C
     */
    public static String fullyQualifiedType(){
        return "A.B.C";
    }

    /**
     * ~F()
     */
    public static String reference(){
        return "~F()";
    }

    /**
     * ~F[a]()
     */
    public static String referenceWithOptionSelection(){
        return "~F[a]()";
    }

    /**
     * ~F(1, 2)
     */
    public static String referenceWithArguments(){
        return "~F(1, 2)";
    }

    /**
     * ~F[a](1, 2)
     */
    public static String referenceWithOptionSelectionAndArguments(){
        return "~F[a](1, 2)";
    }

    /**
     * ~F[a](1, 2)[1]
     */
    public static String referenceWithOptionSelectionAndArgumentsAndIndexOutputSelector(){
        return "~F[a](1, 2)[1]";
    }

    /**
     * ~F[a](1, 2)[x]
     */
    public static String referenceWithOptionSelectionAndArgumentsAndKeywordOutputSelector(){
        return "~F[a](1, 2)[x]";
    }

    /**
     * ~F(a: 1, b: 2)
     */
    public static String referenceWithKeywordArguments(){
        return "~F(a: 1, b: 2)";
    }

    /**
     * ~F[a](a: 1, b: 2)
     */
    public static String referenceWithOptionSelectionAndKeywordArguments(){
        return "~F[a](a: 1, b: 2)";
    }

    /**
     * ~F[a](a: 1, b: 2)[1]
     */
    public static String referenceWithOptionSelectionKeywordArgumentsAndIndexOutputSelector(){
        return "~F[a](a: 1, b: 2)[1]";
    }

    /**
     * ~F[a](a: 1, b: 2)[x]
     */
    public static String referenceWithOptionSelectionKeywordArgumentsAndKeywordOutputSelector(){
        return "~F[a](a: 1, b: 2)[x]";
    }

    /**
     * Add(1, 2)
     */
    public static String functionWithArgs(){
        return "Add(1, 2)";
    }

    /**
     * Add[a](1, 2)
     */
    public static String functionWithOptionAndArgs(){
        return "Add[a](1, 2)";
    }

    /**
     * Add(1, Subtract(2, 1))
     */
    public static String nestedFunctionCalls(){
        return "Add(1, Subtract(2, 1))";
    }

    /**
     * result Add(1.0, 1)
     * m Multiply(result, 2)
     */
    public static String inlineGraph(){
        return
            "result Add(1.0, 1)\n" +
            "m Multiply(result, 2)";
    }

    /**
     * A mess of expressions
     */
    public static String expressions(){
        return
            " -a + @b * 1 % 10 - 2 > F(~a) && 2.3 || \"Apples\" != G.H.I.P";
    }

    public static String path(){
        return "a.inputs[a].outputs[b].b.c.d";
    }

    /**
     * node F begin
     *     output factor Double(2.0)
     * end
     *
     * f F
     * result Add(1.0, 1)
     * m Multiply(result, f.factor)
     */
    public static String inlineGraphWithNode(){
        return
            "node F begin\n" +
            "     output factor Double(2.0)\n" +
            " end\n" +
            "\n" +
            "f F\n" +
            "result Add(1.0, 1)\n" +
            "m Multiply(result, f.factor)";
    }

    /**
     * node F begin
     *     output factor Double(2.0)
     * end
     *
     * graph g begin
     *     f F
     *     result Add(1.0, 1)
     *     m Multiply(result, f.factor)
     * end
     */
    public static String namedGraphWithNode(){
        return
            "node F begin\n" +
            "     output factor Double(2.0)\n" +
            " end\n" +
            "\n" +
            "graph g begin\n" +
            "    f F\n" +
            "    result Add(1.0, 1)\n" +
            "    m Multiply(result, f.factor)\n" +
            "end";
    }

    /**
     * node F begin
     *     output factor Double(2.0)
     * end
     *
     * graph g begin
     *     f F
     *     result Add(1.0, 1)
     *     m Multiply(result, f.factor)
     * end
     *
     * state s1 begin
     *     graph g
     * end
     */
    public static String namedGraphWithNodeAndState(){
        return
            "node F begin\n" +
            "     output factor Double(2.0)\n" +
            " end\n" +
            "\n" +
            "graph g begin\n" +
            "    f F\n" +
            "    result Add(1.0, 1)\n" +
            "    m Multiply(result, f.factor)\n" +
            "end\n" +
            "\n" +
            "state s1 begin\n" +
            "    graph g\n" +
            "end";
    }

    /**
     * node A[a] begin
     *     option input a Double
     *     option input b Double
     * end
     */
    public static String nodeWithOptions(){
        return
            "node A[a] begin\n" +
            "    option input a Double\n" +
            "    option input b Double\n" +
            "end";
    }

    /**
     * node A[a] begin
     *     option input a Double
     *     r Double(a)
     *     option input b Double(r)
     * end
     */
    public static String inputOutputInternalPortsInNode(){
        return
            "node A[a] begin\n" +
            "    option input a Double\n" +
            "    r Double(a)\n" +
            "    option input b Double\n" +
            "end";
    }

    /**
     * node A[a] begin
     *     option input a Double
     *     r Double(a)
     *     option input b Double(r)
     *     node B begin
     *          input in Double(100.0)
     *     end
     * end
     */
    public static String nestedNode(){
        return
            "node A[a] begin\n" +
            "    option input a Double\n" +
            "    r Double(a)\n" +
            "    option input b Double\n" +
            "    node B begin\n" +
            "        input in Double(100.0)\n" +
            "    end\n" +
            "end";
    }

    /**
     * state s1 begin
     *     graph g1
     * end
     */
    public static String simpleState(){
        return "state s1 begin\n" +
                "    graph g1\n" +
                "end";
    }

    /**
     * state s1 begin
     *     graph g1
     *     a[b]
     * end
     */
    public static String stateWithSingleOption(){
        return "state s1 begin\n" +
                "    graph g1\n" +
                "    a[b]\n" +
                "end";
    }

    /**
     * state s1 begin
     *     graph g1
     *     a[b]
     *         b[c]
     *             d[e]
     *         end
     *         h[i]
     *     end
     *     f[g]
     * end
     */
    public static String nestedStates(){
        return
            "state s1 begin\n" +
            "    graph g1\n" +
            "    a[b] begin\n" +
            "        b[c] begin\n" +
            "            d[e]\n" +
            "        end\n" +
            "        h[i]\n" +
            "    end\n" +
            "    f[g]\n" +
            "end";
    }

    /**
     * include "code.sro"
     */
    public static String include() {
        return "include \"code.sro\"\n";
    }

    /**
     * graph g begin
     * end
     */
    public static String emptyGraph(){
        return "graph g begin\n" +
               "end";
    }

    /**
     * graph g begin
     *     a.y(1, 2, 3)
     * end
     */
    public static String graphWithPortAsssignment(){
        return "graph g begin\n" +
                "    a.y(1, 2, 3)\n" +
                "end";
    }

    /**
     * graph g begin
     *     a.y(1, 2, 3)
     * end
     */
    public static String graphWithPortAssignmentsAndFunctionDeclarations(){
        return "graph g begin\n" +
                "    b Box[a](1, 2, 3)\n" +
                "    a.y(1, 2, 3)\n" +
                "end";
    }

    /**
     * node A begin
     *     a.y(1, 2, 3)
     * end
     */
    public static String nodeWithPortAssignment(){
        return
            "node A begin\n" +
            "    a.y(1, 2, 3)\n" +
            "end";
    }

    /**
     * node A begin
     *     a.y(1, 2, 3)
     *     node B begin
     *     end
     * end
     */
    public static String nodeWithNestedNode(){
        return
            "node A begin\n" +
            "    a.y(1, 2, 3)\n" +
            "    node B begin\n" +
            "    end\n" +
            "end";
    }

    /**
     * node Box begin
     *     input length Double
     *     input width Double
     *     length(1.0)
     *     area Multiply(length, width)
     *     output result Double(area)
     * end
     */
    public static String boxNode(){
        return
            "node Box begin\n" +
            "    input length Double\n" +
            "    input width Double\n" +
            "    length(1.0)\n" +
            "    area Multiply(length, width)\n" +
            "    output result Double(area)\n" +
            "end";
    }

    /**
     * node Box begin
     *     input length Double
     *     input width Double
     * end
     */
    public static String boxNodeWithDefs(){
        return
            "node Box begin\n" +
            "    input length Double\n" +
            "    input width Double\n" +
            "end";
    }

    /**
     * node Box begin
     *     input length Double
     *     input width Double
     * end
     *
     * b Box(length: 100.0, width: 13.5)
     */
    public static String boxNodeProgram(){
        return
            "node Box begin\n" +
            "    input length Double\n" +
            "    input width Double\n" +
            "end\n\n" +
            "b Box(length: 100.0, width: 13.5)";
    }

    /**
     * node Box begin
     *     input length Double
     *     input width Double
     * end
     *
     * b Box((length: 100.0, width: 13.5)
     */
    public static String boxNodeProgramSyntaxError(){
        return
            "node Box begin\n" +
            "    input length Double\n" +
            "    input width Double\n" +
            "end\n\n" +
            "b Box((length: 100.0, width: 13.5)";
    }
}
