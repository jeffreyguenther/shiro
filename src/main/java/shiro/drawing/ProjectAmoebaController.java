package shiro.drawing;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

import javax.imageio.ImageIO;
import shiro.SubjunctiveParametricSystem;

/**
 * This class controls 
 * @author jeffreyguenther
 */
public class ProjectAmoebaController {

	private Mode mode;
	private int imageCounter;
	private boolean panning;
	private boolean lineStarted;
	private Line activeLine;
	private SimpleDoubleProperty lineX;
	private SimpleDoubleProperty lineY;
	private Group tempPoint;
	private boolean splitStarted;
	private MoveContext moveContext;
	private Node selectedObject;

	private final ProjectAmoebaUI ui;
        private SubjunctiveParametricSystem model;

	public SimpleStringProperty selectObjectName;
	private boolean createLineSuccessful;

	public ProjectAmoebaController(ProjectAmoebaUI ui) {
		mode = Mode.Waiting;
		imageCounter = 0;
		panning = false;
		lineStarted = false;
		this.ui = ui;
		this.selectObjectName = new SimpleStringProperty("None");
		moveContext = new MoveContext();
		selectedObject = null;
		createLineSuccessful = false;
		
		// create subjunctive parametric system
                model = new SubjunctiveParametricSystem();
                
		// load point and line definitions
                model.loadDefinitions();
	}

	void setWaitingMode() {
		mode = Mode.Waiting;
	}

	public void setLineMode() {
		mode = Mode.Line;
		System.out.println("Set mode to LINE");
	}

	public void setMoveMode() {
		mode = Mode.Move;
		System.out.println("Set mode to MOVE");
	}

	public void setPointMode() {
		mode = Mode.Point;
		System.out.println("Set mode to POINT");
	}

	public Mode getMode() {
		return mode;
	}

	public boolean isLineStarted() {
		return lineStarted;
	}

	public void setLineStarted(boolean lineStarted) {
		this.lineStarted = lineStarted;
	}

	public boolean isPanning() {
		return panning;
	}

	public void setPanning(boolean panning) {
		this.panning = panning;
	}

	public void setSplitStarted(boolean split) {
		splitStarted = split;
	}

	public boolean isSplitStarted() {
		return splitStarted;
	}

	public void setSelectedObject(Node n) {
		selectedObject = n;
		System.out.println("Set selected object: " + selectedObject);
	}
	
	public void handlePointMousePressed(double x, double y){
		// if a line hasn't been started
		if (!isLineStarted()) {
			setLineStarted(true);
			createLineSuccessful = false;
			
			// create line object
			activeLine = LineBuilder.create()
					.startX(x)
					.startY(y)
					.strokeWidth(2)
					.build();
			activeLine.setMouseTransparent(true);
			ui.getDrawGroup().getChildren().add(activeLine);
			
			// create a properties to act as dynamic end points as line
			// is dragged
			lineX = new SimpleDoubleProperty(x);
			lineY = new SimpleDoubleProperty(y);
			activeLine.endXProperty().bind(lineX);
			activeLine.endYProperty().bind(lineY);

			System.out.println("Start a line");
		} else {
			completeLine(x, y);
		}
	}
	
	/**
	 * Complete a line
	 * @param x position of the end point of the line
	 * @param y position of the end point of the line
	 */
	public void completeLine(double x, double y){
		// Unbind the end of the line
		activeLine.endXProperty().unbind();
		activeLine.endYProperty().unbind();

		// set the end point to be the selected point
		activeLine.endXProperty().set(x);
		activeLine.endYProperty().set(y);

		// Clear
		setLineStarted(false);
		lineX = null;
		lineY = null;
		createLineSuccessful = true;
		System.out.println("Line completed");
	}
	
	/**
	 * Clean up after creating a line
	 */
	public void clearLine(){
		if (!createLineSuccessful && !lineStarted) {
			ui.getDrawGroup().getChildren().remove(activeLine);
			activeLine = null;
			System.out.println("Line drawing is unsuccessful.");
			createLineSuccessful = false;
		}
	}

