package other;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import constant.ColorConstants;
import constant.FontConstants;
import model.TrainLineChart;

public class TrainChart {

	/**
	 * Tạo biểu đồ đường biểu diễn theo chiều ngang
	 * 
	 * @param dataset           tập dữ liệu
	 * @param title             tiêu đề (tên) biểu đồ
	 * @param categoryAxisLabel nhãn trục phân loại (x)
	 * @param valueAxisLabel    nhãn trục giá trị (y)
	 * @return biểu đồ
	 */
	public static TrainLineChart createLineChart(DefaultCategoryDataset dataset, String title, String categoryAxisLabel,
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

		ChartPanel pChart = new ChartPanel(chart);
		pChart.setInitialDelay(0);
		pChart.setMaximumDrawHeight(2048);
		pChart.setMaximumDrawWidth(1920);

		return new TrainLineChart(chart, pChart);
	}

}
