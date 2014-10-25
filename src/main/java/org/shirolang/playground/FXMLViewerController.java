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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shirolang.playground;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;
import org.fxmisc.richtext.*;
import org.reactfx.EventStream;
import org.shirolang.interpreter.ShiroLexer;
import org.shirolang.interpreter.ShiroRuntime;

import java.io.*;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author jeffreyguenther
 */
public class FXMLViewerController {

    @FXML
    private BorderPane root;
    @FXML
    private Button run;
    @FXML
    private CodeArea codeArea;
    @FXML
    private ListView<String> altsList;
    @FXML
    private Pane canvas;
    @FXML
    private TextArea console;
    @FXML
    private FlowPane gallery;
    @FXML
    private ScrollPane lightTableScrollPane;
    @FXML
    private ToggleGroup layouts;
    @FXML
    private Label errorLabel;
    @FXML
    private Slider tileSizes;
    @FXML
    private ToggleButton btnGrid;
    @FXML
    private ToggleButton btnFreeForm;

    private ShiroRuntime model;
//    private Map<StateDefinition, Canvas> layers;
    private File currentFile;
//    private Map<StateDefinition, WritableImage> snapshots;
    private Group lightTable;
    private MoveContext moveContext;
    private ImageView selectedTile;
    private ExecutorService executor;


    public void initialize() {
        model = new ShiroRuntime();
        console.textProperty().bind(model.outputProperty());

        // setup the code area
        executor = Executors.newSingleThreadExecutor();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        EventStream<PlainTextChange> textChanges = codeArea.plainTextChanges();

        textChanges.successionEnds(Duration.ofMillis(500))
            .supplyTask(this::computeHighlightingAsync)
            .awaitLatest(textChanges)
            .subscribe(this::applyHighlighting);
}

    @FXML
    private void handleRun(ActionEvent event) {
        run();
    }

    public void stop(){
        executor.shutdown();
    }

    public void run() {
       model.executeStatement(codeArea.getText());
       model.evaluate();
    }

    private void appendToConsole(String s) {
        String text = console.getText();
        String concat = text.concat(s);
        console.setText(concat);
    }

    private ImageView createImageTile(WritableImage image, double size) {
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);