	public void handleCanvasPaneMousePressed(MouseEvent e) {
		System.out.println(e.getSource() + " pressed");

		if (!panning) {
			if (mode.equals(Mode.Move) && !splitStarted) {
				// if a drawing element has been selected
				if (selectedObject != null) {
					moveContext.setInitialTranslateX(selectedObject
							.layoutXProperty().get());
					moveContext.setInitialTranslateY(selectedObject
							.layoutYProperty().get());
					moveContext.setMouseAnchorX(e.getX());
					moveContext.setMouseAnchorY(e.getY());
					System.out.println("Completed setup for move");
				}
			}

			if (mode.equals(Mode.Point) && !e.isAltDown()) {
				double x = e.getX();
				double y = e.getY();
				Group p = ui.createPoint(x, y, Color.BLACK);

				ui.getDrawGroup().getChildren().addAll(p);
				
				// create the point in the model
                                // set port x to e.getX() as expression
                                // set port y expression to e.getY()
				

				// create a point node
				System.out.println("Point created at: [" + x + "," + y + "]");

			} else if (mode.equals(Mode.Point) && e.isAltDown() && splitStarted) {
				tempPoint = ui.createPoint(e.getX(), e.getY(), Color.PINK);
				ui.getDrawGroup().getChildren().add(tempPoint);
			}			
		}
	}

	public void handleCanvasPaneMouseDragged(MouseEvent e) {
		// if mode is moving AND selection is not empty
		// update the objects in the selection
		if (mode.equals(Mode.Move) && !splitStarted) {
			if (selectedObject != null) {
				selectedObject.setLayoutX(moveContext.getDragDestX(e.getX()));
				selectedObject.setLayoutY(moveContext.getDragDestY(e.getY()));
				System.out.println("Moving node...");
			}
		}

		if (mode.equals(Mode.Point) && e.isAltDown() && splitStarted) {
			if (tempPoint == null) {
				tempPoint = ui.createPoint(e.getX(), e.getY(), Color.PINK);
				ui.getDrawGroup().getChildren().add(tempPoint);
			} else {
				tempPoint.setLayoutX(e.getX());
				tempPoint.setLayoutY(e.getY());
			}
		}

		if(mode.equals(Mode.Line)){
			if (!lineStarted) {
				lineX.set(e.getX());
				lineY.set(e.getY());
			}
		}
		
		if (panning) {
			((Node) e.getSource()).setCursor(Cursor.CLOSED_HAND);
		}
	}

	public void handleCanvasMouseReleased(MouseEvent e) {
		if (mode.equals(Mode.Move) && !splitStarted) {
			// clear the selected object
			selectedObject = null;
		}

		if (mode.equals(Mode.Point)) {

			if (e.isAltDown() && splitStarted) {
				Line l1 = (Line) tempPoint.getChildren().get(0);
				Line l2 = (Line) tempPoint.getChildren().get(1);
				l1.setStroke(Color.BLUEVIOLET);
				l2.setStroke(Color.BLUEVIOLET);

				setSplitStarted(false);

				// Create subjunct in the model
				// Get the name of the current point
				// create a new subjunctive node with a new name
				// create a new point with location of the mouse released event
				// create a new state with the point as active subjunct
				// update parametric system
				// render updates to screen
			}
		}

		System.out.println("Mouse released on Canvas");
	}

	/**
	 * export Image handler
	 * @param e ActionEvent to handle
	 */
	public void handleExportImage(ActionEvent e) {
		// capture an image of the canvas Pane
		Image i = saveNodeAsImage(ui.getCanvasPane(), "Canvas" + imageCounter);

		HBox wrapper = new HBox();
		wrapper.setStyle(" -fx-border-color: green;  -fx-border-width: 1px");

		ImageView view = new ImageView(i);
		view.setFitHeight(50);
		view.setPreserveRatio(true);

		wrapper.getChildren().add(view);
		ui.getImageStrip().getChildren().add(wrapper);
		imageCounter++;
	}

	/**
	 * Saves the node as an image
	 * @param n node to turn into an image
	 * @param fileName of image
	 * @return Image of the node saved to disk
	 */
	private Image saveNodeAsImage(Node n, String fileName) {
		WritableImage image = n.snapshot(null, null);
		File outputFile = new File(fileName + ".png");
		try {

			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png",
					outputFile);
			System.out.println(outputFile.getAbsolutePath());

		} catch (IOException ex) {
			Logger.getLogger(ProjectAmoeba.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return image;
	}
}
