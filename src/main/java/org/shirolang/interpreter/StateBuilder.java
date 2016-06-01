package org.shirolang.interpreter;

import org.antlr.v4.runtime.misc.NotNull;
import org.shirolang.base.SState;
import org.shirolang.base.StateActivation;

import java.util.Stack;

/**
 * Build states from the parse tree
 */
public class StateBuilder extends ShiroBaseListener{
    private SState state;
    private Stack<StateActivation> activationStack;

    public StateBuilder() {
        activationStack = new Stack<>();
    }

    public SState getState(){
        return state;
    }

    @Override
    public void enterStateDecl(ShiroParser.StateDeclContext ctx) {
        String stateName = ctx.stateName().getText();
        state = new SState(stateName);
    }

    @Override
    public void enterStateGraphSelection(@NotNull ShiroParser.StateGraphSelectionContext ctx) {
        String graphName = "";
        if (ctx.IDENT() != null){
            graphName = ctx.IDENT().getText();
        }

        state.setGraph(graphName);
    }

    @Override
    public void exitOptionSelection(ShiroParser.OptionSelectionContext ctx) {
        StateActivation activation = new StateActivation(ctx.nodeName.getText(), ctx.activeObject.getText());

        if(activationStack.empty()){
            state.addActivation(activation);
        }else{
            StateActivation parent = activationStack.peek();
            parent.addNestedActivation(activation);
        }
    }

    @Override
    public void enterNestedOptionSelection(ShiroParser.NestedOptionSelectionContext ctx) {
        StateActivation activation = new StateActivation(ctx.nodeName.getText(), ctx.activeObject.getText());
        activationStack.push(activation);

        if(activationStack.empty()){
            state.addActivation(activation);
        }else{
            StateActivation parent = activationStack.peek();
            parent.addNestedActivation(activation);
        }
    }

    @Override
    public void exitNestedOptionSelection(ShiroParser.NestedOptionSelectionContext ctx) {
        activationStack.pop();
    }
}
