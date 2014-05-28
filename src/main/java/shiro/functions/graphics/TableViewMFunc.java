package shiro.functions.graphics;

import com.google.common.collect.Table;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import shiro.functions.*;
import shiro.ResultTuple;
import shiro.Value;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author jeffreyguenther
 */
public class TableViewMFunc implements MultiFunction {

    private static final String NAME = "TableView";
    private static final int TABLE = 0;
    private static final int TABLE_NAME = 1;
    private static final int ORIGIN = 2;

    public TableViewMFunc() {
    }

    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value originValue = arguments.get(ORIGIN);
        Point2D origin = (Point2D) originValue.getValue();
        
        
        Value nameValue = arguments.get(TABLE_NAME);
        String tableName = nameValue.getValueAsString();
        
        Value tableValue = arguments.get(TABLE);
        Table<Integer, String, Object> table = (Table<Integer, String, Object>) tableValue.getValue();

        Set<String> columnNames = table.columnKeySet();
        Map<Integer, Map<String, Object>> rows = table.rowMap();

        ObservableList<Map<String, Object>> tableData = FXCollections.observableArrayList();

        // flatten the table rows into a list
        for (Integer i : rows.keySet()) {
            tableData.add(rows.get(i));
        }

        List<TableColumn<Map<String, Object>, String>> columns = new ArrayList<>();

        for (String name : columnNames) {
            TableColumn<Map<String, Object>, String> col = new TableColumn<>(name);
            col.setCellValueFactory((TableColumn.CellDataFeatures<Map<String, Object>, String> param)
                    -> new ReadOnlyObjectWrapper<>(param.getValue().get(name).toString()));
            columns.add(col);
        }

        BorderPane bp = null;
        try {
            URL resource = getClass().getResource("/shiro/functions/graphics/tableView.fxml");
            bp = FXMLLoader.load(resource);

            TableView<Map<String, Object>> view = new TableView<>(tableData);
            view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            view.getColumns().addAll(columns);
            bp.setCenter(view);
            
            Label l = (Label) bp.getTop();
            l.setText(tableName);
            
            bp.relocate(origin.getX(), origin.getY());
        } catch (IOException ex) {
            Logger.getLogger(TableViewMFunc.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, new Value(bp, BorderPane.class));
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
