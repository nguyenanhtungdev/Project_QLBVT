package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.Box;
import javax.swing.JPanel;

import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.category.DefaultCategoryDataset;

import constant.FontConstants;
import controller.ThongKe_Controller;
import model.CaLam;
import model.StatisticData;
import other.TrainChart;
import other.TrainPanel;
import other.TrainScrollPane;
import other.PrimaryButton;
import other.RoundedButton;
import other.TrainStatisticCard;
import other.TrainTitle;
import util.PrinterUtils;

public class ThongKeTongQuan_View extends View implements Printable {

	private static final long serialVersionUID = 4640477356217922276L;

	private final DefaultCategoryDataset datasetDoanhThu = new DefaultCategoryDataset();
	private final DefaultCategoryDataset datasetHoaDon = new DefaultCategoryDataset();
	private final DefaultCategoryDataset datasetVeBan = new DefaultCategoryDataset();
	private final DefaultCategoryDataset datasetVeHuy = new DefaultCategoryDataset();

	private Box pNorth;
	private Box pSouth;
	private RoundedButton btnIn;

	private TrainTitle tToday;
	private JPanel pCards;
	private TrainStatisticCard cDoanhThu;
	private TrainStatisticCard cSLHoaDon;
	private TrainStatisticCard cSLVeBan;
	private TrainStatisticCard cSLVeHuy;
	private TrainStatisticCard cSLKhuyenMai;

	private TrainPanel pCenter;
	private TrainChart lcDoanhThu;
	private TrainChart lcHoaDon;
	private TrainChart lcVeBan;
	private TrainChart lcVeHuy;

	public ThongKeTongQuan_View(String name, String imagePath) {
		super(name, imagePath);

		setContentPane(new TrainPanel(new BorderLayout(16, 16), new Insets(16, 16, 16, 16)));

		// NORTH
		add(pNorth = Box.createVerticalBox(), BorderLayout.NORTH);
		pNorth.add(tToday = new TrainTitle("Tổng quan hôm nay (dd/MM/yyyy)"));
		pNorth.add(Box.createVerticalStrut(8));
		pNorth.add(new TrainScrollPane(pCards = new TrainPanel(new GridLayout(1, 0, 8, 8))));

		pCards.add(cDoanhThu = new TrainStatisticCard("Doanh thu", "0"));
		pCards.add(cSLHoaDon = new TrainStatisticCard("Số lượng hóa đơn", "0"));
		pCards.add(cSLVeBan = new TrainStatisticCard("Vé bán được", "0"));
		pCards.add(cSLVeHuy = new TrainStatisticCard("Vé đã hủy", "0"));
		pCards.add(cSLKhuyenMai = new TrainStatisticCard("Khuyến mãi đã dùng", "0"));

		// CENTER
		add(pCenter = new TrainPanel(new GridLayout(2, 2, 16, 16)));
		lcDoanhThu = TrainChart.createLineChart(datasetDoanhThu, "Doanh thu theo ngày", "Ngày", "Doanh thu (VNĐ)");
		lcHoaDon = TrainChart.createLineChart(datasetHoaDon, "Số lượng hóa đơn theo ngày", "Ngày", "Số lượng hóa đơn");
		lcVeBan = TrainChart.createLineChart(datasetVeBan, "Số lượng vé bán theo ngày", "Ngày", "Số lượng vé bán ra");
		lcVeHuy = TrainChart.createLineChart(datasetVeHuy, "Số lượng vé hủy theo ngày", "Ngày", "Số lượng vé đã hủy");

		pCenter.add(lcDoanhThu.getPanel());
		pCenter.add(lcHoaDon.getPanel());
		pCenter.add(lcVeBan.getPanel());
		pCenter.add(lcVeHuy.getPanel());

		// SOUTH
		add(pSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pSouth.add(btnIn = new PrimaryButton("In thống kê"));

		btnIn.addActionListener(e -> PrinterUtils.print(this, "In thống kê tổng quan"));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1800, 1100);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
	}

	public void loadSummary(StatisticData data) {
		String today = LocalDate.now().format(ThongKe_Controller.FMT_DATE);
		if (data.getTarget() instanceof CaLam caLam) {
			LocalTime start = caLam.getThoiGianBatDau();
			LocalTime end = caLam.getThoiGianKetThuc();
			tToday.setText("Tổng quan trong ca làm việc (từ " + start.format(ThongKe_Controller.FMT_TIME) + " đến "
					+ end.format(ThongKe_Controller.FMT_TIME) + " ngày " + today + ")");
		} else {
			tToday.setText("Tổng quan hôm nay (" + today + ")");
		}

		cDoanhThu.setValueText(ThongKe_Controller.FMT_MONEY.format(data.getDoanhThu()));
		cSLHoaDon.setValueText(Long.toString(data.getSoLuongHoaDon()));
		cSLVeBan.setValueText(Long.toString(data.getSoLuongVeBan()));
		cSLVeHuy.setValueText(Long.toString(data.getSoLuongVeHuy()));
		cSLKhuyenMai.setValueText(Long.toString(data.getSoLuongKhuyenMai()));
	}

