/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import java.awt.Dimension;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 *
 * @author jeffreyguenther
 */
public class GraphViewMFunc implements MultiFunction{
    private static final String NAME = "GraphView";
    private static final int GRAPH = 0;
    private static final int LAYOUT = 1; 
    private static final int ORIGIN = 2;
    private static final int WIDTH = 3;
    private static final int HEIGHT = 4;

    @SuppressWarnings("unchecked")
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value graphValue = arguments.get(GRAPH);
        Value layoutValue = arguments.get(LAYOUT);
        Value originValue = arguments.get(ORIGIN);
        Value heightValue = arguments.get(HEIGHT);
        Value widthValue = arguments.get(WIDTH);
        
        Graph<Object, Object> graph = (Graph<Object, Object>) graphValue.getValue();
//        System.out.println(graph.getEdgeCount(EdgeType.UNDIRECTED) + " " + graph.getVertexCount());
        Layout<Object, Object> layout = createLayout(graph, layoutValue.getValueAsString());
        
        Point2D point = (Point2D) originValue.getValue();
        Integer height = heightValue.getValueAsInt();
        Integer width = widthValue.getValueAsInt();
        
        layout.setSize(new Dimension(width, height));
        
        GraphViz<Object, Object> viewer = new GraphViz<>(layout);
        Group g = new Group(viewer);
        g.relocate(point.getX(), point.getY());
        
        ResultTuple result = new ResultTuple(0, new Value(g, Group.class));
        return result;
    }
    
    private Layout<Object, Object> createLayout(Graph<Object, Object> graph, String layout){
        switch (layout){
            case "Circle":
                return new CircleLayout<>(graph);
            case "FR":
                FRLayout2<Object, Object> l = new FRLayout2<>(graph);
                l.setMaxIterations(100);
                return l;
            case "KK":
                return new KKLayout<>(graph);
        }
        
        return null;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
    
}
