package org.shirolang.playground.editors;

import javafx.scene.Group;
import org.shirolang.functions.ui.STableView;
import org.shirolang.values.SDouble;

/**
 * Render a TableView
 */
public class TableViewViz extends Group {
    public TableViewViz(STableView tableView) {
        super();

        SDouble originX = (SDouble) tableView.getInput("originX").getResult();
        SDouble originY = (SDouble) tableView.getInput("originY").getResult();
        relocate(originX.getValue(), originY.getValue());
        getChildren().add(tableView.getTableView());
    }
}
