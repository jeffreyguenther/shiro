/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiro.drawing;

import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPaneBuilder;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePaneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private Stage stage;

    public ProjectAmoebaUI(Stage stage) {
        this.stage = stage;
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
                .content(canvasPane)
                .fitToHeight(true)
                .fitToWidth(true)
                .build();

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

        root.setTop(createMenuBar());
        root.setLeft(createToolBar());
//        root.setRight(createListView());
        root.setCenter(scroll);

        return root;
    }
    
    private Node createListView(){
        ListView<String> listView = new ListView<>();
        listView.setEditable(true);
        listView.setCellFactory(TextFieldListCell.forListView());
        ObservableList<String> list = FXCollections.observableArrayList("Alternative 1", "Alteratnive 2");
        listView.setItems(list);
        
        list.addListener(new ListChangeListener<String>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends String> change) {
                
                System.out.println(list);
            }
        });
        return listView;
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
     * Create a menubar for the app
     * @return 
     */
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);

        Menu file = new Menu("File");
        MenuItem open = new MenuItem("Open");
        open.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters()
                        .add(new FileChooser.ExtensionFilter("Shiro code", "*.sro"));
                fileChooser.setTitle("Open Shiro File");
                File file = fileChooser.showOpenDialog(stage);
                
                // TODO open the selected file
                controller.handleOpen(file);
                // IF project is already open prompt to save
            }
        });
        open.setAccelerator(KeyCombination.keyCombination("Shortcut+O"));
        MenuItem save = new MenuItem("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save drawing");
                File file = fileChooser.showSaveDialog(stage);
                controller.handleSave(file);
            }
        });
        save.setAccelerator(KeyCombination.keyCombination("Shortcut+S"));
        MenuItem export = new MenuItem("Export as png");
        export.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                System.out.println("export image button pressed");
                controller.handleExportImage(t);
            }
        });
        export.setAccelerator(KeyCombination.keyCombination("Shortcut+E"));
        file.getItems().addAll(open, save, export);
        
        Menu alts = new Menu("Alternatives");
        MenuItem clearAlts = new MenuItem("Clear All");
        clearAlts.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                drawGroup.getChildren().clear();
                imageStrip.getChildren().clear();
                controller.clearAlternatives();
            }
        });
        
        MenuItem clearCanvas = new MenuItem("Clear Canvas");
        clearCanvas.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                drawGroup.getChildren().clear();
                imageStrip.getChildren().clear();
                controller.clearAlternatives();
            }
        });
        alts.getItems().addAll(clearCanvas, clearAlts);

        menuBar.getMenus().addAll(file, alts);
        return menuBar;
    }

    /**
     * Create tool bar
     *
     * @return Toolbar containing menu
     */
    private ToolBar createToolBar() {
        Button move = createButton(createMoveIcon());
        move.setTooltip(new Tooltip("(m) move a point"));
        move.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                controller.setMoveMode();
            }
        });

        Separator s = new Separator(Orientation.HORIZONTAL);
        Button point = createButton(createPointIcon());
        point.setTooltip(new Tooltip("(p) create a point"));
        point.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                System.out.println("Point button pressed");
                controller.setPointMode();
            }
        });

        Button line = createButton(createLineIcon());
        line.setTooltip(new Tooltip("(l) connect two points with a line"));
        line.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                System.out.println("Line button pressed");
                controller.setLineMode();
            }
        });

        ToolBar tb = new ToolBar(move, s, point, line);
        tb.setOrientation(Orientation.VERTICAL);
        return tb;
    }

    /**
     * Create a point. The point is created with a hidden circle in the
     * background to increase the clickable area.
     *
     * @param x position of the point
     * @param y position of the point
     * @param color line color of the point
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
            }
        ;
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
     * @param g the point to attach the context menu to
     */
    private void createSubjunctContextMenu(Group g) {
        ContextMenu cm = new ContextMenu();
        MenuItem it = new MenuItem("Show subjunts");
        cm.getItems().add(it);
        cm.show(g, Side.RIGHT, 0, 0);
    }
    
    private Shape createLineIcon(){
        Line l = new Line();
        l.setStartX(5);
        l.setStartY(27);
        l.setEndX(27);
        l.setEndY(5);
        
        l.setStroke(Color.BLACK);
        l.setStrokeWidth(3);
        return l;
    }
    
    private Group createMoveIcon(){
        Line horizontal = new Line(16, 5, 16, 27);
        horizontal.setStroke(Color.BLACK);
        horizontal.setStrokeWidth(3);
        
        Polyline ahl = new Polyline(7, 12, 5, 16, 7, 20);
        ahl.setStroke(Color.BLACK);
        ahl.setStrokeWidth(3);
        
        Polyline ahr = new Polyline(24, 12, 27, 16, 24, 20);
        ahr.setStroke(Color.BLACK);
        ahr.setStrokeWidth(3);
        
        Polyline avt = new Polyline(12, 7, 16, 5, 20, 7);
        avt.setStroke(Color.BLACK);
        avt.setStrokeWidth(3);
        
        Polyline ahb = new Polyline(12, 24, 16, 27, 20, 24);
        ahb.setStroke(Color.BLACK);
        ahb.setStrokeWidth(3);
        
        Line vertical = new Line(5, 16, 27, 16);
        vertical.setStroke(Color.BLACK);
        vertical.setStrokeWidth(3);
        
        return new Group(ahl, ahr, avt, ahb, horizontal, vertical);
    }
    
    private Node createPointIcon(){
        Line horizontal = new Line(16, 5, 16, 27);
        horizontal.setStroke(Color.BLACK);
        horizontal.setStrokeWidth(3);
        
        Line vertical = new Line(5, 16, 27, 16);
        vertical.setStroke(Color.BLACK);
        vertical.setStrokeWidth(3);
        
        return new Group(horizontal, vertical);
    }
    
    private Shape createRectIcon(){
        Rectangle r = new Rectangle();
        r.setX(5);
        r.setY(5);
        r.setWidth(22);
        r.setHeight(22);
        r.setStroke(Color.BLACK);
        r.setStrokeWidth(3);
        r.setFill(Color.TRANSPARENT);
        
        return r;
    }
    
    private Shape createCircleIcon(){
        Circle c = new Circle();
        c.setCenterX(16);
        c.setCenterY(16);
        c.setRadius(11);
        c.setStroke(Color.BLACK);
        c.setStrokeWidth(3);
        c.setFill(Color.TRANSPARENT);
        
        return c;
    }
    
    private Shape createTextIcon(){
        Text t = new Text();
        t.setText("T");
        t.setFont(Font.font("Arial", 28));
        t.setFill(Color.BLACK);
        
        return t;
    }
    
    private Button createButton(Node n){
        Button b = new Button();
        b.setGraphic(n);
        b.setMinSize(32, 32);
        b.setPrefSize(32, 32);
        return b;
    }
}
