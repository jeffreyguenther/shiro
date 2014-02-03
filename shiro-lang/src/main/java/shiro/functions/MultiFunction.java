package shiro.functions;

import shiro.ResultTuple;
import shiro.Value;
import java.util.List;

/**
 * Describes a multi-function used to evaluate a port.
 * This abstracts out the algorithms used in the dependency graph.
 * There will exist only one instance of each used algorithm in the system.
 * @author jeffreyguenther
 */
public interface MultiFunction {
    public String getName();
    public ResultTuple evaluate(List<Value> arguments);
}
