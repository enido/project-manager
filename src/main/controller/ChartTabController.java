package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxutils.chart.ChartPanManager;
import jfxutils.chart.JFXChartUtil;
import jfxutils.chart.StableTicksAxis;
import main.Content;
import main.Main;
import main.model.Activity;
import main.model.CurvedFittedAreaChart;
import main.util.CalendarUtil;

public class ChartTabController {

	TabPane tabPane;
	private Activity sum;
	private Content mainApp;
	private Activity temp;
	private ObservableList<Activity> data = FXCollections.observableArrayList();
        private boolean showEac = true;

	int counter = 0;

	public ChartTabController() {
	}

	@SuppressWarnings("unchecked")
	public void showChartOverview() {

		GridPane gridPane = new GridPane();
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		column1.setPercentWidth(80);
		column2.setPercentWidth(20);
		gridPane.getColumnConstraints().addAll(column1, column2);

		final StableTicksAxis xAxis1 = new StableTicksAxis();
		final StableTicksAxis yAxis1 = new StableTicksAxis();

		/******************** FIRST CHART **********************/

		final CurvedFittedAreaChart areaChart1 = new CurvedFittedAreaChart(xAxis1, yAxis1);
		areaChart1.setPrefHeight(10000);
		// areaChart1.setTitle("Grafik");
		xAxis1.setLabel("Koha");
		yAxis1.setLabel("Kosto");

		// First chart
		GridPane.setHalignment(areaChart1, HPos.RIGHT);
		gridPane.add(areaChart1, 0, 0);

		XYChart.Series seriesEV = new XYChart.Series();
		seriesEV.setName("VF");

		seriesEV.getData().add(new XYChart.Data(0, 0));
		seriesEV.getData().add(new XYChart.Data(382, sum.getEV() / 2));
		seriesEV.getData().add(new XYChart.Data(638, sum.getEV()));

		XYChart.Series seriesPV = new XYChart.Series();
		seriesPV.setName("VP");

		seriesPV.getData().add(new XYChart.Data(0, 0));
		seriesPV.getData().add(new XYChart.Data(382, sum.getPV() / 2));
		seriesPV.getData().add(new XYChart.Data(638, sum.getPV()));

		XYChart.Series seriesBAC = new XYChart.Series();
		seriesBAC.setName("PrevP");
		seriesBAC.getData().add(new XYChart.Data(30, sum.getBAC()));
		seriesBAC.getData().add(new XYChart.Data(25, sum.getBAC() / 1.2));
		seriesBAC.getData().add(new XYChart.Data(20, sum.getPV()));
		seriesBAC.getData().add(new XYChart.Data(22, sum.getPV() + 0.325 * sum.getPV()));

		XYChart.Series seriesAC = new XYChart.Series();
		seriesAC.setName("KA");

		seriesAC.getData().add(new XYChart.Data(0, 0));
		seriesAC.getData().add(new XYChart.Data(382, sum.getAC() / 2));
		seriesAC.getData().add(new XYChart.Data(638, sum.getAC()));
                
		XYChart.Series seriesEAC = new XYChart.Series();
		seriesEAC.setName("VPërf");
		seriesEAC.getData().add(new XYChart.Data(762, sum.getEAC()));
		seriesEAC.getData().add(new XYChart.Data(718, sum.getEAC() / 1.08));
                seriesEAC.getData().add(new XYChart.Data(680, sum.getAC() +(0.15*sum.getAC())));
		seriesEAC.getData().add(new XYChart.Data(638, sum.getAC()));

		areaChart1.getData().addAll(seriesEV, seriesPV, seriesAC);
                if(showEac)
                    areaChart1.getData().add(seriesEAC);

		/******************** SECOND GRID COLUMN **********************/

		ObservableList<Activity> tempData = FXCollections.observableArrayList();
		Button display = new Button("Shfaq");
		display.getStyleClass().add("Kot");
		Button resetZoom = new Button("Reset");
                
                Label prog = new Label("Avancimi - "+sum.getCurrentProgressPercentage());
                prog.setWrapText(true);
                prog.getStyleClass().add("ModifiedStyle");
                Label budg = new Label("Buxheti - "+sum.toString(sum.getBudget()));
                budg.setWrapText(true);
                budg.getStyleClass().add("ModifiedStyle");
		Label ev = new Label("VF - "+sum.toString(sum.getEV()));
		ev.setWrapText(true);
		ev.getStyleClass().add("ModifiedStyle");
		Label pv = new Label("VP - "+sum.toString(sum.getPV()));
		pv.getStyleClass().add("ModifiedStyle");
		pv.setWrapText(true);
		Label ac = new Label("KA - "+sum.toString(sum.getAC()));
		ac.getStyleClass().add("ModifiedStyle");
		ac.setWrapText(true);
                Label cpi = new Label("IPK - "+sum.toString(sum.getCPI()));
		cpi.getStyleClass().add("ModifiedStyle");
		cpi.setWrapText(true);
                Label spi = new Label("IPP - "+sum.toString(sum.getSPI()));
		spi.getStyleClass().add("ModifiedStyle");
		spi.setWrapText(true);
                Label ap = new Label("AP - "+sum.toString(sum.getApValue()));
		ap.getStyleClass().add("ModifiedStyle");
		ap.setWrapText(true);
                Label eac = new Label("VPerf - "+sum.toString(sum.getEAC()));
		eac.getStyleClass().add("ModifiedStyle");
		eac.setWrapText(true);
                Label etc = new Label("VPm - "+sum.toString(sum.getETC()));
		etc.getStyleClass().add("ModifiedStyle");
		etc.setWrapText(true);
                Label tcpi = new Label("IPKA - "+sum.toString(sum.getTcpiValue()));
		tcpi.getStyleClass().add("ModifiedStyle");
		tcpi.setWrapText(true);

		/*ToggleGroup group = new ToggleGroup();

		RadioButton rb1 = new RadioButton("Home");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		rb1.getStyleClass().add("ModifiedStyle1");

		RadioButton rb2 = new RadioButton("Calendar");
		rb2.setToggleGroup(group);
		rb2.getStyleClass().add("ModifiedStyle");

		RadioButton rb3 = new RadioButton("Contacts");
		rb3.setToggleGroup(group);
		rb3.getStyleClass().add("ModifiedStyle");*/

		AnchorPane anchorPane = new AnchorPane();
		VBox firstVBox = new VBox();
		firstVBox.setSpacing(10);
		firstVBox.getStyleClass().add("ScrollPane");

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 0, 0, 10));
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.BOTTOM_CENTER);
		hBox.getStyleClass().add("ScrollPane");
		hBox.getChildren().addAll(display, resetZoom);

		firstVBox.getChildren().addAll(scrollPane, hBox, prog, budg, ev, pv, ac, cpi, spi, tcpi, ap);

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getParentValue() == 0) {
				tempData.add(data.get(i));
			}
		}

		VBox vBox = new VBox();

		vBox.setPadding(new Insets(10, 10, 10, 10));
		vBox.setSpacing(15);
		vBox.setAlignment(Pos.TOP_LEFT);

		CheckBox allActivities = new CheckBox("Gjithë Aktivitetet");

		allActivities.getStyleClass().add("CheckBox");

		ArrayList<CheckBox> checkBox = new ArrayList();

		for (int i = 0; i < tempData.size(); i++) {
			checkBox.add(new CheckBox());
			checkBox.get(i).setText(tempData.get(i).getName());
			checkBox.get(i).getStyleClass().add("CheckBox");
			vBox.getChildren().add(checkBox.get(i));
		}

		vBox.getChildren().add(allActivities);
                                                
                display.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
                            ObservableList<Activity> dummy = FXCollections.observableArrayList();
                            
                            for(int i=0;i<tempData.size();i++){
                                if(checkBox.get(i).isSelected()){
                                    dummy.add(tempData.get(i));
                                }
                            }
                            showEac = false;
                            mainApp.Refresh(calculateSum(dummy));
                        }
		});
                
                resetZoom.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event){
                        showEac = true;
                        mainApp.Refresh();
                    }
                });
                
		scrollPane.setContent(vBox);

		anchorPane.getChildren().add(firstVBox);
		anchorPane.setTopAnchor(firstVBox, 10.0);
		anchorPane.setRightAnchor(firstVBox, 10.0);
		anchorPane.setBottomAnchor(firstVBox, 10.0);
		anchorPane.setLeftAnchor(firstVBox, 10.0);
		anchorPane.getStyleClass().add("AnchorPane");
		scrollPane.getStyleClass().add("ScrollPane");
		vBox.getStyleClass().add("ScrollPane");

		GridPane.setHalignment(anchorPane, HPos.RIGHT);
		gridPane.add(anchorPane, 1, 0);

		/******************** SECOND GRID COLUMN **********************/

		areaChart1.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				double xStart = areaChart1.getXAxis().getLocalToParentTransform().getTx();
				double axisXRelativeMousePosition = mouseEvent.getX() - xStart;
			}
		});

		ChartPanManager panner1 = new ChartPanManager(areaChart1);
		panner1.setMouseFilter(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.PRIMARY) {
					// let it through
				} else {
					mouseEvent.consume();
				}
			}
		});
		panner1.start();

		// Zooming works only via primary mouse button without ctrl held down
		JFXChartUtil.setupZooming(areaChart1, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() != MouseButton.MIDDLE || mouseEvent.isShortcutDown())
					mouseEvent.consume();
			}
		});

		tabPane.getTabs().get(0).setContent(gridPane);

		/******************* CPI & SPI Charts **************************/

		temp = new Activity();
		if (mainApp.getTableData().size() != 0)
			temp = mainApp.getTableData().get(0);
		else {
			temp.setFirstCPI(0);
			temp.setSecondCPI(0);
			temp.setThirdCPI(0);
			temp.setFirstSPI(0);
			temp.setSecondSPI(0);
			temp.setThirdSPI(0);
		}

		// --------------------LINE CHART----------------------------//
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final LineChart cpiChart = new LineChart(xAxis, yAxis);
		cpiChart.setTitle("IPK dhe IPP");

		xAxis.setLabel("Data");

		XYChart.Series cpiSeries = new XYChart.Series();
		cpiSeries.setName("IPK");
		if (temp.getFirstCPI() != 0)
			cpiSeries.getData().add(new XYChart.Data(CalendarUtil.format(temp.getFirstDate()), temp.getFirstCPI()));
		if (temp.getSecondCPI() != 0)
			cpiSeries.getData().add(new XYChart.Data(CalendarUtil.format(temp.getSecondDate()), temp.getSecondCPI()));
		if (temp.getThirdCPI() != 0)
			cpiSeries.getData().add(new XYChart.Data(CalendarUtil.format(temp.getThirdDate()), temp.getThirdCPI()));
		/*if (sum.getTcpiValue() != 0)
			cpiSeries.getData().add(new XYChart.Data("fund", sum.getTcpiValue()));*/

		XYChart.Series spiSeries = new XYChart.Series();
		spiSeries.setName("IPP");
		if (temp.getFirstSPI() != 0)
			spiSeries.getData().add(new XYChart.Data(CalendarUtil.format(temp.getFirstDate()), temp.getFirstSPI()));
		if (temp.getSecondSPI() != 0)
			spiSeries.getData().add(new XYChart.Data(CalendarUtil.format(temp.getSecondDate()), temp.getSecondSPI()));
		if (temp.getThirdSPI() != 0)
			spiSeries.getData().add(new XYChart.Data(CalendarUtil.format(temp.getThirdDate()), temp.getThirdSPI()));
		/*if (sum.getTspiValue() != 0)
			spiSeries.getData().add(new XYChart.Data("fund", sum.getTspiValue()));*/

		cpiChart.getData().addAll(cpiSeries, spiSeries);

		// --------------------BAR CHART----------------------------//

		final CategoryAxis boshtiX = new CategoryAxis();
		final NumberAxis boshtiY = new NumberAxis();

		final BarChart<String, Number> bc = new BarChart<String, Number>(boshtiX, boshtiY);

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("IPK");
		series1.getData().add(new XYChart.Data("1", temp.getFirstCPI()));
		series1.getData().add(new XYChart.Data("2", temp.getSecondCPI()));
		series1.getData().add(new XYChart.Data("3", temp.getThirdCPI()));

		XYChart.Series series2 = new XYChart.Series();
		series2.setName("IPP");
		series2.getData().add(new XYChart.Data("1", temp.getFirstSPI()));
		series2.getData().add(new XYChart.Data("2", temp.getSecondSPI()));
		series2.getData().add(new XYChart.Data("3", temp.getThirdSPI()));

		bc.setBarGap(5);
		bc.setCategoryGap(20);

		bc.getData().addAll(series1, series2);
		bc.setTitle("IPK & IPP (Bar Chart)");

		GridPane gridPane2 = new GridPane();
		gridPane2.setPrefHeight(400.0);
		gridPane2.setPrefWidth(1100.0);
		gridPane2.setHgap(35);
		gridPane2.setPadding(new Insets(0, 15, 0, 0));

		GridPane.setHalignment(cpiChart, HPos.RIGHT);
		gridPane2.add(cpiChart, 0, 0);
		gridPane2.add(bc, 1, 0);

		Button add = new Button("Shto vlerë");

		Label tcpiLabel = new Label();
		Label tspiLabel = new Label();

		tcpiLabel.setText("  IPKA=" + sum.getTcpiValue());
		tcpiLabel.setTextFill(Color.WHITE);
		tspiLabel.setText("  IPPA=" + sum.getTspiValue());
		tspiLabel.setTextFill(Color.WHITE);

		FlowPane pane = new FlowPane();

		pane.getChildren().addAll(add, tcpiLabel, tspiLabel);

		add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (mainApp.getTableData().size() != 0) {
					boolean saveClicked = showCpiDialog();
					if (saveClicked) {
						mainApp.Refresh();
					}
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.initOwner(mainApp.getPrimaryStage());
					alert.setTitle("Error");
					alert.setHeaderText("Nuk u gjend asnje aktivitet");
					alert.setContentText("Ju lutem, krijoni te pakten nje aktivitet ne tabele\nper te proceduar");

					alert.showAndWait();
				}
			}

		});

		gridPane2.add(pane, 0, 1, 2, 1);
		gridPane.getColumnConstraints().get(1).halignmentProperty().setValue(HPos.CENTER);
		tabPane.getTabs().get(2).setContent(gridPane2);

	}

	public void setData(ObservableList<Activity> activityData) {
		this.data = activityData;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setMainApp(Content mainApp) {
		this.mainApp = mainApp;
	}

	public void setActivitySum(Activity sum) {
		this.sum = sum;
	}
        
        public void setShowEac(boolean showEac){
            this.showEac = showEac;
        }
        
        public boolean getShowEac(){
            return this.showEac; 
       }

	public void autoZoom(CurvedFittedAreaChart chart) {
		chart.getXAxis().setAutoRanging(true);
		chart.getYAxis().setAutoRanging(true);
		ObservableList<XYChart.Series<Number, Number>> data = chart.getData();
		chart.setData(FXCollections.<XYChart.Series<Number, Number>> emptyObservableList());
		chart.setData(data);
	}

	public boolean showCpiDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CpiDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Vlerat e IPK dhe IPP");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			CpiDialogController controller = loader.getController();
			controller.setData(mainApp.getTableData().get(0));
			controller.setPromptText();
			controller.setDialogStage(dialogStage);

			dialogStage.showAndWait();

			return controller.isSaveClicked();

		} catch (IOException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
        
        public Activity calculateSum(ObservableList<Activity> activities) {
		int size = activities.size();
                int k = 0;
		double tempEV = 0;
		double tempPV = 0;
		double tempAC = 0;
		double tempBUDG = 0;
                double tempCPI = 0;
                double tempSPI = 0;
                double tempProg = 0;
                
                Activity dummy = new Activity();

		for (int i = 0; i < size; i++) {
			Activity current = activities.get(i);
			if (current.getParentValue() == 0) {
				tempEV += current.getEV();
				tempPV += current.getPV();
				tempAC += current.getAC();
				tempBUDG += current.getBudget();
                                tempProg += current.getCurrentProgress();
                                k++;
			}
		}
                
                tempProg = tempProg/k;   
                tempCPI = tempEV / tempAC;
                tempSPI = tempEV / tempPV;
                              
                dummy.setCPI(tempCPI);
                dummy.setSPI(tempSPI);
                dummy.setCurrentProgress(tempProg);
                dummy.setBudget(tempBUDG);
		dummy.setEV(tempEV);
		dummy.setPV(tempPV);
		dummy.setAC(tempAC);
		dummy.setBAC(tempBUDG);
		double ETC = tempBUDG - tempEV;
		dummy.setETC(ETC);
		dummy.setEAC(tempAC + ETC);
		double TCPI = (tempBUDG - tempEV) / (tempBUDG - tempAC);
		dummy.setTcpiValue(TCPI);
		double TSPI = (tempBUDG - tempEV) / (tempBUDG - tempPV);
		dummy.setTspiValue(TSPI);
                
                return dummy;
	}
        
}

// EV JESHILE, PV BLU, AC TE KUQE

// TCPI = (BAC - EV) / (BAC - AC)

// TSPI = (BAC - EV) / (BAC - PV)