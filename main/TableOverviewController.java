/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import main.model.Activity;


/**
 *
 * @author krisli
 */
public class TableOverviewController implements Initializable {
    
    @FXML
    private Button edit;
    
    @FXML
    private TableView<Activity> table;
    
    private Main mainApp;
    
    
    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;
        
        table.setItems(mainApp.getTableData());
    }
    
    
    @FXML
    public void handleEdit(){
        Activity temp = new Activity();
        boolean saveClicked = mainApp.showInputDialog(temp);
        if(saveClicked){
            mainApp.getTableData().add(temp);
        }
    } 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
