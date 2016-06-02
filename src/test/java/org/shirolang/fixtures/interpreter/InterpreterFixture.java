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
     * result Add(1.0, 1)
     * m Multiply(result, 2)
     */
    public static String inlineGraph(){
        return
            "result Add(1.0, 1)\n" +
            "m Multiply(result, 2)";
    }

    /**
     * A mess
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
}
