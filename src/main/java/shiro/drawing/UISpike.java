/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.drawing;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.stage.Stage;

/**
 *
 * @author jeffreyguenther
 */
public class UISpike extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        final Group root = new Group();
        final Line l = LineBuilder.create()
        		.startX(0)
        		.startY(0)
        		.endX(200)
        		.endY(200)
        		.strokeWidth(2)
        		.onMouseClicked(new EventHandler<MouseEvent>() {
        			public void handle(MouseEvent e) {
        				System.out.println("Line was clicked");
        			};
				})
				
        		.build();
        
        root.getChildren().add(l);
       
        
        Scene scene = new Scene(root, 400, 400);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				System.out.println("A key was pressed");
				System.out.println(e.getCode());
				if(e.getCode().equals(KeyCode.BACK_SPACE)){
					System.out.println("BS key was pressed");
					root.getChildren().remove(l);
				}
			};
		});
        stage.setTitle("Point Test");
        stage.setScene(scene);
        stage.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public Group drawPoint(double x, double y){
        Line xLine = LineBuilder.create()
                .startX(-10)
                .startY(0)
                .endX(10)
                .endY(0)
                .strokeWidth(2)
                .build();
        
        Line yLine = LineBuilder.create()
                .startX(0)
                .startY(-10)
                .endX(0)
                .endY(10)
                .strokeWidth(2)
                .build();

        Circle c = CircleBuilder.create()
                .centerX(0)
                .centerY(0)
                .fill(Color.BEIGE)
                .radius(10)
                .build();
        
        
        Group g = new Group(c, xLine, yLine);
        g.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                System.out.println("Hidden circle was clicked");
            }
        });
        g.setLayoutX(x);
        g.setLayoutY(y);
        
        return g;
    }
    
}
