package org.shirolang.interpreter.ast;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodeDefinitionTest {
    private NodeDefinition nodeDef;

    @Before
    public void createInstance(){
        nodeDef = new NodeDefinition("A");
    }

    @Test
    public void getName(){
        assertEquals("A", nodeDef.getName());
    }

    @Test
    public void toCode(){
        assertEquals("node A begin\nend", nodeDef.toCode());
    }
}