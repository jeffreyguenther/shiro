package org.shirolang.interpreter.v2;

/**
 * Provides abstract methods for multipass visitors
 */
public abstract class MultiPassVisitor extends BaseVisitor {
    public static final int FIRST_PASS = 1;
    public static final int SECOND_PASS = 2;
    protected int pass;

    public MultiPassVisitor(ProgramEvaluator evaluator) {
        super(evaluator);
        this.pass = FIRST_PASS;
    }

    /**
     * Sets the pass of the visitor
     *
     * @param pass possible values are FIRST_PASS (1) and SECOND_PASS (2)
     */
    public void setPass(int pass) {
        this.pass = pass;
    }
}
