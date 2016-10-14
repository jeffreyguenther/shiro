package org.shirolang.interpreter.v2;

import org.shirolang.base.SFunc;
import org.shirolang.base.Scope;
import org.shirolang.functions.math.*;
import org.shirolang.interpreter.ast.*;
import org.shirolang.values.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Class that contains visitor methods shared between visitors
 */
public abstract class BaseVisitor {
    protected List<Error> errors;
    protected Stack<Scope> scope;
    protected ProgramEvaluator evaluator;

    public BaseVisitor(ProgramEvaluator evaluator) {
        this.errors = new ArrayList<>();
        this.scope = new Stack<>();
        this.evaluator = evaluator;
    }

    /**
     * Gets a list of errors
     * Errors are added in the order they occur
     * @return the list of errors
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * Determines if a visitor has errors
     * @return true if the list of errors is not empty, otherwise false
     */
    public boolean hasErrors(){
        return !errors.isEmpty();
    }

    protected SFunc assignArguments(SFunc func, FunctionBase functionDef){
        SFunc withArgs = null;

        if(functionDef.hasListArgs()){
            withArgs = setArgsFromList(func, functionDef);
        }else if(functionDef.hasKeywordArgs()){
            withArgs = setArgsFromMap(func, functionDef);
        }

        return withArgs;
    }

    private SFunc setArgsFromList(SFunc func, FunctionBase functionDef){
        List<SFunc> args = functionDef.getArgList().stream().map(this::visit).collect(Collectors.toList());

        if(!args.isEmpty()) {
            SFunc arg0 = args.get(0);
            if (args.size() == 1 && arg0.getType().equals(func.getType())) {
                arg0.setAccess(func.getAccess());
                arg0.setName(func.getName());
                arg0.setSymbolType(func.getSymbolType());

                return arg0;
            } else {
                if(func.getSymbolType().isPort() || func.hasInputs()) {
                    for (int i = 0; i < args.size(); i++) {
                        func.setInput(i, args.get(i));
                    }
                }else{
                    errors.add(new SyntaxError(func.getFullName() + " has not inputs."));
                }
                return func;
            }
        }else{
            return func;
        }
    }

    private SFunc setArgsFromMap(SFunc func, FunctionBase functionDef){
        for(Map.Entry<String, Expression> arg: functionDef.getArgMap().entrySet()){
            func.setInput(arg.getKey(), visit(arg.getValue()));
        }

        return func;
    }

    public SFunc visit(Expression expr){
        if(expr instanceof BinaryOperation){
            return visit((BinaryOperation) expr);
        }else if (expr instanceof UnaryOperation){
            return visit((UnaryOperation) expr);
        }else{
            return visit((Literal) expr);
        }
    }

    public SFunc visit(BinaryOperation op){
        switch(op.getOperator()){
            case ADD:
                return new SAdd(visit(op.getLeft()), visit(op.getRight()));
            case SUBTRACT:
                return new SSubtract(visit(op.getLeft()), visit(op.getRight()));
            case DIVIDE:
                return new SDivide(visit(op.getLeft()), visit(op.getRight()));
            case MULTIPLY:
                return new SMultiply(visit(op.getLeft()), visit(op.getRight()));
            case MOD:
                return new SModulo(visit(op.getLeft()), visit(op.getRight()));
            case AND:
                return new SAnd(visit(op.getLeft()), visit(op.getRight()));
            case OR:
                return new SOr(visit(op.getLeft()), visit(op.getRight()));
            case EQUAL:
                return new SEqual(visit(op.getLeft()), visit(op.getRight()));
            case NOT_EQUAL:
                return new SNotEqual(visit(op.getLeft()), visit(op.getRight()));
            case GREATER_THAN:
                return new SGreaterThan(visit(op.getLeft()), visit(op.getRight()));
            case GREATER_THAN_OR_EQUAL:
                return new SGreaterThanOrEqual(visit(op.getLeft()), visit(op.getRight()));
            case LESS_THAN:
                return new SLessThan(visit(op.getLeft()), visit(op.getRight()));
            case LESS_THAN_OR_EQUAL:
                return new SLessThanOrEqual(visit(op.getLeft()), visit(op.getRight()));
            default:
                return null;
        }
    }

    public SFunc visit(UnaryOperation op){
        switch(op.getOperator()) {
            case NEGATE:
                return new SNegative(visit(op.getOperand()));
            case NOT:
                return new SNot(visit(op.getOperand()));
            default:
                return visit(op.getOperand());
        }
    }

    public SFunc visit(Literal<?> l){
        if(l.getValue() instanceof Double){
            return new SDouble((Double) l.getValue());
        }else if(l.getValue() instanceof Integer){
            return new SInteger((Integer) l.getValue());
        }else if (l.getValue() instanceof Boolean){
            return new SBoolean((Boolean) l.getValue());
        }else if (l.getValue() instanceof String){
            return new SString((String) l.getValue());
        }else if(l.getValue() instanceof org.shirolang.interpreter.ast.Path){
            return new SIdent(scope.peek(), (org.shirolang.interpreter.ast.Path) l.getValue());
        }

        return null;
    }
}
