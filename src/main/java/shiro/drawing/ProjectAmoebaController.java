package shiro.drawing;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

import javax.imageio.ImageIO;
import shiro.PathHelpers;
import shiro.PathNotFoundException;
import shiro.Port;
import shiro.SubjunctiveParametricSystem;
import shiro.SystemState;
import shiro.Value;
import shiro.expressions.Expression;
import shiro.expressions.Path;

/**
 * This class controls the underlying parametric system
 *
 * @author jeffreyguenther
 */
public class ProjectAmoebaController {
    private final ProjectAmoebaUI ui;
    private SubjunctiveParametricSystem model;
    
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
    
    private boolean createLineSuccessful;
    private String startPointName;
    private Map<SystemState, Canvas> layers;
    private Canvas currentCanvas;
    private ObservableList<String> alternatives;
    
    private Node selectedObject;

    public ProjectAmoebaController(ProjectAmoebaUI ui) {
        this.ui = ui;
        model = new SubjunctiveParametricSystem();
        
        mode = Mode.Waiting;
        imageCounter = 0;
        panning = false;
        lineStarted = false;
        moveContext = new MoveContext();
        
        createLineSuccessful = false;
        
        layers = new HashMap<>();
        alternatives = FXCollections.observableArrayList();

        selectedObject = null;
        
        // load point and line definitions
        model.loadDefinitions();
        
        // create a Canvas for the default system state
        SystemState defaultState = model.getState("Default");
        currentCanvas = createCanvas();
        layers.put(defaultState, currentCanvas);
        ui.getLayers().getChildren().add(currentCanvas);
    }

    /**
     * Set controller mode to Waiting.
     * When set the controller is waiting for input. When the controller is
     * first created it's mode is Waiting.
     */
    public void setWaitingMode() {
        mode = Mode.Waiting;
    }

    /**
     * Set the controller mode to Line
     * In Line mode the controller handles input to create lines
     */
    public void setLineMode() {
        mode = Mode.Line;
        System.out.println("Set mode to LINE");
    }

    /**
     * Set the controller mode to Move
     * In the Move mode the controller handles input to move points
     */
    public void setMoveMode() {
        mode = Mode.Move;
        System.out.println("Set mode to MOVE");
    }

    /**
     * Set the controller mode to Point
     * In Point mode, the controller handles input to create points
     */
    public void setPointMode() {
        mode = Mode.Point;
        System.out.println("Set mode to POINT");
    }

    /**
     * Get the controller's mode
     * @return the mode of the controller
     */
    public Mode getMode() {
        return mode;
    }
    
