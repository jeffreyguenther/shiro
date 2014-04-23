/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.drawing;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/**
 *
 * @author jeffreyguenther
 */
public class ListTest extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        ObservableList<String> data = FXCollections.observableArrayList();
        data.add("heelo");
        data.add("yikes");
        
        ListView<String> listView = new ListView<>(data);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.getSelectionModel()
                .getSelectedItems().addListener((ListChangeListener.Change<? extends String> c) -> {
                    System.out.println("Selection Changed");
        });
        
        Arc a = new Arc();
        a.setRadiusX(100);
        a.setRadiusY(100);
        a.setCenterX(100);
        a.setCenterY(100);
        a.setStartAngle(0);
        a.setLength(100);
        a.setFill(Color.TRANSPARENT);
        a.setType(ArcType.OPEN);
        a.setStroke(Color.BLACK);
        a.setStrokeWidth(3);
        
        
        VBox vBox = new VBox(listView);
        
        stage.setScene(new Scene(new Group(a)));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

