/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author krisli
 */
public class RootController {

    private Main mainApp;
    @FXML
    private TabPane ProjectTab;
    
    public void setMainApp(main.Main mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    private void handleNew() {
        ProjectTab.getTabs().add(new Tab("New"));
        mainApp.setProjectTab(ProjectTab);
        Content temp = new Content();
        temp.setEmpty(true);
        mainApp.initContentLayout(temp);
        mainApp.incrementTabIndex();
    }

        @FXML
        private void handleOpen() {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                    "XML files (*.xml)", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);

            // Show save File Dialog
            File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

            if (file != null) {
                ProjectTab.getTabs().add(new Tab(file.getName()));
                mainApp.setProjectTab(ProjectTab);
                mainApp.loadActivityDataFromFile(file);
            }
        }

    @FXML
    private void handleSave() {
        File activityFile = mainApp.getActivityFilePath();
        if (activityFile != null) {
            mainApp.saveActivityDataToFile(activityFile);
        } else {
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveActivityDataToFile(file);
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

}