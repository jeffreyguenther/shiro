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

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import org.reactfx.util.Try;
import org.shirolang.functions.geometry.*;
import org.shirolang.interpreter.ShiroLexer;
import org.shirolang.interpreter.ShiroRuntime;
import org.shirolang.playground.editors.*;

import java.io.*;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
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
    private Map<String, Canvas> layers;
    private File currentFile;
    private Map<String, WritableImage> snapshots;
    private Group lightTable;
    private MoveContext moveContext;
    private ImageView selectedTile;
    private ExecutorService executor;
    private Boolean needsSave;


    public void initialize() {
        model = new ShiroRuntime();
        layers = new HashMap<>();
        addEventHandlersToList();
        altsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        currentFile = null;
        snapshots = new HashMap<>();
        lightTable = new Group();
        moveContext = new MoveContext();
        selectedTile = null;
        console.textProperty().bind(model.outputProperty());

        // setup the code area
        executor = Executors.newSingleThreadExecutor();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.textProperty().addListener((observable, oldValue, newValue) -> {
            needsSave = true;
        });

        EventStream<PlainTextChange> textChanges = codeArea.plainTextChanges();

        textChanges.successionEnds(Duration.ofMillis(500)).subscribe(plainTextChange -> save());

        textChanges.successionEnds(Duration.ofMillis(500))
            .supplyTask(this::computeHighlightingAsync)
            .awaitLatest(textChanges)
            .subscribe(this::applyHighlighting);

        errorLabel.visibleProperty().bind(model.hasErrorProperty());

        model.mapCallBack("SRectangle", r -> new RectangleViz((SRectangle) r));
        model.mapCallBack("SEllipse", e -> new EllipseViz((SEllipse) e));
        model.mapCallBack("SArc", a -> new ArcViz((SArc) a));
        model.mapCallBack("SText", t -> new TextViz((SText) t));
        model.mapCallBack("SLine", l -> new LineViz((SLine) l));
        model.mapCallBack("SGroup", g -> new GroupViz((SGroup) g));

        errorLabel.visibleProperty().bind(model.hasErrorProperty());
        model.hasErrorProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                codeArea.setStyle("-fx-border-color:red;");
            } else {
                codeArea.setStyle("-fx-border-color:null;");
            }
        });

        tileSizes.valueProperty().addListener( (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if(btnGrid.isSelected()){
                gallery.getChildren().clear();

                for(WritableImage img: snapshots.values()){
                    gallery.getChildren().add(createImageTile(img, newValue.doubleValue()));
                }
            }else{
                lightTable = createLightTable(tileSizes.getValue());
                lightTableScrollPane.setContent(lightTable);
            }
        });

    }

    @FXML
    private void handleRun(ActionEvent event) {
        run();
    }

    public void stop(){
        executor.shutdown();
    }

    public void run() {
        altsList.getItems().clear();
        gallery.getChildren().clear();
        lightTable.getChildren().clear();
        snapshots.clear();
        layers.clear();

        if (currentFile == null) {
            FileChooser save = new FileChooser();
            save.setInitialFileName("my_program.sro");
            save.setTitle("Save as .sro");
            currentFile = save.showSaveDialog(root.getScene().getWindow());
            save();
        }

        if(needsSave){
            save();
        }

        // once the file is saved....
        if (currentFile != null) {

            try {
                model.executeFile(Paths.get(currentFile.getPath()));

                model.nodes.entrySet().forEach(e -> {
                    String stateName = e.getKey();
                    Set<Node> nodes = e.getValue();

                    altsList.getItems().add(stateName);

                    Canvas c = createCanvas();
                    c.getDrawing().getChildren().addAll(nodes);
                    Scene s = new Scene(c);

                    layers.put(stateName, c);

                    WritableImage snapshot = c.snapshot(null, null);
                    snapshots.put(stateName, snapshot);
                    gallery.getChildren().add(createImageTile(snapshot, tileSizes.getValue()));

                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        altsList.getSelectionModel().select(0); // select the first alternative in the list
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

        g.setOnMousePressed(this::handleTilePressed);
        g.setOnMouseDragged(this::handleTileDragged);
        g.setOnMouseReleased(this::handleTileReleased);

        double x = 0;
        for (WritableImage img : snapshots.values()) {
            ImageView tile = createImageTile(img, size);
            tile.relocate(x, 0);
            tile.setOnMousePressed(this::handleTileSelected);
            g.getChildren().add(tile);

            x = x + size + 10;
        }

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
                needsSave = false;
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
                String line;
                String newLine = "\n";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    sb.append(newLine);
                }
                codeArea.replaceText(sb.toString());
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
        lightTableScrollPane.setContent(gallery);

        gallery.getChildren().clear();
        for (WritableImage img : snapshots.values()) {
            gallery.getChildren().add(createImageTile(img, tileSizes.getValue()));
        }
    }

    @FXML
    private void handleFreeFormLayout(ActionEvent event) {
        if (lightTable.getChildren().isEmpty()) {
            lightTable = createLightTable(tileSizes.getValue());
        }
        lightTableScrollPane.setContent(lightTable);
    }

    private void addEventHandlersToList() {
        altsList.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends String> c) -> {
            if (c.getList().size() > 1) {
                handleMultipleSelectedAltChange(c.getList());
            } else if (c.getList().size() == 1) {
                handleSingleSelectedAltChange(c.getList().get(0));
            }
        });
    }

    public void handleSingleSelectedAltChange(String altName) {
        Canvas c = layers.get(altName);
        ObservableList<Node> children = canvas.getChildren();
        children.clear();
        children.add(c);
    }

    public void handleMultipleSelectedAltChange(List<? extends String> alts) {
        ObservableList<Node> children = canvas.getChildren();
        children.clear();

        for (String s : alts) {
            Canvas c = layers.get(s);
            children.add(c);
        }
    }

    /**
     * Create a Canvas and setup the event handlers
     *
     * @return Canvas object with event handlers set
     */
    private Canvas createCanvas() {
        Canvas c = new Canvas();
        return c;
    }

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

    private void applyHighlighting(Try<StyleSpans<Collection<String>>> highlighting) {
        codeArea.setStyleSpans(0, highlighting.get());
    }
}
