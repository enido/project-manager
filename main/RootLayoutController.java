/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

/**
 *
 * @author krisli
 */
public class RootLayoutController {
     
    private Main mainApp;
    
    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;
    }
    
    
    @FXML
    private void handleNew(){
        
    }
    
    @FXML
    private void handleOpen(){
        FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
             "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        // Show save File Dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        
        if(file != null){
            mainApp.loadActivityDataFromFile(file);
            mainApp.Refresh();
        }
        
    }
    
    @FXML
    private void handleSave(){
        File activityFile = mainApp.getActivityFilePath();
        if(activityFile != null){
            mainApp.saveActivityDataToFile(activityFile);
        }
        else{
            handleSaveAs();
        }
    }
    
    @FXML
    private void handleSaveAs(){
        FileChooser fileChooser  = new FileChooser();
        
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
