package org.shirolang.playground;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

/**
 *
 */
public class ShiroPlayground extends Application {
    private FXMLViewerController c;

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getResource("fxml_viewer.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        BorderPane root = loader.load();
        c = loader.getController();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("syntax.css").toExternalForm());
        primaryStage.setTitle("Shiro Playground");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        // stop the thread service
        c.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
