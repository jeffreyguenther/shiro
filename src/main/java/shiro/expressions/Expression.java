package shiro.expressions;

import java.util.Arrays;
import java.util.Set;
import shiro.exceptions.PathNotFoundException;
import shiro.Port;
import shiro.exceptions.PortNotActiveException;
import shiro.Value;
import shiro.definitions.Definition;

/**
 * Expression evaluator
 * @author jeffreyguenther
 */
public interface Expression extends Definition{
    public Value evaluate() throws PortNotActiveException;
    public Set<Port> getPortsDependedOn()throws PathNotFoundException;
    
    static Expression number(double d){
        return new Number(d);
    }
    
    static Expression string(String s){
        return new SString(s);
    }
    
    static Expression path(String p, int i){
        Path path = new Path(null,Arrays.asList(p), i);
        return path;
    }
}
