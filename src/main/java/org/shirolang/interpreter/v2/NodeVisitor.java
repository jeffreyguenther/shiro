package org.shirolang.interpreter.v2;

import org.shirolang.base.*;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.functions.math.SNegative;
import org.shirolang.functions.math.SNot;
import org.shirolang.interpreter.ast.*;
import org.shirolang.values.*;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Visits a <code>NodeDefinition</code> to create an instance of <code>SNode</code>
 */
public class NodeVisitor {
    private SymbolTable symbolTable;
    protected Stack<Scope> scope;
    private SGraph graph;

    public NodeVisitor(SymbolTable symbolTable, SGraph graph) {
        this.symbolTable = symbolTable;
        this.scope = new Stack<>();
        this.scope.push(graph);
    }

    public SNode visit(NodeDefinition def){
        if(scope.size() > 1){

            return null;
        }else{
            SNode node = new SNode(def.getName(), def.getName(), scope.peek());
            scope.push(node);

            for(PortDefinition d: def.getDeclarations()){
                SFunc port = visit(d);
                if(d.isOption()){
                    node.addOption(port);
                }else{
                    node.addFunction(port);
                }
            }

            return node;
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

        if(funcDef.hasListArgs()){
            List<SFunc> args = funcDef.getArgList().stream().map(this::visit).collect(Collectors.toList());
            for (int i = 0; i < args.size(); i++) {
                function.setInput(i, args.get(i));
            }

        }else if (funcDef.hasKeywordArgs()){

        }


        return function;
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
                return null;
            case SUBTRACT:
                return null;
            case DIVIDE:
                return null;
            case MULTIPLY:
                return null;
            case MOD:
                return null;
            case AND:
                return null;
            case OR:
                return null;
            case EQUAL:
                return null;
            case NOT_EQUAL:
                return null;
            case GREATER_THAN:
                return null;
            case GREATER_THAN_OR_EQUAL:
                return null;
            case LESS_THAN:
                return null;
            case LESS_THAN_OR_EQUAL:
                return null;
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