	public void loadHourStatistic(List<StatisticData> data, LocalTime from, LocalTime to) {
		clearCharts();

		lcDoanhThu.getChart().setTitle("Doanh thu trong ca làm việc");
		lcHoaDon.getChart().setTitle("Số lượng hóa đơn trong ca làm việc");
		lcVeBan.getChart().setTitle("Số lượng vé bán ra trong ca làm việc");
		lcVeHuy.getChart().setTitle("Số lượng vé được hủy trong ca làm việc");

		String strFrom = from.format(ThongKe_Controller.FMT_TIME);
		String strTo = to.format(ThongKe_Controller.FMT_TIME);

		Title subTitle = new TextTitle("Từ lúc " + strFrom + " đến lúc " + strTo, FontConstants.CAPTION);
		lcDoanhThu.getChart().addSubtitle(subTitle);
		lcHoaDon.getChart().addSubtitle(subTitle);
		lcVeBan.getChart().addSubtitle(subTitle);
		lcVeHuy.getChart().addSubtitle(subTitle);

		lcDoanhThu.getChart().getCategoryPlot().getDomainAxis().setLabel("Giờ");
		lcHoaDon.getChart().getCategoryPlot().getDomainAxis().setLabel("Giờ");
		lcVeBan.getChart().getCategoryPlot().getDomainAxis().setLabel("Giờ");
		lcVeHuy.getChart().getCategoryPlot().getDomainAxis().setLabel("Giờ");

		if (data == null) {
			return;
		}

		int index = 0;
		LocalTime current = from;
		while (current.isBefore(to)) {
			String strCurrent = current.format(ThongKe_Controller.FMT_TIME);
			if (index < data.size() && data.get(index).getTarget().equals(current)) {
				StatisticData currentData = data.get(index);

				datasetDoanhThu.addValue(currentData.getDoanhThu(), "Doanh thu", strCurrent);
				datasetHoaDon.addValue(currentData.getSoLuongHoaDon(), "Hóa đơn", strCurrent);
				datasetVeBan.addValue(currentData.getSoLuongVeBan(), "Vé bán", strCurrent);
				datasetVeHuy.addValue(currentData.getSoLuongVeHuy(), "Vé hủy", strCurrent);
				index++;
			} else {
				datasetDoanhThu.addValue(0, "Doanh thu", strCurrent);
				datasetHoaDon.addValue(0, "Hóa đơn", strCurrent);
				datasetVeBan.addValue(0, "Vé bán", strCurrent);
				datasetVeHuy.addValue(0, "Vé hủy", strCurrent);
			}
			current = current.plusHours(1);
		}
	}

	public void loadWeekStatistic(List<StatisticData> data, LocalDate from, LocalDate to) {
		clearCharts();

		lcDoanhThu.getChart().setTitle("Doanh thu theo ngày");
		lcHoaDon.getChart().setTitle("Số lượng hóa đơn theo ngày");
		lcVeBan.getChart().setTitle("Số lượng vé bán ra theo ngày");
		lcVeHuy.getChart().setTitle("Số lượng vé được hủy theo ngày");

		String strFrom = from.format(ThongKe_Controller.FMT_DATE);
		String strTo = to.format(ThongKe_Controller.FMT_DATE);

		Title title = new TextTitle("Từ ngày " + strFrom + " đến ngày " + strTo, FontConstants.CAPTION);
		lcDoanhThu.getChart().addSubtitle(title);
		lcHoaDon.getChart().addSubtitle(title);
		lcVeBan.getChart().addSubtitle(title);
		lcVeHuy.getChart().addSubtitle(title);

		lcDoanhThu.getChart().getCategoryPlot().getDomainAxis().setLabel("Ngày");
		lcHoaDon.getChart().getCategoryPlot().getDomainAxis().setLabel("Ngày");
		lcVeBan.getChart().getCategoryPlot().getDomainAxis().setLabel("Ngày");
		lcVeHuy.getChart().getCategoryPlot().getDomainAxis().setLabel("Ngày");

		if (data == null) {
			return;
		}

		int index = 0;
		LocalDate current = from;
		while (current.isBefore(to.plusDays(1))) {
			String strCurrent = current.format(ThongKe_Controller.FMT_DATE);
			if (index < data.size() && data.get(index).getTarget().equals(current)) {
				StatisticData currentData = data.get(index);

				datasetDoanhThu.addValue(currentData.getDoanhThu(), "Doanh thu", strCurrent);
				datasetHoaDon.addValue(currentData.getSoLuongHoaDon(), "Hóa đơn", strCurrent);
				datasetVeBan.addValue(currentData.getSoLuongVeBan(), "Vé bán", strCurrent);
				datasetVeHuy.addValue(currentData.getSoLuongVeHuy(), "Vé hủy", strCurrent);
				index++;
			} else {
				datasetDoanhThu.addValue(0, "Doanh thu", strCurrent);
				datasetHoaDon.addValue(0, "Hóa đơn", strCurrent);
				datasetVeBan.addValue(0, "Vé bán", strCurrent);
				datasetVeHuy.addValue(0, "Vé hủy", strCurrent);
			}
			current = current.plusDays(1);
		}
	}

	private void clearCharts() {
		datasetDoanhThu.clear();
		datasetHoaDon.clear();
		datasetVeBan.clear();
		datasetVeHuy.clear();

		lcDoanhThu.getChart().clearSubtitles();
		lcHoaDon.getChart().clearSubtitles();
		lcVeBan.getChart().clearSubtitles();
		lcVeHuy.getChart().clearSubtitles();
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		double pageWidth = pageFormat.getImageableWidth();
		double pageHeight = pageFormat.getImageableHeight();
		double scaleX = pageWidth / getContentPane().getWidth();
		g2d.scale(scaleX, scaleX);

		double scaledPageHeight = pageHeight / scaleX;
		int totalPages = (int) Math.ceil(getContentPane().getHeight() / scaledPageHeight);

		if (pageIndex >= totalPages) {
			return NO_SUCH_PAGE;
		}

		g2d.translate(pageFormat.getImageableX(), -pageIndex * scaledPageHeight);

		pSouth.setVisible(false);
		getContentPane().printAll(g2d);
		pSouth.setVisible(true);

		return PAGE_EXISTS;
	}

}
