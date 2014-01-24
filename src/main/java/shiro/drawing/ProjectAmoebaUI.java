/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.drawing;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPaneBuilder;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

/**
 * 
 * @author jeffreyguenther
 */
public class ProjectAmoebaUI {

	private final ProjectAmoebaController controller;
	private final Parent uiRoot;
	private Pane canvasPane;
	private Pane imageStrip;
	private Group drawGroup;
	private Button move;

	public ProjectAmoebaUI() {
		controller = new ProjectAmoebaController(this);
		uiRoot = createUI();
	}

	public Parent getRoot() {
		return uiRoot;
	}

	public Group getDrawGroup() {
		return drawGroup;
	}

	public Pane getCanvasPane() {
		return canvasPane;
	}

	public Pane getImageStrip() {
		return imageStrip;
	}

	public ProjectAmoebaController getController() {
		return controller;
	}

	private Parent createUI() {
		final BorderPane root = new BorderPane();

		// Create pane to catch drawing events
		canvasPane = new Pane();
		// canvasPane.setStyle("-fx-background-color: whitesmoke; -fx-border-color: pink; -fx-border-width: 2px;");
		canvasPane.setStyle("-fx-background-color: whitesmoke;");

		// A group to contain the actual drawing elements.
		drawGroup = new Group();
		canvasPane.getChildren().add(drawGroup);
		drawGroup.layoutBoundsProperty().addListener(
				new ChangeListener<Bounds>() {

					@Override
					public void changed(ObservableValue<? extends Bounds> ov,
							Bounds t, Bounds t1) {
						canvasPane.setMinSize(t1.getMaxX(), t1.getMaxY());
					}
				});

		// ScrollPane to allow access to entire canvas
		final ScrollPane scroll = ScrollPaneBuilder.create()
				.content(canvasPane).fitToHeight(true).fitToWidth(true).build();

		scroll.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				if (t.getCode().equals(KeyCode.CONTROL)) {

					scroll.setPannable(true);
					canvasPane.setCursor(Cursor.OPEN_HAND);

					controller.setPanning(true);

					System.out.println(t.getSource());
					System.out.println("CONTROL pressed");
				}
			}
		});

		scroll.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				if (t.getCode().equals(KeyCode.CONTROL)) {
					controller.setPanning(false);
					scroll.setPannable(false);
					System.out.println("CONTROL released");
				}
			}
		});

		canvasPane.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				controller.handleCanvasPaneMousePressed(e);
			}
		});

		canvasPane.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				controller.handleCanvasPaneMouseDragged(e);

			}
		});

		canvasPane.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				controller.handleCanvasMouseReleased(e);
			}
		});

		imageStrip = createImageStrip();

		root.setTop(createToolBar());
		root.setCenter(scroll);
		root.setBottom(imageStrip);

		return root;
	}

	private Node createPropertiesPanel() {
		VBox box = new VBox();

		Label name = new Label("Name");
		TextField nameTextField = new TextField();
		nameTextField.textProperty().bind(controller.selectObjectName);

		HBox hbox = new HBox(5);
		hbox.setPadding(new Insets(5));
		hbox.getChildren().addAll(name, nameTextField);

		box.getChildren().add(hbox);
		return box;
	}

	/**
	 * Create thumbnail strip of alternatives
	 * 
	 * @return Pane to contain thumbnails
	 */
	private Pane createImageStrip() {
		// create film strip
		return TilePaneBuilder.create()
				.style(" -fx-border-color: red;  -fx-border-width: 3px")
				.prefHeight(72).padding(new Insets(5)).hgap(10).vgap(10)
				.orientation(Orientation.HORIZONTAL).build();
	}

	/**
	 * Create tool bar
	 * 
	 * @return Toolbar containing menu
	 */
	private ToolBar createToolBar() {
		move = new Button("Move (m)");
		move.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				controller.setMoveMode();
			}
		});

		Separator s = new Separator(Orientation.VERTICAL);
		Button point = new Button("Point (p)");
		point.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				System.out.println("Point button pressed");
				controller.setPointMode();
			}
		});

		Button line = new Button("Line");
		line.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				System.out.println("Line button pressed");
				controller.setLineMode();
			}
		});

		Separator s2 = new Separator(Orientation.VERTICAL);
		Button exportImage = new Button("Export Image");
		exportImage.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				System.out.println("export image button pressed");
				controller.handleExportImage(t);
			}
		});

		Separator s3 = new Separator(Orientation.VERTICAL);
		Button clear = new Button("Clear Canvas");
		clear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				drawGroup.getChildren().clear();
			}
		});

		Button clearAlts = new Button("Clear Alteratives");
		clearAlts.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				imageStrip.getChildren().clear();
			}
		});

		return new ToolBar(move, s, point, line, s2, exportImage, s3, clear,
				clearAlts);
	}

	// public void setAccelerators(){
	// move.getScene().getAccelerators().put(
	// new KeyCodeCombination(KeyCode.M, KeyCombination.META_DOWN),
	// new Runnable(){
	// @Override
	// public void run() {
	// controller.setMoveMode();
	// }
	// }
	// );
	// }

	/**
	 * Create a point. The point is created with a hidden circle in the
	 * background to increase the clickable area.
	 * 
	 * @param x
	 *            position of the point
	 * @param y
	 *            position of the point
	 * @param color
	 *            line color of the point
	 * @return Group containing the point
	 */
	public Group createPoint(double x, double y, Color color) {
		Line xLine = LineBuilder.create().startX(-10).startY(0).endX(10)
				.endY(0).stroke(color).strokeWidth(2).build();

		Line yLine = LineBuilder.create().startX(0).startY(-10).endX(0)
				.endY(10).stroke(color).strokeWidth(2).build();

		Circle c = CircleBuilder.create().centerX(0).centerY(0)
				.fill(Color.TRANSPARENT).radius(10).build();

		final Group g = new Group(xLine, yLine, c);
		g.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				System.out.println("Point pressed");
				controller.setSelectedObject((Node) e.getSource());

				if (controller.getMode().equals(Mode.Line)) {
					controller.handlePointMousePressed(g.getLayoutX(), g.getLayoutY());
				}

				if (e.isAltDown()) {
					controller.setSplitStarted(true);
				}

				if (e.isSecondaryButtonDown()) {
					System.out.println("Right clicked point");

					createSubjunctContextMenu(g);
					e.consume();
				}
			}
		});

		g.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("Drag detected@" + event.getSceneX() + " " + event.getSceneY());
				g.startFullDrag();
				controller.setLineStarted(false);

			}
		});

		g.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
			@Override
			public void handle(MouseDragEvent event) {
				System.out.println(event.getSource() + ":drag released");

				if (controller.getMode().equals(Mode.Line)) {
					Group g = (Group) event.getSource();
                                        controller.setSelectedObject(g);
					controller.completeLine(g.getLayoutX(), g.getLayoutY());
				}
			};
		});

		g.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				 System.out.println(event.getSource() + ":Point released");
				 controller.clearLine();
			}
		});

		g.setLayoutX(x);
		g.setLayoutY(y);

		return g;
	}

	/**
	 * Create a subjunctive context menu
	 * 
	 * @param g
	 *            the point to attach the context menu to
	 */
	private void createSubjunctContextMenu(Group g) {
		ContextMenu cm = new ContextMenu();
		MenuItem it = new MenuItem("Show subjunts");
		cm.getItems().add(it);
		cm.show(g, Side.RIGHT, 0, 0);
	}
}
