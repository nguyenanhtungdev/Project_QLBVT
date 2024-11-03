package other;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BieuDo {

	public static ChartPanel taoBieuDoDuong(DefaultCategoryDataset dataset, String tenBieuDo, String dongX,
			String cotY) {
		JFreeChart chart = ChartFactory.createLineChart(tenBieuDo, dongX, cotY, dataset, PlotOrientation.VERTICAL, true,
				true, true);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(0, 0, 0, 0));
		plot.setOutlineVisible(false);

		plot.setRangeGridlinePaint(Color.GRAY);
		plot.setDomainGridlinePaint(Color.GRAY);
		plot.setDomainGridlinesVisible(true);

		plot.getDomainAxis().setLabelFont(new Font("Arial", Font.PLAIN, 14));
		plot.getDomainAxis().setTickLabelFont(new Font("Arial", Font.PLAIN, 12));

		plot.getRangeAxis().setLabelFont(new Font("Arial", Font.PLAIN, 14));
		plot.getRangeAxis().setTickLabelFont(new Font("Arial", Font.PLAIN, 12));

		plot.getRenderer().setSeriesPaint(0, ColorConstants.PRIMARY_COLOR);

		ChartPanel pChart = new ChartPanel(chart);
		pChart.setMaximumDrawHeight(2048);
		pChart.setMaximumDrawWidth(1920);

		return pChart;
	}

}
