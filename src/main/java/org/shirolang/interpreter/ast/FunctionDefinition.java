package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.*;

/**
 * Defines a function in the Shiro
 */
public class FunctionDefinition implements Codeable{
    public enum ArgumentsType{
        LIST, KEYWORDS
    }

    private String type;
    private String name;
    private String option;
    private ArgumentsType argsType;
    private Map<String, String> argMap;
    private List<String> argList;

    public FunctionDefinition(String type, String name, String option) {
        this.type = type;
        this.name = name;
        this.option = option;
        argsType = ArgumentsType.LIST;
        argList = new ArrayList<>();
        argMap = new TreeMap<>();
    }

    public FunctionDefinition(String type, String name, String option, List<String> argList){
        this(type, name, option);
        this.argList = argList;
    }

    public FunctionDefinition(String type, String name, String option, Map<String, String> argMap){
        this(type, name, option);
        this.argMap = argMap;
        argsType = ArgumentsType.KEYWORDS;
    }

    public FunctionDefinition(String type, String name) {
        this(type, name, "");
    }

    public FunctionDefinition(String type, String name, Map<String, String> argMap){
        this(type, name);
        this.argMap = argMap;
        argsType = ArgumentsType.KEYWORDS;
    }

    public FunctionDefinition(String type, String name, List<String> argList){
        this(type, name);
        this.argList = argList;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean hasActiveOption() {
        return !option.isEmpty();
    }

    public String getOption() {
        return option;
    }

    public ArgumentsType getArgsType() {
        return argsType;
    }

    public boolean hasKeywordArgs(){
        return argsType.equals(ArgumentsType.KEYWORDS);
    }

    public boolean hasListArgs(){
        return argsType.equals(ArgumentsType.LIST);
    }

    public boolean hasArgsDefined() {
        return !argList.isEmpty() || !argMap.isEmpty();
    }

    public List<String> getArgList(){
        return argList;
    }

    public Map<String, String> getArgMap() {
        return argMap;
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        ST code = templates.getInstanceOf("funcDef");
        code.add("f", this);
        return code.render();
    }
}
