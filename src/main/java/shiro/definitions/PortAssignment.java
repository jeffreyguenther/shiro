package shiro.definitions;

import java.util.ArrayList;
import java.util.List;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import shiro.expressions.Expression;
import shiro.expressions.Path;

/**
 * Defines a port assignment used in a graph definition
 * @author jeffreyguenther
 */
public class PortAssignment implements Definition{
    private Path path; //path of the port to update
    private List<Expression> args; // expression and arg position

    public PortAssignment(Path path, Expression exp) {
        this.path = path;
        args = new ArrayList<>();
        args.add(exp);
    }
    
    public PortAssignment(Path path, List<Expression> exps){
        this.path = path;
        this.args = exps;
    }

    public Path getPath() {
        return path;
    }
    
    public String getPathString(){
        return path.getPath();
    }

    public void setPath(Path path) {
        this.path = path;
    }
    
    public void addArgument(int pos, Expression exp){
        args.set(pos, exp);
        
    }
    
    public Expression removeArgument(int i){
        return args.remove(i);
    }

    public List<Expression> getArgs() {
        return args;
    }

    public void setArgs(List<Expression> args) {
        this.args = args;
    }
    
    @Override
    public String toCode(){
        STGroup group = Definition.getTemplate();
        ST st = group.getInstanceOf("portAssignment");
        st.add("a", this);
        return st.render();
        
//        StringBuilder sb = new StringBuilder();
//        sb.append(path.toCode())
//        .append("(");
//        
//        // write out the args as code
//        ArrayList<Integer> keys = new ArrayList<>(args.keySet());
//        Collections.sort(keys);
//        for(Integer i: keys){
//            sb.append(args.get(i).toCode()).append(",");
//        }
//        
//        if(sb.toString().endsWith(",")){
//            sb.deleteCharAt(sb.length() -1 );
//        }
//        sb.append(")");
//        return sb.toString();
    }
}
