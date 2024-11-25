package other;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import constant.FontConstants;

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
	public static ChartPanel createLineChart(DefaultCategoryDataset dataset, String title, String categoryAxisLabel,
			String valueAxisLabel) {
		JFreeChart chart = ChartFactory.createLineChart(title, categoryAxisLabel, valueAxisLabel, dataset,
				PlotOrientation.VERTICAL, true, true, true);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(255, 255, 255, 0));
		plot.setOutlineVisible(false);

		plot.setRangeGridlinePaint(Color.GRAY);
		plot.setDomainGridlinePaint(Color.GRAY);
		plot.setDomainGridlinesVisible(true);

		plot.getDomainAxis().setLabelFont(FontConstants.TEXT);
		plot.getDomainAxis().setTickLabelFont(FontConstants.CAPTION);

		plot.getRangeAxis().setLabelFont(FontConstants.TEXT);
		plot.getRangeAxis().setTickLabelFont(FontConstants.CAPTION);

		plot.getRenderer().setSeriesPaint(0, ColorConstants.SECONDARY_COLOR); // ACCENT was used in original version

		ChartPanel pChart = new ChartPanel(chart);
		pChart.setMaximumDrawHeight(2048);
		pChart.setMaximumDrawWidth(1920);

		return pChart;
	}

}
