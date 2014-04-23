/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.drawing;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author jeffreyguenther
 */
public class ListTest extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Circle c = new Circle();
        c.setRadius(100);
        c.setCenterX(100);
        c.setCenterY(100);
        c.setFill(Color.AQUA);
        c.setStroke(Color.BLACK);
        c.setStrokeWidth(3);
        
        c.setOnMousePressed((e) ->{
            
            switch(e.getClickCount()){
//                case 1:
//                    System.out.println("One click");
//                    break;
                case 2:
                    System.out.println("Two clicks");
                    break;
                case 3:
                    System.out.println("Three clicks");
                    break;
            }
            
        });
        
        stage.setScene(new Scene(new Group(c)));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

