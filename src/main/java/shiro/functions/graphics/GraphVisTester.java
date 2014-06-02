/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics;

import shiro.functions.graphics.GraphViz;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.TestGraphs;
import java.awt.Dimension;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jeffreyguenther
 */
public class GraphVisTester extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Graph<String, Number> demoGraph = TestGraphs.getOneComponentGraph();
        Layout<String, Number> layout = new CircleLayout<>(demoGraph);
        layout.setSize(new Dimension(800, 800));
        GraphViz<String, Number> viewer = new GraphViz<>(layout);
        viewer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        stage.setScene(new Scene(viewer,800, 800));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
