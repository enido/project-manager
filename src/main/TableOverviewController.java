package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import main.model.Activity;

public class TableOverviewController {

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private TableView<Activity> activityTable;

    @FXML
    private TableColumn<Activity, String> nameColumn;

    @FXML
    private TableColumn<Activity, String> idColumn;

    @FXML
    private TableColumn<Activity, String> durationColumn;

    @FXML
    private TableColumn<Activity, String> budgetColumn;
    
    @FXML
    private TableColumn<Activity, String> unitColumn;
    
    @FXML
    private TableColumn<Activity, String> priceColumn;
    
    @FXML
    private TableColumn<Activity, String> plannedAmountColumn;
    
    @FXML
    private TableColumn<Activity, String> currentAmountColumn;
    
    @FXML
    private TableColumn<Activity, String> actualAmountColumn;

    @FXML
    private TableColumn<Activity, String> plannedProgressColumn;

    @FXML
    private TableColumn<Activity, String> currentProgressColumn;

    @FXML
    private TableColumn<Activity, String> pvColumn;

    @FXML
    private TableColumn<Activity, String> acColumn;

    @FXML
    private TableColumn<Activity, String> evColumn;

    @FXML
    private TableColumn<Activity, String> cvColumn;

    @FXML
    private TableColumn<Activity, String> svColumn;

    @FXML
    private TableColumn<Activity, String> cpiColumn;

    @FXML
    private TableColumn<Activity, String> spiColumn;

    private Content mainApp;

    private ObservableList<Activity> activityData = FXCollections.observableArrayList();
    
    public static int tableIndex=0;

