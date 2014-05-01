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
    private Map<String, ParseTree> alternativeDefs;
    private Map<String, ParseTree> graphs;
    private boolean locked;

    public NodeDefinitionListener() {
        super();
        defs = new HashMap<>();
        alternativeDefs = new HashMap<>();
        graphs = new HashMap<>();
        locked = false;
    }
    
    /**
     * Get the node ParseTrees
     * @return map of node definitions mapped by name
     */
    public Map<String, ParseTree> getNodeDefinitions() {
        return defs;
    }
    
    /**
     * Get the alternative ParseTrees
     * @return map of parse trees mapped by name
     */
    public Map<String, ParseTree> getAlternativeDefinitions(){
        return alternativeDefs;
    }
    
    /**
     * Get the parse tree for the graph definition
     * @return parse tree for the graph definition
     */
    public Map<String, ParseTree> getGraphs() {
        return graphs;
    }
    
    @Override
    public void enterNodestmt(ShiroParser.NodestmtContext ctx) {
        // The locked flag is used to prevent nested nodes from being captured
        // as individual trees. Nested nodes will be children of their root
        // node
        if(!locked){
            String name = ctx.IDENT().getText();
            defs.put(name, ctx);
            locked = true;
        }
    }

    @Override
    public void exitNodestmt(ShiroParser.NodestmtContext ctx) {
        locked = false;
    }
    
    @Override
    public void enterGraphDecl(ShiroParser.GraphDeclContext ctx) {
        graphs.put(ctx.IDENT().getText(), ctx);
    }

    @Override
    public void enterStatestmt(ShiroParser.StatestmtContext ctx) {
        alternativeDefs.put(ctx.stateName().getText(), ctx);
    }
}
