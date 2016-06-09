package org.shirolang.interpreter.v2;

import org.shirolang.base.*;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.functions.math.*;
import org.shirolang.interpreter.ast.*;
import org.shirolang.values.*;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Visits a <code>NodeDefinition</code> to create an instance of <code>SNode</code>
 */
public class NodeVisitor {
    public static final int FIRST_PASS = 1;
    public static final int SECOND_PASS = 2;
    private SymbolTable symbolTable;
    protected Stack<Scope> scope;
    private SGraph graph;
    private int pass;

    public NodeVisitor(SymbolTable symbolTable, SGraph graph) {
        this.symbolTable = symbolTable;
        this.scope = new Stack<>();
        this.scope.push(graph);
        this.graph = graph;
        this.pass = FIRST_PASS;
    }

    /**
     * Sets the pass of the visitor
     * @param pass possible values are FIRST_PASS (1) and SECOND_PASS (2)
     */
    public void setPass(int pass) {
        this.pass = pass;
    }

    public SNode visit(NodeDefinition def){
        if(pass == FIRST_PASS) {

            SNode node = new SNode(def.getName(), def.getName(), scope.peek());
            scope.push(node);

            for (PortDefinition d : def.getDeclarations()) {
                SFunc port = visit(d);
                if (d.isOption()) {
                    node.addOption(port);
                } else {
                    node.addFunction(port);
                }
            }
            return node;
        }else{
            def.getAssignments().forEach(this::visit);
            return null;
        }
    }

    public void visit(PortAssignment assignment){
        if(pass == SECOND_PASS) {
            Path path = assignment.getPath();
            try {
                SFunc function = scope.peek().resolvePath(path);
                if (function.getSymbolType().isPort()) {
                    if (function.getAccess().isReadWrite()) {
                        SNode node = (SNode) scope.peek();
                        node.addFunction(assignArguments(function, assignment));
                    } else {
                        throw new RuntimeException(path + " is " + function.getAccess() + ". It's inputs cannot be set.");
                    }
                } else if (function.getSymbolType().isNode()) {
                    throw new RuntimeException(path + " is a node. It cannot be assigned.");
                }
            } catch (PathNotFoundException e) {
                // TODO create error
            }
        }
    }

    public SFunc visit(PortDefinition def) {
        FunctionDefinition funcDef = def.getFunction();
        String type = funcDef.getType();

        SFunc function = symbolTable.createFunction(getGraph(), type);
        function.setAccess(def.getAccess());
        function.setName(funcDef.getName());

        if (def.getFunction().hasActiveOption()) {
            try {
                ((SNode) function).setActiveOption(funcDef.getOption());
            } catch (OptionNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        if(!function.getSymbolType().isNode()) {
            function.setSymbolType(SymbolType.PORT);
        }

        return assignArguments(function, funcDef);
    }

    private SFunc assignArguments(SFunc func, FunctionBase functionDef){
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
                for (int i = 0; i < args.size(); i++) {
                    func.setInput(i, args.get(i));
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
        }else if(l.getValue() instanceof Path){
            return new SIdent(scope.peek(), (Path) l.getValue());
        }

        return null;
    }

    public SGraph getGraph() {
        return graph;
    }
}
