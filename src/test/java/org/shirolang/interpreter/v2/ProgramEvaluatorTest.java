package org.shirolang.interpreter.v2;

import org.junit.Before;
import org.junit.Test;
import org.shirolang.fixtures.interpreter.InterpreterFixture;
import org.shirolang.interpreter.CodeImporter;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProgramEvaluatorTest {
    private SymbolTable t;
    private ProgramEvaluator evaluator;

    @Before
    public void setupTest(){
        t = new SymbolTable();
        evaluator = new ProgramEvaluator(t);
    }
    @Test
    public void create(){
        SymbolTable t = new SymbolTable();
        ProgramEvaluator evaluator = new ProgramEvaluator(t);
        assertNotNull(evaluator);
    }

    @Test
    public void evaluateStringWithSyntaxError(){
        List<Error> errors = evaluator.evaluate(InterpreterFixture.boxNodeProgramSyntaxError());
        assertEquals(4, errors.size());
    }

    @Test
    public void evaluateBadPath(){
        List<Error> errors = evaluator.evaluate(Paths.get("/Users/mess/boom"));
        assertEquals(1, errors.size());
        assertEquals("/Users/mess/boom cannot be opened.", errors.get(0).getMessage());
    }

    @Test
    public void evaluateCodeWithSyntaxErrors(){
        List<Error> errors = evaluator.evaluate("node A be\n");
        assertEquals(1, errors.size());
    }

    @Test
    public void evaluatePathWithSyntaxErrors(){
        List<Error> errors = evaluator.evaluate(Paths.get(CodeImporter.class.getResource("syntax_errors.sro").getPath()));
        assertEquals(1, errors.size());
    }
}