    public Canvas getCurrentCanvas(){
        return currentCanvas;
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
    
    public ObservableList<String> getAlternatives(){
        return alternatives;
    }

    public void handlePointMousePressed(double x, double y) {
        // if a line hasn't been started
        if (!isLineStarted()) {
            setLineStarted(true);
            startPointName = (String) selectedObject.getUserData();
            createLineSuccessful = false;

            // create line object
            activeLine = LineBuilder.create()
                    .startX(x)
                    .startY(y)
                    .strokeWidth(2)
                    .build();
            activeLine.setMouseTransparent(true);
            addToCanvas(activeLine);

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
     *
     * @param x position of the end point of the line
     * @param y position of the end point of the line
     */
    public void completeLine(double x, double y) {
        // Unbind the end of the line
        activeLine.endXProperty().unbind();
        activeLine.endYProperty().unbind();

        // set the end point to be the selected point
        activeLine.endXProperty().set(x);
        activeLine.endYProperty().set(y);
        
        // create a line in the model
        shiro.Node node = model.createNode("Line");
        
        // get the nane of the end point
        String endPointName = (String) selectedObject.getUserData();
        shiro.Node p1 = model.getNode(startPointName);
        shiro.Node p2 = model.getNode(endPointName);

        // set the line's start/end port
        Path pathX = PathHelpers.createPathForPort(node, "p1");
        Path pathY = PathHelpers.createPathForPort(node, "p2");
        
        // Parse the expression into an Expression object
        Expression p1Expr = model.parseExpression(p1, startPointName + ".point[0]");
        Expression p2Expr = model.parseExpression(p2, endPointName + ".point[0]");

        try {
            model.setPortExpression(pathX, p1Expr);
            model.setPortExpression(pathY, p2Expr);

        } catch (PathNotFoundException ex) {
            Logger.getLogger(ProjectAmoebaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        model.update();

        // Clear
        setLineStarted(false);
        startPointName = "";
        lineX = null;
        lineY = null;
        createLineSuccessful = true;
        System.out.println("Line completed");
    }

    /**
     * Clean up after creating a line
     */
    public void clearLine() {
        if (!createLineSuccessful && !lineStarted) {
            removeFromCanvas(activeLine);
            activeLine = null;
            System.out.println("Line drawing is unsuccessful.");
            createLineSuccessful = false;
        }
    }
    
    public void clearCanvas(){
        model.clear();
    }
    
    public void clearAlternatives(){
        model.reset();
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
                
                // create the point and add it to the canvas
                Group p = ui.createPoint(x, y, Color.BLACK);
                addToCanvas(p);

                // create the point in the model
                shiro.Node node = model.createNode("Point");

                // store the instance name in the group
                p.setUserData(node.getFullName());

                // update the created node
                updatePointNode(node, x, y);

                System.out.println("Point created at: [" + x + "," + y + "]");

            } else if (mode.equals(Mode.Point) && e.isAltDown() && splitStarted) {
                tempPoint = ui.createPoint(e.getX(), e.getY(), Color.PINK);
                addToCanvas(tempPoint);
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

                // get the node name from the onscreen element
                String nodeName = (String) selectedObject.getUserData();
                shiro.Node node = model.getNode(new Path(nodeName));
                
                // update the node with drag location
                updatePointNode(node, moveContext.getDragDestX(e.getX()), moveContext.getDragDestY(e.getY()));
                
                // clear all the canvases from the layers group
                ObservableList<Node> children = ui.getLayers().getChildren();
                children.clear();
            
                // Get the selected states' names
                for(String s: ui.getAltsList().getSelectionModel().getSelectedItems()){
                    Canvas c = renderState(model.getState(s));
                    children.add(c);
                }
            }
        }

        if (mode.equals(Mode.Point) && e.isAltDown() && splitStarted) {
            if (tempPoint == null) {
                tempPoint = ui.createPoint(e.getX(), e.getY(), Color.PINK);
                addToCanvas(tempPoint);
            } else {
                tempPoint.setLayoutX(e.getX());
                tempPoint.setLayoutY(e.getY());
            }
        }

        if (mode.equals(Mode.Line)) {
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
    
    public void handleSingleSelectedAltChange(String altName){
        SystemState s = model.getState(altName);

        Canvas c = renderState(s);
        ObservableList<Node> children = ui.getLayers().getChildren();
        children.clear();
        children.add(c);
        currentCanvas = c;
    }
    
    public void handleMultipleSelectedAltChange(List<? extends String> alts){
        ObservableList<Node> children = ui.getLayers().getChildren();
        children.clear();
            
        for(String s: alts){
            Canvas c = renderState(model.getState(s));
            children.add(c);
            
            // current canvas is set to the top canvas.
            currentCanvas = c;
        }
    }

    /**
     * export Image handler
     *
     * @param e ActionEvent to handle
     */
    public void handleExportImage(ActionEvent e) {
        // capture an image of the canvas Pane
//        Image i = saveNodeAsImage(ui.getCanvasPane(), "Canvas" + imageCounter);
//
//        HBox wrapper = new HBox();
//        wrapper.setStyle(" -fx-border-color: green;  -fx-border-width: 1px");
//
//        ImageView view = new ImageView(i);
//        view.setFitHeight(50);
//        view.setPreserveRatio(true);
//
//        wrapper.getChildren().add(view);
//        ui.getImageStrip().getChildren().add(wrapper);
//        imageCounter++;
    }

    /**
     * Saves the node as an image
     *
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

    private shiro.Node updatePointNode(shiro.Node node, double x, double y) {
        // set port x to e.getX() as expression
        // create path object for port
        Path pathX = PathHelpers.createPathForPort(node, "x");
        Path pathY = PathHelpers.createPathForPort(node, "y");

        // Parse the expression into an Expression object
        Expression xExpr = model.parseExpression(node, x + "");
        Expression yExpr = model.parseExpression(node, y + "");

        try {
            model.setPortExpression(pathX, xExpr);
            model.setPortExpression(pathY, yExpr);

        } catch (PathNotFoundException ex) {
            Logger.getLogger(ProjectAmoebaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return node;
    }
    
    public void handleSave(File f){
        model.writeCode(f);
    }
    
    /***
     * Open a file and load the code into the application
     * @param f file to be loaded
     */
    public void handleOpen(File f){
        if(f != null){
            try {
                model.loadCode(f);
                
                // Add all the state names to the list of alternatives
                alternatives.addAll(model.getStateNames());
                
                for(SystemState s: model.getStates()){
                    layers.put(s, createCanvas());
                }
                
                // set selected alternative to the first alternative in the list
                // note: select handler is fired!!
                ui.getAltsList().getSelectionModel().select(0);
            } catch (IOException ex) {
                Logger.getLogger(ProjectAmoebaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Add the appropriate geometry to the canvas.
     * @param s
     * @return 
     */
    private Canvas renderState(SystemState s){
        // evaluate the parametric system
        model.update(s);
        
        // look up the state's Canvas
        Canvas c = layers.get(s);
        
        // draw the geometry on the canvas
        updateCanvas(c.getDrawing());
        
        return c;
    }
    
    /**
     * Draw geometry on canvas
     * @param canvas Group to add geometry to
     */
    public void updateCanvas(Group canvas){
        // get geometry from parametric system
        canvas.getChildren().clear();
        
        // for each line in the model
        for(shiro.Node n: model.getNodesOfType("Line")){
            // create a line object and render
            Line l = getLine(n);
            canvas.getChildren().add(l);
        }
        
        // for each point in the model
        for(shiro.Node n: model.getNodesOfType("Point")){
            // if the node active, render it
            if(n.isActive()){
                canvas.getChildren().add(getPoint(n));
            }
        }
    }
    
    public Group getPoint(shiro.Node n){
        Port ePort = n.getSelectedEvaluatedPort();
        Value point = ePort.getValueForIndex(0);
        Point2D tPoint = (Point2D) point.getValue();
        
        Group p = ui.createPoint(tPoint.getX(), tPoint.getY(), Color.BLACK);
        p.setUserData(n.getFullName());
        return p;
    }
    
    /**
     * Create a Line to
     * @param n
     * @return 
     */
    public Line getLine(shiro.Node n){
        Port ePort = n.getSelectedEvaluatedPort();
        Value line = ePort.getValueForIndex(0);

        Line lTemp = (Line) line.getValue();
        
        Line l= new Line();
        l.setStartX(lTemp.getStartX());
        l.setStartY(lTemp.getStartY());
        l.setEndX(lTemp.getEndX());
        l.setEndY(lTemp.getEndY());
        
        return l;
    }
    
    /**
     * Create a Canvas and setup the event handlers
     * @return Canvas object with event handlers set
     */
    private Canvas createCanvas(){
        Canvas c = new Canvas();
        c.setOnMousePressed(this::handleCanvasPaneMousePressed);
        c.setOnMouseDragged(this::handleCanvasPaneMouseDragged);
        c.setOnMouseReleased(this::handleCanvasMouseReleased);
        return c;
    }
    
    private void addToCanvas(Node n){
        currentCanvas.getChildren().add(n);
    }
    
    private void removeFromCanvas(Node n){
        currentCanvas.getChildren().remove(n);
    }
}