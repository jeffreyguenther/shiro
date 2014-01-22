package shiro.drawing;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * A prototype drawing program that demonstrates the use of subjuncts
 * @author jeffreyguenther
 */
public class ProjectAmoeba extends Application {
	private ProjectAmoebaUI ui;

	@Override
	public void start(Stage stage) {
		// Create the UI
		ui = new ProjectAmoebaUI();
		Scene scene = new Scene(ui.getRoot(), 800, 600);

                // Set up the window
		stage.setMinHeight(600);
		stage.setMinWidth(800);
		stage.setTitle("Project Amoeba");
		stage.setScene(scene);

		stage.show();
		
		// Setup keyboard shortcuts
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case M:
					ui.getController().setMoveMode();
					break;
				case P:
					ui.getController().setPointMode();
					break;
				case L:
					ui.getController().setLineMode();
					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
