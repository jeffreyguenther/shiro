package org.shirolang.interpreter.v2;

import org.junit.Before;
import org.junit.Test;
import org.shirolang.fixtures.interpreter.InterpreterFixture;
import org.shirolang.interpreter.CodeImporter;

import java.nio.file.Path;
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

    @Test
    public void active_option_doesnt_exist(){
        List<Error> errors = evaluator.evaluate(p("errors/active_option_doesnt_exist.sro"));
        assertEquals(1, errors.size());
        assertEquals("^.o does not have an option b", errors.get(0).getMessage());
    }

    @Test
    public void type_doesnt_exist(){
        List<Error> errors = evaluator.evaluate(p("errors/type_doesnt_exist.sro"));
        assertEquals(1, errors.size());
        assertEquals("Option cannot be found.", errors.get(0).getMessage());
    }

    @Test
    public void path_assignment_path_not_found(){
        List<Error> errors = evaluator.evaluate(p("errors/path_assignment_path_not_found.sro"));
        assertEquals(1, errors.size());
        assertEquals("([leeeength]) was not found.", errors.get(0).getMessage());
    }

    @Test
    public void option_selected_as_default_doesnt_exist(){
        List<Error> errors = evaluator.evaluate(p("errors/option_selected_as_default_doesnt_exist.sro"));
        assertEquals(1, errors.size());
        assertEquals("^.o does not have an option b", errors.get(0).getMessage());
    }

    @Test
    public void assign_to_internal_port(){
        List<Error> errors = evaluator.evaluate(p("errors/assign_to_internal_port.sro"));
        assertEquals(1, errors.size());
    }

    @Test
    public void assign_to_internal_port_in_decl(){
        List<Error> errors = evaluator.evaluate(p("errors/assign_to_internal_port_in_decl.sro"));
        assertEquals(1, errors.size());
    }

    @Test
    public void assign_to_output(){
        List<Error> errors = evaluator.evaluate(p("errors/assign_to_output.sro"));
        assertEquals(1, errors.size());
    }

    @Test
    public void read_from_internal_port(){
        List<Error> errors = evaluator.evaluate(p("errors/read_from_internal_port.sro"));
        assertEquals(1, errors.size());
        // TODO
    }

    private Path p(String file){
        return Paths.get(CodeImporter.class.getResource(file).getPath());
    }

    private void e(List<Error> errors){
        errors.forEach(e -> System.out.println(e.getMessage()));
    }
}