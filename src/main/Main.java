/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.model.Activity;
import main.model.ActivityListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

/**
 * @author krisli
 */
public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private TabPane tabRootLayout;
    private ObservableList<Activity> tableData = FXCollections.observableArrayList();
    private Activity sum = new Activity();
    private ChartTabController chartTabController = new ChartTabController();
    //sGanttChart<Activity> gantt = new GanttChart<Activity>(new Activity());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Main");

        initRootLayout();

        showActivityPaneOverview();
        initTabRootLayout();
        showTableOverview();

    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initTabRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/TabRoot.fxml"));
            tabRootLayout = (TabPane) loader.load();
            
            chartTabController.setTabPane(tabRootLayout);
            chartTabController.setMainApp(this);
            chartTabController.setActivitySum(sum);
	    chartTabController.showChartOverview();
            rootLayout.setCenter(tabRootLayout);
            
            TabMenuController controller = loader.getController();
            controller.setMainApp(this);
            
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showActivityPaneOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ActivityPane.fxml"));
            AnchorPane activityPaneOverview = (AnchorPane) loader.load();

            rootLayout.setLeft(activityPaneOverview);

            ActivityPaneController controller = loader.getController();
            controller.setMainApp(this);
            controller.setTableData(tableData);
            controller.startTreeView();
            activityPaneOverview.getChildren().add(controller.getTree());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showTableOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/TableLayout.fxml"));
            AnchorPane tableOverview = (AnchorPane) loader.load();

            rootLayout.setBottom(tableOverview);

            TableOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setTableData(tableData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showInputDialog(Activity aktivitet) {
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
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void showChartOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/tabmenu/ChartTab.fxml"));
            GridPane chartOverview = (GridPane) loader.load();
            
            tabRootLayout.getTabs().get(0).setContent(chartOverview);
            
            //TableOverviewController controller = loader.getController();
            //controller.setMainApp(this);
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public File getActivityFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);

        if (filePath != null)
            return new File(filePath);
        else
            return null;

    }

    public void setActivityFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            //Update titullin e stage
            primaryStage.setTitle("PROING - " + file.getName());
        } else {
            prefs.remove("filePath");

            //Update titullin e stage
            primaryStage.setTitle("PROING");
        }

    }

    public void loadActivityDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(ActivityListWrapper.class);

            Unmarshaller um = context.createUnmarshaller();

            //Lexon XML-n nga skedari dhe ben unmarshall
            ActivityListWrapper wrapper = (ActivityListWrapper) um.unmarshal(file);

            tableData.clear();
            tableData.addAll(wrapper.getActivities());

            // Save the file path to the registry.
            setActivityFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Te dhenat nuk mund te ngarkoheshin!");
            alert.setContentText("Te dhenat nuk mund te ngarkoheshin nga skedari:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void saveActivityDataToFile(File file) {
        try {
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
        } catch (Exception e) { // catches ANY exception
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Te dhenat nuk mund te ruhen!");
            alert.setContentText("Te dhenat nuk mund te ruhen ne skedarin:\n" + file.getPath());

            alert.showAndWait();

        }

    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Activity> getTableData() {
        return tableData;
    }

    public Activity getSum(){
        return sum;
    }

    public void Refresh() {
        CalculateAndSort();
        calculateSum();
        showActivityPaneOverview();
        initTabRootLayout();
        showTableOverview();
    }

    public void CalculateAndSort() {
        ObservableList<Activity> temp;
        int i = 0;
        int j = 0;
        int k;
        int size = tableData.size();
        long sumDur = 0;
        double sumBudg = 0, sumPV = 0, sumAC = 0, sumEV = 0, sumCV = 0, sumSV = 0;
        double totCPI, totSPI, totPP, totCP, sumPP = 0, sumCP = 0, sumCPI = 0, sumSPI = 0;
        int parent;
        int id;
        

        //Calculation
        for (i = 0; i < size; i++) {
            k = 0;
            sumDur = 0;
            sumBudg = 0;
            sumPV = 0;
            sumAC = 0;
            sumEV = 0;
            sumCV = 0;
            sumSV = 0;
            totCPI = 0;
            totSPI = 0;
            totPP = 0;
            totCP = 0;
            sumPP = 0;
            sumCP = 0;
            sumCPI = 0;
            sumSPI = 0;
            parent = tableData.get(i).getParentValue();
            id = tableData.get(i).getID();
            if (parent == 0) {
                for (j = i + 1; j < size; j++) {
                    if (tableData.get(j).getParentValue() == id) {
                        sumDur += tableData.get(j).getDuration();
                        sumBudg += tableData.get(j).getBudget();
                        sumPP += tableData.get(j).getPlannedProgress();
                        sumCP += tableData.get(j).getCurrentProgress();
                        sumPV += tableData.get(j).getPV();
                        sumAC += tableData.get(j).getAC();
                        sumEV += tableData.get(j).getEV();
                        sumCV += tableData.get(j).getCV();
                        sumSV += tableData.get(j).getSV();
                        sumCPI += tableData.get(j).getCPI();
                        sumSPI += tableData.get(j).getSPI();
                        k++;

                    }
                }
                if (k != 0 && sumAC != 0 && sumPV != 0) {
                    totPP = (double) sumPP / k;
                    totCP = (double) sumCP / k;
                    totCPI = (double) sumEV / sumAC;
                    totSPI = (double) sumEV / sumPV;
                    if (k == 1) {
                        totCPI = sumCPI;
                        totSPI = sumSPI;
                    }
                }
                tableData.get(i).setDuration(sumDur);
                tableData.get(i).setBudget(sumBudg);
                tableData.get(i).setPlannedProgress(totPP);
                tableData.get(i).setCurrentProgress(totCP);
                tableData.get(i).setPV(sumPV);
                tableData.get(i).setAC(sumAC);
                tableData.get(i).setEV(sumEV);
                tableData.get(i).setCV(sumCV);
                tableData.get(i).setSV(sumSV);
                tableData.get(i).setCPI(totCPI);
                tableData.get(i).setSPI(totSPI);
                tableData.get(i).ConvertToStringProperty();
            }

        }

    }
    
        public void calculateSum(){
            int size = tableData.size();
            double tempEV = 0;
            double tempPV = 0;
            double tempAC = 0;
            
            for(int i=0;i<size;i++){
                Activity current = tableData.get(i);
                if(current.getParentValue() == 0){
                    tempEV += current.getEV();
                    tempPV += current.getPV();
                    tempAC += current.getAC();
                }
            }
            sum.setEV(tempEV);
            sum.setPV(tempPV);
            sum.setAC(tempAC);
        }

}
