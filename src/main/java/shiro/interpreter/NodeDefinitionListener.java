package shiro.interpreter;

import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Pass 1 of Interpreter
 * Gets the node and graph declarations by saving the ParseTrees of the nodes
 * @author jeffreyguenther
 */
public class NodeDefinitionListener extends ShiroBaseListener{
    private Map<String, ParseTree> defs;
    private Map<String, ParseTree> subjDefs;
    private Map<String, ParseTree> alternativeDefs;
    private ParseTree graph;

    public NodeDefinitionListener() {
        super();
        defs = new HashMap<String, ParseTree>();
        subjDefs = new HashMap<String, ParseTree>();
        alternativeDefs = new HashMap<String, ParseTree>();
    }
    
    /**
     * Get the node ParseTrees
     * @return map of the node name to parse tree
     */
    public Map<String, ParseTree> getNodeDefinitions() {
        return defs;
    }
    
    public Map<String, ParseTree> getSubjNodeDefinitions(){
        return subjDefs;
    }
    
    public Map<String, ParseTree> getAlternativeDefinitions(){
        return alternativeDefs;
    }
    
    /**
     * Get the parse tree for the graph definition
     * @return parse tree for the graph definition
     */
    public ParseTree getGraph() {
        return graph;
    }
    
    @Override
    public void enterNodestmt(ShiroParser.NodestmtContext ctx) {
        String name = ctx.IDENT().getText();
        defs.put(name, ctx);
    }

    @Override
    public void enterSNode(ShiroParser.SNodeContext ctx) {
        String name = ctx.nodeName.getText();
        subjDefs.put(name, ctx);
    }

    @Override
    public void enterGraphDecl(ShiroParser.GraphDeclContext ctx) {
        graph = ctx;
    }

    @Override
    public void enterStatestmt(ShiroParser.StatestmtContext ctx) {
        alternativeDefs.put(ctx.stateName().getText(), ctx);
    }
    
    
}
