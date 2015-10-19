package main;

import main.model.EditingCell;
import main.model.NotEditableCell;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ScrollEvent;
import javafx.util.Callback;
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

	public static int tableIndex = 0;

	ArrayList<Integer> check = new ArrayList<>();

	@FXML
	public void initialize() {

		Callback<TableColumn<Activity, String>, TableCell<Activity, String>> cellFactory = new Callback<TableColumn<Activity, String>, TableCell<Activity, String>>() {

			@Override
			public TableCell call(TableColumn p) {
				return new EditingCell();
			}
		};

		Callback<TableColumn<Activity, String>, TableCell<Activity, String>> cellFactoryNotEditable = new Callback<TableColumn<Activity, String>, TableCell<Activity, String>>() {

			@Override
			public TableCell call(TableColumn p) {
				return new NotEditableCell();
			}
		};

		activityTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		nameColumn.setCellFactory(cellFactory);
		nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setName(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
			}
		});

		idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		idColumn.setCellFactory(cellFactoryNotEditable);
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
		durationColumn.setCellFactory(cellFactoryNotEditable);
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
		budgetColumn.setCellFactory(cellFactory);
		budgetColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setBudgetString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setBudgetString(t.getNewValue());
				activityData.get(index).setBudget(Double.parseDouble(unformatStringNumber(t.getNewValue())));
				activityData.get(index).Calculate();
				activityData.get(index).ConvertToStringProperty();
				mainApp.Refresh();
			}
		});

		unitColumn.setCellValueFactory(cellData -> cellData.getValue().unitProperty());
		unitColumn.setCellFactory(cellFactoryNotEditable);
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
		priceColumn.setCellFactory(cellFactory);
		priceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setPriceString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setPriceString(t.getNewValue());
				activityData.get(index).setPriceValue(Double.parseDouble(unformatStringNumber(t.getNewValue())));
				activityData.get(index).Calculate();
				activityData.get(index).ConvertToStringProperty();
				mainApp.Refresh();
			}
		});

		plannedAmountColumn.setCellValueFactory(cellData -> cellData.getValue().plannedAmountProperty());
		plannedAmountColumn.setCellFactory(cellFactory);
		plannedAmountColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setPlannedAmountString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setPlannedAmountString(t.getNewValue());
				activityData.get(index).setPlannedAmount(Double.parseDouble(unformatStringNumber(t.getNewValue())));
				activityData.get(index).Calculate();
				activityData.get(index).ConvertToStringProperty();
				mainApp.Refresh();
			}
		});

		currentAmountColumn.setCellValueFactory(cellData -> cellData.getValue().currentAmountProperty());
		currentAmountColumn.setCellFactory(cellFactory);
		currentAmountColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setCurrentAmountString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setCurrentAmountString(t.getNewValue());
				activityData.get(index).setCurrentAmount(Double.parseDouble(unformatStringNumber(t.getNewValue())));
				activityData.get(index).Calculate();
				activityData.get(index).ConvertToStringProperty();
				mainApp.Refresh();
			}
		});

		actualAmountColumn.setCellValueFactory(cellData -> cellData.getValue().actualAmountProperty());
		actualAmountColumn.setCellFactory(cellFactory);
		actualAmountColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setActualAmountString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setActualAmountString(t.getNewValue());
				activityData.get(index).setActualAmount(Double.parseDouble(unformatStringNumber(t.getNewValue())));
				activityData.get(index).Calculate();
				activityData.get(index).ConvertToStringProperty();
				mainApp.Refresh();
			}
		});

		plannedProgressColumn.setCellValueFactory(cellData -> cellData.getValue().plannedProgressProperty());
		plannedProgressColumn.setCellFactory(cellFactory);
		plannedProgressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setPlannedProgressString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setPlannedProgressString(t.getNewValue());

				System.out.println(t.getOldValue());
				activityData.get(index).setPlannedProgressFromPercentage(t.getNewValue());
				activityData.get(index).Calculate();
				activityData.get(index).ConvertToStringProperty();
				mainApp.Refresh();
			}
		});

		currentProgressColumn.setCellValueFactory(cellData -> cellData.getValue().currentProgressProperty());
		currentProgressColumn.setCellFactory(cellFactory);
		currentProgressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));

				activityData.get(index).setCurrentProgressString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setCurrentProgressString(t.getNewValue());
				activityData.get(index).setCurrentProgressFromPercentage(t.getNewValue());
				activityData.get(index).Calculate();
				activityData.get(index).ConvertToStringProperty();
				mainApp.Refresh();
			}
		});

		pvColumn.setCellValueFactory(cellData -> cellData.getValue().pvProperty());
		pvColumn.setCellFactory(cellFactory);
		pvColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setPvString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setPvString(t.getNewValue());
				activityData.get(index).setPV(Double.parseDouble(unformatStringNumber(t.getNewValue())));
				activityData.get(index).Calculate();
				activityData.get(index).ConvertToStringProperty();
				mainApp.Refresh();
			}
		});

		acColumn.setCellValueFactory(cellData -> cellData.getValue().acProperty());
		acColumn.setCellFactory(cellFactory);
		acColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Activity, String>>() {

			@Override
			public void handle(CellEditEvent<Activity, String> t) {
				int index = activityData.indexOf(t.getTableView().getItems().get(t.getTablePosition().getRow()));
				activityData.get(index).setAcString(t.getNewValue());
				((Activity) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setAcString(t.getNewValue());
				activityData.get(index).setAC(Double.parseDouble(unformatStringNumber(t.getNewValue())));
				activityData.get(index).Calculate();
				activityData.get(index).ConvertToStringProperty();
				mainApp.Refresh();
			}
		});

		evColumn.setCellValueFactory(cellData -> cellData.getValue().evProperty());
		evColumn.setCellFactory(cellFactoryNotEditable);
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
		cvColumn.setCellFactory(cellFactoryNotEditable);
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
		svColumn.setCellFactory(cellFactoryNotEditable);
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
		cpiColumn.setCellFactory(cellFactoryNotEditable);
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
		spiColumn.setCellFactory(cellFactoryNotEditable);
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
			alert.setHeaderText("Asnje aktivitet nuk Ã«shtÃ« selektuar");
			alert.setContentText("Ju lutemi, zgjidhni njÃ« aktivitet nga tabela.");

			alert.showAndWait();
		}
	}

	public void setMainApp(Content mainApp) {
		this.mainApp = mainApp;
		activityTable.setItems(mainApp.getTableData());
		System.out.println("TableOverview Method: MainApp (" + mainApp.getIndex() + ") set!!");
		System.out.println("Table Index: " + tableIndex);
	}

	public void setTableData(ObservableList<Activity> activityData) {
		this.activityData = activityData;
		System.out.println("Table Data set!");
	}

	public TableView<Activity> getTable() {
		return activityTable;
	}

	public void showAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle("Error");
		alert.setHeaderText("Aktiviteti i zgjedhur eshte Prind");
		alert.setContentText("Fushat e aktivitetit prind nuk mund te modifikohen");

		alert.showAndWait();
	}

	public static String unformatStringNumber(String str) {
		String tmp = new String();
		tmp = str;

		for (int i = 0; i < tmp.length(); i++) {
			if (tmp.contains(","))
				tmp = tmp.replace(",", "");
		}
		return tmp;
	}
}