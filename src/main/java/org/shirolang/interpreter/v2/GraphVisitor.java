package org.shirolang.interpreter.v2;

import org.shirolang.base.SFunc;
import org.shirolang.base.SGraph;
import org.shirolang.base.SNode;
import org.shirolang.base.SymbolType;
import org.shirolang.exceptions.OptionNotFoundException;
import org.shirolang.exceptions.PathNotFoundException;
import org.shirolang.interpreter.ast.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                Optional<SFunc> visit = visit(portDef);
                if(visit.isPresent()){
                    graph.addFunction(visit.get());
                }else{
                    break;
                }
            }
            return graph;
        }else {
            def.getAssignments().forEach(this::visit);
            return null;
        }
    }

    public Optional<SFunc> visit(FunctionDefinition funcDef){
        String type = funcDef.getType();
        SFunc function;

        Optional<SFunc> instance = evaluator.createFunction(getGraph(), type, funcDef.getName());
        if(instance.isPresent()) {
            function = instance.get();

            if (!function.getSymbolType().isNode()) {
                function.setSymbolType(SymbolType.PORT);
            }

            if (funcDef.hasActiveOption()) {
                if (function.getSymbolType().isNode()) {
                    SNode node = (SNode) function;
                    try {
                        node.setActiveOption(funcDef.getOption());
                    } catch (OptionNotFoundException e) {
                        errors.add(new SyntaxError(e.getMessage()));
                    }
                }
            }

            return Optional.of(assignArguments(function, funcDef));
        }else{
            errors.add(new TypeNotFoundError(type + " cannot be found."));
            return Optional.empty();
        }
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
                errors.add(new PathNotFoundError(e.getMessage()));
            }
        }
    }

    public SGraph getGraph(){
        return (SGraph) scope.peek();
    }

}