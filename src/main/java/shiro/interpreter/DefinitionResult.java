/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.interpreter;

import java.util.Map;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Encapsulates the definitions of a file.
 * @author jeffreyguenther
 */
public class DefinitionResult {
    private final Map<String, ParseTree> nodeDefinitions;
    private final Map<String, ParseTree> graphDefinitions;
    private final Map<String, ParseTree> alternativeDefinitions;

    public DefinitionResult(Map<String, ParseTree> nodeDefinitions, Map<String, ParseTree> graphDefinitions, Map<String, ParseTree> alternativeDefinitions) {
        this.nodeDefinitions = nodeDefinitions;
        this.graphDefinitions = graphDefinitions;
        this.alternativeDefinitions = alternativeDefinitions;
    }

    /**
     * Gets the node definitions
     * @return a map of the node definitions. The definitions are stored by name
     * to parse tree mapping.
     */
    public Map<String, ParseTree> getNodeDefinitions() {
        return nodeDefinitions;
    }

    /**
     * Gets the graph definitions
     * @return a map of the graph definitions. The definitions are stored by name
     * to parse tree mapping.
     */
    public Map<String, ParseTree> getGraphDefinitions() {
        return graphDefinitions;
    }

    /**
     * Gets the alternative definitions
     * @return a map of the alternative definitions. The definitions are stored
     * by name to parse tree mapping.
     */
    public Map<String, ParseTree> getAlternativeDefinitions() {
        return alternativeDefinitions;
    }
    
}
