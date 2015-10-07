package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import jfxutils.chart.ChartPanManager;
import jfxutils.chart.JFXChartUtil;
import jfxutils.chart.StableTicksAxis;
import main.model.Activity;

public class ChartTabController {

	TabPane tabPane;
	private Activity sum;
	private Content mainApp;

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

		final StableTicksAxis xAxis2 = new StableTicksAxis();
		final StableTicksAxis yAxis2 = new StableTicksAxis();

		final CurvedFittedAreaChart areaChart1 = new CurvedFittedAreaChart(xAxis1, yAxis1);
		areaChart1.setTitle("Grafik");
		xAxis1.setLabel("Koha");
		yAxis1.setLabel("Kosto");

		final CurvedFittedAreaChart areaChart2 = new CurvedFittedAreaChart(xAxis2, yAxis2);
		areaChart2.setTitle("Temperature Monitoring (in Degrees F)");
		xAxis2.setLabel("X");
		yAxis2.setLabel("Y");

		// First chart
		GridPane.setHalignment(areaChart1, HPos.RIGHT);
		gridPane.add(areaChart1, 0, 0);

		// Second chart
		GridPane.setHalignment(areaChart2, HPos.RIGHT);
		gridPane.add(areaChart2, 1, 0);

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

		XYChart.Series seriesAC = new XYChart.Series();
		seriesAC.setName("AC");

		seriesAC.getData().add(new XYChart.Data(0, 0));
		seriesAC.getData().add(new XYChart.Data(12, sum.getAC() / 2));
		seriesAC.getData().add(new XYChart.Data(20, sum.getAC()));

		XYChart.Series seriesMay = new XYChart.Series();
		seriesMay.setName("May");
		seriesMay.getData().add(new XYChart.Data(1, 20));
		seriesMay.getData().add(new XYChart.Data(3, 15));
		seriesMay.getData().add(new XYChart.Data(6, 13));
		seriesMay.getData().add(new XYChart.Data(9, 12));
		seriesMay.getData().add(new XYChart.Data(12, 14));
		seriesMay.getData().add(new XYChart.Data(15, 18));
		seriesMay.getData().add(new XYChart.Data(18, 25));
		seriesMay.getData().add(new XYChart.Data(21, 25));
		seriesMay.getData().add(new XYChart.Data(24, 23));
		seriesMay.getData().add(new XYChart.Data(27, 26));
		seriesMay.getData().add(new XYChart.Data(31, 26));

		XYChart.Series series = new XYChart.Series();
		series.setName("June");

		series.getData().add(new XYChart.Data(0, 0));
		series.getData().add(new XYChart.Data(3, 3));
		series.getData().add(new XYChart.Data(5, 5));

		areaChart1.getData().addAll(seriesEV, seriesPV, seriesAC);
		areaChart2.getData().addAll(seriesMay, series);

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

		areaChart2.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				double xStart = areaChart2.getXAxis().getLocalToParentTransform().getTx();
				double axisXRelativeMousePosition = mouseEvent.getX() - xStart;
			}
		});

		ChartPanManager panner2 = new ChartPanManager(areaChart2);
		panner2.setMouseFilter(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.PRIMARY) {
					// let it through
				} 
                                else if(mouseEvent.getButton()==MouseButton.SECONDARY)
                                        autoZoom(areaChart2);
                                else
                                    mouseEvent.consume();
			}
		});
		panner2.start();

		JFXChartUtil.setupZooming(areaChart2, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() != MouseButton.MIDDLE || mouseEvent.isShortcutDown())
					mouseEvent.consume();
                                else if(mouseEvent.getButton()==MouseButton.SECONDARY)
                                        autoZoom(areaChart2);
			}
		});

		tabPane.getTabs().get(0).setContent(gridPane);
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
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
}
