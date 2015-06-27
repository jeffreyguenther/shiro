/*
 * Copyright (c) 2012-2014 Jeffrey Guenther
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software  and associated documentation files (the
 * "Software"), to deal in the Software without restriction,  including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute,  sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT  NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
