package shiro.interpreter;

import main.java.shiro.interpreter.ShiroBaseListener;
import main.java.shiro.interpreter.ShiroParser;

/**
 *
 * @author jeffreyguenther
 */
public class GraphPass extends ShiroBaseListener {

    public GraphPass() {
    }

    @Override
    public void enterNodestmt(ShiroParser.NodestmtContext ctx) {
        System.out.println("Realize the node");
    }
    
}
