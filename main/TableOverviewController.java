package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
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

	private Main mainApp;

	private ObservableList<Activity> activityData = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() {
                nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		// durationColumn.setCellFactory(new TextFieldCellFactory());
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setName(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setName(t.getNewValue());
			}
		});
            
		idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		// durationColumn.setCellFactory(new TextFieldCellFactory());
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
				activityData.get(index).setDurationString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setDurationString(t.getNewValue());
			}
		});

		budgetColumn.setCellValueFactory(cellData -> cellData.getValue().budgetProperty());
		budgetColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		budgetColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setBudgetString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setBudgetString(t.getNewValue());
			}
		});

		plannedProgressColumn.setCellValueFactory(cellData -> cellData.getValue().plannedProgressProperty());
		plannedProgressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		plannedProgressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setPlannedProgressString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setPlannedProgressString(t.getNewValue());
			}
		});

		currentProgressColumn.setCellValueFactory(cellData -> cellData.getValue().currentProgressProperty());
		currentProgressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		currentProgressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setCurrentProgressString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setCurrentProgressString(t.getNewValue());
			}
		});

		pvColumn.setCellValueFactory(cellData -> cellData.getValue().pvProperty());
		pvColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		pvColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setPvString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setPvString(t.getNewValue());
			}
		});

		acColumn.setCellValueFactory(cellData -> cellData.getValue().acProperty());
		acColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		acColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setAcString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setAcString(t.getNewValue());
			}
		});

		evColumn.setCellValueFactory(cellData -> cellData.getValue().evProperty());
		evColumn.setCellFactory(TextFieldTableCell.forTableColumn());
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
		cvColumn.setCellFactory(TextFieldTableCell.forTableColumn());
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
		svColumn.setCellFactory(TextFieldTableCell.forTableColumn());
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
		cpiColumn.setCellFactory(TextFieldTableCell.forTableColumn());
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
		spiColumn.setCellFactory(TextFieldTableCell.forTableColumn());
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
		}
	}

	@FXML
	public void handleDelete() {
		int selectedIndex = activityTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			activityTable.getItems().remove(selectedIndex);
			
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Activity Selected");
			alert.setContentText("Please select an activity from the table.");

			alert.showAndWait();
		}
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		activityTable.setItems(mainApp.getTableData());
	}

	public void setTableData(ObservableList<Activity> activityData) {
		this.activityData = activityData;
	}

	public TableView<Activity> getTable() {
		return activityTable;
	}
}
