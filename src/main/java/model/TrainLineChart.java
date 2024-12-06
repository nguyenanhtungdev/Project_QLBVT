package model;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class TrainLineChart {

	private JFreeChart chart;
	private ChartPanel panel;

	public TrainLineChart(JFreeChart chart, ChartPanel panel) {
		this.chart = chart;
		this.panel = panel;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public ChartPanel getPanel() {
		return panel;
	}

}
