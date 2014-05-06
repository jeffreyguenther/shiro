package shiro.interpreter;

import java.util.ArrayList;
import java.util.List;
import shiro.interpreter.ShiroParser.ActivationContext;
import shiro.definitions.GraphDefinition;
import shiro.Node;
import shiro.Port;
import shiro.SubjunctiveParametricSystem;
import shiro.Symbol;
import shiro.dag.DependencyRelation;
import shiro.expressions.Path;

/**
 * Build dependency graph from graph declaration parse tree
 *
 * @author jeffreyguenther
 */
public class GraphBuilderListener extends ShiroBasePassListener {
    private GraphDefinition graphDef;

    public GraphBuilderListener(SubjunctiveParametricSystem ps) {
        super(ps);
    }

    public GraphDefinition getGraphDef() {
        return graphDef;
    }

    @Override
    public void enterGraphDecl(ShiroParser.GraphDeclContext ctx) {
        graphDef = new GraphDefinition(ctx.IDENT().getText());
    }

    @Override
    public void exitGraphDecl(ShiroParser.GraphDeclContext ctx) {
        // Once all of the nodes have been produced an assignments made,
        // build the dependency graph.
        
        List<DependencyRelation<Port>> deps = new ArrayList<>();

        // for each node generated in the graph generation process
        for (Node n : pSystem.getNodes()) {
            // get all of the dependencies for each node
            deps.addAll(n.getDependencies());
        }

        // resolve the dependencies between ports as indicated by expressions
        // by adding dependencies between the two ports to the graph
//        System.out.println();
//        System.out.println("Dependencies");
        for (DependencyRelation<Port> d : deps) {
            pSystem.addDependency(d);
        }
    }

    @Override
    public void exitNodeProduction(ShiroParser.NodeProductionContext ctx) {
        // get the path of LHS of production operator
        Path leftHandSide = (Path) getExpr(ctx.path());

        // for each activation
        for (ActivationContext ac : ctx.activation()) {
            String nodeName = ac.nodeName.getText();

            //TODO this might not work in the long run with long path names
            graphDef.addProduction(leftHandSide.getCurrentPathHead(), nodeName);

            // need to differentiate between creating nodes and subjunctive nodes
            Symbol producedSymbol = pSystem.produceSymbolFromName(leftHandSide.getPath(), nodeName);
            Node producedNode = (Node) producedSymbol;
            pSystem.addNode(producedNode);

            if (ac.activeObject != null) {
                String updatePort = ac.activeObject.getText();
                producedNode.setActiveOption(updatePort);

                System.out.println("Node produced: "
                        + producedNode.getFullName() + " with active update ");
                System.out.println();
            }
        }
    }
}
