package shiro.interpreter;

import shiro.interpreter.ShiroParser.ActivationContext;
import shiro.definitions.GraphDefinition;
import shiro.Node;
import shiro.Port;
import shiro.SubjunctiveParametricSystem;
import shiro.Symbol;
import shiro.dag.DAGraph;
import shiro.expressions.Path;

/**
 * Build dependency graph from graph declaration parse tree
 *
 * @author jeffreyguenther
 */
public class GraphBuilderListener extends ShiroBasePassListener {
    private GraphDefinition graphDef;
    private DAGraph<Port> graph;

    public GraphBuilderListener(SubjunctiveParametricSystem ps) {
        super(ps);
        graph = new DAGraph<>();
    }

    public GraphDefinition getGraphDef() {
        return graphDef;
    }
    
    public DAGraph<Port> getGraph(){
        return graph;
    }

    @Override
    public void enterGraphDecl(ShiroParser.GraphDeclContext ctx) {
        graphDef = new GraphDefinition(ctx.IDENT().getText());
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
            graphDef.addNode(producedNode);

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
