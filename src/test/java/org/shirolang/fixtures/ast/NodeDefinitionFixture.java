package org.shirolang.fixtures.ast;

import org.shirolang.interpreter.ast.NodeDefinition;

/**
 * Node ASTs to make testing easier
 */
public class NodeDefinitionFixture {
    /**
     * node A begin
     *     input b Box
     *     input b Box
     * end
     */
    public static NodeDefinition withInputs(){
        NodeDefinition nodeDef = new NodeDefinition("A");
        nodeDef.add(PortStatementFixture.input());
        nodeDef.add(PortStatementFixture.input());
        return nodeDef;
    }

    /**
     * node A begin
     *     output b Box
     *     option output b Box
     * end
     */
    public static NodeDefinition withOutputs(){
        NodeDefinition nodeDef = new NodeDefinition("A");
        nodeDef.add(PortStatementFixture.output());
        nodeDef.add(PortStatementFixture.outputOption());
        return nodeDef;
    }

    /**
     * node A begin
     *     input b Box
     *     option input b Box
     *     output b Box
     *     option output b Box
     * end
     */
    public static NodeDefinition withInputsAndOutputs(){
        NodeDefinition nodeDef = new NodeDefinition("A");
        nodeDef.add(PortStatementFixture.input());
        nodeDef.add(PortStatementFixture.inputOption());
        nodeDef.add(PortStatementFixture.output());
        nodeDef.add(PortStatementFixture.outputOption());
        return nodeDef;
    }

    /**
     * node A begin
     *     input b Box
     *     option input b Box
     *     b Box
     *     option b Box
     *     output b Box
     *     option output b Box
     * end
     */
    public static NodeDefinition withInputsOutputsAndInternals(){
        NodeDefinition nodeDef = new NodeDefinition("A");
        nodeDef.add(PortStatementFixture.internal());
        nodeDef.add(PortStatementFixture.internalOption());
        nodeDef.add(PortStatementFixture.input());
        nodeDef.add(PortStatementFixture.inputOption());
        nodeDef.add(PortStatementFixture.output());
        nodeDef.add(PortStatementFixture.outputOption());
        return nodeDef;
    }
}
