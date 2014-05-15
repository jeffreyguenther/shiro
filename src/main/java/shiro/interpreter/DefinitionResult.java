/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.interpreter;

import java.util.Map;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author jeffreyguenther
 */
public class DefinitionResult {
    private Map<String, ParseTree> nodeDefinitions;
    private Map<String, ParseTree> graphDefinitions;
    private Map<String, ParseTree> alternativeDefinitions;

    public DefinitionResult(Map<String, ParseTree> nodeDefinitions, Map<String, ParseTree> graphDefinitions, Map<String, ParseTree> alternativeDefinitions) {
        this.nodeDefinitions = nodeDefinitions;
        this.graphDefinitions = graphDefinitions;
        this.alternativeDefinitions = alternativeDefinitions;
    }

    public Map<String, ParseTree> getNodeDefinitions() {
        return nodeDefinitions;
    }

    public Map<String, ParseTree> getGraphDefinitions() {
        return graphDefinitions;
    }

    public Map<String, ParseTree> getAlternativeDefinitions() {
        return alternativeDefinitions;
    }
    
}
