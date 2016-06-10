package org.shirolang.interpreter.v2;

import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.SNode;
import org.shirolang.base.SymbolType;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.interpreter.ast.*;

/**
 * Visits a <code>NodeDefinition</code> to create an instance of <code>SNode</code>
 */
public class NodeVisitor extends MultiPassVisitor{
    private SGraph graph;

    public NodeVisitor(SymbolTable symbolTable, SGraph graph) {
        super(symbolTable);
        this.scope.push(graph);
        this.graph = graph;
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

            try {
                node.setDefaultOption(def.getDefaultOption());
            } catch (OptionNotFoundException e) {
             // TODO create error
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

    public SGraph getGraph() {
        return graph;
    }
}
