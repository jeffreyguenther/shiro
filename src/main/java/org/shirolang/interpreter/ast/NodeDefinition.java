package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;

/**
 * Describes a node definition
 */
public class NodeDefinition implements Codeable{
    private String name;
    private String defaultOption;
    private List<PortDefinition> inputs;
    private List<PortDefinition> outputs;
    private List<PortDefinition> internal;
    private List<PortAssignment> assignments;
    private List<NodeDefinition> nodes;

    public NodeDefinition(String name, String defaultOption) {
        this.name = name;
        this.defaultOption = defaultOption;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        internal = new ArrayList<>();
        assignments = new ArrayList<>();
        nodes = new ArrayList<>();
    }

    public NodeDefinition(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public String getDefaultOption() {
        return defaultOption;
    }

    public boolean hasDefaultOption(){
        return defaultOption != null && !defaultOption.isEmpty();
    }

    public void add(PortDefinition def) {
        if (def.isInput()){
            inputs.add(def);
        }else if(def.isInternal()){
            internal.add(def);
        }else{
            outputs.add(def);
        }
    }

    public void add(NodeDefinition nodeDefinition) {
        nodes.add(nodeDefinition);
    }

    public boolean add(PortAssignment portAssignment) {
        return assignments.add(portAssignment);
    }

    public List<PortDefinition> getInputs() {
        return inputs;
    }

    public List<PortDefinition> getOutputs() {
        return outputs;
    }

    public List<PortDefinition> getInternal() {
        return internal;
    }

    public List<PortDefinition> getDeclarations(){
        ArrayList<PortDefinition> decls = new ArrayList<>();
        decls.addAll(getInputs());
        decls.addAll(getInternal());
        decls.addAll(getOutputs());
        return decls;
    }

    public List<PortAssignment> getAssignments() {
        return assignments;
    }

    public List<NodeDefinition> getNodes() {
        return nodes;
    }

    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        templates.registerModelAdaptor(Codeable.class, new CodeableAdaptor());
        ST code = templates.getInstanceOf("nodeDef");
        code.add("n", this);
        return code.render();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeDefinition that = (NodeDefinition) o;

        if (!name.equals(that.name)) return false;
        if (defaultOption != null ? !defaultOption.equals(that.defaultOption) : that.defaultOption != null)
            return false;
        if (!inputs.equals(that.inputs)) return false;
        if (!outputs.equals(that.outputs)) return false;
        if (!internal.equals(that.internal)) return false;
        if (!assignments.equals(that.assignments)) return false;
        return nodes.equals(that.nodes);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (defaultOption != null ? defaultOption.hashCode() : 0);
        result = 31 * result + inputs.hashCode();
        result = 31 * result + outputs.hashCode();
        result = 31 * result + internal.hashCode();
        result = 31 * result + assignments.hashCode();
        result = 31 * result + nodes.hashCode();
        return result;
    }
}
