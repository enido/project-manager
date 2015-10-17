/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.model.Activity;
import main.util.GanttChartBuilder;
import main.util.IntervalBuilder;
import org.jfree.chart.ChartPanel;
import org.jfree.data.category.IntervalCategoryDataset;

/**
 *
 * @author krisli
 */
public class Content {
    private Stage primaryStage;
    private ObservableList<Activity> data = FXCollections.observableArrayList();
    private BorderPane RootLayout;
    private Activity sum = new Activity();
    private ChartTabController chartTabController = new ChartTabController();
    private Main mainApp;
    private TabPane tabRootLayout;
    public boolean empty = false;
    private int index;
    private ObservableList<Activity>  temp = FXCollections.observableArrayList();
    
    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Content.class.getResource("view/ContentRoot.fxml"));
            RootLayout = (BorderPane) loader.load();
            
            ContentRootController controller = loader.getController();
            controller.setMainApp(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initTabRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/TabRoot.fxml"));
            tabRootLayout = (TabPane) loader.load();
            
            tabRootLayout.setStyle(Main.class.getResource("view/DarkTheme.css").toString());
                    
            chartTabController.setTabPane(tabRootLayout);
            chartTabController.setMainApp(this);
            chartTabController.setActivitySum(sum);
	    chartTabController.showChartOverview();
            tabRootLayout = chartTabController.getTabPane();
            
            RootLayout.setCenter(tabRootLayout);
            
            System.out.println("initTabRootLayout u therrit" +index);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showTreeView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/TreePane.fxml"));
            ScrollPane activityPaneOverview = (ScrollPane) loader.load();


            TreeViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setTableData(data);
            controller.startTreeView();
            activityPaneOverview.setContent((controller.getTree()));
            
             RootLayout.setLeft(activityPaneOverview);
             
             
             System.out.println("showTreeOveerview u therrit" +index);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showTableOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/TableLayout.fxml"));
            AnchorPane tableOverview = (AnchorPane) loader.load();

            RootLayout.setBottom(tableOverview);

            TableOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setTableData(data);
            System.out.println("showTableOveerview u therrit" +index);
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
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            InputDialogController controller = loader.getController();
            controller.setListData(data);
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
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showGanttOverview() {
            GridPane gridPane = new GridPane();
            
            if(temp.isEmpty() || temp == null){
                temp = data;
                for(int i=0;i<temp.size();i++)
                    System.out.println("Emri: "+temp.get(i).getName());
            }
            
            IntervalCategoryDataset dataSet = IntervalBuilder.buildDataSet(temp);
            GanttChartBuilder ganttChartBuilder = new GanttChartBuilder("Gantt", "X", "Y");
            ChartPanel panel = ganttChartBuilder.buildChartPanel(dataSet);
            SwingNode wrapperNode = new SwingNode();
            wrapperNode.setContent(panel);
            gridPane.add(wrapperNode, 0, 0);

        tabRootLayout.getTabs().get(1).setContent(gridPane);
    }   
    
    public void CalculateAndSort() {
        ObservableList<Activity> temp;
        int i = 0;
        int j = 0;
        int k;
        int size = data.size();
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
            parent = data.get(i).getParentValue();
            id = data.get(i).getID();
            if (parent == 0) {
                for (j = i + 1; j < size; j++) {
                    if (data.get(j).getParentValue() == id) {
                        sumDur += data.get(j).getDuration();
                        sumBudg += data.get(j).getBudget();
                        sumPP += data.get(j).getPlannedProgress();
                        sumCP += data.get(j).getCurrentProgress();
                        sumPV += data.get(j).getPV();
                        sumAC += data.get(j).getAC();
                        sumEV += data.get(j).getEV();
                        sumCV += data.get(j).getCV();
                        sumSV += data.get(j).getSV();
                        sumCPI += data.get(j).getCPI();
                        sumSPI += data.get(j).getSPI();
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
                data.get(i).setDuration(sumDur);
                data.get(i).setBudget(sumBudg);
                data.get(i).setPlannedProgress(totPP);
                data.get(i).setCurrentProgress(totCP);
                data.get(i).setPV(sumPV);
                data.get(i).setAC(sumAC);
                data.get(i).setEV(sumEV);
                data.get(i).setCV(sumCV);
                data.get(i).setSV(sumSV);
                data.get(i).setCPI(totCPI);
                data.get(i).setSPI(totSPI);
                data.get(i).ConvertToStringProperty();
            }
        }
    }
    
    public void calculateSum(){
            int size = data.size();
            double tempEV = 0;
            double tempPV = 0;
            double tempAC = 0;
            double tempBUDG = 0;
            
            for(int i=0;i<size;i++){
                Activity current = data.get(i);
                if(current.getParentValue() == 0){
                    tempEV += current.getEV();
                    tempPV += current.getPV();
                    tempAC += current.getAC();
                    tempBUDG += current.getBudget();
                }
            }
            sum.setEV(tempEV);
            sum.setPV(tempPV);
            sum.setAC(tempAC);
            sum.setBAC(tempBUDG);
            double ETC = tempBUDG - tempEV;
            sum.setETC(ETC);
            sum.setEAC(tempAC + ETC);
    }
    
    public void Refresh() {
        CalculateAndSort();
        calculateSum();
        initRootLayout();
        initTabRootLayout();
        showTreeView();
        showTableOverview();
        showGanttOverview();
        mainApp.Refresh(index);
    }
    
    public void initialize(){
        initRootLayout();
        initTabRootLayout();
        showTreeView();
        showTableOverview();
        showGanttOverview();
    }
    
    public Stage getPrimaryStage() {
        return mainApp.getPrimaryStage();
    }
    
    public BorderPane getContentRoot(){
        return RootLayout;
    }
    
    public ObservableList<Activity> getTableData(){
        return data;
    }
    
    public void setTableData(ObservableList<Activity> data){
        this.data = data;
    }
    
    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;
    }
    
    public Activity getSum(){
        return sum;
    }
        
    public boolean isEmpty(){
        return empty;
    }
    
    public void setEmpty(boolean val){
        this.empty = val;
    }
    
    public void setIndex(int index){
        this.index = index;
    }
    
    public int getIndex(){
        return index;
    }   
    
    public void setData(ObservableList<String> treeData){
        temp = FXCollections.observableArrayList();
        for(int i=0;i<treeData.size();i++){
            String index = treeData.get(i).toString();
            System.out.println(treeData.size());
            index = index.substring(0, 4);
            int parent = Integer.parseInt(index.substring(0, 1));
            int id;
            if(index.charAt(3) == ' ')
                id = Integer.parseInt(index.substring(2,3));
            else
                id = Integer.parseInt(index.substring(2,4));
            
            for(int j=0;j<data.size();j++){
                Activity tempData = data.get(j);
                if(tempData.getParentValue() == parent && tempData.getID() == id ){
                    temp.add(tempData);
                    break;
                }
            }
        }
    }
}