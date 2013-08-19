package shiro.interpreter;

import java.util.HashMap;
import java.util.Map;
import main.java.shiro.interpreter.ShiroBaseListener;
import main.java.shiro.interpreter.ShiroParser;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author jeffreyguenther
 */
public class DefPass extends ShiroBaseListener{
    public Map<String, ParseTree> defs;

    public DefPass() {
        super();
        defs = new HashMap<String, ParseTree>();
    }

    @Override
    public void enterNodestmt(ShiroParser.NodestmtContext ctx) {
        String name = ctx.IDENT().getText();
        defs.put(name, ctx);
        System.out.format("Saved %s\n", name);
    }
    
    
}
