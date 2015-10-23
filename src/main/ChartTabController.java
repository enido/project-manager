package main;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import jfxutils.chart.ChartPanManager;
import jfxutils.chart.JFXChartUtil;
import jfxutils.chart.StableTicksAxis;
import main.model.Activity;
import main.model.CurvedFittedAreaChart;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.GroupLayout.Alignment;
import main.util.CalendarUtil;

public class ChartTabController {

	TabPane tabPane;
	private Activity sum; 
	private Content mainApp;
        private Activity temp;

	public ChartTabController() {
	}

	@SuppressWarnings("unchecked")
	public void showChartOverview() {

		GridPane gridPane = new GridPane();
		gridPane.setPrefHeight(400.0);
		gridPane.setPrefWidth(1100.0);
		gridPane.setHgap(35);
		gridPane.setPadding(new Insets(0, 15, 0, 0));
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		column1.setPercentWidth(50);
		column2.setPercentWidth(50);
		gridPane.getColumnConstraints().addAll(column1, column2);

		final StableTicksAxis xAxis1 = new StableTicksAxis();
		final StableTicksAxis yAxis1 = new StableTicksAxis();

		/********************FIRST CHART**********************/
                
                final CurvedFittedAreaChart areaChart1 = new CurvedFittedAreaChart(xAxis1, yAxis1);
		areaChart1.setTitle("Grafik");
		xAxis1.setLabel("Koha");
		yAxis1.setLabel("Kosto");
                
		// First chart
		GridPane.setHalignment(areaChart1, HPos.RIGHT);
		gridPane.add(areaChart1, 0, 0);


		XYChart.Series seriesEV = new XYChart.Series();
		seriesEV.setName("EV");

		seriesEV.getData().add(new XYChart.Data(0, 0));
		seriesEV.getData().add(new XYChart.Data(12, sum.getEV() / 2));
		seriesEV.getData().add(new XYChart.Data(20, sum.getEV()));

		XYChart.Series seriesPV = new XYChart.Series();
		seriesPV.setName("PV");

		seriesPV.getData().add(new XYChart.Data(0, 0));
		seriesPV.getData().add(new XYChart.Data(12, sum.getPV() / 2));
		seriesPV.getData().add(new XYChart.Data(20, sum.getPV()));
                
                XYChart.Series seriesBAC = new XYChart.Series();
                seriesBAC.setName("BAC");
                seriesBAC.getData().add(new XYChart.Data(30, sum.getBAC()));
                seriesBAC.getData().add(new XYChart.Data(25, sum.getBAC()/1.2));
                seriesBAC.getData().add(new XYChart.Data(20, sum.getPV()));
                seriesBAC.getData().add(new XYChart.Data(22, sum.getPV()+0.325*sum.getPV()));
                
		XYChart.Series seriesAC = new XYChart.Series();
		seriesAC.setName("AC");

		seriesAC.getData().add(new XYChart.Data(0, 0));
		seriesAC.getData().add(new XYChart.Data(12, sum.getAC() / 2));
		seriesAC.getData().add(new XYChart.Data(20, sum.getAC()));
                
                XYChart.Series seriesEAC = new XYChart.Series();
                seriesEAC.setName("EAC");
                seriesEAC.getData().add(new XYChart.Data(30, sum.getEAC()));
                seriesEAC.getData().add(new XYChart.Data(25, sum.getEAC()/1.2));
                seriesEAC.getData().add(new XYChart.Data(20, sum.getAC()));
                seriesEAC.getData().add(new XYChart.Data(22, sum.getAC()+0.325*sum.getAC()));
               
		areaChart1.getData().addAll(seriesEV, seriesPV, seriesAC, seriesBAC, seriesEAC);
                
                /********************SECOND CHART**********************/
                	          
		final NumberAxis xAxis2 = new NumberAxis();
		final NumberAxis yAxis2 = new NumberAxis(0, sum.getPV()+sum.getPV()/10, sum.getPV()/3 );             
                
                LineChart areaChart2 = new LineChart(xAxis2, yAxis2);
		areaChart2.setTitle("PROVE");
		xAxis2.setLabel("X");
		yAxis2.setLabel("Y");
                
                XYChart.Series seriesEV2 = new XYChart.Series();
                seriesEV2.setName("EV");
                seriesEV2.getData().add(new XYChart.Data(0, 0));
		seriesEV2.getData().add(new XYChart.Data(20, sum.getEV()));
                
                XYChart.Series seriesPV2 = new XYChart.Series();
                seriesPV2.setName("PV");
                seriesPV2.getData().add(new XYChart.Data(0, 0));
		seriesPV2.getData().add(new XYChart.Data(20, sum.getPV()));
                
                XYChart.Series seriesAC2 = new XYChart.Series();
		seriesAC2.setName("AC");
		seriesAC2.getData().add(new XYChart.Data(0, 0));
		seriesAC2.getData().add(new XYChart.Data(20, sum.getAC()));
                
                areaChart2.getData().addAll(seriesEV2, seriesPV2, seriesAC2);
                               
		GridPane.setHalignment(areaChart2, HPos.RIGHT);
		gridPane.add(areaChart2, 1, 0);                           
                
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
                
                
                
                /*******************CPI & SPI Charts**************************/
                
                temp = new Activity();
                if(mainApp.getTableData().size()!=0)
                    temp = mainApp.getTableData().get(0);
                else{
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
                cpiChart.setTitle("CPI dhe SPI");
                
                xAxis.setLabel("Data");
                
                XYChart.Series cpiSeries = new XYChart.Series();
                cpiSeries.setName("CPI");
                if(temp.getFirstCPI()!=0)
                    cpiSeries.getData().add(new XYChart.Data(CalendarUtil.format(temp.getFirstDate()), temp.getFirstCPI()));
                if(temp.getSecondCPI()!=0)
                    cpiSeries.getData().add(new XYChart.Data(CalendarUtil.format(temp.getSecondDate()),temp.getSecondCPI()));
                if(temp.getThirdCPI()!=0)
                    cpiSeries.getData().add(new XYChart.Data(CalendarUtil.format(temp.getThirdDate()),temp.getThirdCPI()));
                if(sum.getTcpiValue()!=0)
                    cpiSeries.getData().add(new XYChart.Data("fund", sum.getTcpiValue()));
                     
                XYChart.Series spiSeries = new XYChart.Series();
                spiSeries.setName("SPI");
                if(temp.getFirstSPI()!=0)
                    spiSeries.getData().add(new XYChart.Data(CalendarUtil.format(temp.getFirstDate()), temp.getFirstSPI()));
                if(temp.getSecondSPI()!=0)
                    spiSeries.getData().add(new XYChart.Data(CalendarUtil.format(temp.getSecondDate()),temp.getSecondSPI()));
                if(temp.getThirdSPI()!=0)
                    spiSeries.getData().add(new XYChart.Data(CalendarUtil.format(temp.getThirdDate()),temp.getThirdSPI()));
                if(sum.getTspiValue()!=0)
                    spiSeries.getData().add(new XYChart.Data("fund", sum.getTspiValue()));
                
                cpiChart.getData().addAll(cpiSeries, spiSeries);
                
                // --------------------BAR CHART----------------------------//
               
                final CategoryAxis boshtiX = new CategoryAxis();
                final NumberAxis boshtiY = new NumberAxis();
                
                final BarChart<String,Number> bc =  new BarChart<String,Number>(boshtiX,boshtiY);
                
                XYChart.Series series1 = new XYChart.Series();
                series1.setName("CPI");
                series1.getData().add(new XYChart.Data("1", temp.getFirstCPI()));
                series1.getData().add(new XYChart.Data("2", temp.getSecondCPI()));
                series1.getData().add(new XYChart.Data("3", temp.getThirdCPI()));
                
                XYChart.Series series2 = new XYChart.Series();
                series2.setName("SPI");
                series2.getData().add(new XYChart.Data("1", temp.getFirstSPI()));
                series2.getData().add(new XYChart.Data("2", temp.getSecondSPI()));
                series2.getData().add(new XYChart.Data("3", temp.getThirdSPI()));
                
                bc.setBarGap(5);
                bc.setCategoryGap(20);
                
                bc.getData().addAll(series1, series2);
                bc.setTitle("CPI & SPI (Bar Chart)");
                 
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
                
                tcpiLabel.setText("  TCPI="+sum.getTcpiValue());
                tcpiLabel.setTextFill(Color.WHITE);
                tspiLabel.setText("  TSPI="+sum.getTspiValue());
                tspiLabel.setTextFill(Color.WHITE);
                
                FlowPane pane = new FlowPane();
                
                pane.getChildren().addAll(add, tcpiLabel, tspiLabel);
               
                add.setOnAction(new EventHandler<ActionEvent>(){
                    
                    @Override
                    public void handle(ActionEvent event) {
                        if(mainApp.getTableData().size()!=0){
                            boolean saveClicked = showCpiDialog();
                            if(saveClicked){
                                mainApp.Refresh();
                            }
                        }
                        else{
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

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}
        
        public TabPane getTabPane(){
            return tabPane;
        }

	public void setMainApp(Content mainApp) {
		this.mainApp = mainApp;
	}

	public void setActivitySum(Activity sum) {
		this.sum = sum;
	}

	public void autoZoom(CurvedFittedAreaChart chart) {
		chart.getXAxis().setAutoRanging(true);
		chart.getYAxis().setAutoRanging(true);
		ObservableList<XYChart.Series<Number, Number>> data = chart.getData();
		chart.setData(FXCollections.<XYChart.Series<Number, Number>> emptyObservableList());
		chart.setData(data);
	}
        
        public boolean showCpiDialog(){
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("view/CpiDialog.fxml"));
                AnchorPane page = (AnchorPane) loader.load();
                
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Vlerat e CPI dhe SPI");
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
}

//EV JESHILE, PV BLU, AC TE KUQE

//TCPI = (BAC - EV) / (BAC - AC)

//TSPI = (BAC - EV) / (BAC - PV)