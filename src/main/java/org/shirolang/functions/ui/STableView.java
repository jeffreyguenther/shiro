package org.shirolang.functions.ui;

import com.google.common.collect.Table;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.shirolang.base.SFuncBase;
import org.shirolang.base.TypedValue;
import org.shirolang.functions.data.STable;
import org.shirolang.values.SDouble;
import org.shirolang.values.SString;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Draw a table
 */
public class STableView extends SFuncBase {
    private BorderPane bp;

    public STableView() {
        super();

        inputs.setKeyForIndex("table", 0);
        inputs.add(new TypedValue("Table"));

        inputs.setKeyForIndex("name", 1);
        inputs.add(new TypedValue("String"));

        inputs.setKeyForIndex("originX", 2);
        inputs.add(new TypedValue("Double"));

        inputs.setKeyForIndex("originY", 3);
        inputs.add(new TypedValue("Double"));

        results.add(new TypedValue(getType()));
    }

    @Override
    public void evaluate() {
        STable tableFunc = (STable) getInput("table").getResult();

        SString tableName = (SString) getInput("name").getResult();

        SDouble x = (SDouble) getInput("originX").getResult();
        SDouble y = (SDouble) getInput("originY").getResult();

        Table<Integer, String, Comparable> table = tableFunc.getValue();

        Set<String> columnNames = table.columnKeySet();
        Map<Integer, Map<String, Comparable>> rows = table.rowMap();

        ObservableList<Map<String, Comparable>> tableData = FXCollections.observableArrayList();

        // flatten the table rows into a list
        tableData.addAll(rows.keySet().stream().map(rows::get).collect(Collectors.toList()));

        List<TableColumn<Map<String, Comparable>, String>> columns = new ArrayList<>();

        for (String name : columnNames) {
            TableColumn<Map<String, Comparable>, String> col = new TableColumn<>(name);
            col.setCellValueFactory((TableColumn.CellDataFeatures<Map<String, Comparable>, String> param)
                    -> new ReadOnlyObjectWrapper<>(param.getValue().get(name).toString()));
            columns.add(col);
        }

        try {
            URL resource = getClass().getResource("/org/shirolang/functions/graphics/tableView.fxml");
            bp = FXMLLoader.load(resource);

            TableView<Map<String, Comparable>> view = new TableView<Map<String, Comparable>>(tableData);
            view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            view.getColumns().addAll(columns);
            bp.setCenter(view);

            Label l = (Label) bp.getTop();
            l.setText(tableName.getValue());

            bp.relocate(x.getValue(), y.getValue());
        } catch (IOException ex) {
            Logger.getLogger(STableView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getMaxArgs() {
        return 4;
    }

    @Override
    public int getMinArgs() {
        return 4;
    }

    @Override
    public String getType() {
        return "STableView";
    }

    public BorderPane getTableView() {
        return bp;
    }
}
