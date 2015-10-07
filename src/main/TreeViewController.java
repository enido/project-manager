/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import main.model.Activity;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author krisli
 */
public class ActivityPaneController implements Initializable {

    public TreeItem<String> rootItem = new TreeItem<String>("Activities");
    private Main mainApp;
    private ObservableList<Activity> data;

    private TreeView<String> tree;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void startTreeView() {
        rootItem.setExpanded(true);

        for (int i = 0; i < data.size(); i++) {
            TreeItem<String> item = new TreeItem<String>(data.get(i).getIdString());
            rootItem.getChildren().add(item);
            System.out.println(data.get(i).getIdString() + ":" + i);
        }


        tree = new TreeView<String>(rootItem);
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setTableData(ObservableList<Activity> activityData) {
        this.data = activityData;
    }

    public TreeView<String> getTree() {
        return tree;
    }
}
