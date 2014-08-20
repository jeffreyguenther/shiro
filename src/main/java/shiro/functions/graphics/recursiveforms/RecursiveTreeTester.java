package shiro.functions.graphics.recursiveforms;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX application to test recursive form renderer
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
