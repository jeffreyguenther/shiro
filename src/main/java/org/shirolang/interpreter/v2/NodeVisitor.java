package org.shirolang.interpreter.v2;

import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.SNode;
import org.shirolang.base.SymbolType;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.interpreter.ast.*;

import java.util.Optional;

/**
 * Visits a <code>NodeDefinition</code> to create an instance of <code>SNode</code>
 */
public class NodeVisitor extends MultiPassVisitor{
    private SGraph graph;

    public NodeVisitor(ProgramEvaluator evaluator, SGraph graph) {
        super(evaluator);
        this.scope.push(graph);
        this.graph = graph;
    }

    public SNode visit(NodeDefinition def){
        if(pass == FIRST_PASS) {

            SNode node = new SNode(def.getName(), "", scope.peek());
            scope.push(node);

            for (PortDefinition d : def.getDeclarations()) {
                Optional<SFunc> visit = visit(d);
                if(visit.isPresent()) {
                    SFunc port = visit.get();
                    if (d.isOption()) {
                        node.addOption(port);
                    } else {
                        node.addFunction(port);
                    }
                }else{
                    break;
                }
            }
            return node;
        }else{
            SNode node = (SNode) scope.peek();
            try {

                if(def.hasDefaultOption()) {
                    node.setDefaultOption(def.getDefaultOption());
                }
            } catch (OptionNotFoundException e) {
                errors.add(new SyntaxError(e.getMessage()));
            }

            if(!hasErrors()){
                def.getAssignments().forEach(this::visit);
            }
            return node;
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
                        errors.add(new SyntaxError(path + " is " + function.getAccess() + ". It's inputs cannot be set."));
                    }
                } else if (function.getSymbolType().isNode()) {
                    throw new RuntimeException(path + " is a node. It cannot be assigned.");
                }
            } catch (PathNotFoundException e) {
                errors.add(new PathNotFoundError(e.getMessage()));
            }
        }
    }

    public Optional<SFunc> visit(PortDefinition def) {
        FunctionDefinition funcDef = def.getFunction();
        String type = funcDef.getType();
        SFunc function;

        Optional<SFunc> instance = evaluator.createFunction(getGraph(), type);
        if(instance.isPresent()) {
            function = instance.get();
            function.setAccess(def.getAccess());
            function.setName(funcDef.getName());

            if (def.getFunction().hasActiveOption()) {
                try {
                    ((SNode) function).setActiveOption(funcDef.getOption());
                } catch (OptionNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (!function.getSymbolType().isNode()) {
                function.setSymbolType(SymbolType.PORT);
            }

            return Optional.of(assignArguments(function, funcDef));
        }else{
            return Optional.empty();
        }
    }

    public SGraph getGraph() {
        return graph;
    }
}