    @FXML
    public void initialize() {

        activityTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                activityData.get(index).setName(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
            }
        });

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        idColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                activityData.get(index).setIdString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setIdString(t.getNewValue());
            }
        });

        durationColumn.setCellValueFactory(cellData -> cellData.getValue().durationProperty());
        durationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        durationColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                if(activityData.get(index).getParentValue() ==0){
                    showAlert();
                }
                else{
                activityData.get(index).setDurationString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setDurationString(t.getNewValue());}
            }
        });
        
        budgetColumn.setCellValueFactory(cellData -> cellData.getValue().budgetProperty());
        budgetColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        budgetColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                if(activityData.get(index).getParentValue() ==0){
                    showAlert();
                }
                else{
                    activityData.get(index).setBudgetString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setBudgetString(t.getNewValue());
                activityData.get(index).setBudget(Double.parseDouble(t.getNewValue()));
                activityData.get(index).Calculate();
                activityData.get(index).ConvertToStringProperty();}
            }
        });

        unitColumn.setCellValueFactory(cellData -> cellData.getValue().unitProperty());
        unitColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        unitColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                activityData.get(index).setUnitString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setUnitString(t.getNewValue());
            }
        });
        
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                if(activityData.get(index).getParentValue() ==0){
                    showAlert();
                }
                else{
                    activityData.get(index).setPriceString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setPriceString(t.getNewValue());
                activityData.get(index).setPriceValue(Double.parseDouble(t.getNewValue()));
                activityData.get(index).Calculate();
                activityData.get(index).ConvertToStringProperty();}
            }
        });
        
        plannedAmountColumn.setCellValueFactory(cellData -> cellData.getValue().plannedAmountProperty());
        plannedAmountColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        plannedAmountColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                if(activityData.get(index).getParentValue() ==0){
                    showAlert();
                }
                else{
                    activityData.get(index).setPlannedAmountString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setPlannedAmountString(t.getNewValue());
                activityData.get(index).setPlannedAmount(Double.parseDouble(t.getNewValue()));
                activityData.get(index).Calculate();
                activityData.get(index).ConvertToStringProperty();}
            }
        });
        
        currentAmountColumn.setCellValueFactory(cellData -> cellData.getValue().currentAmountProperty());
        currentAmountColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        currentAmountColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                if(activityData.get(index).getParentValue() ==0){
                    showAlert();
                }
                else{
                    activityData.get(index).setCurrentAmountString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setCurrentAmountString(t.getNewValue());
                activityData.get(index).setCurrentAmount(Double.parseDouble(t.getNewValue()));
                activityData.get(index).Calculate();
                activityData.get(index).ConvertToStringProperty();}
            }
        });
        
        actualAmountColumn.setCellValueFactory(cellData -> cellData.getValue().actualAmountProperty());
        actualAmountColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        actualAmountColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                if(activityData.get(index).getParentValue() ==0){
                    showAlert();
                }
                else{
                    activityData.get(index).setActualAmountString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setActualAmountString(t.getNewValue());
                activityData.get(index).setActualAmount(Double.parseDouble(t.getNewValue()));
                activityData.get(index).Calculate();
                activityData.get(index).ConvertToStringProperty();}
            }
        });

        plannedProgressColumn.setCellValueFactory(cellData -> cellData.getValue().plannedProgressProperty());
        plannedProgressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        plannedProgressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                if(activityData.get(index).getParentValue() ==0){
                    showAlert();
                }
                else{
                    activityData.get(index).setPlannedProgressString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setPlannedProgressString(t.getNewValue());
                activityData.get(index).setPlannedProgressFromPercentage(t.getNewValue());
                System.out.println("PP :"+activityData.get(index).getPlannedProgress());
                activityData.get(index).Calculate();
                activityData.get(index).ConvertToStringProperty();}
            }
        });

        currentProgressColumn.setCellValueFactory(cellData -> cellData.getValue().currentProgressProperty());
        currentProgressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        currentProgressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                if(activityData.get(index).getParentValue() ==0){
                    showAlert();
                }
                else{
                    activityData.get(index).setCurrentProgressString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setCurrentProgressString(t.getNewValue());
                activityData.get(index).setCurrentProgressFromPercentage(t.getNewValue());
                System.out.println("CP :"+activityData.get(index).getCurrentProgress());
                activityData.get(index).Calculate();
                activityData.get(index).ConvertToStringProperty();}
            }
        });

        pvColumn.setCellValueFactory(cellData -> cellData.getValue().pvProperty());
        pvColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pvColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                if(activityData.get(index).getParentValue() ==0){
                    showAlert();
                }
                else{
                    activityData.get(index).setPvString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setPvString(t.getNewValue());
                activityData.get(index).setPV(Double.parseDouble(t.getNewValue()));
                activityData.get(index).Calculate();
                activityData.get(index).ConvertToStringProperty();}
            }
        });

        acColumn.setCellValueFactory(cellData -> cellData.getValue().acProperty());
        acColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        acColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                if(activityData.get(index).getParentValue() ==0){
                    showAlert();
                }
                else{
                    activityData.get(index).setAcString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setAcString(t.getNewValue());
                activityData.get(index).setAC(Double.parseDouble(t.getNewValue()));
                activityData.get(index).Calculate();
                activityData.get(index).ConvertToStringProperty();}
            }
        });

        evColumn.setCellValueFactory(cellData -> cellData.getValue().evProperty());
        //evColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        evColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                activityData.get(index).setEvString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setEvString(t.getNewValue());
            }
        });

        cvColumn.setCellValueFactory(cellData -> cellData.getValue().cvProperty());
        //cvColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cvColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                activityData.get(index).setCvString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setCvString(t.getNewValue());
            }
        });

        svColumn.setCellValueFactory(cellData -> cellData.getValue().svProperty());
        //svColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        svColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                activityData.get(index).setSvString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setSvString(t.getNewValue());
            }
        });

        cpiColumn.setCellValueFactory(cellData -> cellData.getValue().cpiProperty());
        //cpiColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cpiColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                activityData.get(index).setCpiString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setCpiString(t.getNewValue());
            }
        });

        spiColumn.setCellValueFactory(cellData -> cellData.getValue().spiProperty());
        //spiColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        spiColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

            @Override
            public void handle(CellEditEvent<Activity, String> t) {
                int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                activityData.get(index).setSpiString(t.getNewValue());
                ((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setSpiString(t.getNewValue());
            }
        });
    }

    @FXML
    public void handleAdd() {
        Activity temp = new Activity();
        boolean saveClicked = mainApp.showInputDialog(temp);
        if (saveClicked) {
            mainApp.getTableData().add(temp);
            mainApp.Refresh();
        }
    }

    @FXML
    public void handleDelete() {

        ObservableList<Activity> selectedIndex = activityTable.getSelectionModel().getSelectedItems();

        if (selectedIndex.size() > 0) {
            activityTable.getItems().removeAll(selectedIndex);
            mainApp.Refresh();
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Nuk ka selektim");
            alert.setHeaderText("Asnje aktivitet nuk është selektuar");
            alert.setContentText("Ju lutemi, zgjidhni një aktivitet nga tabela.");

            alert.showAndWait();
        }
    }

    public void setMainApp(Content mainApp) {
        this.mainApp = mainApp;
        activityTable.setItems(mainApp.getTableData());
        System.out.println("TableOverview Method: MainApp ("+mainApp.getIndex()+") set!!");
        System.out.println("Table Index: "+tableIndex);
    }

    public void setTableData(ObservableList<Activity> activityData) {
        this.activityData = activityData;
        System.out.println("Table Data set!");
    }

    public TableView<Activity> getTable() {
        return activityTable;
    }
    
    public void showAlert(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Error");
        alert.setHeaderText("Aktiviteti i zgjedhur eshte Prind");
        alert.setContentText("Fushat e aktivitetit prind nuk mund te modifikohen");

        alert.showAndWait();
    }
}
