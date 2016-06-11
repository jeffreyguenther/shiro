package org.shirolang.interpreter.v2;

import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.SNode;
import org.shirolang.base.SymbolType;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.interpreter.ast.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Walks a graph AST to generate a SGraph instance
 */
public class GraphVisitor extends MultiPassVisitor{

    public GraphVisitor(ProgramEvaluator evaluator) {
        super(evaluator);
    }

    public SGraph visit(GraphDefinition def){
        if(pass == FIRST_PASS) {
            SGraph graph = new SGraph(def.getName());
            scope.push(graph);

            for (Expression expr : def.getAnonymousExpressions()) {
                SFunc visit = visit(expr);
                graph.addAnonymousPort(visit);
            }

            for (FunctionDefinition portDef : def.getFunctions()) {
                graph.addFunction(visit(portDef));
            }
            return graph;
        }else {
            def.getAssignments().forEach(this::visit);
            return null;
        }
    }

    public SFunc visit(FunctionDefinition funcDef){
        String type = funcDef.getType();

        SFunc function = evaluator.createFunction(getGraph(), type);
        function.setName(funcDef.getName());

        if(!function.getSymbolType().isNode()) {
            function.setSymbolType(SymbolType.PORT);
        }

        if(funcDef.hasActiveOption()){
            if(function.getSymbolType().isNode()){
                SNode node = (SNode) function;
                try {
                    node.setActiveOption(funcDef.getOption());
                } catch (OptionNotFoundException e) {
                    //TODO create error
                }
            }
        }

        return assignArguments(function, funcDef);
    }

    public void visit(PortAssignment assignment){
        if(pass == SECOND_PASS) {
            Path path = assignment.getPath();
            try {
                SFunc function = scope.peek().resolvePath(path);
                if (function.getSymbolType().isPort()) {
                    if (function.getAccess().isReadWrite()) {
                        Path p = new Path();
                        List<PathSegment> segments = new ArrayList<>(path.getSegments());
                        segments.remove(path.getLast());
                        segments.forEach(p::addSegment);
                        SNode node = (SNode) scope.peek().resolvePath(p);
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

    public SGraph getGraph(){
        return (SGraph) scope.peek();
    }

}