/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import java.util.Arrays;
import java.util.List;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Defines an abstract representation of a comment in Shiro.
 * This class is part of the abstract syntax tree of Shiro. It is used to capture
 * a comment from a source file and store it for output later.
 * @author jeffreyguenther
 */
public class Comment implements Definition{
    public enum Type{ INLINE, BLOCK }
    
    private final Type type;
    private final String content;

    public Comment(Type type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    
    public List<String> getLines(){
        return Arrays.asList(content.split("\n"));
    }

    public Type getType() {
        return type;
    }
    
    @Override
    public String toCode() {
        STGroup group = new STGroupFile("shiro/definitions/shiro.stg");
        
        if(type.equals(Type.INLINE)){
            ST st = group.getInstanceOf("inlineComment");
            st.add("c", this);
            return st.render();
        }else{
            ST st = group.getInstanceOf("blockComment");
            st.add("c", this);
            return st.render();
        }
    }
    
}
