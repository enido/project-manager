package main.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.IntervalCategoryDataset;

import java.awt.*;

/**
 * Created by Marin Kaçaj on 10/6/2015.
 * Builds a Gantt chart.
 */
public class GanttChartBuilder {

    private String title;
    private String xLabel;
    private String yLabel;

    public GanttChartBuilder(String title, String xLabel, String yLabel) {
        this.title = title;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
    }

    public ChartPanel buildChartPanel(IntervalCategoryDataset dataSet) {
        JFreeChart chart = ChartFactory.createGanttChart(title, xLabel, yLabel, dataSet, true, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(500, 270));
        return panel;
    }
}
