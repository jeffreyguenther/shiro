/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author jeffreyguenther
 */
public class ImageTester extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Image img = new Image("file:" + "/Users/jeffreyguenther/Projects/Thesis/shiro/example_code/PebbleAndBammBamm/pebbles.png");
        ImageView viewer = new ImageView(img);
        
        stage.setScene(new Scene(new Group(viewer)));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