        ImageView v = new ImageView(image);
        v.setFitWidth(size);
        v.setPreserveRatio(true);
        v.setSmooth(true);
        v.setEffect(ds);
        return v;
    }

    private Group createLightTable(double size) {
        Group g = new Group();

//        g.setOnMousePressed(this::handleTilePressed);
//        g.setOnMouseDragged(this::handleTileDragged);
//        g.setOnMouseReleased(this::handleTileReleased);
//
//        double x = 0;
//        for (WritableImage img : snapshots.values()) {
//            ImageView tile = createImageTile(img, size);
//            tile.relocate(x, 0);
//            tile.setOnMousePressed(this::handleTileSelected);
//            g.getChildren().add(tile);
//
//            x = x + size + 10;
//        }

        return g;
    }

    private void handleTileSelected(MouseEvent e) {
        selectedTile = (ImageView) e.getSource();
    }

    private void handleTilePressed(MouseEvent e) {
        if (selectedTile != null) {
            ImageView v = selectedTile;
            moveContext.setInitialTranslateX(v.layoutXProperty().get());
            moveContext.setInitialTranslateY(v.layoutYProperty().get());
            moveContext.setMouseAnchorX(e.getX());
            moveContext.setMouseAnchorY(e.getY());
            System.out.println("tile pressed");
        }
    }

    private void handleTileDragged(MouseEvent e) {
        if (selectedTile != null) {
            ImageView v = selectedTile;
            v.setLayoutX(moveContext.getDragDestX(e.getX()));
            v.setLayoutY(moveContext.getDragDestY(e.getY()));
        }
    }

    private void handleTileReleased(MouseEvent e) {
        System.out.println("tile released");
    }

    @FXML
    private void handleSave(ActionEvent event) {
        save();
    }

    private void save() {
        if (currentFile != null) {
            try (FileWriter writer = new FileWriter(currentFile)) {
                writer.write(codeArea.getText());
                writer.flush();
                appendToConsole("Saved\n");
            } catch (IOException ex) {
                Logger.getLogger(FXMLViewerController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void handleFileOpen(ActionEvent event) {
        try {
            FileChooser open = new FileChooser();
            File fileToOpen = open.showOpenDialog(root.getScene().getWindow());

            if (fileToOpen != null) {
                BufferedReader reader = new BufferedReader(new FileReader(fileToOpen));
                StringBuilder sb = new StringBuilder();
                String line = null;
                String newLine = "\n";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    sb.append(newLine);
                }
                codeArea.replaceText(sb.toString().trim());
                currentFile = fileToOpen;

            }
        } catch (IOException ex) {

        }
    }

    @FXML
    private void handleSaveAs(ActionEvent event
    ) {
        FileChooser save = new FileChooser();
        save.setInitialFileName("Code.sro");
        save.setTitle("Save as .sro");
        File fileToSave = save.showSaveDialog(root.getScene().getWindow());

        if (fileToSave != null) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(fileToSave);
                writer.write(codeArea.getText());
                writer.flush();
            } catch (IOException ex) {
                Logger.getLogger(FXMLViewerController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLViewerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    private void layoutAsGrid(ActionEvent event) {
//        lightTableScrollPane.setContent(gallery);
//
//        gallery.getChildren().clear();
//        for (WritableImage img : snapshots.values()) {
//            gallery.getChildren().add(createImageTile(img, tileSizes.getValue()));
//        }
    }

    @FXML
    private void handleFreeFormLayout(ActionEvent event) {
//        if (lightTable.getChildren().isEmpty()) {
//            lightTable = createLightTable(tileSizes.getValue());
//        }
//        lightTableScrollPane.setContent(lightTable);
    }

//    private void addEventHandlersToList() {
//        altsList.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends String> c) -> {
//            if (c.getList().size() > 1) {
//                handleMultipleSelectedAltChange(c.getList());
//            } else if (c.getList().size() == 1) {
//                handleSingleSelectedAltChange(c.getList().get(0));
//            }
//        });
//    }
//
//    public void handleSingleSelectedAltChange(String altName) {
//        StateDefinition s = model.getState(altName);
//
//        Canvas c = layers.get(s);
//        ObservableList<Node> children = canvas.getChildren();
//        children.clear();
//        children.add(c);
//    }
//
//    public void handleMultipleSelectedAltChange(List<? extends String> alts) {
//        ObservableList<Node> children = canvas.getChildren();
//        children.clear();
//
//        for (String s : alts) {
//            Canvas c = renderState(model.getState(s));
//            children.add(c);
//        }
//    }
//
//    /**
//     * Add the appropriate geometry to the canvas.
//     *
//     * @param s
//     * @return
//     */
//    private Canvas renderState(StateDefinition s) {
//        // evaluate the parametric system
//        model.update(s);
//        Graph g = model.getGraph(s.getGraphDef());
//
//        // look up the state's Canvas
//        Canvas c = layers.get(s);
//
//        // draw the geometry on the canvas
//        updateCanvas(g, c.getDrawing());
//
//        return c;
//    }
//
//    /**
//     * Create a Canvas and setup the event handlers
//     *
//     * @return Canvas object with event handlers add
//     */
//    private Canvas createCanvas() {
//        Canvas c = new Canvas();
//        return c;
//    }
//
//    /**
//     * Draw geometry on canvas
//     *
//     * @param canvas Group to add geometry to
//     */
//    public void updateCanvas(Graph graph, Group canvas) {
//        // get geometry from parametric system
//        canvas.getChildren().clear();
//
//        // for each line in the model
//        for (shiro.Node n : model.getNodesOfType(graph, "Line")) {
//            // create a line object and render
//            Line l = getLine(n);
//            canvas.getChildren().add(l);
//        }
//
//        for (shiro.Node n : model.getNodesOfType(graph, "Rectangle")) {
//            Rectangle r = getRect(n);
//            canvas.getChildren().add(r);
//        }
//
//        for (shiro.Node n : model.getNodesOfType(graph, "Circle")) {
//            Circle c = getCircle(n);
//            canvas.getChildren().add(c);
//        }
//
//        for (shiro.Node n : model.getNodesOfType(graph,"Arc")) {
//            Arc ac = getArc(n);
//            canvas.getChildren().add(ac);
//        }
//
//        for (shiro.Node n : model.getNodesOfType(graph, "Group")) {
//            Group g = getGroup(n);
//            canvas.getChildren().add(g);
//        }
//
////        for (shiro.Node n : model.getNodesOfType("Image")) {
////            ImageView image = getImage(n);
////            canvas.getChildren().add(image);
////        }
//
//        for (shiro.Node n : model.getNodesOfType(graph, "Layer")) {
//            Group g = getLayer(n);
//            canvas.getChildren().add(g);
//        }
//
//        for(shiro.Node n: model.getNodesOfType(graph, "TableView")){
//            BorderPane bp = getTableView(n);
//            canvas.getChildren().add(bp);
//        }
//
//        for(shiro.Node n: model.getNodesOfType(graph, "GraphView")){
//           Group graphView = getGraphView(n);
//            canvas.getChildren().add(graphView);
//        }
//
//        for(shiro.Node n: model.getNodesOfType(graph, "RecursiveForm")){
//            Group form = getRecursiveForm(n);
//            canvas.getChildren().add(form);
//        }
        

//        // for each point in the model
//        for (shiro.Node n : model.getNodesOfType("Point")) {
//            // if the node active, render it
//            if (n.isActive()) {
//                canvas.getChildren().add(getPoint(n));
//            }
//        }
//    }

//    public Group getPoint(shiro.Node n) {
//        Port ePort = n.getSelectedEvaluatedPort();
//        Value point = ePort.getValueForIndex(0);
//        Point2D tPoint = (Point2D) point.getValue();
//
//        Group p = ui.createPoint(tPoint.getX(), tPoint.getY(), Color.BLACK);
//        p.setUserData(n.getFullName());
//        return p;
//    }
//    /**
//     * Create a Line to
//     *
//     * @param n
//     * @return
//     */
//    public Line getLine(shiro.Node n) {
//        Port ePort = n.getActiveEvalPort();
//        Value line = ePort.getValueForIndex(0);
//
//        Line lTemp = (Line) line.getValue();
//
//        Line l = new Line();
//        l.setStartX(lTemp.getStartX());
//        l.setStartY(lTemp.getStartY());
//        l.setEndX(lTemp.getEndX());
//        l.setEndY(lTemp.getEndY());
//
//        return l;
//    }
//
//    public Rectangle getRect(shiro.Node n) {
//        Port ePort = n.getActiveEvalPort();
//        Value rect = ePort.getValueForIndex(0);
//
//        Rectangle rTemp = (Rectangle) rect.getValue();
//
//        Rectangle r = new Rectangle();
//        r.setX(rTemp.getX());
//        r.setY(rTemp.getY());
//        r.setWidth(rTemp.getWidth());
//        r.setHeight(rTemp.getHeight());
//        r.setStroke(rTemp.getStroke());
//        r.setStrokeWidth(rTemp.getStrokeWidth());
//        r.setFill(rTemp.getFill());
//
//        return r;
//    }
//
//    public Circle getCircle(shiro.Node n) {
//        Port ePort = n.getActiveEvalPort();
//        Value circle = ePort.getValueForIndex(0);
//
//        Circle cTemp = (Circle) circle.getValue();
//
//        Circle c = new Circle();
//        c.setRadius(cTemp.getRadius());
//        c.setCenterX(cTemp.getCenterX());
//        c.setCenterY(cTemp.getCenterY());
//        c.setStroke(cTemp.getStroke());
//        c.setStrokeWidth(cTemp.getStrokeWidth());
//        c.setFill(cTemp.getFill());
//
//        return c;
//    }
//
//    public Arc getArc(shiro.Node n) {
//        Port ePort = n.getActiveEvalPort();
//        Value arc = ePort.getValueForIndex(0);
//
//        Arc cTemp = (Arc) arc.getValue();
//
//        Arc c = new Arc();
//        c.setRadiusX(cTemp.getRadiusX());
//        c.setRadiusY(cTemp.getRadiusY());
//        c.setCenterX(cTemp.getCenterX());
//        c.setCenterY(cTemp.getCenterY());
//        c.setStartAngle(cTemp.getStartAngle());
//        c.setLength(cTemp.getLength());
//        c.setStroke(cTemp.getStroke());
//        c.setStrokeWidth(cTemp.getStrokeWidth());
//        c.setFill(cTemp.getFill());
//        c.setType(cTemp.getType());
//
//        return c;
//    }
//
//    public Group getGroup(shiro.Node n){
//        Port ePort = n.getActiveEvalPort();
//        Value rawGroup = ePort.getValueForIndex(0);
//
//        Group g = (Group) rawGroup.getValue();
//
//        return g;
//    }
//
//    public ImageView getImage(shiro.Node n){
//        Port ePort = n.getActiveEvalPort();
//        Value rawImage = ePort.getValueForIndex(0);
//
//        return (ImageView) rawImage.getValue();
//    }
//
//    public Group getLayer(shiro.Node n){
//        Port ePort = n.getActiveEvalPort();
//        Value rawGroup = ePort.getValueForIndex(0);
//
//        Group g = (Group) rawGroup.getValue();
//
//        return g;
//    }
//
//    public BorderPane getTableView(shiro.Node n){
//        Port ePort = n.getActiveEvalPort();
//        Value rawTableGroup = ePort.getValueForIndex(0);
//
//        BorderPane g = (BorderPane) rawTableGroup.getValue();
//
//        return g;
//    }
//
//    public Group getGraphView(shiro.Node n){
//        Port ePort = n.getActiveEvalPort();
//        Value rawGraphView = ePort.getValueForIndex(0);
//
//        Group g = (Group) rawGraphView.getValue();
//
//        return g;
//    }
//
//    public Group getRecursiveForm(shiro.Node n){
//        Port ePort = n.getActiveEvalPort();
//        Value rawGraphView = ePort.getValueForIndex(0);
//
//        Group g = (Group) rawGraphView.getValue();
//
//        return g;
//    }

    private Task<StyleSpans<Collection<String>>> computeHighlightingAsync(){
        String code = codeArea.getText();

        Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
            @Override
            protected StyleSpans<Collection<String>> call() throws Exception {
                return computeHighlighting(code);
            }
        };
        executor.execute(task);
        return task;
    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text){
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        int lastEnd = 0;

        ShiroLexer lex = new ShiroLexer(new ANTLRInputStream(text));
        // parse

        for(Token t: lex.getAllTokens()){
            spansBuilder.add(Collections.emptyList(), t.getStartIndex() - lastEnd);
            spansBuilder.add(Collections.singleton(getStyleClass(t)), (t.getStopIndex() + 1 - t.getStartIndex()));
            lastEnd = t.getStopIndex() + 1;
        }

        spansBuilder.add(Collections.emptyList(), text.length() - lastEnd);

        return spansBuilder.create();
    }

    private static String getStyleClass(Token t){
        switch (t.getType()){
            case ShiroLexer.BEGIN:
                return "begin-end";
            case ShiroLexer.END:
                return "begin-end";
            case ShiroLexer.MFNAME:
                return "mf";
            case ShiroLexer.IDENT:
                return "ident";
            default:
                return "";
        }
    }

    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        codeArea.setStyleSpans(0, highlighting);
    }
}
