/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.model.Activity;

/**
 *
 * @author krisli
 */
public class Main extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Activity> tableData = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Main");
       
        initRootLayout();
        
        showTabPaneOverview();
        showTableOverview();
    }
    
    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showTableOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/fxml_table.fxml"));
            AnchorPane tableOverview = (AnchorPane) loader.load();
            
            rootLayout.setBottom(tableOverview);
            
            TableOverviewController controller = loader.getController();
            controller.setMainApp(this);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showTabPaneOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/TabRoot.fxml"));
            AnchorPane TabOverview = (AnchorPane) loader.load();
            
            rootLayout.setCenter(TabOverview);
            
            TabMenuController controller = loader.getController();
            controller.setMainApp(this); 
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public boolean showInputDialog(Activity aktivitet){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/InputDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Data Input");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            
            InputDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setData(aktivitet);
            
            dialogStage.showAndWait();
            
            return controller.isSaveClicked();
        } 
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    
    public ObservableList<Activity> getTableData(){
        return tableData;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
