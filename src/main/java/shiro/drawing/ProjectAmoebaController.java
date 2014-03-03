package shiro.drawing;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
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
import shiro.PathHelpers;
import shiro.PathNotFoundException;
import shiro.Port;
import shiro.SubjunctiveParametricSystem;
import shiro.Value;
import shiro.expressions.Expression;
import shiro.expressions.Path;

/**
 * This class controls the underlying parametric system
 *
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
    private String startPointName;
    Map<shiro.Node, Line> lines;

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
        lines = new HashMap<>();

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
        lines.put(node, activeLine);
        
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
            ui.getDrawGroup().getChildren().remove(activeLine);
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
                Group p = ui.createPoint(x, y, Color.BLACK);

                ui.getDrawGroup().getChildren().addAll(p);

                // create the point in the model
                shiro.Node node = model.createNode("Point");

                // store the instance name in the group
                p.setUserData(node.getFullName());

                // update the created node
                updatePointNode(node, x, y);

                System.out.println(model.printDependencyGraph());
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

                // get the node name from the onscreen element
                String nodeName = (String) selectedObject.getUserData();
                
                shiro.Node node = model.getNode(nodeName);
                
                updatePointNode(node, moveContext.getDragDestX(e.getX()), moveContext.getDragDestY(e.getY()));
                
                // update the model
                model.update();
                
                // update the view
                //TODO update the lines the might be dependent on the point
                for(shiro.Node n: model.getNodesOfType("Line")){
                    Port ePort = n.getSelectedEvaluatedPort();
                    Value p1 = ePort.getValueForIndex(0);
                    
                    Line lTemp = (Line) p1.getValue();
                    
                    Line l = lines.get(n);
                    l.setStartX(lTemp.getStartX());
                    l.setStartY(lTemp.getStartY());
                    l.setEndX(lTemp.getEndX());
                    l.setEndY(lTemp.getEndY());
                }
                
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
            System.out.println(model.printDependencyGraph());
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
     *
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
    
    public void handleOpen(File f){
        if(f != null){
            try {
                model.loadCode(f);
                // update canvas with new geometry
                updateCanvas(ui.getDrawGroup());
                
            } catch (IOException ex) {
                Logger.getLogger(ProjectAmoebaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Update the canvas
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
            // create a point object and render
            canvas.getChildren().add(getPoint(n));
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

        Line l;
        if(lines.containsKey(n)){
             l = lines.get(n);
        
        }else{
            l = new Line();
            lines.put(n, l);
        }
        
        l.setStartX(lTemp.getStartX());
        l.setStartY(lTemp.getStartY());
        l.setEndX(lTemp.getEndX());
        l.setEndY(lTemp.getEndY());
        
        return l;
    }
}