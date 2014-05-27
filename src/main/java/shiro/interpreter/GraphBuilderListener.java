package shiro.interpreter;

import shiro.Graph;
import shiro.interpreter.ShiroParser.ActivationContext;
import shiro.Node;
import shiro.Symbol;
import shiro.expressions.Path;

/**
 * Build dependency graph from graph declaration parse tree
 *
 * @author jeffreyguenther
 */
public class GraphBuilderListener extends ShiroBasePassListener {
    private Graph graph;
    private shiro.ShiroRuntime runtime;

    public GraphBuilderListener(shiro.ShiroRuntime rt, Graph g) {
        super(g);
        graph = g;
        runtime = rt;
    }

    @Override
    public void exitNodeProduction(ShiroParser.NodeProductionContext ctx) {
        // get the path of LHS of production operator
        Path leftHandSide = (Path) getExpr(ctx.path());

        // for each activation
        for (ActivationContext ac : ctx.activation()) {
            String nodeName = ac.nodeName.getText();
            
            Symbol producedSymbol = runtime.produceSymbolFromName(graph, leftHandSide.getPath(), nodeName);
            Node producedNode = (Node) producedSymbol;
            
            graph.addNode(producedNode);

            if (ac.activeObject != null) {
                String updatePort = ac.activeObject.getText();
                producedNode.setActiveOption(updatePort);
            }
        }
    }

    @Override
    public void exitPortAssignment(ShiroParser.PortAssignmentContext ctx) {
        super.exitPortAssignment(ctx);
        // TODO store the port assignment in the graph definition
    }
    
    
}
