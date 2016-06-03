package org.shirolang.fixtures.ast;

import org.omg.PortableServer.POAManagerPackage.State;
import org.shirolang.interpreter.ast.OptionSelection;
import org.shirolang.interpreter.ast.StateDefinition;

/**
 * Constructs states to be used in AST test
 */
public class StateDefinitionFixture {
    /**
     * state s1 begin
     *     graph g1
     *     a[b] begin
     *         b[c] begin
     *             d[e]
     *         end
     *         h[i]
     *     end
     *     f[g]
     * end
     */
    public static StateDefinition nested(){
        OptionSelection innerBinnerA = new OptionSelection("d", "e");
        OptionSelection innerB = new OptionSelection("b", "c", innerBinnerA);
        OptionSelection innerA = new OptionSelection("h", "i");
        OptionSelection selection = new OptionSelection("a", "b", innerB, innerA);

        OptionSelection selection2 = new OptionSelection("f", "g");
        return new StateDefinition("s1", "g1", selection, selection2);
    }

    /**
     * state s1 begin
     *     graph g1
     *     a[b]
     * end
     */
    public static StateDefinition withSingleOption(){
        OptionSelection selection = new OptionSelection("a", "b");
        return new StateDefinition("s1", "g1", selection);
    }
    public static StateDefinition defaultGraph(String name){
        return new StateDefinition(name);
    }

    public static StateDefinition defaultGraphAndState (){
        return new StateDefinition();
    }

    public static StateDefinition graphOnly() {
        return new StateDefinition("s1", "g1");
    }
}
