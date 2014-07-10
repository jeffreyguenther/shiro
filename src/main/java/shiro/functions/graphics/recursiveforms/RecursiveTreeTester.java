/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.functions.graphics.recursiveforms;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

/**
 *
 * @author jeffreyguenther
 */
public class RecursiveTreeTester extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group g = new Group();

//        RecursiveFormRenderer r = new RecursiveFormRenderer(new SymmetricalTree(100, 0.75, 60), 6, true);
        RecursiveFormRenderer r = new RecursiveFormRenderer(new SpikeyPattern(), 6);
        g.getChildren().addAll(r.getShapes());
        
        g.relocate(200, 200);

        stage.setScene(new Scene(g, 800, 800));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
