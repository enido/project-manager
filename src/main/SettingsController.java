/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author krisli
 */
public class SettingsController {

    @FXML
    private ComboBox combo;
    @FXML
    private CheckBox avancimiCB;
    @FXML
    private CheckBox buxhetiCB;
    @FXML
    private CheckBox vfCB;
    @FXML
    private CheckBox vpCB;
    @FXML
    private CheckBox kaCB;
    @FXML
    private CheckBox ipkCB;
    @FXML
    private CheckBox ippCB;
    @FXML
    private CheckBox ipkaCB;
    @FXML
    private CheckBox apCB;

    
    private ToggleGroup group;
    
    private Stage dialogStage;
    private boolean saveClicked = false;
    private int Settings[];
    
    @FXML
    public void initialize() {
            
    }    
    
    public void initData(){
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Shfaq");
        options.add("Mos Shfaq");
        combo.setValue("Shfaq");
        combo.setItems(options);
        if(Settings[0] == 1)
            combo.setValue("Shfaq");
        else
            combo.setValue("Mos Shfaq");
        
        avancimiCB.selectedProperty().set(convert(Settings[1]));
        buxhetiCB.selectedProperty().set(convert(Settings[2]));
        vfCB.selectedProperty().set(convert(Settings[3]));
        vpCB.selectedProperty().set(convert(Settings[4]));
        kaCB.selectedProperty().set(convert(Settings[5]));
        ipkCB.selectedProperty().set(convert(Settings[6]));
        ippCB.selectedProperty().set(convert(Settings[7]));
        ipkaCB.selectedProperty().set(convert(Settings[8]));
        apCB.selectedProperty().set(convert(Settings[9]));    
    }
    
    @FXML
    private void handleSave(){
            if(combo.getValue().toString().equals("Shfaq"))
                Settings[0] = 1;
            else
                Settings[0] = 0;
            if(avancimiCB.selectedProperty().getValue())
                Settings[1] = 1;
            else
                Settings[1] = 0;
            if(buxhetiCB.selectedProperty().getValue())
                Settings[2] = 1;
            else
                Settings[2] = 0;
            if(vfCB.selectedProperty().getValue())
                Settings[3] = 1;
            else
                Settings[3] = 0;
            if(vpCB.selectedProperty().getValue())
                Settings[4] = 1;
            else
                Settings[4] = 0;
            if(kaCB.selectedProperty().getValue())
                Settings[5] = 1;
            else
                Settings[5] = 0;
            if(ipkCB.selectedProperty().getValue())
                Settings[6] = 1;
            else
                Settings[6] = 0;
            if(ippCB.selectedProperty().getValue())
                Settings[7] = 1;
            else
                Settings[7] = 0;
            if(ipkaCB.selectedProperty().getValue())
                Settings[8] = 1;
            else
                Settings[8] = 0;
            if(apCB.selectedProperty().getValue())
                Settings[9] = 1;
            else
                Settings[9] = 0;
            
            saveClicked = true;
    }
    
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public boolean isSaveClicked() {
        return saveClicked;
    }
    
    public void setSettings(int[] Settings){
        this.Settings = Settings;
    }
    
    public int[] getSettings(){
        return Settings;
    }
    
    private boolean convert(int val){
        if(val == 1)
            return true;
        else
            return false;
    }
    
}
