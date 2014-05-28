/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.graphics.TableViewMFunc;

/**
 *
 * @author jeffreyguenther
 */
public class TableViewMFTestViewer extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Table<Integer, String, Object> table = HashBasedTable.create();
        table.put(0, "A", "A0 Content");
        table.put(0, "B", "B0 Content");
        table.put(1, "A", "A1 Content");
        table.put(1, "B", "B1 Content");
        
        List<Value> args = new ArrayList<>();
        args.add(new Value(table, Table.class));
        args.add(new Value("Testing a Big table", String.class));
        args.add(new Value(new Point2D(100, 100), Point2D.class));
        
        TableViewMFunc mf = new TableViewMFunc();
        ResultTuple result = mf.evaluate(args);
        
        Value valueForIndex = result.getValueForIndex(0);
        BorderPane viewer = (BorderPane) valueForIndex.getValue();
        
        stage.setScene(new Scene(new Group(viewer)));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
