package org.shirolang.fixtures.ast;

import org.shirolang.interpreter.ast.*;
import org.shirolang.values.SegmentType;

import javax.sound.sampled.Port;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Creates function calls for testing
 */
public class PortAssignmentFixture {
    /**
     * a.y(1, 2, 3)
     */
    public static PortAssignment withPathAndListOfArgs(){
        return new PortAssignment(Path.create("a.y"),
                Arrays.asList(Literal.asInteger(1), Literal.asInteger(2), Literal.asInteger(3)));
    }

    /**
     * x.y(a: 1, b: 10)
     */
    public static PortAssignment withPathAndKeywordargs(){
        Map<String, Expression> args = new HashMap<>();
        args.put("a", new Literal<>(1));
        args.put("b", new Literal<>(10));

        return new PortAssignment(Path.create("x.y"), args);
    }

    /**
     * outputs[a].inputs[y](1, 2, 3)
     */
    public static PortAssignment withInputsOutputsAndListOfArgs(){
        Path p = new Path();
        p.addSegment(new PathSegment(SegmentType.OUTPUT, "a"));
        p.addSegment(new PathSegment(SegmentType.INPUT, "y"));

        return new PortAssignment(p,
                Arrays.asList(Literal.asInteger(1), Literal.asInteger(2), Literal.asInteger(3)));
    }
}