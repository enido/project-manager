/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.Activity;
import main.util.CalendarUtil;

/**
 * @author krisli
 */
public class InputDialogController {

    @FXML
    private TextField nameTF;
    @FXML
    private TextField idTF;
    @FXML
    private TextField startTimeTF;
    @FXML
    private TextField endTimeTF;
    @FXML
    private TextField budgTF;
    @FXML
    private ComboBox unitCombo;
    @FXML
    private TextField priceTF;
    @FXML
    private TextField saTF; // static Amount
    @FXML
    private TextField paTF; // planned Amount
    @FXML
    private TextField caTF; // current Amount
    @FXML
    private TextField aaTF; // actual Amount
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

    public void setListData(ObservableList<Activity> data) {
        this.temp = data;

        ObservableList<String> units = FXCollections.observableArrayList(); 
        ObservableList<String> option = FXCollections.observableArrayList();
        int i = 0;
        option.add("none");
        for (i = 0; i < temp.size(); i++) {
            if (temp.get(i).getParentValue() == 0)
                option.add(temp.get(i).getIdString());
        }

        comboBox.setValue("none");
        comboBox.setItems(option);
        
        units.add("l");
        units.add("cp");
        units.add("ml");
        units.add("m2");
        units.add("m3");
        units.add("ton");
        units.add("kv");
        units.add("kg");
        units.add("g");
        units.add("m");
        units.add("cm");
        
        unitCombo.setItems(units);
        
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSave() {

        int parent;
        String errorMessage= "";
        activity.setName(nameTF.getText());
        if(idTF.getText() == null || idTF.getText().length() == 0){
                errorMessage += "ID e aktivitetit nuk është e vlefshme\n";
            }else{
                try{
                    activity.setID(Integer.parseInt(idTF.getText()));
            }   catch (NumberFormatException e){
                    errorMessage += "ID e aktivitetit nuk është e vlefshme (Duhet të jetë numër)\n";
                }
            }
        if (!startTimeTF.isDisabled() && isInputValid()) {
            if (!comboBox.getValue().toString().contains("none"))
                activity.setParentValue(Integer.parseInt(comboBox.getValue().toString()));
            activity.setStartTimeValue(CalendarUtil.parse(startTimeTF.getText()));
            activity.setEndTimeValue(CalendarUtil.parse(endTimeTF.getText()));
            activity.setBudget(Double.parseDouble(budgTF.getText()));
            activity.setUnitString(unitCombo.getValue().toString());
            activity.setStaticAmount(Double.parseDouble(saTF.getText()));
            activity.setPlannedAmount(Double.parseDouble(paTF.getText()));
            activity.setCurrentAmount(Double.parseDouble(caTF.getText()));
            activity.setActualAmount(Double.parseDouble(aaTF.getText()));
            activity.setPriceValue(Double.parseDouble(priceTF.getText()));
            activity.Calculate();
            activity.ConvertToStringProperty();
            saveClicked = true;
            dialogStage.close();

        } else{
            if(nameTF.getText() == null || nameTF.getText().length() == 0){
                errorMessage += "Emri i aktivitetit nuk është i vlefshëm\n";
            }
            if(!(errorMessage.length() == 0)){
                //Trego mesazhin e errorit
                Alert alert = new Alert(AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Fusha jo të vlefshme");
                alert.setHeaderText("Ju lutem korrigjoni fushat e pavlefshme");
                alert.setContentText(errorMessage);

                alert.showAndWait();
            }
            else if(startTimeTF.isDisabled()){
                activity.ConvertToStringProperty();
                saveClicked = true;
                dialogStage.close();
            }
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Activity getData() {
        return activity;
    }

    public void setData(Activity data) {
        this.activity = data;
    }

    public int getParentOfSubActivity() {
        return Integer.parseInt(comboBox.getValue().toString());
    }

    @FXML
    public void valueChanged() {
        if (comboBox.getValue().toString().contains("none")) {
            unitCombo.setDisable(true);
            startTimeTF.setDisable(true);
            endTimeTF.setDisable(true);
            saTF.setDisable(true);
            paTF.setDisable(true);
            caTF.setDisable(true);
            aaTF.setDisable(true);
            budgTF.setDisable(true);
            priceTF.setDisable(true);
        } else {
            unitCombo.setDisable(false);
            startTimeTF.setDisable(false);
            endTimeTF.setDisable(false);
            budgTF.setDisable(false);
            saTF.setDisable(false);
            paTF.setDisable(false);
            caTF.setDisable(false);
            aaTF.setDisable(false);
            priceTF.setDisable(false);
        }
    }
    
    private boolean isInputValid(){
        String errorMessage = "";
        
        if(nameTF.getText() == null || nameTF.getText().length() == 0){
            errorMessage += "Emri i aktivitetit nuk është i vlefshëm\n";
        }
        if(idTF.getText() == null || idTF.getText().length() == 0){
            errorMessage += "ID e aktivitetit nuk është e vlefshme\n";
        }else{
            try{
                Integer.parseInt(idTF.getText());
            } catch (NumberFormatException e){
                errorMessage += "ID e aktivitetit nuk është e vlefshme (Duhet të jetë numër)\n";
            }
        }
        if(unitCombo.getValue()==null || unitCombo.getValue().toString().length() == 0){
            errorMessage += "Njësia matëse nuk është zgjedhur\n";
        }
        if(budgTF.getText() == null || budgTF.getText().length() == 0){
            errorMessage += "Buxheti i aktivitetit nuk është i vlefshëm\n";
        }else{
            try{
                Double.parseDouble(budgTF.getText());
            } catch (NumberFormatException e){
                errorMessage += "Buxheti i aktivitetit nuk është i vlefshëm (Duhet të jetë numër)\n";
            }
        }
        if(startTimeTF.getText() == null || startTimeTF.getText().length() == 0){
            errorMessage += "Koha e fillimit nuk është e vlefshme\n";
        }else{
            if (!CalendarUtil.validString(startTimeTF.getText())) {
                errorMessage += "Ju lutem perdorni formatin dd-mm-vvvv!\n";
            }
        }
        if(endTimeTF.getText() == null || endTimeTF.getText().length() == 0){
            errorMessage += "Koha e mbarimit nuk është e vlefshme\n";
        }else{
            if (!CalendarUtil.validString(endTimeTF.getText())) {
                errorMessage += "Ju lutem perdorni formatin format dd-mm-vvvv!\n";
                }
        }
        if(saTF.getText() == null || saTF.getText().length() == 0){
            errorMessage += "Sasia statike e planifikuar e aktivitetit nuk është e vlefshme\n";
        }else{
            try{
                Double.parseDouble(saTF.getText());
            } catch (NumberFormatException e){
                errorMessage += "Sasia statike e planifikuar e aktivitetit nuk është e vlefshme (Duhet të jetë numër)\n";
            }
        }
        if(paTF.getText() == null || paTF.getText().length() == 0){
            errorMessage += "Sasia e planifikuar e aktivitetit nuk është e vlefshme\n";
        }else{
            try{
                Double.parseDouble(paTF.getText());
            } catch (NumberFormatException e){
                errorMessage += "Sasia e planifikuar e aktivitetit nuk është e vlefshme (Duhet të jetë numër)\n";
            }
        }
        if(caTF.getText() == null || caTF.getText().length() == 0){
            errorMessage += "Sasia e aktivitetit nuk është e vlefshme\n";
        }else{
            try{
                Double.parseDouble(caTF.getText());
            } catch (NumberFormatException e){
                errorMessage += "Sasia e aktivitetit nuk është e vlefshme (Duhet të jetë numër)\n";
            }
        }
        if(aaTF.getText() == null || aaTF.getText().length() == 0){
            errorMessage += "Sasia faktike e aktivitetit nuk është e vlefshme\n";
        }else{
            try{
                Double.parseDouble(aaTF.getText());
            } catch (NumberFormatException e){
                errorMessage += "Sasia faktike e aktivitetit nuk është e vlefshme (Duhet të jetë numër)\n";
            }
        }
        if(priceTF.getText() == null || priceTF.getText().length() == 0){
            errorMessage += "Cmimi i aktivitetit nuk është i vlefshëm\n";
        }else{
            try{
                Double.parseDouble(priceTF.getText());
            } catch (NumberFormatException e){
                errorMessage += "Cmimi i aktivitetit nuk është i vlefshëm (Duhet të jetë numër)\n";
            }
        }
        
        if(errorMessage.length()==0)
            return true;
        else{
            //Trego mesazhin e errorit
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Fusha jo të vlefshme");
            alert.setHeaderText("Ju lutem korrigjoni fushat e pavlefshme");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
