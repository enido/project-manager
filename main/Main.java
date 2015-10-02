/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import main.model.Activity;
import main.model.ActivityListWrapper;

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
        
        showActivityPaneOverview();
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
            
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            
            primaryStage.show();
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showActivityPaneOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ActivityPane.fxml"));
            AnchorPane activityPaneOverview = (AnchorPane) loader.load();
            
            rootLayout.setLeft(activityPaneOverview);
            
            ActivityPaneController controller = loader.getController();
            controller.setMainApp(this);
            controller.setTableData(tableData);
            controller.startTreeView();
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public void showTableOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/TableLayout.fxml"));
            AnchorPane tableOverview = (AnchorPane) loader.load();
            
            rootLayout.setBottom(tableOverview);
            
            TableOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setTableData(tableData);
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
            controller.setListData(tableData);
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
    
    public File getActivityFilePath(){
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
       
        if(filePath != null)
            return new File(filePath);
        else
            return null;
        
    }
    
    public void setActivityFilePath(File file){
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if(file!=null){
            prefs.put("filePath", file.getPath());
            
            //Update titullin e stage
            primaryStage.setTitle("AdressApp - " + file.getName());
        }
        else{
            prefs.remove("filePath");
            
            //Update titullin e stage
        primaryStage.setTitle("AddressApp");
        }
        
    }
    
    public void loadActivityDataFromFile(File file){
        try{
            JAXBContext context = JAXBContext
                    .newInstance(ActivityListWrapper.class);
            
            Unmarshaller um = context.createUnmarshaller();
            
            //Lexon XML-n nga skedari dhe ben unmarshall
            ActivityListWrapper wrapper = (ActivityListWrapper) um.unmarshal(file);
            
            tableData.clear();
            tableData.addAll(wrapper.getActivities());
            
            // Save the file path to the registry.
            setActivityFilePath(file);
        }
        catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Te dhenat nuk mund te ngarkoheshin!");
        alert.setContentText("Te dhenat nuk mund te ngarkoheshin nga skedari:\n" + file.getPath());

        alert.showAndWait();
     }
    }
    
    public void saveActivityDataToFile(File file){
        try{
            JAXBContext context = JAXBContext
                    .newInstance(ActivityListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            //Wrapping e te dhenave te activity
            ActivityListWrapper wrapper = new ActivityListWrapper();
            wrapper.setActivities(tableData);
            
            
            //Marshalling dhe ruajtja e XML ne skedar
            m.marshal(wrapper, file);
            
            // Save the file path to the registry.
            setActivityFilePath(file);    
        }
        catch (Exception e) { // catches ANY exception
            e.printStackTrace();
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not save data");
        alert.setContentText("Could not save data to file:\n" + file.getPath());

        alert.showAndWait();
        
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
