/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jeffreyguenther
 */
public class Comment implements Definition{
    public enum Type{ INLINE, BLOCK }
    
    private Type type;
    private String content;

    public Comment(Type type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Type getType() {
        return type;
    }
    
    @Override
    public String toCode() {
        StringBuilder sb = new StringBuilder();
        if(type.equals(Type.INLINE)){
            sb.append("// ")
              .append(content);
        }else{
            
            List<String> lines = Arrays.asList(content.split("\n"));
            
            sb.append("/*\n");
               lines.stream().forEach((s) -> {
                   sb.append("* ")
                     .append(s)
                     .append("\n");
               });
            sb.append("*/");
        }
        
        return sb.toString();
    }
    
}
