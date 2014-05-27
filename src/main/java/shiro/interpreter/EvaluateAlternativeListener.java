package shiro.interpreter;

import java.util.HashMap;
import java.util.Map;
import shiro.ShiroRuntime;
import shiro.definitions.StateDefinition;

/**
 * Walk the parse tree of an alternative to evaluate it.
 * @author jeffreyguenther
 */
public class EvaluateAlternativeListener extends ShiroBaseListener{
    private Map<String, String> subjunctTable;
    private String graphName = "<empty>";
    private StateDefinition createdState;

    public EvaluateAlternativeListener() {
        subjunctTable = new HashMap<>();
        createdState = null;
    }
    
    public StateDefinition getState(){
        return createdState;
    }

    @Override
    public void exitStatestmt(ShiroParser.StatestmtContext ctx) {
        String stateName  = ctx.stateName().getText();
        
        StateDefinition state = new StateDefinition(graphName, stateName);
        state.setActiveNodes(subjunctTable);
        createdState = state;
    }

    @Override
    public void enterStateGraph(ShiroParser.StateGraphContext ctx) {
        graphName = ctx.IDENT().getText();
    }
    
    @Override
    public void enterActivation(ShiroParser.ActivationContext ctx) {
       String nodeName = ctx.nodeName.getText();
       String activeObject = ctx.activeObject.getText();
       
       subjunctTable.put(nodeName, activeObject);
    }
}
