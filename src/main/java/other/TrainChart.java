package other;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYDataset;

import constant.ColorConstants;
import constant.FontConstants;

public class TrainChart {

	private JFreeChart chart;
	private ChartPanel panel;

	public TrainChart(JFreeChart chart, ChartPanel panel) {
		this.chart = chart;
		this.panel = panel;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public ChartPanel getPanel() {
		return panel;
	}

	public void setChartTitle(String title) {
		chart.setTitle(title);
	}

	public void addChartSubtitle(String subTitle) {
		chart.addSubtitle(new TextTitle(subTitle, FontConstants.CAPTION));
	}

	public void clearSubtitles() {
		chart.clearSubtitles();
	}

	public void setCategoryAxisLabel(String label) {
		CategoryPlot plot = chart.getCategoryPlot();
		plot.getDomainAxis().setLabel(label);
	}

	public static TrainChart createLineChart(CategoryDataset dataset, String title, String categoryAxisLabel,
			String valueAxisLabel) {
		JFreeChart chart = ChartFactory.createLineChart(title, categoryAxisLabel, valueAxisLabel, dataset,
				PlotOrientation.VERTICAL, true, true, false);

		chart.getTitle().setFont(FontConstants.HEADING_5);
		chart.getTitle().setPaint(ColorConstants.SECONDARY_COLOR);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(255, 255, 255, 0));
		plot.setOutlineVisible(false);

		plot.setRangeGridlinePaint(Color.GRAY);
		plot.setDomainGridlinePaint(Color.GRAY);
		plot.setDomainGridlinesVisible(true);

		plot.setNoDataMessage("Không có dữ liệu");
		plot.setNoDataMessageFont(FontConstants.TEXT);
		plot.setNoDataMessagePaint(Color.GRAY);

		plot.getDomainAxis().setLabelFont(FontConstants.TEXT);
		plot.getDomainAxis().setTickLabelFont(FontConstants.CAPTION);
		plot.getRangeAxis().setLabelFont(FontConstants.TEXT);
		plot.getRangeAxis().setTickLabelFont(FontConstants.CAPTION);

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, ColorConstants.SECONDARY_COLOR);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(2, ColorConstants.DANGER_COLOR);

		ChartPanel pChart = new ChartPanel(chart);
		pChart.setInitialDelay(0);
		pChart.setMaximumDrawHeight(2048);
		pChart.setMaximumDrawWidth(1920);

		return new TrainChart(chart, pChart);
	}

	public static TrainChart createBarChart(CategoryDataset dataset, String title, String categoryAxisLabel,
			String valueAxisLabel) {
		JFreeChart chart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, dataset,
				PlotOrientation.VERTICAL, true, true, false);

		chart.getTitle().setFont(FontConstants.HEADING_5);
		chart.getTitle().setPaint(ColorConstants.SECONDARY_COLOR);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(255, 255, 255, 0));
		plot.setOutlineVisible(false);

		plot.setRangeGridlinePaint(Color.GRAY);
		plot.setDomainGridlinePaint(Color.GRAY);
		plot.setDomainGridlinesVisible(true);

		plot.setNoDataMessage("Không có dữ liệu");
		plot.setNoDataMessageFont(FontConstants.TEXT);
		plot.setNoDataMessagePaint(Color.GRAY);

		plot.getDomainAxis().setLabelFont(FontConstants.TEXT);
		plot.getDomainAxis().setTickLabelFont(FontConstants.CAPTION);
		plot.getRangeAxis().setLabelFont(FontConstants.TEXT);
		plot.getRangeAxis().setTickLabelFont(FontConstants.CAPTION);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, ColorConstants.SECONDARY_COLOR);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(2, ColorConstants.DANGER_COLOR);

		renderer.setBarPainter(new StandardBarPainter());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		ChartPanel pChart = new ChartPanel(chart);
		pChart.setInitialDelay(0);
		pChart.setMaximumDrawHeight(2048);
		pChart.setMaximumDrawWidth(1920);

		return new TrainChart(chart, pChart);
	}

	public static TrainChart createTimeSeriesChart(XYDataset dataset, String title, String timeAxisLabel,
			String valueAxisLabel) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(title, timeAxisLabel, valueAxisLabel, dataset, true, true,
				false);

		chart.getTitle().setFont(FontConstants.HEADING_5);
		chart.getTitle().setPaint(ColorConstants.SECONDARY_COLOR);

		XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(new Color(255, 255, 255, 0));
		plot.setOutlineVisible(false);

		plot.setRangeGridlinePaint(Color.GRAY);
		plot.setDomainGridlinePaint(Color.GRAY);
		plot.setDomainGridlinesVisible(true);

		plot.setNoDataMessage("Không có dữ liệu");
		plot.setNoDataMessageFont(FontConstants.TEXT);
		plot.setNoDataMessagePaint(Color.GRAY);

		plot.getDomainAxis().setLabelFont(FontConstants.TEXT);
		plot.getDomainAxis().setTickLabelFont(FontConstants.CAPTION);
		plot.getRangeAxis().setLabelFont(FontConstants.TEXT);
		plot.getRangeAxis().setTickLabelFont(FontConstants.CAPTION);

		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, ColorConstants.SECONDARY_COLOR);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(2, ColorConstants.DANGER_COLOR);

		ChartPanel pChart = new ChartPanel(chart);
//		pChart.mouseZoomable(true);
		pChart.setInitialDelay(0);
		pChart.setMaximumDrawHeight(2048);
		pChart.setMaximumDrawWidth(1920);

		return new TrainChart(chart, pChart);
	}

}
