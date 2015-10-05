package main;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TabPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import main.model.Activity;

public class ChartTabController {

    TabPane tabPane;
    private Activity sum;
    private Main mainApp;

    public ChartTabController() {
    }

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

        final NumberAxis xAxis1 = new NumberAxis(0, 33, 3);
        final NumberAxis yAxis1 = new NumberAxis();

        final CurvedFittedAreaChart areaChart1 = new CurvedFittedAreaChart(xAxis1, yAxis1);
        areaChart1.setTitle("Grafik");
        xAxis1.setLabel("Koha");
        yAxis1.setLabel("Kosto");

        final NumberAxis xAxis2 = new NumberAxis(0, 33, 3);
        final NumberAxis yAxis2 = new NumberAxis();

        final CurvedFittedAreaChart areaChart2 = new CurvedFittedAreaChart(xAxis2, yAxis2);
        areaChart2.setTitle("Temperature Monitoring (in Degrees F)");
        xAxis2.setLabel("X");
        yAxis2.setLabel("Y");

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

        areaChart1.getData().addAll(seriesEV);
        areaChart1.getData().addAll(seriesPV);
        areaChart1.getData().addAll(seriesAC);

        // First chart
        GridPane.setHalignment(areaChart1, HPos.RIGHT);
        gridPane.add(areaChart1, 0, 0);

        // Second chart
        GridPane.setHalignment(areaChart2, HPos.RIGHT);
        gridPane.add(areaChart2, 1, 0);

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

        areaChart2.getData().addAll(seriesMay);

        tabPane.getTabs().get(0).setContent(gridPane);
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setActivitySum(Activity sum) {
        this.sum = sum;
    }
}
