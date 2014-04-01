/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import java.util.Arrays;
import java.util.List;

/**
 * This object provides an abstract representation of the source code found within
 * the shiro runtime. It can be 
 * @author jeffreyguenther
 */
public class SourceCode {
    private List<Definition> source;
    
    public SourceCode(Definition... defs){
        this.source = Arrays.asList(defs);
    }

    public List<Definition> getSource() {
        return source;
    }
    
    public String toCode(){
        StringBuilder sb = new StringBuilder();
        for(Definition d: source){
            sb.append(d.toCode());
        }
        return sb.toString();
    }
}