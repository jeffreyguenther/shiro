package shiro.interpreter;

import java.util.HashMap;
import java.util.Map;
import main.java.shiro.interpreter.ShiroBaseListener;
import main.java.shiro.interpreter.ShiroParser;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Pass 1 of Interpreter
 * Gets the node definitions by saving the ParseTrees of the nodes
 * @author jeffreyguenther
 */
public class NodeDefinitionListener extends ShiroBaseListener{
    private Map<String, ParseTree> defs;

    public NodeDefinitionListener() {
        super();
        defs = new HashMap<String, ParseTree>();
    }
    
    /**
     * Get the node ParseTrees
     * @return map of the node name to parse tree
     */
    public Map<String, ParseTree> getNodeDefinitions() {
        return defs;
    }

    @Override
    public void enterNodestmt(ShiroParser.NodestmtContext ctx) {
        String name = ctx.IDENT().getText();
        defs.put(name, ctx);
        System.out.format("Saved %s\n", name);
    }
}
