/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.Activity;

/**
 *
 * @author krisli
 */
public class InputDialogController{
    
    @FXML
    private TextField nameTF;
    @FXML
    private TextField idTF;
    @FXML
    private TextField durTF;
    @FXML
    private TextField budgTF;
    @FXML
    private TextField paTF;
    @FXML
    private TextField caTF;
    @FXML
    private TextField pvTF;
    @FXML
    private TextField acTF;
    @FXML
    private TextField evTF;
    @FXML
    private Button cancel;
    @FXML
    private Button save;
    @FXML
    private ComboBox comboBox;
    
    private Stage dialogStage;
    private boolean saveClicked = false;
    private Activity activity;
    private ObservableList<Activity> temp;
    
    @FXML
    private void initialize() {
        
    }
    
    
    public void setData(Activity data){
        this.activity = data;
    }
    
    public void setListData(ObservableList<Activity> data){
        this.temp = data;
        
        ObservableList<String> option = FXCollections.observableArrayList();
        int i=0;
        option.add("none");
        for(i=0;i<temp.size();i++){
            if(temp.get(i).getParentValue()==0)
                option.add(temp.get(i).getIdString());
        }
        
        comboBox.setValue("none");
        comboBox.setItems(option);

    }
    
    public boolean isSaveClicked(){
        return saveClicked;
    }
    
    @FXML
    private void handleSave(){        
        
        int parent;
        activity.setName(nameTF.getText());
        activity.setID(Integer.parseInt(idTF.getText()));
        if(!durTF.isDisabled()) {
            if(!comboBox.getValue().toString().contains("none"))
                activity.setParentValue(Integer.parseInt(comboBox.getValue().toString()));    
            activity.setDuration(Integer.parseInt(durTF.getText()));
            activity.setBudget(Integer.parseInt(budgTF.getText()));
            activity.setPlannedProgress(Double.parseDouble(paTF.getText()));
            activity.setCurrentProgress(Double.parseDouble(caTF.getText()));
            activity.setPV(Integer.parseInt(pvTF.getText()));
            activity.setAC(Integer.parseInt(acTF.getText()));
            activity.setEV(Integer.parseInt(evTF.getText()));
            activity.Calculate();
            
        }
        
        activity.ConvertToStringProperty();    
        saveClicked = true;
        dialogStage.close();
    }
    
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    
    public Activity getData(){
        return activity;
    }
    
    public int getParentOfSubActivity(){
        return Integer.parseInt(comboBox.getValue().toString());
    }
    
    @FXML
     public void valueChanged(){
          if(comboBox.getValue().toString().contains("none")){
              durTF.setDisable(true);
              budgTF.setDisable(true);
              paTF.setDisable(true);
              caTF.setDisable(true);
              pvTF.setDisable(true);
              acTF.setDisable(true);
              evTF.setDisable(true);
          }
          else{
              idTF.setDisable(false);
              durTF.setDisable(false);
              budgTF.setDisable(false);
              paTF.setDisable(false);
              caTF.setDisable(false);
              pvTF.setDisable(false);
              acTF.setDisable(false);
              evTF.setDisable(false);
          }
     }
    
}
