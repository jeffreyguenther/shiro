/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.viewer;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author jeffreyguenther
 */
public class Viewer extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        URL resource = getClass().getResource("fxml_viewer.fxml");
        FXMLLoader loader = new FXMLLoader();
        BorderPane root = (BorderPane) loader.load(resource);
        final FXMLViewerController c = (FXMLViewerController) loader.getController();
        Scene s = new Scene(root);
        
        final KeyCombination key = new KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_DOWN);
        s.addEventFilter(KeyEvent.KEY_RELEASED, (event -> {
            if(key.match(event)){
                c.run();
                System.out.println("Test");
            }
        }));
        
        stage.setScene(s);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setTitle("Shiro Viewer");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
