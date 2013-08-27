package shiro.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.shiro.interpreter.ShiroParser;
import main.java.shiro.interpreter.ShiroParser.ActivationContext;
import org.antlr.v4.runtime.tree.ParseTree;
import shiro.Node;
import shiro.PathNotFoundException;
import shiro.Port;
import shiro.SubjunctiveNode;
import shiro.SubjunctiveParametricSystem;
import shiro.Symbol;
import shiro.SymbolType;
import shiro.dag.DependencyRelation;
import shiro.expressions.Expression;
import shiro.expressions.Path;

/**
 * Build dependency graph from graph declaration parse tree
 *
 * @author jeffreyguenther
 */
public class GraphBuilderListener extends ShiroBasePassListener {

    private Node currentNode;

    public GraphBuilderListener(SubjunctiveParametricSystem ps) {
        super(ps);
        currentNode = null;
    }

    @Override
    public void enterGraphDecl(ShiroParser.GraphDeclContext ctx) {
        System.out.println("Enter graph Decl");
    }

    @Override
    public void exitGraphDecl(ShiroParser.GraphDeclContext ctx) {
        List<DependencyRelation<Port>> deps = new ArrayList<DependencyRelation<Port>>();

        // for each node generated in the graph generation process
        for (Node n : pSystem.getNodes()) {
            // get all of the dependencies for each node
            deps.addAll(n.getDependencies());
        }

        // resolve the dependencies between ports as indicated by expressions
        // by adding dependencies between the two ports to the graph
        System.out.println();
        System.out.println("Dependencies");
        for (DependencyRelation<Port> d : deps) {
            System.out.println(d.getDependent().getFullName() + " -> " + d.getDependedOn().getFullName());
            pSystem.addDependency(d);
        }
    }

    @Override
    public void exitNodeProduction(ShiroParser.NodeProductionContext ctx) {
        System.out.println("exit node production");

        // get the path of LHS of production operator
        Path leftHandSide = (Path) getExpr(ctx.path());

        // for each activation
        for (ActivationContext ac : ctx.activation()) {
            String nodeName = ac.nodeName.getText();

            // need to differentiate between creating nodes and subjunctive nodes
            Symbol producedSymbol = pSystem.produceSymbolFromName(leftHandSide.getPathAsString(), nodeName);

            if (producedSymbol.getType() == SymbolType.Node) {
                Node producedNode = (Node) producedSymbol;
                //pSystem.produceNodeFromName(leftHandSide.getPathAsString(), nodeName);

                if (ac.activeObject != null) {
                    try {
                        String updatePort = ac.activeObject.getText();
                        producedNode.activate(updatePort);
                    } catch (PathNotFoundException ex) {
                        Logger.getLogger(GraphBuilderListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                System.out.println("Node produced: "
                        + producedNode.getFullName() + " with active update "
                        + producedNode.getSelectedEvaluatedPort());
                System.out.println();
                
            } else if (producedSymbol.getType() == SymbolType.SubjNode) {
                SubjunctiveNode subjNode = (SubjunctiveNode) producedSymbol;
                
                if (ac.activeObject != null) {
                    try {
                        String activeSubjunct = ac.activeObject.getText();
                        subjNode.activate(activeSubjunct);
                    } catch (PathNotFoundException ex) {
                        Logger.getLogger(GraphBuilderListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                System.out.println("SubjNode produced: "
                        + subjNode.getFullName() + " with active subjunct "
                        + subjNode.getActiveSubjunct());
                System.out.println();
            }
        }
    }

    @Override
    public void enterPortAssignment(ShiroParser.PortAssignmentContext ctx) {
        // Get the node matched by the path to set the scope for expressions
        Path p = createPath(currentScope, ctx.path());
        currentNode = pSystem.getNode(p);
        currentScope = currentNode;
    }

    @Override
    public void exitPortAssignment(ShiroParser.PortAssignmentContext ctx) {
        // look up port based on the path
        try {
            Port p = (Port) pSystem.resolvePath((Path) getExpr(ctx.path()));

            List<Expression> mfExpressions = new ArrayList<Expression>();

            for (ParseTree pt : ctx.mfparams().expr()) {
                Expression exp = getExpr(pt);
                mfExpressions.add(exp);
            }
            // set the port's expression
            p.setArguments(mfExpressions);
            System.out.println("Set port args: " + p);
            System.out.println("Node is now:\n" + currentNode);
        } catch (PathNotFoundException pnfe) {
            System.out.println(pnfe);
        }
    }
}
