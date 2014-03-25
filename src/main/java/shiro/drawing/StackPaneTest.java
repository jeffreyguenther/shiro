/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.drawing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author jeffreyguenther
 */
public class StackPaneTest extends Application{

    @Override
    public void start(Stage stage) throws Exception {
//        StackPane root = new StackPane();
//        Group root = new Group();
        Pane root = new Pane();
        root.setOnMousePressed((MouseEvent event) -> {
            System.out.println("Group mouse pressed");
        });
       
        Rectangle r = new Rectangle(100, 50);
        r.setFill(Color.GREEN);
        r.setOpacity(0.5);
        r.setOnMousePressed((MouseEvent event) -> {
            System.out.println("Green Rectangle mouse pressed");
        });
        
        Rectangle r2 = new Rectangle(75, 35, 50, 50);
        r2.setFill(Color.RED);
        r2.setOpacity(0.5);
        r2.setOnMousePressed((MouseEvent event) -> {
            System.out.println("Red Rectangle mouse pressed");
        });
        
        root.getChildren().addAll(r2, r); // red green -> green on top
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

