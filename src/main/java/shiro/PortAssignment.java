package shiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import shiro.expressions.Expression;
import shiro.expressions.Path;

/**
 * Defines a port assignment used in a graph definition
 * @author jeffreyguenther
 */
public class PortAssignment {
    private Path path; //path of the port to update
    private Map<Integer, Expression> args; // expression and arg position

    public PortAssignment(Path path, Expression exp, int position) {
        this.path = path;
        args = new HashMap<>();
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
    
    public Expression addArgument(int pos, Expression exp){
        return args.put(pos, exp);
    }
    
    public Expression removeArgument(int i){
        return args.remove(i);
    }

    public Map<Integer, Expression> getArgs() {
        return args;
    }

    public void setArgs(Map<Integer, Expression> args) {
        this.args = args;
    }
    
    public String toCode(){
        StringBuilder sb = new StringBuilder();
        sb.append(path.toCode());
        ArrayList<Integer> keys = new ArrayList<>(args.keySet());
        Collections.sort(keys);
        
        sb.append("(");
        // write out the args as code
        for(Integer i: keys){
            sb.append(args.get(i).toCode()).append(",");
        }
        sb.deleteCharAt(sb.length() -1 ).append((")"));
        return sb.toString();
    }
}
