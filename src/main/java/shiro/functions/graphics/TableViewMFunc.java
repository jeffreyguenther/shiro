package shiro.functions.graphics;

import com.google.common.collect.Table;
import java.util.ArrayList;
import shiro.functions.*;
import shiro.ResultTuple;
import shiro.Value;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author jeffreyguenther
 */
public class TableViewMFunc implements MultiFunction {
    private static final String NAME = "TableView";
    private static final int TABLE = 0;
    private static final int TABLE_NAME = 1;
    
    public TableViewMFunc() {
    }
    
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value tableValue = arguments.get(TABLE);
        Table<Integer, String, Object> table = (Table<Integer, String, Object>) tableValue.getValue();
        
        Set<String> columnNames = table.columnKeySet();
        Map<Integer, Map<String, Object>> rows = table.rowMap();
        
        ObservableList<Map<String, Object>> tableData = FXCollections.observableArrayList();
        
        // flatten the table rows into a list
        for(Integer i: rows.keySet()){
            tableData.add(rows.get(i));
        }
        
        List<TableColumn<Map<String, Object>, String>> columns = new ArrayList<>();
        
        for(String name: columnNames){
            TableColumn<Map<String, Object>, String> col = new TableColumn<>(name);
            col.setCellValueFactory((TableColumn.CellDataFeatures<Map<String, Object>, String> param) 
                    -> new ReadOnlyObjectWrapper<>(param.getValue().get(name).toString()));
            columns.add(col);
        }
        
        
        TableView<Map<String, Object>> view = new TableView<>(tableData);
        view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        view.getColumns().addAll(columns);
        
        
        ResultTuple result = new ResultTuple();
        result.setValueForIndex(0, new Value(view, TableView.class));
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
