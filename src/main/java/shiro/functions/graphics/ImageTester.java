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
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import shiro.ResultTuple;
import shiro.Value;

/**
 *
 * @author jeffreyguenther
 */
@SuppressWarnings("unchecked")
class ImageTester extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Table<Integer, String, Object> table = HashBasedTable.create();
        table.put(0, "A", "A0 Content");
        table.put(0, "B", "B0 Content");
        table.put(1, "A", "A1 Content");
        table.put(1, "B", "B1 Content");
        
        List<Value> args = new ArrayList<>();
        args.add(new Value(table, Table.class));
        
        TableViewMFunc mf = new TableViewMFunc();
        ResultTuple result = mf.evaluate(args);
        
        Value valueForIndex = result.getValueForIndex(0);
        TableView<Map<String, Object>> viewer = (TableView<Map<String, Object>>) valueForIndex.getValue();
        
        stage.setScene(new Scene(new Group(viewer)));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
