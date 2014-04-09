package shiro.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.path);
        hash = 83 * hash + Objects.hashCode(this.args);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PortAssignment other = (PortAssignment) obj;
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.args, other.args)) {
            return false;
        }
        return true;
    }
}
